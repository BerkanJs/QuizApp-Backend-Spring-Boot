package com.BerkanOzcelik.enums;



import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ExamStatus {
    SCHEDULED,
    COMPLETED
}