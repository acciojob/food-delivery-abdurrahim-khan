package com.driver.service.impl;

import com.driver.converter.OrderConverter;
import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.io.repository.UserRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;
@Service
@ControllerAdvice
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public OrderDto createOrder(OrderDto order){

        OrderEntity orderEntity = OrderEntity.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .cost(order.getCost())
                .items(order.getItems())
                .status(order.isStatus())
                .build();
        orderRepository.save(orderEntity);

        OrderEntity orderEntity1 = orderRepository.findByOrderId(order.getOrderId());
        OrderDto orderDto = OrderConverter.entityToDto(orderEntity1);

        return orderDto;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        try
        {
            if(!orderRepository.existsByOrderId(orderId))
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            throw new Exception("invalid order id");
        }
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        OrderDto orderDto = OrderConverter.entityToDto(orderEntity);
        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        try
        {
            if(!orderRepository.existsByOrderId(orderId))
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            throw new Exception("invalid order id");
        }
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        orderEntity.setCost(order.getCost());
        orderEntity.setItems(order.getItems());
        orderEntity.setStatus(order.isStatus());
        orderEntity.setUserId(order.getUserId());
        orderRepository.save(orderEntity);
        return OrderConverter.entityToDto(orderEntity);
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        try
        {
            if(!orderRepository.existsByOrderId(orderId))
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            throw new Exception("invalid order id");
        }
        long id = orderRepository.findByOrderId(orderId).getId();
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDto> getOrders(){
        List<OrderEntity> orderEntityList = (List<OrderEntity>) orderRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for(OrderEntity orderEntity : orderEntityList)
        {
            OrderDto orderDto = OrderConverter.entityToDto(orderEntity);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}