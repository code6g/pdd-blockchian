/****************************************************** 
 *  Copyright 2018 IBM Corporation 
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  you may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at 
 *  http://www.apache.org/licenses/LICENSE-2.0 
 *  Unless required by applicable law or agreed to in writing, software 
 *  distributed under the License is distributed on an "AS IS" BASIS, 
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 *  See the License for the specific language governing permissions and 
 *  limitations under the License.
 */
package com.ruoyi.blockchain.tool.firstnetwork.chaincode.callmycc;

import com.google.protobuf.ByteString;
import com.ruoyi.blockchain.tool.firstnetwork.client.CAClient;
import com.ruoyi.blockchain.tool.firstnetwork.client.ChannelClient;
import com.ruoyi.blockchain.tool.firstnetwork.client.FabricClient;
import com.ruoyi.blockchain.tool.firstnetwork.config.Config;
import com.ruoyi.blockchain.tool.firstnetwork.user.UserContext;
import com.ruoyi.blockchain.tool.firstnetwork.util.Loglw;
import com.ruoyi.blockchain.tool.firstnetwork.util.Util;
import org.apache.commons.codec.binary.Hex;
import org.hyperledger.fabric.protos.peer.Query;
import org.hyperledger.fabric.sdk.*;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.charset.StandardCharsets.UTF_8;


/**
 * @author 牛哥
 */

public class QueryChaincode {

    private static final byte[] EXPECTED_EVENT_DATA = "!".getBytes(UTF_8);
    private static final String EXPECTED_EVENT_NAME = "event";

    public static void main(String args[]) {
        try {
            Util.cleanUp();
            String caUrl = Config.CA_ORG1_URL;
            CAClient caClient = new CAClient(caUrl, null);
            // Enroll Admin to Org1MSP
            UserContext adminUserContext = new UserContext();
            adminUserContext.setName(Config.ADMIN);
            adminUserContext.setAffiliation(Config.ORG1);
            adminUserContext.setMspId(Config.ORG1_MSP);
            caClient.setAdminUserContext(adminUserContext);
            adminUserContext = caClient.enrollAdminUser(Config.ADMIN, Config.ADMIN_PASSWORD);

            FabricClient fabClient = new FabricClient(adminUserContext);

            ChannelClient channelClient = fabClient.createChannelClient(Config.CHANNEL_NAME);
            Channel channel = channelClient.getChannel();
            Peer peer = fabClient.getInstance().newPeer(Config.ORG1_PEER_0, Config.ORG1_PEER_0_URL);
            EventHub eventHub = fabClient.getInstance().newEventHub("eventhub01", "grpc://" + Config.baseUrl + ":7053");
            Orderer orderer = fabClient.getInstance().newOrderer(Config.ORDERER_NAME, Config.ORDERER_URL);
            channel.addPeer(peer);
            channel.addEventHub(eventHub);
            channel.addOrderer(orderer);
            channel.initialize();

            Logger.getLogger(QueryChaincode.class.getName()).log(Level.INFO, "Querying for all cars ...");
            Collection<ProposalResponse> responsesQuery = channelClient.queryByChainCode("mycc", "query", new String[]{"b"});
            for (ProposalResponse pres : responsesQuery) {
                String stringResponse = new String(pres.getChaincodeActionResponsePayload());
                Logger.getLogger(QueryChaincode.class.getName()).log(Level.INFO, stringResponse);
            }
            BlockchainInfo bci = channel.queryBlockchainInfo(peer);
            //System.out.println("LW==== [区块高度:] " + bci.getHeight());
            Loglw.print("区块高度", bci.getHeight());


            BlockInfo bi = channel.queryBlockByNumber(2);
            ByteString blockhash = bi.getBlock().getHeader().getPreviousHash();
            String blocakhashStr = Hex.encodeHexString(bi.getPreviousHash());
            //System.out.println("LW==== [Perior Block Hash code is]  "+blocakhashStr);
            Loglw.print("Perior Block Hash code", blocakhashStr);


            List<Query.ChaincodeInfo> ccdl = channel.queryInstantiatedChaincodes(peer);
            for (Query.ChaincodeInfo a : ccdl) {
                String name = a.getName();
                String path = a.getPath();

                //System.out.println(name + path);
                Loglw.print("Chain Code name and path is", name + "   " + path);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
