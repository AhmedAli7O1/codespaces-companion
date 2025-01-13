package com.orbiktech.codespacescompanion;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GithubAuth {

    private static final String CLIENT_ID = "Ov23liqJpzS0RIDYpODD";
    private static final String SCOPE = "repo";

    public Map<String, String> requestDeviceCode() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://github.com/login/device/code"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("client_id="+ CLIENT_ID +"&scope=" + SCOPE))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(response.body(), Map.class);
    }
}
