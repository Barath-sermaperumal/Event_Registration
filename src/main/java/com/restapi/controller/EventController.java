package com.restapi.controller;

import com.restapi.model.Event;
import com.restapi.response.common.APIResponse;
import com.restapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("EventRegistration/API/User/Event")
public class EventController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<APIResponse> getAllEvents(){
        List<Event> eventList= eventService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(eventList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getAEvent(@PathVariable Long id) {
        Event event = eventService.findById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(event);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
