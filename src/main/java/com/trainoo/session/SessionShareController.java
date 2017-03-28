package com.trainoo.session;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试session共享
 *
 * @author zhout
 * @date 2017年3月27日
 */
@Controller
@RequestMapping("/session")
public class SessionShareController {

	@RequestMapping("/addSession.do")
	@ResponseBody
	public String addSession(HttpServletRequest request){
		System.out.println("--------begin----------");
		request.getSession().setAttribute("session.test", "testting session share~");
		return "{\"count\":\"0\"}";
	}
	
	@RequestMapping("/getSession.do")
	@ResponseBody
	public String getSession(HttpServletRequest request){
		String str = (String) request.getSession().getAttribute("session.test");
		System.out.println("session:"+ str);
		return "{\"count\":\""+str+"\"}";
	}
}
