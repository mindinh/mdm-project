package com.mdm.project.service;

import com.mdm.project.dto.UserCollectionDto;
import com.mdm.project.entity.UserCollection;
import com.mdm.project.exception.ResourceNotFoundException;
import com.mdm.project.exception.UserAlreadyExistException;
import com.mdm.project.mapper.UsersCollectionMapper;
import com.mdm.project.repository.UserCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserCollectionService {

    @Autowired
    private UserCollectionRepository userCollectionRepository;

    public List<UserCollection> getAllUsers() {
        return userCollectionRepository.findAll();
    }

    public UserCollectionDto getUserById(String customerId) {
        UserCollection userCollection = userCollectionRepository.findByCustomerId(customerId).orElseThrow(
                () -> new ResourceNotFoundException("User collection", "customerId", customerId)
        );

        return UsersCollectionMapper.mapToDto(userCollection);

    }

    public boolean addUser(UserCollectionDto user) {
        Optional<UserCollection> userCollection = userCollectionRepository.findByCustomerId(user.getId());
        if (userCollection.isPresent()) {
            throw new UserAlreadyExistException("UserId " + user.getId() + " already exists");
        }
        UserCollection newUser = UsersCollectionMapper.mapToEntity(user);

        userCollectionRepository.save(newUser);

        return true;
    }

    public boolean deleteUser(String customerId) {
        UserCollection userCollection = userCollectionRepository.findByCustomerId(customerId).orElseThrow(
                () -> new ResourceNotFoundException("User collection", "customerId", customerId)
        );
        userCollectionRepository.deleteByCustomerId(customerId);

        return true;
    }

}
