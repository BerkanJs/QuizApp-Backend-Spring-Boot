package com.BerkanOzcelik.service;

import com.BerkanOzcelik.dto.DtoDepartments;
import com.BerkanOzcelik.dto.DtoDepartmentsIU;

import java.util.List;

public interface IDepartmentsService {

    DtoDepartments createDepartment(DtoDepartmentsIU dto);


    DtoDepartments updateDepartment(Long departmentId, DtoDepartmentsIU dto);


    void deleteDepartment(Long departmentId);


    List<DtoDepartments> getAllDepartments();


    DtoDepartments getDepartmentById(Long departmentId);
}
