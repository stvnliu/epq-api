package me.imsonmia.epqapi.messaging;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.slf4j.Logger;

import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import me.imsonmia.epqapi.model.ChatConvert;
import me.imsonmia.epqapi.model.ChatMessage;
import me.imsonmia.epqapi.repository.ChatMessageRepository;

// https://www.baeldung.com/java-websockets
@ServerEndpoint(
    value = "/chat/{username}",
    encoders = MessageEncoder.class,
    decoders = MessageDecoder.class
    )
public class WebSocketHandler {
    private Logger logger;
    private ChatMessageRepository chatMessageRepository;
    private Session session;
    private Set<WebSocketHandler> chatEndpoints = new CopyOnWriteArraySet<>();
    private HashMap<String, String> users = new HashMap<>();
    @OnOpen
    public void onOpen(Session session, String username) throws IOException {
        this.session = session;
        chatEndpoints.add(this);
        users.put(session.getId(), username);
        Message message = new Message();
        message.setFrom(username);
        message.setContent("Connected, hello world!");
        broadcast(message);
        ChatMessage cmessage = new ChatConvert().fromMessage(message);
        chatMessageRepository.save(cmessage);
    }
    @OnMessage
    public void onMessage(Session session, Message message) throws IOException {
        message.setFrom(users.get(session.getId()));
        broadcast(message);
    }
    @OnClose
    public void onClose(Session session) throws IOException {
        chatEndpoints.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!");
        broadcast(message);
    }
    public void broadcast(Message message) {
        chatEndpoints.forEach(endpoint -> {
            try {
                endpoint.session.getBasicRemote().sendObject(message);
            }
            catch (IOException e) {
                logger.info("Error sending message! IOException");
                logger.error(null, e);
            }
            catch (EncodeException e) {
                logger.info("Error sending message! EncodeException");
            }  
        });
    }
}
