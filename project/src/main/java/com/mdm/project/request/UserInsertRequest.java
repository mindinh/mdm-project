package com.mdm.project.request;

import lombok.Data;

@Data
public class UserInsertRequest {

    private String email;
    private String name;
    private String phoneNumber;
    private String password;
    private String gender;
    private String DOB;

}
