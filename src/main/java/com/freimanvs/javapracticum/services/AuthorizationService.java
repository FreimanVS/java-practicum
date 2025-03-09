package com.freimanvs.javapracticum.services;

import com.freimanvs.javapracticum.dto.AuthorizationProfile;
import com.freimanvs.javapracticum.dto.User;

public interface AuthorizationService<T> {
    void register(T profile);

    User authorize(T profile);
}
