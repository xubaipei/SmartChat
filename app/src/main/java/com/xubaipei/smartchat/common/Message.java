package com.xubaipei.smartchat.common;

public class Message implements java.io.Serializable{

	private String id;
	private String mesType;
	private String sender;
	private String getter;
	private String con;
	private String sendTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public Message(){

	}

	public Message(String id, String mesType, String sender, String getter, String con, String sendTime) {
		this.id = id;
		this.mesType = mesType;
		this.sender = sender;
		this.getter = getter;
		this.con = con;
		this.sendTime = sendTime;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}
}
