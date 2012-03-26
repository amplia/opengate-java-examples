package com.opengate.session;

import es.amplia.opengate.common.OGConstants;
import es.amplia.opengate.common.ogmessage.OGMessage;
import es.amplia.opengate.session.OpenGateSession;
import es.amplia.opengate.session.OpenGateSessionAdapter;
import es.amplia.opengate.session.OpenGateSessionException;

/**
 * <p>
 * <b>OpenGate M2M/Mobility Communications Platform</b>
 * </p>
 * <p>
 * <i>Wireless your bussiness</i>
 * </p>
 * <p>
 * <b>Description:</b><br/> Description
 * </p>
 * <p>
 * If you want more info about this example please send an email to <a
 * href="mailto:support@amplia.es">Amplia Support</a>
 * <p>
 * Copyright 2007 Amplia Soluciones S.L. All rights reserved.
 * 
 * @author Amplia Soluciones S.L
 */
public class SendAsyncCommand extends OpenGateSessionAdapter {

	public void handleResponse(long _transactionId, int _errorCode,
			String _errorDescription, OpenGateSession _session,
			OGMessage _message) {
		System.out.println("Response received");
	}

	public static void main(String[] args) {

		OpenGateSession session = new OpenGateSession(OGConstants.ROLE_CLIENT);
		/*
		 * In this example you can see connection method by setting previously
		 * the connection properties.
		 */
		session.setIpAddress("172.19.17.63");
		session.setPort(22500);
		session.setUserId("default_client");
		session.setPassword("default");
		session.setChannelId("default_channel");
		session.setOrganization("default_organization");
		session.setDefaultDestinationId("default_application");
		session.setTimeout(10000);

		SessionListener listener = new SessionListener();
		session.setListener(listener);

		try {
			// Connecting to OpenGate Platform
			session.connect();

			// Composing an OpenGate Message
			OGMessage message = new OGMessage();
			message.setOperationName("EXAMPLE_ASYNC_COMMAND");
			message.addInt(12);// int data
			message.addString("Example Message");// String data
			message.addBoolean(true);// boolean data

			session.sendAsyncCommand(message);
		} catch (OpenGateSessionException e) {
			e.printStackTrace();
		}

	}

}
