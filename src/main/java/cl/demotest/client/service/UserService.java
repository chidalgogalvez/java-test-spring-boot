package cl.demotest.client.service;

import cl.demotest.client.dto.UserRequestDTO;
import cl.demotest.client.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface UserService {

    UserResponseDTO addUser(UserRequestDTO userRequestDTO);

    String prueba();

    UserResponseDTO findUserById(UUID uuid);

    UserResponseDTO updateUserById(UserRequestDTO updatedUserRequestDTO, UUID uuid);

    String deleteUserById(UUID uuid);
}
