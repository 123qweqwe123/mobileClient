package com.bdcore.webservice.client.tool;

import java.text.SimpleDateFormat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import com.bdcore.webservice.client.bean.MsgOfSend;
import com.bdcore.webservice.client.config.ClientConfig;

public class SendMsgTools {
	
	private String path = "mobileMsg/send";
	ClientConfig config ;
	Client client;
	String url;
	public SendMsgTools(ClientConfig config){
		this.config = config;
		this.client = ClientBuilder.newClient();
		
		url = "http://"+config.get(ClientConfig.MSG_SERVER_IP)
				+":"+config.get(ClientConfig.MSG_SERVER_PORT)
				+"/"+ClientConfig.MSG_SERVER_CONTEXT;
	}

	
	public String send(MsgOfSend msg){
		/*
		Form form = new Form();
		form.param("mobileNums", msg.getTel());
		form.param("msg", msg.getContent());
		form.param("sign", config.get(ClientConfig.MSG_SIGN));
		form.param("projNo", config.get(ClientConfig.PROJ_ID));
		form.param("channelNo", config.get(ClientConfig.SERVER_CHANNEL));
		
		WebTarget target = client.target(url);
		String result = target.path(path)
		.request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE),
		        String.class);
*/
		WebTarget target = client.target(url);
		String result = target.path(path)
			.queryParam("mobileNums", msg.getTel())
			.queryParam("msg", msg.getContent())
			.queryParam("linkId", msg.getLinkId())
		.queryParam("sign", config.get(ClientConfig.MSG_SIGN))
		.queryParam("projNo", config.get(ClientConfig.PROJ_ID))
		.queryParam("channelNo", config.get(ClientConfig.SERVER_CHANNEL))
		.queryParam("subCode", config.get(ClientConfig.MSG_SEND_SUBCODE))
			
		.request().get(String.class);
		
		return result;
	}
}
