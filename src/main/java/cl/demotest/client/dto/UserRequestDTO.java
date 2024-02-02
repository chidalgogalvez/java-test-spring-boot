package cl.demotest.client.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO implements Serializable {

    private static final long serialVersionUID = 2594834018431691067L;
    @NotEmpty(message = "The [name] field cannot be empty.")
    private String name;

    @NotEmpty(message = "The [email] field cannot be empty.")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    private String isActive;

    /**
     * Validation for password
     * Min 1 uppercase letter.
     * Min 1 lowercase letter.
     * Min 1 special character.
     * Min 1 number.
     * Min 8 characters.
     */
    @Size(min = 8, max = 20, message = "The [password] is not valid.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$")
    private String password;

    @Valid
    private List<PhonesDTO> phones;
}
