package ang.neggaw.batchs.stepItems;

import ang.neggaw.batchs.models.Film;
import ang.neggaw.batchs.models.FilmsByYears;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by:
 *
 * @author ANG
 * @since 12-08-2021 12:52
 */

@StepScope
@Component
public class FilmsByYearsItemProcessor implements ItemProcessor<Film, FilmsByYears> {

    @Value(value = "#{jobParameters['firstYear']}")
    private int firstYear;

    @Value(value = "#{jobParameters['secondYear']}")
    private int secondYear;

    @Override
    public FilmsByYears process(Film film) throws Exception {

        int year = Objects.equals(film.getYear(), "") ? 0 : Integer.parseInt(film.getYear());
        if ( year >= firstYear && year < secondYear) {
            return FilmsByYears.builder()
                .title(film.getTitle())
                .length(Objects.equals(film.getLength(), "") ? 0 : Integer.parseInt(film.getLength()))
                .year(year)
                .actor(film.getActor())
                .director(film.getDirector())
                .awards(Objects.equals(film.getAwards(), "yes"))
                .build();
        }
        return null;
    }
}
