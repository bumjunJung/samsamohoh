package com.ex2i.samsamohoh.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex2i.samsamohoh.dao.ReceiptDao;
import com.ex2i.samsamohoh.vo.MemberVO;
import com.ex2i.samsamohoh.vo.PayVO;
import com.ex2i.samsamohoh.vo.RestaurantVO;

@Service
public class ReceiptServiceImpl implements ReceiptService {

	@Autowired
	private ReceiptDao dao;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getFamilyList(Map<String, Object> params) throws SQLException {
		return this.dao.getFamilyList(params);
	}
	
	@Override
	public int getFamilyListTotalCnt(Map<String, Object> params) throws SQLException {
		return this.dao.getFamilyListTotalCnt(params);
	}

	@Override
	public List<Map<String, Object>> getParticipantNames(Map<String, Object> params) throws SQLException {
		return this.dao.getParticipantNames(params);
	}

	@Transactional(rollbackFor={SQLException.class})
	@Override
	public int updateReceiptType(Map<String, String> params) throws SQLException {
		this.dao.updateReceiptType(params);
		return 0;
	}

	@Transactional(rollbackFor={SQLException.class})
	@Override
	public int deleteReceipt(Map<String, Object> params) throws SQLException {
		this.dao.deleteReceipt(params);	
		return 0;
	}

	@Override
	public List<Map<String, Object>> getFamilyInfo(Map<String, String> params) throws SQLException {
		return this.dao.getFamilyInfo(params);
	}

	@Override
	public MemberVO findeMemberInfo(Map<String, Object> params) throws SQLException {
		return this.dao.findeMemberInfo(params);
	}
	
	@Transactional(rollbackFor={SQLException.class})
	@Override
	public RestaurantVO findeRestaurantInfo(Map<String, Object> params) throws SQLException {
		return this.dao.findeRestaurantInfo(params);
	}

		
	@Override
	public int insertCheck(Map<String, Object> params) throws SQLException {
		return this.dao.insertCheck(params);
	}

	@Transactional(rollbackFor={SQLException.class})
	@Override
	public void insertReceipt(Map<String, Object> params, List<String> list) throws SQLException {
		String temp = params.get("total_price").toString();
        int pay_seq = 0;
        int price = Integer.parseInt(temp);

        dao.insertReceipt(params, list);
        pay_seq = dao.getPaySeq();
        price /= list.size();
        Map<String, Object> param = new HashMap<String, Object>();
        for(int i = 0; i < list.size(); i++)
        {
            param.put("member", list.get(i));
            param.put("pay_seq", Integer.valueOf(pay_seq));
            param.put("price", Integer.valueOf(price));
            dao.insertPayMember(param);
        }
	}

	@Override
	public PayVO getReceiptInfo(Map<String, Object> params) throws SQLException {
		return this.dao.getReceiptInfo(params);
	}

	@Override
	public List<Map<String, Object>> getReceiptMember(Map<String, Object> params) throws SQLException {
		return this.dao.getReceiptMember(params);
	}

	@Transactional(rollbackFor={SQLException.class})
	@Override
	public void modifyReceipt(Map<String, Object> params, List<String> list) throws SQLException {
		String temp = params.get("total_price").toString();
		String tempseq = params.get("seq").toString();
        int price = Integer.parseInt(temp);
        int seq = Integer.parseInt(tempseq);
        params.put("seq", seq);
        dao.modifyReceipt(params, list);
		dao.deletePayMember(seq);
		price /= list.size();
        Map<String, Object> param = new HashMap<String, Object>();
        for(int i = 0; i < list.size(); i++)
        {
            param.put("member", list.get(i));
            param.put("pay_seq", seq);
            param.put("price", Integer.valueOf(price));
            dao.insertPayMember(param);
        }
		
	}
	
	
	
	
}
