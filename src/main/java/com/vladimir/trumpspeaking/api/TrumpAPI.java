package com.vladimir.trumpspeaking.api;

import com.vladimir.trumpspeaking.api.models.Quote;
import com.vladimir.trumpspeaking.api.parser.ResponseToQuote;
import com.vladimir.trumpspeaking.api.tools.ProcessingStr;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Component
public class TrumpAPI {

    private final HttpClient client;
    public TrumpAPI() {
        client = HttpClient.newHttpClient();
    }

    public Quote getRandomQuote() throws IOException, InterruptedException {
        String urlForRequest = "https://api.whatdoestrumpthink.com/api/v1/quotes/random";
        return ResponseToQuote.processingRandomQuoteResponse(sendRequest(urlForRequest).body());
    }

    public Quote getRandomQuoteWithName(String name) throws IOException, InterruptedException {
        String urlForRequest = "https://api.whatdoestrumpthink.com/api/v1/quotes/personalized" +
                "?q="+ ProcessingStr.replaceSpace(name);

        return ResponseToQuote.processingRandomQuoteResponse(sendRequest(urlForRequest).body());
    }

    private HttpResponse<String> sendRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

}
