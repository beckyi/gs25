package kr.ac.sungkyul.gs25.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.sungkyul.gs25.service.ProductService;
import kr.ac.sungkyul.gs25.vo.ProductVo;

@Controller
@RequestMapping("/sub")
public class SubMainController {
	
	@Autowired
	ProductService productservice;
	
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
	
	@RequestMapping("/event_check")
	public String event_check_form(){
		return "SubPage/event_check";
	}
}
