package com.ex2i.samsamohoh.service;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex2i.samsamohoh.dao.FamilyDao;

@Service
public class FamilyServiceImpl implements FamilyService{

	@Autowired
	private FamilyDao dao;
	
	private static final LocalTime startTime = LocalTime.now();
	private static final LocalTime endTime = LocalTime.of(16, 00);
	
	//back

	@Override
	public int getFamilyListCount(Map<String, Object> params) throws SQLException {
		return this.dao.getFamilyListCount(params);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getFamilyList(Map<String, Object> params) throws SQLException {
		return this.dao.getFamilyList(params);
	}
	
	@Override
	public List<Map<String, Object>> getFamilyNames(Map<String, Object> params) throws SQLException {
		return this.dao.getFamilyNames(params);
	}

	
	//front
	@Override
	public int getMaxFamilySeq() throws SQLException {
		return this.dao.selectMaxFamilySeq();
	}
	
	@Transactional(rollbackFor={SQLException.class})
	@Override
	public int setJoinFamily(Map<String, Object> params) throws SQLException {
		int result = this.dao.insertJoinFamily(params);
		return result;
	}
	
	@Transactional(rollbackFor={SQLException.class})
	@Override
	public int setJoinFamilyMember(Map<String, Object> params) throws SQLException {
		int result = this.dao.insertJoinFamilyMember(params);
		return result;
	}
	
	@Override
	public List<Map<String, Object>> getFamilyListAll() throws SQLException {
		if(startTime.isBefore(endTime)){
			return this.dao.selectFamilyLunchListAll();
		} else{
			return this.dao.selectFamilyDinnerListAll();
		}		
	}
	
	@Transactional(rollbackFor={SQLException.class})
	@Override
	public int modifyJoinFamilyMembers(Map<String, Object> params) throws SQLException {
		return this.dao.updateJoinFamilyMembers(params);
	}
	
	@Transactional(rollbackFor={SQLException.class})
	@Override
	public int setModifyFamilyMember(Map<String, Object> params) throws SQLException {
		int result = this.dao.insertModifyFamilyMember(params);
		return result;
	}
	
	@Override
	public List<Map<String, Object>> getMembers(Map<String, Object> params) throws SQLException {
		return this.dao.selectMembers(params);
	}

	@Override
	public List<Map<String, Object>> getMembersName(Map<String, Object> params) throws SQLException {
		return this.dao.selectMembersName(params);
	}
	
	@Override
	public String getBeforeFamilySeq(String member_code) throws SQLException {
		if(startTime.isBefore(endTime)){
			return this.dao.selectBeforeLunchFamilySeq(member_code);
		} else{
			return this.dao.selectBeforeDinnerFamilySeq(member_code);
		}	
	}

	@Override
	public int getAfterFamilySeq(String restaurant_code) throws SQLException {
		if(startTime.isBefore(endTime)){
			return this.dao.selectAfterLunchFamilySeq(restaurant_code);
		} else{
			return this.dao.selectAfterDinnerFamilySeq(restaurant_code);
		}	
	}
	
	@Override
	public String getRestautrant_codeToFamily(String restaurant_code) throws SQLException {
		if(startTime.isBefore(endTime)){
			return this.dao.selectRestautrantLunch_codeToFamily(restaurant_code);
		} else{
			return this.dao.selectRestautrantDinner_codeToFamily(restaurant_code);
		}	
	}
	
	@Override
	public int setMoveFamily(Map<String, Object> params) throws SQLException {
		return this.dao.insertMoveFamily(params);
	}
	
	@Override
	public void dropFamilyMember(Map<String, Object> params) throws SQLException {
		this.dao.deleteFamilyMember(params);
	}
	
	@Override
	public List<Map<String, Object>> getMembersIsNullSeq() throws SQLException {
		return this.dao.selectMembersIsNullSeq();
	}
	
	@Override
	public List<Map<String, Object>> getMembersIsNotNullSeq() throws SQLException {
		return this.dao.selectMembersIsNotNullSeq();
	}
	
	@Transactional(rollbackFor={SQLException.class})
	@Override
	public void modifyActive_type() throws SQLException {
		List<Map<String, Object>> membersIstNotNullSeq = this.dao.selectMembersIsNotNullSeq();
		for(int i = 0; i < membersIstNotNullSeq.size(); i++){
			String active_seq = membersIstNotNullSeq.get(i).get("seq").toString();
			this.dao.updateActive_typeY(active_seq);
		}		

		List<Map<String, Object>> membersIstNullSeq = this.dao.selectMembersIsNullSeq();
		for(int i = 0; i < membersIstNullSeq.size(); i++){
			String active_seq = membersIstNullSeq.get(i).get("seq").toString();
			this.dao.updateActive_typeN(active_seq);
		}
	}
	
	@Override
	public String getMemberCode(String param) throws SQLException {
		return this.dao.selectMemberCode(param);
	}
	
	

}
