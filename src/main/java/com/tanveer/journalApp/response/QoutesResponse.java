package com.tanveer.journalApp.response;

import com.tanveer.journalApp.entity.Quote;
import lombok.Data;

import java.util.List;

@Data
public class QoutesResponse {
    private List<Quote> quotes;
    private int total;
}

