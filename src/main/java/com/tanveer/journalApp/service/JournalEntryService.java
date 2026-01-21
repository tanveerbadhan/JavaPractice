package com.tanveer.journalApp.service;

import com.tanveer.journalApp.entity.JournalEntry;
import com.tanveer.journalApp.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry myEntry){
        journalEntryRepository.save(myEntry);
    }
}
