package com.ex2i.samsamohoh.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex2i.samsamohoh.dao.MemberDao;
import com.ex2i.samsamohoh.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao dao;
	
	//front
		@Override
		public String getMaxMemberCode() throws SQLException {
			return this.dao.selectMaxMemberCode();
		}
		
		@Override
		public int setMemeber(MemberVO memberVO) throws SQLException {
			this.dao.insertMemeber(memberVO);
			return 0;
		}
		
		@Override
		public int compareMemberId(MemberVO memberVO) throws SQLException {
			int result = this.dao.selectCompareMemberId(memberVO);
			return result;
		}
		
		@Override
		public String getMemberCode(MemberVO memberVO) throws SQLException {
			String result = this.dao.selectMemberCode(memberVO);
			return result;
		}
		
		@Override
		public MemberVO getMemberInfoToCode(MemberVO memberVO) throws SQLException {
			MemberVO memberVOInfo = new MemberVO();
			memberVOInfo = this.dao.selectMemberInfoToCode(memberVO);
			return memberVOInfo;
		}
		
		@Override
		public MemberVO getMemberInfoToToken(MemberVO memberVO) throws SQLException {
			MemberVO memberVOInfo = new MemberVO();
			memberVOInfo = this.dao.selectMemberInfoToToken(memberVO);
			return memberVOInfo;
		}

		  @Override
		  public MemberVO getMemberInfo(Map<String, String> params) throws SQLException {
			  MemberVO memberInfo = this.dao.getMemberInfo(params);
			  return memberInfo;
		  }
		
	//admin
		  
	@Override
	public List<MemberVO> getMemberList(Map<String, Object> params) throws SQLException {
		return this.dao.getMemberList(params);
	}

	@Override
	public int getMemberListCount(Map<String, Object> params) throws SQLException {
		return this.dao.getMemberListCount(params);
	}

	@Transactional(rollbackFor={SQLException.class})
	@Override
	public int updateMemberInfo(List<MemberVO> dataList) throws SQLException {
	
		Map<String, Object> param = new HashMap<String, Object>();
        for(int i = 0; i < dataList.size(); i++)
        {
            param.put("code", dataList.get(i).getCode());
            param.put("state", dataList.get(i).getState());
            param.put("grade",  dataList.get(i).getGrade());
            this.dao.updateMemberInfo(param);
        }
		return 0;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getmemberCntList(Map<String, Object> params) throws SQLException {
		return this.dao.getmemberCntList(params);
	}

	@Override
	public int memberCnt(Map<String, Object> params) throws SQLException {
		return this.dao.memberCnt(params);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getrestaurantCntList(Map<String, Object> params) throws SQLException {
		return this.dao.getrestaurantCntList(params);
	}

	@Override
	public int restarurantTotalCnt(Map<String, Object> params) throws SQLException {
		return this.dao.restarurantTotalCnt(params);
	}
}
