package com.bdcor.mobile.client;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bdcore.webservice.client.MsgClient;
import com.bdcore.webservice.client.SendResultCallback;
import com.bdcore.webservice.client.bean.MsgOfSend;
import com.bdcore.webservice.client.config.ClientConfig;

public class SendTest {

	@Test
	public void testSendOne() {
		ClientConfig config = new ClientConfig();
		//测试
		//config.put(ClientConfig.MSG_SERVER_IP, "172.31.201.181");
		//正式
		config.put(ClientConfig.MSG_SERVER_IP, "10.24.10.191");
		
		//config.put(ClientConfig.MSG_SERVER_IP, "172.31.131.144");
		
		config.put(ClientConfig.MSG_SERVER_PORT, "3212");
		config.put(ClientConfig.PROJ_ID, "01");
		config.put(ClientConfig.MSG_SIGN, "");
		
		config.put(ClientConfig.SEND_STATUS_SERVER_IP, "172.31.131.144");
		config.put(ClientConfig.SEND_STATUS_SERVER_PORT, "12302");
		
		config.put(ClientConfig.REPLY_SERVER_IP, "172.31.131.144");
		config.put(ClientConfig.REPLY_SERVER_PORT, "12301");
		
		MsgClient client = new MsgClient(config);
final long start = System.currentTimeMillis();
		MsgOfSend msg = new MsgOfSend();
		//msg.setTel("18551633281");//瑞伟
		//msg.setTel("15811120896");//罗丹
		//msg.setTel("18610001946");
		//msg.setContent("，早上好。祝您身体健康！一切顺利！");
			
//		msg.setTel("15811120896");
//		msg.setContent("爱惜家人和自己，强身健体满生机，春夏秋冬传爱意.");
//		msg.setLinkId("0019");
		
		msg.setTel("18611685308");
		msg.setContent("爱惜家人和自己.");
		msg.setLinkId("0009");
		
//		msg.setTel("15304787308");
//		msg.setContent("张战荣先生，早上好。祝您身体健康！一切顺利！");
//		msg.setLinkId("0002");
//		
//		msg.setTel("15091224666");
//		msg.setContent("白靖花女士，早上好。祝您身体健康！一切顺利！");
//		msg.setLinkId("0003");
		
//		msg.setTel("18601390543");
//		msg.setContent("肖倩女士，早上好。祝您身体健康！一切顺利！");
//		msg.setLinkId("0004");
		
		
		client.send(msg, new SendResultCallback() {
			@Override
			public void callback(int result, String resultMsg, MsgOfSend msg) {
System.out.println("短信发送耗时："+(System.currentTimeMillis()-start));
				if(result==1){
					System.out.println("发送成功");
				}else{
					System.out.println("发送失败");
				}
			}
		});
	
	}

	//@Test
	public void testSendMul() {
		ClientConfig config = new ClientConfig();
		config.put(ClientConfig.MSG_SERVER_IP, "localhost");
		config.put(ClientConfig.MSG_SERVER_PORT, "32121");
		config.put(ClientConfig.PROJ_ID, "01");
		
		MsgClient client = new MsgClient(config);

		MsgOfSend msg = new MsgOfSend();
		msg.setTel("18611685308");
		msg.setContent("请按时吃药");
		
		List<MsgOfSend> msgs = new ArrayList<MsgOfSend>();
		msgs.add(msg);
		
		client.send(msgs, new SendResultCallback() {
			@Override
			public void callback(int result, String resultMsg, MsgOfSend msg) {
				if(result==1){
					System.out.println("发送成功");
				}else{
					System.out.println("发送失败");
				}
			}
		});
	
	}

}
