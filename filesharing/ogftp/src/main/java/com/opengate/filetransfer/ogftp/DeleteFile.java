package com.opengate.filetransfer.ogftp;

import es.amplia.opengate.session.filetransfer.FileTransferSession;
import es.amplia.opengate.session.filetransfer.FileTransferSessionException;
import es.amplia.opengate.session.filetransfer.Notification;

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
public class DeleteFile {
	public static void main(String[] args) {
		FileTransferSession fileTransferSession = new FileTransferSession();

		String openGateIPAddress = "172.19.17.63";
		int opengatePort = 22521;
		String login = "default_client";
		String password = "default";
		String channel = "default_channel";
		String organization = "default_organization";
		String path = "../default_application/opengate.png";
		boolean secure = false;
		int timeout = 10000;
//		OGMessage notification = null;
		Notification notification = null;

		try {
			System.out
					.println("Connecting to OpenGate File Transfer Service...");
			fileTransferSession.connect(openGateIPAddress, opengatePort, login,
					password, channel, organization, secure, timeout);
			System.out
					.println("Deleting file in remote OpenGate file repository...");
			fileTransferSession.deleteFile(path, notification);
			System.out.println("Operation finished successfully!");
			/*
			 * Remember that put method is very overloaded with a lot of useful
			 * parameters, so please see javadoc or doxygen for more information
			 */

			System.out.println("Disconnecting...");
			fileTransferSession.disconnect();
			System.out.println("...disconnected");

		} catch (FileTransferSessionException e) {
			System.out.println(e.getMessage());
		}
	}
}
