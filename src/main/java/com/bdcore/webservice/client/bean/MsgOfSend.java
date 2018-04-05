package com.bdcore.webservice.client.bean;

public class MsgOfSend {

	private String tel;
	
	private String content;
	
	private String linkId;

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String toString(){
		return "!"+tel+"!"+content+"!";
	}
}
