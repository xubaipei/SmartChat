package com.xubaipei.smartchat.common;

public class User implements java.io.Serializable
{
	public User(){

	}
	private String userId;
	private String passwd;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public User(String userId, String passwd) {
		super();
		this.userId = userId;
		this.passwd = passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
