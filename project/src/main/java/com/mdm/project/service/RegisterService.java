package com.mdm.project.service;

import com.mdm.project.entity.UserEntity;
import com.mdm.project.exception.UserAlreadyExistException;
import com.mdm.project.repository.UserCollectionRepository;
import com.mdm.project.request.RegisterRequest;
import com.mdm.project.utils.UsernameGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class RegisterService {

    private UserCollectionRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegisterService(UserCollectionRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest registerRequest) {
        Optional<UserEntity> user = userRepository.findByCustomerPhone(registerRequest.phoneNumber());
        if (user.isPresent()) {
            throw new UserAlreadyExistException("User with phone number " + registerRequest.phoneNumber() + " already exists");
        }
        else {
            String encryptedPass = passwordEncoder.encode(registerRequest.password());

            UserEntity userEntity = new UserEntity();
            userEntity.setCustomerId(UUID.randomUUID().toString());
            userEntity.setCustomerPhone(registerRequest.phoneNumber());
            userEntity.setPassword(encryptedPass);
            userEntity.setUsername(UsernameGenerator.generateUsername(8));

            userRepository.save(userEntity);
        }


    }

}
