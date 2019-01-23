package com.FCdemo.setting;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.FCdemo.setting.EmployeeMapper;
import com.FCdemo.model.NhanSu;
import com.FCdemo.service.SearchDAO;

public class searchListDAO implements SearchDAO {

	private JdbcTemplate jdbcTemplate;
	String sql;
	
	@Autowired
	public void setDataSource(DataSource dataSource) { //ten doi tuong trung voi id cua datasource
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<NhanSu> searchbyLoai(String loai) {
		sql="select * from QLNhanSu where KindID like ?";
		List<NhanSu> list=jdbcTemplate.query(sql, new EmployeeMapper(), loai.trim());
		if(list.size()>0)
			return list;
		else return null;
	}

	@Override
	public List<NhanSu> searchbyName(String name) {
		sql="select * from QLNhanSu where Name like ? ";
		List<NhanSu> list=jdbcTemplate.query(sql, new EmployeeMapper(), "%"+name+"%");
		if(list.size()>0) {
			System.out.println("done");
			return list;
		}else {
			System.out.println("error");
		}
		return null;
	}

	@Override
	public List<NhanSu> searchbySex(String sex) {
		sql="select * from QLNhanSu where Sex like ? ";
		List<NhanSu> list=jdbcTemplate.query(sql, new EmployeeMapper(), "%"+sex.trim()+"%");
		if(list.size()>0) {
			System.out.println("done");
			return list;
		}else {
			System.out.println("error");
		}
			return null;
	}

	@Override
	public List<NhanSu> searchbyAge(int age) {
		sql="select * from QLNhanSu where Age Like ? ";
		List<NhanSu> list=jdbcTemplate.query(sql, new EmployeeMapper(), "%"+age+"%");
		if(list.size()>0) {
			System.out.println("done");
			return list;
		}else {
			System.out.println("error");
		}
			
		return null;
	}

}
