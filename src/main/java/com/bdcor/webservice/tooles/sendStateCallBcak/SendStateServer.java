package com.bdcor.webservice.tooles.sendStateCallBcak;

import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdcor.webservice.tooles.sendStateCallBcak.thrift.SendStateService;
import com.bdcore.webservice.client.config.ClientConfig;

public class SendStateServer {
	Logger log = LoggerFactory.getLogger(SendStateServer.class);

	public static final int SERVER_PORT = 23014;

	TServer server = null;
	SendStatusServerRegisterTools registertoole = null;
	
	private String ip;
	private String port;
	
	public SendStateServer(ClientConfig config){
		this.registertoole = new SendStatusServerRegisterTools(config);
		this.ip = config.get(ClientConfig.SEND_STATUS_SERVER_IP);
		this.port = config.get(ClientConfig.SEND_STATUS_SERVER_PORT);
		
		if(StringUtils.isEmpty(this.ip)){
			throw new RuntimeException("短信发送状态的服务的ip不能为空");
		}
		if(StringUtils.isEmpty(this.port)){
			throw new RuntimeException("短信发送状态的服务的端口号不能为空");
		}
	}
	
	public void startServer() {
		if(server==null){
			log.info("启动短信发送状态服务。端口号:"+this.port);
	    	
	    	new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						SendStateServer.this.registerServer();
						SendStateService.Processor<SendStateService.Iface> tprocessor = new SendStateService.Processor<SendStateService.Iface>(new SendStateServiceImpl());

					      TServerSocket serverTransport = new TServerSocket(Integer.parseInt(SendStateServer.this.port));
					      TServer.Args tArgs = new TServer.Args(serverTransport);
					      tArgs.processor(tprocessor);
					      tArgs.protocolFactory(new TCompactProtocol.Factory());
					      server = new TSimpleServer(tArgs);
					      server.serve();
						 } catch (Exception e) {
							 SendStateServer.this.cancelServer();
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