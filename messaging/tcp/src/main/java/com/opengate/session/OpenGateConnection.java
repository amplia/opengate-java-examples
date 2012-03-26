package com.opengate.session;

import java.util.Properties;

import es.amplia.opengate.common.OGConstants;
import es.amplia.opengate.session.OpenGateSession;
import es.amplia.opengate.session.OpenGateSessionException;

public class OpenGateConnection {

	public static void main(String[] args) {

		OpenGateSession session;
		long timeBeforeDisconnect = 15000;

		try {
			// =================================================================
			// First connection method: Parameter centric
			// =================================================================
			session = new OpenGateSession(OGConstants.ROLE_CLIENT);

			String openGateIPAddress = "172.19.17.59";
			int opengatePort = 22500;
			String login = "default_client";
			String password = "default";
			String channel = "default_channel";
			String organization = "default_organization";
			String defaultDestination = "default_application";
			int timeout = 10000;
			boolean secure = false;

			System.out.println("Trying to connect...");
			// In this example you can see connection
			// by passing connection parameters.
			// -----------------------------------------------------------------
			session.connect(openGateIPAddress, opengatePort, login, password,
					channel, organization, defaultDestination, timeout, secure);
			// -----------------------------------------------------------------
			System.out.println("...connected:" + session.isConnected());

			try {
				Thread.sleep(timeBeforeDisconnect);
			} catch (InterruptedException e) {
			}
			session.disconnect();
			System.out.println("...disconnected");

			// =================================================================
			// Second connection method: Properties centric
			// =================================================================
			session = new OpenGateSession();// By default is ROLE_CLIENT

			// In this example you can see connection method using
			// a Properties object containing connection properties.
			Properties properties = new Properties();
			properties.setProperty("opengate.host", openGateIPAddress);
			properties.setProperty("opengate.port", "" + opengatePort);
			properties.setProperty("opengate.login", login);
			properties.setProperty("opengate.password", password);
			properties.setProperty("opengate.channel", channel);
			properties.setProperty("opengate.organization", organization);
			properties.setProperty("opengate.default", defaultDestination);
			properties.setProperty("opengate.timeout", "" + timeout);

			// Connection stablishment by passing a properties object
			System.out.println("Trying to connect...");
			// -----------------------------------------------------------------
			session.connect(properties);
			// -----------------------------------------------------------------
			System.out.println("...connected:" + session.isConnected());

			try {
				Thread.sleep(timeBeforeDisconnect);
			} catch (InterruptedException e) {
			}
			session.disconnect();
			System.out.println("...disconnected");

			// =================================================================
			// Third connection method: Setting session fields
			// =================================================================
			session = new OpenGateSession();// By default is ROLE_CLIENT

			session.setIpAddress(openGateIPAddress);
			session.setPort(opengatePort);
			session.setUserId(login);
			session.setPassword(password);
			session.setChannelId(channel);
			session.setOrganization(organization);
			session.setDefaultDestinationId(defaultDestination);
			session.setTimeout(timeout);
			session.setConnectionSecure(false);

			System.out.println("Trying to connect...");
			// -----------------------------------------------------------------
			session.connect();
			// -----------------------------------------------------------------
			System.out.println("...connected:" + session.isConnected());

			try {
				Thread.sleep(timeBeforeDisconnect);
			} catch (InterruptedException e) {
			}
			session.disconnect();
			System.out.println("...disconnected");

		} catch (OpenGateSessionException exception) {
			int errorCode = exception.getErrorCode();
			System.out.println("Exception caught with error code:" + errorCode);
			exception.printStackTrace();
		}// try-catch
	}// main
}// class
