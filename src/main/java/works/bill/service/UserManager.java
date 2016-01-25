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
import works.bill.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

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
        return userRepository.save(user);
    }
}
