package com.ex2i.samsamohoh.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ex2i.samsamohoh.dao.PayDao;

@Service
public class PayServiceImpl implements PayService {
	
	@Autowired
	private PayDao dao;

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getDataList(Map<String, Object> params) throws SQLException {
		return this.dao.getDataList(params);
	}
	
	
}
