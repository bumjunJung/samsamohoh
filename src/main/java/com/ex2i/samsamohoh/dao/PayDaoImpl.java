package com.ex2i.samsamohoh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PayDaoImpl implements PayDao {
	
	@Autowired
	private SqlSessionTemplate sql;

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getDataList(Map<String, Object> params) throws SQLException {
		List<Map> list = sql.selectList("pay.getPayDataList", params);
		return list;
	}

	

}
