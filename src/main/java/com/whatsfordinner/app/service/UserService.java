package com.whatsfordinner.app.service;

import com.whatsfordinner.app.models.User;

public interface UserService {

    public void saveUser(User user);
    public boolean isUserAlreadyPresent(User user);
    public void changeUserPassword(User user, String password);
}

