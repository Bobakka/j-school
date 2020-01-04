package com.sbt.javaschool.rnd.Terminal.Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
/*
@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {
    public void configureMessageBroker(MessageBrokerRegistry confi) {
        confi.enableSimpleBroker("/chat");
        confi.setApplicationDestinationPrefixes("/app");
    }

    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-messaging").withSockJS();

    }

}

@Controller
class ChatController {

    @MessageMapping("/message")
    @SendTo("/chat/messages")
    public Message getMessages(Message message) {
        System.out.println(message);
        return message;
    }
}

lass Message {

    private String from;
    private String message;
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "Message [from=" + from + ", message=" + message + "]";
    }

}
*/

@SpringBootApplication
public class Server {

    public static void main(String[] args) {
//        SpringApplication.run(Server.class, args);
        SpringApplication app = new SpringApplication(Server.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "9090"));
        app.run(args);
    }

}
