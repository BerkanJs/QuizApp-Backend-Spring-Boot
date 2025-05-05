package com.BerkanOzcelik.controller.impl;

import com.BerkanOzcelik.controller.IExamQuestionsController;
import com.BerkanOzcelik.controller.RestBaseController;
import com.BerkanOzcelik.controller.RootEntity;
import com.BerkanOzcelik.dto.DtoExamQuestions;
import com.BerkanOzcelik.model.ExamQuestions;
import com.BerkanOzcelik.service.impl.ExamQuestionsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/exam-questions")
    @RequiredArgsConstructor
    public class ExamQuestionsControllerImpl extends RestBaseController implements IExamQuestionsController {

        private final ExamQuestionsServiceImpl examQuestionsService;

        @PostMapping("/add")
        @Override
        public ResponseEntity<RootEntity<ExamQuestions>> addQuestionToExam(@RequestBody DtoExamQuestions dto) {
            ExamQuestions created = examQuestionsService.addQuestionToExam(dto);
            return ResponseEntity.ok(ok(created));
        }

        @DeleteMapping("/remove/{id}")
        @Override
        public ResponseEntity<RootEntity<String>> removeQuestionFromExam(@PathVariable("id") Long examQuestionId) {
            examQuestionsService.removeQuestionFromExam(examQuestionId);
            return ResponseEntity.ok(ok("Soru başarıyla sınavdan silindi"));
        }

        @GetMapping("/by-exam/{examId}")
        @Override
        public ResponseEntity<RootEntity<List<DtoExamQuestions>>> getQuestionsByExamId(@PathVariable Long examId) {
            List<DtoExamQuestions> questions = examQuestionsService.getQuestionsByExamId(examId);
            return ResponseEntity.ok(ok(questions));
        }

        @PostMapping("/add-multiple")
        public ResponseEntity<RootEntity<List<ExamQuestions>>> addMultipleQuestionsToExam(@RequestBody List<DtoExamQuestions> dtoList) {
            List<ExamQuestions> created = examQuestionsService.addMultipleQuestionsToExam(dtoList);
            return ResponseEntity.ok(ok(created));
        }
    }
