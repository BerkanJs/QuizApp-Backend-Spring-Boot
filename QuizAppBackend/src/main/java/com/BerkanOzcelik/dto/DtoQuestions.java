package com.BerkanOzcelik.dto;

import java.util.Date;
import java.util.List;

import com.BerkanOzcelik.enums.QuestionType;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DtoQuestions extends DtoBase {

    
    private String questionText;

    private QuestionType questionType;

    private List<String> options;

    private String option; 

    private Date reviewedBy;

    private Date reviewedAt;

    private Long questionPoint;

    private Long user_Id;  

    private Long departmentId;  

    private Long categoryId;  
    
}
