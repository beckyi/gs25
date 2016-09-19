package kr.ac.sungkyul.gs25.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;

import kr.ac.sungkyul.gs25.service.SenderEmail;
import kr.ac.sungkyul.gs25.vo.EmailVo;

@Controller
@RequestMapping("/email")
public class EmailController {
	@Autowired
    private SenderEmail senderEmail;
     
    @RequestMapping("/send")
    public String sendEmailAction () throws Exception {
    	System.out.println("email넘어옴");
        EmailVo email = new EmailVo();
         
        String receiver = "lisa_i@naver.com"; //받을사람의 이메일입니다.
        String subject = "GS25 편의점 회원님의 임시 비밀번호입니다.";
        String content = "이메일 내용입니다.";
         
        email.setReceiver(receiver);
        email.setSubject(subject);
        email.setContent(content);
        senderEmail.SendEmail(email);
        
//	        return new ModelAndView("success");
        return "user/passresult";
    }
}
