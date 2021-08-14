package ang.neggaw.batchs.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by:
 *
 * @author ANG
 * @since 12-08-2021 12:50
 */

@Entity
@Table(name = "films")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Film implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilm;

    private String year; // Integer

    private String length; // Integer

    private String title;

    private String subject;

    private String actor;

    private String actress;

    private String director;

    private String popularity;

    private String awards; // Boolean

    private String  image;
}