package com.BerkanOzcelik.dto;

import com.BerkanOzcelik.enums.UserRole;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoUser extends DtoBase{
    
    private String username;

    private String email;

    private UserRole userRole;

    private Long departmentId;

    private String password;
    

    
}
