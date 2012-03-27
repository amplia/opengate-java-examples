package com.opengate.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;

import com.opengate.common.xml.Bool;
import com.opengate.common.xml.Header;
import com.opengate.common.xml.MessageType;
import com.opengate.common.xml.Ogmessage;
import com.opengate.common.xml.Payload;
import com.opengate.common.xml.Str;

public class JmsQueueSender {

    private JmsTemplate jmsTemplate;
    private Queue queue;

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void sendMessage() {
        Ogmessage ogmessage = new Ogmessage();
        ogmessage.setId(121212);
        ogmessage.setVersion(5);
        
        Header header = new Header();
        header.setName("opName");
        header.setUsername("default_client");
        header.setMessageType(MessageType.EVENT);

        ogmessage.setHeader(header);

        Payload payload = new Payload();
        Bool bool = new Bool();
        bool.setId(0);
        bool.setValue(true);
        payload.getBoolOrInt8OrInt16().add(bool);
        Str str = new Str();
        str.setId(1);
        str.setValue("Prueba");
        payload.getBoolOrInt8OrInt16().add(str);
        ogmessage.setPayload(payload);
        
        System.out.println("Ogmessage created: " + ogmessage);
        
        this.getJmsTemplate().convertAndSend(this.queue, ogmessage, new MessagePostProcessor() {
            
            public Message postProcessMessage(Message message) throws JMSException {
                message.setStringProperty("key", "default_application|default_channel|default_organization");
                return message;
            }
        });
        System.out.println("Message sent!");
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context;
        System.out.println("Creating application context...");
        context = new ClassPathXmlApplicationContext("com/opengate/jms/sender-receiver-jms.xml");
        System.out.println("Getting jms sender...");
        JmsQueueSender jmsSender = (JmsQueueSender) context.getBean("jmsQueueSender");
        try {
            System.out.println("Sending message...");
            jmsSender.sendMessage();
            System.out.println("Message sent!");
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        context.destroy();
    }

}
