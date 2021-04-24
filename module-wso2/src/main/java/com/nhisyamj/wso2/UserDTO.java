package com.nhisyamj.wso2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String password;
    private String emailAddress;
    private String fullName;
    private String mobileNumber;
}
