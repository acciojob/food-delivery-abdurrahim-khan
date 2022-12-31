package com.driver.converter;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.shared.dto.FoodDto;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class FoodConverter {
    public static FoodDto foodRequestToDto(FoodDetailsRequestModel foodDetailsRequestModel) throws Exception
    {
        FoodDto foodDto = FoodDto.builder()
                .foodName(foodDetailsRequestModel.getFoodName())
                .foodPrice(foodDetailsRequestModel.getFoodPrice())
                .foodCategory(foodDetailsRequestModel.getFoodCategory())
                .foodId(UUID.randomUUID().toString()).build();
        return foodDto;
    }
    public static FoodDetailsResponse dtoToResponse(FoodDto foodDto) throws Exception
    {
        FoodDetailsResponse foodDetailsResponse = FoodDetailsResponse.builder()
                .foodName(foodDto.getFoodName())
                .foodPrice(foodDto.getFoodPrice())
                .foodCategory(foodDto.getFoodCategory())
                .foodId(foodDto.getFoodId())
                .build();
        return  foodDetailsResponse;
    }

}
