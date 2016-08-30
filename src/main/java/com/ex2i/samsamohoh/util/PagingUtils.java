package com.ex2i.samsamohoh.util;

public class PagingUtils {

	//Total Page계산.
	public static int calculateTotalPage(int limit, int totalCnt) {
		double dLimit = (double) limit;
		double dtotalCnt = (double) totalCnt;
		double totalPage = Math.ceil((dtotalCnt / dLimit));
		int iTotalPage = (int) totalPage;
		return iTotalPage;
	}
    // 페이지 보여질 list의 첫번째 index.
	public static int startIndex(int page, int limit) {
		int start = (page - 1) * limit;
		return start;
	}
	// pagingNav 의 첫번쨰 페이지.
	public static int startPage(int page, int pageLimit) {
		int startPage = 1;
		// 1~10 = 1
		// 11~20 = 11
		startPage = ((page - 1) / pageLimit * pageLimit) + 1;

		return startPage;
	}
	// 한 페이지에 보여줄 list갯수.
	public static int pageLimit (int defaultPageLimit,int totalPage, int startPage){
		int currentPageLimit = 0;
			
		if (totalPage - (startPage - 1) < defaultPageLimit) {
			currentPageLimit = totalPage - (startPage - 1);
		} else {
			currentPageLimit = defaultPageLimit;
		}
		return currentPageLimit;
	}

}
