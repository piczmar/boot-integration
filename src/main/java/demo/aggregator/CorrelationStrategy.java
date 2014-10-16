package demo.aggregator;

import org.springframework.messaging.Message;

import java.util.HashMap;

public class CorrelationStrategy {
    public Object getCorrelationKey(Message<HashMap> message){
        System.out.println("Correlate payload: " + message.getPayload());
        System.out.println("Correlate header: " + message.getHeaders().get("selector"));
        return message.getHeaders().get("selector") ;
    }
}
