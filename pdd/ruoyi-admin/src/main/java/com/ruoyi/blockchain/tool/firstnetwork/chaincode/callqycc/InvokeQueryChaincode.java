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
package com.ruoyi.blockchain.tool.firstnetwork.chaincode.callqycc;

import com.ruoyi.blockchain.tool.firstnetwork.client.CAClient;
import com.ruoyi.blockchain.tool.firstnetwork.client.ChannelClient;
import com.ruoyi.blockchain.tool.firstnetwork.client.FabricClient;
import com.ruoyi.blockchain.tool.firstnetwork.config.Config;
import com.ruoyi.blockchain.tool.firstnetwork.user.UserContext;
import com.ruoyi.blockchain.tool.firstnetwork.util.Loglw;
import com.ruoyi.blockchain.tool.firstnetwork.util.Util;
import org.hyperledger.fabric.sdk.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 
 * @author
 */

public class InvokeQueryChaincode {

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
			EventHub eventHub = fabClient.getInstance().newEventHub("eventhub01", "grpc://localhost:7053");
			Orderer orderer = fabClient.getInstance().newOrderer(Config.ORDERER_NAME, Config.ORDERER_URL);
			channel.addPeer(peer);
			channel.addEventHub(eventHub);
			channel.addOrderer(orderer);
			channel.initialize();


			//=========call function insertQyxx==================
			TransactionProposalRequest request = fabClient.getInstance().newTransactionProposalRequest();
			ChaincodeID ccid = ChaincodeID.newBuilder().setName("qycc").build();
			request.setChaincodeID(ccid);

//			request.setFcn("createCar");
//			String[] arguments = { "CAR1", "Chevy", "Volt", "Red", "Nick" };


 			request.setFcn("insertQyxx");
 			String qyid="qyxx"+ (new Random()).nextInt();
			String qynr="qynr----"+ (new Random()).nextInt();
			String[] arguments = { qyid, qynr };
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
				Loglw.print("qycc-insertQyxx-"+qyid+" Response",pres.getProposalResponse().toString());

				//String stringResponse = new String(pres.getChaincodeActionResponsePayload());
				//Loglw.print("query a result:",stringResponse);
				//System.out.println(stringResponse);
			}

			//=========end ========================================


			//Thread.sleep(3000);
			
//			Collection<ProposalResponse>  responsesQuery = channelClient.queryByChainCode("mycc", "queryAllCars", null);
//			for (ProposalResponse pres : responsesQuery) {
//				String stringResponse = new String(pres.getChaincodeActionResponsePayload());
//				System.out.println(stringResponse);
//			}

			Thread.sleep(3000);
			String[] args1 = {qyid};
			Collection<ProposalResponse>  responses1Query = channelClient.queryByChainCode("qycc", "queryQyxx", args1);
			for (ProposalResponse pres : responses1Query) {
				Loglw.print("qycc-queryQycc-"+qyid+" Response",pres.getProposalResponse().toString());

				//String stringResponse = new String(pres.getChaincodeActionResponsePayload());
				//Loglw.print("query a result:",stringResponse);
				//System.out.println(stringResponse);
			}



//			Thread.sleep(3000);
//			String[] argsb = {"liuwei-xiaozhuren-ht001"};
//			Collection<ProposalResponse>  responses1Queryb = channelClient.queryByChainCode("qycc", "queryQycc", argsb);
//			for (ProposalResponse pres : responses1Queryb) {
//				String stringResponse = new String(pres.getChaincodeActionResponsePayload());
//				Loglw.print("query b result:",stringResponse);
//				//System.out.println(stringResponse);
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
