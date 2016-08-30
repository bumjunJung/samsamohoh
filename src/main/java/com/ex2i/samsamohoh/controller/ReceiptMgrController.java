package com.ex2i.samsamohoh.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ex2i.samsamohoh.service.MemberService;
import com.ex2i.samsamohoh.service.ReceiptService;
import com.ex2i.samsamohoh.util.PagingUtils;
import com.ex2i.samsamohoh.vo.MemberVO;
import com.ex2i.samsamohoh.vo.PayVO;
import com.ex2i.samsamohoh.vo.RestaurantVO;


@RestController
public class ReceiptMgrController {

	@Autowired
	private ReceiptService service;
	@Autowired
	private MemberService memberservice;
	
	@SuppressWarnings("rawtypes")

	/**
	 * @api {post} /receiptListAjax [ReceiptList]
	 * @apiVersion 0.1.0
	 * @apiName ReceiptList
	 * @apiGroup Receipt
	 * 
	 * @apiParam {String} searchYear search year ('YYYY') 
	 * @apiParam {String} searchMonth search month ('MM')
	 * @apiParam {int}  page current Page
	 * 
	 * @apiSuccess FoundReceiptList ReceiptList was found.
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      [{"total_price":xx000,"meal_type":(0,1,2,3),"etc":"",
	 *        "cnt":Number,"settling_date":"date","pay_seq":Number},,{}]
	 *     }
	 */
	@RequestMapping("/receiptListAjax")
	public Map<String, Object> getReceiptList(String searchYear, String searchMonth,int page) throws SQLException{
		List<Map> list = null;	
		Map<String, Object>result = new HashMap<String, Object>();
		Map<String, Object>params = new HashMap<String, Object>();
		
		int totalCnt = 0;
		
		int limit = 5;
		int startIndex = 0;
		int startPage = 0;
		int totalPage = 0;
		int defaultPageLimit = 10;
		int pageLimit = 0;
		
		startIndex = PagingUtils.startIndex(page, limit);
		
		params.put("searchYear", searchYear);
		params.put("searchMonth", searchMonth);
		params.put("startIndex", startIndex);
		params.put("limit", limit);
		
		totalCnt = service.getFamilyListTotalCnt(params);
		list = service.getFamilyList(params);	
		
		totalPage = PagingUtils.calculateTotalPage(limit, totalCnt);
		startPage = PagingUtils.startPage(page, defaultPageLimit);
		pageLimit = PagingUtils.pageLimit(defaultPageLimit, totalPage, startPage);

		
		result.put("list", list);
		result.put("pageLimit",pageLimit);
		result.put("startPage",startPage);
		result.put("totalPage",totalPage);
		result.put("defaultPageLimit",defaultPageLimit);
		result.put("page",page);
		
		return result;
	}
	
	/**
	 * @api {post} /deleteReceipt [DeleteReceipt]
	 * @apiVersion 0.1.0
	 * @apiName DeleteReceipt
	 * @apiGroup Receipt
	 * 
	 * @apiParam {String} seq receipts unique Number. 
	 * @apiParam {String} searchYear search year ('YYYY') 
	 * @apiParam {String} searchMonth search month ('MM')
	 * @apiParam {int}  page current Page
	 * 
	 * @apiSuccess DeleteReceipt Receipt was delete
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      [{"total_price":xx000,"meal_type":(0,1,2,3),"etc":"",
	 *        "cnt":Number,"settling_date":"date","pay_seq":Number},,{}]
	 *     }
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/deleteReceipt")
	public List<Map> deleteReceipt(int seq, String searchYear, String searchMonth,int page) throws SQLException{
		List<Map> list = 	null;	
		
		Map<String, Object>params = new HashMap<String, Object>();
		
		int startIndex = 0;
		int limit = 5;
		startIndex = PagingUtils.startIndex(page, limit);
		
		params.put("seq", seq);
		
		service.deleteReceipt(params);
		
		params.remove("seq");
		params.put("searchYear", searchYear);
		params.put("searchMonth", searchMonth);
		params.put("startIndex", startIndex);
		params.put("limit", limit);
		
		list = service.getFamilyList(params);	
		
		return list;
	}
	
	/**
	 * @api {post} /getFamilyMembers [GetFmilyMember]
	 * @apiVersion 0.1.0
	 * @apiName GetFmilyMember
	 * @apiGroup Receipt
	 * 
	 * @apiDescription If you access from the android must have access to the token value.
	 * 
	 * @apiParam {String} searchDate Search date 'YYYY-MM-DD'
	 * 
	 * @apiSuccess GetFmilyMember Family Members was exist
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      {"code":"Member Code",
	 *       "r_code":"Restaurant Code",
	 *       "r_name":"Restaurant Name",
	 *       "list":[{"code":"Restaurant Code","family_seq":Family Number,"member_code":"Member Code",
	 *                "restaurantName":"Restaurant Name","name":"Member Name"}]}
	 *       or
	 *       {"code":"Member Code","r_code":"","list":[],"r_name":""}
	 *     }
	 */
	@RequestMapping("/getFamilyMembers")
	public  Map<String, Object> getFamilymembers(HttpServletRequest req, HttpSession session, String searchDate, String meal_type) throws SQLException{
		Map<String, Object> result =  new HashMap<String, Object>(); 
		 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 session = req.getSession();
		 int check = 0;
		 
		 
		 String headerName = req.getHeader("User-Agent");
		 String code = "";
		 
		if (headerName.contains("Android")){
			String app_id = (String) session.getAttribute("id");
			Map<String, String> params = new HashMap<String, String>();
			params.put("id", app_id);
			MemberVO memberInfo = memberservice.getMemberInfo(params);
			
			code= memberInfo.getCode();
		}else{
			code= (String) session.getAttribute("code");
			if(session.getAttribute("code") == null){
				return result;
			}
		}
		check = alreadyInsertCheck(searchDate,meal_type, code);
		if(check > 0){
			result.put("check", "no");
		}else{
			result.put("check", "yes");
		}
		 
		Map<String, String>params = new HashMap<String, String>();
		params.put("searchDate", searchDate);
		params.put("meal_type", meal_type);
		params.put("code", code);
		
		list = service.getFamilyInfo(params);
		
		List<Map<String,Object>> listMap = list;  
		String r_code ="";
		String r_name ="";
		for (int i = 0; i < listMap.size(); i++) {
			r_code = listMap.get(i).get("code").toString();
			r_name = listMap.get(i).get("restaurantName").toString();
		}
		result.put("list", list);
		result.put("r_code", r_code);
		result.put("r_name",r_name);
		result.put("code",code);
		
		return result;
	}
	
	/**
	 * @api {post} /findMemberName [AddMember]
	 * @apiVersion 0.1.0
	 * @apiName AddMember
	 * @apiGroup Receipt
	 * 
	 * @apiDescription If you access from the android must have access to the token value.
	 * 
	 * @apiParam {String} member_name Members Name
	 * 
	 * @apiSuccess FindMember Member Name was exist
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *     	{"code":"Member Code","id":null,"name":"Member Name","reg_date":null,"state":null,"grade":null}
	 *		  or
	 *     	{"code":"no Have Member Code","id":null,"name":null,"reg_date":null,"state":null,"grade":null}
	 *     }
	 */
	@RequestMapping("/findMemberName")
	public  MemberVO findMemberName(HttpServletRequest req, HttpSession session, String member_name) throws SQLException{
		Map<String, Object> params =  new HashMap<String, Object>(); 
		params.put("member_name", member_name);
		MemberVO memberInfo = new MemberVO();
		memberInfo = service.findeMemberInfo(params);
		if(memberInfo == null){
			memberInfo = new MemberVO();
			memberInfo.setCode("no Have Member Code");
		}
		return memberInfo;
	}
	
	/**
	 * @api {post} /findRestaurantName [ModifyRestaurant]
	 * @apiVersion 0.1.0
	 * @apiName ModifyRestaurant
	 * @apiGroup Receipt
	 * 
	 * @apiDescription If you access from the android must have access to the token value.
	 * 
	 * @apiParam {String} restaurant_name Restaurants Name
	 * 
	 * @apiSuccess FindRestaurant Restaurant Name was exist
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      {"code":"Restaurant Code","menu":null,"name":"Restaurant Name","reg_id":null,"reg_date":null,"tag":null,"use_yn":null,"update_id":null,"update_date":null,"imageVO":null}
	 *      or
	 *      {"code":"no Have Restaurant Code","menu":null,"name":"Restaurant Name","reg_id":null,"reg_date":null,"tag":null,"use_yn":null,"update_id":null,"update_date":null,"imageVO":null}
	 *     }
	 */
	@RequestMapping("findRestaurantName")
	public RestaurantVO findRestaurantname(HttpServletRequest req, HttpSession session, String restaurant_name) throws SQLException{
		Map<String, Object> params =  new HashMap<String, Object>(); 
		params.put("restaurant_name", restaurant_name);
		RestaurantVO restaurantInfo = new RestaurantVO();
		
		restaurantInfo = service.findeRestaurantInfo(params);
		if(restaurantInfo == null){
			restaurantInfo =  new RestaurantVO();
			restaurantInfo.setCode("no Have Restaurant Code");
		}
		return restaurantInfo;
	}
	
	/**
	 * @api {post} /getParticipantNames [ParticipantNames]
	 * @apiVersion 0.1.0
	 * @apiName ParticipantNames
	 * @apiGroup Receipt
	 * 
	 * @apiParam {String} pay_seq  Receipts unique SEQ  
	 * 
	 * @apiSuccess FindParticipantMembers Participant Members Name was exist
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      ["membersName,,,,membersName"]
	 *     }
	 */
	@RequestMapping("/getParticipantNames")
	public List<String> getparticipantName(String pay_seq) throws SQLException{
		List<String> nameList = new ArrayList<String>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> params =  new HashMap<String, Object>(); 
		params.put("pay_seq", pay_seq);
		
		list = service.getParticipantNames(params);

		String members = "";
        for(int i = 0; i < list.size(); i++)
        	
        	if(i == list.size() - 1)
        		members = (new StringBuilder(String.valueOf(members))).append(list.get(i).get("name")).toString();
        	else
        		members = (new StringBuilder(String.valueOf(members))).append(list.get(i).get("name")).append(",").toString();
		
		nameList.add(members);
		
		return nameList;
	}
	
	@RequestMapping("/getReceiptInfo")
	public Map<String, Object> getreceiptinfo(String seq) throws SQLException{
		Map<String, Object> params = new HashMap<String, Object>();
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		PayVO payInfo = new PayVO();
		
		params.put("seq", seq);
		list = service.getReceiptMember(params);
		payInfo = service.getReceiptInfo(params);
		
		params.put("list", list);
		params.put("payInfo", payInfo);
		
		return params;
	}
	
	/**
	 * @api {post} /addReceipt [AddReceipt]
	 * @apiVersion 0.1.0
	 * @apiName AddReceipt
	 * @apiGroup Receipt
	 * 
	 * @apiDescription If you access from the android must have access to the token value.
	 * 
	 * @apiParam {List} realMember Of the participants(Members) unique CODE
	 * @apiParam {String} card_type Cards type
	 * @apiParam {String} meal_type Meals type 
	 * @apiParam {String} etc Other Description 
	 * @apiParam {String} restaurant_code Restaurants unique CODE
	 * @apiParam {String} total_price The total amount used 
	 * @apiParam {String} settling_date Payment Date ('YYYY-MM-DD')
	 * @apiParam {String} card_no Rear four-digit card number
	 * @apiParam {String} reg_code Users unique CODE
	 * 
	 * @apiSuccess AddReceipt Receipt add success
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      {"restaurant_code":"Restaurant Code","memberList":"Member Code,Member Code,Member Code",
	 *       "card_no":"Number","total_price":"xx000","month":"Date","meal_type":"(0,1)","etc":"",
	 *       "year":"Date","settling_date":"Date","card_type":"Number","day":"Date","reg_code":"Member Code"}
	 *     }
	 */
	@RequestMapping("/addReceipt")
	public Map<String, Object> addReceipt(@RequestParam("realMember") List<String> memberList,
							@RequestParam("card_type") String card_type,
							@RequestParam("meal_type") String meal_type,
							@RequestParam("etc") String etc,
							@RequestParam("restaurant_code") String restaurant_code,
							@RequestParam("total_price") String total_price,
							@RequestParam("settling_date") String settling_date,
							@RequestParam("card_no") String card_no,
							@RequestParam("reg_code") String reg_code,
							HttpServletRequest request,
							Model model) throws SQLException{
		
		String members = "";
        for(int i = 0; i < memberList.size(); i++)
        	
        	if(i == memberList.size() - 1)
        		members = (new StringBuilder(String.valueOf(members))).append((String)memberList.get(i)).toString();
        	else
        		members = (new StringBuilder(String.valueOf(members))).append((String)memberList.get(i)).append(",").toString();
        	
        Map<String, Object> params = new HashMap<String, Object>();
        String headerName = request.getHeader("User-Agent");
        params.put("card_type", card_type);
        params.put("meal_type", meal_type);
        params.put("memberList", members);
        params.put("restaurant_code", restaurant_code);
        params.put("etc", etc);
        params.put("settling_date", settling_date);
        params.put("card_no", card_no);
        params.put("total_price", total_price);
        if(headerName.contains("Android")){
        	params.put("reg_code", reg_code);
        }else{
        	params.put("reg_code", request.getSession().getAttribute("code"));
        }
        service.insertReceipt(params, memberList);
        
		params.put("year", settling_date.substring(0, 4));
		params.put("month",settling_date.substring(5, 7));
		params.put("day", settling_date.substring(8, 10));
		
		return params;
	}
		
	public int alreadyInsertCheck(String searchDate, String meal_type, String code) throws SQLException{
		int result = 0;
		String member_code = code;
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("member_code",member_code);
		params.put("searchDate",searchDate);
		params.put("meal_type",meal_type);
		
		result = service.insertCheck(params);
		return result;
	}
}
