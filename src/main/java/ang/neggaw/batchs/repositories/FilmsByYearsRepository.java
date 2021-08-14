package ang.neggaw.batchs.repositories;

import ang.neggaw.batchs.models.FilmsByYears;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by:
 *
 * @author ANG
 * @since 12-08-2021 12:51
 */

@RepositoryRestResource
public interface FilmsByYearsRepository extends JpaRepository<FilmsByYears, Long> { }
