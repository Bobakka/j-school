package com.sbt.javaschool.rnd.Terminal.Client;

import com.sbt.javaschool.rnd.Terminal.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/terminal/messages", this);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        System.out.println(String.format("Got an exception %s", exception) );
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Message.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Message msg = (Message) payload;
        if (!msg.getErrorString().isEmpty()) {
            System.out.println(msg.getErrorString());
            return;
        }

        switch (msg.getCommand()) {
            case getBalance:
                System.out.println(String.format("Your balance is %d", msg.getMoney()) );
                break;
            case getMoney:
                System.out.println("Please, take money");
            case putMoney:
                System.out.println(String.format("You add %d money", msg.getMoney()));
        }
    }

}
