package com.ex2i.samsamohoh.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FamilyService {
	
	//back
	public int getFamilyListCount(Map<String, Object> params) throws SQLException;
	@SuppressWarnings("rawtypes")
	public List<Map> getFamilyList(Map<String, Object> params) throws SQLException;
	public List<Map<String, Object>> getFamilyNames(Map<String, Object> params) throws SQLException;
	
	//front
	public int getMaxFamilySeq() throws SQLException;
	public int setJoinFamily(Map<String, Object> params) throws SQLException;
	public int setJoinFamilyMember(Map<String, Object> params) throws SQLException;
	public List<Map<String, Object>> getFamilyListAll() throws SQLException;
	public int modifyJoinFamilyMembers(Map<String, Object> params) throws SQLException;
	public int setModifyFamilyMember(Map<String, Object> params) throws SQLException;
	public List<Map<String, Object>> getMembers(Map<String, Object> params) throws SQLException;
	public List<Map<String, Object>> getMembersName(Map<String, Object> params) throws SQLException;
	
	public String getBeforeFamilySeq(String member_code) throws SQLException;
	public int getAfterFamilySeq(String restaurant_code) throws SQLException;
	public String getRestautrant_codeToFamily(String restaurant_code) throws SQLException;
	public int setMoveFamily(Map<String, Object> params) throws SQLException;
	public void dropFamilyMember(Map<String, Object> params) throws SQLException;	
	public List<Map<String, Object>> getMembersIsNullSeq() throws SQLException;
	public List<Map<String, Object>> getMembersIsNotNullSeq() throws SQLException;
	public void modifyActive_type() throws SQLException;	
	public String getMemberCode(String param) throws SQLException;
}
