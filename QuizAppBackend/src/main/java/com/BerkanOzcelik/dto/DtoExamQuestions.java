package com.BerkanOzcelik.dto;

import com.BerkanOzcelik.model.Exams;
import com.BerkanOzcelik.model.Questions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoExamQuestions extends DtoBase {
    private Long examId;
    private Long questionId;
}
