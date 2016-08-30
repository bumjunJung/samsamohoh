package com.ex2i.samsamohoh.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ex2i.samsamohoh.service.FamilyService;
import com.ex2i.samsamohoh.util.PagingUtils;

@RestController
public class FamilyMgrController {

	@Autowired
	private FamilyService service;

	/**
	 * @api {post} /familyListAjax
	 * @apiVersion 0.1.0
	 * @apiName FamilyList
	 * @apiGroup FamilyList
	 * 
	 * @apiParam {String} searchYear search year ('YYYY') 
	 * @apiParam {String} searchMonth search month ('MM')
	 * @apiParam {String} searchDay search day ('DD')
	 * @apiParam {int}  page current Page
	 * 
	 * @apiSuccess FoundFamilyList FamilyList was found.
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      [{"reg_date":"YYYY-MM-DD", "family_seq":"Number", "meal_type":"(0,1,2,3)", "name":"restaurants name", "cnt":"Number"},,,,{}]
	 *     }
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/familyListAjax")
	public Map<String, Object> getFamilyList(String searchYear, String searchMonth, String searchDay, int page) throws SQLException{

		Map<String, Object> result = new HashMap<String, Object>();
		List<Map> list = null;	
		int totalCnt = 0;
		int limit = 5;
		int startIndex = 0;

		int startPage = 0;
		int totalPage = 0;
		int defaultPageLimit = 10;
		int pageLimit = 0;
		
		startIndex = PagingUtils.startIndex(page, limit);

		Map<String, Object>params = new HashMap<String, Object>();
		params.put("searchYear", searchYear);
		params.put("searchMonth", searchMonth);
		params.put("searchDay", searchDay);
		params.put("limit", limit);
		params.put("startIndex", startIndex);
		
		totalCnt = service.getFamilyListCount(params);
		list = service.getFamilyList(params);
		
		totalPage = PagingUtils.calculateTotalPage(limit, totalCnt);
		startPage = PagingUtils.startPage(page, defaultPageLimit);
		pageLimit = PagingUtils.pageLimit(defaultPageLimit, totalPage, startPage);
		
		result.put("list",list);
		result.put("totalCnt",totalCnt);
		result.put("totalPage",totalPage);
		result.put("page",page);
		result.put("pageLimit",pageLimit);
		result.put("defaultPageLimit", defaultPageLimit);
		result.put("startPage",startPage);

		return result;
	}
	
	/**
	 * @api {post} /getFamilyMembersName
	 * @apiVersion 0.1.0
	 * @apiName FamilyMembersName
	 * @apiGroup FamilyList
	 * 
	 * @apiParam {String} family_seq Families unique SEQ  
	 * 
	 * @apiSuccess FoundFamilyMembers Family Members name was found.
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      ["membersName,,,,membersName"]
	 *     }
	 */
	@RequestMapping("/getFamilyMembersName")
	public List<String> getFamilyMembersNameList(String family_seq) throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<String> nameList = new ArrayList<String>();
		Map<String, Object>params = new HashMap<String, Object>();
		params.put("family_seq", family_seq);
		
		list = service.getFamilyNames(params);

		String members = "";
        for(int i = 0; i < list.size(); i++)
        	
        	if(i == list.size() - 1)
        		members = (new StringBuilder(String.valueOf(members))).append(list.get(i).get("name")).toString();
        	else
        		members = (new StringBuilder(String.valueOf(members))).append(list.get(i).get("name")).append(",").toString();
		
		
		nameList.add(members);
		
		return nameList;
	}
}
