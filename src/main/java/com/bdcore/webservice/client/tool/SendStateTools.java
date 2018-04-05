package com.bdcore.webservice.client.tool;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.bdcore.webservice.client.config.ClientConfig;

public class SendStateTools {

	private String path = "recMsg/sendState";
	ClientConfig config;
	Client client;
	String url;
	
	public SendStateTools(ClientConfig config){
		this.config = config;
		this.client = ClientBuilder.newClient();
		
		url = "http://"+config.get(ClientConfig.MSG_SERVER_IP)
				+":"+config.get(ClientConfig.MSG_SERVER_PORT)
				+"/"+ClientConfig.MSG_SERVER_CONTEXT;
	}

	
	public String sendState(String FUnikey,
			String FOrgAddr,
			String FDestAddr,
			String FSubmitTime,
			String FFeeTerminal,
			String FServiceUPID,
			String reportCode,
			String FAckStatus,
			String linkId
			){
		WebTarget target = client.target(url);
		
		String result = target.path(path)
				.queryParam("FUnikey", FUnikey)
				.queryParam("FOrgAddr", FOrgAddr)
				.queryParam("FDestAddr", FDestAddr)
				.queryParam("FSubmitTime", FSubmitTime)
				.queryParam("FFeeTerminal", FFeeTerminal)
				.queryParam("FServiceUPID", FServiceUPID)
				.queryParam("FAckStatus", FAckStatus)
				.queryParam("linkId", linkId)
				.queryParam("reportCode", reportCode)
		.request().get(String.class);
		return result;
	}
}
