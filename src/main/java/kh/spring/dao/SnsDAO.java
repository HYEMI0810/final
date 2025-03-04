package kh.spring.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kh.spring.dto.LoveDTO;
import kh.spring.dto.SnsDTO;

@Component
public class SnsDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insert(Map<String,Object>param) {
		mybatis.insert("Sns.insert", param);
	}
	
	public int seq() {
		return mybatis.selectOne("Sns.seq");
	}
	
	public List<SnsDTO> page(Map<String,Object> param){
		return mybatis.selectList("Sns.page", param);
	}
	
	public List<SnsDTO> initpage(String id){
		return mybatis.selectList("Sns.initSNSpage", id);
	}
	
	public List<SnsDTO>selectAll(String id){
		return mybatis.selectList("Sns.selectAll",id);
	}
	
	public int getLove(int seq) {
		return mybatis.selectOne("Sns.getLove", seq);
	}
	
	public int like(Map<Integer,Integer> param) {
		return mybatis.update("Sns.like", param);
	}
	
	public int delete(int seq) {
		return mybatis.delete("Sns.delete", seq);
	}
	
	public String region(String id) {
		return mybatis.selectOne("Sns.region", id);
	}
	
	public String select(int seq){
		return mybatis.selectOne("Sns.select",seq);
	}
	
	public void modify(Map<String,Object>param) {
		mybatis.update("Sns.modify", param);
	}
	
	public int love(Map<String,Integer>param) {
		return mybatis.update("Sns.love", param);
	}
	
	public int getlike(Map<String,Object>param) {
		return mybatis.selectOne("Sns.getlike", param);
	}
	
	public void pluslove(Map<String,Object>param) {
		mybatis.insert("Sns.pluslove", param);
	}
	
	public void cancellove(Map<String,Integer>param) {
		mybatis.update("Sns.cancellove", param);
	}
	
	public void minuslove(Map<String,Object>param) {
		mybatis.update("Sns.minuslove", param);
	}
	
	public int getcount(Map<String,Object>param) {
		return mybatis.selectOne("Sns.getcount", param);
	}
	
	public void updatecount(Map<String,Object>param) {
		mybatis.update("Sns.updatecount", param);
	}
	
	public List<String> existlike(String id){
		return mybatis.selectList("Sns.existlike", id);
	}
	
	// 내 sns 출력
	public List<SnsDTO> mySelectAll(String id){
		return mybatis.selectList("Sns.mySelectAll", id);
	}
}
