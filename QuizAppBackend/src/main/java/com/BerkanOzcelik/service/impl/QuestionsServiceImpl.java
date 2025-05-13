package com.BerkanOzcelik.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.BerkanOzcelik.dto.DtoQuestions;
import com.BerkanOzcelik.dto.DtoQuestionsIU;
import com.BerkanOzcelik.exception.BaseException;
import com.BerkanOzcelik.exception.ErrorMessage;
import com.BerkanOzcelik.exception.MessageType;
import com.BerkanOzcelik.model.Categories;
import com.BerkanOzcelik.model.Departments;
import com.BerkanOzcelik.model.Questions;
import com.BerkanOzcelik.model.User;
import com.BerkanOzcelik.repository.CategoriesRepository;
import com.BerkanOzcelik.repository.DepartmentsRepository;
import com.BerkanOzcelik.repository.QuestionsRepository;
import com.BerkanOzcelik.repository.UserRepository;
import com.BerkanOzcelik.service.IQuestionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionsServiceImpl implements IQuestionService {

    private final QuestionsRepository questionsRepository;
    private final UserRepository userRepository;
    private final DepartmentsRepository departmentsRepository;
    private final CategoriesRepository categoriesRepository;

    @Override
    public Questions createQuestion(DtoQuestionsIU dto) {
        if (dto.getUserId() == null || dto.getDepartmentId() == null || dto.getCategoryId() == null) {
            throw new BaseException(new ErrorMessage(MessageType.INVALID_INPUT,
                    "User, Department, or Category cannot be null"));
        }

        Questions question = new Questions();
        question.setQuestionText(dto.getQuestionText());
        question.setQuestionType(dto.getQuestionType());
        question.setOptions(dto.getOptions());
        question.setOption(dto.getOption());
        question.setQuestionPoint(dto.getQuestionPoint());

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "User")));
        Departments department = departmentsRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Department")));
        Categories category = categoriesRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Category")));

        question.setUser(user); // istersen: question.setUser(user);
        question.setDepartment(department);
        question.setCategory(category);

        return questionsRepository.save(question);
    }

    @Override
    public Questions updateQuestion(DtoQuestionsIU dto) {
        Questions existing = questionsRepository.findById(dto.getQuestionId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Question")));

        existing.setQuestionText(dto.getQuestionText());
        existing.setQuestionType(dto.getQuestionType());
        existing.setOptions(dto.getOptions());
        existing.setOption(dto.getOption());
        existing.setQuestionPoint(dto.getQuestionPoint());

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "User")));
        Departments department = departmentsRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Department")));
        Categories category = categoriesRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Category")));

        existing.setUser(user); // istersen: existing.setUser(user);
        existing.setDepartment(department);
        existing.setCategory(category);

        return questionsRepository.save(existing);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        Questions question = questionsRepository.findById(questionId)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "Question")));
        questionsRepository.delete(question);
    }

    @Override
    public List<DtoQuestions> getAllQuestions() {
        return questionsRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private DtoQuestions convertToDto(Questions question) {
        DtoQuestions dto = new DtoQuestions();
        dto.setQuestionText(question.getQuestionText());
        dto.setQuestionType(question.getQuestionType());
        dto.setOptions(question.getOptions());
        dto.setOption(question.getOption());
        dto.setReviewedBy(question.getReviewedBy());
        dto.setReviewedAt(question.getReviewedAt());
        dto.setQuestionPoint(question.getQuestionPoint());
        dto.setUser_Id(question.getUser().getId());
        dto.setDepartmentId(question.getDepartment().getId());
        dto.setCategoryId(question.getCategory().getId());
        return dto;
    }
}
