package com.opengate.filetransfer.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

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
public class HttpGetFile {
	public static void main(String[] args) {
		try {
			// OpenGate user login
			String login = "default_application";
			// OpenGate user password
			String password = "default";
			// OpenGate user channel
			String channel = "default_channel";
			// OpenGate user organization
			String organization = "default_organization";
			// Local file path for upload
			String localPath = "./filetransfer/opengate2.png";
			// Remote path for upload
			String remotePath = "../default_client/opengate.png";
			remotePath = URLEncoder.encode(remotePath, "UTF-8");

			// Neccessary remotePath encoding to avoid wrong "/" in URI
			remotePath = URLEncoder.encode(remotePath, "UTF-8");
			// URI composition
			String uri = "http://172.19.17.62:9090/opengate/httpfiletransfer/"
					+ "GetFileServlet?" + "organization=" + organization
					+ "&channel=" + channel + "&login=" + login + "&password="
					+ password + "&remotePath=" + remotePath;
			System.out.println("Using URI:");
			System.out.println(uri);

			System.out.println("Composing post method...");
			PostMethod post = new PostMethod(uri);
			System.out.println("...post method composed");
			System.out.println("Creating http client...");
			HttpClient client = new HttpClient();
			System.out.println("...http client created");
			System.out.println("Executing post method...");
			int status = client.executeMethod(post);
			System.out.println("...post method executed with http status " + status);
			InputStream istream = post.getResponseBodyAsStream();
			long fileLength = post.getResponseContentLength();
			System.out.println("Dumping file with size " + fileLength + "...");
			File localFile = new File(localPath);
			OutputStream ostream = new FileOutputStream(localFile);
			byte[] bytes = new byte[1024];
			int readed;
			while ((readed = istream.read(bytes)) > 0) {
				ostream.write(bytes, 0, readed);
			}
			ostream.flush();
			ostream.close();
			istream.close();
			System.out.println("...file dumped to "
					+ localFile.getCanonicalPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
