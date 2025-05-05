package com.BerkanOzcelik.service.impl;

import com.BerkanOzcelik.dto.DtoUserAnswers;
import com.BerkanOzcelik.dto.DtoUserAnswersIU;
import com.BerkanOzcelik.enums.QuestionType;
import com.BerkanOzcelik.exception.BaseException;
import com.BerkanOzcelik.exception.ErrorMessage;
import com.BerkanOzcelik.exception.MessageType;
import com.BerkanOzcelik.model.Questions;
import com.BerkanOzcelik.model.User;
import com.BerkanOzcelik.model.UserAnswers;
import com.BerkanOzcelik.repository.QuestionsRepository;
import com.BerkanOzcelik.repository.UserAnswersRepository;
import com.BerkanOzcelik.repository.UserRepository;
import com.BerkanOzcelik.service.IUserAnswersService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAnswersServiceImpl implements IUserAnswersService {
    private final UserAnswersRepository userAnswersRepository;
    private final UserRepository userRepository;
    private final QuestionsRepository questionsRepository;

    @Override
    public UserAnswers createAnswer(DtoUserAnswers dto) {
        UserAnswers answer = new UserAnswers();

        Questions question = questionsRepository.findById(dto.getQuestionId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Question")));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "User")));

        answer.setQuestionId(question);
        answer.setUserId(user);
        answer.setIsGraded(dto.getIsGraded());

        if (question.getQuestionType() == QuestionType.CLASSIC) {
            answer.setWrittenAnswer(dto.getWrittenAnswer());
            answer.setSelectedOption(null);
        } else {
            answer.setSelectedOption(dto.getSelectedOption());
            answer.setWrittenAnswer(null);
        }

        return userAnswersRepository.save(answer);

    }

    @Override
    public UserAnswers updateAnswer(DtoUserAnswersIU dto) {
        UserAnswers existing = userAnswersRepository.findById(dto.getUserAnswersId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "User Answer")));

        Questions question = questionsRepository.findById(dto.getQuestionId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Question")));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "User")));

        existing.setQuestionId(question);
        existing.setUserId(user);
        existing.setIsGraded(dto.getIsGraded());

        if (question.getQuestionType() == QuestionType.CLASSIC) {
            existing.setWrittenAnswer(dto.getWrittenAnswer());
            existing.setSelectedOption(null);
        } else {
            existing.setSelectedOption(dto.getSelectedOption());
            existing.setWrittenAnswer(null);
        }

        return userAnswersRepository.save(existing);
    }

    @Override
    public void deleteAnswer(Long answerId) {
        UserAnswers answer = userAnswersRepository.findById(answerId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "User Answer")));
        userAnswersRepository.delete(answer);
    }

    @Override
    public List<UserAnswers> getAnswersByUserId(Long userId) {
        return userAnswersRepository.findByUserId_Id(userId);
    }

    @Override
    public List<UserAnswers> getAnswersByQuestionId(Long questionId) {
        return userAnswersRepository.findByQuestionId_Id(questionId);
    }

    @Override
    public UserAnswers getAnswerById(Long id) {
        return userAnswersRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "User Answer")));
    }

}
