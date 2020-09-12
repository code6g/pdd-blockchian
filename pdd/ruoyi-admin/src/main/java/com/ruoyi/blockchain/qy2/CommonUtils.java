package com.ruoyi.blockchain.qy2;



public class CommonUtils {

    //peer节点的CA文件路径
    public static final String cryptoPeerPath = "crypto-config/peerOrganizations/";
    //order节点的CA文件路径
    public static final String cryptoOrderPath = "crypto-config/ordererOrganizations/";

//    //peer节点的CA文件路径
//    public static String peerFilePath = CommonUtils.class.getResource("/").getPath() + cryptoPeerPath;
//
//    //order节点的CA文件路径
//    public static String orderFilePath = CommonUtils.class.getResource("/").getPath() + cryptoOrderPath;


   //this code is for the first network test
    public static String pathFirstNetwork="/usr/local/go/src/github.com/hyperledger/fabric-samples/first-network/";

    //peer节点的CA文件路径
    public static String peerFilePath = pathFirstNetwork + cryptoPeerPath;

    //order节点的CA文件路径
    public static String orderFilePath = pathFirstNetwork + cryptoOrderPath;






    //org1组织的目录
    public static final String org1Path = "org1.example.com/users/";

    //org1组织的cert文件目录
    public static final String org1CertFilePath = "@org1.example.com/msp/signcerts/";

    //org1组织的Key文件目录
    public static final String org1KeyFilePath = "@org1.example.com/msp/keystore/";

    //org2组织的目录
    public static final String org2Path = "org2.example.com/users/";

    //org2组织的cert文件目录
    public static final String org2CertFilePath = "@org2.example.com/msp/signcerts/";

    //org2组织的Key文件目录
    public static final String org2KeyFilePath = "@org2.example.com/msp/keystore/";

    //链码名称
    public static final String chainCodeName = "mycc";

    //链码版本
    public static final String chaincodeVersion = "1";

    //channel名称
    public static final String channelId = "mychannel";

    //order名称 和 Fabrci-java-sdk保持一致
    public static final String orderName = "orderer.example.com";

    //peer0org1名称  和 Fabrci-java-sdk保持一致
    public static final String peer0Org1Name = "peer0.org1.example.com";
    //peer1org1名称 和 Fabrci-java-sdk保持一致
    public static final String peer1Org1Name = "peer1.org1.example.com";

    //peer0org2名称 和 Fabrci-java-sdk保持一致
    public static final String peer0Org2Name = "peer0.org2.example.com";
    //peer1org2名称 和 Fabrci-java-sdk保持一致
    public static final String peer1Org2Name = "peer1.org2.example.com";

    //order地址
    public static final String orderLocation = "grpc://localhost:7050";
    //peer0org1地址
    public static final String peer0Org1Location = "grpc://localhost:7051";
    //peer1org1地址
    public static final String peer1Org1Location = "grpc://localhost:8051";
    //peer0org2地址
    public static final String peer0Org2Location = "grpc://localhost:9051";
    //peer1org2地址
    public static final String peer1Org2Location = "grpc://localhost:10051";

}
