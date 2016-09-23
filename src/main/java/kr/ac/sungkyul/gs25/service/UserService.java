package kr.ac.sungkyul.gs25.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.gs25.dao.UserDao;
import kr.ac.sungkyul.gs25.exception.UserInfoUpdateException;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao usersdao;
	
	@Autowired
	private MailSender mailSender;

	
	public void join(UserVo vo){
		usersdao.insert(vo);
	}
	
	public UserVo login(String email, String password){
		UserVo authUser = usersdao.get(email,password);
		return authUser;
	}
	
	public Boolean loginCheck(String email, String password){	//로그인 시 검사
		System.out.println("service: "+email);
		UserVo vo =  usersdao.get(email,password);
		Boolean result = (vo != null);
		System.out.println(result);
		return result;
	}
		
	public UserVo get(Long no){	//회원정보 수정 시 정보 출력
		UserVo uservo = usersdao.get(no);
		return uservo;
	}
	
	public void update(UserVo vo){
		usersdao.update(vo);
	}
	
	public void update(String tempPass){
		usersdao.update(tempPass);
	}
	
	public void updateInfo(UserVo vo){
		try {
			usersdao.update(vo);
		} catch (UserInfoUpdateException e) {
			e.printStackTrace();
		}
	}
	
	public String idfind(UserVo vo){	//아이디 찾기
		String email = usersdao.find(vo);
		return email;
	}
	
	public Boolean idCheck(UserVo uservo){	//로그인 시 검사
		System.out.println("service: "+uservo);
		String email = usersdao.find(uservo);
		System.out.println("service: "+email);
		Boolean result = (email != null);
		System.out.println(result);
		return result;
	}
	
	public void setpass(String email,String password){	//비밀번호 찾기 후 재설정
		usersdao.setPass(email,password);
	}
	
	public Map<String, Object> checkEmail(String email){	//아이디 유효성 검사
		Long no = usersdao.checkEmail(email);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", no != null);	//존재할 경우 true
//		System.out.println(email + " -service- "+ (no != null));
		
		return map;
	}
	
}
