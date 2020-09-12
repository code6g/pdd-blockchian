package com.ruoyi.blockchain.ywtool;

import com.ruoyi.blockchain.qy3.util.Loglw;
import com.ruoyi.blockchain.tool.BlockChainTool;
import com.ruoyi.blockchain.tool.firstnetwork.client.ChannelClient;
import com.ruoyi.blockchain.tool.firstnetwork.client.FabricClient;
import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.ProposalResponse;
import org.hyperledger.fabric.sdk.TransactionProposalRequest;
import sun.reflect.annotation.ExceptionProxy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;

//签约业务工具类
public class QyTool {


    private static final byte[] EXPECTED_EVENT_DATA = "!".getBytes(UTF_8);
    private static final String EXPECTED_EVENT_NAME = "event";


    public static boolean insertQyxx(String qyid, String qynr) {
        try {
            FabricClient fabClient = BlockChainTool.getFabricClient();
            ChannelClient channelClient = BlockChainTool.getChannelClient();

            //=========call function insertQyxx==================
            TransactionProposalRequest request = fabClient.getInstance().newTransactionProposalRequest();
            ChaincodeID ccid = ChaincodeID.newBuilder().setName("qycc").build();
            request.setChaincodeID(ccid);
            request.setFcn("insertQyxx");
            //String qyid="qyxx"+ (new Random()).nextInt();
            //String qynr="qynr----"+ (new Random()).nextInt();
            String[] arguments = {qyid, qynr};
            request.setArgs(arguments);
            request.setProposalWaitTime(1000);

            Map<String, byte[]> tm2 = new HashMap<>();
            tm2.put("HyperLedgerFabric", "TransactionProposalRequest:JavaSDK".getBytes(UTF_8)); // Just some extra junk in transient map
            tm2.put("method", "TransactionProposalRequest".getBytes(UTF_8)); // ditto
            tm2.put("result", ":)".getBytes(UTF_8)); // This should be returned see chaincode why.
            tm2.put(EXPECTED_EVENT_NAME, EXPECTED_EVENT_DATA); // This should trigger an event see chaincode why.
            request.setTransientMap(tm2);

            Collection<ProposalResponse> responses = channelClient.sendTransactionProposal(request);
            for (ProposalResponse pres : responses) {
                Loglw.print("QyTool-qycc-insertQyxx--" + qyid + " Response", pres.getProposalResponse().toString());

                //String stringResponse = new String(pres.getChaincodeActionResponsePayload());
                //Loglw.print("query a result:",stringResponse);
                //System.out.println(stringResponse);
            }
            //=========end ========================================

            return true;

        } catch (Exception e2) {
            return false;
        }

    }

    public static String queryQyxxByQyidJson(String qyid) {
        String sResult = "";
        try {
            FabricClient fabClient = BlockChainTool.getFabricClient();
            ChannelClient channelClient = BlockChainTool.getChannelClient();

            //Thread.sleep(3000);
            String[] args1 = {qyid};
            Collection<ProposalResponse> responses1Query = channelClient.queryByChainCode("qycc", "queryQyxx", args1);
            for (ProposalResponse pres : responses1Query) {
                Loglw.print("根据签约编号，查询签约信息(" + qyid + "), 结果:", pres.getProposalResponse().toString());

                sResult = pres.getProposalResponse().toString();
                //pres.getChaincodeActionResponsePayload()
                String sPayload = new String(pres.getChaincodeActionResponsePayload());

                Loglw.print("sPayload:", sPayload);

                sResult=sPayload;
                //String stringResponse = new String(pres.getChaincodeActionResponsePayload());
                //Loglw.print("query a result:",stringResponse);
                //System.out.println(stringResponse);
            }

        } catch (Exception e2) {
            e2.printStackTrace();
        }

        return sResult;
    }


    public static String[] queryQyxxByQyidJson_all_and_enttJson(String qyid) {
        String[] sResult =new String[2];
        try {
            FabricClient fabClient = BlockChainTool.getFabricClient();
            ChannelClient channelClient = BlockChainTool.getChannelClient();

            //Thread.sleep(3000);
            String[] args1 = {qyid};
            Collection<ProposalResponse> responses1Query = channelClient.queryByChainCode("qycc", "queryQyxx", args1);
            for (ProposalResponse pres : responses1Query) {
                Loglw.print("根据签约编号，查询签约信息(" + qyid + "), 结果:", pres.getProposalResponse().toString());

                sResult[0] = pres.getProposalResponse().toString();
                //pres.getChaincodeActionResponsePayload()
                String sPayload = new String(pres.getChaincodeActionResponsePayload());

                Loglw.print("sPayload:", sPayload);

                sResult[1]=sPayload;
                //String stringResponse = new String(pres.getChaincodeActionResponsePayload());
                //Loglw.print("query a result:",stringResponse);
                //System.out.println(stringResponse);
            }

        } catch (Exception e2) {
            e2.printStackTrace();
        }

        return sResult;
    }

}
