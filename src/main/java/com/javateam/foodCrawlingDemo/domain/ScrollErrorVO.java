package com.javateam.foodCrawlingDemo.domain;

import java.io.Serializable;

public class ScrollErrorVO implements Serializable {
	
	private int id ;
	private String msg ;
	
	public ScrollErrorVO(int id, String msg) {
		this.id = id;
		this.msg = msg;
	}

	public int getId() {
		return id;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public String toString() {
		return "ScrollErrorVO [id=" + id + ", msg=" + msg + "]";
	}

	

}
