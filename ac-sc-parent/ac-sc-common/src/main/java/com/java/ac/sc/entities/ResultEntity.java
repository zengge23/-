package com.java.ac.sc.entities;

public class ResultEntity<T> {
	
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";
	public static final String NO_MSG = "NO_MSG";
	public static final String NO_DATA = "NO_DATA";
	
	private String result;
	private String message = NO_MSG;
	private T dataT;
	public ResultEntity() {
		super();
	}
	public ResultEntity(String result, String message, T dataT) {
		super();
		this.result = result;
		this.message = message;
		this.dataT = dataT;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getDataT() {
		return dataT;
	}
	public void setDataT(T dataT) {
		this.dataT = dataT;
	}

	
}
