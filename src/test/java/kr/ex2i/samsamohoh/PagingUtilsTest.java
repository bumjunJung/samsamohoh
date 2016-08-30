package kr.ex2i.samsamohoh;

import com.ex2i.samsamohoh.util.PagingUtils;

import junit.framework.TestCase;

public class PagingUtilsTest extends TestCase {


	public void testStartPage() {
		
		int pageLimit = 10;	
		
		assertEquals(1, PagingUtils.startPage(1, pageLimit));
		assertEquals(1, PagingUtils.startPage(10, pageLimit));
		
		
		assertEquals(11, PagingUtils.startPage(11, pageLimit));
		assertEquals(11, PagingUtils.startPage(20, pageLimit));
		
	}
	
	public void testPageLimit(){
		assertEquals(10, PagingUtils.pageLimit(10,22,1));
		assertEquals(10, PagingUtils.pageLimit(10,22,11));
		assertEquals(2, PagingUtils.pageLimit(10,22,21));
	}
}
