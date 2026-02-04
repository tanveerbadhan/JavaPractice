package com.tanveer.journalApp.controller;

import com.tanveer.journalApp.entity.User;
import com.tanveer.journalApp.repository.UserRepository;
import com.tanveer.journalApp.response.UsernameAndQuotesResponse;
import com.tanveer.journalApp.service.QoutesService;
import com.tanveer.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QoutesService qoutesService;

    @GetMapping
    public ResponseEntity<User> getUserByUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        try {
            if(user != null){
                return new ResponseEntity<>(userService.findByUserName(username), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/qoutes")
    public ResponseEntity<UsernameAndQuotesResponse> getUserNameAndQoute() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        UsernameAndQuotesResponse usernameAndQuotes = new UsernameAndQuotesResponse(username, qoutesService.getQuotes());
        try {
            if(user != null){
                return new ResponseEntity<>(usernameAndQuotes, HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody User user){
//        try {
//            userService.saveEntry(user);
//            return new ResponseEntity<>(user, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//    }

    @PutMapping
    public ResponseEntity<User> updateUserById(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            User updatedUser = userService.updateUserByUserName(username, user);
            if(updatedUser != null){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteByUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if(userRepository.findByUserName(username) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            userRepository.deleteByUserName(authentication.getName());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
