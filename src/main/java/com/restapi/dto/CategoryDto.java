package com.restapi.dto;

import com.restapi.model.Category;
import com.restapi.response.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDto {
    public List<CategoryResponse> mapToCategoryResponse(List<Category> categoryList) {
        List<CategoryResponse> rs=new ArrayList<>();
        for(int i=0;i<categoryList.size();i++){
            CategoryResponse categoryResponse=new CategoryResponse();
            categoryResponse.setId(categoryList.get(i).getId());
            categoryResponse.setEvent(categoryList.get(i).getEvents());
            categoryResponse.setName(categoryList.get(i).getName());
            categoryResponse.setEventsCount(categoryList.get(i).getEvents().size());
            rs.add(categoryResponse);
        }
        return rs;
    }
}
