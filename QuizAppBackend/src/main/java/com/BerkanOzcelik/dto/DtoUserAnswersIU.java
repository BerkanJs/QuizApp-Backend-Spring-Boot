package com.BerkanOzcelik.dto;



import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoUserAnswersIU {

    private Long userAnswersId;


    private String selectedOption;

    private String writtenAnswer;


    private Boolean isGraded;


    private Long userId; 


    private Long questionId; 
    
}
