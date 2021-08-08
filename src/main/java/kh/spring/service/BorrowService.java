package kh.spring.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.dao.BorrowDAO;

import kh.spring.dto.BorrowDTO;

@Service
public class BorrowService {
	
	
	@Autowired
	private BorrowDAO dao;
	

	public int getPageCount(String search, String strPageNum){
		
		String ecdsearch = "";
		try {
			ecdsearch = URLEncoder.encode(search, java.nio.charset.StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		//보여줄 페이지의 번호를 일단 1이라고 초기값 지정
		int pageNum = 1;
		
		if(strPageNum != null) {
			pageNum = Integer.parseInt(strPageNum);
		}
		
		System.out.println(ecdsearch + strPageNum);
		
		return dao.getPageCount(ecdsearch,pageNum);
	}
	
		public List<BorrowDTO> getPageList(String search){
		
		String ecdsearch = "";
		try {
			ecdsearch = URLEncoder.encode(search, java.nio.charset.StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
				
		System.out.println(ecdsearch);
		
		return dao.getPageList(ecdsearch);
	}

}
