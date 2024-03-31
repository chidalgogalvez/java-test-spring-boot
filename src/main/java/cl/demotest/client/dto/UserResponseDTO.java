package cl.demotest.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {

  private UUID id;

  private String name;

  private String email;

  private String password;

  private String isActive;

  private Date createAt;

  private Date updateAt;

  private String token;

  private List<PhonesDTO> phones;

}
