package ang.neggaw.batchs.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by:
 *
 * @author ANG
 * @since 12-08-2021 12:51
 */

@Entity
@Table(name = "films_by_years")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FilmsByYears implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilm;

    private String title;

    private int length;

    private int year;

    private String actor;

    private String director;

    private Boolean awards;
}
