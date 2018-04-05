package com.bdcore.webservice.client.bean;

public class MsgOfRec {
	private String tel;
	
	private String content;
	
	private String dateTime;

	public MsgOfRec(String tel,String content,String datetime){
		this.tel = tel;
		this.content = content;
		this.dateTime = datetime;
	}
	
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

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	public String toString(){
		return this.tel+"|"+this.content+"|"+this.dateTime;
	}
	
}
