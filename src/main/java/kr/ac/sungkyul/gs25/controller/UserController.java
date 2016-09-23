package kr.ac.sungkyul.gs25.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.sungkyul.gs25.service.UserService;

import kr.ac.sungkyul.gs25.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/joinform")
	public String joinform(){
		return "user/joinform";
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo){
		System.out.println("join: "+vo.toString());
//		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess(){
		return "user/joinsuccess";
	}
	
	@RequestMapping("/loginform")
	public String loginform(){
		return "user/loginform";
	}
	
//	@RequestMapping(value= "/login")
//	public String login(
//			HttpSession session, Model model,
//			@RequestParam(value= "email", required=false, defaultValue="") String email,
//			@RequestParam(value="password", required=false, defaultValue="") String password){
//
//		UserVo authUser =  userService.login(email,  password);
//
//		if(authUser == null){
//			Boolean result = true;
//			model.addAttribute("result",result);
//			return "user/loginform";	//html(jsp)화면만 
//		}
//		
//		//인증성공
//		session.setAttribute("authUser",authUser);
//		return "redirect:/main";
//	}
	
	//1. Ajax 사용 시 - DB (로그인 정보 비교)
	//2. Ajax 사용 시 - 인증 완료 (세션&redirect)
	
	@ResponseBody
	@RequestMapping(value= "loginCheck", method = RequestMethod.POST)
	public Boolean loginCheck(String email, String password){
		System.out.println("controll email: "+email);
		Boolean result =  userService.loginCheck(email,  password);
		System.out.println("controll: "+result);
		return result;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("authUser");
		session.invalidate();	//로그아웃 처리 시 세션을 지워줌
		
		return "redirect:/main";
	}
	
	@RequestMapping("/modifyform")
	public String modifyform(HttpSession session, Model model){
		UserVo temp = (UserVo)session.getAttribute("authUser");
		
		Long no = temp.getNo();
		
		UserVo nvo = userService.get(no);
		model.addAttribute("userVo", nvo);
		
		return "user/modifyform";
	}
	
	@RequestMapping("/modify")
	public String modify(HttpSession session, @ModelAttribute UserVo vo){
		
		UserVo temp = (UserVo)session.getAttribute("authUser");
		Long no = temp.getNo();
		
		vo.setNo(no);
		
		userService.update(vo);
		return "user/modifysuccess";
	}
	
	@RequestMapping("/findInfo")
	public String findInfo(){
		return "user/findInfo";
	}

	@RequestMapping("/idFind")
	public String idFind(@ModelAttribute UserVo vo,Model model){
		System.out.println(vo);
		String email = userService.idfind(vo);
		System.out.println("idfind: "+email);
		if(email == null){
			Boolean result = false;
			model.addAttribute("result", result);
			return "user/findInfo";
		}
		
		model.addAttribute("email",email);
		return "user/idresult";
	}
	
	@RequestMapping("/repassword")
	public String repasswordForm(){
		return "user/repassword";
	}
	
	@RequestMapping("/setPass")
	public String setPassword
			(@RequestParam(value= "email", required=false, defaultValue="") String email,
			 @RequestParam(value= "password", required=false, defaultValue="") String password){
		
		userService.setpass(email,password);
		
		return "user/send";
	}
	
	@RequestMapping("/passresult")
	public String passResult(){
		return "user/passresult";
	}
	
	@ResponseBody
	@RequestMapping(value = "CheckEmail", method = RequestMethod.POST)
	public Map<String, Object> checkEmail(String email) {	//Request 객체받음, script or DB 객체 분별
		
		Map<String, Object> map = userService.checkEmail(email);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "checkLogin", method = RequestMethod.POST)
	public Boolean checkId(String email, String password, HttpSession session) {	//Request 객체받음, script or DB 객체 분별
		
		UserVo authUser =  userService.login(email,  password);
		Boolean result = true;
		
		if(authUser == null){
			result = false;
			return result;
		}
		//인증성공
		session.setAttribute("authUser",authUser);
		
		return result;
	}
}
