package demo;

import java.util.HashMap;

public class MessageHandler {
    public HashMap onMessage(HashMap message) {
        System.out.println("In onMessage");
        System.out.println(message);

        HashMap payload = message;

        return payload;
    }
}