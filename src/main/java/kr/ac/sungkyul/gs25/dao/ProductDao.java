package kr.ac.sungkyul.gs25.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.AttachFilePrVo;
import kr.ac.sungkyul.gs25.vo.CartVo;
import kr.ac.sungkyul.gs25.vo.ProductVo;
import kr.ac.sungkyul.gs25.vo.StoreProductVo;

@Repository
public class ProductDao {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	// 상품 리스트 총 개수 구하기
	public int getTotalCount() {
		
		int totalCount=sqlSession.selectOne("product.getTotalCount");
		return totalCount;
	}
	
	//상품 리스트
	public List<ProductVo> getList(int page, int pagesize,String keyword) {
		
		Map<String, Object> map=new HashMap<>();
		
		//키보드가 null or 비어있을 때 리스트 가져오기
		if(keyword ==null || "".equals(keyword)){
			
		map.put("page_start", (page - 1) * pagesize + 1);
		map.put("page_end", page * pagesize);
		
		//검색된 리스트 가져오기
		List<ProductVo> list=sqlSession.selectList("product.getList",map);
		return list;
		}else{
			
			map.put("keyword", "%"+keyword+"%");
			map.put("page_start", (page - 1) * pagesize + 1);
			map.put("page_end", page * pagesize);
			
			List<ProductVo> list=sqlSession.selectList("product.getListKeyword",map);
			return list;
		}
	}
	
	// 상품 등록
	public Long insert(ProductVo vo) {
		sqlSession.insert("product.insertBoard", vo);
		return vo.getNo();
	}

	//상품 첨부파일 등록
	public void insertAttachPrFile(AttachFilePrVo attachFilePrVO) {
		sqlSession.insert("product.insertAttachPrFile", attachFilePrVO);
	}
	
	//상품 첨부파일 삭제
	public void deletefile(Long no){
		sqlSession.delete("product.deleteproductfile",no);
	}
	
	//상품 삭제
	public void delete(Long no){
		sqlSession.delete("product.deleteproduct",no);
	}
	
	//서브메인 - 유통기한별 4개
	public List<ProductVo> getSubDate() {
		
		List<ProductVo> list=sqlSession.selectList("product.getSubDate");
		System.out.println("유통기한 "+list);
		return list;
	}
	
	//서브메인 - 신상품별 4개
	public List<ProductVo> getSubNew() {
		
		List<ProductVo> list=sqlSession.selectList("product.getSubNew");
		System.out.println("신상 "+list);
		return list;
	}
	
	//서브메인 - 추천별 4개
	public List<ProductVo> getSubReco() {
		
		List<ProductVo> list=sqlSession.selectList("product.getSubReco");
		System.out.println("추천 " + list);
		return list;
	}
	
	// 서브메인 - 인기상품4개
	public List<ProductVo> getSubPopular() {

		List<ProductVo> list = sqlSession.selectList("product.getSubPopular");
		return list;
	}

	
	//상품 상세정보 출력
	public StoreProductVo productInfo(Long no,Long store_no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("store_no", store_no);
		StoreProductVo vo = sqlSession.selectOne("product.searchproduct", map);
		return vo;
	}
	
	// 1000원 이하 랜덤 상품 (출석체크 상품 증정)
	public StoreProductVo random1000(Long store_no){
		
		StoreProductVo vo = sqlSession.selectOne("product.random1000",store_no);
		return vo;
	}
	
	// 2000원 이하 랜덤 상품 (출석체크 상품 증정)
	public StoreProductVo random2000(Long store_no){
		StoreProductVo vo = sqlSession.selectOne("product.random2000",store_no);
		return vo;
	}
	

	// 상품 조회수 증가
	public void updateViewCount(Long no) {
		sqlSession.update("product.updateViewCount", no);
	}
	
	//할인된 가격계산
	public Map<String, Object> price(){
		List<ProductVo> PriceList=sqlSession.selectList("product.getPriceList");
		Map<String, Object> PriceMap=new HashMap<String, Object>();
		
		PriceMap.put("PriceList", PriceList);
	
		return PriceMap;
	}
	
	//카트 추가
	public CartVo maintainCheck(Long user_no, Long product_no){
		CartVo checkVo = new CartVo();
		checkVo.setUser_no(user_no);
		checkVo.setProduct_no(product_no);
		checkVo = sqlSession.selectOne("product.maintainCheck", checkVo);
		return checkVo;
	}
	
	//기프티콘에 쓰일 정보 출력
	public StoreProductVo giftprductInfo(Long store_no, Long no){
				
		Map<String, Object> map=new HashMap<>();
		map.put("store_no", store_no);
		map.put("no", no);
		
		StoreProductVo vo = sqlSession.selectOne("product.searchstoreproduct",map);
//		System.out.println("상품상세정보: " + vo.toString());
		return vo;
	}
	
	//상품 수량 감소
	public void cutmount(Long storeproduct_no){
		
		sqlSession.update("product.cutmount",storeproduct_no);
		System.out.println("성공");

	}
	
	//서브 상품 리스트 총 개수 구하기
	public int getTotalCount(Long StoreNo) {

		int totalCount = sqlSession.selectOne("product.getTotalCount",StoreNo);
		return totalCount;

	}
	
	//서브 상품 리스트
	public List<StoreProductVo> getList(int page, int pagesize, String keyword,Long StoreNo) {

		Map<String, Object> map = new HashMap<>();

		// 키보드가 null or 비어있을 때 리스트 가져오기
		if (keyword == null || "".equals(keyword)) {

			map.put("page_start", (page - 1) * pagesize + 1);
			map.put("page_end", page * pagesize);
			map.put("store_no", StoreNo);

			// 게시물 리스트 가져오기
			List<StoreProductVo> list = sqlSession.selectList("product.getList", map);
			return list;
		} else {

			map.put("keyword", "%" + keyword + "%");
			map.put("page_start", (page - 1) * pagesize + 1);
			map.put("page_end", page * pagesize);
			map.put("store_no", StoreNo);
			
			//검색된 리스트 가져오기
			List<StoreProductVo> list = sqlSession.selectList("product.getListKeyword", map);
			return list;
		}
	}
	
}
