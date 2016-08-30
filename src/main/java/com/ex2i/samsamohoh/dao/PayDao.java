package com.ex2i.samsamohoh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PayDao {
	@SuppressWarnings("rawtypes")
	public List<Map> getDataList(Map<String, Object> params)throws SQLException;
}
