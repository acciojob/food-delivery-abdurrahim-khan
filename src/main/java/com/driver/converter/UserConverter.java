package com.driver.converter;

import com.driver.io.entity.UserEntity;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.UserResponse;
import com.driver.shared.dto.UserDto;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class UserConverter {
    public static UserDto userRequestToDto(UserDetailsRequestModel userDetailsRequestModel) throws Exception
    {
        UserDto userDto = UserDto.builder()
                .firstName(userDetailsRequestModel.getFirstName())
                .lastName(userDetailsRequestModel.getLastName())
                .email(userDetailsRequestModel.getEmail())
                .userId(UUID.randomUUID().toString())
                .build();
        return userDto;
    }
    public static UserResponse dtoToResponse(UserDto userDto) throws Exception
    {
        UserResponse userResponse = UserResponse.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .userId(userDto.getUserId())
                .build();
        return userResponse;
    }
    public static UserDto entityToDto(UserEntity userEntity) throws Exception
    {
        UserDto userDto = UserDto.builder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .userId(userEntity.getUserId())
                .id(userEntity.getId())
                .build();
        return  userDto;
    }
}
