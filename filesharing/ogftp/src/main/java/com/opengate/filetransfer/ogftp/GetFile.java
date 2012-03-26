package com.opengate.filetransfer.ogftp;

import es.amplia.opengate.session.filetransfer.FileTransferSession;
import es.amplia.opengate.session.filetransfer.FileTransferSessionException;
import es.amplia.opengate.session.filetransfer.GetFileException;

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
public class GetFile {

	public static void main(String[] args) {

		FileTransferSession fileTransferSession = new FileTransferSession();

		String openGateIPAddress = "172.19.17.63";
		int opengatePort = 22521;
		String login = "default_client";
		String password = "default";
		String channel = "default_channel";
		String organization = "default_organization";
		String localPath = "opengate2.png";
		String remotePath = "../default_application/opengate.png";
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
			fileTransferSession.get(localPath, remotePath);
			/*
			 * Remember that get method is very overloaded with a lot of useful
			 * parameters, so please see javadoc or doxygen for more information
			 */

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
			long checksum = getFileException.getChecksumRemote();
			System.out.println("You can use this " + checksum
					+ " in case you want resume this get operation");
		}

	}
}
