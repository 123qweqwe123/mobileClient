package com.bdcor.webservice.tooles.replyCallBcak;

import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdcor.webservice.tooles.replyCallBcak.thrift.IReplyService;
import com.bdcore.webservice.client.config.ClientConfig;

public class ReplayServer {
	Logger log = LoggerFactory.getLogger(ReplayServer.class);

	public static final int SERVER_PORT = 23013;

	TServer server = null;
	ReplyServerRegisterTools registertoole = null;
	
	private String ip;
	private String port;
	
	public ReplayServer(ClientConfig config){
		this.registertoole = new ReplyServerRegisterTools(config);
		this.ip = config.get(ClientConfig.REPLY_SERVER_IP);
		this.port = config.get(ClientConfig.REPLY_SERVER_PORT);
		
		if(StringUtils.isEmpty(this.ip)){
			throw new RuntimeException("回复短信的服务的ip不能为空");
		}
		if(StringUtils.isEmpty(this.port)){
			throw new RuntimeException("回复短信的服务的端口号不能为空");
		}
	}
	
	public void startServer() {
		if(server==null){
			log.info("启动短信回复接受服务。端口号:"+this.port);
	    	
	    	new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						ReplayServer.this.registerServer();
							IReplyService.Processor<IReplyService.Iface> tprocessor = new IReplyService.Processor<IReplyService.Iface>(new ReplyService());

					      TServerSocket serverTransport = new TServerSocket(Integer.parseInt(ReplayServer.this.port));
					      TServer.Args tArgs = new TServer.Args(serverTransport);
					      tArgs.processor(tprocessor);
					      tArgs.protocolFactory(new TCompactProtocol.Factory());
					      server = new TSimpleServer(tArgs);
					      server.serve();
						 } catch (Exception e) {
							 ReplayServer.this.cancelServer();
						    	log.error("",e);
						    } 
				}
	    	}).start();
	    	
		}
    	
    
   
    
  }

	public void stopServer() {
		if (server != null) {
			server.stop();
		}
		this.cancelServer();
	}

	
	private void registerServer(){
			
		log.info("register rec server:"+ip+"  "+port);
		this.registertoole.registerServer(ip,port);
		
	}
	private void cancelServer(){
		log.info("cancel rec server:"+ip+"  "+port);
		this.registertoole.cancelServer(ip, port);
	}
}