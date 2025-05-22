package com.BerkanOzcelik.dto;

import java.util.List;



import com.BerkanOzcelik.enums.QuestionType;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoQuestionsIU {

    private Long questionId;

    private String questionText;

    private QuestionType questionType;

    private List<String> options;

    @JsonProperty("correctOption")
    private String option;

    private Long questionPoint;

    private Long userId;

    private Long departmentId;

    private Long categoryId;

    private String photoUrl;
}
