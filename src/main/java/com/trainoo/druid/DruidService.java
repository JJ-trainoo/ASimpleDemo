package com.trainoo.druid;

import java.util.List;
import java.util.Map;

public interface DruidService {

	int queryTest();

	List<Map<Integer, String>> queryDetail();

	int insertData();

	int deleteData();

}
