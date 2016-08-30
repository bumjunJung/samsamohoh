package com.ex2i.samsamohoh.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ex2i.samsamohoh.service.RestaurantService;
import com.ex2i.samsamohoh.vo.RestaurantVO;

@Controller
public class RestaurantPageController {
	@Autowired
	private RestaurantService service;

	@RequestMapping(value = "/restaurantPage", method = RequestMethod.GET)
		public ModelAndView restaurant(){
			ModelAndView mav = new ModelAndView("restaurant/main");
			return mav;
		}	
		
		@RequestMapping(value = "/addRestaurantPage", method = RequestMethod.GET)
		public ModelAndView addRestaurant(){
			ModelAndView mav = new ModelAndView("restaurant/addrestaurant");
			return mav;
		}
		
		/**
		 * @api {post} /doAddRestaurant [AddRestaurant]
		 * @apiVersion 0.1.0
		 * @apiName AddRestarurant
		 * @apiGroup Restarurant
		 * 
		 * @apiDescription If you access from the android must have access to the token value.
		 * 
		 * @apiParam {String} reg_id Users unique ID.
		 * @apiParam {String} restaurantName add restaurant naming
		 * @apiParam {MultipartFile} restaurantImage restaurant use image
		 * @apiParam {String} restaurantMenu restaurant menu
		 * @apiParam {String} restaurantTag restaurant related tags
		 * @apiParam {String} type intro web or app
		 * 
	 	 * @apiSuccess ExistRestaurant The restaurantsList was exist
		 * @apiSuccessExample Success-Response
		 *     HTTP/1.1 200 OK
		 *     {
		 *      web - "redirect:/detailRestaurant"
		 *      app - "true"
		 *     }
		 */
		@RequestMapping(value = "/doAddRestaurant", method = RequestMethod.POST)
		public String doAddRestaurant(@RequestParam("restaurantName") String restaurantName,
											@RequestParam("restaurantImage") MultipartFile restaurantImage,
											@RequestParam("restaurantMenu") String restaurantMenu,
											@RequestParam("restaurantTag") String restaurantTag,
											@RequestParam("reg_id") String reg_id,
											RedirectAttributes redirectAttributes,
											HttpServletRequest request,
											Model model) throws Exception{
 			RestaurantVO setRestaurantVO = inputRestaursntVO(restaurantName, restaurantMenu, reg_id, restaurantTag);
			String code = setRestaurantVO.getCode();
			Map<String, Object> setRestaurantMap = inputRestaursntMap(setRestaurantVO); 
			Map<String, Object> setImageFileMap = inputImageFileMap(restaurantImage, code);
			
			service.setRestaurant(setRestaurantMap, setImageFileMap, request);	
			
			String headerName = request.getHeader("User-Agent");
			
			if (headerName.contains("Android")){
				String result="";
				model.addAttribute("json","true");
				result = "login/login2/androidResult";
				return result;
			}else{
				redirectAttributes.addAttribute("CODE", code);
				String url = "redirect:/detailRestaurant";	
				return url;
			}
		}

		/**
	     * @api {get} /detailRestaurant [detailRestaurant]
	     * @apiVersion 0.1.0
	     * @apiName detailRestaurant
	     * @apiGroup WebView
	     * 
	     * @apiDescription If you access from the android must have access to the token value.
	     * 
	     * @apiParam {String} CODE Users unique CODE.
	     * 
	     */

		@RequestMapping(value = "/detailRestaurant", method = RequestMethod.GET)
		public String goDetailPage(@RequestParam("CODE") String code,
									Model model,
									HttpServletRequest request,
									HttpSession session){
			String url = "restaurant/detailrestaurant";	
			
			
			
			model.addAttribute("CODE", code);
			return url;
		}
		
		/**
		 * @api {get} /deleteRestaurant [DeleteRestaurant]
		 * @apiVersion 0.1.0
		 * @apiName DeleteRestaurant
		 * @apiGroup Restarurant
		 * 
		 * @apiDescription If you access from the android must have access to the token value.
		 * 
		 * @apiParam {String} CODE Users unique CODE.
		 * @apiParam {String} type intro web or app
		 * 
	 	 * @apiSuccess ExistRestaurant The restaurantsList was exist
		 * @apiSuccessExample Success-Response
		 *     HTTP/1.1 200 OK
		 *     {
		 *      web - "restaurant/main"
		 *      app - "true"
		 *     }
		 */
		@RequestMapping(value = "/deleteRestaurant", method = RequestMethod.GET)
		public String deleteRestaurant(@RequestParam("CODE") String code,Model model, HttpServletRequest request) throws Exception{
			service.setDeleteImage(code);
			service.setDeleteRestaurant(code);	
			
			String headerName = request.getHeader("User-Agent");
			
			if(headerName.contains("Android")){
				String result = "";
				model.addAttribute("json","true");
				result = "login/login2/androidResult";
				return result;
			}else{
				return "restaurant/main";
			}
		}
		
		@RequestMapping(value = "/modifyRestaurantPage", method = RequestMethod.GET)
		public String modifyRestaurantPage(@RequestParam("CODE") String code) throws Exception{
			return "restaurant/modifyrestaurant";
		}
		

		/**
		 * @api {post} /doModifyRestaurantPage [ModifyRestaurant]
		 * @apiVersion 0.1.0
		 * @apiName ModifyRestarurant
		 * @apiGroup Restarurant
		 * 
		 * @apiDescription If you access from the android must have access to the token value.
		 * 
		 * @apiParam {String} CODE Restaurnats unique CODE
		 * @apiParam {String} restaurantName add restaurant naming
		 * @apiParam {MultipartFile} restaurantImage restaurant use image
		 * @apiParam {String} originalImage imageFile original name
		 * @apiParam {String} restaurantMenu restaurant menu
		 * @apiParam {String} restaurantTag restaurant related tags
		 * @apiParam {String} restaurantUse_Yn Whether operating a restaurant
		 * @apiParam {String} update_id Users unique ID.
		 * @apiParam {String} type intro web or app
		 * 
	 	 * @apiSuccess ExistRestaurant The restaurantsList was exist
		 * @apiSuccessExample Success-Response
		 *     HTTP/1.1 200 OK
		 *     {
		 *      web - "redirect:/detailRestaurant"
		 *      app - "true"
		 *     }
		 */
		@RequestMapping(value = "/doModifyRestaurantPage", method = RequestMethod.POST)
		public String doModifyRestaurantPage(@RequestParam("CODE") String code,
												@RequestParam("restaurantName") String restaurantName,
												@RequestParam("restaurantImage") MultipartFile restaurantImage,
												@RequestParam("originalImage") String originalImage,
												@RequestParam("restaurantMenu") String restaurantMenu,
												@RequestParam("restaurantTag") String restaurantTag,
												@RequestParam("restaurantUse_Yn") String restaurantUse_Yn,
												@RequestParam("update_id") String update_id,
												RedirectAttributes redirectAttributes,
												HttpServletRequest request,
												Model model) throws Exception{
			Map<String, Object> setImageFileMap = null;
			RestaurantVO setRestaurantVO = updateRestaursntVO(code, restaurantName, restaurantMenu, update_id, restaurantTag, restaurantUse_Yn);
			Map<String, Object> setRestaurantMap = inputRestaursntMap(setRestaurantVO); 
			setImageFileMap = inputImageFileMap(restaurantImage, code);		
			service.modifyRestaurant(setRestaurantMap, setImageFileMap, request);	
			
			String headerName = request.getHeader("User-Agent");
			
			if(headerName.contains("Android")){
				String result = "";
				model.addAttribute("json","true");
				result = "login/login2/androidResult";
				return result;
			}else{
				redirectAttributes.addAttribute("CODE", code);
				String url = "redirect:/detailRestaurant";	
				return url;
			}
		}
		
		public RestaurantVO inputRestaursntVO(String restaurantName, String restaurantMenu, String reg_id, String restaurantTag) throws SQLException{
			RestaurantVO setRestaurantVO = new RestaurantVO();
			setRestaurantVO.setCode(createCode());
			setRestaurantVO.setName(restaurantName);
			setRestaurantVO.setMenu(restaurantMenu);
			setRestaurantVO.setReg_id(reg_id);
			setRestaurantVO.setTag(restaurantTag);
			setRestaurantVO.setUse_yn("Y");
			setRestaurantVO.setUpdate_id("-");
			return setRestaurantVO;
		}
		
		public Map<String, Object> inputRestaursntMap(RestaurantVO setRestaurantVO) throws SQLException{
			Map<String, Object> setRestaurantMap = new HashMap<String, Object>();
			setRestaurantMap.put("code", setRestaurantVO.getCode());
			setRestaurantMap.put("name", setRestaurantVO.getName());
			setRestaurantMap.put("menu", setRestaurantVO.getMenu());
			setRestaurantMap.put("reg_id", setRestaurantVO.getReg_id());
			setRestaurantMap.put("tag", setRestaurantVO.getTag());
			setRestaurantMap.put("use_yn", setRestaurantVO.getUse_yn());
			if(setRestaurantVO.getUpdate_id().equals("-")){
				setRestaurantMap.put("update_id", setRestaurantVO.getReg_id());
			}else{
				setRestaurantMap.put("update_id", setRestaurantVO.getUpdate_id());
			}
			return setRestaurantMap;
		}
		
		public Map<String, Object> inputImageFileMap(MultipartFile restaurantImage, String code){
			Map<String, Object> setImageFileMap = new HashMap<String, Object>();
			setImageFileMap.put("restaurantImage", restaurantImage);
			setImageFileMap.put("code", code);
			return setImageFileMap;
		}
		
		public RestaurantVO updateRestaursntVO(String code, String restaurantName, String restaurantMenu, String update_id, String restaurantTag, String restaurantUse_Yn) throws SQLException{
			RestaurantVO setRestaurantVO = new RestaurantVO();
			setRestaurantVO.setCode(code);
			setRestaurantVO.setName(restaurantName);
			setRestaurantVO.setMenu(restaurantMenu);
			setRestaurantVO.setTag(restaurantTag);
			setRestaurantVO.setUse_yn(restaurantUse_Yn);
			setRestaurantVO.setUpdate_id(update_id);
			setRestaurantVO.setUpdate_date(LocalDateTime.now().toString());
			return setRestaurantVO;
		}	
		
		public String createCode() throws SQLException{
			String code = "";
			code = createCodePrefix() + "_R" + getNewRestaurantCode();
			return code;		
		}
		
		public String createCodePrefix(){
			LocalDate time = LocalDate.now();
			String codePrefix = time.toString();
			return codePrefix;
		}
		
		public int getNewRestaurantCode() throws SQLException{
			int realCode = codeNumberAdd();
			return realCode;
		}	
		
		public int codeNumberAdd() throws SQLException{
			String code = service.getMaxRestaurantCode();
			String stringCode = null;
			int realCode = 0;
			if (code == null || code.equals("")) {
				code = "2016-07-04_R0";
				stringCode = code.substring(12);
				realCode = Integer.parseInt(stringCode) + 1;
			} else {
				realCode = Integer.parseInt(code) + 1;
			}
			
			return realCode;
		}
}
