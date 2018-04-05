package com.bdcor.mobile.client;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bdcore.webservice.client.MsgClient;
import com.bdcore.webservice.client.RecInvoke;
import com.bdcore.webservice.client.bean.MsgOfRec;
import com.bdcore.webservice.client.config.ClientConfig;

public class RecTest {

	
	MsgClient client = null;
		//@Before
		public void bef(){
			
			
		}
		
		//@Test
		public void testSendOne() {
			client.recInvoke(new RecInvoke() {
				
				@Override
				public void invoke(MsgOfRec rec) {
					System.out.println("接受到短信:"+rec);
				}
			});
		}
		
		
		//@Test
		public void testSendOne2() {
			
			
			client.replyMsgRegister("001", "133xxxxxx", "不在接受短信", "2015-01-03");
	}

	//@After
	public void after() {
		client.stop();
	}
	
	
	public static void main(String[] a){
		ClientConfig config = new ClientConfig();
		//测试
		//config.put(ClientConfig.MSG_SERVER_IP, "172.31.201.181");
		//正式
		config.put(ClientConfig.MSG_SERVER_IP, "10.24.10.191");
		//config.put(ClientConfig.MSG_SERVER_IP, "localhost");
		config.put(ClientConfig.MSG_SERVER_PORT, "3212");
		config.put(ClientConfig.PROJ_ID, "01");
		
		config.put(ClientConfig.REPLY_SERVER_IP, "172.31.131.144");
		config.put(ClientConfig.REPLY_SERVER_PORT, "12302");
		
		
		
		MsgClient client = new MsgClient(config);
		client.start();
		
		client.recInvoke(new RecInvoke() {
			
			@Override
			public void invoke(MsgOfRec rec) {
				System.out.println("接受到短信:"+rec);
			}
		});
		
		
		client.replyMsgRegister("001", "133xxxxxx", "不在接受短信", "2015-01-03");
	
		
		client.stop();
	}

}
