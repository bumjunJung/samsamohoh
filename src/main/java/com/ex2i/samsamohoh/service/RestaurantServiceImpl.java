package com.ex2i.samsamohoh.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ex2i.samsamohoh.dao.RestaurantDao;
import com.ex2i.samsamohoh.util.FileUtils;
import com.ex2i.samsamohoh.vo.ReplyVO;
import com.ex2i.samsamohoh.vo.RestaurantVO;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	private RestaurantDao dao;

	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public String getMaxRestaurantCode() throws SQLException {
		return this.dao.selectMaxRestaurantCode();
	}

	@Override
	public int getMaxImgSeq() throws SQLException {
		return this.dao.selectMaxImgSeq();
	}
	
	@Transactional(rollbackFor={SQLException.class})
	@Override
	public void setRestaurant(Map<String, Object> setRestaurantMap, Map<String, Object> setImageFileMap, HttpServletRequest request) throws Exception {
		this.dao.insertRestaurant(setRestaurantMap);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(setImageFileMap, request);
        for(int i=0, size=list.size(); i<size; i++){
            this.dao.insertImageFile(list.get(i));
        }	
	}

	@Override
	public List<RestaurantVO> getRestaurantDetailtoCode(Map<String, String> params) throws Exception {
		return this.dao.selectRestaurantDetailtoCode(params);
	}
	
	@Transactional(rollbackFor={SQLException.class})
	@Override
	public void setDeleteRestaurant(String code) throws Exception{
		this.dao.deleteRestaurant(code);
	}
	
	@Transactional(rollbackFor={SQLException.class})
	@Override
	public void setDeleteImage(String code) throws Exception{
		this.dao.deleteImage(code);
	}
	
	@Transactional(rollbackFor={SQLException.class})
	@Override
	public void modifyRestaurant(Map<String, Object> setRestaurantMap, Map<String, Object> setImageFileMap, HttpServletRequest request) throws Exception{
		this.dao.updateRestaurant(setRestaurantMap);
		
		int result = this.dao.selectCheckImage(setRestaurantMap.get("code").toString());

		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(setImageFileMap, request);
		if(result > 0){
	        for(int i=0, size=list.size(); i<size; i++){
	            this.dao.upeateImageFile(list.get(i));
	        }	
		} else {
	        for(int i=0, size=list.size(); i<size; i++){
	            this.dao.insertImageFile(list.get(i));
	        }	
		}			
	}
	
	@Override
	public int getMaxReplySeq() throws SQLException {
		return this.dao.selectMaxReplySeq();
	}

	@Override
	public List<ReplyVO> getReplyContent(String code) throws SQLException {
		return this.dao.selectReplyContent(code);
	}
	
	@Transactional(rollbackFor={SQLException.class})
	@Override
	public int setRestaurantReply(ReplyVO replyVO) throws SQLException {
		int result = this.dao.insertRestaurantReply(replyVO);
		return result;
	}
	
	@Transactional(rollbackFor={SQLException.class})
	@Override
	public int setDeleteRestaurantReply(ReplyVO replyVO) throws SQLException {
		int result = this.dao.deleteRestaurantReply(replyVO);
		return result;
	}

	@Override
	public List<Map<String, Object>> getRestaurantInfo() throws SQLException {
		return this.dao.selectRestaurantInfo();
	}

	@Override
	public String getMemberCode(String param) throws SQLException {
		return this.dao.selectMemberCode(param);
	}

	@Override
	public int getFamilyMembers(Map<String, Object> param) throws SQLException {
		return this.dao.selectFamilyMembers(param);
	}

	@Override
	public int getRestaurantNameCheck(String restaurantName) throws SQLException {
		return this.dao.selectRestaurantNameCheck(restaurantName);
	}
	
	@Override
	public List<Map<String, Object>> getSearchRestaurant(String search_word) throws SQLException {
		return this.dao.selectSearchRestaurant(search_word);	
	}
}
