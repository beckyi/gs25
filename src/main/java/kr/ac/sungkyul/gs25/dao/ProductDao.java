package kr.ac.sungkyul.gs25.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.sungkyul.gs25.vo.ProductVo;

@Repository
public class ProductDao {
	
	@Autowired
	private DataSource dataSource;
	
	public int getTotalCount() {
		int totalCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn=dataSource.getConnection();

			String sql = "select count(*) from product";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalCount = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return totalCount;
	}
	

	public List<ProductVo> getList(int page, int pagesize,String keyword) {
		List<ProductVo> list = new ArrayList<ProductVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=dataSource.getConnection();
			String sql = (keyword ==null || "".equals(keyword))? 
		    "select * from(select c.*,rownum rn from(select name,price,to_char(reg_date,'yyyy.mm.dd'),TO_CHAR(EXPIRY_DATE,'YYYY.MM.DD'),maker,product_url from product) c) where ?<=rn and rn<=?"
			: "select * from(select c.*,rownum rn from(select a.NAME,a.PRICE,to_char(a.EXPIRY_DATE,'yyyy-mm-dd') from product a, productkind b where a.KIND_NO=b.NO and a.name like ? order by price desc, a.EXPIRY_DATE asc) c) where ?<=rn and rn<=?";
				
			pstmt = conn.prepareStatement(sql);
			
			if(keyword ==null || "".equals(keyword)){
			pstmt.setInt(1, (page - 1) * pagesize + 1);
			pstmt.setInt(2, page * pagesize);
		
			}else{
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setString(2, "%"+keyword+"%");
			pstmt.setInt(3, (page - 1) * pagesize + 1);
			pstmt.setInt(4, page * pagesize);
			}
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name=rs.getString(1);
				Integer price=rs.getInt(2);
				String regdate=rs.getString(3);
				String expirydate=rs.getString(4);
				String maker=rs.getString(5);
				String producturl=rs.getString(6);
				
				ProductVo vo=new ProductVo();
				vo.setName(name);
				vo.setPrice(price);
				vo.setRegdate(regdate);
				vo.setExpirydate(expirydate);
				vo.setMaker(maker);
				vo.setProducturl(producturl);

				list.add(vo);
				System.out.println(list);
			}
			
			return list;
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
			return list;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	public List<ProductVo> getSubDate() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductVo> list = null;

		try {
			conn=dataSource.getConnection();
			list = new ArrayList<ProductVo>();
			
			String sql = "SELECT name, price, reg_date, expiry_date, maker, product_url"
					+ " FROM (SELECT name, price, reg_date, expiry_date, maker, product_url"
					+ " FROM product where expiry_date > sysdate ORDER BY expiry_date) WHERE rownum < 5";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name=rs.getString(1);
				Integer price=rs.getInt(2);
				String regdate=rs.getString(3);
				String expirydate=rs.getString(4);
				String maker=rs.getString(5);
				String producturl=rs.getString(6);
				
				ProductVo vo=new ProductVo();
				vo.setName(name);
				vo.setPrice(price);
				vo.setRegdate(regdate);
				vo.setExpirydate(expirydate);
				vo.setMaker(maker);
				vo.setProducturl(producturl);

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<ProductVo> getSubNew() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductVo> list = null;

		try {
			conn=dataSource.getConnection();
			list = new ArrayList<ProductVo>();
			
			String sql = "SELECT name, price, reg_date, expiry_date, maker, product_url"
					+ " FROM (SELECT name, price, reg_date, expiry_date, maker, product_url"
					+ " FROM product ORDER BY reg_date) WHERE rownum < 5";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name=rs.getString(1);
				Integer price=rs.getInt(2);
				String regdate=rs.getString(3);
				String expirydate=rs.getString(4);
				String maker=rs.getString(5);
				String producturl=rs.getString(6);
				
				ProductVo vo=new ProductVo();
				vo.setName(name);
				vo.setPrice(price);
				vo.setRegdate(regdate);
				vo.setExpirydate(expirydate);
				vo.setMaker(maker);
				vo.setProducturl(producturl);

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public List<ProductVo> getSubReco() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductVo> list = null;

		try {
			conn=dataSource.getConnection();
			list = new ArrayList<ProductVo>();
			
			String sql = "SELECT name, price, reg_date, expiry_date, maker, product_url"
					+ " FROM(select name, price, reg_date, expiry_date, maker, product_url"
					+ " FROM product order by DBMS_RANDOM.RANDOM) WHERE rownum < 5";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name=rs.getString(1);
				Integer price=rs.getInt(2);
				String regdate=rs.getString(3);
				String expirydate=rs.getString(4);
				String maker=rs.getString(5);
				String producturl=rs.getString(6);
				
				ProductVo vo=new ProductVo();
				vo.setName(name);
				vo.setPrice(price);
				vo.setRegdate(regdate);
				vo.setExpirydate(expirydate);
				vo.setMaker(maker);
				vo.setProducturl(producturl);

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
