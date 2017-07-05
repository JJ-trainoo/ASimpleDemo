package com.trainoo.druid;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * JUNIT测试service
 * @author zhoutao
 * @date 2017年7月5日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/spring-context.xml")
public class DruidServiceTest {
	
	@Autowired
	private DruidService druidService;
	
	
	@Test
	public void queryDetailTest(){
		System.out.println(druidService);
		List<Map<Integer, String>> queryDetail = druidService.queryDetail();
		Iterator<Map<Integer, String>> ite = queryDetail.iterator();
		while(ite.hasNext()){
			System.out.println(ite.next());
		}
	}

}
