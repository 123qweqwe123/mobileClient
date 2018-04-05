package com.bdcor.webservice.tooles.replyCallBcak;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdcor.webservice.tooles.replyCallBcak.thrift.IReplyService;
import com.bdcor.webservice.tooles.replyCallBcak.thrift.ReplyMsg;
import com.bdcore.webservice.client.RecInvoke;
import com.bdcore.webservice.client.bean.MsgOfRec;


public class ReplyService implements IReplyService.Iface{
	static Logger log = LoggerFactory.getLogger(ReplyService.class);
	private static List<RecInvoke> recInvokes = new ArrayList<RecInvoke>();
	
  public ReplyService() {
	  
  }
  
  public static void regiestInvoke(RecInvoke invoke){
	  log.info("注册回调类:"+invoke);
	  recInvokes.add(invoke);
  }

	@Override
	public boolean replyOneMsg(ReplyMsg arg0) throws TException {
		MsgOfRec rec = new MsgOfRec(arg0.getTel(),arg0.getContent(),arg0.getTime());
		
		for(RecInvoke in:recInvokes){
			in.invoke(rec);
		}
		return false;
	}


}