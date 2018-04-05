package com.bdcor.webservice.tooles.sendStateCallBcak;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdcor.webservice.tooles.sendStateCallBcak.thrift.SendStateService;
import com.bdcore.webservice.client.RecInvoke;
import com.bdcore.webservice.client.SendStateInvoke;


public class SendStateServiceImpl  implements SendStateService.Iface{
	static Logger log = LoggerFactory.getLogger(SendStateServiceImpl.class);
	private static List<SendStateInvoke> sendStateInvokes = new ArrayList<SendStateInvoke>();
	
	public SendStateServiceImpl() {
		  
	}
	
	public static void regiestInvoke(SendStateInvoke invoke){
		log.info("注册发送状态回调类:"+invoke);
		sendStateInvokes.add(invoke);
	}

	@Override
	public boolean sendState(String linkId, String reportCode) throws TException {
		for(SendStateInvoke in:sendStateInvokes){
			in.invoke(linkId,reportCode);
		}
		return false;
	}

}