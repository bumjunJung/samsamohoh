package com.ex2i.samsamohoh.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ex2i.samsamohoh.service.MemberService;
import com.ex2i.samsamohoh.util.PagingUtils;
import com.ex2i.samsamohoh.vo.MemberVO;

@RestController
public class MemberMgrController {
	@Autowired
	private MemberService service;
	
	// -- 회원관리 --
	/**
	 * @api {post} /memberListAjax [MemberList]
	 * @apiVersion 0.1.0
	 * @apiName MemberList
	 * @apiGroup Member
	 * 
	 * @apiParam {String} searchNm search key word(id, name)
	 * @apiParam {int}  page current Page
	 * 
	 * @apiSuccess FoundMemberList MemberList was found.
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      {"code":"members code","grade":"user or admin","id":"ID""name":"Name","reg_date":"date","state":"Y or N"}
	 *     }
	 */
	@ResponseBody
	@RequestMapping("/memberListAjax")
	public Map<String, Object> getMemberList(String searchNm, int page) throws SQLException{

		List<MemberVO> list = null;
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();

		int totalCnt =0;
		int limit = 5;
		int startIndex = 0;
		int startPage = 0;
		int totalPage = 0;
		int defaultPageLimit = 10;
		int pageLimit = 0;
		
		startIndex = PagingUtils.startIndex(page, limit);
		params.put("searchNm", searchNm);
		params.put("limit", limit);
		params.put("startIndex", startIndex);
		
		totalCnt = service.getMemberListCount(params);
		list = service.getMemberList(params);

		totalPage = PagingUtils.calculateTotalPage(limit, totalCnt);
		startPage = PagingUtils.startPage(page, defaultPageLimit);
		pageLimit = PagingUtils.pageLimit(defaultPageLimit, totalPage, startPage);
		
		result.put("list", list);
		result.put("pageLimit",pageLimit);
		result.put("totalPage",totalPage);
		result.put("page",page);
		result.put("defaultPageLimit", defaultPageLimit);
		result.put("startPage",startPage);
		
		return result;
	}


	/**
	 * @api {post} /modifyMember [modifyMember]
	 * @apiVersion 0.1.0
	 * @apiName ModifyMember
	 * @apiGroup Member
	 * 
	 * @apiParam {List} data changed Members data)
	 * 
	 * @apiSuccess FoundMemberList MemberList was found.
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      {"code":"members code","grade":"user or admin","id":"ID""name":"Name","reg_date":"date","state":"Y or N"}
	 *     }
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyMember", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public List<MemberVO> modifyMemberList(@RequestBody List<MemberVO> data ) throws SQLException{
		List<MemberVO> list = data;
		
		service.updateMemberInfo(data);
		
		return list;
	}
}
