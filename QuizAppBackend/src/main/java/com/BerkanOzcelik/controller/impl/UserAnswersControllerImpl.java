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

import com.BerkanOzcelik.controller.IUserAnswersController;
import com.BerkanOzcelik.controller.RestBaseController;
import com.BerkanOzcelik.controller.RootEntity;
import com.BerkanOzcelik.dto.DtoUserAnswers;
import com.BerkanOzcelik.dto.DtoUserAnswersIU;
import com.BerkanOzcelik.model.UserAnswers;
import com.BerkanOzcelik.service.IUserAnswersService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/user-answers")
@RequiredArgsConstructor
public class UserAnswersControllerImpl extends RestBaseController implements IUserAnswersController {

    private final IUserAnswersService userAnswersService;

    @Override
    @PostMapping
    public ResponseEntity<RootEntity<UserAnswers>> createAnswer(@RequestBody DtoUserAnswers dto) {
        UserAnswers createdAnswer = userAnswersService.createAnswer(dto);
        return ResponseEntity.ok(ok(createdAnswer));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<RootEntity<UserAnswers>> updateAnswer(@PathVariable Long id, @RequestBody DtoUserAnswersIU dto) {
        dto.setUserAnswersId(id); // Ensure the ID is set in DTO for updating
        UserAnswers updatedAnswer = userAnswersService.updateAnswer(dto);
        return ResponseEntity.ok(ok(updatedAnswer));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<RootEntity<Void>> deleteAnswer(@PathVariable Long id) {
        userAnswersService.deleteAnswer(id);
        return ResponseEntity.ok(ok(null));
    }

    @Override
    @GetMapping("/user/{userId}")
    public ResponseEntity<RootEntity<List<UserAnswers>>> getAnswersByUserId(@PathVariable Long userId) {
        List<UserAnswers> answers = userAnswersService.getAnswersByUserId(userId);
        return ResponseEntity.ok(ok(answers));
    }

    @Override
    @GetMapping("/question/{questionId}")
    public ResponseEntity<RootEntity<List<UserAnswers>>> getAnswersByQuestionId(@PathVariable Long questionId) {
        List<UserAnswers> answers = userAnswersService.getAnswersByQuestionId(questionId);
        return ResponseEntity.ok(ok(answers));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RootEntity<UserAnswers>> getAnswerById(@PathVariable Long id) {
        UserAnswers answer = userAnswersService.getAnswerById(id);
        return ResponseEntity.ok(ok(answer));
    }
}