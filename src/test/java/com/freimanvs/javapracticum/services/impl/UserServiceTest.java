package com.freimanvs.javapracticum.services.impl;

import com.freimanvs.javapracticum.dto.Role;
import com.freimanvs.javapracticum.dto.User;
import com.freimanvs.javapracticum.exceptions.FinanceTrackerException;
import com.freimanvs.javapracticum.repositories.UserCrudRepository;
import com.freimanvs.javapracticum.repositories.impls.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private final UserCrudRepository<User, Long> userRepository = mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);

    @Test
    void createSuccess() {
        User user = new User()
                .setName("name")
                .setEmail("email@mail.ru")
                .setPassword("password");

        when(userRepository.save(any(User.class)))
                .thenReturn(user.setId(1L));

        User createdUser = userService.create(user);

        assertEquals(user.getName(), createdUser.getName());
        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(user.getPassword(), createdUser.getPassword());
        assertEquals(1L, createdUser.getId());
        assertEquals(Role.USER, createdUser.getRole());
    }

    @Test
    void getSuccess() {
        String email = "email@mail.ru";

        when(userRepository.findByEmail(same(email)))
                .thenReturn(Optional.of(new User().setEmail(email)));

        User user = userService.getBy(email);

        assertEquals(email, user.getEmail());
    }

    @Test
    void getFailure() {
        String email = "email@mail.ru";

        when(userRepository.findByEmail(same(email)))
                .thenReturn(Optional.ofNullable(null));

        assertThrowsExactly(FinanceTrackerException.class, () -> userService.getBy(email));
    }

    @Test
    void updateSuccess() {
        String email = "mail@mail.ru";
        User user = new User()
                .setName("name2")
                .setEmail("email2@mail.ru")
                .setPassword("password2")
                .setRole(Role.ADMIN);

        when(userRepository.updateByEmail(same(email), same(user)))
                .thenReturn(user);
        when(userRepository.findByEmail(same(email)))
                .thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class)))
                .thenReturn(user.setId(1L));

        User updatedUser = userService.updateBy(email, user);

        assertEquals(user.getName(), updatedUser.getName());
        assertEquals(user.getEmail(), updatedUser.getEmail());
        assertEquals(user.getPassword(), updatedUser.getPassword());
        assertEquals(user.getRole(), updatedUser.getRole());
    }

    @Test
    void deleteBy() {
        String email = "mail@mail.ru";

        when(userRepository.deleteByEmail(same(email)))
                .thenReturn(new User().setEmail(email));

        String deletedByEmail = userService.deleteBy(email);

        assertEquals(email, deletedByEmail);
    }
}