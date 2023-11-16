package com.restapi.service;

import com.restapi.model.Category;
import com.restapi.model.Event;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EventRepository eventRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public  List<Category> addCategory(Category category) {
        categoryRepository.save(category);
        return findAll();
    }

    public List<Category> updateCategory(Category category){
        categoryRepository.save(category);
        return findAll();
    }

    public List<Category> deleteCategory(Long id){
        categoryRepository.deleteById(id);
        return findAll();
    }

    public List<Event> findACategory(Long id) {
        return eventRepository.findCategory(id);

    }
}
