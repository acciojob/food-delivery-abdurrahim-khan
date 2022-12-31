package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.converter.UserConverter;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.model.response.UserResponse;
import com.driver.service.impl.UserServiceImpl;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserServiceImpl userService;

	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable("id") String id) throws Exception{

		UserDto userDto = userService.getUserByUserId(id);
		UserResponse userResponse = UserConverter.dtoToResponse(userDto);
		return userResponse;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto1 = UserConverter.userRequestToDto(userDetails);
		UserDto userDto = userService.createUser(userDto1);
		UserResponse userResponse = UserConverter.dtoToResponse(userDto);
		return userResponse;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto1 = UserConverter.userRequestToDto(userDetails);
		UserDto userDto = userService.updateUser(id,userDto1);
		UserResponse userResponse = UserConverter.dtoToResponse(userDto);
		return userResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{
//		try
//		{
//			userService.deleteUser(id);
//		}
//		catch (Exception e)
//		{
//			OperationStatusModel operationStatusModel = OperationStatusModel.builder()
//					.operationName(RequestOperationName.DELETE.toString())
//					.operationResult(RequestOperationStatus.ERROR.toString())
//					.build();
//			e.printStackTrace();
//			return operationStatusModel;
//		}
		OperationStatusModel operationStatusModel = OperationStatusModel.builder()
				.operationName(RequestOperationName.DELETE.toString())
				.operationResult(RequestOperationStatus.SUCCESS.toString())
				.build();
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){
		List<UserDto> userDtoList = userService.getUsers();
		List<UserResponse> userResponseList = new ArrayList<>();
		for(UserDto userDto : userDtoList)
		{
			UserResponse userResponse = UserConverter.dtoToResponse(userDto);
			userResponseList.add(userResponse);
		}
		return userResponseList;
	}
	
}
