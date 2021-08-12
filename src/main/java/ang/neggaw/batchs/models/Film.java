package ang.neggaw.batchs.models;

import lombok.*;

import java.io.Serializable;

/**
 * Created by:
 *
 * @author ANG
 * @since 12-08-2021 12:50
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Film implements Serializable {

    private String year; // int

    private String length; // int

    private String title;

    private String subject;

    private String actor;

    private String actress;

    private String director;

    private String popularity;

    private String awards; // boolean

    private String  image;
}