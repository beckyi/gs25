package kr.ac.sungkyul.gs25.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.sungkyul.gs25.service.MapService;
import kr.ac.sungkyul.gs25.service.RegionService;
import kr.ac.sungkyul.gs25.vo.MapVo;
import kr.ac.sungkyul.gs25.vo.RegionVo;
import kr.ac.sungkyul.gs25.vo.StoreVo;

@Controller
public class MainController {

	@Autowired
	RegionService regionservice;
	
	@Autowired
	MapService mapservice;
	
	@RequestMapping("/main")
	public String main(Model model,
						@RequestParam(value="no", required=true, defaultValue="1") Long no){
		
		List<RegionVo> regionvo = regionservice.research();
		model.addAttribute("regionvo",regionvo);
		
		List<StoreVo> storevo= mapservice.getlist();
		model.addAttribute("storevo", storevo);
		
		List<MapVo> mapvo = mapservice.mainmaplist(); //지도 리스트
		model.addAttribute("mapvo", mapvo);
		
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
