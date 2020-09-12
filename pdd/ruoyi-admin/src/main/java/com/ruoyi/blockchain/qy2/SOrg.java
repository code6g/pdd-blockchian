package com.ruoyi.blockchain.qy2;

import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.User;
import org.hyperledger.fabric_ca.sdk.HFCAClient;

import java.util.*;

/**
 * Java-SDK直接复制得来
 */
public class SOrg {

    private String name;
    private String mspid;
    private HFCAClient caClient;  //暂时未使用
    private Map<String, User> userMap = new HashMap<String, User>();
    private Map<String, String> peerLocations = new HashMap<String, String>();
    private Map<String, String> ordererLocations = new HashMap<String, String>();
    private Map<String, String> eventHubLocations = new HashMap<String, String>(); //暂时未使用
    private Set<Peer> peers = new HashSet<Peer>(); //暂时未使用
    private SUser admin;
    private String caLocation; //暂时未使用
    private Properties caProperties = null; //暂时未使用
    private SUser peerAdmin; //暂时未使用
    private String domainName; //暂时未使用

    public SOrg(String name, String mspid) {
        this.name = name;
        this.mspid = mspid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMspid(String mspid) {
        this.mspid = mspid;
    }

    public void setCaClient(HFCAClient caClient) {
        this.caClient = caClient;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }

    public void setPeerLocations(Map<String, String> peerLocations) {
        this.peerLocations = peerLocations;
    }

    public void setOrdererLocations(Map<String, String> ordererLocations) {
        this.ordererLocations = ordererLocations;
    }

    public void setEventHubLocations(Map<String, String> eventHubLocations) {
        this.eventHubLocations = eventHubLocations;
    }

    public void setPeers(Set<Peer> peers) {
        this.peers = peers;
    }

    public void setAdmin(SUser admin) {
        this.admin = admin;
    }

    public void setCaLocation(String caLocation) {
        this.caLocation = caLocation;
    }

    public void setCaProperties(Properties caProperties) {
        this.caProperties = caProperties;
    }

    public void setPeerAdmin(SUser peerAdmin) {
        this.peerAdmin = peerAdmin;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getName() {
        return name;
    }

    public String getMspid() {
        return mspid;
    }

    public HFCAClient getCaClient() {
        return caClient;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public Map<String, String> getPeerLocations() {
        return peerLocations;
    }

    public Map<String, String> getOrdererLocations() {
        return ordererLocations;
    }

    public Map<String, String> getEventHubLocations() {
        return eventHubLocations;
    }

    public Set<Peer> getPeers() {
        return peers;
    }

    public SUser getAdmin() {
        return admin;
    }

    public String getCaLocation() {
        return caLocation;
    }

    public Properties getCaProperties() {
        return caProperties;
    }

    public SUser getPeerAdmin() {
        return peerAdmin;
    }

    public String getDomainName() {
        return domainName;
    }

    public void addPeerLocation(String name, String location) {

        peerLocations.put(name, location);
    }

    public void addOrdererLocation(String name, String location) {

        ordererLocations.put(name, location);
    }

    public void addEventHubLocation(String name, String location) {

        eventHubLocations.put(name, location);
    }

    public String getPeerLocation(String name) {
        return peerLocations.get(name);

    }

    public String getOrdererLocation(String name) {
        return ordererLocations.get(name);

    }

    public String getEventHubLocation(String name) {
        return eventHubLocations.get(name);

    }

    public void addUser(SUser user) {
        userMap.put(user.getName(), user);
    }

}
