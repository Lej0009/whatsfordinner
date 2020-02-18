package com.whatsfordinner.app.dao;

import com.whatsfordinner.app.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {

    User findByEmail(String email);

    User findByUserId(Integer userId);

    User findByPassword(String password);

    User findByResetToken(String resetToken);
}
