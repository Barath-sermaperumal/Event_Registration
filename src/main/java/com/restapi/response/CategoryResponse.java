package com.restapi.response;

import com.restapi.model.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryResponse {
    private Long id;
    private String name;
    private List<Event> event;
    private int eventsCount;
}
