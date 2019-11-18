package com.sbt.javaschool.rnd.Lesson5.Terminal.Server;

import com.sbt.javaschool.rnd.Lesson5.Terminal.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TerminalController {

    private Map<Integer, Integer> balance = new HashMap<>();

    @MessageMapping("/terminal")
    @SendTo("/terminal/messages")
    public Message getMessage(Message msg) {
        System.out.println(msg);

        Integer id = msg.getFrom();
        msg.setDirection(Message.Direction.server);
        if  (!checkId(msg))
            return msg;

        switch (msg.getCommand()) {
            case getBalance:
                msg.setMoney(balance.get(id));
                break;
            case getMoney:
                if (balance.get(id) < msg.getMoney()) {
                    balance.put(id, balance.get(id) - msg.getMoney());
                } else {
                    msg.setErrorString("Not enough money");
                }
                break;
            case putMoney:
                balance.put(id, balance.get(id) + msg.getMoney());
                break;
        }
        return msg;
    }

    private boolean checkId(Message msg) {
        if (balance.containsKey(msg.getFrom())) {
            msg.setErrorString("Don't have this account!");
            return false;
        }
        return true;
    }
}
