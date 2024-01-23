package com.restapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.dto.EventDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Event;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.EventRepository;
import com.restapi.request.EventRequest;
import com.restapi.request.FilteredEvents;
import lombok.SneakyThrows;
import net.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventDto eventDto;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Cacheable(value = "Event", key = "'allEvent'")
    public  List<Event> findAll() {
//        String key="Eventor_Events";
//        HashOperations<String, Long, List<Event>> hashOperations = redisTemplate.opsForHash();
//
//        if (redisTemplate.hasKey(key)) {
//            Map<Long, List<Event>> cachedEvents = hashOperations.entries(key);
////            System.out.println("Retrieved from cache: " + cachedEvents.toString());
//            return cachedEvents.values().stream().flatMap(List::stream).toList();
//        }
//
//        // Store & Retrieve a HashMap
//            Map<Long, List<Event> > Events = new HashMap<>();
//            for(int i=0;i<eventRepository.findAll().size();i++){
//                Events.computeIfAbsent(eventRepository.findAll().get(i).getId(), k -> new ArrayList<>()).add(eventRepository.findAll().get(i));
//            }
////            System.out.println("BarathEventsMap"+Events.toString());
//
//            hashOperations.putAll(key, Events);

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

    public List<Event> findTopEvents() {
        return eventRepository.findAllTopEvents();
    }

    public List<Event> getFilteredEvents(FilteredEvents events){
        if(!events.getCheckedCategory().isEmpty() && !events.getCheckedVenue().isEmpty()){
            return eventRepository.findFilteredEvents(events.getFromDate(),events.getToDate(),events.getMinPrice(),events.getMaxPrice(),events.getCheckedCategory(),events.getCheckedVenue());
        } else if (events.getCheckedCategory().isEmpty() && events.getCheckedVenue().isEmpty()) {
            return eventRepository.findByDateBetweenAndPriceBetween(events.getFromDate(),events.getToDate(),events.getMinPrice(),events.getMaxPrice());
        } else if (events.getCheckedVenue().isEmpty()) {
            return eventRepository.findFilteredEventsWithoutVenues(events.getFromDate(),events.getToDate(),events.getMinPrice(),events.getMaxPrice(),events.getCheckedCategory());
        }
        else {
            return eventRepository.findByDateBetweenAndPriceBetweenAndIdIn(events.getFromDate(),events.getToDate(),events.getMinPrice(),events.getMaxPrice(),events.getCheckedVenue());
        }
    }
}
