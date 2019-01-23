package com.FCdemo.setting;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.FCdemo.model.NhanSu;

public class EmployeeMapper implements RowMapper<NhanSu>{

	@Override
	public NhanSu mapRow(ResultSet rs, int rowNum) throws SQLException {
		NhanSu emp=new NhanSu();
		emp.setID(rs.getInt(1));
		emp.setName(rs.getString(2));
		emp.setAge(rs.getInt(3));
		emp.setSex(rs.getString(4));
		emp.setKindID(rs.getString(5));
		emp.setAvatar(rs.getString(6));
		return emp;
	}
	
}
