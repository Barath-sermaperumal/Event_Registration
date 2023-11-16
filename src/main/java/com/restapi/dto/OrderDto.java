package com.restapi.dto;

import com.restapi.model.Event;
import com.restapi.model.Order;
import com.restapi.request.EventRequest;
import com.restapi.request.OrderRequest;
import com.restapi.response.OrderResponse;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDto {

    public Order mapToOrder(OrderRequest orderRequest){
        Order order=new Order();
        if(orderRequest.getId()!=null){
            order.setId(orderRequest.getId());
        }
//        order.setBookedUserList(orderRequest.getBookedUserList());
        order.setCount(orderRequest.getCount());
        return order;
    }

    public OrderResponse mapToOrderResponse(Order order){
        OrderResponse orderResponse=new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUserId(order.getUsers().getId());
        orderResponse.setEventId(order.getEvent().getId());
        orderResponse.setCount(order.getCount());
//        orderResponse.setBookedUserList(order.getBookedUserList());
        return orderResponse;
    }

    public List<OrderResponse> mapListToOrderResponse(List<Order> orderList) {
        OrderResponse orderResponse=new OrderResponse();
        List<OrderResponse> rs=new ArrayList<>();
        for(int i=0;i<orderList.size();i++){
            orderResponse.setId(orderList.get(i).getId());
            orderResponse.setUserId(orderList.get(i).getUsers().getId());
            orderResponse.setEventId(orderList.get(i).getEvent().getId());
            orderResponse.setCount(orderList.get(i).getCount());
            rs.add(orderResponse);
        }
        return rs;
    }
}
