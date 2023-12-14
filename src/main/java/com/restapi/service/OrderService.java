package com.restapi.service;

import com.restapi.dto.OrderDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Event;
import com.restapi.model.Order;
import com.restapi.model.Seat;
import com.restapi.repository.EventRepository;
import com.restapi.repository.OrderRepository;
import com.restapi.repository.SeatRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.OrderRequest;
import com.restapi.request.SeatRequest;
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

    @Autowired
    private SeatRepository seatRepository;

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
        int excount=eventRepository.findById(order.getEvent().getId()).get().getSoldTickets();
        Event event1=eventRepository.findById(order.getEvent().getId()).orElseThrow();
        event1.setId(event1.getId());
        event1.setName(event1.getName());
        event1.setOrder(event1.getOrder());
        event1.setVenue(event1.getVenue());
        event1.setCategory(event1.getCategory());
        event1.setDate(event1.getDate());
        event1.setAvailableTickets(event1.getAvailableTickets());
        event1.setHost(event1.getHost());
        event1.setDescription(event1.getDescription());
        event1.setPrice(event1.getPrice());
        event1.setSoldTickets(order.getCount()+excount);
        eventRepository.save(event1);
        for(int i=0;i<orderRequest.getBookedSeats().size();i++){
            SeatRequest seatRequest=new SeatRequest();
            seatRequest.setOrderid(order.getId());
            seatRequest.setEventid(event.getId());
            seatRequest.setUserid(appUser.getId());
            seatRequest.setSeatnumber(orderRequest.getBookedSeats().get(i).getSeatNumber());
            seatRequest.setIsbooked(orderRequest.getBookedSeats().get(i).isSeatBooked());
            Seat bookedseat=orderDto.mapSeatRequestToSeat(seatRequest);
            seatRepository.save(bookedseat);
        }
        return order;
    }

    public Object deleteOrder(long id) {
        seatRepository.deleteSeats(id);
        int count=orderRepository.findById(id).get().getEvent().getSoldTickets()-orderRepository.findById(id).get().getCount();
//        eventRepository.updateSoldTickets(orderRepository.findById(id).get().getEvent().getId(),count);
        System.out.println(count);
        orderRepository.deleteById(id);
        return findAll();
    }
}
