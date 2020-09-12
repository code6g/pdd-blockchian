package com.ruoyi.blockchain.qy2;


import org.apache.log4j.Logger;
import org.hyperledger.fabric.sdk.Channel;

public class TestFabricQuery {

    private static Logger logger=Logger.getLogger(TestFabricQuery.class);



    public static void testQuery(DataRecord record) throws Exception {
        logger.debug("测试Fabric 查询功能");
        Channel channel = FabricUtils.client.newChannel(CommonUtils.channelId);
//        channel.addPeer(FabricUtils.client.newPeer(CommonUtils.peer0Org1Name, FabricUtils.orgHashMap.get("org1").getPeerLocation(CommonUtils.peer0Org1Name)));
//        channel.addOrderer(FabricUtils.client.newOrderer(CommonUtils.orderName, FabricUtils.orgHashMap.get("org1").getOrdererLocation(CommonUtils.orderName)));
//        channel.initialize();
        FabricUtils.queryFabcar(channel, record.getKey());
    }

    public static void main(String[] args) throws Exception{
        DataRecord dataRecord = new DataRecord("data","");

        FabricUtils.init();

        testQuery(dataRecord);

    }
}
