package com.trainoo.druid;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class DruidServiceImpl implements DruidService {

	@Resource
	private DruidMapper mapper;
	
	@Override
	public int queryTest() {
		return mapper.queryTest();
	}

	@Override
	public List<Map<Integer, String>> queryDetail() {
		return mapper.queryDetail();
	}

	@Override
	public int insertData() {
		String name = "Druid_" + new Date().getTime();
		return mapper.insertData(name);
	}

	@Override
	public int deleteData() {
		return mapper.deleteData();
	}

}
