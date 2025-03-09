package com.freimanvs.javapracticum.dto;

import java.util.Objects;

public class AuthorizationProfile {
    private String name;
    private String email;
    private String password;

    public AuthorizationProfile() {
    }

    public String getName() {
        return name;
    }

    public AuthorizationProfile setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AuthorizationProfile setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AuthorizationProfile setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizationProfile that = (AuthorizationProfile) o;
        return Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password);
    }

    @Override
    public String toString() {
        return "AuthorizationProfile{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
