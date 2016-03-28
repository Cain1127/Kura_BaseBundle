/**
 * 
 */
package com.accenture.iot.agent.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.protocol.HTTP;
import com.google.gson.Gson;
/**
 * @author yong.du
 *
 */
public class IotAgentServiceImp implements IIotAgentService {
	private String mToken = "Bearer ";
	private final String mUrlBase = "https://dcpp-ecosystem-platform.cpaas-accenture.com/";
	private final String mPathInfo = "t/dcpp107.acn/";

	public boolean login() {
		// TODO Auto-generated method stub
		System.out.print("IotAgentServiceImp login");
		HashMap<String, Object> hds = new HashMap<String, Object>();
		hds.put(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");

		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("grant_type", "client_credentials");
		params.put("client_id", "fM6s3LvPZh9f9ExLButJaTj5ia0a");
		params.put("client_secret", "2BCV3FCEiqYhlGkUqUowCQK25zEa");

		// Get login
		RestfulClient.sendRestReq("POST", params, mUrlBase + "oauth2/token", hds, new IHttpListener() {

			//@Override
			public void onReqCompleted(String result) {
				// TODO Auto-generated method stub
				System.out.println("Get token resp:" + result);
				Gson gson = new Gson();
				//Type type = new TypeToken<B>() {  
		        //}.getType();
				Map<String, Object> jsonObj = gson.fromJson(result, Map.class);
				mToken = "Bearer " + jsonObj.get("access_token");
				//System.out.println("Get token token is:" + mToken);
			}

			//@Override
			public void onReqError(String resutl) {
				// TODO Auto-generated method stub
				System.out.println("Get token error:" + resutl);
			}
		});

		// /platform/enabler/device-manager/DeviceCommand/retrieve/1.0.0
		hds.put(HTTP.CONTENT_TYPE, "application/json");
		hds.put("tenant ", "107");
		hds.put("enterprise", "1");
		hds.put("Authorization", mToken);

		params.clear();
		params.put("deviceId", "Device_aaron10");
		params.put("deviceType", "AliGateway");
		params.put("transactionId", "tx-789");

		RestfulClient.sendRestReq("POST", params,
				mUrlBase + mPathInfo +"platform/enabler/device-manager/DeviceCommand/retrieve/1.0.0", hds,
				new IHttpListener() {

					//@Override
					public void onReqCompleted(String resutl) {
						// TODO Auto-generated method stub
						System.out.println("Fetch commands resp:" + resutl);
					}

					//@Override
					public void onReqError(String resutl) {
						// TODO Auto-generated method stub
						System.out.println("Fetch commands error:" + resutl);
					}

				});
		
		
		// /platform/enabler/crsm/subscription/retrieve/1.0.0
		hds.put(HTTP.CONTENT_TYPE, "application/json");
		hds.put("Authorization", mToken);

		params.clear();
		params.put("transactionId", "tx-789");
		params.put("tenant", "107");

		RestfulClient.sendRestReq("POST", params,
				mUrlBase + mPathInfo +"/platform/enabler/crsm/subscription/retrieve/1.0.0", hds,
				new IHttpListener() {

					//@Override
					public void onReqCompleted(String resutl) {
						// TODO Auto-generated method stub
						System.out.println("Fetch subscriptions resp:" + resutl);
					}

					//@Override
					public void onReqError(String resutl) {
						// TODO Auto-generated method stub
						System.out.println("Fetch subscriptions error:" + resutl);
					}

				});

		return true;
	}

	@Override
	public float getTemp() {
		// TODO Auto-generated method stub
		System.out.print("IotAgentServiceImp getTemp");
		return 0;
	}

	@Override
	public void sendTemp(double tmp, double hum) {
		
		System.out.print("IotAgentServiceImp sendTemp");
		// TODO Auto-generated method stub
		
		// /platform/enabler/dcm/monitoringDataCollection/send/1.0.0
		HashMap<String, Object> hds = new HashMap<String, Object>();
		HashMap<String, Object> params = new HashMap<String, Object>();

		hds.put(HTTP.CONTENT_TYPE, "application/json");
		hds.put("tenantId ", "107");
		//hds.put("enterprise", "1");
		hds.put("Authorization", mToken);
		hds.put("parentBusinessUserId", "1");
		hds.put("device-type", "AliGateway");
		hds.put("device-id", "Device_aaron10");
		hds.put("transaction-id", "tx-789");

		params.clear();
		params.put("engineId", "test-12356");
		params.put("date", "2016-1-14T17:50:00.000+0100");
		params.put("temperature", tmp);
		params.put("humidity", hum);

		RestfulClient.sendRestReq("POST", params,
				mUrlBase + mPathInfo +"/platform/enabler/dcm/monitoringDataCollection/send/1.0.0", hds,
				new IHttpListener() {

					//@Override
					public void onReqCompleted(String resutl) {
						// TODO Auto-generated method stub
						System.out.println("Send temp resp:" + resutl);
					}

					//@Override
					public void onReqError(String resutl) {
						// TODO Auto-generated method stub
						System.out.println("Send temp error:" + resutl);
					}

				});
		
		
		// /platform/enabler/device-manager/DeviceCommand/complete/1.0.0
		
		hds.put(HTTP.CONTENT_TYPE, "application/json");
		hds.put("tenant", "107");
		hds.put("enterprise", "1");
		hds.put("Authorization", mToken);

		params.clear();
		params.put("deviceId", "Device_aaron10");
		params.put("commandTransactionId", "DM_COMMAND_TRANSACTION_1072");
		params.put("commandResult", "COMPLETED");
		params.put("deviceType", "AliGateway");
		params.put("transactionId", "tx-789");
		
		System.out.println("Going to send temperature:"+tmp);

		RestfulClient.sendRestReq("POST", params,
				mUrlBase + mPathInfo +"/platform/enabler/device-manager/DeviceCommand/complete/1.0.0", hds,
				new IHttpListener() {

					//@Override
					public void onReqCompleted(String resutl) {
						// TODO Auto-generated method stub
						System.out.println("Complete command resp:" + resutl);
					}

					//@Override
					public void onReqError(String resutl) {
						// TODO Auto-generated method stub
						System.out.println("Complete command error:" + resutl);
					}

				});
	}

}
