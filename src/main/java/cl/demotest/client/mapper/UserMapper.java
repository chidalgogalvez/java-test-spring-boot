package cl.demotest.client.mapper;

import cl.demotest.client.dto.UserRequestDTO;
import cl.demotest.client.dto.UserResponseDTO;
import cl.demotest.client.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Date;

@Mapper(componentModel = "spring", imports = Date.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "isActive", constant = "true"),
            @Mapping( expression = "java(new Date())", target = "createAt"),
            @Mapping( expression = "java(new Date())", target = "updateAt")
    })
    Users userDTOToEntity(UserRequestDTO userRequestDTO);

    UserResponseDTO userEntityToDto(Users userEntity);


    @Mappings({
            @Mapping( expression = "java(new Date())", target = "updateAt")
    })
    Users userUpdateDTOToEntity(UserRequestDTO updatedUserRequestDTO,
                                   @MappingTarget Users userEntity);
}
