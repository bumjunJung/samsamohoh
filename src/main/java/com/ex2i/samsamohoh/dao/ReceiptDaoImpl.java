package com.ex2i.samsamohoh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ex2i.samsamohoh.vo.MemberVO;
import com.ex2i.samsamohoh.vo.PayVO;
import com.ex2i.samsamohoh.vo.RestaurantVO;


@Repository
public class ReceiptDaoImpl implements ReceiptDao {

	@Autowired
	private SqlSessionTemplate sql;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getFamilyList(Map<String, Object> params) throws SQLException {
		List<Map> list = this.sql.selectList("receipt.getReceiptList",params);
		return list;
	}
	
	@Override
	public int getFamilyListTotalCnt(Map<String, Object> params) throws SQLException {
		int cnt = 0;
		cnt = this.sql.selectOne("receipt.getReceiptListTotalCnt",params);
		return cnt;
	}

	@Override
	public List<Map<String, Object>> getParticipantNames(Map<String, Object> params) throws SQLException {
		List<Map<String, Object>> list = this.sql.selectList("receipt.getParticipantNames",params);
		return list;
	}

	@Override
	public int updateReceiptType(Map<String, String> params) throws SQLException {
		int cnt = 0;
	
		return cnt;
	}

	@Override
	public int deleteReceipt(Map<String, Object> params) throws SQLException {
		int cnt = 0;
		cnt = this.sql.delete("receipt.deleteReceipt",params);
		return cnt;
	}

	@Override
	public List<Map<String, Object>> getFamilyInfo(Map<String, String> params) throws SQLException {
		List<Map<String, Object>> list = this.sql.selectList("receipt.getFamilyInfo",params);
		return list;
	}

	@Override
	public MemberVO findeMemberInfo(Map<String, Object> params) throws SQLException {
		MemberVO memberInfo = this.sql.selectOne("receipt.findeMemberInfo",params);
		return memberInfo;
	}

	@Override
	public RestaurantVO findeRestaurantInfo(Map<String, Object> params) throws SQLException {
		RestaurantVO restaurantInfo = this.sql.selectOne("receipt.findeRestaurantInfo",params);
		return restaurantInfo;
	}

	@Override
	public void insertReceipt(Map<String, Object> params, List<String> list) throws SQLException {
		sql.insert("receipt.insertRecipt", params);
	}

	@Override
	public int getPaySeq() throws SQLException {
		int pay_seq = 0;
        pay_seq = ((Integer)sql.selectOne("receipt.getPaySeq")).intValue();
        return pay_seq;
	}

	@Override
	public void insertPayMember(Map<String, Object> param) throws SQLException {
		sql.insert("receipt.insertPayMember", param);		
	}

	@Override
	public int insertCheck(Map<String, Object> params) throws SQLException {
		int result = 0;
		result = sql.selectOne("receipt.insertCheck", params);
		return result;
	}

	
	
	@Override
	public int deletePayMember(int seq) throws SQLException {
		int result = 0;
		result = sql.delete("receipt.deletePayMember", seq);
		return result;
	}

	@Override
	public void modifyReceipt(Map<String, Object> params, List<String> list) throws SQLException {
		sql.update("receipt.modifyReceipt", params);
	}

	@Override
	public PayVO getReceiptInfo(Map<String, Object> params) throws SQLException {
		PayVO payInfo = new PayVO();
		payInfo = sql.selectOne("receipt.receiptInfo",params);
		return payInfo;
	}

	@Override
	public List<Map<String, Object>> getReceiptMember(Map<String, Object> params) throws SQLException {
		List<Map<String, Object>> list = null;
		list = sql.selectList("receipt.receiptMembers",params);
		return list;
	}
	
	
	
}
