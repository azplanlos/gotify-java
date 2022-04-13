package net.gotify;

public interface GotifyService {

    @RequestLine("POST /message")
    Message postMessage(Message message);
}