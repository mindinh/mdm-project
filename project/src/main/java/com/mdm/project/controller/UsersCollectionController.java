package com.mdm.project.controller;

import com.mdm.project.dto.ErrorResponseDto;
import com.mdm.project.dto.ResponseDto;
import com.mdm.project.dto.UserCollectionDto;
import com.mdm.project.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document")
public class UsersCollectionController {
    @Autowired
    private UserCollectionService userCollectionService;

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers() {
        List<UserCollectionDto> res = userCollectionService.getAllUsers().stream().map(
                userCollection -> {
                    UserCollectionDto userCollectionDto = new UserCollectionDto();
                    userCollectionDto.setId(userCollection.getCustomerId());
                    userCollectionDto.setName(userCollection.getName());
                    return userCollectionDto;
                }

        ).toList();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        UserCollectionDto userCollectionDto = userCollectionService.getUserById(id);

        return ResponseEntity.ok(userCollectionDto);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertUser(@RequestBody UserCollectionDto userCollectionDto) {
        boolean isSuccess = userCollectionService.addUser(userCollectionDto);
        if (isSuccess) {
            return ResponseEntity.ok("User created successfully");
        }
        else {
            return ResponseEntity.internalServerError().body(new ResponseDto("500", "Internal Server Error"));
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        boolean isSucess = userCollectionService.deleteUser(id);
        if (isSucess) {
            return ResponseEntity.ok("Successful");
        }
        else {
            return ResponseEntity.internalServerError().body(new ResponseDto("500", "Internal Server Error"));

        }
    }



}
