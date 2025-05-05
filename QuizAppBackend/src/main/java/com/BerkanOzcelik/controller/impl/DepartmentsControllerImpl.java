package com.BerkanOzcelik.controller.impl;


import com.BerkanOzcelik.controller.IDepartmentsController;
import com.BerkanOzcelik.controller.RestBaseController;
import com.BerkanOzcelik.controller.RootEntity;
import com.BerkanOzcelik.dto.DtoDepartments;
import com.BerkanOzcelik.dto.DtoDepartmentsIU;
import com.BerkanOzcelik.service.IDepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentsControllerImpl extends RestBaseController implements IDepartmentsController {

    @Autowired
    private IDepartmentsService departmentsService;

    // Departman ekleme
    @Override
    @PostMapping("/add")
    public ResponseEntity<RootEntity<DtoDepartments>> addDepartment(@RequestBody DtoDepartmentsIU dto) {
        DtoDepartments createdDepartment = departmentsService.createDepartment(dto);
        return ResponseEntity.ok(ok(createdDepartment));
    }

    // Departman silme
    @Override
    @DeleteMapping("/delete/{departmentId}")
    public ResponseEntity<RootEntity<String>> deleteDepartment(@PathVariable Long departmentId) {
        departmentsService.deleteDepartment(departmentId);
        return ResponseEntity.ok(ok("Departman başarıyla silindi"));
    }

    // Tüm departmanları listeleme
    @Override
    @GetMapping("/all")
    public ResponseEntity<RootEntity<List<DtoDepartments>>> getAllDepartments() {
        List<DtoDepartments> departments = departmentsService.getAllDepartments();
        return ResponseEntity.ok(ok(departments));
    }

    // ID ile departman getirme
    @Override
    @GetMapping("/{departmentId}")
    public ResponseEntity<RootEntity<DtoDepartments>> getDepartmentById(@PathVariable Long departmentId) {
        DtoDepartments department = departmentsService.getDepartmentById(departmentId);
        return ResponseEntity.ok(ok(department));
    }

    // Departman güncelleme
    @Override
    @PutMapping("/update/{departmentId}")
    public ResponseEntity<RootEntity<DtoDepartments>> updateDepartment(@PathVariable Long departmentId, @RequestBody DtoDepartmentsIU dto) {
        DtoDepartments updatedDepartment = departmentsService.updateDepartment(departmentId, dto);
        return ResponseEntity.ok(ok(updatedDepartment));
    }
}
