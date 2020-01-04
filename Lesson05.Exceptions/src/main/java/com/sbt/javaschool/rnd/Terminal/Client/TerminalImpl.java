package com.sbt.javaschool.rnd.Terminal.Client;

import com.sbt.javaschool.rnd.Terminal.Card;
import com.sbt.javaschool.rnd.Terminal.Message;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import javax.management.timer.Timer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TerminalImpl implements Terminal {

    private WebSocketStompClient stompClient;
    private StompSessionHandler sessionHandler;
    private StompSession stompSession;
    private Card card;
    private Timer time = new Timer();

    public void connect(String url) {
        try {
            stompSession = stompClient.connect(url, sessionHandler).get();
        }
        catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    public TerminalImpl() {
        WebSocketClient socket = new StandardWebSocketClient();
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(socket));
        SockJsClient sockJsClient = new SockJsClient(transports);
        stompClient = new WebSocketStompClient(sockJsClient);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        sessionHandler = new MyStompSessionHandler();
    }
    @Override
    public void putCard(Card card) throws NullPointerException, IllegalArgumentException {
            this.card = card;
    }

    @Override
    public boolean checkPin(Integer pin) throws NullPointerException {
        if (pin == null) {
            throw new NullPointerException();
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pin.byteValue());
            return card.getPing() == md.digest();
        }
        catch (NoSuchAlgorithmException ex) {
            System.out.println("No such algorithm MD5!");
            return false;
        }
    }

    @Override
    public void takeMoney(Integer sum) {
        Message msg = new Message();
        msg.setFrom(card.getId());
        msg.setDirection(Message.Direction.terminal);
        msg.setCommand(Message.Cmd.getMoney);
        msg.setMoney(sum);

        stompSession.send("/terminal/messages", msg);
    }

    @Override
    public void appendMoney(Integer sum) {
        Message msg = new Message();
        msg.setFrom(card.getId());
        msg.setDirection(Message.Direction.terminal);
        msg.setCommand(Message.Cmd.putMoney);
        msg.setMoney(sum);

        stompSession.send("/terminal/messages", msg);
    }

    @Override
    public void balance() {
        Message msg = new Message();
        msg.setFrom(card.getId());
        msg.setDirection(Message.Direction.terminal);
        msg.setCommand(Message.Cmd.getBalance);

        stompSession.send("/terminal/messages", msg);
    }
}
