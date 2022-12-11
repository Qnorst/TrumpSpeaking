package com.vladimir.trumpspeaking.api.parser;

import com.google.gson.*;
import com.vladimir.trumpspeaking.api.models.Quote;
import com.vladimir.trumpspeaking.api.tools.ProcessingStr;

public class ResponseToQuote {
    public static Quote processingRandomQuoteResponse(String JSON){
        Gson gson = new GsonBuilder().create();
        Quote quote = gson.fromJson(JSON, Quote.class);
        quote.setTitle(ProcessingStr.processingTitle(quote.getText()));

        return quote;
    }
}
