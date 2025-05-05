package com.BerkanOzcelik.dto;

import java.util.Date;

import com.BerkanOzcelik.enums.ExamStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DtoExams extends DtoBase {

    private String name;
    private Long departmentId;
    private String createdBy;
    private Date startingTime;
    private Date endingTime;
    private ExamStatus examStatus;
}
