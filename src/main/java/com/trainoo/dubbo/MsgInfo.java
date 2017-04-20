package com.trainoo.dubbo;

import java.io.Serializable;
import java.util.List;

public class MsgInfo implements Serializable {
	
	/**
	 * @author zhout
	 * @date 2017年4月6日
	 */
	private static final long serialVersionUID = 4573577808814509332L;
	private int id;
	private String name;
	private List<String> msgs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<String> msgs) {
		this.msgs = msgs;
	}
}
