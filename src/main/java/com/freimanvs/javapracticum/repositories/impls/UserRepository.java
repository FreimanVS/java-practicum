package com.freimanvs.javapracticum.repositories.impls;

import com.freimanvs.javapracticum.dto.User;
import com.freimanvs.javapracticum.repositories.UserCrudRepository;
import com.freimanvs.javapracticum.utils.GeneratorUtil;

import java.util.*;

public class UserRepository implements UserCrudRepository<User, Long> {
    private static final Map<Long, User> usersMapById = new HashMap<>();
    private static final Map<String, Long> usersEmailToIdToMap = new HashMap<>();
    private static final GeneratorUtil generatorUtil = new GeneratorUtil();
    
    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(generatorUtil.generateId());
        }
        usersMapById.put(user.getId(), user);
        usersEmailToIdToMap.put(user.getEmail(), user.getId());
        return usersMapById.get(user.getId());
    }

    @Override
    public User updateByEmail(String email, User user) {
        usersEmailToIdToMap.remove(email);
        return save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = usersMapById.get(id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Long id = usersEmailToIdToMap.get(email);
        User user = usersMapById.get(id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return usersMapById.values().stream().toList();
    }

    @Override
    public User deleteById(Long id) {
        return usersMapById.remove(id);
    }

    @Override
    public User deleteByEmail(String email) {
        Long id = usersEmailToIdToMap.get(email);
        return deleteById(id);
    }
}
