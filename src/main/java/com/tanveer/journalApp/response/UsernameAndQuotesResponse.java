package com.tanveer.journalApp.response;

import com.tanveer.journalApp.entity.Quote;
import lombok.Data;

import java.util.List;

@Data
public class UsernameAndQuotesResponse {
    private String userName;
    private QoutesResponse quotes;

    public UsernameAndQuotesResponse(String username, QoutesResponse quotes) {
        this.userName = username;
        this.quotes = quotes;
    }
}
