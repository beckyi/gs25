package kr.ac.sungkyul.gs25.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
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
	
	public Boolean idCheck(UserVo uservo){	//로그인 시 검사
		System.out.println("service: "+uservo);
		String email = usersdao.find(uservo);
		System.out.println("service: "+email);
		Boolean result = (email != null);
		System.out.println(result);
		return result;
	}
	
	public String idfind(UserVo vo){	//아이디 찾기
		String email = usersdao.find(vo);
		return email;
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
	
	public String checkPass(UserVo uservo){	//비밀번호 찾기 시 검사
//		System.out.println("service: "+uservo);
		String email = usersdao.passfind(uservo);
//		System.out.println("service: "+email);
		return email;
	}
	
	 public String sendEmail( String email) throws Exception {
	    	System.out.println("emailController: "+email);
	    	
	    	String id = email;
	    	String ranNum= random();
	    	
	    	SimpleMailMessage message = new SimpleMailMessage();
	    	
	    	String sender = "GS25_Manager@gs25.com"; //받을사람의 이메일입니다.
	    	String receiver = "beckyi@naver.com"; //받을사람의 이메일입니다.
	        String subject = "GS25편의점 회원님의 임시 비밀번호입니다.";
	        String content = "안녕하세요. GS25편의점입니다. 회원님의 임시 비밀번호는 "+ranNum+" 입니다. \n"
	        				+ "http://localhost:8088/gs25/user/repassword"+"?ranNum="+ranNum+"&userid="+id;
	        
	        //random, id 값 session 전송
//	        HttpSession session = new HttpSession(); //컨트롤러에서 전달해줄 수 있으나 세션은 불안정!(외부컴터 X)
//	        session.setAttribute("rannum",ranNum);
//	        session.setAttribute("userid",id);
	    	
	        message.setFrom(sender);
	        message.setTo(receiver);
	        message.setSubject(subject);
	        message.setText(content);
	        mailSender.send(message);
	        return "";
//	        return "redirect:/user/passresult";
	 }
	 
	 public String random(){
			StringBuffer buffer = new StringBuffer();
			for(int i =0;i<=6;i++){
				int n = (int)(Math.random()*10);
				buffer.append(n);
			}
			System.out.println(buffer.toString());
			return buffer.toString();
		}
	
}
