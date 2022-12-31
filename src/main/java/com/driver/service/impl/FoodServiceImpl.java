package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FoodServiceImpl implements FoodService
{
    @Autowired
    FoodRepository foodRepository;
    @Override
    public FoodDto createFood(FoodDto food) throws Exception {

        FoodEntity foodEntity = FoodEntity.builder()
                .foodCategory(food.getFoodCategory())
                .foodPrice(food.getFoodPrice())
                .foodName(food.getFoodName())
                .foodId(food.getFoodId())
                .build();
        foodRepository.save(foodEntity);
        return getFoodById(food.getFoodId());

    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity foodEntity= null;
//        try {
            foodEntity = foodRepository.findByFoodId(foodId);
//            if(foodEntity == null)
//            {
//                throw new Exception();
//            }
//        }
//        catch (Exception e)
//        {
//            throw new Exception("invalid food ID");
//        }

        FoodDto foodDto = FoodDto.builder()
                .foodCategory(foodEntity.getFoodCategory())
                .foodId(foodEntity.getFoodId())
                .foodName(foodEntity.getFoodName())
                .foodPrice(foodEntity.getFoodPrice())
                .id(foodEntity.getId())
                .build();
        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodEntity foodEntity = null;
//        try
//        {
            foodEntity = foodRepository.findByFoodId(foodId);
//            if(foodEntity == null)
//            {
//                throw new Exception();
//            }
//        }
//        catch (Exception e)
//        {
//            throw new Exception("food not found");
//        }
        foodEntity.setFoodCategory(foodDetails.getFoodCategory());
        foodEntity.setFoodName(foodDetails.getFoodName());
        foodEntity.setFoodPrice(foodDetails.getFoodPrice());

        foodRepository.save(foodEntity);
        FoodDto foodDto = FoodDto.builder()
                .id(foodEntity.getId())
                .foodPrice(foodEntity.getFoodPrice())
                .foodName(foodEntity.getFoodName())
                .foodId(foodEntity.getFoodId())
                .foodCategory(foodEntity.getFoodCategory())
                .build();
        return foodDto;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(id);
//        try
//        {
//            if(foodEntity == null)
//            {
//                throw  new Exception();
//            }
            foodRepository.deleteById(foodEntity.getId());
//        }
//        catch (Exception e)
//        {
//            throw  new Exception("food not found");
//        }
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodDto> foodDtoList = new ArrayList<>();
        List<FoodEntity> foodEntityList = (List<FoodEntity>)foodRepository.findAll();
        for(FoodEntity foodEntity : foodEntityList)
        {
            FoodDto foodDto = FoodDto.builder()
                    .id(foodEntity.getId())
                    .foodPrice(foodEntity.getFoodPrice())
                    .foodName(foodEntity.getFoodName())
                    .foodId(foodEntity.getFoodId())
                    .foodCategory(foodEntity.getFoodCategory())
                    .build();
            foodDtoList.add(foodDto);
        }
        return foodDtoList;
    }
}