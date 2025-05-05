package com.BerkanOzcelik.dto;





import com.BerkanOzcelik.enums.UserRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoUserIU {


    private Long userId;


    private String username;
    

    private String email;


    private String password;


    @Enumerated(EnumType.STRING)
    private UserRole userRole;


    private Long departmentId;

}
