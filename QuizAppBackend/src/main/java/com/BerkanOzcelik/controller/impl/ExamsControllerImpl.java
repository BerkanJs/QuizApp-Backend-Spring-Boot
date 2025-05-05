package com.BerkanOzcelik.controller.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BerkanOzcelik.controller.IExamsController;
import com.BerkanOzcelik.controller.RestBaseController;
import com.BerkanOzcelik.controller.RootEntity;
import com.BerkanOzcelik.dto.DtoExams;
import com.BerkanOzcelik.enums.ExamStatus;
import com.BerkanOzcelik.service.impl.ExamsServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamsControllerImpl extends RestBaseController implements IExamsController {
    private final ExamsServiceImpl examsService;
    @Override
    @PostMapping("/add")
    public ResponseEntity<RootEntity<DtoExams>> addExam(@RequestBody DtoExams dtoExams) {
        DtoExams createdExam = examsService.addExam(dtoExams);
        return ResponseEntity.ok(ok(createdExam));
    }

    @Override
    @PutMapping("/update/{examId}")
    public ResponseEntity<RootEntity<DtoExams>> updateExam(@PathVariable Long examId, @RequestBody DtoExams dtoExams) {
        DtoExams updatedExam = examsService.updateExam(examId, dtoExams);
        return ResponseEntity.ok(ok(updatedExam));
    }

    @Override
    @DeleteMapping("/delete/{examId}")
    public ResponseEntity<RootEntity<String>> deleteExam(@PathVariable Long examId) {
        examsService.deleteExam(examId);
        return ResponseEntity.ok(ok("Exam deleted successfully"));
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<RootEntity<List<DtoExams>>> listExams() {
        List<DtoExams> examsList = examsService.listExams();
        return ResponseEntity.ok(ok(examsList));
    }

    @Override
    @PutMapping("/updateStatus/{examId}")
    public ResponseEntity<RootEntity<DtoExams>> updateExamStatus(@PathVariable Long examId, @RequestBody ExamStatus examStatus) {
        DtoExams updatedExamStatus = examsService.updateExamStatus(examId, examStatus);
        return ResponseEntity.ok(ok(updatedExamStatus));
    }
    
}
