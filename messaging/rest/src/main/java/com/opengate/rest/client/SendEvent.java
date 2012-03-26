package com.opengate.rest.client;

import com.opengate.common.xml.*;
import java.util.List;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SendEvent {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context;
        System.out.println("Creating application context...");
        context = new ClassPathXmlApplicationContext("clientApplicationContext.xml");
        System.out.println("Getting rest template...");
        RestTemplate restTemplate = context.getBean("restTemplate", RestTemplate.class);

        String login = "default_application";
        String channel = "default_channel";
        String organization = "default_organization";
        String token = null;
        String baseUrl = "http://172.19.17.64:9090/opengate/rest/messages/";

        System.out.println("Setting Endpoint...");
        String listenerUrl = "http://172.19.17.64:8080/og-msg-rest-examples";
        String endpointUrl = "http://172.19.17.64:9090/opengate/rest/endpoints";
        String password = "default";

        Endpoint endpoint = new Endpoint();
        endpoint.setLogin(login);
        endpoint.setChannel(channel);
        endpoint.setOrganization(organization);
        endpoint.setPassword(password);
        endpoint.setUrl(listenerUrl);

        EndpointStatus endpointStatus = restTemplate.postForObject(endpointUrl, endpoint, EndpointStatus.class);
        StatusCode statusCode = endpointStatus.getStatusCode();
        System.out.println("Endpoint status response: " + statusCode);
        if (statusCode.equals(StatusCode.OK)) {
            token = endpointStatus.getToken();
        }
        System.out.println("Token get: " + token);

        System.out.println("Sending Event...");

        Ogmessage ogmessage = new Ogmessage();
        ogmessage.setId(System.currentTimeMillis());
        ogmessage.setVersion(5);
        Header header = new Header();
        header.setName("Test");
        header.setUsername("default_client");
        ogmessage.setHeader(header);

        Payload payload = new Payload();
        List<Object> list = payload.getBoolOrInt8OrInt16();
        Int32 int32 = new Int32();
        int32.setId(0);
        int32.setValue(2006);
        list.add(int32);
        Str str = new Str();
        str.setId(1);
        str.setValue("Testing");
        list.add(str);
        ogmessage.setPayload(payload);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Cookie", "token=" + token + "; domain=host; path=/");
        HttpEntity<Ogmessage> requestEntity = new HttpEntity<Ogmessage>(ogmessage, requestHeaders);

        ResponseEntity<MessageStatus> response = restTemplate.exchange(baseUrl + "events", HttpMethod.POST,
                requestEntity, MessageStatus.class);
        MessageStatus eventResponse = response.getBody();
        Code code = eventResponse.getStatusCode();
        System.out.println("Event response: " + code);

    }
}
