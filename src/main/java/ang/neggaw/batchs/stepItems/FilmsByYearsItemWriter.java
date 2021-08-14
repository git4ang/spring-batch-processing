package ang.neggaw.batchs.stepItems;

import ang.neggaw.batchs.models.FilmsByYears;
import ang.neggaw.batchs.repositories.FilmsByYearsRepository;
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
public class FilmsByYearsItemWriter implements ItemWriter<FilmsByYears> {

    private final FilmsByYearsRepository filmsByYearsRepository;

    @Override
    public void write(List<? extends FilmsByYears> list) throws Exception {
        filmsByYearsRepository.saveAll(list);
    }
}
