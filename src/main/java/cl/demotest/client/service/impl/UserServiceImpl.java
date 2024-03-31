package cl.demotest.client.service.impl;

import cl.demotest.client.dto.UserRequestDTO;
import cl.demotest.client.dto.UserResponseDTO;
import cl.demotest.client.entity.Users;
import cl.demotest.client.exception.ConflictException;
import cl.demotest.client.mapper.UserMapper;
import cl.demotest.client.repository.UserRepository;
import cl.demotest.client.service.UserService;
import cl.demotest.client.util.Constants;
import cl.demotest.client.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static cl.demotest.client.util.Utils.getJWTToken;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {

        Utils.verifyForConflict(
                userRepository.findByEmail(userRequestDTO.getEmail()).isPresent(),
                Constants.THE_EMAIL_ALREADY_IS_USED);

        userRequestDTO.setPassword(bCryptPasswordEncoder.encode(userRequestDTO.getPassword()));

        Users userEntity = userRepository.save(
                userMapper.userDTOToEntity(userRequestDTO));


        UserResponseDTO response = userMapper.userEntityToDto(userEntity);

        response.setToken(getJWTToken(userRequestDTO.getEmail()));

        return response;
    }



    @Override
    public String prueba() {
        return "prueba";
    }

    @Override
    public UserResponseDTO findUserById(UUID uuid) {
        Users userEntity = userRepository.findUserById(uuid)
                .orElseThrow(() -> new ConflictException(Constants.THE_USER_NOT_EXIST));

        return userMapper.userEntityToDto(userEntity);
    }

    @Override
    public UserResponseDTO updateUserById(UserRequestDTO updatedUserRequestDTO, UUID uuid) {
        Users userEntity = userRepository.findUserById(uuid)
                .orElseThrow(() -> new ConflictException(Constants.THE_USER_NOT_EXIST));

        return userMapper.userEntityToDto(
                userRepository.save(userMapper.userUpdateDTOToEntity(
                        updatedUserRequestDTO, userEntity)));
    }


    @Override
    @Transactional
    public String deleteUserById(UUID uuid) {

        Utils.verifyForNotFound(
                userRepository.findUserById(uuid).isEmpty(),
                Constants.THE_USER_NOT_EXIST);

        userRepository.deleteByid(uuid);

        return Constants.DELETED_DOCUMENT;
    }


}
