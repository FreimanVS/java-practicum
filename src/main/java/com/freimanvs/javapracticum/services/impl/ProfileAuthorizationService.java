package com.freimanvs.javapracticum.services.impl;

import com.freimanvs.javapracticum.dto.AuthorizationProfile;
import com.freimanvs.javapracticum.dto.User;
import com.freimanvs.javapracticum.exceptions.FinanceTrackerException;
import com.freimanvs.javapracticum.mappings.FinanceTrackerMapping;
import com.freimanvs.javapracticum.services.AuthorizationService;

public class ProfileAuthorizationService implements AuthorizationService<AuthorizationProfile> {

    private final FinanceTrackerMapping financeTrackerMapping;
    private final UserService userService;

    public ProfileAuthorizationService(UserService userService,
                                       FinanceTrackerMapping financeTrackerMapping) {
        this.userService = userService;
        this.financeTrackerMapping = financeTrackerMapping;
    }

    public void register(AuthorizationProfile profile) {
        User user = financeTrackerMapping.map(profile);
        userService.create(user);
    }

    public User authorize(AuthorizationProfile profile) {
        validateProfile(profile);
        User user = userService.getBy(profile.getEmail());
        validateUser(profile.getPassword(), user);
        return user;
    }

    private static void validateUser(String password, User user) {
        if (user == null || !password.equals(user.getPassword())) {
            throw new FinanceTrackerException("No such user or password!");
        }
    }

    private static void validateProfile(AuthorizationProfile profile) {
        if (profile == null
                || profile.getEmail() == null
                || profile.getPassword() == null) {
            throw new FinanceTrackerException("Not enough data!");
        }
    }
}
