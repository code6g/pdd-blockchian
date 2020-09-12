package com.ruoyi.blockchain.qy2;


import org.apache.log4j.Logger;
import org.hyperledger.fabric.sdk.*;
import org.hyperledger.fabric.sdk.security.CryptoSuite;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * FabricUtils,暂时不关注CA方面的东西
 */
public class FabricUtils {

    //日志记录
    private static Logger logger = Logger.getLogger(FabricUtils.class);

    //CA客户端
    public static HFClient client = null;

    public static CryptoSuite cs = null;

    public static HashMap<String, SOrg> orgHashMap=null;

    public static ChaincodeID cid = null;

    public static User peer0org1=null;

    public static void init()throws Exception{

        cs = CryptoSuite.Factory.getCryptoSuite();
        cid = ChaincodeID.newBuilder().setName(CommonUtils.chainCodeName).build();
        client = HFClient.createNewInstance();
        client.setCryptoSuite(cs);
        orgHashMap = OrgConfig.getSOrg();
        peer0org1 = orgHashMap.get("org1").getAdmin();
        client.setUserContext(peer0org1);

    }

    /*
     *   实现插入数据
     * */
    public static void instertFabcar(Channel channel, DataRecord data) throws Exception {
        TransactionProposalRequest req = client.newTransactionProposalRequest();
        req.setChaincodeID(cid);
        req.setFcn("set");
        req.setArgs(data.toStringArray());
        Map<String, byte[]> tm2 = new HashMap<>();
        //代码来自End2End
        tm2.put("HyperLedgerFabric", "TransactionProposalRequest:JavaSDK".getBytes(UTF_8));
        tm2.put("method", "TransactionProposalRequest".getBytes(UTF_8));
        tm2.put("result", ":)".getBytes(UTF_8));
        req.setTransientMap(tm2);
        Collection<ProposalResponse> resps = channel.sendTransactionProposal(req);
        for (ProposalResponse resp : resps) {
            String payload = new String(resp.getChaincodeActionResponsePayload());
            logger.debug("payload!!!!!!!: "+payload);
        }
        channel.sendTransaction(resps);
    }

    /*
     *   实现给定的Key查询数据
     * */
    public static void queryFabcar(Channel channel, String key) throws Exception {
        QueryByChaincodeRequest req = client.newQueryProposalRequest();
        ChaincodeID cid = ChaincodeID.newBuilder().setName(CommonUtils.chainCodeName).build();
        req.setChaincodeID(cid);
        req.setFcn("get");
        req.setArgs(new String[] { key });
        Collection<ProposalResponse> resps = channel.queryByChaincode(req);
        for (ProposalResponse resp : resps) {
            String payload = new String(resp.getChaincodeActionResponsePayload());
            logger.debug("payload!!!!!!!: "+payload);
        }
    }
}
