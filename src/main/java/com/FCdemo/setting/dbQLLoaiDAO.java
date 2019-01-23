package com.FCdemo.setting;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.FCdemo.model.Loai;
import com.FCdemo.service.LoaiDAO;

@Repository
public class dbQLLoaiDAO implements LoaiDAO {
	JdbcTemplate jdbcTemplate;
	String sql;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate= new JdbcTemplate(dataSource);
	}
	
	public boolean checkLoai(String KindID) {
		sql="Select * from QLLoai where KindID like ?";
		List<Loai> loai2=jdbcTemplate.query(sql, new KindMapper(),KindID);
		if(loai2.size()>0) {
			return true;
		}else return false;
	}
	
	@Override
	public int addLoai(Loai loai) {
		boolean check=checkLoai(loai.getKindID());
		if(!check) {
			String name=loai.getName().trim();
			String des=loai.getDescription().trim();
			sql="INSERT INTO QLLoai(Name,Description,KindID) VALUES (?,?,?)";
			int a=jdbcTemplate.update(sql,name,des,loai.getKindID().trim());
			if(a>0) {
				System.out.println("done");
				return 1;
			}else {
				System.out.println("insert error");
				return 0;
			}
		}else System.out.println("da co trong csdl");
		return -1;
	}

	@Override
	public int updadeLoai(Loai loai) {
		boolean check=checkLoai(loai.getKindID());
		if (check) {
			sql="UPDATE QLLoai SET Name=?, Description=? Where KindID=?";
			int a=jdbcTemplate.update(sql,loai.getName().trim(),loai.getDescription().trim(),loai.getKindID().trim());
			if (a>0) {
				System.out.println("update done");
				return 1;
			} else {
				System.out.println("update error");
				return 0;
			}
		} else 
			System.out.println("k co trong csdl");
			return -1;
	}

	public int deleteLoai(String type) {
			sql="DELETE from QLLoai Where kindid like ?";
			String sql1="delete from QLNhanSu where kindid like ?";
			String sql2="DELETE from QLTaiChinh Where kindid like ?";
			jdbcTemplate.update(sql1,type);
			jdbcTemplate.update(sql2,type);
			int a=jdbcTemplate.update(sql,type);
			if (a>0) {
				System.out.println("delete done");
				return 1;
			} else {
				System.out.println("delete error");
				return 0;
			}
		
	}
	
	//lay danh sach kind vao addEmployee
	//@SuppressWarnings("unchecked")
	public List<String> getlistKind(){
		sql="Select KindID from QLLoai";
		List<String> list=jdbcTemplate.query(sql,new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String temp;
				temp=rs.getString(1);
				return temp;
			}
		});
		if(!list.isEmpty()) {
			System.out.println("loc loai xong");
			return list;
		}else System.out.println("loc loi");
		return null;
	}
	
	public List<Loai> getlist(){
		sql="Select * from QLLoai";
		List<Loai> list=jdbcTemplate.query(sql,new KindMapper());
		if(!list.isEmpty()) {
			System.out.println("loc loai xong");
			return list;
		}else System.out.println("loc loi");
		return null;
	}
}
class KindMapper implements RowMapper<Loai>{

	@Override
	public Loai mapRow(ResultSet rs, int rowNum) throws SQLException {
		Loai temp=new Loai();
		temp.setID(rs.getInt(1));
		temp.setName(rs.getString(2));
		temp.setDescription(rs.getString(3));
		temp.setKindID(rs.getString(4));
		return temp;
	}
	
}
