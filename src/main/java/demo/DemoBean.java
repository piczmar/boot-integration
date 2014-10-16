package demo;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * @author Mark Fisher
 */
@MessageEndpoint
public class DemoBean {

    @ServiceActivator
    public String upperCase(String input) {
        return "JMS response: " + input.toUpperCase();
    }
}
