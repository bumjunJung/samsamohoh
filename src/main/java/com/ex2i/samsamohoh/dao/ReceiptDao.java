package com.ex2i.samsamohoh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ex2i.samsamohoh.vo.MemberVO;
import com.ex2i.samsamohoh.vo.PayVO;
import com.ex2i.samsamohoh.vo.RestaurantVO;


public interface ReceiptDao {
	@SuppressWarnings("rawtypes")
	public List<Map> getFamilyList(Map<String, Object> params) throws SQLException;
	public List<Map<String, Object>> getParticipantNames(Map<String, Object> params) throws SQLException;
	public int getFamilyListTotalCnt(Map<String, Object> params) throws SQLException;
	public int updateReceiptType(Map<String, String> params) throws SQLException;
	public int deleteReceipt(Map<String, Object> params) throws SQLException;
	public int deletePayMember(int seq) throws SQLException;
	public List<Map<String, Object>> getFamilyInfo(Map<String, String> params) throws SQLException;
	public MemberVO  findeMemberInfo(Map<String, Object> params) throws SQLException;
    public void insertReceipt(Map<String, Object> params, List<String> list) throws SQLException;
    public int getPaySeq() throws SQLException;
    public void insertPayMember(Map<String, Object> param)  throws SQLException;
    public RestaurantVO findeRestaurantInfo(Map<String, Object> params) throws SQLException;
    public int insertCheck(Map<String, Object> params) throws SQLException;
    public PayVO getReceiptInfo(Map<String, Object> params) throws SQLException;
	public List<Map<String, Object>> getReceiptMember(Map<String, Object> params) throws SQLException;
	public void modifyReceipt(Map<String, Object> params, List<String> list) throws SQLException;
}
