package com.ex2i.samsamohoh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class FamilyDaoImpl implements FamilyDao{

	@Autowired
	private SqlSessionTemplate sql;
	
	//back
	
	@Override
	public int getFamilyListCount(Map<String, Object> params) throws SQLException {
		return sql.selectOne("family.familyListTotalCount",params);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getFamilyList(Map<String, Object> params) throws SQLException {
		List<Map> list = sql.selectList("family.familyList",params);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getFamilyNames(Map<String, Object> params) throws SQLException {
		List<Map<String, Object>> list = sql.selectList("family.familyMembersName",params);
		return list;
	}

	
	
	//front
	public int selectMaxFamilySeq() throws SQLException {
		return sql.selectOne("family.selectMaxFamillySeq");
	}

	@Override
	public int insertJoinFamily(Map<String, Object> params) throws SQLException {
		int result = sql.insert("family.insertJoinFamily", params);
		return result;
	}
	
	@Override
	public int insertJoinFamilyMember(Map<String, Object> params) throws SQLException {
		int result = sql.insert("family.insertJoinFamilyMember", params);
		return result;
	}
	
	@Override
	public List<Map<String, Object>> selectFamilyLunchListAll() throws SQLException {
		List<Map<String, Object>> list = sql.selectList("family.selectFamilyLunchListAll");
		return list;
	}
	
	@Override
	public List<Map<String, Object>> selectFamilyDinnerListAll() throws SQLException {
		List<Map<String, Object>> list = sql.selectList("family.selectFamilyDinnerListAll");
		return list;
	}

	@Override
	public int updateJoinFamilyMembers(Map<String, Object> params) throws SQLException {
		return sql.update("family.updateJoinFamilyMembers", params);
	}
	
	@Override
	public int insertModifyFamilyMember(Map<String, Object> params) throws SQLException {
		int result = sql.insert("family.insertModifyFamilyMember", params);
		return result;
	}
	
	@Override
	public List<Map<String, Object>> selectMembers(Map<String, Object> params) throws SQLException {
		return sql.selectList("family.selectMembers", params);
	}
	
	@Override
	public List<Map<String, Object>> selectMembersName(Map<String, Object> params) throws SQLException {
		return sql.selectList("family.selectMembersName", params);
	}

	
	
	
	
	@Override
	public String selectBeforeLunchFamilySeq(String member_code) throws SQLException {
		return sql.selectOne("family.selectBeforeLunchFamilySeq", member_code);
	}
	
	@Override
	public String selectBeforeDinnerFamilySeq(String member_code) throws SQLException {
		return sql.selectOne("family.selectBeforeDinnerFamilySeq", member_code);
	}

	@Override
	public int selectAfterLunchFamilySeq(String restaurant_code) throws SQLException {
		return sql.selectOne("family.selectAfterLunchFamilySeq", restaurant_code);
	}
	
	@Override
	public int selectAfterDinnerFamilySeq(String restaurant_code) throws SQLException {
		return sql.selectOne("family.selectAfterDinnerFamilySeq", restaurant_code);
	}

	@Override
	public String selectRestautrantLunch_codeToFamily(String restaurant_code) throws SQLException {
		return sql.selectOne("family.selectRestautrantLunch_codeToFamily", restaurant_code);
	}
	
	@Override
	public String selectRestautrantDinner_codeToFamily(String restaurant_code) throws SQLException {
		return sql.selectOne("family.selectRestautrantDinner_codeToFamily", restaurant_code);
	}

	@Override
	public int insertMoveFamily(Map<String, Object> params) throws SQLException {
		return sql.update("family.insertMoveFamily", params);
	}

	@Override
	public void deleteFamilyMember(Map<String, Object> params) throws SQLException {
		sql.update("family.deleteFamilyMember", params);
	}

	@Override
	public List<Map<String, Object>> selectMembersIsNullSeq() throws SQLException {
		return sql.selectList("family.selectMembersIsNullSeq");
	}

	@Override
	public void updateActive_typeN(String active_seq) throws SQLException {
		sql.update("family.updateActive_typeN", active_seq);
	}

	@Override
	public List<Map<String, Object>> selectMembersIsNotNullSeq() throws SQLException {
		return sql.selectList("family.selectMembersIsNotNullSeq");
	}

	@Override
	public void updateActive_typeY(String active_seq) throws SQLException {
		sql.update("family.updateActive_typeY", active_seq);
	}
	
	@Override
	public String selectMemberCode(String param) throws SQLException {
		return sql.selectOne("family.selectFamilyMemberCode", param);
	}
	
	
}