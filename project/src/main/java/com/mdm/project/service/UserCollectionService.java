package com.mdm.project.service;

import com.mdm.project.dto.UserCollectionDto;
import com.mdm.project.entity.UserEntity;
import com.mdm.project.exception.ResourceNotFoundException;
import com.mdm.project.exception.UserAlreadyExistException;
import com.mdm.project.mapper.UsersCollectionMapper;
import com.mdm.project.repository.UserCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCollectionService {

    private UserCollectionRepository userCollectionRepository;
    public UserCollectionService(UserCollectionRepository userCollectionRepository) {
        this.userCollectionRepository = userCollectionRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userCollectionRepository.findAll();
    }

    public UserCollectionDto getUserById(String customerId) {
        UserEntity userEntity = userCollectionRepository.findByCustomerId(customerId).orElseThrow(
                () -> new ResourceNotFoundException("User collection", "customerId", customerId)
        );

        return UsersCollectionMapper.mapToDto(userEntity);

    }

    public boolean addUser(UserCollectionDto user) {
        Optional<UserEntity> userCollection = userCollectionRepository.findByCustomerId(user.getId());
        if (userCollection.isPresent()) {
            throw new UserAlreadyExistException("UserId " + user.getId() + " already exists");
        }
        UserEntity newUser = UsersCollectionMapper.mapToEntity(user);

        userCollectionRepository.save(newUser);

        return true;
    }

    public boolean updateUser(String id, UserCollectionDto userDto) {
        Optional<UserEntity> userCollection = userCollectionRepository.findByCustomerId(id);
        if (userCollection.isEmpty()) {
            return false;
        }
        UserEntity userEntity = userCollection.get();

        if (userDto.getName() != null) {
            userEntity.setCustomerName(userDto.getName());
        }
        if (userDto.getEmail() != null) {
            userEntity.setCustomerEmail(userDto.getEmail());
        }
        if (userDto.getPhoneNumber() != null) {
            userEntity.setCustomerPhone(userDto.getPhoneNumber());
        }
        if (userDto.getDOB() != null) {
            userEntity.setCustomerDOB(userDto.getDOB());
        }
        if (userDto.getGender() != null) {
            userEntity.setCustomerGender(userDto.getGender());
        }
        if (userDto.getAddresses() != null) {
            userEntity.setCustomerAddress(userDto.getAddresses());
        }
        if (userDto.getCards() != null) {
            userEntity.setCustomerCards(userDto.getCards());
        }

        userCollectionRepository.save(userEntity);

        return true;
    }

    public boolean deleteUser(String customerId) {
        UserEntity userEntity = userCollectionRepository.findByCustomerId(customerId).orElseThrow(
                () -> new ResourceNotFoundException("User collection", "customerId", customerId)
        );
        userCollectionRepository.deleteByCustomerId(customerId);

        return true;
    }



}
