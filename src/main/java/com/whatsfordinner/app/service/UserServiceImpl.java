package com.whatsfordinner.app.service;

import com.whatsfordinner.app.dao.RoleDao;
import com.whatsfordinner.app.dao.UserDao;
import com.whatsfordinner.app.models.Role;
import com.whatsfordinner.app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    RoleDao roleDao;

    @Autowired
    UserDao userDao;

    @Override
    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus("VERIFIED");
        Role userRole = roleDao.findByRole("APP_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userDao.save(user);
    }

    @Override
    public boolean isUserAlreadyPresent(User user) {
        boolean isUserAlreadyExists = false;
        User existingUser = userDao.findByEmail(user.getEmail());
        // If user is found in database, then the user already exists.
        if(existingUser != null){
            isUserAlreadyExists = true;
        }
        return isUserAlreadyExists;
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findUserByResetToken(String resetToken) {
        return userDao.findByResetToken(resetToken);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

}

