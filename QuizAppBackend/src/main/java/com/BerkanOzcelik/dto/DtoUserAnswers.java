package com.BerkanOzcelik.dto;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoUserAnswers extends DtoBase{

    private String selectedOption;
    private String writtenAnswer;
    private Boolean isGraded;
    private Long userId;
    private Long questionId;


    
}
