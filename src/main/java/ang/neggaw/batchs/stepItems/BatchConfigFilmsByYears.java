package ang.neggaw.batchs.stepItems;

import ang.neggaw.batchs.listeners.*;
import ang.neggaw.batchs.models.Film;
import ang.neggaw.batchs.models.FilmsByYears;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by:
 *
 * @author ANG
 * @since 12-08-2021 12:52
 */

@RequiredArgsConstructor
@EnableBatchProcessing
@Configuration
public class BatchConfigFilmsByYears {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Qualifier(value = "filmsByYearsItemReader") private final ItemReader<Film> filmOutputItemReader;
    private final ItemProcessor<Film, FilmsByYears> filmOutputFilmOutputItemProcessor;
    private final ItemWriter<FilmsByYears> filmOutputByYearItemWriter;
    private final FilmsByYearsJobExecutionListener filmsByYearsJobExecutionListener;
    private final FilmsByYearsItemReaderListener filmsByYearsItemReaderListener;
    private final FilmsByYearsItemProcessorListener filmsByYearsItemProcessorListener;
    private final FilmsByYearsItemWriterListener filmsByYearsItemWriterListener;

    @Qualifier("stepFilms") private final Step stepFilms;

    @Bean
    public Step stepFilmsByYears() {
        return stepBuilderFactory.get("step-filmsByYears")
                .<Film, FilmsByYears>chunk(150)
                .reader(filmOutputItemReader)
                .processor(filmOutputFilmOutputItemProcessor)
                .writer(filmOutputByYearItemWriter)
                .listener(filmsByYearsItemReaderListener)
                .listener(filmsByYearsItemProcessorListener)
                .listener(filmsByYearsItemWriterListener)
                .build();
    }

    @Bean
    public Job jobFilmsByYears() {

        return jobBuilderFactory.get("job-filmsByYears")
                .incrementer(new RunIdIncrementer())
                .preventRestart()
                .listener(filmsByYearsJobExecutionListener)
                .start(stepFilms)
                .next(stepFilmsByYears())
                .build();
    }
}
