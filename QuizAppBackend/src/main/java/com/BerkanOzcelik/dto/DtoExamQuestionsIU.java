package com.BerkanOzcelik.dto;



import com.BerkanOzcelik.model.Exams;
import com.BerkanOzcelik.model.Questions;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoExamQuestionsIU {
    

    private Long examQuestionsId;
    

    private Exams examId;


    private Questions questionId;

    
}
