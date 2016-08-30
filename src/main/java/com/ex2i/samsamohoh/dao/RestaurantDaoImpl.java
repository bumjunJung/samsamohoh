package com.ex2i.samsamohoh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ex2i.samsamohoh.vo.ReplyVO;
import com.ex2i.samsamohoh.vo.RestaurantVO;

@Repository
public class RestaurantDaoImpl implements RestaurantDao {
	@Autowired
	private SqlSessionTemplate sql;

	@Override
	public String selectMaxRestaurantCode() throws SQLException {
		String result = this.sql.selectOne("restaurant.selectMaxRestaurantCode");
		return result;
	}

	@Override
	public int selectMaxImgSeq() throws SQLException {
		int result = this.sql.selectOne("restaurant.selectMaxImgSeq");
		return result;
	}
	
	@Override
	public void insertRestaurant(Map<String, Object> map) throws SQLException {
		this.sql.insert("restaurant.insertRestaurant", map);
	}

	@Override
	public void insertImageFile(Map<String, Object> map) throws Exception {
		this.sql.insert("restaurant.insertImageFile", map);
	}

	@Override
	public List<RestaurantVO> selectRestaurantDetailtoCode(Map<String, String> params) throws Exception {		
		return this.sql.selectList("restaurant.selectRestaurantDetailtoCode", params);
	}
	
	@Override
	public void deleteRestaurant(String code) throws Exception{
		this.sql.delete("restaurant.deleteRestaurant", code);
	}
	
	@Override
	public void deleteImage(String code) throws Exception{
		this.sql.delete("restaurant.deleteImage", code);
	}
	
	@Override
	public void updateRestaurant(Map<String, Object> setRestaurantMap) throws Exception{
		this.sql.update("restaurant.updateRestaurant" , setRestaurantMap);
	}
	
	@Override
	public void upeateImageFile(Map<String, Object> map) throws Exception {
		this.sql.update("restaurant.upeateImageFile" , map);
	}

	@Override
	public int selectMaxReplySeq() throws SQLException {
		return this.sql.selectOne("restaurant.selectMaxReplySeq");
	}

	@Override
	public List<ReplyVO> selectReplyContent(String code) throws SQLException {
		List<ReplyVO> list = this.sql.selectList("restaurant.selectReplyContent", code);
		return list;
	}

	@Override
	public int insertRestaurantReply(ReplyVO replyVO) throws SQLException {
		int result = this.sql.insert("restaurant.insertRestaurantReply", replyVO);;
		return result;
	}

	@Override
	public int deleteRestaurantReply(ReplyVO replyVO) throws SQLException {
		int result = this.sql.insert("restaurant.deleteRestaurantReply", replyVO);;
		return result;
	}

	@Override
	public List<Map<String, Object>> selectRestaurantInfo() throws SQLException {
		return this.sql.selectList("restaurant.selectRestaurantInfo");
	}

	@Override
	public String selectMemberCode(String param) throws SQLException {
		return this.sql.selectOne("restaurant.selectRestaurantMemberCode", param);
	}

	@Override
	public int selectFamilyMembers(Map<String, Object> param) throws SQLException {
		int result = this.sql.selectOne("restaurant.selectFamilyMembers", param);
		return result;
	}

	@Override
	public int selectRestaurantNameCheck(String restaurantName) throws SQLException {
		return this.sql.selectOne("restaurant.selectRestaurantNameCheck", restaurantName);
	}

	@Override
	public List<Map<String, Object>> selectSearchRestaurant(String search_word) throws SQLException {
		return sql.selectList("restaurant.selectSearchRestaurant", search_word);
	}
	
	@Override
	public int selectCheckImage(String code) throws SQLException {
		return sql.selectOne("restaurant.selectCheckImage", code);
	}

}
