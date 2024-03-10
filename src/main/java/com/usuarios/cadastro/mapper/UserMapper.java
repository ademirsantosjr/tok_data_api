package com.usuarios.cadastro.mapper;

import com.usuarios.cadastro.entity.Profile;
import com.usuarios.cadastro.entity.User;
import com.usuarios.cadastro.record.UserRecord;

public class UserMapper {

    public static UserRecord toRecord(User user) {
        return new UserRecord(
                user.getId(),
                user.getName(),
                user.getEmail(),
                null,
                user.getProfile().getName()
        );
    }

    public static User toModel(UserRecord userRecord) {
        return new User(
                userRecord.id(),
                userRecord.name(),
                userRecord.email(),
                userRecord.password(),
                new Profile(null, userRecord.profile(), null)
        );
    }
}
