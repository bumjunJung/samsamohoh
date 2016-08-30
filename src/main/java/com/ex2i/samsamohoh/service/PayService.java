package com.ex2i.samsamohoh.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PayService {
	@SuppressWarnings("rawtypes")
	public List<Map> getDataList(Map<String, Object> params)throws SQLException;
}
