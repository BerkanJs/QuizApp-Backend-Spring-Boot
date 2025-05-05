package com.BerkanOzcelik.dto;

import java.util.Date;



import com.BerkanOzcelik.enums.ExamResultsStatus;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoExamResultsIU {


    private Long examResultsId;


    private Long score;


    private ExamResultsStatus examResultsStatus;


    private Date submittedAt;


    private Long examId;


    private Long userId;
    
}
