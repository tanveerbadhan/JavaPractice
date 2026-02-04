package com.tanveer.journalApp.service;

import com.tanveer.journalApp.entity.JournalEntry;
import com.tanveer.journalApp.entity.User;
import com.tanveer.journalApp.repository.JournalEntryRepository;
import com.tanveer.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserRepository userRepository;

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public JournalEntry saveEntryByUserName(JournalEntry journalEntry, String userName){
        User user = userRepository.findByUserName(userName);
        if(user != null){
            List<JournalEntry> userJournalList = user.getJournalEntryList();
            journalEntry.setDate(LocalDateTime.now());
            journalEntryRepository.save(journalEntry);
            userJournalList.add(journalEntry);
            userRepository.save(user);
            log.info("User is saved");
            return journalEntry;
        }
        return null;
    }

    public JournalEntry updateEntryById(ObjectId id, JournalEntry updatedEntry) {
        JournalEntry oldEntry = findById(id).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(updatedEntry.getTitle() != null && !updatedEntry.getTitle().equals("") ? updatedEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(updatedEntry.getContent() != null && !updatedEntry.getContent().equals("") ? updatedEntry.getContent() : oldEntry.getContent());
            journalEntryRepository.save(oldEntry);
            return oldEntry;
        }
        return null;
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }

    @Transactional
    public void deleteJournalEntryByUserNameAndId(String userName, ObjectId id) {
        User user = userRepository.findByUserName(userName);
        boolean journalRemovedEntry = user.getJournalEntryList().removeIf(val -> val.getId().equals(id));
        if(journalRemovedEntry){
            journalEntryRepository.deleteById(id);
        }
        userRepository.save(user);
    }
}
