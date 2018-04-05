package com.bdcore.webservice.client.tool;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import com.bdcore.webservice.client.config.ClientConfig;

public class ReplyMsgTools {

	private String path = "recMsg/recOne";
	ClientConfig config ;
	Client client;
	String url;
	public ReplyMsgTools(ClientConfig config){
		this.config = config;
		this.client = ClientBuilder.newClient();
		
		url = "http://"+config.get(ClientConfig.MSG_SERVER_IP)
				+":"+config.get(ClientConfig.MSG_SERVER_PORT)
				+"/"+ClientConfig.MSG_SERVER_CONTEXT;
	}

	
	public String replyOneMsg(String channel,String tel,
			String content,String time){
		/*
		Form form = new Form();
		form.param("channel", channel);
		form.param("tel", tel);
		form.param("content", content);
		form.param("time", time);
		
		WebTarget target = client.target(url);
		
		String result = target.path(path)
		.request().put(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE),
		        String.class);
		*/
		
		
		WebTarget target = client.target(url);
		
		String result = target.path(path)
				.queryParam("channel", channel)
				.queryParam("tel", tel)
				.queryParam("content", content)
				.queryParam("time", time)
				
		.request().get(String.class);
		return result;
	}
}
