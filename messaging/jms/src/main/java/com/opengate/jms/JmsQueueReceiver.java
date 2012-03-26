package com.opengate.jms;

import java.util.List;

import javax.jms.Queue;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.opengate.common.xml.Header;
import com.opengate.common.xml.Ogmessage;

public class JmsQueueReceiver {

    private JmsTemplate jmsTemplate;
    private Queue queue;

    public void setQueue(Queue queue) {
        this.queue = queue;
    }
    
    public void receiveMessages() {
        try {
          System.out.println("Getting Messages for example...");
          int i = 1;
          
          Ogmessage ogmessage;
          do {
            System.out.println("Obtaining Message " + i + "....");
            i++;
            System.out.println(jmsTemplate.getMessageConverter());
            ogmessage = (Ogmessage) getJmsTemplate().receiveAndConvert(this.queue);
            
            if (ogmessage != null) {
              try {
                System.out.println("Ogmessage received: " + ogmessage);
                Header header = ogmessage.getHeader();
                System.out.println("\tType:" + header.getMessageType().name());
                System.out.println("\tFrom:" + header.getUsername());
                System.out.println("\tOperation Name:" + header.getName());
                System.out.println("\tData:");
                List<Object> list = ogmessage.getPayload().getBoolOrInt8OrInt16();
                for (Object object : list) {
                    System.out.println("\t\t" + object.toString());
                }
              } catch (Exception e) {
                  e.printStackTrace();
              }
            }
          } while (ogmessage != null);
          
          System.out.println("There are no more messages...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
    
    public static void main(String[] args) {
        System.out.println("Creating application context...");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/opengate/jms/sender-receiver-jms.xml");

        System.out.println("Getting jms receiver...");
        JmsQueueReceiver jmsReceiver = (JmsQueueReceiver) context.getBean("jmsQueueReceiver");
        System.out.println("Receiving messages...");
        jmsReceiver.receiveMessages();
    }
    
}
