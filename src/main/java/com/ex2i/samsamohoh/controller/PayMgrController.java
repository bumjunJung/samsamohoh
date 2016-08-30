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

import com.ex2i.samsamohoh.service.PayService;

@RestController 
public class PayMgrController {
	
	@Autowired
	private PayService service;

	/**
	  * @api {post} /getPriceData [PriceData]
	 * @apiVersion 0.1.0
	 * @apiName PriceData
	 * @apiGroup PayAnalysis
	 * 
	 * @apiParam {String} id Search users ID. 
	 * @apiParam {String} meal_type Search Meal types
	 * @apiParam {String} searchYear search year ('YYYY') 
	 * @apiParam {String} searchMonth search month ('MM')
	 * 
	 * @apiSuccess FoundPriceData PriceData was found.
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      [{"meal_type":"(0,1,2,3)", "price"="xx000", "settling_date"="date", "id"="ID"},,,,{}]
	 *      or
	 *      []
	 *     }
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("/getPriceData")
	public List<Map> getPayData(String meal_type, String id, String searchYear, String searchMonth,HttpServletRequest req, HttpSession session) throws SQLException{
		List<Map> list = new ArrayList<Map>();
		Map<String, Object> params = new HashMap<String, Object>();
		session = req.getSession();
		if(session.getAttribute("id") == null){
			return list;
		}
		
		params.put("id", id);
		params.put("meal_type", meal_type);
		params.put("searchYear", searchYear);
		params.put("searchMonth", searchMonth);
		
		list = service.getDataList(params);
		return list;
	}
	
	/**
	 * @api {post} /getPriceLabel [PriceLabel]
	 * @apiVersion 0.1.0
	 * @apiName PriceLabel
	 * @apiGroup PayAnalysis
	 * 
	 * @apiParam {String} id Search users ID. 
	 * @apiParam {String} meal_type Search Meal types
	 * @apiParam {String} searchYear search year ('YYYY') 
	 * @apiParam {String} searchMonth search month ('MM')
	 * 
	 * @apiSuccess FoundPriceLabel Price Label was found.
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      [점심, 저녁]
	 *      or
	 *      []
	 *     }
	 */
	@ResponseBody
	@RequestMapping("/getPriceLabel")
	public List<String> getChartLabel(HttpServletRequest req, HttpSession session,String meal_type, String id, String searchYear, String searchMonth) throws SQLException{
		List<String> labelList = new ArrayList<String>();
		session = req.getSession();
		if(session.getAttribute("id") == null){
			return labelList;
		}
		
		@SuppressWarnings("rawtypes")
		List<Map> list = null;
		@SuppressWarnings("rawtypes")
		List<Map> mapList  = null;

		Map<String, Object> params = new HashMap<String, Object>();

		String label = "";

		params.put("id", id);
		params.put("meal_type", meal_type);
		params.put("searchYear", searchYear);
		params.put("searchMonth", searchMonth);
		
		list = service.getDataList(params);

		if(!list.isEmpty()){
			labelList = getLabelList(mapList, list, label);
		}
		return labelList;
	}
	
	
	public List<String> getLabelList(@SuppressWarnings("rawtypes") List<Map> mapList, @SuppressWarnings("rawtypes") List<Map> list, String label){
		List<String> labelList = new ArrayList<String>();
		mapList  = list;
		for (int i = 0; i < mapList.size(); i++) {
			label = mapList.get(i).get("meal_type").toString();
			if(label.equals("0")){
				labelList.add("점심");
			}else if(label.equals("1")){
				labelList.add("저녁");
			}else if(label.equals("2")){
				labelList.add("간식");
			}else if(label.equals("3")){
				labelList.add("회식");
			}
		}
		return labelList;
	}
}
