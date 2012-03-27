package com.opengate.session.control;

import java.util.Properties;

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
public class Ping {

	public static void main(String[] args) throws OpenGateSessionException {

		/*
		 * In this example you can see connection method using a Properties
		 * object containing connection properties.
		 */
		Properties properties = new Properties();
		properties.setProperty("opengate.host", "172.19.17.62");
		properties.setProperty("opengate.port", "22500");
		properties.setProperty("opengate.login", "default_client");
		properties.setProperty("opengate.password", "default");
		properties.setProperty("opengate.channel", "default_channel");
		properties.setProperty("opengate.organization", "default_organization");
		properties.setProperty("opengate.default", "default_application");
		properties.setProperty("opengate.timeout", "10000");

		OpenGateSession session = new OpenGateSession();

		session.connect(properties);
		long pingTime = session.pingToPlatform();
		System.out.println("Ping time:"+pingTime);
	}
}
