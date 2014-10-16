package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class JmsSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(JmsSender.class);

    protected JmsTemplate jmsTemplate;
    protected Destination destination;

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    /**
     * Send message to JMS queue destination
     *
     * @param msg a message to send
     * @throws JMSException
     */
    public void send(final Message msg) throws JmsException {
        // Send a message
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public MapMessage createMessage(Session session) throws JMSException {
                MapMessage m = session.createMapMessage();
                try {
                    fillMessage(m,msg);
                } catch (Exception e) {
                   LOGGER.error("Cannot create message", e);
                }
                return m;
            }
        };

        System.out.println("Sending a new message.");
        jmsTemplate.send(destination, messageCreator);
    }

    public MapMessage fillMessage(javax.jms.Message theMessage, Message requestMessage)
            throws Exception {
        try {
            MapMessage aMapMessage = (MapMessage) theMessage;

            aMapMessage.setStringProperty("reference", "ref..");
            aMapMessage.setIntProperty("type", 0);
            aMapMessage.setStringProperty("sender", "sender...");
            aMapMessage.setStringProperty("receiver", "receiver..");
            if(requestMessage.getSelector() != null)
                aMapMessage.setStringProperty("selector", requestMessage.getSelector());
            aMapMessage.setIntProperty("channelId", 1);

            if(requestMessage.getText()!= null)
                aMapMessage.setString("message", requestMessage.getText());

            aMapMessage.setString("gwParameters", "GW params..");
            aMapMessage.setLong("validityTime", 36000);
            aMapMessage.setInt("prefixStart", 48);
            aMapMessage.setInt("prefixLength", 2);
            aMapMessage.setString("status", "OK");
            return aMapMessage;
        } catch (ClassCastException e) {
            LOGGER.error("Cannot handle this message type", e);
            throw e;
        } catch (JMSException e) {
            LOGGER.error("Error adding data to JMS message", e);
            throw e;
        }
    }

    protected String prepareString(String theString) {
        if (theString == null) {
            return "";
        }
        return theString;
    }

}
