package com.ex2i.samsamohoh.controller;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ex2i.samsamohoh.service.RestaurantService;
import com.ex2i.samsamohoh.vo.ReplyVO;
import com.ex2i.samsamohoh.vo.RestaurantVO;

@RestController
public class RestaurantController {
	
	@Autowired
	private RestaurantService service;
	Logger log = Logger.getLogger(this.getClass());
	private static final LocalTime startTime = LocalTime.now();
	private static final LocalTime endTime = LocalTime.of(16, 00);
	
	
	@RequestMapping(value = "/doModifyDetailRestaurantAjax", method = RequestMethod.POST)
	public List<RestaurantVO> doModifyDetailRestaurant(	@RequestParam("CODE") String code,	
													Model model,
													HttpServletRequest request,
													HttpSession session
													) throws Exception{
		Map<String, String> params = new HashMap<>();
		params.put("code", code);
		List<RestaurantVO> list = null;
		
		session = request.getSession();
		if(session.equals(null)){
			return list;
		}
		
		list = setRestaurantDetailDateList(params);
		return list;
	}
	
	/**
	 * @api {post} /doDetailRestaurantAjax [ModifyRestaurantInfo]
	 * @apiVersion 0.1.0
	 * @apiName ModifyRestarurantInfo
	 * @apiGroup Restaurant
	 * 
	 * @apiParam {String} CODE Restaurants unique CODE
	 * 
	 * @apiSuccess ExistRestaurant The restaurantsInfo was exist
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      {"restaurant":[{"code":"Restaurant Code",
                         "menu":"Restaurant Menu",
                         "name":"Restaurant Name",
                         "reg_id":"writer",
                         "reg_date":"YYYY-MM-DD HH:mm",
                         "tag":"Tags",
                         "use_yn":"Y or N",
                         "update_id":"",
                         "update_date":"YYYY-MM-DD HH:mm:ss.0",
                         "imageVO":{"seq":Number,
                                    "real_name":"file.jpg",
                                    "change_name":"prefix-file.jpg",
                                    "file_path":"filePath",
                                    "reg_date":"YYYY-MM-DD HH:mm:ss.0",
                                    "code":"Restaurant Code"}}]}
	 *     }
	 */
	@RequestMapping(value = "/doDetailRestaurantAjax", method = RequestMethod.POST)
	public List<RestaurantVO> doDetailRestaurant(	@RequestParam("CODE") String code,	
													Model model,
													HttpServletRequest request,
													HttpSession session
													) throws Exception{
		Map<String, String> params = new HashMap<>();
		params.put("code", code);
		List<RestaurantVO> list = null;
		
		session = request.getSession();
		if(session.equals(null)){
			return list;
		}
		
		list = setRestaurantDetailDateList(params);
		return list;
	}
	
	public List<RestaurantVO> setRestaurantDetailDateList(Map<String, String> params) throws Exception{
		List<RestaurantVO> list = null;
		list = service.getRestaurantDetailtoCode(params);
		String date = "";
		for(int i = 0; i < list.size(); i++){
			date = subStringDate(list.get(i).getReg_date());
			list.get(i).setReg_date(date);
		}
		
		return list;
	}
	
	/**
	 * @api {post} /doGetListRestaurantPageAjax [RestaurantList]
	 * @apiVersion 0.1.0
	 * @apiName RestaurantList
	 * @apiGroup Main
	 * @apiSuccess ExistRestaurant The restaurantsInfo was exist
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
     *      [
	 *      	{
     *      		myFam = yes or no
     *      	},
     *      	{
     *       		code = Restaurant Code,
     *        		change_name = Image Change Name,
     *        		name = Restaurant Name,
     *        		real_name = Image Real Name,
     *        		member_code = Member Code      
     *      	}
     *      ] 
	 *     }
	 */
	@RequestMapping(value = "/doGetListRestaurantPageAjax", method = RequestMethod.POST)
	public List<Map<String, Object>> doGetListRestaurantAjax(HttpServletRequest request,
															HttpSession session) throws Exception{
		
		List<Map<String, Object>> restaurantInfo = new ArrayList<>();
		Map<String, Object> member_code = new HashMap<>();
		
		session = request.getSession();
		if(session.equals(null)){
			return restaurantInfo;
		}
		
		restaurantInfo = service.getRestaurantInfo();
		
		restaurantInfo.add(0, getButtonState(session));
		
		String headerName = request.getHeader("User-Agent");
		if (headerName.contains("Android")){
			member_code.put("member_code", MobileMemberCode(session));
			restaurantInfo.add(member_code);
		}

		return restaurantInfo;		
	}
	
	/**
	 * @api {post} /doSearchRestaurantAjax [SearchRestaurant]
	 * @apiVersion 0.1.0
	 * @apiName SearchRestaurant
	 * @apiGroup Main
	 * 
	 * @apiParam {String} SEARCH_WORD search words(restaurant name, tag, menu)
	 * 
	 * @apiSuccess SearchRestaurant searchResult
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *     	[{
	 *     		file_path=C:\wrokspace\..., 
	 *     		code=Restaurant_code, 
	 *     		change_name=ImageChangeName, 
	 *     		name=Restaurant_name
	 *     	]}
	 *     }
	 */
	@RequestMapping(value = "/doSearchRestaurantAjax", method = RequestMethod.POST)
	public List<Map<String, Object>> doSearchRestaurantAjax(@RequestParam("SEARCH_WORD") String search_word, HttpSession session, HttpServletRequest request) throws SQLException{
		List<Map<String, Object>> restaurantInfo = new ArrayList<>();
		
		session = request.getSession();
		if(session.equals(null)){
			return restaurantInfo;
		}	
		
		restaurantInfo = service.getSearchRestaurant(search_word);
		
		restaurantInfo.add(0, getButtonState(session));		
		
		return restaurantInfo;
	}
	
	public Map<String, Object> getButtonState(HttpSession session) throws SQLException{
		Map<String, Object> param = new HashMap<>();
		param.put("member_code", session.getAttribute("code"));
		param.put("meal_type", mealTypeResult());
		
		int count = service.getFamilyMembers(param);		
		Map<String, Object> result = new HashMap<>();

		if(count > 0){
			result.put("myFam", "yes");
		} else {
			result.put("myFam", "no");
		}
		return result;
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
	 * @api {post} /doGetRestaurantReplyAjax [ReplyList]
	 * @apiVersion 0.1.0
	 * @apiName ReplyList
	 * @apiGroup Reply
	 * 
	 * @apiParam {String} CODE Restaurants unique CODE
	 * 
	 * @apiSuccess ExistRestaurantReply The Reply was exist
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *     	[{
	 *     		content=Reply Content,
				id=Member ID,
				reg_code=Member Code,
				name=Member Name,
				restaurant_code=null,
				write_date=Date,
				seq=Number
	 *     	]}
	 *     }
	 */
	@RequestMapping(value = "/doGetRestaurantReplyAjax", method = RequestMethod.POST)
	public List<ReplyVO> doGetRestaurantReplyAjax(@RequestParam("CODE") String code,
													HttpServletRequest request,
													HttpSession session) throws Exception{
		List<ReplyVO> restaurantReplyList = null;
		
		session = request.getSession();
		if(session.equals(null)){
			return restaurantReplyList;
		}
		
		restaurantReplyList = setRestaurantReplyDateList(code);
		return restaurantReplyList;		
	}
	
	public List<ReplyVO> setRestaurantReplyDateList(String code) throws SQLException{
		List<ReplyVO> restaurantReplyList = null;
		restaurantReplyList = service.getReplyContent(code);
		String date = "";
		for(int i = 0; i < restaurantReplyList.size(); i++){
			date = subStringDate(restaurantReplyList.get(i).getWrite_date());
			restaurantReplyList.get(i).setWrite_date(date);
		}
		return restaurantReplyList;
	}
	
	public String subStringDate(String date){
		String subdate = date.substring(0, 16);
		return subdate;
	}
	
	/**
	 * @api {post} /doSetRestaurantReplyAjax [RegisterReply]
	 * @apiVersion 0.1.0
	 * @apiName RegisterReply
	 * @apiGroup Reply
	 * 
	 * @apiParam {String} CODE Restaurants unique CODE
	 * @apiParam {String} CONTENT Reply CONTENT
	 * @apiParam {String} CODE Users Reply unique CODE
	 * 
	 * @apiSuccess ExistRestaurantReply The RegisterReply was exist
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *     		web - "1"
	 *      	app - "true" 
	 *     }
	 */
	@RequestMapping(value = "/doSetRestaurantReplyAjax", method = RequestMethod.POST)
	public String doSetRestaurantReplyAjax(@RequestParam("RESTAURANT_CODE") String code,
											@RequestParam("CONTENT") String content,
											@RequestParam("MEMBER_CODE") String member_code,
											HttpSession session,
											HttpServletRequest request,
											Model model
											) throws Exception{
		session = request.getSession();
		if(session.equals(null)){
			return "0";
		}
		
		int maxSeq = getMaxReply_seq();
		ReplyVO replyVO = new ReplyVO();
		replyVO.setSeq(maxSeq);
		replyVO.setContent(content);
		replyVO.setMember_code(member_code);
		replyVO.setRestaurant_code(code);
		
		int replyResult = service.setRestaurantReply(replyVO);

		return String.valueOf(replyResult);		
	}
	
	public int getMaxReply_seq() throws SQLException{
		int maxSeq = 0;
		maxSeq = service.getMaxReplySeq();
		if(maxSeq == 0){
			maxSeq = 1;
		} else{
			maxSeq = maxSeq + 1;
		}
		return maxSeq;
	}
	
	/**
	 * @api {post} /doDeleteRestaurantReplyAjax [DeleteReply]
	 * @apiVersion 0.1.0
	 * @apiName DeleteReply
	 * @apiGroup Reply
	 * 
	 * @apiParam {String} CODE Restaurants unique CODE
	 * @apiParam {String} SEQ Reply unique SEQ
	 * 
	 * @apiSuccess ExistRestaurantReply The RegisterReply was exist
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *     		web - "1"
	 *      	app - "true" 
	 *     }
	 */
	@RequestMapping(value = "/doDeleteRestaurantReplyAjax", method = RequestMethod.POST)
	public int doDeleteRestaurantReplyAjax(@RequestParam("CODE") String member_code,
											@RequestParam("SEQ") int seq,
											HttpServletRequest request,
											Model model,
											HttpSession session) throws Exception{
		session = request.getSession();
		if(session.equals(null)){
			return 0;
		}
		
		ReplyVO replyVO = new ReplyVO();
		String headerName = request.getHeader("User-Agent");
		if (headerName.contains("Android")){
			member_code = MobileMemberCode(session);
		}
		replyVO.setSeq(seq);
		replyVO.setRestaurant_code(member_code);
		int deleteResult = service.setDeleteRestaurantReply(replyVO);
		return deleteResult;		
	}
	
	public String MobileMemberCode(HttpSession session) throws SQLException{
		String param = (String) session.getAttribute("id");
		String memberCode = service.getMemberCode(param);
		return memberCode;
	}
	
	@RequestMapping(value = "/doRestaurantNameCheckAjax", method = RequestMethod.POST)
	public int doRestaurantNameCheckAjax(@RequestParam("RESTAURANT_NAME") String restaurant_name, 
														HttpServletRequest request, 
														HttpSession session) throws Exception{
		session = request.getSession();
		if(session.equals(null)){
			return 0;
		}
		
		return service.getRestaurantNameCheck(restaurant_name);
	}
}