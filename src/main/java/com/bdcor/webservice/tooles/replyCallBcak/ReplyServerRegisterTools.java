package com.bdcor.webservice.tooles.replyCallBcak;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.bdcore.webservice.client.config.ClientConfig;

public class ReplyServerRegisterTools {

	private String registerPath = "mobileMsg/registerRecServer";
	private String cancelPath = "mobileMsg/cancelRecServer";
	ClientConfig config ;
	Client client;
	String url;
	public ReplyServerRegisterTools(ClientConfig config){
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
