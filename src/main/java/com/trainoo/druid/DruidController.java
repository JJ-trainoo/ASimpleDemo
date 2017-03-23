package com.trainoo.druid;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SSM整合测试
 *
 * @author zhout
 * @date 2017年3月23日
 */
@Controller
@RequestMapping("/druidTest")
public class DruidController {

	@Autowired
	private DruidService druidService;
	
	@RequestMapping("/query.do")
	@ResponseBody
	public String queryTest(HttpServletRequest request){
		int count = druidService.queryTest();
		return "{\"count\":\""+ count +"\"}";
	}
	
	@RequestMapping("/queryDetail.do")
	@ResponseBody
	public List<Map<Integer, String>> queryDetail(HttpServletRequest request){
		List<Map<Integer, String>> list = druidService.queryDetail();
		return list;
	}
	
	@RequestMapping("/insertData.do")
	@ResponseBody
	public String insertData(HttpServletRequest request){
		int count = druidService.insertData();
		return "{\"count\":\"插入数据成功~"+count+"\"}";
	}
	
	@RequestMapping("/deleteData.do")
	@ResponseBody
	public String deleteData(HttpServletRequest request){
		int count = druidService.deleteData();
		return "{\"count\":\"删除数据成功~"+count+"\"}";
	}
}
