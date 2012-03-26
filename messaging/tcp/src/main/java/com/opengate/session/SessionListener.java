package com.opengate.session;

import java.util.Properties;

import es.amplia.opengate.common.ogmessage.OGDataUnit;
import es.amplia.opengate.common.ogmessage.OGDataUnitIterator;
import es.amplia.opengate.common.ogmessage.OGHeader;
import es.amplia.opengate.common.ogmessage.OGMessage;
import es.amplia.opengate.session.OpenGateRemoteException;
import es.amplia.opengate.session.OpenGateSession;
import es.amplia.opengate.session.OpenGateSessionException;
import es.amplia.opengate.session.OpenGateSessionListener;

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
public class SessionListener implements OpenGateSessionListener {

    @Override
    public OGMessage handleCommand(long _messageId, OpenGateSession _session, OGMessage _message)
            throws OpenGateRemoteException {

        System.out.println("=====================================");

        OGHeader header = _message.getHeader();

        String operationName = header.getOperationName();
        String from = header.getIdentifier();

        System.out.println("Command received:");
        System.out.println("\tMessage identifier:" + _messageId);
        System.out.println("\tFrom:" + from);
        System.out.println("\tOperation Name:" + operationName);
        System.out.println("\tData:");
        OGDataUnitIterator iterator = _message.getDataUnitIterator();
        while (iterator.hasNext()) {
            OGDataUnit dataUnit = iterator.next();
            System.out.println("\t\t" + dataUnit.toString());
        }

        // Uncomment this if you want to simulate
        // a large time consumming command
        // System.out.println("Wait some time before respond");
        // try {
        // Thread.sleep(1000);
        // } catch (InterruptedException e) {
        // }

        if (operationName.equals("EXCEPTION_EXAMPLE")) {
            throw new OpenGateRemoteException(_message);
        }

        System.out.println("Sending an echo response message with new String data unit");

        _message.addString("Response Message");

        return _message;
    }

    @Override
    public void handleControlEvent(OpenGateSession _session, OGMessage _message) {
        System.out.println("=====================================");

        OGHeader header = _message.getHeader();

        String operationName = header.getOperationName();
        String from = header.getIdentifier();

        System.out.println("Control Event received:");
        System.out.println("\tFrom:" + from);
        System.out.println("\tOperation Name:" + operationName);
        System.out.println("\tData:");
        OGDataUnitIterator iterator = _message.getDataUnitIterator();
        while (iterator.hasNext()) {
            OGDataUnit dataUnit = iterator.next();
            System.out.println("\t\t" + dataUnit.toString());
        }

    }

    @Override
    public void handleDownConnection(OpenGateSession _session) {
        System.out.println("=================================================");
        System.out.println("Connection has been released!");
    }

    @Override
    public void handleEvent(OpenGateSession _session, OGMessage _message) {
        System.out.println("=====================================");

        OGHeader header = _message.getHeader();

        String operationName = header.getOperationName();
        String from = header.getIdentifier();

        System.out.println("Event received:");
        System.out.println("\tFrom:" + from);
        System.out.println("\tOperation Name:" + operationName);
        System.out.println("\tData:");
        OGDataUnitIterator iterator = _message.getDataUnitIterator();
        while (iterator.hasNext()) {
            OGDataUnit dataUnit = iterator.next();
            System.out.println("\t\t" + dataUnit.toString());
        }

    }

    @Override
    public void handleException(long _messageId, int _errorCode, String _errorDescription, OpenGateSession _session,
            OGMessage _message) {
        System.out.println("=====================================");

        System.out.println("Exception Received:");
        System.out.println("\tMessage identifier:" + _messageId);
        System.out.println("\tError code:" + _errorCode);
        System.out.println("\tError description:" + _errorDescription);

        OGHeader header = _message.getHeader();

        String operationName = header.getOperationName();
        String from = header.getIdentifier();

        System.out.println("Embedded Message:");
        System.out.println("\tFrom:" + from);
        System.out.println("\tOperation Name:" + operationName);
        System.out.println("\tData:");
        OGDataUnitIterator iterator = _message.getDataUnitIterator();
        while (iterator.hasNext()) {
            OGDataUnit dataUnit = iterator.next();
            System.out.println("\t\t" + dataUnit.toString());
        }

    }

    @Override
    public void handleNotification(byte _notificationType, long _messageId, OpenGateSession _session, int _errorCode,
            String _destinationIdentifier) {
        System.out.println("=================================================");
        System.out.println("Notification Received:");
        System.out.println("\tNotification type:" + _notificationType);
        System.out.println("\tMessage identifier:" + _messageId);
        System.out.println("\tError code:" + _errorCode);
        System.out.println("\tFrom:" + _destinationIdentifier);
    }

    @Override
    public void handleResponse(long _messageIdentifier, int _errorCode, String _errorDescription,
            OpenGateSession _session, OGMessage _message) {
        System.out.println("=================================================");
        OGHeader header = _message.getHeader();

        String operationName = header.getOperationName();
        String from = header.getIdentifier();

        System.out.println("Async Response Received:");
        System.out.println("\tFrom:" + from);
        System.out.println("\tOperation Name:" + operationName);
        System.out.println("\tMessage identifier:" + _messageIdentifier);
        System.out.println("\tError code:" + _errorCode);
        System.out.println("\tError description:" + _errorDescription);
        System.out.println("\tData:");
        OGDataUnitIterator iterator = _message.getDataUnitIterator();
        while (iterator.hasNext()) {
            OGDataUnit dataUnit = iterator.next();
            System.out.println("\t\t" + dataUnit.toString());
        }

    }

    @Override
    public void handleUpConnection(OpenGateSession _session) {
        System.out.println("=================================================");
        System.out.println("Connection is up");
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("opengate.host", "172.19.17.64");
        properties.setProperty("opengate.port", "22500");
        properties.setProperty("opengate.login", "default_client");
        properties.setProperty("opengate.password", "default");
        properties.setProperty("opengate.channel", "default_channel");
        properties.setProperty("opengate.organization", "default_organization");
        properties.setProperty("opengate.default", "default_application");
        properties.setProperty("opengate.timeout", "10000");

        OpenGateSession session = new OpenGateSession();

        SessionListener listener = new SessionListener();
        session.setListener(listener);
        try {
            session.connect(properties);
        } catch (OpenGateSessionException e) {
            e.printStackTrace();
        }
    }

}
