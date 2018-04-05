package com.bdcore.webservice.client;

import com.bdcore.webservice.client.bean.MsgOfSend;

public interface SendResultCallback {

	
	
	/**
	 * 发送结果回调。
	 * @param result       结果            1：成功             0：失败
	 * @param resultMsg    结果说明。失败时，会用到
	 * @param msg          本次发送的短信
	 */
	void callback(int result,String resultMsg,MsgOfSend msg);
}
