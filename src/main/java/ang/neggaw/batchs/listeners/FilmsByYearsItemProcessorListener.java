package ang.neggaw.batchs.listeners;

import ang.neggaw.batchs.models.FilmsByYears;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

/**
 * Created by:
 *
 * @author ANG
 * @since 13-08-2021 17:57
 */

@Log4j2
@Component
public class FilmsByYearsItemProcessorListener implements ItemProcessListener<FilmsByYears, FilmsByYears> {

    @Override
    public void beforeProcess(FilmsByYears film) {
       log.info("Before process...");
    }

    @Override
    public void afterProcess(FilmsByYears film, FilmsByYears filmsByYears) {
        log.info("After process: " + film + " ==> " + filmsByYears);
    }

    @Override
    public void onProcessError(FilmsByYears film, Exception e) {
        log.error("onProcessError: " + film.toString() + " Error: " + e.getMessage());
    }
}
