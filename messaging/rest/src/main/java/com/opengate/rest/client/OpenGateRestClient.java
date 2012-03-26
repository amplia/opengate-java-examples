package com.opengate.rest.client;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.opengate.common.xml.Code;
import com.opengate.common.xml.Endpoint;
import com.opengate.common.xml.EndpointStatus;
import com.opengate.common.xml.Header;
import com.opengate.common.xml.Int32;
import com.opengate.common.xml.MessageStatus;
import com.opengate.common.xml.Ogmessage;
import com.opengate.common.xml.Payload;
import com.opengate.common.xml.StatusCode;
import com.opengate.common.xml.Str;

public class OpenGateRestClient {

    RestTemplate         restTemplate;

    private final long   id           = 1;

    private final String login        = "default_application";
    private final String channel      = "default_channel";
    private final String organization = "default_organization";
    private String       token        = null;
    private final String baseUrl      = "http://172.19.17.64:9090/opengate/rest/messages/";

    public void setRestTemplate(RestTemplate _restTemplate) {
        restTemplate = _restTemplate;
    }

    public void setUserEndpoint() {
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
            this.token = endpointStatus.getToken();
        }
        System.out.println("Token get: " + this.token);
    }

    public void sendEvent() {
        System.out.println("Sending Event...");
        if (!setToken()) {
            System.out.println("Error setting endpoint. Event can't be sent!");
        }
        Ogmessage ogmessage = createOgmessage();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Cookie", "token=" + this.token + "; domain=host; path=/");
        HttpEntity<Ogmessage> requestEntity = new HttpEntity<Ogmessage>(ogmessage, requestHeaders);

        ResponseEntity<MessageStatus> response = restTemplate.exchange(this.baseUrl + "events", HttpMethod.POST,
                requestEntity, MessageStatus.class);
        MessageStatus eventResponse = response.getBody();
        Code code = eventResponse.getStatusCode();
        System.out.println("Event response: " + code);
    }

    private boolean setToken() {
        boolean isTokenSet = true;
        if (this.token == null) {
            setUserEndpoint();
            if (this.token == null) {
                isTokenSet = false;
            }
        }
        return isTokenSet;
    }

    public void sendRequest() {
        System.out.println("Sending Request...");
        if (!setToken()) {
            System.out.println("Error setting endpoint. Request can't be sent!");
        }
        Ogmessage ogmessage = createOgmessage();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Cookie", "token=" + this.token + "; domain=host; path=/");
        HttpEntity<Ogmessage> requestEntity = new HttpEntity<Ogmessage>(ogmessage, requestHeaders);

        ResponseEntity<MessageStatus> response = restTemplate.exchange(this.baseUrl + "requests", HttpMethod.POST,
                requestEntity, MessageStatus.class);
        MessageStatus eventResponse = response.getBody();
        Code code = eventResponse.getStatusCode();
        System.out.println("Request response: " + code);
    }

    public void sendResponse(Long id) {
        System.out.println("Sending Response...");
        if (!setToken()) {
            System.out.println("Error setting endpoint. Response can't be sent!");
        }
        Ogmessage ogmessage = createOgmessage(id);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Cookie", "token=" + this.token + "; domain=host; path=/");
        HttpEntity<Ogmessage> requestEntity = new HttpEntity<Ogmessage>(ogmessage, requestHeaders);

        ResponseEntity<MessageStatus> response = restTemplate.exchange(this.baseUrl + "responses", HttpMethod.POST,
                requestEntity, MessageStatus.class);
        MessageStatus eventResponse = response.getBody();
        Code code = eventResponse.getStatusCode();
        System.out.println("Response response: " + code);
    }

    public void sendException(Long id) {
        System.out.println("Sending Exception...");
        if (!setToken()) {
            System.out.println("Error setting endpoint. Exception can't be sent!");
        }
        Ogmessage ogmessage = createOgmessage(id);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Cookie", "token=" + this.token + "; domain=host; path=/");
        HttpEntity<Ogmessage> requestEntity = new HttpEntity<Ogmessage>(ogmessage, requestHeaders);

        ResponseEntity<MessageStatus> response = restTemplate.exchange(this.baseUrl + "exceptions", HttpMethod.POST,
                requestEntity, MessageStatus.class);
        MessageStatus eventResponse = response.getBody();
        Code code = eventResponse.getStatusCode();
        System.out.println("Exception response: " + code);
    }

    public void fetchRESTObject() {
        setUserEndpoint();
        // sendEvent();
        // sendRequest();
        // sendResponse(this.id);
        // sendException(this.id);
    }

    private Ogmessage createOgmessage() {
        return createOgmessage(this.id);
    }

    private Ogmessage createOgmessage(Long id) {
        Ogmessage ogmessage = new Ogmessage();
        ogmessage.setId(id++);
        ogmessage.setVersion(5);
        Header header = new Header();
        header.setName("Test");
        header.setUsername("default_client");
        ogmessage.setHeader(header);

        Payload payload = new Payload();
        List<Object> list = payload.getBoolOrInt8OrInt16();
        Int32 int32 = new Int32();
        int32.setId(0);
        int32.setValue(id.intValue());
        list.add(int32);
        Str str = new Str();
        str.setId(1);
        str.setValue("Testing " + id);
        list.add(str);
        ogmessage.setPayload(payload);

        return ogmessage;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context;
        System.out.println("Creating application context...");
        context = new ClassPathXmlApplicationContext("listener-servlet.xml");
        System.out.println("Getting rest client...");
        OpenGateRestClient restClient = (OpenGateRestClient) context.getBean("restClient");
        restClient.fetchRESTObject();
    }

}
