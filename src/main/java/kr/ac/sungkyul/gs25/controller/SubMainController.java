package kr.ac.sungkyul.gs25.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
//	@Autowired
//	CartService cartservice;

	
	//서브 메인 페이지 이동
		@RequestMapping("/main")
		public String SubMain(Model model, 
			@RequestParam("store_no") Long store_no,
			HttpSession session){
		
			System.out.println("매장 번호 :"+store_no);
			
			//상품 번호 추가
			session.setAttribute("store_no", store_no);
	
			//매장 이름  정보얻기
//			StoreProductVo StoreVo=productservice.getStoreName(store_no);
//			model.addAttribute("StoreVo", StoreVo);
	
			//유통기한
			List<ProductVo> expiryVo = productservice.getSubDate();
			model.addAttribute("expiryVo",expiryVo);
			
			//인기상품
//			List<ProductVo> popularity=productservice.getSubPopular();
//			model.addAttribute("popularityVo", popularity);
			
			//신상품
			List<ProductVo> newProductVo = productservice.getSubNew();
			model.addAttribute("newProductVo",newProductVo);
			
			//추천상품
			List<ProductVo> recommendVo = productservice.getSubReco();
			model.addAttribute("recommendVo",recommendVo);
			
			//찜목록 총 개수
//			Integer TotalCount=cartservice.getCount();
//			model.addAttribute("TotalCount", TotalCount);	

		return "SubPage/sub_index";
	}
	
	@RequestMapping("/event_check")	//출석체크 창 뿌려줄 경우 (누적횟수, 출석 상황)
	public String event_check_form(HttpSession session, Model model,
									@RequestParam("store_no") Long store_no){
		
		System.out.println("sub con: "+store_no);
		
		UserVo uservo = (UserVo)session.getAttribute("authUser");
		Long user_no = uservo.getNo();
		
		Integer count = ceService.getCount(user_no, store_no);
		System.out.println("con count: "+count);
		List<CheckeventVo> checkeventvo = ceService.checkList(user_no, store_no);
		
		model.addAttribute("count", count);
		model.addAttribute("checkeventvo", checkeventvo);
		model.addAttribute("store_no", store_no);
		
		return "SubPage/event_check";
	}
	
	@ResponseBody
	@RequestMapping("/checkDate")	//출석체크 클릭 시
	public String checkDate(HttpSession session, Model model){
		
		Long store_no = (Long) session.getAttribute("store_no");
		System.out.println("컨트롤 checkDate: "+store_no);
		
		UserVo uservo = (UserVo)session.getAttribute("authUser");
		Long user_no = uservo.getNo();
		
		String result = ceService.setCheck(user_no, store_no);
		
		return result;
	}	
}
