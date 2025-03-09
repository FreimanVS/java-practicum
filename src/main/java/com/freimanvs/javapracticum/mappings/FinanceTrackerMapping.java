package com.freimanvs.javapracticum.mappings;

import com.freimanvs.javapracticum.dto.AuthorizationProfile;
import com.freimanvs.javapracticum.dto.User;

public class FinanceTrackerMapping {
    public User map(AuthorizationProfile profile) {
        return new User()
                .setName(profile.getName())
                .setEmail(profile.getEmail())
                .setPassword(profile.getPassword());
    }
    public AuthorizationProfile map(User user) {
        return new AuthorizationProfile()
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword());
    }
}
