package me.imsonmia.epqapi.messaging;

import com.google.gson.Gson;

import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {
    private static Gson gson = new Gson();
    @Override
    public String encode(Message message) throws EncodeException {
        return gson.toJson(message);
    }
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        Text.super.destroy();
    }
    @Override
    public void init(EndpointConfig endpointConfig) {
        // TODO Auto-generated method stub
        Text.super.init(endpointConfig);
    }
}