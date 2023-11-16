package com.restapi.controller.admin;

import com.restapi.model.Category;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("EventRegistration/API/Admin/Category")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminCategoryController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllCategories(){
        List<Category> categoryList= categoryService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> addCategory(@RequestBody Category category){
        List<Category> categoryList= categoryService.addCategory(category);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateCategory(@RequestBody Category category){
        List<Category> categoryList= categoryService.addCategory(category);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteCategoryById(@PathVariable Long id){
        List<Category> categoryList= categoryService.deleteCategory(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(categoryList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
