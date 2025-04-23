package com.mdm.project.service;

import com.mdm.project.dto.UserCollectionDto;
import com.mdm.project.entity.UserEntity;
import com.mdm.project.exception.ResourceNotFoundException;
import com.mdm.project.exception.UserAlreadyExistException;
import com.mdm.project.mapper.UsersCollectionMapper;
import com.mdm.project.repository.UserCollectionRepository;
import com.mdm.project.request.UserInsertRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCollectionService {

    private final UserCollectionRepository userCollectionRepository;
    private final RedisIdGenerator idGenerator;

    public UserCollectionService(UserCollectionRepository userCollectionRepository, RedisIdGenerator idGenerator) {
        this.userCollectionRepository = userCollectionRepository;
        this.idGenerator = idGenerator;
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

    public boolean addUser(UserInsertRequest request) {
        Optional<UserEntity> userCollection = userCollectionRepository.findByCustomerPhone(request.getPhoneNumber());
        if (userCollection.isPresent()) {
            throw new UserAlreadyExistException("User Phone Number " + request.getPhoneNumber() + " already exists");
        }
        UserEntity newUser = new UserEntity();
        newUser.setCustomerId(idGenerator.getNextIdWithPrefix("user", "U"));
        newUser.setCustomerPhone(request.getPhoneNumber());
        newUser.setCustomerName(request.getName());
        newUser.setCustomerEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setCustomerGender(request.getGender());
        newUser.setCustomerDOB(request.getDOB());

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
