package com.restapi.dto;

import com.restapi.model.Event;
import com.restapi.request.EventRequest;
import com.restapi.response.ReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventDto {

    public Event mapToEvent(EventRequest eventRequest){
        Event event=new Event();
        if(eventRequest.getId()!=null){
            event.setId(eventRequest.getId());
        }
        event.setHost(eventRequest.getHost());
        event.setDate(eventRequest.getDate());
        event.setName(eventRequest.getName());
        event.setDescription(eventRequest.getDescription());
        event.setPrice(eventRequest.getPrice());
        event.setAvailableTickets(eventRequest.getAvailableTickets());
        event.setVenue(eventRequest.getVenue());

        return event;
    }

    public List<ReportResponse> mapToReport(List<Event> eventList) {
        ReportResponse reportResponse=new ReportResponse();
        List<ReportResponse> rs=new ArrayList<>();
        for(int i=0;i< eventList.size();i++){
            reportResponse.setEventName(eventList.get(i).getName());
            reportResponse.setLocalDate(eventList.get(i).getDate());
            reportResponse.setTickets_sold(eventList.get(i).getAvailableTickets());
            rs.add(reportResponse);
        }
        return rs;
    }
}
