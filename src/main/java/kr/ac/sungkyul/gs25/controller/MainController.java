package kr.ac.sungkyul.gs25.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.sungkyul.gs25.service.RegionService;
import kr.ac.sungkyul.gs25.service.StoreService;
import kr.ac.sungkyul.gs25.vo.RegionVo;
import kr.ac.sungkyul.gs25.vo.StoreVo;

@Controller
public class MainController {
	

	@Autowired
	RegionService regionservice;
	
	@Autowired
	StoreService storeservice;
	
	@RequestMapping("/main")
	public String main(Model model){
		
		List<RegionVo> regionvo = regionservice.research();
		System.out.println(regionvo.toString());
		model.addAttribute("regionvo",regionvo);
		
//		List<StoreVo> storevo = storeservice.research();
//		model.addAttribute("storevo",storevo);
		
		return "/Main_Page/index";
	}
	
	@RequestMapping("/membership")
	public String membership(){
		return "/Main_Page/membership";
	}
	
	@RequestMapping("/companyintro")
	public String companyintro(){
		return "/Main_Page/companyintro";
	}

}
