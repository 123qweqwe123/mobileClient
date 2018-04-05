package com.bdcore.webservice.client;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdcor.webservice.tooles.replyCallBcak.ReplayServer;
import com.bdcor.webservice.tooles.replyCallBcak.ReplyService;
import com.bdcor.webservice.tooles.sendStateCallBcak.SendStateServer;
import com.bdcor.webservice.tooles.sendStateCallBcak.SendStateServiceImpl;
import com.bdcore.webservice.client.bean.MsgOfRec;
import com.bdcore.webservice.client.bean.MsgOfSend;
import com.bdcore.webservice.client.config.ClientConfig;
import com.bdcore.webservice.client.tool.ReplyMsgTools;
import com.bdcore.webservice.client.tool.SendMsgTools;
import com.bdcore.webservice.client.tool.SendStateTools;

public class MsgClient {
	Logger log = LoggerFactory.getLogger(MsgClient.class);
	ClientConfig config = new ClientConfig();
	SendMsgTools sendTools = null;
	ReplyMsgTools replyTools = null;
	SendStateTools sendStateTools = null;
	
	public MsgClient(ClientConfig config){
		config.check();
		this.replyTools = new ReplyMsgTools(config);
		this.sendStateTools = new SendStateTools(config);
		this.sendTools = new SendMsgTools(config);
		this.config = config;
	}
	
	/**
	 * 设置为单例
	 * @param config
	 * @return
	 */
	/*
	
	private static List<MsgClient> msgClientList;
	public synchronized static MsgClient getMsgClient(ClientConfig config){
		if(msgClientList==null)msgClientList=new ArrayList<MsgClient>();
		for(MsgClient m : msgClientList){
			if(m.config.equals(config)){
				return m;
			}
		}	
		MsgClient msgClient=new MsgClient(config);
		msgClientList.add(msgClient);
		return msgClient;
	}

	*/
	
	ReplayServer replayS = null;
	SendStateServer sendStatusS = null;
	public void start(){
		replayS = new ReplayServer(config);
		replayS.startServer();
		
		sendStatusS = new SendStateServer(config);
		sendStatusS.startServer();
	}
	
	public void stop(){
		if(replayS!=null){
			replayS.stopServer();
		}
		
		if(sendStatusS!=null){
			sendStatusS.stopServer();
		}
	}
	
	
	/**
	 * 发送单条
	 * @param msg
	 * @param callback
	 * @throws Exception
	 */
	public void send(MsgOfSend msg,SendResultCallback callback){
		try{
			String result = sendTools.send(msg);
			int reslutInt = 1;
			if(!result.equals("成功")){
				reslutInt = 0;
			}
			log.info("send:"+reslutInt+":"+msg);
			callback.callback(reslutInt, "", msg);
		}catch(Exception e){
			log.error("服务调用发生错误",e);
			throw new RuntimeException("调用发送短信服务发生错误");
		}
		
	}
	
	/**
	 * 发送多条
	 * @param msg
	 * @param callback
	 * @throws Exception
	 */
	public void send(List<MsgOfSend> msgs,SendResultCallback callback){
		for(MsgOfSend msg:msgs){
			this.send(msg, callback);
		}
	}
	
	
	/**
	 * 注册接受短信回调类
	 * @param invoke
	 */
	public void recInvoke(RecInvoke invoke){
		ReplyService.regiestInvoke(invoke);
	}
	
	/**
	 * 注册发送状态回调类
	 * @param invoke
	 */
	public void sendStateInvoke(SendStateInvoke invoke){
		SendStateServiceImpl.regiestInvoke(invoke);
	}
	
	/**
	 * 读取回复短信
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<MsgOfRec> rec(Date startDate,Date endDate){
		
		return null;
	}
	
	
	/**
	 * 客户端不用调用。
	 * 
	 * 短信发送结果
	 * 
	 * ver - PlatForm=C053
09:48:40.209 [http-nio-8983-exec-3] INFO  c.b.mobile.service.SendResultServer - FUnikey=61A631400B76DB240000000013874436409
09:48:40.210 [http-nio-8983-exec-3] INFO  c.b.mobile.service.SendResultServer - FOrgAddr=10657568110177167
09:48:40.210 [http-nio-8983-exec-3] INFO  c.b.mobile.service.SendResultServer - FDestAddr=13874436409
09:48:40.210 [http-nio-8983-exec-3] INFO  c.b.mobile.service.SendResultServer - FSubmitTime=20160603093500
09:48:40.210 [http-nio-8983-exec-3] INFO  c.b.mobile.service.SendResultServer - FFeeTerminal=13874436409
09:48:40.210 [http-nio-8983-exec-3] INFO  c.b.mobile.service.SendResultServer - FServiceUPID=121124
09:48:40.210 [http-nio-8983-exec-3] INFO  c.b.mobile.service.SendResultServer - FReportCode=DELIVRD
09:48:40.210 [http-nio-8983-exec-3] INFO  c.b.mobile.service.SendResultServer - FAckStatus=0
09:48:40.210 [http-nio-8983-exec-3] INFO  c.b.mobile.service.SendResultServer - FLinkID=20160603093538457254
	 * 
	 * @param linkId
	 * @param reportCode
	 */
	public String sendStateRegister(
			String FUnikey,
			String FOrgAddr,
			String FDestAddr,
			String FSubmitTime,
			String FFeeTerminal,
			String FServiceUPID,
			String reportCode,
			String FAckStatus,
			String linkId
			){
		return sendStateTools.sendState(
				FUnikey,
				FOrgAddr,
				FDestAddr,
				FSubmitTime,
				FFeeTerminal,
				FServiceUPID,
				reportCode,
				FAckStatus,
				linkId);
	}
	
	/**
	 * 客户端不用调用。
	 * 
	 * 短信回复
	 * 
	 * @param channel
	 * @param tel
	 * @param content
	 * @param time
	 */
	public String replyMsgRegister(String channel,String tel,String content,String time){
		return replyTools.replyOneMsg(channel, tel, content, time);
	}
	
}
