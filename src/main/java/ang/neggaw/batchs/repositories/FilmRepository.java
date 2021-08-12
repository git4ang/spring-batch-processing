package ang.neggaw.batchs.repositories;

import ang.neggaw.batchs.models.Film;
import ang.neggaw.batchs.models.FilmOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by:
 *
 * @author ANG
 * @since 12-08-2021 12:51
 */

@RepositoryRestResource
public interface FilmRepository extends JpaRepository<FilmOutput, Long> { }
