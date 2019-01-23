package com.FCdemo.setting;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.FCdemo.model.Sum;
import com.FCdemo.service.FinanceDAO;

@Repository
public class dbSumDAO implements FinanceDAO {
	private JdbcTemplate jdbcTemplate;
	String sql;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int searchbyKind(String kind) {
		sql = "select * from FinanceManager where KindID like ?";
		List<Sum> list = jdbcTemplate.query(kind, new SumMapper(), kind);
		if (list.size() > 0) {
			System.out.println("search done");
			return 1;
		} else
			System.out.println("search error");
		return 0;
	}

	@Override
	public float sumAmount(List<Sum> list) {
		float sumAmount = 0;
		for (Sum sum : list) {
			sumAmount += sum.getAmount();
		}
		return sumAmount;
	}

	@Override
	public List<Sum> filterEMP(String name,String type) {
		System.out.println(name+"\t"+type);
		sql = "SELECT emp.Name,emp.Age,emp.KindID,finance.Amount,finance.Name FROM QLNhanSu AS emp,QLTaiChinh AS finance WHERE emp.KindID=finance.KindID "
				+ "and emp.name like ? and emp.KindID like ? order by emp.name ASC";
		List<Sum> list = jdbcTemplate.query(sql, new RowMapper<Sum>() {

			@Override
			public Sum mapRow(ResultSet rs, int rowNum) throws SQLException {
				Sum emp = new Sum();
				emp.setName(rs.getString(1));
				emp.setAge(rs.getInt(2));
				emp.setKindID(rs.getString(3));
				emp.setAmount(rs.getDouble(4));
				emp.setDescription(rs.getString(5));
				return emp;
			}

		},"%"+name.trim()+"%",type);
		if (!list.isEmpty()) {
			System.out.println("k null");
			return list;
		}
			System.out.println("null");
		return null;
	}

	public List<Sum> filter() {
		
		sql = "SELECT emp.Name,emp.Age,emp.KindID,finance.Amount,finance.Name FROM QLNhanSu AS emp,QLTaiChinh AS finance WHERE emp.KindID=finance.KindID "
				+ " order by emp.name ASC";
		List<Sum> list = jdbcTemplate.query(sql, new RowMapper<Sum>() {

			@Override
			public Sum mapRow(ResultSet rs, int rowNum) throws SQLException {
				Sum emp = new Sum();
				emp.setName(rs.getString(1));
				emp.setAge(rs.getInt(2));
				emp.setKindID(rs.getString(3));
				emp.setAmount(rs.getDouble(4));
				emp.setDescription(rs.getString(5));
				return emp;
			}

		});
		if (!list.isEmpty()) {
			System.out.println("k null");
			return list;
		}
			System.out.println("null");
		return null;
	}
	
	public int getNumbSum() {
		sql = "SELECT COUNT(*) FROM QLNhanSu AS emp,QLTaiChinh AS finance WHERE emp.KindID=finance.KindID";
		List<Integer> total = jdbcTemplate.query(sql, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int numb;
				numb = rs.getInt(1);
				return numb;
			}
		});
		return total.get(0);
	}

	public List<Sum> getPageSum(int numbEmp, int pagenumb) {

		sql = "SELECT emp.Name,emp.Age,emp.KindID,finance.Amount,finance.Name FROM QLNhanSu AS emp,"
				+ "QLTaiChinh AS finance WHERE emp.KindID=finance.KindID "
				+ "order by emp.name offset ? rows FETCH NEXT ? ROWS ONLY;";
		
		List<Sum> list = jdbcTemplate.query(sql, new RowMapper<Sum>() {

			@Override
			public Sum mapRow(ResultSet rs, int rowNum) throws SQLException {
				Sum emp = new Sum();
				emp.setName(rs.getString(1));
				emp.setAge(rs.getInt(2));
				emp.setKindID(rs.getString(3));
				emp.setAmount(rs.getDouble(4));
				emp.setDescription(rs.getString(5));
				return emp;
			}

		}, (pagenumb - 1) * numbEmp, numbEmp);
		System.out.println(list.size());
		if (list.isEmpty())
			return null;
		return list;
	}
	
	public int getNumbSearch(String name,String type) {
		sql = "SELECT COUNT(*) FROM QLNhanSu AS emp,QLTaiChinh AS finance WHERE emp.KindID=finance.KindID"
				+ " and emp.Name like ? and emp.KindID like ?";
		List<Integer> total = jdbcTemplate.query(sql, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int numb;
				numb = rs.getInt(1);
				return numb;
			}
		},"%"+name.trim()+"%",type);
		return total.get(0);
	}
	
	public List<Sum> getPageSearch(int numbEmp, int pagenumb,String name,String type) {

		sql = "SELECT emp.Name,emp.Age,emp.KindID,finance.Amount,finance.Name FROM QLNhanSu AS emp,"
				+ " QLTaiChinh AS finance WHERE emp.KindID=finance.KindID "
				+ " and emp.Name like ? and emp.KindID like ? "
				+ " order by emp.name offset ? rows FETCH NEXT ? ROWS ONLY;";
		
		List<Sum> list = jdbcTemplate.query(sql, new RowMapper<Sum>() {

			@Override
			public Sum mapRow(ResultSet rs, int rowNum) throws SQLException {
				Sum emp = new Sum();
				emp.setName(rs.getString(1));
				emp.setAge(rs.getInt(2));
				emp.setKindID(rs.getString(3));
				emp.setAmount(rs.getDouble(4));
				emp.setDescription(rs.getString(5));
				return emp;
			}

		},"%"+name.trim()+"%",type, (pagenumb - 1) * numbEmp, numbEmp);
		System.out.println(list.size());
		if (list.isEmpty())
			return null;
		return list;
	}
}

class SumMapper implements RowMapper<Sum> {

	@Override
	public Sum mapRow(ResultSet rs, int rowNum) throws SQLException {
		Sum test = new Sum();
		test.setName(rs.getString(1));
		test.setAge(rs.getInt(2));
		test.setAmount(rs.getDouble(3));
		test.setKindID(rs.getString(4));
		return test;
	}

}