package com.matheusol.cursomc.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int status;
	private String msg;
	private Long timeStampLong;
	
	public StandardError(int status, String msg, Long timeStampLong) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStampLong = timeStampLong;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getTimeStampLong() {
		return timeStampLong;
	}
	public void setTimeStampLong(Long timeStampLong) {
		this.timeStampLong = timeStampLong;
	}
	
	
}
