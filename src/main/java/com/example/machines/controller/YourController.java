package com.example.machines.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
public class YourController {
    private final WebClient.Builder webClientBuilder;

    public YourController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping("/fetch-data")
    @ResponseBody
    public Mono<String> fetchDataFromAnotherServer() {
        String url = "https://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer/reverseGeocode?f=pjson&langCode=EN&location=17.04776645,51.12683886"; // Replace with the actual URL
        Mono<String> response = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
        System.out.println(response);

        return response;
    }
}
