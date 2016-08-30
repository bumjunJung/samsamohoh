package com.ex2i.samsamohoh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FamilyDao {
	//back
	public int getFamilyListCount(Map<String, Object> params) throws SQLException;
	@SuppressWarnings("rawtypes")
	public List<Map> getFamilyList(Map<String, Object> params) throws SQLException;
	public List<Map<String, Object>> getFamilyNames(Map<String, Object> params) throws SQLException;
	
	//front
	public int selectMaxFamilySeq() throws SQLException;
	public int insertJoinFamily(Map<String, Object> params) throws SQLException;
	public int insertJoinFamilyMember(Map<String, Object> params) throws SQLException;
	public List<Map<String, Object>> selectFamilyLunchListAll() throws SQLException;
	public List<Map<String, Object>> selectFamilyDinnerListAll() throws SQLException;
	public int updateJoinFamilyMembers(Map<String, Object> params) throws SQLException;
	public int insertModifyFamilyMember(Map<String, Object> params) throws SQLException;
	public List<Map<String, Object>> selectMembers(Map<String, Object> params) throws SQLException;
	public List<Map<String, Object>> selectMembersName(Map<String, Object> params) throws SQLException;
	
	public String selectBeforeLunchFamilySeq(String member_code) throws SQLException;
	public String selectBeforeDinnerFamilySeq(String member_code) throws SQLException;
	public int selectAfterLunchFamilySeq(String restaurant_code) throws SQLException;
	public int selectAfterDinnerFamilySeq(String restaurant_code) throws SQLException;
	public String selectRestautrantLunch_codeToFamily(String restaurant_code) throws SQLException;
	public String selectRestautrantDinner_codeToFamily(String restaurant_code) throws SQLException;
	public int insertMoveFamily(Map<String, Object> params) throws SQLException;
	public void deleteFamilyMember(Map<String, Object> params) throws SQLException;
	public List<Map<String, Object>> selectMembersIsNullSeq() throws SQLException;
	public void updateActive_typeN(String active_seq) throws SQLException;
	public List<Map<String, Object>> selectMembersIsNotNullSeq() throws SQLException;
	public void updateActive_typeY(String active_seq) throws SQLException;
	public String selectMemberCode(String param) throws SQLException;
	
}
