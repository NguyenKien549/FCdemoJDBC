package com.FCdemo.setting;

import java.util.List;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.FCdemo.model.TaiChinh;
import com.FCdemo.service.TaiChinhDAO;

@Repository
public class dbQLTaiChinhDAO implements TaiChinhDAO{
	private JdbcTemplate jdbcTemplate;
	String sql;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	@Override
	public int addTaiChinh(TaiChinh taiChinh) {
		sql="INSERT INTO QLTaiChinh(Name,Amount,KindID) VALUES (?,?,?)";
		int a=jdbcTemplate.update(sql,taiChinh.getName(),taiChinh.getAmount() ,taiChinh.getKindID());
		if(a>0) {
			System.out.println("DONE!!!");
			return 1;
		}else System.out.println("insert error");
		return 0;
	}

	@Override
	public int updateTaiChinh(TaiChinh taiChinh) {
		sql="UPDATE QLTaiChinh SET Name=?,Amount=?,KindID = ? where ID=?";
		int a= jdbcTemplate.update(sql, taiChinh.getName(), taiChinh.getAmount(),taiChinh.getKindID(),taiChinh.getID());
		if (a>0) {
			System.out.println("update done");
			return 1;
		} else {
			System.out.println("update error:"+a);
			
		}
		return 0;
	}

	@Override
	public int deleteTaiChinh(int id) {
			sql="DELETE From QLTaiChinh where ID=?";
			int a= jdbcTemplate.update(sql,id);
			if (a>0) {
				System.out.println("delete done");
				return 1;
			} else {
				System.out.println("delete error");
			}
		return 0;
	}

	
	public List<TaiChinh> getlist(){
		sql="select * from QLTaiChinh";
		List<TaiChinh> list=jdbcTemplate.query(sql, new FinanceMapper());
		return list;
	}
}


