package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.converter.FoodConverter;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
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
@RequestMapping("/foods")
public class FoodController {
	@Autowired
	FoodServiceImpl foodService;


	@GetMapping(path="/get/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		FoodDto foodDto = foodService.getFoodById(id);
		FoodDetailsResponse foodDetailsResponse = FoodDetailsResponse.builder()
				.foodName(foodDto.getFoodName())
				.foodCategory(foodDto.getFoodCategory())
				.foodPrice(foodDto.getFoodPrice())
				.foodId(foodDto.getFoodId())
				.build();
		return foodDetailsResponse;
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) throws Exception {
		FoodDto foodDto = FoodConverter.foodRequestToDto(foodDetails);
		FoodDto foodDto1 = foodService.createFood(foodDto);
		FoodDetailsResponse foodDetailsResponse = FoodConverter.dtoToResponse(foodDto1);
		return foodDetailsResponse;
	}

	@PutMapping(path="/update/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto foodDto = FoodConverter.foodRequestToDto(foodDetails);
		FoodDto foodDto1 = foodService.updateFoodDetails(id,foodDto);
		FoodDetailsResponse foodDetailsResponse = FoodConverter.dtoToResponse(foodDto1);
		return foodDetailsResponse;
	}

	@DeleteMapping(path = "/delete/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		try
		{
			foodService.deleteFoodItem(id);
		}
		catch (Exception e)
		{
			OperationStatusModel operationStatusModel = OperationStatusModel.builder()
					.operationName(RequestOperationName.DELETE.toString())
					.operationResult(RequestOperationStatus.ERROR.toString())
					.build();
			e.printStackTrace();
			return operationStatusModel;

		}

		OperationStatusModel operationStatusModel = OperationStatusModel.builder()
				.operationName(RequestOperationName.DELETE.toString())
				.operationResult(RequestOperationStatus.SUCCESS.toString())
				.build();
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
		List<FoodDetailsResponse> foodDetailsResponseList = new ArrayList<>();
		List<FoodDto> foodDtoList = foodService.getFoods();
		for(FoodDto foodDto : foodDtoList)
		{
			FoodDetailsResponse foodDetailsResponse = FoodConverter.dtoToResponse(foodDto);
			foodDetailsResponseList.add(foodDetailsResponse);
		}
		return foodDetailsResponseList;

	}
}
