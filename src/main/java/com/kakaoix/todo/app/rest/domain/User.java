package com.kakaoix.todo.app.rest.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 *
 */
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int idNo ;

	private String userName;  
	
	public User(int idNo) {
		this.idNo = idNo;
	}
	
	public User(int idNo, String userName) {
		this.idNo = idNo;
		this.userName =  userName;
	}

	public int getIdNo() { return idNo; }
	 
	public void setIdNo(int idNo) { this.idNo = idNo; }
	 
	public String getUserName() { return userName; }
	 
	public void setUserName(String userName) { this.userName = userName; }
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
