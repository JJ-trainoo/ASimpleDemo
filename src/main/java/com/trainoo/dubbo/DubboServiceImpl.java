package com.trainoo.dubbo;

public class DubboServiceImpl implements DubboService {

	@Override
	public void sayHello(String name) {
		System.out.println("hello " + name);  
	}

	@Override
	public String returnHello() {
		return "hello world!";
	}

	@Override
	public MsgInfo returnMsgInfo(MsgInfo info) {
		info.getMsgs().add("处理完毕");  
	    return info;
	}

}
