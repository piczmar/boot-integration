package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SendController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendController.class);

    @Autowired
    @Qualifier(value = "healthJmsSender")
    private JmsSender healthJmsSender;

    @RequestMapping(value = "/send",
            method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json")
    public @ResponseBody Message addMessageToQueue(@RequestBody Message testSms) {
        LOGGER.info("Sending message");

        try{
        healthJmsSender.send(testSms);
        }catch(JmsException exc){
            LOGGER.error("Sending failed",exc);
        }
        LOGGER.info("Message sent");
        return testSms;
    }

}
