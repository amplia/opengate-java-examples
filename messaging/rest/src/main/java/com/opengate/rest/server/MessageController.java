package com.opengate.rest.server;

import com.opengate.common.xml.*;
import com.opengate.rest.OpenGateDataHolder;
import java.lang.reflect.Method;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageController {

	private RestTemplate restTemplate;
	private OpenGateDataHolder holder;

	@RequestMapping(method = RequestMethod.POST, value = "/{messageType}")
	public ModelAndView handleMessage(@PathVariable String messageType,
			@RequestBody Ogmessage ogmessage) {

		System.out.println("Ogmessage received: " + ogmessage + " with type: "
				+ messageType);
		Header header = ogmessage.getHeader();
		System.out.println("\tID:" + ogmessage.getId());
		System.out.println("\tType:" + messageType);
		System.out.println("\tFrom:" + header.getUsername());
		System.out.println("\tOperation Name:" + header.getName());
		System.out.println("\tData:");
		List<Object> list = ogmessage.getPayload().getBoolOrInt8OrInt16();
		for (Object object : list) {
			try {
				Class clazz = object.getClass();
				Method method = null;
				if (object instanceof Bool) {
					method = clazz.getMethod("isValue", new Class[] {});
				} else {
					method = clazz.getMethod("getValue", new Class[] {});
				}
				System.out.println("\t\t"
						+ method.invoke(object, new Object[] {}));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (messageType.equals("requests")) {
			System.out.println("Request received. Sending response with id: "
					+ ogmessage.getId());
			System.out.println("Sending Response...");

			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("Cookie", "token=" + holder.getToken()
					+ "; domain=host; path=/");
			HttpEntity<Ogmessage> requestEntity = new HttpEntity<Ogmessage>(
					ogmessage, requestHeaders);

			String responseUrl = holder.getBaseUrl() + "/messages/responses";
			ResponseEntity<MessageStatus> response = restTemplate.exchange(
					responseUrl, HttpMethod.POST, requestEntity,
					MessageStatus.class);
			MessageStatus eventResponse = response.getBody();
			Code code = eventResponse.getStatusCode();
			System.out.println("Response response: " + code);
		}

		ModelAndView modelAndView = new ModelAndView("messagesView");
		DeliveryStatus deliveryStatus = new DeliveryStatus();
		deliveryStatus.setDeliveryCode(DeliveryCode.OK);
		modelAndView.addObject("deliveryStatus", deliveryStatus);
		return modelAndView;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public void setDataHolder(OpenGateDataHolder holder) {
		this.holder = holder;
	}
}