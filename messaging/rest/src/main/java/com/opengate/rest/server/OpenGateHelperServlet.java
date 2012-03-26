package com.opengate.rest.server;

import com.opengate.common.xml.*;
import com.opengate.rest.OpenGateDataHolder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class OpenGateHelperServlet extends HttpServlet {

    private static final long serialVersionUID = 4657105465218304929L;
    private WebApplicationContext applicationContext = null;
    private RestTemplate restTemplate = null;
    private OpenGateDataHolder holder = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        restTemplate = applicationContext.getBean("restTemplate", RestTemplate.class);
        holder = applicationContext.getBean("dataHolder", OpenGateDataHolder.class);
    }

    @Override
    public void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException,
            IOException {

        PrintWriter writer = _response.getWriter();

        String action = _request.getParameter("action");
        if (action == null || action.equals("")) {
            writer.println("Application context got " + applicationContext);
            writer.println("RestTemplate got " + restTemplate);

        } else if (action.equals("endpoint")) {

            writer.println("Set Endpoint report");
            Endpoint endpoint = new Endpoint();
            endpoint.setLogin(holder.getLogin());
            endpoint.setPassword(holder.getPassword());
            endpoint.setChannel(holder.getChannel());
            endpoint.setOrganization(holder.getOrganization());
            endpoint.setUrl(holder.getListenerUrl());
            String endpointUrl = holder.getBaseUrl() + "/endpoints";
            EndpointStatus endpointStatus = restTemplate.postForObject(endpointUrl, endpoint, EndpointStatus.class);
            StatusCode statusCode = endpointStatus.getStatusCode();
            writer.println("Endpoint status response: " + statusCode);
            if (statusCode.equals(StatusCode.OK)) {
                holder.setToken(endpointStatus.getToken());
            }
            writer.println("Token got: " + holder.getToken());

        } else if (action.equals("sendmessage")) {

            String messageType= _request.getParameter("type");

            writer.println("Send Message report");

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
            requestHeaders.set("Cookie", "token=" + holder.getToken() + "; domain=host; path=/");
            HttpEntity<Ogmessage> requestEntity = new HttpEntity<Ogmessage>(ogmessage, requestHeaders);

            String messageUrl= holder.getBaseUrl()+"/messages/"+messageType;
            ResponseEntity<MessageStatus> response = restTemplate.exchange(messageUrl, HttpMethod.POST,
                    requestEntity, MessageStatus.class);
            MessageStatus messageStatus = response.getBody();
            Code code = messageStatus.getStatusCode();
            writer.println("Event response: " + code);
        }

        writer.flush();

    }
}
