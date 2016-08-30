package com.ex2i.samsamohoh.vo;

public class ReplyVO {
	private int seq;
	private String content;
	private String member_code;
	private String write_date;
	private String restaurant_code;
	private String name;
	private String id;	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMember_code() {
		return member_code;
	}
	public void setMember_code(String member_code) {
		this.member_code = member_code;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	public String getRestaurant_code() {
		return restaurant_code;
	}
	public void setRestaurant_code(String restaurant_code) {
		this.restaurant_code = restaurant_code;
	}
}
