package com.BerkanOzcelik.service;

import com.BerkanOzcelik.dto.DtoCategories;
import com.BerkanOzcelik.dto.DtoCategoriesIU;

import java.util.List;

public interface ICategoriesService {


    DtoCategories createCategory(DtoCategoriesIU dto);


    List<DtoCategories> getAllCategories();


    DtoCategories getCategoryById(Long categoryId);


    DtoCategories updateCategory(Long categoryId, DtoCategoriesIU dto);


    void deleteCategory(Long categoryId);
}
