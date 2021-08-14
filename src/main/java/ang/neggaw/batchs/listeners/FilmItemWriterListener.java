package ang.neggaw.batchs.listeners;

import ang.neggaw.batchs.models.Film;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by:
 *
 * @author ANG
 * @since 13-08-2021 17:58
 */

@Log4j2
@Component
public class FilmItemWriterListener implements ItemWriteListener<Film> {

    @Override
    public void beforeWrite(List<? extends Film> list) {
        log.info("before write...");
    }

    @Override
    public void afterWrite(List<? extends Film> list) {
        log.info("after write ...");
        log.info("*******************************");
        list.forEach(System.out::println);
        log.info("*******************************");
    }

    @Override
    public void onWriteError(Exception e, List<? extends Film> list) {
        log.error("onWriteError: " + e.getMessage());
    }
}
