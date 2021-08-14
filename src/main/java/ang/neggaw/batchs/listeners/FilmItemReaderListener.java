package ang.neggaw.batchs.listeners;

import ang.neggaw.batchs.models.Film;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

/**
 * Created by:
 *
 * @author ANG
 * @since 13-08-2021 17:57
 */

@Log4j2
@Component
public class FilmItemReaderListener implements ItemReadListener<Film> {

    @Override
    public void beforeRead() {
       log.info("Before read...");
    }

    @Override
    public void afterRead(Film f) {
        log.info("After read: " + f.toString());
    }

    @Override
    public void onReadError(Exception e) {
        log.error("onReadError: " + e.getMessage());
    }
}
