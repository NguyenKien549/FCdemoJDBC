package com.FCdemo.setting;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.FCdemo.model.Account;

@Repository
public class dbAccountDAO {
	private JdbcTemplate jdbcTemplate;
	String sql;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Account getAccount(String username, String password) {
		try {
			sql = "Select Name,Mail,username,password from Account where Account.username like ? and Account.password like ?";
			Account a = jdbcTemplate.queryForObject(sql, new RowMapper<Account>() {

				@Override
				public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				}
			}, username, password);
			if (a != null) {
				System.out.println("dang nhap thanh cong");
				return a;
			} else
				System.out.println("k thanh cong");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean check(String username) {
		List<String> result;
		try {
			sql = "Select username from Account where username like ?";
			result= jdbcTemplate.query(sql, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString(1);
				}
			}, username);

			if (result!=null) {
				System.out.println("co the dang ki dc");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result=null;
		}
		
		return false;
	}

//	public boolean createRole(String username,String role) {
//		sql = "insert QLRole(username,role) values (?,?)";
//		int a=jdbcTemplate.update(sql,username,role);
//		if(a>0) return true;
//		return false;
//	}
	
	public String createAccount(Account account) {
		boolean check = check(account.getUsername());
		if (check) {
			sql = "insert Account(Name,Mail,username,password) values (?,?,?,?)";
			
			int a = jdbcTemplate.update(sql, account.getName(), account.getMail(),
					account.getUsername(), account.getPassword());
			if (a > 0) {
				System.out.println("register done");
				return "done";
			} else {
				System.out.println("register false");
				return "fail";
			}
		} else
			System.out.println("da co trong csdl");
		return "already exist";
	}

}
