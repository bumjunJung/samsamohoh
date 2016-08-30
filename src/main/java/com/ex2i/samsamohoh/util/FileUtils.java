package com.ex2i.samsamohoh.util;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ex2i.samsamohoh.service.RestaurantService;

@Component("fileUtils")
public class FileUtils {
	
		@Autowired
		private RestaurantService service;
		
	    public List<Map<String,Object>> parseInsertFileInfo(Map<String,Object> map, HttpServletRequest request) throws Exception{
	        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
	        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
	        
	        MultipartFile multipartFile = null;
	        int imageIdx = 0;
	        String originalFileName = null;
	        String changeFileName = null; 
	        
	        String filePath = request.getSession().getServletContext().getRealPath("/image/");
	        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	        Map<String, Object> listMap = null; 
	        
	        File file = new File(filePath);
	        if(file.exists() == false){
	            file.mkdirs();
	        }
	         
	        while(iterator.hasNext()){
	            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
	            if(multipartFile.isEmpty() == false){
	            	imageIdx = getMaxImg_seq();
	                originalFileName = multipartFile.getOriginalFilename();
	                changeFileName = getRandomString() + "-" + originalFileName;
	                file = new File(filePath + changeFileName);
	                multipartFile.transferTo(file);
	                listMap = new HashMap<String,Object>();
	                listMap.put("seq", imageIdx);
	                listMap.put("real_name", originalFileName);
	                listMap.put("change_name", changeFileName);
	                listMap.put("file_path", filePath);
	                listMap.put("reg_date", LocalDateTime.now());
	                listMap.put("code", map.get("code"));
	                list.add(listMap);
	            }
	        }
	        return list;
	    }
	    
		public int getMaxImg_seq() throws SQLException{
			int maxSeq = 0;
			maxSeq = service.getMaxImgSeq();
			if(maxSeq == 0){
				maxSeq = 1;
			} else{
				maxSeq = maxSeq + 1;
			}
			return maxSeq;
		}
		
		public static String getRandomString(){
	        return UUID.randomUUID().toString().replaceAll("-", "");
	    }
}
