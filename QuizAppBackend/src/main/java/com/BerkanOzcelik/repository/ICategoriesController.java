package com.BerkanOzcelik.repository;

import com.BerkanOzcelik.controller.RootEntity;
import com.BerkanOzcelik.dto.DtoCategories;

import com.BerkanOzcelik.dto.DtoCategoriesIU;

import org.springframework.http.ResponseEntity;
import java.util.List;
public interface ICategoriesController {


    ResponseEntity<RootEntity<DtoCategories>> addCategory(DtoCategoriesIU dto);


    ResponseEntity<RootEntity<List<DtoCategories>>> getAllCategories();


    ResponseEntity<RootEntity<DtoCategories>> getCategoryById(Long categoryId);


    ResponseEntity<RootEntity<DtoCategories>> updateCategory(Long categoryId, DtoCategoriesIU dto);


    ResponseEntity<RootEntity<String>> deleteCategory(Long categoryId);
}