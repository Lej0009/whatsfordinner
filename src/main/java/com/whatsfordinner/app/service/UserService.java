package com.whatsfordinner.app.service;

import com.whatsfordinner.app.models.User;

import java.util.Optional;

public interface UserService {

    public void saveUser(User user);
    public boolean isUserAlreadyPresent(User user);
    public Optional<User> findUserByEmail(String email);
    public Optional<User> findUserByResetToken(String resetToken);
    public void save(User user);
}

