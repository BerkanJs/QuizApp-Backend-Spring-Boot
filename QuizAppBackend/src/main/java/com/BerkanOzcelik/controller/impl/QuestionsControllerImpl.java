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

import com.BerkanOzcelik.controller.IQuestionsController;
import com.BerkanOzcelik.controller.RestBaseController;
import com.BerkanOzcelik.controller.RootEntity;
import com.BerkanOzcelik.dto.DtoQuestions;
import com.BerkanOzcelik.dto.DtoQuestionsIU;
import com.BerkanOzcelik.model.Questions;
import com.BerkanOzcelik.service.impl.QuestionsServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionsControllerImpl extends RestBaseController implements IQuestionsController {
    private final QuestionsServiceImpl questionsService;

    @Override
    @PostMapping("/create")
    public ResponseEntity<RootEntity<Questions>> createQuestion(@RequestBody DtoQuestionsIU dto) {
        Questions createdQuestion = questionsService.createQuestion(dto);
        return ResponseEntity.ok(ok(createdQuestion));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<RootEntity<Questions>> updateQuestion(@RequestBody DtoQuestionsIU dto) {
        Questions updatedQuestion = questionsService.updateQuestion(dto);
        return ResponseEntity.ok(ok(updatedQuestion));
    }

    @Override
    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<RootEntity<String>> deleteQuestion(@PathVariable Long questionId) {
        questionsService.deleteQuestion(questionId);
        return ResponseEntity.ok(ok("Question deleted successfully"));
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<RootEntity<List<DtoQuestions>>> getAllQuestions() {
        List<DtoQuestions> allQuestions = questionsService.getAllQuestions();
        return ResponseEntity.ok(ok(allQuestions));
    }

}
