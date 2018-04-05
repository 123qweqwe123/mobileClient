package com.bdcore.webservice.client.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class ClientConfig {

	
	public static final String MSG_SERVER_IP = "msgSIp";
	
	
	public static final String MSG_SERVER_PORT = "msgSPort";
	
	public static final String MSG_SERVER_CONTEXT = "myapps";
	
	public static final String MSG_SIGN = "msgSign";
	
	/**
	 * 短信回复ip
	 */
	public static final String REPLY_SERVER_IP = "replyIp";
	
	/**
	 * 短信回复端口
	 */
	public static final String REPLY_SERVER_PORT = "replyPort";
	//public static final String REPLY_IS_NEED = "";
	/**
	 * 短信发送状态ip
	 */
	public static final String SEND_STATUS_SERVER_IP = "sendStatusIp";
	
	/**
	 * 短信发送状态端口
	 */
	public static final String SEND_STATUS_SERVER_PORT = "sendStatusPort";
	
	/**
	 * 使用那个短信服务商发送短信
	 */
	public static final String SERVER_CHANNEL = "SChannel";
	
	public static final String SERVER_CHANNEL_DEFAULT_VALUE = "01";
	
	public static final String MSG_SEND_SUBCODE = "subCode";
	
	/**
	 * 使用短信发送方的 项目编号
	 */
	public static final String PROJ_ID = "projId";
	
	
	private Map<String,String> config = new HashMap<String,String>();
	public ClientConfig(){
		config.put(SERVER_CHANNEL, SERVER_CHANNEL_DEFAULT_VALUE);
	}
	
	public void put(String key,String value){
		config.put(key, value);
	}
	public String get(String key){
		return config.get(key);
	}
	
	public void check(){
		if(StringUtils.isBlank(config.get(MSG_SERVER_IP))){
			throw new RuntimeException("短信服务的ip不能为空");
		}
		if(StringUtils.isBlank(config.get(MSG_SERVER_PORT))){
			throw new RuntimeException("短信服务的端口号不能为空");
		}
		if(StringUtils.isBlank(config.get(SEND_STATUS_SERVER_IP))){
			throw new RuntimeException("短信发送状态服务的ip不能为空");
		}
		if(StringUtils.isBlank(config.get(SEND_STATUS_SERVER_PORT))){
			throw new RuntimeException("短信发送状态服务的端口号不能为空");
		}

		if(StringUtils.isBlank(config.get(PROJ_ID))){
			throw new RuntimeException("调用端的项目编号不能为空");
		}
//		if(StringUtils.isBlank(config.get(MSG_SEND_SUBCODE))){
//			throw new RuntimeException("调用端设置的扩展小号属性不能为空");
//		}
	}

	public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof ClientConfig) {
        	ClientConfig c = (ClientConfig) anObject;
            if(get(MSG_SERVER_IP).equals(c.get(MSG_SERVER_IP))
            		&& get(MSG_SERVER_PORT).equals(c.get(MSG_SERVER_PORT))){
            	if(get(REPLY_SERVER_IP)==null&&c.get(REPLY_SERVER_IP)==null 
            			&&get(REPLY_SERVER_PORT)==null&&c.get(REPLY_SERVER_PORT)==null){
            		return true;
            	}
            	if(get(REPLY_SERVER_IP)!=null && get(REPLY_SERVER_IP).equals(c.get(REPLY_SERVER_IP))
            		&&get(REPLY_SERVER_PORT)!=null&& get(REPLY_SERVER_PORT).equals(c.get(REPLY_SERVER_PORT))){
            	return true;
            	}
            }
        }
        return false;
    }
}
