package com.ruoyi.blockchain.qy;


import org.hyperledger.fabric.sdk.User;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.QueryByChaincodeRequest;
import org.hyperledger.fabric.sdk.ProposalResponse;
import org.hyperledger.fabric.sdk.TransactionProposalRequest;
import org.hyperledger.fabric.sdk.BlockEvent.TransactionEvent;

import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

public class App{
    public static void main(String[] args) throws Exception{
        System.out.println("============ create fabric client and call chain code ......");



        //String c_cafile ="/opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem";
        String c_channelName="mychannel";
        String c_chaincodeName="mycc";//"qycc";

        //String c_tlsRootCertFiles="/opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt";
        String c_org="org1";
        String c_user="User1";
        String c_configFile="./config.yaml";


        //Admin private key file
        String c_Admin_keyFile="/usr/local/go/src/github.com/hyperledger/fabric-samples/first-network/crypto-config/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp/keystore/b6d4bf00d114bab2ca49a6c6d32313e77ed638ef8f34e761d3114648a97f9b45_sk";
        //Admin CA file
        String c_Admin_certFile="/usr/local/go/src/github.com/hyperledger/fabric-samples/first-network/crypto-config/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp/signcerts/Admin@org1.example.com-cert.pem";


        //User1 private key file
        String c_User1_keyFile="/usr/local/go/src/github.com/hyperledger/fabric-samples/first-network/crypto-config/peerOrganizations/org1.example.com/users/User1@org1.example.com/msp/keystore/"
               // +"e621b2f4988fcbc10dbbda12bb07aad4b742b8ed2518e03abeb589a41028d48b_sk";
                  +"ba1617d5fe15defc60043df0fe844789b4ba7a655076ef07ae3be31b0fd624a7_sk";
        //User1 CA file
        String c_User1_certFile="/usr/local/go/src/github.com/hyperledger/fabric-samples/first-network/crypto-config/peerOrganizations/org1.example.com/users/User1@org1.example.com/msp/signcerts/"
                +"User1@org1.example.com-cert.pem";

        //String c_ordererName="orderer.example.com";
        String c_ordererName="orderer";
        String c_ordererGrpcUrl="grpcs://localhost:7050";

        //String c_peerName="peer0.org1.example.com";
       String c_peerName="peer0";
        String c_peerGrpcUrl="grpcs://localhost:7051";



        /*

peer chaincode invoke
-o orderer.example.com:7050
--tls true
--cafile /opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem
-C mychannel
-n qycc
--peerAddresses peer0.org1.example.com:7051
--tlsRootCertFiles /opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt
-c '{"Args":["insertQyxx", "qy001","qianyue ok!"]}'



peer chaincode invoke -C mychannel -n qycc -c '{"Args":[UserContext"queryQyxx","qy001"]}'

*/

         /*

        //创建User实例
        String keyFile = "../solo-network/msp/keystore/user-key.pem";
        String certFile = "../solo-network/msp/signcerts/user-cert.pem";
        LocalUser user = new LocalUser("admin","SampleOrg",keyFile,certFile);

        //创建HFClient实例
        HFClient client = HFClient.createNewInstance();
        client.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
        client.setUserContext(user);

        */



        //创建User实例
//        String keyFile = "../solo-network/msp/keystore/user-key.pem";
//        String certFile = "../solo-network/msp/signcerts/user-cert.pem";
//        LocalUser user = new LocalUser("admin","SampleOrg",keyFile,certFile);

        LocalUser user = new LocalUser(c_user,c_org,c_User1_keyFile,c_User1_certFile);
        //LocalUser user = new LocalUser(c_user,c_org);


        //创建HFClient实例
        HFClient client = HFClient.createNewInstance();
        client.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
        client.setUserContext(user);
//


        //创建通道实例
//        Channel channel = client.newChannel("ch1");
//        Peer peer = client.newPeer("peer1`","grpc://127.0.0.1:7051");
//        channel.addPeer(peer);
//        Orderer orderer = client.newOrderer("orderer1","grpc://127.0.0.1:7050");
//        channel.addOrderer(orderer);
//        channel.initialize();
        //原文链接：https://blog.csdn.net/rejames/article/details/89876566




        //创建通道实例
        Channel channel = client.newChannel(c_channelName);
        //47d39c32c582        hyperledger/fabric-orderer:latest   "orderer"           1 second ago             Up Less than a second   0.0.0.0:7050->7050/tcp     orderer.example.com
        Orderer orderer = client.newOrderer(c_ordererName,c_ordererGrpcUrl);
        channel.addOrderer(orderer);
        //4c5d3245b9ea    hyperledger/fabric-peer:latest  "peer node start"   0.0.0.0:7051->7051/tcp     peer0.org1.example.com

        Properties aProperties=new Properties();
        aProperties.put("","");

//        - CORE_PEER_TLS_CERT_FILE=/opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/server.crt
//        - CORE_PEER_TLS_KEY_FILE=/opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/server.key
//        - CORE_PEER_TLS_ROOTCERT_FILE=/opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt

        Peer peer = client.newPeer(c_peerName,c_peerGrpcUrl,aProperties);

        channel.addPeer(peer);
        channel.initialize();

       //=============
        //查询链码
        QueryByChaincodeRequest req = client.newQueryProposalRequest();
        ChaincodeID cid = ChaincodeID.newBuilder().setName(c_chaincodeName).build();
        req.setChaincodeID(cid);
        req.setFcn("a");
        ProposalResponse[] rsp = channel.queryByChaincode(req).toArray(new ProposalResponse[0]);
        System.out.format("rsp message => %s\n",rsp[0].getProposalResponse().getResponse().getPayload().toStringUtf8());



        //提交链码交易
//        TransactionProposalRequest req2 = client.newTransactionProposalRequest();
//        req2.setChaincodeID(cid);
//        req2.setFcn("queryQyxx");
//        req2.setArgs("qy001");
//        Collection<ProposalResponse> rsp2 = channel.sendTransactionProposal(req2);
//        TransactionEvent event = channel.sendTransaction(rsp2).get();

//        System.out.format("txid: %s\n", event.getTransactionID());
//        System.out.format("valid: %b\n", event.isValid());
    }
}
/*

解决方法：

1.修改e2e_cli/base 目录下的两个配置文件中CORE_PEER_TLS_ENABLED为false

2.修改e2e_cli目录下docker-compose-cli.yaml文件中CORE_PEER_TLS_ENABLED为false

3.最后重启网络，同时fabric-sdk-java的调用demo中，peer节点及orderer节点通讯链接不要使用grpcs，使用grpc进行通讯


暂时解决方案就是关闭tls.....

1. 在e2e/base内的两个文件里面分别关掉

docker-compose-base.yaml  ---- >  ORDERER_GENERAL_TLS_ENABLED=false

peer-base.yaml  ---- >  CORE_PEER_TLS_ENABLED=false

2.在e2e/docker-compose-cli.yaml内的文件中关掉 CORE_PEER_TLS_ENABLED=false

然后重新部署环境。

需要额外注意的是，搭建步骤内的4.2  / 5.2 等涉及到tls的参数设置 --tls true 去掉
————————————————

原文链接：https://blog.csdn.net/qq_32924343/article/details/81777067

 */