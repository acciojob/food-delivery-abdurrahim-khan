package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.converter.OrderConverter;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
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
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderService orderService;
	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{

		OrderDto orderDto = orderService.getOrderById(id);
		OrderDetailsResponse orderDetailsResponse = OrderConverter.dtoToResponse(orderDto);
		return orderDetailsResponse;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) throws Exception {
		OrderDto orderDto1 = OrderConverter.orderRequestToDto(order);
		OrderDto orderDto = orderService.createOrder(orderDto1);
		OrderDetailsResponse orderDetailsResponse = OrderConverter.dtoToResponse(orderDto);
		return  orderDetailsResponse;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		
		OrderDto orderDto = OrderConverter.orderRequestToDto(order);
		OrderDto orderDto1 = orderService.updateOrderDetails(id,orderDto);
		OrderDetailsResponse orderDetailsResponse = OrderConverter.dtoToResponse(orderDto1);
		return orderDetailsResponse;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		try
		{
			orderService.deleteOrder(id);
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
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDto> orderDtoList = orderService.getOrders();
		List<OrderDetailsResponse> orderDetailsResponseList = new ArrayList<>();
		for(OrderDto orderDto : orderDtoList)
		{
			OrderDetailsResponse orderDetailsResponse = OrderConverter.dtoToResponse(orderDto);
			orderDetailsResponseList.add(orderDetailsResponse);
		}
		return orderDetailsResponseList;
	}
}
