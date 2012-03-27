package com.opengate.filetransfer.http;

import java.io.File;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

/**
 * <p>
 * The HttpGetFile class shows how to download a remote file from OpenGate file
 * repository.
 * </p>
 * <p>
 * <b>OpenGate M2M/Mobility Communications Platform</b>
 * </p>
 * <p>
 * <i>Wireless your bussiness</i>
 * </p>
 * <hr/>
 * <p>
 * If you want more info about this example please send an email to <a
 * href="mailto:support@amplia.es">Amplia Support</a>
 * <p>
 * Copyright 2007 Amplia Soluciones S.L. All rights reserved.
 * 
 * @author Amplia Soluciones S.L
 */
public class HttpPutFile {
	public static void main(String[] args) {
		try {
			// OpenGate user login
			String login = "default_client";
			// OpenGate user password
			String password = "default";
			// OpenGate user channel
			String channel = "default_channel";
			// OpenGate user organization
			String organization = "default_organization";
			// Local file path for upload
			String localPath = "opengate.png";
			// Remote path for upload
			String remotePath = "opengate.png";

			// Local file to upload
			File localFile = new File(localPath);
			// Neccessary remotePath encoding to avoid wrong "/" in URI
			remotePath = URLEncoder.encode(remotePath, "UTF-8");
			// URI composition
			String uri = "http://172.19.17.62:9090/opengate/httpfiletransfer/"
					+ "PutFileServlet?" + "organization=" + organization
					+ "&channel=" + channel + "&login=" + login + "&password="
					+ password + "&remotePath=" + remotePath;
			System.out.println("Using URI:");
			System.out.println(uri);
			System.out.println("Composing post method...");
			PostMethod post = new PostMethod(uri);
			Part[] parts = { new FilePart(localFile.getName(), localFile) };
			post.setRequestEntity(new MultipartRequestEntity(parts, post
					.getParams()));
			System.out.println("...post method composed");
			System.out.println("Creating http client...");
			HttpClient client = new HttpClient();
			System.out.println("...http client created");
			System.out.println("Executing upload...");
			int status = client.executeMethod(post);
			System.out.println("...upload executed with http status " + status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
