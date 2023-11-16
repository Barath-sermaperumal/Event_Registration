package com.restapi.controller.admin;

import com.restapi.model.Event;
import com.restapi.request.EventRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("EventRegistration/API/Admin/Event")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminEventController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private EventService eventService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllEvents(){
        List<Event> eventList= eventService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(eventList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getAEvent(@PathVariable Long id){
        Event event= eventService.findById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(event);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> addEvent(@RequestBody EventRequest eventRequest){
        List<Event> eventList= eventService.createEvent(eventRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(eventList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateEvent(@RequestBody EventRequest eventRequest){
        List<Event> eventList= eventService.updateEvent(eventRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(eventList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteEventById(@PathVariable Long id){
        List<Event> eventList= eventService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(eventList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
