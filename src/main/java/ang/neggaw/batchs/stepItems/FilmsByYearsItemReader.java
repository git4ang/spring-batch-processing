package ang.neggaw.batchs.stepItems;

import ang.neggaw.batchs.models.Film;
import ang.neggaw.batchs.models.FilmsByYears;
import ang.neggaw.batchs.repositories.FilmRepository;
import ang.neggaw.batchs.repositories.FilmsByYearsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Created by:
 *
 * @author ANG
 * @since 13-08-2021 17:26
 */

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class FilmsByYearsItemReader implements ItemReader<Film> {

    private final FilmRepository filmRepository;

    private Iterator<Film> filmIterator;

    @BeforeStep
    public void before(StepExecution stepExecution) {
        log.info("beforeReader...");
        filmIterator = filmRepository.findAll().iterator();
    }

    @Override
    public Film read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if(filmIterator != null && filmIterator.hasNext()) {
            return filmIterator.next();
        } else {
            return null;
        }
    }
}
