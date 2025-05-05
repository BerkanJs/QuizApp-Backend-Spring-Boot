package com.BerkanOzcelik.controller;

import java.util.List;

import com.BerkanOzcelik.dto.DtoDepartmentsIU;
import org.springframework.http.ResponseEntity;

import com.BerkanOzcelik.dto.DtoDepartments;
import com.BerkanOzcelik.model.Departments;

public interface IDepartmentsController {

    ResponseEntity<RootEntity<DtoDepartments>> addDepartment(DtoDepartmentsIU dto);

    ResponseEntity<RootEntity<String>> deleteDepartment(Long departmentId);

    ResponseEntity<RootEntity<List<DtoDepartments>>> getAllDepartments();

    ResponseEntity<RootEntity<DtoDepartments>> getDepartmentById(Long departmentId);

    ResponseEntity<RootEntity<DtoDepartments>> updateDepartment(Long departmentId, DtoDepartmentsIU dto);
}
