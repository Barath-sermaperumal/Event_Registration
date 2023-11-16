package com.restapi.service;

import com.restapi.dto.OrderDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Event;
import com.restapi.model.Order;
import com.restapi.repository.EventRepository;
import com.restapi.repository.OrderRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDto orderDto;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public List<Order> findAll(Long id) {
        System.out.println(orderRepository.findAllForId(id));
        return orderRepository.findAllForId(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order bookTickets(OrderRequest orderRequest) {
        Order order=orderDto.mapToOrder(orderRequest);
        AppUser appUser=userRepository.findById(orderRequest.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("userId","userId", orderRequest.getUserId()));
        Event event=eventRepository.findById(orderRequest.getEventId())
                .orElseThrow(()->new ResourceNotFoundException("eventId","eventId", orderRequest.getEventId()));
        order.setUsers(appUser);
        order.setEvent(event);
        orderRepository.save(order);
        return order;
    }
}
