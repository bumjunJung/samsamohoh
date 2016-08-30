package com.ex2i.samsamohoh.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ex2i.samsamohoh.service.MemberService;
import com.ex2i.samsamohoh.vo.MemberVO;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.json.jackson2.JacksonFactory;

@RestController
public class LoginController {

	@Autowired
	private MemberService service;

	private static final JacksonFactory jsonFactory = new JacksonFactory();

	public GoogleIdToken readIdToken(String idtoken) throws IOException {
		GoogleIdToken idToken = GoogleIdToken.parse(jsonFactory, idtoken);
		return idToken;
	}

	public Payload getPayload(GoogleIdToken idToken) throws SQLException {
		Payload payload = null;
		if (idToken != null) {
			payload = idToken.getPayload();
		} else {
			System.out.println("Invalid ID token.");
		}
		;
		return payload;
	}

	public String setUserId(Payload payload) {
		String userId = payload.getEmail();
		return userId;
	}

	public String readEmail(String userId) {
		int mainStartNum = userId.indexOf('@');
		String mailName = userId.substring(mainStartNum);
		return mailName;
	}

	public MemberVO findUserName(MemberVO memberVO) throws SQLException {
		MemberVO memberVOInfo = new MemberVO();
		memberVOInfo = service.getMemberInfoToCode(memberVO);
		return memberVOInfo;
	}
	/**
	   * @api {POST} /compareIdAjax [WebLogin]
	   * @apiVersion 0.1.0
	   * @apiName UserLogin
	   * @apiGroup Login
	   * @apiParam {idtoken} idtoken Users unique TOKEN.
	   * 
	   * @apiSuccess ExistMember The memberInfo was exist
	   * @apiSuccessExample Success-Response
	   *     HTTP/1.1 200 OK
	   *     {
	   *     code = Member Code
	   *     grade = User Grade
	   *     id = User Email
	   *     name = User Name
	   *     reg_date = Register Date
	   *     state = User State     
	   *      }
	   */
	@RequestMapping(value = "/compareIdAjax", method = RequestMethod.POST)
	public HashMap<String, Object> idCheckAnduserInfoInsert(String idtoken, HttpSession session) throws IOException, SQLException {
		GoogleIdToken idToken = readIdToken(idtoken);
		Payload payload = getPayload(idToken);
		MemberVO memberVOToken = new MemberVO();
		MemberVO searchMemberVO = new MemberVO();
		HashMap<String, Object> retVal = new HashMap<>();

		MemberVO memberVO = new MemberVO();

		String id = payload.getEmail();
		
		String oursEmail = "@ex2i.com";
		int mailIndex = id.indexOf('@');
		String userEmail = id.substring(mailIndex);
		
		if(!userEmail.equals(oursEmail)){
			System.out.println("회사id아님");
		}else{
			memberVOToken.setId(payload.getEmail());
			searchMemberVO = service.getMemberInfoToToken(memberVOToken);
			if(searchMemberVO == null ){
				sign_in(idtoken);
				
				ArrayList<MemberVO> memberInfoList = userInfoInsert(searchMemberVO, memberVO, memberVOToken);
				retVal.put("memberInfoList", memberInfoList);
			} else {			
				ArrayList<MemberVO> memberInfoList = userInfoInsert(searchMemberVO, memberVO, memberVOToken);
				retVal.put("memberInfoList", memberInfoList);
			}
		}
				
		return retVal;
	}
	
	public ArrayList<MemberVO> userInfoInsert(MemberVO searchMemberVO, MemberVO memberVO, MemberVO memberVOToken) throws SQLException{
		searchMemberVO = service.getMemberInfoToToken(memberVOToken);
		String memeberCode = findCodeToId(searchMemberVO);
		
		memberVO.setCode(memeberCode);
		memberVO.setState(searchMemberVO.getState());
		memberVO.setName(searchMemberVO.getName());
		memberVO.setId(searchMemberVO.getId());
		memberVO.setGrade(searchMemberVO.getGrade());
		memberVO.setCode(memeberCode);
		/*2016-07-13 추가*/
		if(!memeberCode.equals("absence")){
			memberVO.setState(searchMemberVO.getState());
			memberVO.setName(searchMemberVO.getName());
			memberVO.setId(searchMemberVO.getId());
			memberVO.setState(searchMemberVO.getState());
			memberVO.setGrade(searchMemberVO.getGrade());
		}

		ArrayList<MemberVO> memberInfoList = new ArrayList<>();
		memberInfoList.add(memberVO);
	
		return memberInfoList;
	}

	public String findCodeToId(MemberVO memberVO) throws SQLException {
		int count = service.compareMemberId(memberVO);
		String result;
		if (count > 0) {
			result = getMemberCode(memberVO);
		} else {
			result = "absence";
		}
		return result;
	}

	public String getMemberCode(MemberVO memberVO) throws SQLException {
		String code = service.getMemberCode(memberVO);
		return code;
	}
	
	/**
	   * @api {POST} /doLogin [WebLogin]
	   * @apiVersion 0.1.0
	   * @apiName UserLogin
	   * @apiGroup Login
	   * @apiParam {idtoken} idtoken Users unique TOKEN.
	   * 
	   * @apiSuccess ExistMember The memberInfo was exist
	   * @apiSuccessExample Success-Response
	   *     HTTP/1.1 200 OK
	   *     {
	   *      PageTransfer -> restaurant/main    
	   *      }
	   */
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public ModelAndView sign_in(String idtoken) throws IOException, SQLException {
		ModelAndView mav = new ModelAndView("restaurant/main");
		GoogleIdToken idToken = readIdToken(idtoken);
		Payload payload = getPayload(idToken);
		insertMemInfo(payload);
		return mav;
	}

	public void insertMemInfo(Payload payload) throws SQLException {
		String code = createCode();
		String email = payload.getEmail();
		String name = (String) payload.get("name");

		MemberVO memberVO = new MemberVO();
		memberVO.setCode(code);
		memberVO.setId(email);
		memberVO.setName(name);

		service.setMemeber(memberVO);
	}

	public int getMaxMemberCode() throws SQLException {
		int realCode = codeSubStringToAdd();
		return realCode;
	}

	public int codeSubStringToAdd() throws SQLException {
		String code = service.getMaxMemberCode();
		String stringCode = null;

		if (code == null || code.equals("")) {
			code = "2016-07-04_0";
		};
		stringCode = code.substring(11);

		int realCode = Integer.parseInt(stringCode) + 1;
		return realCode;
	}

	public String createCodePrefix() {
		LocalDate time = LocalDate.now();
		String codePrefix = time.toString();
		return codePrefix;
	}

	public String createCode() throws SQLException {
		String code;
		code = createCodePrefix() + "_" + getMaxMemberCode();
		return code;
	}
	/**
	 * @api {post} /loginCheck [AppLogin]
	 * @apiVersion 0.1.0
	 * @apiName Login
	 * @apiGroup Login
	 * 
	 * @apiDescription If you access from the android must have access to the token value.
	 * 
	 * @apiParam {String} id Users unique ID
	 * 
	 * @apiSuccess AddReceipt Receipt add success
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      {"state":"true"}
	 *     }
	 */
	@RequestMapping("/loginCheck")
	public Map<String, String> putSession(String id,HttpSession session,HttpServletRequest req) throws SQLException, IOException{
		Map<String, String> params = new HashMap<String, String>();
		
		String headerName = req.getHeader("User-Agent");
		String session_id = (String) session.getAttribute("id");
		String state = "";
		
		if(headerName.contains("Android")){
			params.put("id", session_id); 
		}else{
			params.put("id", id);
		}
		
		MemberVO memberInfo = service.getMemberInfo(params);

		if(memberInfo == null){
			if(headerName.contains("Android")){
				
				GoogleIdToken idToken = readIdToken(req.getParameter("idToken"));
				Payload payload = getPayload(idToken);
				insertMemInfo(payload);
				
				params.put("id", payload.getEmail());
				memberInfo = service.getMemberInfo(params);
			}
		}
			session.setAttribute("id", memberInfo.getId());
			session.setAttribute("code", memberInfo.getCode());
			session.setAttribute("grade", memberInfo.getGrade());
			session.setAttribute("name", memberInfo.getName());
			state = "true";
			params.remove("id");
			params.put("state", state);
		return params;
	}
}