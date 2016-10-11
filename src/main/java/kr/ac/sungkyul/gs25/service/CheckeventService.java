package kr.ac.sungkyul.gs25.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.gs25.dao.CheckEventDao;
import kr.ac.sungkyul.gs25.vo.CheckeventVo;

@Service
public class CheckeventService {

	@Autowired
	CheckEventDao checkeventdao;

	public Integer getCount(Long user_no) { // 출첵 횟수 가져오기
		
		Integer count = checkeventdao.getCount(user_no);
		return count;
	}
	
	public String setCheck(Long user_no) { // 출첵 횟수 가져오기
		
		Integer resultInt = checkeventdao.setCheck(user_no);
		
		String result = String.valueOf(resultInt);
		
		return result;
	}
	
	public List<CheckeventVo> checkList(Long user_no){
		List<CheckeventVo> checkeventvo = checkeventdao.checkList(user_no);
		return checkeventvo;
	}
	
}
