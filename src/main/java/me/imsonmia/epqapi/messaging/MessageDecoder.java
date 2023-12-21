package me.imsonmia.epqapi.messaging;

import com.google.gson.Gson;

import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message> {
    private static Gson gson = new Gson();
    @Override
    public Message decode(String message) throws DecodeException {
        return gson.fromJson(message, Message.class);
    }
    @Override
    public void destroy() {
        Text.super.destroy();
    }
    @Override
    public void init(EndpointConfig endpointConfig) {
        Text.super.init(endpointConfig);
    }
    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }
}