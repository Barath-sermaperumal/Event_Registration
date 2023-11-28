package com.restapi.controller.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.restapi.model.Event;
import com.restapi.request.EventRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.EventService;
import com.restapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("EventRegistration/API/Admin/Event")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminEventController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private EventService eventService;

    @Autowired
    private StorageService storageService;

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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)


    public ResponseEntity<APIResponse> addEvent(
            @RequestParam MultipartFile image,
            @RequestParam long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String venue,
            @RequestParam String date,
            @RequestParam String host,
            @RequestParam int price,
            @RequestParam int availableTickets,
            @RequestParam Long categoryId
            ) throws ParseException {
        EventRequest eventRequest = new EventRequest();
        String file=storageService.storeFile(image);
        eventRequest.setImage(file);
        eventRequest.setId(id);
        eventRequest.setName(name);
        eventRequest.setHost(host);
        //formatting string to date
        LocalDate datetime = null;
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            datetime = LocalDate.parse(date, pattern);
            System.out.println(datetime);
        } catch (DateTimeParseException e) {
            System.out.println(e);
        }
        //formatting string to date
        eventRequest.setDate(datetime);
        eventRequest.setDescription(description);
        eventRequest.setVenue(venue);
        eventRequest.setPrice(price);
        eventRequest.setAvailableTickets(availableTickets);
        eventRequest.setCategoryId(categoryId);
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
