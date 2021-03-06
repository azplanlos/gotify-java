package net.gotify;

import lombok.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.RequestTemplate;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class GotifyClient {

    @Getter
    private GotifyService service;

    /**
     * create a new client for Gotify
     * @param url endpoint to connect to
     * @param token token for authetication, can be application token or user token
     */
    public GotifyClient(String url, String token) {
        ObjectMapper jacksonMapper = new ObjectMapper();
        jacksonMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        jacksonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jacksonMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        service = new Feign.Builder()
                .decoder(new JacksonDecoder(jacksonMapper))
                .encoder(new JacksonEncoder(jacksonMapper))
                .requestInterceptor((RequestTemplate template) -> {
                    template.header("Content-Type", "application/json");
                    template.header("X-Gotify-Key", token);
                })
                .target(GotifyService.class, url);
    }

    public int postMessage(Message message) {
        return this.service.postMessage(message).getId();
    }
}