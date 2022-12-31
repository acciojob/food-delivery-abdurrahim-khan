package com.driver.service.impl;

import com.driver.converter.UserConverter;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;
@Service
@ControllerAdvice
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto user) throws Exception {

//        try
//        {
//            if(userRepository.existsByEmail(user.getEmail()))
//            {
//                throw new Exception();
//            }
//        }
//        catch (Exception e)
//        {
//            throw new Exception("user already exists");
//        }
        UserEntity userEntity = UserEntity.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .userId(user.getUserId())
                .build();
        userRepository.save(userEntity);
        return getUser(user.getEmail());
    }

    @Override
    public UserDto getUser(String email) throws Exception {
//        try
//        {
//            if(!userRepository.existsByEmail(email))
//            {
//                throw new Exception();
//            }
//        }
//        catch (Exception e)
//        {
//            throw new Exception("invalid email");
//        }
        UserEntity userEntity = userRepository.findByEmail(email);
        UserDto userDto = UserConverter.entityToDto(userEntity);
        return userDto;

    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
//        try
//        {
//            if(!userRepository.existsByUserId(userId))
//            {
//                throw new Exception();
//            }
//        }
//        catch (Exception e)
//        {
//            throw new Exception("user does not exists");
//        }
        UserEntity userEntity = userRepository.findByUserId(userId);
        UserDto userDto = UserConverter.entityToDto(userEntity);
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
//        try
//        {
//            if(!userRepository.existsByUserId(userId))
//            {
//                throw new Exception();
//            }
//        }
//        catch (Exception e)
//        {
//            throw new Exception("user does not exists");
//        }

        UserEntity userEntity = userRepository.findByUserId(userId);
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userRepository.save(userEntity);

        UserDto userDto = UserConverter.entityToDto(userEntity);
        return userDto;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
//        try
//        {
//            if(!userRepository.existsByUserId(userId))
//            {
//                throw new Exception();
//            }
//        }
//        catch (Exception e)
//        {
//            throw new Exception("user does not exist");
//        }
        long id = userRepository.findByUserId(userId).getId();
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getUsers() throws Exception {
        List<UserEntity> userEntityList = (List<UserEntity>) userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(UserEntity userEntity : userEntityList)
        {
            UserDto userDto = UserConverter.entityToDto(userEntity);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}