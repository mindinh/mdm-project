package com.mdm.project.controller;

import com.mdm.project.dto.ErrorResponseDto;
import com.mdm.project.dto.ResponseDto;
import com.mdm.project.dto.UserCollectionDto;
import com.mdm.project.mapper.UsersCollectionMapper;
import com.mdm.project.request.UserInsertRequest;
import com.mdm.project.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersCollectionController {
    @Autowired
    private UserCollectionService userCollectionService;

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers() {
        List<UserCollectionDto> res = userCollectionService.getAllUsers().stream().map(
                UsersCollectionMapper::mapToDto

        ).toList();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        UserCollectionDto userCollectionDto = userCollectionService.getUserById(id);

        return ResponseEntity.ok(userCollectionDto);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertUser(@RequestBody UserInsertRequest userInsertRequest) {
        boolean isSuccess = userCollectionService.addUser(userInsertRequest);
        if (isSuccess) {
            return ResponseEntity.ok("User created successfully");
        }
        else {
            return ResponseEntity.internalServerError().body(new ResponseDto("500", "Internal Server Error"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserCollectionDto userCollectionDto) {
        boolean isSuccess = userCollectionService.updateUser(id, userCollectionDto);
        if (!isSuccess) {
            return ResponseEntity.internalServerError().body(new ResponseDto("500", "Internal Server Error"));
        }
        return ResponseEntity.ok(new ResponseDto("200", "User updated successfully"));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        boolean isSuccess = userCollectionService.deleteUser(id);
        if (isSuccess) {
            return ResponseEntity.ok("Successful");
        }
        else {
            return ResponseEntity.internalServerError().body(new ResponseDto("500", "Internal Server Error"));

        }
    }



}
