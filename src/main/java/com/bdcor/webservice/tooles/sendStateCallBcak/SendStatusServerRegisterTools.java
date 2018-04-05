package com.bdcor.webservice.tooles.sendStateCallBcak;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.bdcore.webservice.client.config.ClientConfig;

public class SendStatusServerRegisterTools {

	private String registerPath = "mobileMsg/registerSendStatusServer";
	private String cancelPath = "mobileMsg/cancelSendStatusServer";
	ClientConfig config ;
	Client client;
	String url;
	public SendStatusServerRegisterTools(ClientConfig config){
		this.config = config;
		this.client = ClientBuilder.newClient();
		
		url = "http://"+config.get(ClientConfig.MSG_SERVER_IP)
				+":"+config.get(ClientConfig.MSG_SERVER_PORT)
				+"/"+ClientConfig.MSG_SERVER_CONTEXT;
	}

	
	public String registerServer(String ip,
			String port){
		
		
		WebTarget target = client.target(url);
		
		String result = target.path(registerPath)
				.queryParam("proid", config.get(ClientConfig.PROJ_ID))
				.queryParam("ip", ip)
				.queryParam("port", port)
				
				
		.request().get(String.class);
		return result;
	}
	
	public String cancelServer(String ip,
			String port){
		
		
		WebTarget target = client.target(url);
		
		String result = target.path(cancelPath)
				.queryParam("proid", config.get(ClientConfig.PROJ_ID))
				.queryParam("ip", ip)
				.queryParam("port", port)
				
				
		.request().get(String.class);
		return result;
	}
	
}
