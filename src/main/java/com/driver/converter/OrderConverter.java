package com.driver.converter;

import com.driver.io.entity.OrderEntity;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.shared.dto.OrderDto;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class OrderConverter {
    public static OrderDto orderRequestToDto(OrderDetailsRequestModel orderDetailsRequestModel) throws Exception
    {
        OrderDto orderDto = OrderDto.builder()
                .orderId(UUID.randomUUID().toString())
                .cost(orderDetailsRequestModel.getCost())
                .items(orderDetailsRequestModel.getItems())
                .userId(orderDetailsRequestModel.getUserId())
                .status(true)
                .build();
        return orderDto;
    }
    public static OrderDetailsResponse dtoToResponse(OrderDto orderDto)
    {
        OrderDetailsResponse orderDetailsResponse = OrderDetailsResponse.builder()
                .orderId(orderDto.getOrderId())
                .cost(orderDto.getCost())
                .items(orderDto.getItems())
                .status(orderDto.isStatus())
                .userId(orderDto.getUserId())
                .build();
        return orderDetailsResponse;
    }
    public static OrderDto entityToDto(OrderEntity orderEntity)
    {
        OrderDto orderDto = OrderDto.builder()
                .status(orderEntity.isStatus())
                .id(orderEntity.getId())
                .userId(orderEntity.getUserId())
                .orderId(orderEntity.getOrderId())
                .items(orderEntity.getItems())
                .cost(orderEntity.getCost())
                .build();
        return orderDto;
    }
}
