package ang.neggaw.batchs.stepItems;

import ang.neggaw.batchs.listeners.*;
import ang.neggaw.batchs.models.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * Created by:
 *
 * @author ANG
 * @since 12-08-2021 12:52
 */

@RequiredArgsConstructor
@EnableBatchProcessing
@Configuration
public class BatchConfigFilm {

    @Value(value = "${app4ang.file.header-names}")
    private String names;

    @Value(value = "${app4ang.file.input-file-name}")
    private Resource resource;

    @Value(value = "${app4ang.file.delimiter}")
    private String delimiter;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final JobRepository jobRepository;

    @Qualifier(value = "filmFlatFileItemReader") private final ItemReader<Film> filmItemReader;
    private final ItemProcessor<Film, Film> filmFilmItemProcessor;
    private final ItemWriter<Film> filmItemWriter;
    private final FilmJobExecutionListener filmJobExecutionListener;
    private final FilmItemReaderListener filmItemReaderListener;
    private final FilmItemProcessorListener filmItemProcessorListener;
    private final FilmItemWriterListener filmItemWriterListener;

    @Bean
    public JobLauncher jobLauncherFilm() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

    @Bean
    public Step stepFilms() {

        return stepBuilderFactory.get("step-film")
                .<Film, Film>chunk(100)
                .reader(filmItemReader)
                .processor(filmFilmItemProcessor)
                .writer(filmItemWriter)
                .listener(filmItemReaderListener)
                .listener(filmItemProcessorListener)
                .listener(filmItemWriterListener)
                .build();
    }

    @Bean
    public Job jobFilm() {

        return jobBuilderFactory.get("job-film")
                .incrementer(new RunIdIncrementer())
                .preventRestart()
                .listener(filmJobExecutionListener)
                .start(stepFilms())
//                .end()
                .build();
    }

    @Bean
    @Primary
    @StepScope
    public FlatFileItemReader<Film> filmFlatFileItemReader() {

        FlatFileItemReader<Film> reader = new FlatFileItemReader<>();
        DefaultLineMapper<Film> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        BeanWrapperFieldSetMapper<Film> fieldSetMapper = new BeanWrapperFieldSetMapper<>();

        reader.setName("FILM-READER");
        reader.setResource(resource);
        reader.setLinesToSkip(2);

        tokenizer.setDelimiter(delimiter);
        tokenizer.setStrict(false);
        tokenizer.setNames(names.split(";".strip()));
        lineMapper.setLineTokenizer(tokenizer);

        fieldSetMapper.setTargetType(Film.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        reader.setLineMapper(lineMapper);
        return reader;
    }
}
