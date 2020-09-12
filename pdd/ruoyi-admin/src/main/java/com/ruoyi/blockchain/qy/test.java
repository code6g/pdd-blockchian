package com.ruoyi.blockchain.qy;


import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.security.CryptoSuite;

public class test {
    public static void main(String[] args) throws Exception {

        HFClient client = HFClient.createNewInstance();
        client.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());

        System.out.println("fabric-sdk-java installed successfully.");
    }
}