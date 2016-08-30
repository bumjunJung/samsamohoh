package com.ex2i.samsamohoh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ex2i.samsamohoh.vo.MemberVO;

public interface MemberDao {
	//front
	public String selectMaxMemberCode() throws SQLException;
	public int insertMemeber(MemberVO memberVO) throws SQLException;
	public int selectCompareMemberId(MemberVO memberVO) throws SQLException;
	public String selectMemberCode(MemberVO memberVO) throws SQLException;
	public MemberVO selectMemberInfoToCode(MemberVO memberVO) throws SQLException;
	public MemberVO selectMemberInfoToToken(MemberVO memberVO) throws SQLException;
	public MemberVO getMemberInfo(Map<String, String> params)throws SQLException;


	// admin 
	public int getMemberListCount(Map<String, Object> params) throws SQLException;
	public List<MemberVO> getMemberList(Map<String, Object> params) throws SQLException;
	public int updateMemberInfo(Map<String, Object> params) throws SQLException;
	@SuppressWarnings("rawtypes")
	public List<Map> getmemberCntList(Map<String, Object> params) throws SQLException;
	public int memberCnt(Map<String, Object> params) throws SQLException;
	@SuppressWarnings("rawtypes")
	public List<Map> getrestaurantCntList(Map<String, Object> params) throws SQLException;
	public int restarurantTotalCnt(Map<String, Object> params) throws SQLException;
}
