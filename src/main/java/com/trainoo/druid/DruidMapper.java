package com.trainoo.druid;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface DruidMapper {

	int queryTest();

	List<Map<Integer, String>> queryDetail();

	int insertData(String name);

	int deleteData();

}
