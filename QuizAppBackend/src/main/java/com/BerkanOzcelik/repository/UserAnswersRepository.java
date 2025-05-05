package com.BerkanOzcelik.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BerkanOzcelik.model.UserAnswers;

@Repository
public interface UserAnswersRepository extends JpaRepository<UserAnswers, Long> {
    List<UserAnswers> findByUserId_Id(Long userId);

    List<UserAnswers> findByQuestionId_Id(Long questionId);

}
