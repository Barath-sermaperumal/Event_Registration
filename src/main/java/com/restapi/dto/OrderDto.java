package com.restapi.dto;

import com.restapi.model.AppUser;
import com.restapi.model.Event;
import com.restapi.model.Order;
import com.restapi.model.Seat;
import com.restapi.repository.EventRepository;
import com.restapi.repository.OrderRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.EventRequest;
import com.restapi.request.OrderRequest;
import com.restapi.request.SeatRequest;
import com.restapi.response.OrderResponse;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDto {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventDto eventDto;

    public Order mapToOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setSeat(orderRequest.getBookedSeats());
        order.setCount(orderRequest.getBookedSeats().size());
        return order;
    }

    public OrderResponse mapToOrderResponse(Order order){
        OrderResponse orderResponse=new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUserId(order.getUsers().getId());
        orderResponse.setUserName(order.getUsers().getName());
        orderResponse.setEventId(order.getEvent().getId());
        orderResponse.setEventName(order.getEvent().getName());
        orderResponse.setCount(order.getCount());
        orderResponse.setDate(order.getEvent().getDate());
        orderResponse.setBookedSeats(order.getSeat());
        orderResponse.setTotalPrice(order.getEvent().getPrice());
        orderResponse.setBookedSeatsString(eventDto.mapToEventSeatResponse(order.getSeat()));
        return orderResponse;
    }

    public List<OrderResponse> mapListToOrderResponse(List<Order> orderList) {
        List<OrderResponse> rs=new ArrayList<>();
        for (int i=0;i<orderList.size();i++) {
            OrderResponse orderResponse=new OrderResponse();
            orderResponse.setId(orderList.get(i).getId());
            orderResponse.setEventName(orderList.get(i).getEvent().getName());
            orderResponse.setUserId(orderList.get(i).getUsers().getId());
            orderResponse.setEventId(orderList.get(i).getEvent().getId());
            orderResponse.setUserName(orderList.get(i).getUsers().getName());
            orderResponse.setCount(orderList.get(i).getCount());
            orderResponse.setTotalPrice(orderList.get(i).getEvent().getPrice() * orderList.get(i).getCount());
            orderResponse.setDate(orderList.get(i).getEvent().getDate());
            orderResponse.setBookedSeatsString(eventDto.mapToEventSeatResponse(orderList.get(i).getSeat()));
            rs.add(orderResponse);
        }
        return rs;
    }

    public Seat mapSeatRequestToSeat(SeatRequest seatRequest) {
        Seat seat = new Seat();
        seat.setOrder(orderRepository.findById(seatRequest.getOrderid()).get());
        seat.setSeatBooked(seatRequest.isIsbooked());
        seat.setSeatNumber(seatRequest.getSeatnumber());
        seat.setUser(userRepository.findById(seatRequest.getUserid()).get());
        seat.setEvent(eventRepository.findById(seatRequest.getEventid()).get());
        return seat;
    }
}
