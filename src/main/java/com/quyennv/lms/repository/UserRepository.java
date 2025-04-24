package com.quyennv.lms.repository;


import com.quyennv.lms.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> getUserById(String userId);

    Optional<User> getByUsername(String username);

    Optional<User> getByUsernameForAuth(String username);

    Integer persist(User user);

    List<User> getByUserIdList(List<UUID> studentIds);
}
