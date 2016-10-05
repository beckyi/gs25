package kr.ac.sungkyul.gs25.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.AttachFilePrVo;
import kr.ac.sungkyul.gs25.vo.ProductVo;

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
	
	public List<ProductVo> getSubDate() {
		
		List<ProductVo> list=sqlSession.selectList("product.getSubDate");
		System.out.println("유통기한 "+list);
		return list;
	}
	
	public List<ProductVo> getSubNew() {
		
		List<ProductVo> list=sqlSession.selectList("product.getSubNew");
		System.out.println("신상 "+list);
		return list;
	}
	
	public List<ProductVo> getSubReco() {
		
		List<ProductVo> list=sqlSession.selectList("product.getSubReco");
		System.out.println("추천 "+list);
		return list;
	}
}
