package kr.ac.sungkyul.gs25.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.sungkyul.gs25.service.CheckeventService;
import kr.ac.sungkyul.gs25.service.ProductService;
import kr.ac.sungkyul.gs25.vo.CheckeventVo;
import kr.ac.sungkyul.gs25.vo.ProductVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Controller
@RequestMapping("/sub")
public class SubMainController {
	
	@Autowired
	ProductService productservice;
	
	@Autowired
	CheckeventService ceService;
	
	@RequestMapping("/main")
	public String SubMain(Model model){
		List<ProductVo> vo = productservice.getSubDate();
		model.addAttribute("vo",vo);
		
//		List<ProductVo> vo2 = productservice.getSubPopular();
//		System.out.println(vo2.toString());
//		model.addAttribute("vo2",vo2);
		
		List<ProductVo> vo3 = productservice.getSubNew();
		model.addAttribute("vo3",vo3);
		
		List<ProductVo> vo4 = productservice.getSubReco();
		model.addAttribute("vo4",vo4);
		
//		productservice.countDate();

		return "SubPage/sub_index";
	}
	
	@RequestMapping("/event_check")	//출석체크 창 뿌려줄 경우 (누적횟수, 출석 상황)
	public String event_check_form(HttpSession session, Model model){
		UserVo uservo = (UserVo)session.getAttribute("authUser");
		Long user_no = uservo.getNo();
		
		Integer count = ceService.getCount(user_no);
		List<CheckeventVo> checkeventvo = ceService.checkList(user_no);
		
		model.addAttribute("count", count);
		model.addAttribute("checkeventvo", checkeventvo);
		
		return "SubPage/event_check";
	}
	
	@ResponseBody
	@RequestMapping("/checkDate")	//출석체크 클릭 시
	public String checkDate(HttpSession session, Model model){
		
		UserVo uservo = (UserVo)session.getAttribute("authUser");
		Long user_no = uservo.getNo();
		
		String result = ceService.setCheck(user_no);
		
		return result;
	}
}
