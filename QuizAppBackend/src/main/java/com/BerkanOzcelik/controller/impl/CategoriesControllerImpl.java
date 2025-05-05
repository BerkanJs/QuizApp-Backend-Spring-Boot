package com.BerkanOzcelik.controller.impl;

import com.BerkanOzcelik.controller.RestBaseController;
import com.BerkanOzcelik.controller.RootEntity;
import com.BerkanOzcelik.dto.DtoCategories;
import com.BerkanOzcelik.dto.DtoCategoriesIU;
import com.BerkanOzcelik.repository.ICategoriesController;
import com.BerkanOzcelik.service.ICategoriesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesControllerImpl extends RestBaseController implements ICategoriesController {

    private final ICategoriesService categoriesService;

    @Autowired
    public CategoriesControllerImpl(ICategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }


    @Override
    @PostMapping("/add")
    public ResponseEntity<RootEntity<DtoCategories>> addCategory(@RequestBody DtoCategoriesIU dto) {
        DtoCategories createdCategory = categoriesService.createCategory(dto);
        return ResponseEntity.ok(ok(createdCategory));
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<RootEntity<List<DtoCategories>>> getAllCategories() {
        List<DtoCategories> categories = categoriesService.getAllCategories();
        return ResponseEntity.ok(ok(categories));
    }


    @Override
    @GetMapping("/{categoryId}")
    public ResponseEntity<RootEntity<DtoCategories>> getCategoryById(@PathVariable Long categoryId) {
        DtoCategories category = categoriesService.getCategoryById(categoryId);
        return ResponseEntity.ok(ok(category));
    }


    @Override
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<RootEntity<DtoCategories>> updateCategory(@PathVariable Long categoryId, @RequestBody DtoCategoriesIU dto) {
        DtoCategories updatedCategory = categoriesService.updateCategory(categoryId, dto);
        return ResponseEntity.ok(ok(updatedCategory));
    }


    @Override
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<RootEntity<String>> deleteCategory(@PathVariable Long categoryId) {
        categoriesService.deleteCategory(categoryId);
        return ResponseEntity.ok(ok("Kategori başarıyla silindi"));
    }
}





