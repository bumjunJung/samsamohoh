package com.ex2i.samsamohoh.controller;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ex2i.samsamohoh.service.FamilyService;

@RestController
public class FamilyController {
	
	@Autowired
	private FamilyService service;
	
	private static final LocalTime startTime = LocalTime.now();
	private static final LocalTime endTime = LocalTime.of(16, 00);
	
	
	/**
	 * @api {post} /doJoinSetFamilyAjax [FamilyRegister]
     * @apiVersion 0.1.0
     * @apiName FamilyRegister
     * @apiGroup Main
     * 
     * @apiParam {String} RESTAURNAT_CODE Restaurant unique CODE
     * @apiParam {String} MEMBER_CODE Users unique CODE
     * 
     * @apiSuccess ExistFamily The SetFamily was Set
     * @apiSuccessExample Success-Response
     *     HTTP/1.1 200 OK
     *     {
     *      web - "1"
     *      app - "true"
     *     }
	 */
	@RequestMapping(value = "/doJoinSetFamilyAjax", method = RequestMethod.POST)
	public String doJoinSetFamilyAjax(@RequestParam("RESTAURANT_CODE") String restaurant_code,
										@RequestParam("MEMBER_CODE") String member_code,
										HttpServletRequest request,
										Model model,
										HttpSession session) throws SQLException{
		session = request.getSession();
		if(session.equals(null)){
			return "0";
		}	
		
		Map<String, Object> familyparams = settingFamilyInfo(restaurant_code, member_code);
		Map<String, Object> familyMemberParams = new HashMap<String, Object>();
		familyMemberParams.put("member_code", member_code);
		familyMemberParams.put("restaurant_code", restaurant_code);
		
		String headerName = request.getHeader("User-Agent");
		if (headerName.contains("Android")){
			String result="";
			setFamily(familyparams, familyMemberParams);
			result = "true";
			return result;
		}
		String beforeSeq = berforeSeq(member_code);	
		Map<String, Object> beforeParams = beforeParams(member_code, beforeSeq);	
		service.dropFamilyMember(beforeParams);
		setFamily(familyparams, familyMemberParams);
		service.modifyJoinFamilyMembers(beforeParams);
		return "0";
	}	
	
	public int setFamily(Map<String, Object> familyparams, Map<String, Object> familyMemberParams) throws SQLException{
		int result = service.setJoinFamily(familyparams);
		if(result == 1){
			service.setJoinFamilyMember(familyMemberParams);
		}	
		return result;
	}
	
	public Map<String, Object> settingFamilyInfo(String restaurant_code, String member_code) throws SQLException{
		int familyMaxSeq = getMaxFamilySeq();
		int meal_type = mealTypeResult();
		
		Map<String, Object> familyparams = new HashMap<String, Object>();
		familyparams.put("maxSeq", familyMaxSeq);
		familyparams.put("restaurant_code", restaurant_code);
		familyparams.put("member_code", member_code);
		familyparams.put("meal_type", meal_type);
		return familyparams;
	}
	
	public int getMaxFamilySeq() throws SQLException{
		int maxSeq = 0;
		maxSeq = service.getMaxFamilySeq();
		if(maxSeq == 0){
			maxSeq = 1;
		} else{
			maxSeq = maxSeq + 1;
		}
		return maxSeq;
	}
	
	public int mealTypeResult(){
		int meal_type = 0;
		if(startTime.isBefore(endTime)){
			meal_type = 0;
		} else{
			meal_type = 1;
		}
		return meal_type;
	}
	
	/**
	 * @api {post} /doGetFamilyAjax [FamilyList2]
	 * @apiVersion 0.1.0
	 * @apiName FamilyList
	 * @apiGroup Main
	 * @apiSuccess ExistFamily The FamilyInfo was exist
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *       [{code=restaurantsCode, meal_type=(0,1,2,3), change_name=prefix-file.jpg, real_name=file.jpg, 
	 *         members=memberCode,memberCode,memberCode, seq=Number, restaurant_name=restaurants Name}]
	 *     }
	 */
	@RequestMapping(value = "/doGetFamilyAjax", method = RequestMethod.POST)
	public List<Map<String, Object>> doGetFamilyAjax(HttpServletRequest request, HttpSession session) throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		session = request.getSession();
		if(session.equals(null)){
			return list;
		}
		
		String headerName = request.getHeader("User-Agent");
		if (headerName.contains("Android")){
			list = service.getFamilyListAll();
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).get("members") != null){
					result = list;
				} 
			}
			return result;
		}
		
		list = service.getFamilyListAll();
		return list;			
	}

	
	/**
	 * @api {post} /doGetMembersAjax [MemberList]
	 * @apiVersion 0.1.0
	 * @apiName Family in MemberList
	 * @apiGroup Main
	 * 
	 * @apiParam {String} FAMILY_SEQ Family unique SEQ
	 * 
	 * @apiSuccess ExistFamily The Code of MemberList was exist
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *  		[{code=Restaurant_Code, members=(Member_code), (Member_code), (Member_code) , change_name=ImageChangeName}]   
	 *     }
	 */
	@RequestMapping(value = "/doGetMembersAjax", method = RequestMethod.POST)
	public List<Map<String, Object>> doGetMembersAjax(@RequestParam("FAMILY_SEQ") String family_seq, HttpServletRequest request, HttpSession session) throws SQLException{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("family_seq", family_seq);
		List<Map<String, Object>> list =  new ArrayList<>();
		session = request.getSession();
		if(session.equals(null)){
			return list;
		}	
		list = service.getMembers(params);
		return list;
	}
	
	/**
	 * @api {post} /doGetMembersNameAjax [MemberNameList]
	 * @apiVersion 0.1.0
	 * @apiName MemberNameList
	 * @apiGroup Main
	 * 
	 * @apiParam {String} MEMBER_CODE Users unique CODE
	 * 
	 * @apiSuccess ExistMember The Name of MemberList was exist
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *  		[{name=Member_name}]	 
	 *     }
	 */
	@RequestMapping(value = "/doGetMembersNameAjax", method = RequestMethod.POST)
	public List<Map<String, Object>> doGetMembersNameAjax(@RequestParam("MEMBER_CODE") String member_code, HttpServletRequest request, HttpSession session) throws SQLException{
	
		List<Map<String, Object>> list =  new ArrayList<>();
		session = request.getSession();
		if(session.equals(null)){
			return list;
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("member_code", member_code);
		
		list = service.getMembersName(params);
		return list;
	}
	
	/**
	 * @api {post} /doMoveButtonClickAjax [TransfetFamily]
	 * @apiVersion 0.1.0
	 * @apiName TransfetFamily
	 * @apiGroup Main
	 * 
	 * @apiParam {String} RESTAURNAT_CODE Restaurants unique CODE
	 * @apiParam {String} MEMBER_CODE Users unique CODE
	 * 
	 * @apiSuccess ExistFamily The TransfetFamily was exist
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      	web - "1"
	 *      	app - "true" 
	 *     }  
	 */
	@RequestMapping(value = "/doMoveButtonClickAjax", method = RequestMethod.POST)
	public int doMoveButtonClickAjax(@RequestParam("RESTAURANT_CODE") String restaurant_code,
									@RequestParam("MEMBER_CODE") String member_code, 
									HttpServletRequest request,
									Model model,
									HttpSession session) throws SQLException{	
		session = request.getSession();
		if(session.equals(null)){
			return 0;
		}			
		int moveResult = 0;
		
		String beforeSeq = berforeSeq(member_code);
		String code = restaurnat_code(restaurant_code);		
		Map<String, Object> beforeParams = beforeParams(member_code, beforeSeq);
		
		if(code == null){
			service.dropFamilyMember(beforeParams);
			doJoinSetFamilyAjax(restaurant_code, member_code, request, model, session);
			service.modifyJoinFamilyMembers(beforeParams);
		} else {
			int afterSeq = afterSeq(restaurant_code);
			Map<String, Object> params = afterParams(restaurant_code, member_code, afterSeq);
			doMove(params, beforeParams);
		}
		active_typeChange();
		
		return moveResult;	
	}
	
	public void doMove(Map<String, Object> params, Map<String, Object> beforeParams) throws SQLException{
		service.dropFamilyMember(beforeParams);
		doMoveFamily(params);
		service.modifyJoinFamilyMembers(params);
		service.modifyJoinFamilyMembers(beforeParams);
	}
	
	public String berforeSeq(String member_code) throws SQLException{
		String seq;
		seq  = service.getBeforeFamilySeq(member_code);
		return seq;
	}
	
	public int afterSeq(String restaurant_code) throws SQLException{
		int seq;
		seq  = service.getAfterFamilySeq(restaurant_code);
		return seq;
	}
	
	public String restaurnat_code(String restaurant_code) throws SQLException{
		String result = "";
		result  = service.getRestautrant_codeToFamily(restaurant_code);
		return result;
	}
	
	public Map<String, Object> afterParams(String restaurant_code, String member_code, int afterSeq){
		Map<String, Object> afterParams = new HashMap<String, Object>();
		afterParams.put("restaurant_code", restaurant_code);
		afterParams.put("member_code", member_code);
		afterParams.put("family_seq", afterSeq);
		return afterParams;
	}
	
	public Map<String, Object> beforeParams(String member_code, String beforeSeq){
		Map<String, Object> beforeParams = new HashMap<String, Object>();
		beforeParams.put("member_code", member_code);	
		beforeParams.put("family_seq", beforeSeq);
		return beforeParams;
	}	
	
	public void active_typeChange() throws SQLException{
		service.modifyActive_type();
	}
	
	public void doMoveFamily(Map<String, Object> params) throws SQLException{
		service.setMoveFamily(params);
	}
	
	public String mobileMemberCode(HttpSession session) throws SQLException{
		String param = (String) session.getAttribute("id");
		String memberCode = service.getMemberCode(param);
		return memberCode;
	}

}
