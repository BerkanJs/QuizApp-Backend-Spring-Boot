package com.BerkanOzcelik.dto;



import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCategoriesIU {



    private Long CategoriesId;  


    private String categoryName;


    private Long departmentId; 
}
