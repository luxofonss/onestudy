package com.quyennv.lms.repository.impl;

import com.quyennv.lms.entities.Auth;
import com.quyennv.lms.entities.User;
import com.quyennv.lms.mappers.AuthMapper;
import com.quyennv.lms.mappers.UserMapper;
import com.quyennv.lms.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper usersMapper;

    private final AuthMapper authMapper;

    public UserRepositoryImpl(UserMapper usersMapper, AuthMapper authMapper) {
        this.usersMapper = usersMapper;
        this.authMapper = authMapper;
    }


    @Override
    public Optional<User> getUserById(String userId) {
        return Optional.ofNullable(usersMapper.selectByPrimaryKey(UUID.fromString(userId)));
    }


    @Override
    public Optional<User> getByUsername(String username) {
        User user = usersMapper.selectByUsername(username);

        if (Objects.nonNull(user) && user.getDeletedAt() != null) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(user);
        }
    }

    @Override
    public Optional<User> getByUsernameForAuth(String username) {
        User user = usersMapper.selectByUsername(username);

        if (Objects.nonNull(user) && user.getDeletedAt() != null) {
            return Optional.empty();
        } else {
            Auth auth = authMapper.selectByUserId(user.getId());
            user.setAuth(auth);
            return Optional.of(user);
        }
    }


    @Override
    @Transactional
    public Integer persist(User user) {
        user.setId(UUID.randomUUID());
        usersMapper.insert(user);

        Auth auth = user.getAuth();
        auth.setUserId(user.getId());
        authMapper.insert(auth);

        return 1;
    }

    @Override
    public List<User> getByUserIdList(List<UUID> userIds) {
        return usersMapper.selectByUserIdList(userIds);
    }

}
