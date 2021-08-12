package ang.neggaw.batchs.steps;

import ang.neggaw.batchs.models.FilmOutput;
import ang.neggaw.batchs.repositories.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by:
 *
 * @author ANG
 * @since 12-08-2021 12:52
 */

@RequiredArgsConstructor
@Component
public class FilmItemWriter implements ItemWriter<FilmOutput> {

    private final FilmRepository filmRepository;

    @Override
    public void write(List<? extends FilmOutput> list) throws Exception {
        filmRepository.saveAll(list);
    }
}
