package com.freimanvs.javapracticum.repositories;

import com.freimanvs.javapracticum.dto.User;

import java.util.Optional;

public interface UserCrudRepository<T, ID> extends CrudRepository<T, ID> {
    Optional<T> findByEmail(String email);
    User deleteByEmail(String email);
    User updateByEmail(String email, User user);
}
