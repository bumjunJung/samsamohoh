package com.ex2i.samsamohoh.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ex2i.samsamohoh.vo.ReplyVO;
import com.ex2i.samsamohoh.vo.RestaurantVO;

public interface RestaurantService {
	public String getMaxRestaurantCode() throws SQLException;
	public int getMaxImgSeq() throws SQLException;
	public void setRestaurant(Map<String, Object> setRestaurantMap, Map<String, Object> setImageFileMap, HttpServletRequest request) throws Exception;
	public List<RestaurantVO> getRestaurantDetailtoCode(Map<String, String> params) throws Exception;
	public void setDeleteRestaurant(String code) throws Exception;
	public void setDeleteImage(String code) throws Exception;
	public void modifyRestaurant(Map<String, Object> setRestaurantMap, Map<String, Object> setImageFileMap, HttpServletRequest request) throws Exception;
	public int getMaxReplySeq() throws SQLException;
	public List<ReplyVO> getReplyContent(String code) throws SQLException;
	public int setRestaurantReply(ReplyVO replyVO)throws SQLException;
	public int  setDeleteRestaurantReply(ReplyVO replyVO) throws SQLException;
	public List<Map<String, Object>> getRestaurantInfo() throws SQLException;
	public String getMemberCode(String param) throws SQLException;
	
	public int getFamilyMembers(Map<String, Object> param) throws SQLException;
	public int getRestaurantNameCheck(String restaurantName) throws SQLException;
	public List<Map<String, Object>> getSearchRestaurant(String search_word) throws SQLException;
}