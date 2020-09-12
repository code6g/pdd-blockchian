package com.ruoyi.blockchain.qy2;



import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.hyperledger.fabric.sdk.Enrollment;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

/**
 * 模拟peer,order,admin,user等一些配置项
 */
public class OrgConfig {

    /**
     * 获取Org组织信息,
     * 代码精简自End2End
     * @return
     */
    public static HashMap<String,SOrg> getSOrg() throws Exception{

        HashMap<String,SOrg> orgMap = new HashMap<>();

        SOrg org1=new SOrg("Org1","Org1MSP");

        org1.addPeerLocation(CommonUtils.peer0Org1Name,CommonUtils.peer0Org1Location);
        org1.addPeerLocation(CommonUtils.peer1Org1Name,CommonUtils.peer1Org1Location);
        org1.addOrdererLocation(CommonUtils.orderName,CommonUtils.orderLocation);

        SUser adminOrg1 = new SUser("Admin","Org1MSP");
        SUser user1Org1 = new SUser("User1","Org1MSP");

        PrivateKey adminOrg1PrivateKey=getKeyFile(adminOrg1.getName(),adminOrg1.getMspId());
        String adminOrg1CertificateFile=getCertificateFile(adminOrg1.getName(),adminOrg1.getMspId());
        SampleStoreEnrollement adminOrg1SampleStoreEnrollement=new SampleStoreEnrollement(adminOrg1PrivateKey,adminOrg1CertificateFile);

        adminOrg1.setEnrollment(adminOrg1SampleStoreEnrollement);

//
//        user1Org1.setEnrollment(new SampleStoreEnrollement(getKeyFile(user1Org1.getName(),user1Org1.getMspId())
//                ,getCertificateFile(user1Org1.getName(),user1Org1.getMspId())));

        org1.addUser(adminOrg1);
        org1.addUser(user1Org1);
        org1.setAdmin(adminOrg1);

//        SOrg org2=new SOrg("Org2","Org2MSP");
//        org2.addPeerLocation(CommonUtils.peer0Org2Name,CommonUtils.peer0Org2Location);
//        org2.addPeerLocation(CommonUtils.peer1Org2Name,CommonUtils.peer1Org2Location);
//        org2.addOrdererLocation(CommonUtils.orderName,CommonUtils.orderLocation);
//        SUser adminOrg2 = new SUser("Admin","Org2MSP");
//        SUser user1Org2 = new SUser("User1","Org2MSP");
//
//        adminOrg2.setEnrollment(new SampleStoreEnrollement(getKeyFile(adminOrg2.getName(),adminOrg2.getMspId())
//                ,getCertificateFile(adminOrg2.getName(),adminOrg2.getMspId())));
//
//        user1Org2.setEnrollment(new SampleStoreEnrollement(getKeyFile(user1Org2.getName(),user1Org2.getMspId())
//                ,getCertificateFile(user1Org2.getName(),user1Org2.getMspId())));
//
//        org2.addUser(adminOrg2);
//        org2.addUser(user1Org2);
//        org2.setAdmin(adminOrg2);

        orgMap.put("org1",org1);
//        orgMap.put("org2",org2);
        return orgMap;

    }

    /**
     * 获取对应用户的私钥Key
     * @param userName
     * @param mspId
     * @return
     */
    public static PrivateKey getKeyFile(String userName,String mspId) throws Exception{

        String tempPath = null;

        String fileName = null;

        String keyFile = null;

        if("Org1MSP".equals(mspId)){
            tempPath = CommonUtils.peerFilePath +CommonUtils.org1Path + userName + CommonUtils.org1KeyFilePath;

            fileName = new File(tempPath).listFiles()[0].getName();

            keyFile = tempPath + fileName;
        }else {

            tempPath = CommonUtils.peerFilePath + CommonUtils.org2Path + userName + CommonUtils.org2KeyFilePath;

            fileName = new File(tempPath).listFiles()[0].getName();

            keyFile = tempPath + fileName;
        }
        //来源于ChannelTest
        byte[] bytesKeyFile=IOUtils.toByteArray(new FileInputStream(keyFile));
        return getPrivateKeyFromBytes(bytesKeyFile);

    }

    /**
     * 获取对应用户的ceritificate证书
     * @param userName
     * @param mspId
     * @return
     * @throws Exception
     */
    public static String getCertificateFile(String userName,String mspId) throws Exception{

        String tempPath = null;

        String fileName = null;

        String certFile = null;

        if("Org1MSP".equals(mspId)){
            tempPath = CommonUtils.peerFilePath + CommonUtils.org1Path + userName + CommonUtils.org1CertFilePath;

            fileName = new File(tempPath).listFiles()[0].getName();

            certFile = tempPath + fileName;
        }else {
            tempPath = CommonUtils.peerFilePath + CommonUtils.org2Path + userName + CommonUtils.org2CertFilePath;

            fileName = new File(tempPath).listFiles()[0].getName();

            certFile = tempPath + fileName;
        }
        //来源于ChannelTest
        return new String(IOUtils.toByteArray(new FileInputStream(certFile)), "UTF-8");

    }



    public static PrivateKey getPrivateKeyFromBytes(byte[] data) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        final Reader pemReader = new StringReader(new String(data));

        final PrivateKeyInfo pemPair;
        try (PEMParser pemParser = new PEMParser(pemReader)) {
            pemPair = (PrivateKeyInfo) pemParser.readObject();
        }

        PrivateKey privateKey = new JcaPEMKeyConverter().setProvider(BouncyCastleProvider.PROVIDER_NAME).getPrivateKey(pemPair);

        return privateKey;
    }


    static final class SampleStoreEnrollement implements Enrollment, Serializable {

        private final PrivateKey privateKey;
        private final String certificate;

        SampleStoreEnrollement(PrivateKey privateKey, String certificate)  {
            this.certificate = certificate;
            this.privateKey =  privateKey;
        }

        @Override
        public PrivateKey getKey() {

            return privateKey;
        }

        @Override
        public String getCert() {
            return certificate;
        }

    }

}
