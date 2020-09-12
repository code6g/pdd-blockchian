package com.ruoyi.blockchain.ywtool;

import com.ruoyi.blockchain.qy3.util.Loglw;
import com.ruoyi.blockchain.tool.BlockChainTool;
import com.ruoyi.blockchain.tool.firstnetwork.client.ChannelClient;
import com.ruoyi.blockchain.tool.firstnetwork.client.FabricClient;
import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.ProposalResponse;
import org.hyperledger.fabric.sdk.TransactionProposalRequest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

//Base operate block chain 工具类

public class BaseTool {


    private static final byte[] EXPECTED_EVENT_DATA = "!".getBytes(UTF_8);
    private static final String EXPECTED_EVENT_NAME = "event";


    //在区块链上插入键值对，同时需要传入链码名称，函数名称，key是组合主键，value是值的字符串array
    public static boolean insertKeyArrayValue(String chainCodeName,String functionName, String[] valueArrayStr) {
        try {

            FabricClient fabClient = BlockChainTool.getFabricClient(); //创建fabirc客户端
            ChannelClient channelClient = BlockChainTool.getChannelClient(); //获得通道客户端

            //=========call function insertEntt==================
            //创建提案会话
            TransactionProposalRequest request = fabClient.getInstance().newTransactionProposalRequest();
            //创建链码引用ID
            ChaincodeID ccid = ChaincodeID.newBuilder().setName(chainCodeName).build();
            //提案中设置链码ID
            request.setChaincodeID(ccid);
            //提案中设置函数名称
            request.setFcn(functionName);

            //创建参数数组
            String[] arguments = new String[valueArrayStr.length];
            //arguments[0]=key;
            for(int i=0;i<valueArrayStr.length;i++){
                arguments[i]=valueArrayStr[i];
            }


            // create and log the exec command
            //peer chaincode invoke -o orderer.example.com:7050 -C mychannel -n spxxcc --peerAddresses peer0.org1.example.com:7051 -c  '{"Args":["insertSpxx","sp001","0101","food","apple","5","shanxi apple","no","1"]}'
            String s_exec="";
            s_exec+=" peer chaincode invoke -o orderer.example.com:7050 -C mychannel ";
            s_exec+=" -n "+chainCodeName+" ";
            s_exec+=" --peerAddresses peer0.org1.example.com:7051  ";
            s_exec+=" -c '{\"Args\":[";
            s_exec+="\""+functionName+"\",";
            for(int i=0;i<valueArrayStr.length;i++){
                //arguments[i]=valueArrayStr[i];
                s_exec+="\""+valueArrayStr[i]+"\"";
                if(i!=(valueArrayStr.length-1)){
                    s_exec+=",";
                }
            }
            s_exec+="]}' ";
            Loglw.write_to_logfile(s_exec);
            //end create and log the exec command


            //提案中设置参数
            request.setArgs(arguments);
            //设置提案超时时间
            request.setProposalWaitTime(1000);

            Map<String, byte[]> tm2 = new HashMap<>();
            tm2.put("HyperLedgerFabric", "TransactionProposalRequest:JavaSDK".getBytes(UTF_8)); // Just some extra junk in transient map
            tm2.put("method", "TransactionProposalRequest".getBytes(UTF_8)); // ditto
            tm2.put("result", ":)".getBytes(UTF_8)); // This should be returned see chaincode why.
            tm2.put(EXPECTED_EVENT_NAME, EXPECTED_EVENT_DATA); // This should trigger an event see chaincode why.

            //设置临时事件参数
            request.setTransientMap(tm2);

            //通过通道客户端，发送提案，得到响应信sPayload息
            Collection<ProposalResponse> responses = channelClient.sendTransactionProposal(request);
            //解析响应信息，打印出来
            for (ProposalResponse pres : responses) {
                Loglw.print("BlockChain-BaseTool-"+chainCodeName+"-"+functionName+"- arguments:" + arguments + " Response", pres.getProposalResponse().toString());

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


    //在区块链上插入键值对，同时需要传入链码名称，函数名称，key是组合主键，value是值的json字符串
    public static boolean insertKeyJsonValue(String chainCodeName,String functionName, String key, String valueJsonStr) {
        try {

            FabricClient fabClient = BlockChainTool.getFabricClient(); //创建fabirc客户端
            ChannelClient channelClient = BlockChainTool.getChannelClient(); //获得通道客户端

            //=========call function insertEntt==================
            //创建提案会话
            TransactionProposalRequest request = fabClient.getInstance().newTransactionProposalRequest();
            //创建链码引用ID
            ChaincodeID ccid = ChaincodeID.newBuilder().setName(chainCodeName).build();
            //提案中设置链码ID
            request.setChaincodeID(ccid);
            //提案中设置函数名称
            request.setFcn(functionName);

            //创建参数数组
            String[] arguments = {key, valueJsonStr};
            //提案中设置参数
            request.setArgs(arguments);
            //设置提案超时时间
            request.setProposalWaitTime(1000);

            Map<String, byte[]> tm2 = new HashMap<>();
            tm2.put("HyperLedgerFabric", "TransactionProposalRequest:JavaSDK".getBytes(UTF_8)); // Just some extra junk in transient map
            tm2.put("method", "TransactionProposalRequest".getBytes(UTF_8)); // ditto
            tm2.put("result", ":)".getBytes(UTF_8)); // This should be returned see chaincode why.
            tm2.put(EXPECTED_EVENT_NAME, EXPECTED_EVENT_DATA); // This should trigger an event see chaincode why.

            //设置临时事件参数
            request.setTransientMap(tm2);

            //通过通道客户端，发送提案，得到响应信息
            Collection<ProposalResponse> responses = channelClient.sendTransactionProposal(request);
            //解析响应信息，打印出来
            for (ProposalResponse pres : responses) {
                Loglw.print("BlockChain-BaseTool-"+chainCodeName+"-"+functionName+"-" + key + " Response", pres.getProposalResponse().toString());

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

    //根据链码，函数名称，及key，查询对应的value
    public static String queryValueByKey(String chainCodeName,String functionName, String key) {
        String sResult = "";
        try {
            //创建fabirc客户端
            FabricClient fabClient = BlockChainTool.getFabricClient();
            //获得通道客户端
            ChannelClient channelClient = BlockChainTool.getChannelClient();

            //Thread.sleep(3000);
            //创建参数数组
            String[] args1 = {key};
            //通过通道客户端，进行查询，得到响应信息
            Collection<ProposalResponse> responses1Query = channelClient.queryByChainCode(chainCodeName, functionName, args1);
            //解析响应信息，打印出来
            for (ProposalResponse pres : responses1Query) {
                Loglw.print("根据key，查询value(" + key + "), 结果:", pres.getProposalResponse().toString());

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

    //根据链码，函数名称，及rangeStart,rangeEnd，查询对应的value
    public static String queryRange(String chainCodeName,String functionName, String rangeStart,String rangeEnd) {
        String sResult = "";
        try {
            //创建fabirc客户端
            FabricClient fabClient = BlockChainTool.getFabricClient();
            //获得通道客户端
            ChannelClient channelClient = BlockChainTool.getChannelClient();

            //Thread.sleep(3000);
            //创建参数数组
            String[] args1 = {rangeStart,rangeEnd};
            //通过通道客户端，进行查询，得到响应信息
            Collection<ProposalResponse> responses1Query = channelClient.queryByChainCode(chainCodeName, functionName, args1);
            //解析响应信息，打印出来
            for (ProposalResponse pres : responses1Query) {
                Loglw.print("rangeStart,rangeEnd，查询value(" + rangeStart+" -- "+rangeEnd + "), 结果:", pres.getProposalResponse().toString());

                sResult = pres.getProposalResponse().toString();
                //pres.getChaincodeActionResponsePayload()
                String sPayload = new String(pres.getChaincodeActionResponsePayload());

                Loglw.print("==queryBatch--sPayload:", sPayload);

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



    //在区块链上delete value by key
    public static boolean deleteByKey(String chainCodeName,String functionName, String key) {
        try {

            FabricClient fabClient = BlockChainTool.getFabricClient(); //创建fabirc客户端
            ChannelClient channelClient = BlockChainTool.getChannelClient(); //获得通道客户端

            //=========call function insertEntt==================
            //创建提案会话
            TransactionProposalRequest request = fabClient.getInstance().newTransactionProposalRequest();
            //创建链码引用ID
            ChaincodeID ccid = ChaincodeID.newBuilder().setName(chainCodeName).build();
            //提案中设置链码ID
            request.setChaincodeID(ccid);
            //提案中设置函数名称
            request.setFcn(functionName);

            //创建参数数组
            String[] arguments = {key};
            //提案中设置参数
            request.setArgs(arguments);
            //设置提案超时时间
            request.setProposalWaitTime(1000);

            Map<String, byte[]> tm2 = new HashMap<>();
            tm2.put("HyperLedgerFabric", "TransactionProposalRequest:JavaSDK".getBytes(UTF_8)); // Just some extra junk in transient map
            tm2.put("method", "TransactionProposalRequest".getBytes(UTF_8)); // ditto
            tm2.put("result", ":)".getBytes(UTF_8)); // This should be returned see chaincode why.
            tm2.put(EXPECTED_EVENT_NAME, EXPECTED_EVENT_DATA); // This should trigger an event see chaincode why.

            //设置临时事件参数
            request.setTransientMap(tm2);

            //通过通道客户端，发送提案，得到响应信息
            Collection<ProposalResponse> responses = channelClient.sendTransactionProposal(request);
            //解析响应信息，打印出来
            for (ProposalResponse pres : responses) {
                Loglw.print("BlockChain-BaseTool-"+chainCodeName+"-"+functionName+"-" + key + " Response", pres.getProposalResponse().toString());

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



    //根据链码，函数名称，及部分key值匹配，查询所有的value的json字符串数组
    public static String[] queryMultiValueByPartKey(String chainCodeName,String functionName, String partKey) {
        String[] sResult =new String[2];
        try {
            FabricClient fabClient = BlockChainTool.getFabricClient();
            ChannelClient channelClient = BlockChainTool.getChannelClient();

            //Thread.sleep(3000);
            String[] args1 = {partKey};
            Collection<ProposalResponse> responses1Query =
                    channelClient.queryByChainCode(chainCodeName, functionName, args1);
            for (ProposalResponse pres : responses1Query) {
                Loglw.print("根据key，查询value信息(" + partKey + "), 结果:", pres.getProposalResponse().toString());

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
