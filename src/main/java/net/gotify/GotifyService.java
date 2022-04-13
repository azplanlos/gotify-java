package net.gotify;

import feign.RequestLine;

public interface GotifyService {

    @RequestLine("POST /message")
    Message postMessage(Message message);
}