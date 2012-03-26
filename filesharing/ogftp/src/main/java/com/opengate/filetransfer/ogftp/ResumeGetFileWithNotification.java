package com.opengate.filetransfer.ogftp;

import es.amplia.opengate.common.ogmessage.OGHeader;
import es.amplia.opengate.common.ogmessage.OGMessage;
import es.amplia.opengate.session.filetransfer.FileTransferSession;
import es.amplia.opengate.session.filetransfer.FileTransferSessionException;
import es.amplia.opengate.session.filetransfer.GetFileException;
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
public class ResumeGetFileWithNotification {

	public static void main(String[] args) {

		FileTransferSession fileTransferSession = new FileTransferSession();

		String openGateIPAddress = "172.19.17.63";
		int opengatePort = 22521;
		String login = "default_client";
		String password = "default";
		String channel = "default_channel";
		String organization = "default_organization";
		String localPath = "./filetransfer/opengate.png";
		String remotePath = "opengate.png";
		boolean resume = true;
		boolean mkdirs = true;
		long checksum = 0;
		String notificationDestination = "default_application";
		OGMessage message = new OGMessage();
		OGHeader header = message.getHeader();
		header.setIdentifier(notificationDestination);
		header.setOperationName("TEST_OGFTP_NOTIFICATION");
		Notification notification = new Notification(notificationDestination, message);
		boolean secure = false;
		int timeout = 10000;

		try {
			// Try to connect to OpenGate File Transfer Service
			System.out
					.println("Connecting to OpenGate File Transfer Service...");
			fileTransferSession.connect(openGateIPAddress, opengatePort, login,
					password, channel, organization, secure, timeout);

			// Try to get a remote file from OpenGate file repository
			System.out
					.println("Getting file from remote OpenGate file repository...");
			// Remember that get method is very overloaded with a lot of useful
			// parameters, so please see javadoc or doxygen for more information
			fileTransferSession.get(localPath, remotePath, resume, mkdirs,
					checksum, notification);

			System.out.println("Disconnecting...");
			fileTransferSession.disconnect();
			System.out.println("...disconnected");

		} catch (FileTransferSessionException e) {
			System.out.println(e.getMessage());
		} catch (GetFileException getFileException) {
			System.out.println(getFileException.getMessage());
			/*
			 * It's necessary to pick up remote checksum in order to resume this
			 * get operation
			 */
			checksum = getFileException.getChecksumRemote();
			System.out.println("You can use this " + checksum
					+ " in case you want resume this get operation");
		}

	}
}
