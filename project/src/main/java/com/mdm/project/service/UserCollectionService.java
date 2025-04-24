package com.mdm.project.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mdm.project.dto.UserCollectionDto;
import com.mdm.project.entity.AddressEntity;
import com.mdm.project.entity.CreditCardEntity;
import com.mdm.project.entity.UserEntity;
import com.mdm.project.exception.ResourceNotFoundException;
import com.mdm.project.exception.UserAlreadyExistException;
import com.mdm.project.mapper.UsersCollectionMapper;
import com.mdm.project.repository.UserCollectionRepository;
import com.mdm.project.request.UpdateUserRequest;
import com.mdm.project.request.UserInsertRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserCollectionService {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private final UserCollectionRepository userCollectionRepository;
    private final RedisIdGenerator idGenerator;
    private final ObjectMapper objectMapper;
    private final FileService fileService;

    public UserCollectionService(UserCollectionRepository userCollectionRepository, RedisIdGenerator idGenerator, ObjectMapper objectMapper, FileService fileService) {
        this.userCollectionRepository = userCollectionRepository;
        this.idGenerator = idGenerator;
        this.objectMapper = objectMapper;
        this.fileService = fileService;
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

    public boolean updateUser(String id, UpdateUserRequest request) {

        Optional<UserEntity> userCollection = userCollectionRepository.findByCustomerId(id);
        if (userCollection.isEmpty()) {
            return false;
        }
        UserEntity userEntity = userCollection.get();

        if (request.getName() != null) {
            userEntity.setCustomerName(request.getName());
        }
        if (request.getEmail() != null) {
            userEntity.setCustomerEmail(request.getEmail());
        }
        if (request.getPhoneNumber() != null) {
            userEntity.setCustomerPhone(request.getPhoneNumber());
        }

        if (request.getUsername() != null) {
            userEntity.setUsername(request.getUsername());
        }

        if (request.getDOB() != null) {
            try {
                Date dob = dateFormat.parse(request.getDOB());
                String formattedDate = dateFormat.format(dob);
                userEntity.setCustomerDOB(formattedDate);

            } catch (ParseException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        if (request.getGender() != null) {
            userEntity.setCustomerGender(request.getGender());
        }
        if (request.getAddresses() != null) {
            try {
                List<AddressEntity> addressEntityList = objectMapper.readValue(request.getAddresses(), new TypeReference<List<AddressEntity>>() {});
                userEntity.setCustomerAddress(addressEntityList);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (request.getCards() != null) {
            try {
                List<CreditCardEntity> creditCardEntityList = objectMapper.readValue(request.getCards(), new TypeReference<List<CreditCardEntity>>() {});
                userEntity.setCustomerCards(creditCardEntityList);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (request.getAvatarImg() != null) {
            String filePath = fileService.copyAvatarFile(request.getAvatarImg(), id);
            userEntity.setCustomerAvatar(filePath);
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
