package ang.neggaw.batchs.stepItems;

import ang.neggaw.batchs.models.Film;
import ang.neggaw.batchs.models.FilmsByYears;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by:
 *
 * @author ANG
 * @since 12-08-2021 12:52
 */

@Component
public class FilmsByYearsItemProcessor implements ItemProcessor<Film, FilmsByYears> {

    @Override
    public FilmsByYears process(Film film) throws Exception {

        int year = Objects.equals(film.getYear(), "") ? 0 : Integer.parseInt(film.getYear());
        if ( year >= 1990 && year < 2000) {
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
