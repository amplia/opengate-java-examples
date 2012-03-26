package com.opengate.rest;

import com.opengate.common.xml.Ogmessage;
import java.util.ArrayList;
import java.util.List;

public class OpenGateDataHolder {

    private String login;
    private String password;
    private String channel;
    private String organization;
    private String baseUrl;
    private String listenerUrl;
    private String token;
    private List<Ogmessage> messageList;

    public OpenGateDataHolder() {
        messageList = new ArrayList<Ogmessage>();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getListenerUrl() {
        return listenerUrl;
    }

    public void setListenerUrl(String listenerUrl) {
        this.listenerUrl = listenerUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public synchronized String getToken() {
        return token;
    }

    public synchronized void setToken(String token) {
        this.token = token;
    }

    public synchronized void addMessage(Ogmessage ogmessage) {
        this.messageList.add(ogmessage);
    }

    public synchronized void clearMessageList() {
        this.messageList.clear();
    }

    public List<Ogmessage> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Ogmessage> messageList) {
        this.messageList = messageList;
    }
}
