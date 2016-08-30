package com.ex2i.samsamohoh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ex2i.samsamohoh.vo.MemberVO;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private SqlSessionTemplate sql;
	
	//front
		@Override
		public String selectMaxMemberCode() throws SQLException {
			String result = this.sql.selectOne("member.selectMaxMemberCode");
			return result;
		}
		
		@Override
		public int insertMemeber(MemberVO memberVO) throws SQLException {
			this.sql.insert("member.insertMemeber", memberVO);
			return 0;
		}
		
		@Override
		public int selectCompareMemberId(MemberVO memberVO) throws SQLException {
			int result = this.sql.selectOne("member.selectCompareMemberId", memberVO);
			return result;
		}	

		@Override
		public String selectMemberCode(MemberVO memberVO) throws SQLException {
			String result = this.sql.selectOne("member.selectMemberCode", memberVO);
			return result;
		}

		@Override
		public MemberVO selectMemberInfoToCode(MemberVO memberVO) throws SQLException {
			MemberVO memberVOInfo = new MemberVO();
			memberVOInfo = this.sql.selectOne("member.selectMemberInfoToCode", memberVO);
			return memberVOInfo;
		}
		
		@Override
		public MemberVO selectMemberInfoToToken(MemberVO memberVO) throws SQLException {
			MemberVO memberVOInfo = new MemberVO();
			memberVOInfo = this.sql.selectOne("member.selectMemberInfoToToken", memberVO);
			return memberVOInfo;
		}
		
	//admin
	@Override
	public List<MemberVO> getMemberList(Map<String, Object> params) throws SQLException {
		List<MemberVO> list = sql.selectList("member.memberList",params);
		return list;
	}

	@Override
	public int getMemberListCount(Map<String, Object> params) throws SQLException {
		int cnt =0;
		cnt = sql.selectOne("member.memberListTotalCnt",params);
		return cnt;
	}
	
	@Override
	public int updateMemberInfo(Map<String, Object> params) throws SQLException {
		int cnt =0;
		cnt = sql.update("member.updateMember",params);
		return cnt;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getmemberCntList(Map<String, Object> params) throws SQLException {
		List<Map> list = sql.selectList("member.memberCntList",params);
		return list;
	}

	@Override
	public int memberCnt(Map<String, Object> params) throws SQLException {
		int cnt = 0;
		cnt = this.sql.selectOne("member.memberTotalCnt", params);
		return cnt;
	}

 @SuppressWarnings("rawtypes")
	@Override
	public List<Map> getrestaurantCntList(Map<String, Object> params) throws SQLException {
		List<Map> list = sql.selectList("member.restaurantCntList",params);
		return list;
	}

	@Override
	public int restarurantTotalCnt(Map<String, Object> params) throws SQLException {
		int cnt = 0;
		cnt = this.sql.selectOne("member.restarurantTotalCnt", params);
		return cnt;
	}

	@Override
	public MemberVO getMemberInfo(Map<String, String> params) throws SQLException {
		MemberVO memberInfo = sql.selectOne("member.getMemberInfo",params);
		return memberInfo;
	}
}
