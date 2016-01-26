/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import works.bill.entities.User;
import works.bill.entities.dto.UserRegistration;
import works.bill.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author bill
 */
@Service
public class UserManager {
    
    @Autowired
    UserRepository userRepository;
    
    public List<User> findAll() {
        return new ArrayList<>(userRepository.findAll());
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setActivated(true);
        return userRepository.save(user);
    }

    public User createUser(UserRegistration userRegistration) {
        User user = new User();
        user.setUsername(userRegistration.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userRegistration.getPassword()));
        user.setActivationHash(UUID.randomUUID().toString());
        user.setActivated(false);
        user = userRepository.save(user);
        return user;
    }

    public UserRegistration createUserRegistration() {
        return new UserRegistration();
    }

    public boolean isUsernameInUse(String username) {
        return findByUsername(username) != null;
    }

    public boolean activateUser(String username, String activationHash) {
        if (username == null || activationHash == null) {
            return false;
        }
        User user = userRepository.findByUsername(username);
        if (user == null || user.getActivated()) {
            return false;
        }
        if (user.getActivationHash().equals(activationHash)) {
            user.setActivated(true);
            user.setActivationHash(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
