package com.ex2i.samsamohoh.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ex2i.samsamohoh.vo.MemberVO;

public interface MemberService {
	//front
	public String getMaxMemberCode() throws SQLException;
	public int setMemeber(MemberVO memberVO) throws SQLException;
	public int compareMemberId(MemberVO memberVO) throws SQLException;
	public String getMemberCode(MemberVO memberVO) throws SQLException;
	public MemberVO getMemberInfoToCode(MemberVO memberVO) throws SQLException;
	public MemberVO getMemberInfoToToken(MemberVO memberVO) throws SQLException;
	
	public MemberVO getMemberInfo(Map<String, String> params)throws SQLException;

	// admin
	public int getMemberListCount(Map<String, Object> params) throws SQLException;
	public List<MemberVO> getMemberList(Map<String, Object> params) throws SQLException;
    public int updateMemberInfo(List<MemberVO> dataList) throws SQLException;
	@SuppressWarnings("rawtypes")
	public List<Map> getmemberCntList(Map<String, Object> params) throws SQLException;
	public int memberCnt(Map<String, Object> params) throws SQLException;
	@SuppressWarnings("rawtypes")
	public List<Map> getrestaurantCntList(Map<String, Object> params) throws SQLException;
	public int restarurantTotalCnt(Map<String, Object> params) throws SQLException;


}
