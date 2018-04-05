## 整体说明
    短信客户端通过http请求，将短信内容等数据提供给短信服务端，由短信
    服务端实现短信的统一发送，并返回回执信息给客户端。
    
## 使用方式

    <dependency>
                 <groupId>com.bdcor</groupId>
                 <artifactId>mobileClient</artifactId>
                 <version>0.0.1-SNAPSHOT</version>
     </dependency>

     
     ClientConfig类是配置信息类,需要配置短信发送服务端的IP、端口，短信回执服务的IP、端口
     短信回复服务的IP、端口

     ClientConfig config = new ClientConfig();
        config.put(ClientConfig.MSG_SERVER_IP, serverIp);
        config.put(ClientConfig.MSG_SERVER_PORT, serverPort);
        config.put(ClientConfig.PROJ_ID, pro) // 项目编号(主要用以区分项目签名,默认值为Peace3项目
                                              // 的004,);
        config.put(ClientConfig.REPLY_SERVER_IP, replyIp);
        config.put(ClientConfig.REPLY_SERVER_PORT, replyPort);
        config.put(ClientConfig.SEND_STATUS_SERVER_IP, msg_send_status_ip);
        config.put(ClientConfig.SEND_STATUS_SERVER_PORT, msg_send_status_port);
        MsgClient client = new MsgClient(config); 
        client.start();
        // 短信信息封装类
        MsgOfSend msg = new MsgOfSend();
        msg.setLinkId("唯一标识");
        msg.setTel(msgMap.get("手机号"));
        msg.setContent("短信内容");
        client.send(msg, new SendResultCallback() {
            @Override
            public void callback(int result, String resultMsg, MsgOfSend msg) {
                if(result == 1){ // 调用接口成功
                    logger.info("调用短信发送接口成功");
                }
                else{
                    logger.info("调用短信发送接口失败，调用返回值："+result);
                }
            }
        });
    更多参考信息可以参见Peace3项目短信功能代码
## 部署
    
    以jar包形式打包
    随项目启停
    
## 注意事项
    初始化客户端时配置信息不要丢失或错误
    短信内容太长会被截分为两条或以上短信
## 版本说明
    目前可以请求短信发送、获取短信回执、接收短信回复信息
    根据项目编号区分短信签名