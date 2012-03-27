package com.opengate.session;

import java.util.Properties;

import es.amplia.opengate.common.OGConstants;
import es.amplia.opengate.common.ogmessage.OGHeader;
import es.amplia.opengate.common.ogmessage.OGMessage;
import es.amplia.opengate.session.OpenGateSession;
import es.amplia.opengate.session.OpenGateSessionException;

/**
 * <p>
 * <b>OpenGate M2M/Mobility Communications Platform</b>
 * </p>
 * <p>
 * <i>Wireless your bussiness</i>
 * </p>
 * <p>
 * <b>Description:</b><br/>
 * Description
 * </p>
 * <p>
 * If you want more info about this example please send an email to <a
 * href="mailto:support@amplia.es">Amplia Support</a>
 * <p>
 * Copyright 2007 Amplia Soluciones S.L. All rights reserved.
 * 
 * @author Amplia Soluciones S.L
 */
public class SendEvent {

	public static void main(String[] args) {

		// ---------------------------------------------------------------------
		// In this example you can see connection method using
		// a Properties object containing connection properties.
		Properties properties = new Properties();
		properties.setProperty("opengate.host", "172.19.17.62");
		properties.setProperty("opengate.port", "22500");
		properties.setProperty("opengate.login", "default_client");
		properties.setProperty("opengate.password", "default");
		properties.setProperty("opengate.channel", "default_channel");
		properties.setProperty("opengate.organization", "default_organization");
		properties.setProperty("opengate.default", "default_application");
		properties.setProperty("opengate.timeout", "10000");
		properties.setProperty("opengate.role", "client");

		// ---------------------------------------------------------------------
		// Session object creation
		OpenGateSession session = new OpenGateSession();

		// ---------------------------------------------------------------------
		// Session listener setup
		SessionListener listener = new SessionListener();
		session.setListener(listener);

		try {
			// -----------------------------------------------------------------
			// Connection stablishment
			session.connect(properties);

			// ---------------------------------------------------------------------
			// Message creation
			OGMessage message = new OGMessage();
			OGHeader header = message.getHeader();
			// ---------------------------------------------------------------------
			// Notification levels setup
			byte notificationMask = OGHeader.NOTIFICATION_PLATFORM_ARRIVAL
					| OGHeader.NOTIFICATION_PLATFORM_DEPARTURE
					| OGHeader.NOTIFICATION_DESTINATION_ARRIVAL
					| OGHeader.NOTIFICATION_IF_ERROR;
			header.setNotificationLevel((byte) 0);
//			header.setTransport(OGConstants.TRANSPORT_JMS);
			message.setOperationName("EXAMPLE_EVENT");
			message.addInt(12);
			message.addString("Example Message");
			message.addBoolean(true);

			// ---------------------------------------------------------------------
			// Event sending
			long messageId;
			// to default destination
			messageId = session.sendEvent(message);
			System.out.println("Event sent with messageId: " + messageId);

			try {
				System.out.println("Wait to ensure message reception...");
				Thread.sleep(3000);// Wait to ensure message reception
				System.out.println("...end wait");
			} catch (InterruptedException e) {
			}

			// ---------------------------------------------------------------------
			// Session disconnection
			session.disconnect();

		} catch (OpenGateSessionException e) {
			int errorCode = e.getErrorCode();
			System.out.println("Error sending event:" + errorCode);
			e.printStackTrace();
		}// try-catch
	}// main
}// class
