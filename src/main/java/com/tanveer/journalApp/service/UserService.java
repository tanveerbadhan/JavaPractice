package com.tanveer.journalApp.service;

import com.tanveer.journalApp.entity.JournalEntry;
import com.tanveer.journalApp.entity.User;
import com.tanveer.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void saveEntry(User user){
        userRepository.save(user);
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public void deleteJournalEntryByUserNameAndId(String userName, ObjectId id) {
        User user = userRepository.findByUserName(userName);
        user.getJournalEntryList().removeIf(val -> val.getId().equals(id));
        userRepository.save(user);
    }
}
