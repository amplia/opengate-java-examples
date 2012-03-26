package com.opengate.jms;

import java.io.IOException;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opengate.common.xml.Header;
import com.opengate.common.xml.Ogmessage;

public class JmsQueueListener {

    public void receive(Ogmessage ogmessage) {
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
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("Creating application context...");
        new ClassPathXmlApplicationContext("com/opengate/jms/listener-jms.xml");
        
        System.in.read();
    }
}
