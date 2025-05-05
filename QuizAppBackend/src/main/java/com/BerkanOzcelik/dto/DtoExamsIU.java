package com.BerkanOzcelik.dto;

import java.util.Date;



import com.BerkanOzcelik.enums.ExamStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoExamsIU {

    private Long examId;
    private String name;
    private Long departmentId;
    private String createdBy;
    private Date startingTime;
    private Date endingTime;
    private ExamStatus examStatus;
}
