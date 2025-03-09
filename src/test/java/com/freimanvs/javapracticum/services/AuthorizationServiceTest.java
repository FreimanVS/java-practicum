package com.freimanvs.javapracticum.services;

import com.freimanvs.javapracticum.dto.AuthorizationProfile;
import com.freimanvs.javapracticum.dto.User;
import com.freimanvs.javapracticum.exceptions.FinanceTrackerException;
import com.freimanvs.javapracticum.mappings.FinanceTrackerMapping;
import com.freimanvs.javapracticum.services.impl.ProfileAuthorizationService;
import com.freimanvs.javapracticum.services.impl.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AuthorizationServiceTest {
    private final UserService userService = mock(UserService.class);
    private final FinanceTrackerMapping financeTrackerMapping = mock(FinanceTrackerMapping.class);
    private final AuthorizationService<AuthorizationProfile> authorizationService = new ProfileAuthorizationService(userService, financeTrackerMapping);

    @Test
    void authorizeFailure() {
        AuthorizationProfile profile = new AuthorizationProfile()
                .setName("Vasiliy")
                .setEmail("vasiliy@mail.ru")
                .setPassword("vasiliy1234");
        when(userService.getBy(anyString())).thenReturn(new User());

        assertThrowsExactly(FinanceTrackerException.class, () -> authorizationService.authorize(profile));
    }

    @Test
    void authorizeSuccess() {
        AuthorizationProfile profile = new AuthorizationProfile()
                .setName("Vasiliy")
                .setEmail("vasiliy@mail.ru")
                .setPassword("vasiliy1234");

        when(userService.getBy(anyString())).thenReturn(new User()
                .setName(profile.getName())
                .setEmail(profile.getEmail())
                .setPassword(profile.getPassword()));

        User authorizedUser = authorizationService.authorize(profile);

        assertEquals(profile.getName(), authorizedUser.getName());
        assertEquals(profile.getEmail(), authorizedUser.getEmail());
        assertEquals(profile.getPassword(), authorizedUser.getPassword());
    }
}