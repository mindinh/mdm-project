package com.mdm.project.request;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class UpdateUserRequest {

    private String email;
    private String name;
    private String username;
    private String phoneNumber;
    private String gender;
    private String DOB;

    private MultipartFile avatarImg;

    private String addresses;

    private String cards;
}
