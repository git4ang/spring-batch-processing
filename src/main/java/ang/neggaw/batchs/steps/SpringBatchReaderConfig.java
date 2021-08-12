package ang.neggaw.batchs.steps;

import ang.neggaw.batchs.models.Film;
import ang.neggaw.batchs.models.FilmOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Created by:
 *
 * @author ANG
 * @since 12-08-2021 12:52
 */

@RequiredArgsConstructor
@Component
public class SpringBatchReaderConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ItemReader<Film> filmItemReader;
    private final ItemProcessor<Film, FilmOutput> filmFilmOutputItemProcessor;
    private final ItemWriter<FilmOutput> filmOutputItemWriter;

    @Bean
    public Job myJobFilm() {
        Step step = stepBuilderFactory.get("step-data-film")
                .<Film, FilmOutput>chunk(100)
                .reader(filmItemReader)
                .processor(filmFilmOutputItemProcessor)
                .writer(filmOutputItemWriter)
                .build();
        return jobBuilderFactory.get("job-data-film")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Film> reader(@Value(value = "${inputFileFilm}") Resource resource) {

        FlatFileItemReader<Film> reader = new FlatFileItemReader<>();
        DefaultLineMapper<Film> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        BeanWrapperFieldSetMapper<Film> fieldSetMapper = new BeanWrapperFieldSetMapper<>();

        reader.setName("FILM-READER");
        reader.setResource(resource);
        reader.setLinesToSkip(2);

        tokenizer.setDelimiter(";");
        tokenizer.setStrict(false);
        tokenizer.setNames("year", "length", "title", "subject", "actor", "actress", "director", "popularity", "awards", "image");
        lineMapper.setLineTokenizer(tokenizer);

        fieldSetMapper.setTargetType(Film.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        reader.setLineMapper(lineMapper);
        return reader;
    }
}
