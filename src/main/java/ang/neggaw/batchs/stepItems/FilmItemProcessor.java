package ang.neggaw.batchs.stepItems;

import ang.neggaw.batchs.models.Film;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Created by:
 *
 * @author ANG
 * @since 12-08-2021 12:52
 */

@Component
@Primary
public class FilmItemProcessor implements ItemProcessor<Film, Film> {

    @Override
    public Film process(Film film) throws Exception {

        return Film.builder()
                .title(film.getTitle())
                .length(film.getLength())
                .year(film.getYear())
                .actor(film.getActor())
                .actress(film.getActress())
                .director(film.getDirector())
                .popularity(film.getPopularity())
                .awards(film.getAwards())
                .image(film.getImage())
                .build();
    }
}
