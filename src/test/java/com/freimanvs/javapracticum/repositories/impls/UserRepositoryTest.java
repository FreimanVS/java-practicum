package com.freimanvs.javapracticum.repositories.impls;

import com.freimanvs.javapracticum.dto.Role;
import com.freimanvs.javapracticum.dto.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = spy(UserRepository.class);
    }

    @AfterEach
    void tearDown() {
        userRepository = null;
    }

    @Test
    void save() {
        User user = new User()
                .setName("name")
                .setEmail("email@mail.ru")
                .setPassword("password");

        User savedUser = userRepository.save(user);

        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getPassword(), savedUser.getPassword());
        assertEquals(Role.USER, savedUser.getRole());
        assertNotNull(savedUser.getId());

        user = new User()
                .setName("name2")
                .setEmail("email2@mail.ru")
                .setPassword("password2");

        User savedUser2 = userRepository.save(user);
    }

    @Test
    void updateByEmail() {
        String email = "email@mail.ru";
        User user = new User()
                .setName("name")
                .setEmail("email2@mail.ru")
                .setPassword("password");

        doAnswer(o -> o.getArgument(0, User.class).setId(1L))
                .when(userRepository).save(any(User.class));

        User updatedUser = userRepository.updateByEmail(email, user);
        assertEquals(user.getName(), updatedUser.getName());
        assertEquals(user.getEmail(), updatedUser.getEmail());
        assertEquals(user.getPassword(), updatedUser.getPassword());
    }

//    @Test
//    void findByIdFailure() {
//        Long id = 1L;
//        assertEquals(Optional.empty(), userRepository.findById(id));
//    }

    @Test
    void findByIdSuccess() {
        User user = new User()
                .setName("name")
                .setEmail("email@mail.ru")
                .setPassword("password");
        User savedUser = userRepository.save(user);
        Long id = savedUser.getId();

        User gottenUser = userRepository.findById(id).get();

        assertNotNull(gottenUser);
        assertEquals(id, gottenUser.getId());
    }

    @Test
    void findByEmailSuccess() {
        String email = "email@mail.ru";
        User user = new User()
                .setName("name")
                .setEmail(email)
                .setPassword("password");
        userRepository.save(user);

        User gottenUser = userRepository.findByEmail(email).get();

        assertNotNull(gottenUser);
        assertEquals(email, gottenUser.getEmail());
    }

    @Test
    void findAll() {
        User user1 = new User()
                .setName("name")
                .setEmail("email@mail.ru")
                .setPassword("password");
        User user2 = new User()
                .setName("name2")
                .setEmail("email2@mail.ru")
                .setPassword("password2");

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> allUsers = userRepository.findAll();

    }

    @Test
    void deleteById() {
        User user1 = new User()
                .setId(1L)
                .setName("name")
                .setEmail("email@mail.ru")
                .setPassword("password");
        User user2 = new User()
                .setId(2L)
                .setName("name2")
                .setEmail("email2@mail.ru")
                .setPassword("password2");

        userRepository.save(user1);
        userRepository.save(user2);

        userRepository.deleteById(1L);

        List<User> allUsers = userRepository.findAll();

        assertEquals(user2.getName(), allUsers.get(0).getName());
    }

    @Test
    void deleteByEmail() {
        String email2 = "email2@mail.ru";
        User user1 = new User()
                .setId(1L)
                .setName("name")
                .setEmail("email@mail.ru")
                .setPassword("password");
        User user2 = new User()
                .setId(2L)
                .setName("name2")
                .setEmail(email2)
                .setPassword("password2");

        userRepository.save(user1);
        userRepository.save(user2);

        userRepository.deleteByEmail(email2);

        List<User> allUsers = userRepository.findAll();

        assertEquals(user1.getName(), allUsers.get(0).getName());
    }
}