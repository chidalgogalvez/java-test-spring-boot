package cl.demotest.client.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhonesDTO implements Serializable {

    private static final long serialVersionUID = 3300716831106807502L;

    @NotEmpty(message = "The [number] field cannot be empty.")
    private String number;

    @NotEmpty(message = "The [citycode] field cannot be empty.")
    private String citycode;

    @NotEmpty(message = "The [countrycode] field cannot be empty.")
    private String countrycode;
}
