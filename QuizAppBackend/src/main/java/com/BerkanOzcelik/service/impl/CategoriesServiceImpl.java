package com.BerkanOzcelik.service.impl;
import com.BerkanOzcelik.exception.BaseException;
import com.BerkanOzcelik.exception.ErrorMessage;
import com.BerkanOzcelik.exception.MessageType;
import com.BerkanOzcelik.model.Categories;
import com.BerkanOzcelik.model.Departments;
import com.BerkanOzcelik.repository.CategoriesRepository;
import com.BerkanOzcelik.repository.DepartmentsRepository;
import com.BerkanOzcelik.service.ICategoriesService;
import com.BerkanOzcelik.dto.DtoCategories;
import com.BerkanOzcelik.dto.DtoCategoriesIU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CategoriesServiceImpl implements ICategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final DepartmentsRepository departmentsRepository;

    @Autowired
    public CategoriesServiceImpl(CategoriesRepository categoriesRepository, DepartmentsRepository departmentsRepository) {
        this.categoriesRepository = categoriesRepository;
        this.departmentsRepository = departmentsRepository;
    }

    @Override
    @Transactional
    public DtoCategories createCategory(DtoCategoriesIU dto) {

        Optional<Departments> department = departmentsRepository.findById(dto.getDepartmentId());
        if (department.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Departman bulunamadı"));
        }


        Categories category = new Categories();
        category.setCategoryName(dto.getCategoryName());
        category.setDepartmentId(department.get());

        Categories savedCategory = categoriesRepository.save(category);
        return mapToDto(savedCategory);
    }

    @Override
    @Transactional
    public List<DtoCategories> getAllCategories() {
        List<Categories> categories = categoriesRepository.findAll();
        return categories.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DtoCategories getCategoryById(Long categoryId) {
        Optional<Categories> category = categoriesRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Kategori bulunamadı"));
        }
        return mapToDto(category.get());
    }

    @Override
    @Transactional
    public DtoCategories updateCategory(Long categoryId, DtoCategoriesIU dto) {
        Optional<Categories> category = categoriesRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Kategori bulunamadı"));
        }


        Optional<Departments> department = departmentsRepository.findById(dto.getDepartmentId());
        if (department.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Departman bulunamadı"));
        }


        Categories updatedCategory = category.get();
        updatedCategory.setCategoryName(dto.getCategoryName());
        updatedCategory.setDepartmentId(department.get());

        Categories savedCategory = categoriesRepository.save(updatedCategory);
        return mapToDto(savedCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {
        Optional<Categories> category = categoriesRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Kategori bulunamadı"));
        }

        categoriesRepository.delete(category.get());
    }


    private DtoCategories mapToDto(Categories category) {
        DtoCategories dto = new DtoCategories();
        dto.setCategoryName(category.getCategoryName());
        dto.setDepartmentId(category.getDepartmentId().getId());
        dto.setId(category.getId());
        dto.setCreateTime(category.getCreateTime());
        return dto;
    }
}