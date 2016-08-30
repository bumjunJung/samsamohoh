package com.ex2i.samsamohoh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ex2i.samsamohoh.vo.ReplyVO;
import com.ex2i.samsamohoh.vo.RestaurantVO;

public interface RestaurantDao {
	public String selectMaxRestaurantCode() throws SQLException;
	public int selectMaxImgSeq() throws SQLException;
	public void insertRestaurant(Map<String, Object> map) throws SQLException;
	public void insertImageFile(Map<String, Object> map) throws Exception;
	public List<RestaurantVO> selectRestaurantDetailtoCode(Map<String, String> params) throws Exception;
	public void deleteRestaurant(String code) throws Exception;
	public void deleteImage(String code) throws Exception;
	public void updateRestaurant(Map<String, Object> setRestaurantMap) throws Exception;
	public void upeateImageFile(Map<String, Object> map) throws Exception;
	public int selectMaxReplySeq() throws SQLException;
	public List<ReplyVO> selectReplyContent(String code) throws SQLException;
	public int insertRestaurantReply(ReplyVO replyVO)throws SQLException;
	public int  deleteRestaurantReply(ReplyVO replyVO) throws SQLException;
	public List<Map<String, Object>> selectRestaurantInfo() throws SQLException;
	public String selectMemberCode(String param) throws SQLException;

	public int selectFamilyMembers(Map<String, Object> param) throws SQLException;
	public int selectRestaurantNameCheck(String restaurantName) throws SQLException;
	public List<Map<String, Object>> selectSearchRestaurant(String search_word) throws SQLException;
	public int selectCheckImage(String code) throws SQLException;

}
