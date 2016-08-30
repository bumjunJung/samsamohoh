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
public class RestaurantAnalysisController {
	
	@Autowired
	private MemberService service;
	
	/**
		 * @api {post} /getRchartLabel [RestaurantsLabel]
		 * @apiVersion 0.1.0
		 * @apiName RestaurantsLabel
		 * @apiGroup RestaurantAnalysis
		 * 
		 * @apiParam {String} id Search users ID. 
		 * @apiParam {int} meal_type Search Meal types
		 * @apiParam {String} session_id Users session ID 
		 * @apiParam {String} grade Users GRADE. 
		 * 
		 * @apiSuccess FoundRestaurantsLabel RestaurantsLabel was found.
		 * @apiSuccessExample Success-Response
		 *     HTTP/1.1 200 OK
		 *     {
		 *      [{"meal_type":"(0,1,2,3)", "name":"RestaurantName", "cnt":"Number", "id":"ID"},,,,{}]
		 *      or
		 *      []
		 *     }
	 */
		@SuppressWarnings("rawtypes")
		@ResponseBody
		@RequestMapping("/getRchartLabel")
		public List<Map> getRestarantLabel(HttpSession session, HttpServletRequest req,String id, int meal_type) throws SQLException{
			List<Map> list = new ArrayList<Map>();
			Map<String, Object> params = new HashMap<String, Object>();
			Map<String, String> param = new HashMap<String, String>();
			session = req.getSession();
			if(session.getAttribute("id") == null){
				return list;
			}
			String session_id = (String) session.getAttribute("id");
			
			param.put("id", session_id);
			MemberVO memberInfo = service.getMemberInfo(param);
			
			String grade = memberInfo.getGrade();
			
			if(id.equals("")){
				id = session_id;
			}
			
			if(grade.equals("user")){
				params.put("id", session_id);
			}else if(grade.equals("admin")){
				params.put("id", id);
			}
			params.put("meal_type", meal_type);
			
			list = service.getrestaurantCntList(params);
			
			return list;
		}
		
		/**
		 * @api {post} /getRChartData [RestaurantsData]
		 * @apiVersion 0.1.0
		 * @apiName RestaurantsData
		 * @apiGroup RestaurantAnalysis
		 * 
		 * @apiParam {String} id Search users ID. 
		 * @apiParam {int} meal_type Search Meal types
		 * @apiParam {String} session_id Users session ID 
		 * @apiParam {String} grade Users GRADE. 
		 * 
		 * @apiSuccess FoundRestaurantsData RestaurantsData was found.
		 * @apiSuccessExample Success-Response
		 *     HTTP/1.1 200 OK
		 *     {
		 *      [xx.x, xx.x,,,,,, xx.x]
		 *      or
		 *      []
		 *     }
		 */
		@ResponseBody
		@RequestMapping("/getRChartData")
		public List <Double> getRestarantInfo(HttpSession session, HttpServletRequest req, String id, int meal_type) throws SQLException{
			@SuppressWarnings("rawtypes")
			List<Map> list = null;

			List <Double>dataList = new ArrayList<Double>();
			Map<String, Object> params = new HashMap<String, Object>();
			Map<String, String> param = new HashMap<String, String>();

			session = req.getSession();
			if(session.getAttribute("id") == null){
				return dataList;
			}
			String session_id = (String) session.getAttribute("id");
			param.put("id", session_id);
			MemberVO memberInfo = service.getMemberInfo(param);
			
			String grade = memberInfo.getGrade();
			Double totalcnt = 0.0;
			String count = "";

			if(id.equals("")){
				id = session_id;
			}
			
			if(grade.equals("user")){
				params.put("id", session_id);
			}else if(grade.equals("admin")){
				params.put("id", id);
			}
			params.put("meal_type", meal_type);
			
			totalcnt = (double) service.restarurantTotalCnt(params);
			list = service.getrestaurantCntList(params);
			
			dataList = setPercent(list, totalcnt,count,dataList);
						
			return dataList;
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
