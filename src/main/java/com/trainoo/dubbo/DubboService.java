package com.trainoo.dubbo;

public interface DubboService {

	public void sayHello(String name);

	public String returnHello();

	public MsgInfo returnMsgInfo(MsgInfo info);
}
