package com.restapi.service;

import com.restapi.dto.EventDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Event;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.EventRepository;
import com.restapi.request.EventRequest;
import net.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventDto eventDto;

    @Autowired
    private CategoryRepository categoryRepository;

    public  List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id","Id",id));
    }

    public List<Event> createEvent(EventRequest eventRequest) {
        Event event=eventDto.mapToEvent(eventRequest);
        Category category=categoryRepository.findById(eventRequest.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("category","category", eventRequest.getCategoryId()));
        event.setCategory(category);

        eventRepository.save(event);
        return findAll();
    }

    public List<Event> updateEvent(EventRequest eventRequest) {
        Event event=eventDto.mapToEvent(eventRequest);
        Category category=categoryRepository.findById(eventRequest.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("category","category", eventRequest.getCategoryId()));
        event.setCategory(category);
        eventRepository.save(event);
        return findAll();
    }

    public List<Event> deleteById(Long id) {
        eventRepository.deleteById(id);
        return findAll();
    }

    public List<Event> getReport() {
        return eventRepository.findAll();
    }
}
