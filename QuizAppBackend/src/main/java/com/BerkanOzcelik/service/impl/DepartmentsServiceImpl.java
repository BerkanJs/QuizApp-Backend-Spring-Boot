package com.BerkanOzcelik.service.impl;

import com.BerkanOzcelik.dto.DtoDepartments;
import com.BerkanOzcelik.dto.DtoDepartmentsIU;
import com.BerkanOzcelik.exception.BaseException;
import com.BerkanOzcelik.exception.ErrorMessage;
import com.BerkanOzcelik.exception.MessageType;
import com.BerkanOzcelik.model.Departments;
import com.BerkanOzcelik.repository.DepartmentsRepository;
import com.BerkanOzcelik.service.IDepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentsServiceImpl implements IDepartmentsService {

    private final DepartmentsRepository departmentsRepository;

    @Autowired
    public DepartmentsServiceImpl(DepartmentsRepository departmentsRepository) {
        this.departmentsRepository = departmentsRepository;
    }

    @Override
    @Transactional
    public DtoDepartments createDepartment(DtoDepartmentsIU dto) {
        Departments department = new Departments();
        department.setDepartmentName(dto.getDepartmentName());
        department.setDepartmentDescription(dto.getDepartmentDescription());

        Departments savedDepartment = departmentsRepository.save(department);
        return mapToDto(savedDepartment);
    }

    @Override
    @Transactional
    public DtoDepartments updateDepartment(Long departmentId, DtoDepartmentsIU dto) {
        Optional<Departments> optDepartment = departmentsRepository.findById(departmentId);
        if (optDepartment.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Departman bulunamadı"));
        }

        Departments department = optDepartment.get();
        department.setDepartmentName(dto.getDepartmentName());
        department.setDepartmentDescription(dto.getDepartmentDescription());

        Departments updatedDepartment = departmentsRepository.save(department);
        return mapToDto(updatedDepartment);
    }

    @Override
    @Transactional
    public void deleteDepartment(Long departmentId) {
        Optional<Departments> optDepartment = departmentsRepository.findById(departmentId);
        if (optDepartment.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Departman bulunamadı"));
        }

        departmentsRepository.delete(optDepartment.get());
    }

    @Override
    public List<DtoDepartments> getAllDepartments() {
        List<Departments> departments = departmentsRepository.findAll();
        return departments.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DtoDepartments getDepartmentById(Long departmentId) {
        Optional<Departments> optDepartment = departmentsRepository.findById(departmentId);
        if (optDepartment.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Departman bulunamadı"));
        }

        return mapToDto(optDepartment.get());
    }

    // DTO'ya dönüştürme işlemi
    private DtoDepartments mapToDto(Departments department) {
        DtoDepartments dto = new DtoDepartments();
        dto.setId(department.getId());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setDepartmentDescription(department.getDepartmentDescription());
        return dto;
    }
}
