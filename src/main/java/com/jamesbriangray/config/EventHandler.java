package com.jamesbriangray.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.jamesbriangray.config.WebSocketConfig.MESSAGE_PREFIX;

@Component
public class EventHandler {

    private final SimpMessagingTemplate websocket;

    @Autowired
    public EventHandler(SimpMessagingTemplate websocket) {
        this.websocket = websocket;
    }

    public void cacheUpdate(String apiCallType, List<?> payload) {
        System.out.print("Sending via socket: ");
        this.websocket.convertAndSend(
                MESSAGE_PREFIX + "/" + apiCallType, payload
        );
    }

}
