package com.restapi.controller;

import com.restapi.dto.OrderDto;
import com.restapi.model.Event;
import com.restapi.model.Order;
import com.restapi.request.OrderRequest;
import com.restapi.response.OrderResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("EventRegistration/API/User")
@PreAuthorize("hasRole('ROLE_USER')")
public class OrderController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDto orderDto;

    @GetMapping("/Order/{id}")
    public ResponseEntity<APIResponse> viewAllOrders(@PathVariable Long id){
        List<Order> orderList= orderService.findAll(id);
        List<OrderResponse> orderResponse= orderDto.mapListToOrderResponse(orderList);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/Order/Ticketbooking")
    public ResponseEntity<APIResponse> bookTickets(@RequestBody OrderRequest orderRequest){
        Order order=orderService.bookTickets(orderRequest);
        OrderResponse orderResponse= orderDto.mapToOrderResponse(order);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("order/delete/{id}")
    public ResponseEntity<APIResponse> deleteOrder(@PathVariable long id){
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderService.deleteOrder(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
