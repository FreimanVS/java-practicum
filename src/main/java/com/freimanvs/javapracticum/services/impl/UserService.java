package com.freimanvs.javapracticum.services.impl;

import com.freimanvs.javapracticum.dto.User;
import com.freimanvs.javapracticum.exceptions.FinanceTrackerException;
import com.freimanvs.javapracticum.repositories.UserCrudRepository;
import com.freimanvs.javapracticum.services.CommonService;

public class UserService implements CommonService<String, User> {

    private final UserCrudRepository<User, Long> userRepository;

    public UserService(UserCrudRepository<User, Long> userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        validate(user);
        return userRepository.save(user);
    }

    public User getBy(String email) {
        validateEmail(email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new FinanceTrackerException("No such user!"));
    }

    public User updateBy(String email, User newUser) {
        validateEmail(email);
        validate((newUser));

        User oldUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new FinanceTrackerException("No such user!"));
        oldUser.setName(newUser.getName());
        oldUser.setPassword(newUser.getPassword());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setRole(newUser.getRole());
        return userRepository.updateByEmail(email, oldUser);
    }

    public String deleteBy(String email) {
        validateEmail(email);
        User removedUser = userRepository.deleteByEmail(email);
        return removedUser.getEmail();
    }

    private static void validate(User user) {
        if (user == null
                || !isValidName(user.getName())
                || !isValidPassword(user.getPassword())
                || !isValidEmail(user.getEmail())) {
            throw new FinanceTrackerException("Invalid user's data!");
        }
    }

    private static void validateEmail(String email) {
        if (!isValidEmail(email)) {
            throw new FinanceTrackerException("Invalid email!");
        }
    }

    private static boolean isValidName(String name) {
        return name != null;
    }

    private static boolean isValidPassword(String password) {
        return password != null;
    }

    private static boolean isValidEmail(String email) {
        return email != null;
    }
}
