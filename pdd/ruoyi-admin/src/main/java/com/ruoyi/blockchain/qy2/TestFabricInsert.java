package com.ruoyi.blockchain.qy2;

import org.apache.log4j.Logger;
import org.hyperledger.fabric.sdk.Channel;

public class TestFabricInsert {

    private static Logger logger=Logger.getLogger(TestFabricInsert.class);

    public static void main(String[] args) throws Exception{

        FabricUtils.init();

        DataRecord dataRecord = new DataRecord("data","{name:\"testName\",address:\"testAddress\"}");

        testInsert(dataRecord);

    }

    public static void testInsert(DataRecord record) throws Exception {
        System.out.println("test insert");
        Channel channel = FabricUtils.client.newChannel(CommonUtils.channelId);
        channel.addPeer(FabricUtils.client.newPeer(CommonUtils.peer0Org1Name, CommonUtils.peer0Org1Location));
        channel.addOrderer(FabricUtils.client.newOrderer(CommonUtils.orderName, CommonUtils.orderLocation));
        channel.initialize();
        FabricUtils.instertFabcar(channel,record);
    }

}
