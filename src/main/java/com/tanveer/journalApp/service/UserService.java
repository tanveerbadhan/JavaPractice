package com.tanveer.journalApp.service;

import com.tanveer.journalApp.entity.JournalEntry;
import com.tanveer.journalApp.entity.User;
import com.tanveer.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void saveEntry(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public void saveAdminEntry(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public User updateUserByUserName(String userName, User user){
        User userInDb = findByUserName(userName);
        if(userInDb != null){
            userInDb.setUserName(user.getUserName() != null && !user.getUserName().equals("") ? user.getUserName() : userInDb.getUserName());
            userInDb.setPassword(user.getPassword() != null && !user.getPassword().equals("") ? user.getPassword() : userInDb.getPassword());
            saveEntry(userInDb);
            return user;
        }
        return null;
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
}
