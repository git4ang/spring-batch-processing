package ang.neggaw.batchs.steps;

import ang.neggaw.batchs.models.Film;
import ang.neggaw.batchs.models.FilmOutput;
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
public class FilmItemProcessor implements ItemProcessor<Film, FilmOutput> {

    @Override
    public FilmOutput process(Film film) throws Exception {

        return FilmOutput.builder()
                .title(film.getTitle())
                .length(Objects.equals(film.getLength(), "") ? 0 : Integer.parseInt(film.getLength()))
                .year(film.getYear() == null ? 0 : Integer.parseInt(film.getYear()))
                .actor(film.getActor())
                .director(film.getDirector())
                .awards(Objects.equals(film.getAwards().toLowerCase(), "yes"))
                .build();
    }
}
