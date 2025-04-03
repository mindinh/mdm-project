package com.mdm.project.mapper;

import com.mdm.project.dto.UserCollectionDto;
import com.mdm.project.entity.UserCollection;

public class UsersCollectionMapper {

    public static UserCollectionDto mapToDto(UserCollection userCollection) {
        UserCollectionDto dto = new UserCollectionDto();
        dto.setId(userCollection.getCustomerId());
        dto.setName(userCollection.getName());
        return dto;
    }

    public static UserCollection mapToEntity(UserCollectionDto dto) {
        UserCollection userCollection = new UserCollection();
        userCollection.setCustomerId(dto.getId());
        userCollection.setName(dto.getName());
        return userCollection;
    }

}
