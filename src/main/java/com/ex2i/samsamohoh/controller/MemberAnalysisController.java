package com.ex2i.samsamohoh.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ex2i.samsamohoh.service.MemberService;
import com.ex2i.samsamohoh.vo.MemberVO;

@RestController

public class MemberAnalysisController {
	
	@Autowired
	private MemberService service;

	/**
	 * @api {post} /getMemberName [MemberName]
	 * @apiVersion 0.1.0
	 * @apiName MemberName
	 * @apiGroup MemberAnalysis
	 * 
	 * @apiParam {String} id Users unique ID. 
	 * 
	 * @apiSuccess FoundMembersName Members Name was found.
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      {"code":"members code","grade":"user or admin","id":"ID""name":"Name","reg_date":"date","state":"Y or N"}
	 *     }
	 */
		@ResponseBody
		@RequestMapping("/getMemberName")
		public List<MemberVO> getmemberName(HttpSession session, HttpServletRequest req) throws SQLException{
			/*select 이름 선택*/
			Map<String, Object>params = new HashMap<String, Object>();
			Map<String, String>param = new HashMap<String, String>();
			List<MemberVO> list = new ArrayList<MemberVO>();
			
			session = req.getSession();
			if(session.equals(null)){
				return list;
			}
			String id = (String) session.getAttribute("id");
			 params.put("id",id); 
			 param.put("id",id); 
			 MemberVO memberInfo = service.getMemberInfo(param);
			 if(memberInfo == null){
				 return list;
			 }else{
				 if(memberInfo.getGrade().equals("user")){
					 list = service.getMemberList(params);
				 }else if(memberInfo.getGrade().equals("admin")){
					 params.remove("id");
					 list = service.getMemberList(params);
				 }
				 
				 return list;
			 }
		}
		
		/**
		 * @api {post} /getChartLabel [MemberLabel]
		 * @apiVersion 0.1.0
		 * @apiName MemberLabel
		 * @apiGroup MemberAnalysis
		 * 
		 * @apiParam {String} id Search users ID. 
		 * @apiParam {int} meal_type Search Meal types
		 * @apiParam {String} session_id Users session ID 
		 * 
		 * @apiSuccess FoundMembersName Members Name was found.
		 * @apiSuccessExample Success-Response
		 *     HTTP/1.1 200 OK
		 *     {
		 *      [{"meal_type":"(0,1,2,3)", "cnt"="Number", "name"="Name", "id"="ID"},,,,,{}]
		 *      or
		 *      []
		 *     }
		 */
		@SuppressWarnings("rawtypes")
		@ResponseBody
		@RequestMapping("/getChartLabel")
		public List<Map> getChartlabel(HttpSession session, HttpServletRequest req, String id, int meal_type)throws SQLException{
			List<Map> list = new ArrayList<>();
			Map<String, String>param = new HashMap<String, String>();
			Map<String, Object>params = new HashMap<String, Object>();			
			
			session = req.getSession();
			String session_id = "";
			
			if(session.getAttribute("id") == null){
				return list;
			}
			session_id = (String) session.getAttribute("id");
			
			param.put("id", session_id);
			MemberVO memberInfo = service.getMemberInfo(param);
			
			if(memberInfo == null){
				return list;
			}else{
				if(memberInfo.getGrade().equals("user")){
					params.put("id", session_id);
				}else if(memberInfo.getGrade().equals("admin")){
					params.put("id", id);  
				}
				params.put("meal_type", meal_type);
				
				list = service.getmemberCntList(params);
				return list;
			}
		}
		
		/**
		 * @api {post} /getChartData [MemberData]
		 * @apiVersion 0.1.0
		 * @apiName MemberData
		 * @apiGroup MemberAnalysis
		 * 
		 * @apiParam {String} id Search users ID. 
		 * @apiParam {int} meal_type Search Meal types
		 * @apiParam {String} session_id Users session ID 
		 * 
		 * @apiSuccess FoundMemberData MemberData was found.
		 * @apiSuccessExample Success-Response
		 *     HTTP/1.1 200 OK
		 *     {
		 *      [xx.x, xx.x,,,,,, xx.x]
		 *      or
		 *      []
		 *     }
		 */
		@ResponseBody
		@RequestMapping("/getChartData")
		public List<Double> getchartData(HttpSession session, HttpServletRequest req, String id, int meal_type)throws SQLException{
			/*chart data*/
			@SuppressWarnings("rawtypes")
			List<Map> list = null;
			List <Double>dataList = new ArrayList<Double>();
			Map<String, String>param = new HashMap<String, String>();
			Map<String, Object>params = new HashMap<String, Object>();
			double totalcnt = 0;
			String count = "";

			session = req.getSession();
			if(session.getAttribute("id") == null){
				return dataList;
			}
			String session_id = (String) session.getAttribute("id");
			
			param.put("id", session_id);
			MemberVO memberInfo = service.getMemberInfo(param);
			
			if(memberInfo == null){
				return dataList;
			}else{
				if(memberInfo.getGrade().equals("user")){
					params.put("id", session_id);
				}else if(memberInfo.getGrade().equals("admin")){
					params.put("id", id);  
				}
				params.put("meal_type", meal_type);
				
				
				totalcnt = service.memberCnt(params);
				list = service.getmemberCntList(params);
				
				dataList = setPercent(list, totalcnt,count,dataList);
				
				return dataList;
			}
		}
		
		public List<Double> setPercent(@SuppressWarnings("rawtypes") List<Map> list, Double totalCnt, String count, List<Double> dataList){
			@SuppressWarnings("rawtypes")
			List<Map> listMap = list;  
			double real_cnt = 0, percent = 0;
			for (int i = 0; i < listMap.size(); i++) {
				count = listMap.get(i).get("cnt").toString();
				real_cnt = Integer.parseInt(count);
				percent = Math.round(((real_cnt / totalCnt) * 100)*100)/100.0;
				dataList.add(percent);
			}
			return dataList;
		}
}
