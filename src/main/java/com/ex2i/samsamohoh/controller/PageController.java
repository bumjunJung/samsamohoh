package com.ex2i.samsamohoh.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	// -- 로그인 --
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView sign_inGET(HttpSession session) throws SQLException {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login/login2/login");
		return mav;
	}

	@RequestMapping(value = "/memberLogin", method = RequestMethod.GET)
	public ModelAndView loginAfter() throws SQLException {
		String url = "redirect:/restaurantPage";	
		ModelAndView mav = new ModelAndView(url);
		return mav;
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView errorPage() {
		ModelAndView mav = new ModelAndView("login/login2/error");
		return mav;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logOutPage(HttpServletRequest req, HttpSession session){
		session = req.getSession();
        session.removeAttribute("id");
        session.invalidate();
        ModelAndView mav = new ModelAndView("login/login2/logout");
		return mav;
	}
	
	// -- 회원관리 --
	@RequestMapping("/memberList")
	public ModelAndView showMemberList(){
		return new ModelAndView("memberMgr/memberList");
	}
	// -- 팸기록 --
	@RequestMapping("/familyList")
	public ModelAndView showFamilyList(){
		return new ModelAndView("familyMgr/familyList");
	}
	// -- 멤버이력 --
	/**
	 * @api {get} /member/member/analysis [MemberAnalysis]
	 * @apiVersion 0.1.0
	 * @apiName MemberAnalysis
	 * @apiGroup WebView
	 * 
	 * @apiDescription If you access from the android must have access to the token value.
	 */
	@RequestMapping("/member/member/analysis")
	public ModelAndView showMemberAnalysis(HttpServletRequest req){
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = req.getSession();
		String headerName = req.getHeader("User-Agent");
		
		System.out.println(session.getAttribute("id"));
		if (headerName.contains("Android")) {
			mav.setViewName("login/login2/appAnalysisMember");
		}else{
			mav.setViewName("memberMgr/analysisMember");
		}
		return mav;
	}
	@RequestMapping("/admin/member/analysis")
	public ModelAndView showAdminAnalysis(){
		return new ModelAndView("memberMgr/analysisMember");
	}
	// -- 식당이력 --
	/**
	 * @api {get} /member/restaurant/analysis [RestaurantAnalysis]
	 * @apiVersion 0.1.0
	 * @apiName RestaurantAnalysis
	 * @apiGroup WebView
	 * 
	 * @apiDescription If you access from the android must have access to the token value.
	 */
	@RequestMapping("/member/restaurant/analysis")
	public ModelAndView showMemberRestaurantAnlysis(HttpServletRequest req){
		
	ModelAndView mav = new ModelAndView();
		
		String headerName = req.getHeader("User-Agent");
		
		if (headerName.contains("Android")) {
			mav.setViewName("login/login2/appAnalysisRestaruant");
		}else{
			mav.setViewName("memberMgr/analysisRestaruant");
		}
		return mav;
	}
	@RequestMapping("/admin/restaurant/analysis")
	public ModelAndView showAdminRestaurantAnlysis(){
		return new ModelAndView("memberMgr/analysisRestaruant");
	}

	// -- 지출이력 --
	@RequestMapping("/admin/pay/analysis")
	public ModelAndView showPayanalysis(){
		return new ModelAndView("payMgr/analysisPay");
	}
	//-- 영수증 관리 --
	@RequestMapping("/receipt")
	public String showReceiptList() throws SQLException{
		return "receipt/receiptList";
	}
	//-- 영수증 등록 --
	@RequestMapping("/insertReceiptForm")
	public ModelAndView showInsertReceiptform(){
		return new ModelAndView("receipt/insertReceipt");
	}
}
