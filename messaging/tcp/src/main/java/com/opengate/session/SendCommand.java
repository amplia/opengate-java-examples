package com.opengate.session;

import es.amplia.opengate.common.OGConstants;
import es.amplia.opengate.common.ogmessage.OGDataUnit;
import es.amplia.opengate.common.ogmessage.OGHeader;
import es.amplia.opengate.common.ogmessage.OGMessage;
import es.amplia.opengate.session.OpenGateRemoteException;
import es.amplia.opengate.session.OpenGateSession;
import es.amplia.opengate.session.OpenGateSessionException;
import java.util.Date;

/**
 * <p> <b>OpenGate M2M/Mobility Communications Platform</b> </p> <p> <i>Wireless your bussiness</i> </p> <p>
 * <b>Description:</b><br/> Description </p> <p> If you want more info about this example please send an email to <a
 * href="mailto:support@amplia.es">Amplia Support</a> <p> Copyright 2007 Amplia Soluciones S.L. All rights reserved.
 *
 * @author Amplia Soluciones S.L
 */
public class SendCommand {

    public static void main(String[] args) {

        OpenGateSession session = new OpenGateSession(OGConstants.ROLE_CLIENT);

        SessionListener listener = new SessionListener();
        session.setListener(listener);

        String openGateIPAddress = "172.19.17.62";
        int opengatePort = 22500;
        String login = "default_client";
        String password = "default";
        String channel = "default_channel";
        String organization = "default_organization";
        String defaultDestination = "default_application";
        int timeout = 10000;
        boolean secure = false;

        try {
            /*
             * In this example you can see connection method by passing connection parameters.
             */
            session.connect(openGateIPAddress, opengatePort, login, password,
                    channel, organization, defaultDestination, timeout, secure);
            OGMessage request = new OGMessage();
            request.setOperationName("EXAMPLE_COMMAND");
            request.addInt(12);
            request.addString("Example Message");
            request.addBoolean(true);


            long trId = new Date().getTime() / 1000;
            System.out.println("Transaction ID:" + trId);

            OGMessage response = session.sendCommand(trId, request,
                    "default_application", 10000);
            OGHeader header = response.getHeader();

            String from = header.getIdentifier();

            System.out.println("=============================================");
            System.out.println("Response Received:");
            System.out.println("\tFrom:" + from);
            System.out.println("\tData:");

            /*
             * In this example we will try to recover response data getting OGDataUnit objects by their position into
             * OGMessage
             */
            OGDataUnit dataUnit;

            dataUnit = response.getDataUnitAt(0);
            int intValue = dataUnit.getIntValue();
            System.out.println("\t\tInt Value:" + intValue);

            dataUnit = response.getDataUnitAt(1);
            String stringValue = response.getDataUnitAt(1).getStringValue();
            System.out.println("\t\tString Value:" + stringValue);

            dataUnit = response.getDataUnitAt(2);
            boolean booleanValue = response.getDataUnitAt(2).getBoolValue();
            System.out.println("\t\tBoolean Value:" + booleanValue);

        } catch (OpenGateSessionException e) {
            e.printStackTrace();
        } catch (OpenGateRemoteException e) {
            OGMessage exceptionMessage = e.getogMessage();
            System.out.println(exceptionMessage);
        }// try-catch
    }// main
}// class
