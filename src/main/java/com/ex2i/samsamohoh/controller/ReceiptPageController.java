package com.ex2i.samsamohoh.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ex2i.samsamohoh.service.ReceiptService;


@Controller
public class ReceiptPageController {

	@Autowired
	private ReceiptService service;

	
	/**
	 * @api {post} /modifyReceiptForm [ModifyReceiptForm]
	 * @apiVersion 0.1.0
	 * @apiName ModifyReceiptForm
	 * @apiGroup Receipt
	 * 
	 * @apiParam {String} seq receipts unique Number. 
	 * @apiParam {String} msg if success modify, user to message
	 * 
	 * @apiSuccess ModifyReceiptForm modify form
	 * @apiSuccess FoundReceiptList ReceiptInfo was found.
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      ["receipt/modifyReceipt"]     	
	 *     }
	 */
	@RequestMapping("/modifyReceiptForm")
	public String modifyForm(String seq, String msg, Model model){
		String url = "receipt/modifyReceipt";
		model.addAttribute("seq",seq);
		model.addAttribute("msg",msg);
		return url;
	}
	
	/**
	 * @api {post} /modifyReceipt [ModifyReceipt]
	 * @apiVersion 0.1.0
	 * @apiName ModifyReceipt
	 * @apiGroup Receipt
	 * 
	 * @apiParam {String} seq receipts unique Number. 
	 * @apiParam {List} memberList members Code 
	 * @apiParam {String} mealType change mealTypes
	 * @apiParam {String} etc  etc for receipt
	 * @apiParam {String} total_price peoples use total price
	 * @apiParam {String} settling_date this receipt settling date
	 * 
	 * @apiSuccess ModifyReceipt Receipt was modify
	 * @apiSuccess FoundReceiptList ReceiptList was found.
	 * @apiSuccessExample Success-Response
	 *     HTTP/1.1 200 OK
	 *     {
	 *      ["redirect:/modifyReceiptForm"]     	
	 *     }
	 */
	@RequestMapping("/modifyReceipt")
	public String modifyreceipt(@RequestParam("realMember") List<String> memberList,
							@RequestParam("meal_type") String meal_type,
							@RequestParam("seq") String seq,
							@RequestParam("etc") String etc,
							@RequestParam("total_price") String total_price,
							@RequestParam("settling_date") String settling_date,
							HttpServletRequest request,
							RedirectAttributes redirectAttributes) throws SQLException{
		
		String members = "";
        for(int i = 0; i < memberList.size(); i++)
        	
        	if(i == memberList.size() - 1)
        		members = (new StringBuilder(String.valueOf(members))).append((String)memberList.get(i)).toString();
        	else
        		members = (new StringBuilder(String.valueOf(members))).append((String)memberList.get(i)).append(",").toString();
		
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("seq", seq);
        params.put("meal_type", meal_type);
        params.put("memberList", members);
        params.put("etc", etc);
        params.put("total_price", total_price);
        params.put("settling_date", settling_date);
        
        service.modifyReceipt(params, memberList);
        redirectAttributes.addAttribute("seq", seq);
        redirectAttributes.addAttribute("msg", "수정이완료되었습니다");
		String url = "redirect:/modifyReceiptForm";	
		return url;
		
	}
}
