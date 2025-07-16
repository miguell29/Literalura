package com.myapp.literalura.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.literalura.models.GutendexResponse;

@Service
public class ApiService {

    private static final String BASE_URL = "https://gutendex.com/books/";
    private static final ObjectMapper objectMapper = new ObjectMapper();



    public static GutendexResponse searchBooks(String query) {
        String urlString = UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("search", query)
                .toUriString();

                System.out.println(urlString);
        return getData(urlString, GutendexResponse.class);
    }

    private static <T> T getData(String url, Class<T> clase) {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "Java HttpClient") // <-- AGREGADO
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response: " + response.body());
            return objectMapper.readValue(response.body(),clase);
        } catch (IOException e) {
            throw new RuntimeException("error: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException("error: " + e.getMessage());
        }
    }
}
