package com.FCdemo.setting;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class dbQLRoleDAO {
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	public int createRole(String username,String role) {
		try {
			sql="insert into user_role(username,Role) values(?,?)";
			int a=jdbcTemplate.update(sql,username,role);
			if(a>0)System.out.println("role insert done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public List<String> getRole() {
		List<String> a = null;
		try {
			sql="select Role from Role";
			a=jdbcTemplate.query(sql, new RowMapper<String>() {

				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString(1);
				}
				
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	
	
	public String getRoleURL(String url) {
		String a = null;
		try {
			sql="select Role from QLRole where url like ?";
			a=jdbcTemplate.queryForObject(sql, new RowMapper<String>() {

				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString(1);
				}
				
			},url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	
}
