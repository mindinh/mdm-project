package com.mdm.project.mapper;

import com.mdm.project.dto.UserCollectionDto;
import com.mdm.project.entity.UserEntity;

public class UsersCollectionMapper {

    public static UserCollectionDto mapToDto(UserEntity userEntity) {
        UserCollectionDto dto = new UserCollectionDto();
        dto.setId(userEntity.getCustomerId());
        dto.setName(userEntity.getCustomerName());
        dto.setUsername(userEntity.getUsername());
        dto.setDOB(userEntity.getCustomerDOB());
        dto.setEmail(userEntity.getCustomerEmail());
        dto.setPhoneNumber(userEntity.getCustomerPhone());
        dto.setGender(userEntity.getCustomerGender());
        dto.setAvatar(userEntity.getCustomerAvatar() == null ? "" : "http://localhost:8080" + userEntity.getCustomerAvatar());
        dto.setAddresses(userEntity.getCustomerAddress());
        dto.setCards(userEntity.getCustomerCards());
        return dto;
    }

    public static UserEntity mapToEntity(UserCollectionDto dto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setCustomerId(dto.getId());
        userEntity.setCustomerName(dto.getName());
        userEntity.setCustomerDOB(dto.getDOB());
        userEntity.setCustomerEmail(dto.getEmail());
        userEntity.setCustomerPhone(dto.getPhoneNumber());
        userEntity.setCustomerGender(dto.getGender());
        userEntity.setCustomerAvatar(dto.getAvatar());
        userEntity.setCustomerAddress(dto.getAddresses());
        userEntity.setCustomerCards(dto.getCards());

        return userEntity;
    }

}
