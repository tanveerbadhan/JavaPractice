package com.tanveer.journalApp.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@Builder
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @DBRef
    private List<JournalEntry> journalEntryList = new ArrayList<>();
    private List<String> roles;

    public User() {
    }

    public User(@NonNull String userName, @NonNull String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(ObjectId id, @NonNull String userName, @NonNull String password,
                List<JournalEntry> journalEntryList, List<String> roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.journalEntryList = journalEntryList != null ? journalEntryList : new ArrayList<>();
        this.roles = roles;
    }
}
