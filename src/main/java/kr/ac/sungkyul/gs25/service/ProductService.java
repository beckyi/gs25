package kr.ac.sungkyul.gs25.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import kr.ac.sungkyul.gs25.dao.ProductDao;
import kr.ac.sungkyul.gs25.vo.AttachFilePrVo;
import kr.ac.sungkyul.gs25.vo.CartVo;
import kr.ac.sungkyul.gs25.vo.GifticonDao;
import kr.ac.sungkyul.gs25.vo.NblogVo;
import kr.ac.sungkyul.gs25.vo.ProductVo;
import kr.ac.sungkyul.gs25.vo.StoreProductVo;

@Service
public class ProductService {

	// 리스팅 되는 게시물 수
	private static final int LIST_PAGESIZE = 12; // 리스팅 되는 게시물 수

	// 페이지 리스트에 표시되는 페이지 수
	private static final int LIST_BLOCKSIZE = 5; // 페이지 리스트에 표시되는 페이지 수

	@Autowired
	private ProductDao productdao;
	
	@Autowired
	private GifticonDao gifticondao;

	@Autowired
	GificonService gifticonservice;
	
    public Map<String, Object> listBoard(String spage, String keyword){
    	
    	// 1. 페이지 값 받기
		int page=Integer.parseInt(spage);
		
		// 2. 페이지를 그리기 위한 기초 작업
		int totalCount = productdao.getTotalCount();
		int pageCount = (int) Math.ceil((double) totalCount / LIST_PAGESIZE);
		int blockCount = (int) Math.ceil((double) pageCount / LIST_BLOCKSIZE);
		int currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);
		
		// 3. page값 검증
		if (page < 1) {
			page = 1;
			currentBlock = 1;
		} else if (page > pageCount) {
			page = pageCount;
			currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);
		}


		// 4. 페이지를 그리기 위한 값 계산
		int startPage = (currentBlock - 1) * LIST_BLOCKSIZE + 1;
		int endPage = (startPage - 1) + LIST_BLOCKSIZE;
		int prevPage = (page >= startPage) ? (page-1) : (currentBlock - 1) * LIST_BLOCKSIZE;
		int nextPage = (page <= endPage) ? (page+1) : currentBlock * LIST_BLOCKSIZE + 1;
		int nexttoPage = (currentBlock < blockCount) ? currentBlock * LIST_BLOCKSIZE + 1 : page;
		int prevtoPage = (currentBlock > 1) ? startPage-3  : page;
		
		List<ProductVo> list=productdao.getList(page, LIST_PAGESIZE, keyword);
		
		// 5. map에 객체 담기
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("sizeList", LIST_PAGESIZE);
		map.put("firstPage", startPage);
		map.put("lastPage", endPage);
		map.put("prevPage", prevPage);
		map.put("nextPage", nextPage);
		map.put("currentPage", page);
		map.put("pageCount", pageCount);
		map.put("list", list);
		map.put("totalCount", totalCount);
		map.put("keyword", keyword);
		map.put("nexttoPage", nexttoPage);
		map.put("prevtoPage", prevtoPage);
		
		
		return map;
	}
    
    //상품 등록
	public void insert(ProductVo vo, MultipartFile file) throws Exception{

			// 1. 게시물의 번호 얻기
			Long no=productdao.insert(vo);

	       // 2. orgName
			String orgName =file.getOriginalFilename();
		
			// 3. fileSize
			long fileSize =file.getSize();
			
			// 4. saveName
			String saveName = orgName;
			
			// 5. path 경로 정하기
		    String path ="C:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\gs25\\assets\\images\\product";

		    // 6. imageurl 경로
			String imageurl="/gs25/assets/images/product/" + saveName;
			
			//7. 첨부파일 객체에 담기
		    AttachFilePrVo attachFilePrVO = new AttachFilePrVo();
			attachFilePrVO.setNo(no);
			attachFilePrVO.setPath(path);
			attachFilePrVO.setOrgName(orgName);
			attachFilePrVO.setSaveName(saveName);
			attachFilePrVO.setFileSize(fileSize);
			attachFilePrVO.setImageurl(imageurl);
			
			//8. 첨부파일 삽입
			productdao.insertAttachPrFile(attachFilePrVO);
			
			//9. 파일 복사 및 이동
			File target = new File(path, saveName);
			FileCopyUtils.copy(file.getBytes(),target);
		
	}
	
	//상품 첨부파일 삭제
	public void deletefile(Long no){
		productdao.deletefile(no);
	}
	
	//상품 삭제
	public void delete(Long no){
		
		productdao.delete(no);
	}
	// 상품 유통기한별 리스트
	public List<ProductVo> getSubDate() {
		List<ProductVo> list = productdao.getSubDate();
		// System.out.println("service: "+list.toString());
		return list;
	}

	//상품 조회수 증가
	public void viewcountup(Long no) {

		productdao.updateViewCount(no);
	}
	
	//할인된 가격계산
    public Map<String, Object> price(){
    	
    	Map<String, Object> PriceMap=productdao.price();
    	return PriceMap;
    }
	
	// 상품 신상품별 리스트
	public List<ProductVo> getSubNew() {
		List<ProductVo> list = productdao.getSubNew();
		// System.out.println("service: "+list.toString());
		return list;
	}
	
	// 상품 랜덤추천별 리스트
	public List<ProductVo> getSubReco() {
		List<ProductVo> list = productdao.getSubReco();
		// System.out.println("service: "+list.toString());
		return list;
	}
		
//	// 할인가격 계산
//	public ProductVo countPrice(Long no) {
//		ProductVo vo = productdao.productInfo(no);
//		return vo;
//	}
	
	// 상품 상세보기
	public StoreProductVo productInfo(Long no,Long store_no) {
		StoreProductVo vo = productdao.productInfo(no,store_no);
		return vo;
	}
	
	//네이버 검색 API 연동(XML파싱 방법)
	private static String clientID = "l4imIq5y2XeyyT7P16JT"; //api 사용 신청시 제공되는 아이디
    private static String clientSecret = "u4oZj43QzL"; //패스워드
 
    public List<NblogVo> searchNBlog(String keyword) {
        URL url;
        int display = 3;
        int start = 1;
        List<NblogVo> list = null;
        
        System.out.println("!"+keyword);
        
        keyword = "편의점 " + keyword;
        System.out.println("2"+keyword);
        
        try {
            url = new URL("https://openapi.naver.com/v1/search/blog.xml?query=" + URLEncoder.encode(keyword, "UTF-8")
                    + (display != 0 ? "&display=" + display : "") + (start != 0 ? "&start=" + start : ""));
            URLConnection urlConn;
 
            //url 연결
            urlConn = url.openConnection();
            urlConn.setRequestProperty("X-naver-Client-Id", clientID);
            urlConn.setRequestProperty("X-naver-Client-Secret", clientSecret);
 
            //파싱 - 팩토리 만들고 팩토리로 파서 생성 (풀 파서 사용)
            XmlPullParserFactory factory; 
 
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            System.out.println("parser: "+parser.toString());
            
            parser.setInput((new InputStreamReader(urlConn.getInputStream())));

            int eventType = parser.getEventType();
            NblogVo blogvo = null;
            
            while (eventType != XmlPullParser.END_DOCUMENT) {  //parser가 시작 태그를 만나면 실행
                switch (eventType) {
                case XmlPullParser.END_DOCUMENT: // 문서의 끝
                    break;
                case XmlPullParser.START_DOCUMENT:
                    list = new ArrayList<NblogVo>();
                    break;
                case XmlPullParser.START_TAG: {
                    String tag = parser.getName();
                    switch (tag) {
                    case "item":
                    	blogvo = new NblogVo();
                    	
                    	System.out.println("item : " + blogvo);
                        break;
                    case "title":
                        if (blogvo != null)
                        	blogvo.setTitle(parser.nextText());
                        break;
                    case "link":
                        if (blogvo != null)
                            blogvo.setLink(parser.nextText());
                        break;
                    case "description":
                        if (blogvo != null)
                            blogvo.setDescription(parser.nextText());
                        break;
                    case "bloggername":
                        if (blogvo != null)
                            blogvo.setBloggername(parser.nextText());
                        break;
                    case "bloggerlink":
                        if (blogvo != null)
                            blogvo.setBloggerlink(parser.nextText());
                        break;
                    }
                    break;
                }
 
                case XmlPullParser.END_TAG: {
                    String tag = parser.getName();
                    if (tag.equals("item")) {
                        list.add(blogvo);
                        blogvo = null;
                        }
                    }
                }
                eventType = parser.next();
            }
 
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
        
        return list;
    }
    // 1000원 이하 랜덤 상품 (출석체크 상품 증정)
 	public StoreProductVo random1000(Long user_no, Long store_no) {
 		
 		StoreProductVo vo = productdao.random1000(store_no);
 		
 		//기프티콘으로 저장
 		Long storeproduct_no = vo.getNo();
 		gifticonservice.insert(user_no, storeproduct_no, store_no);
 		
 		//수량 감소
 		productdao.cutmount(storeproduct_no);
 		
 		return vo;
 	}	
 	
 	// 2000원 이하 랜덤 상품 (출석체크 상품 증정)
  	public StoreProductVo random2000(Long user_no, Long store_no) {
  		
  		StoreProductVo vo = productdao.random2000(store_no);
 		
 		//기프티콘으로 저장
 		Long storeproduct_no = vo.getNo();
 		gifticonservice.insert(user_no, storeproduct_no, store_no);
 		
 		//수량 감소
 		productdao.cutmount(storeproduct_no);
 		
 		return vo;
  	}
  	
  	//카트
  	public CartVo maintainCheck(Long user_no, Long product_no){
		CartVo checkVo = productdao.maintainCheck(user_no, product_no);
		return checkVo;
	}
  	
  //서브 상품 검색 리스트
    public Map<String, Object> listBoard(String spage, String keyword,Long StoreNo){
    	
    	// 1. 페이지 값 받기
		int page=Integer.parseInt(spage);
		
		// 2. 페이지를 그리기 위한 기초 작업
		int totalCount = productdao.getTotalCount(StoreNo);
		int pageCount = (int) Math.ceil((double) totalCount / LIST_PAGESIZE);
		int blockCount = (int) Math.ceil((double) pageCount / LIST_BLOCKSIZE);
		int currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);
		
		// 3. page값 검증
		if (page < 1) {
			page = 1;
			currentBlock = 1;
		} else if (page > pageCount) {
			page = pageCount;
			currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);
		}


		// 4. 페이지를 그리기 위한 값 계산
		int startPage = (currentBlock - 1) * LIST_BLOCKSIZE + 1;
		int endPage = (startPage - 1) + LIST_BLOCKSIZE;
		int prevPage = (page >= startPage) ? (page-1) : (currentBlock - 1) * LIST_BLOCKSIZE;
		int nextPage = (page <= endPage) ? (page+1) : currentBlock * LIST_BLOCKSIZE + 1;
		int nexttoPage = (currentBlock < blockCount) ? currentBlock * LIST_BLOCKSIZE + 1 : page;
		int prevtoPage = (currentBlock > 1) ? startPage-3  : page;
		
		List<StoreProductVo> list= productdao.getList(page, LIST_PAGESIZE, keyword,StoreNo);
		
		// 6. map에 객체 담기
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("sizeList", LIST_PAGESIZE);
		map.put("firstPage", startPage);
		map.put("lastPage", endPage);
		map.put("prevPage", prevPage);
		map.put("nextPage", nextPage);
		map.put("currentPage", page);
		map.put("pageCount", pageCount);
		map.put("list", list);
		map.put("totalCount", totalCount);
		map.put("keyword", keyword);
		map.put("nexttoPage", nexttoPage);
		map.put("prevtoPage", prevtoPage);
		
		
		return map;
	}
}
