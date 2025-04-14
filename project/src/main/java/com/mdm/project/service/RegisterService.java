package com.mdm.project.service;

import com.mdm.project.entity.UserEntity;
import com.mdm.project.repository.UserCollectionRepository;
import com.mdm.project.request.RegisterRequest;
import com.mdm.project.utils.UsernameGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RegisterService {

    private UserCollectionRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegisterService(UserCollectionRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest registerRequest) {
        try {

            String encryptedPass = passwordEncoder.encode(registerRequest.password());

            UserEntity userEntity = new UserEntity();
            userEntity.setCustomerPhone(registerRequest.phoneNumber());
            userEntity.setPassword(encryptedPass);
            userEntity.setCustomerName(UsernameGenerator.generateUsername(8));

            userRepository.save(userEntity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }


    }

}
