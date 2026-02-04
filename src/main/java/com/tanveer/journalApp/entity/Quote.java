package com.tanveer.journalApp.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Quote {
    private String id;
    private String text;
    private String author;
}