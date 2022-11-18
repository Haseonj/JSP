package dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.DBHelper;
import db.Sql;
import vo.CustomerVO;

public class CustomerDAO extends DBHelper {

	private static CustomerDAO instance = new CustomerDAO();
	public static CustomerDAO getInstance() {
		return instance;
	}
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private CustomerDAO () {}
	
	public void insertCustomer(CustomerVO vo) {
		try {
			logger.info("insertCustomer...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.INSERT_CUSTOMER);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getAddress());
			psmt.setString(3, vo.getPhone());
			psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public CustomerVO selectCustomer(String custId) {
		CustomerVO vo = null;
		try {
			logger.info("selectCustomer...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.SELECT_CUSTOMER);
			psmt.setString(1, custId);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo = new CustomerVO();
				vo.setCustId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setAddress(rs.getString(3));
				vo.setPhone(rs.getString(4));
			}
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		return vo;
	}
	
	public List<CustomerVO> selectCustomers() {
		List<CustomerVO> books = new ArrayList<>();
		try {
			logger.info("selectCustomers...");
			
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(Sql.SELECT_CUSTOMERS);
			
			while(rs.next()) {
				CustomerVO vo = new CustomerVO();
				vo.setCustId(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setAddress(rs.getString(3));
				vo.setPhone(rs.getString(4));
				
				books.add(vo);
			}
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return books;
	}
	
	public void updateCustomer(CustomerVO vo) {
		try {
			logger.info("updateCustomer...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.UPDATE_CUSTOMER);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getAddress());
			psmt.setString(3, vo.getPhone());
			psmt.setInt(4, vo.getCustId());
			psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void deleteCustomer(String custId) {
		try {
			logger.info("deleteCustomer...");
			conn = getConnection();
			psmt = conn.prepareStatement(Sql.DELETE_CUSTOMER);
			psmt.setString(1, custId);
			psmt.executeUpdate();
			
			close();
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
}

