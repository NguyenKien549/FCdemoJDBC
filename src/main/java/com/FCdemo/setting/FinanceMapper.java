package com.FCdemo.setting;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.FCdemo.model.TaiChinh;

public class FinanceMapper implements RowMapper<TaiChinh>{
	@Override
	public TaiChinh mapRow(ResultSet rs, int rowNum) throws SQLException {
		TaiChinh temp=new TaiChinh();
		temp.setID(rs.getInt(1));
		temp.setName(rs.getString(2));
		temp.setAmount(rs.getFloat(3));
		temp.setKindID(rs.getString(4));
		return temp;
	}
}