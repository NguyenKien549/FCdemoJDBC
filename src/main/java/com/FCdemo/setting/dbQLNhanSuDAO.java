package com.FCdemo.setting;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.FCdemo.model.NhanSu;

@Repository
public class dbQLNhanSuDAO {
	private JdbcTemplate jdbcTemplate;
	String sql;

	@Autowired
	public void setDataSource(DataSource dataSource) { // ten doi tuong trung voi id cua datasource
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean checkID(int id) {
		sql = "Select * from QLNhanSu where ID=?";
		List<NhanSu> nhanSu = jdbcTemplate.query(sql, new EmployeeMapper(), id);
		if (!nhanSu.isEmpty()) {
			return true;
		}
		return false;
	}

	public NhanSu getID(int id) {
		sql = "Select * from QLNhanSu where ID=?";
		List<NhanSu> nhanSu = jdbcTemplate.query(sql, new EmployeeMapper(), id);
		return nhanSu.get(0);
	}

	// public byte[] convert(String path) throws IOException {
	// File imagefile=new File(path);
	// InputStream is=new FileInputStream(new File(path));
	// InputStreamReader ir=new InputStreamReader(is);
	// byte[] data=new byte[(int) imagefile.length()];
	//
	// return
	// }

	// Read the image using the read() method of the ImageIO class.
	// Create a ByteArrayOutputStream object.
	// Write the image to the ByteArrayOutputStream object created above using the
	// write() method of the ImageIO class.
	// Finally, convert the contents of the ByteArrayOutputStream to a byte array
	// using the toByteArray() method.
	// public byte[] extractBytes (String file) throws IOException {
	// // open image
	// BufferedImage bImage = ImageIO.read(new File(file));
	// ByteArrayOutputStream bos = new ByteArrayOutputStream();
	// ImageIO.write(bImage, "jpg", bos );
	// byte [] data = bos.toByteArray();
	// return data;
	// }

	public String encodebase64(byte[] bytes) throws IOException {

		return Base64.getEncoder().encodeToString(bytes);

	}

	// decode
	// public byte[] decodebase64(String encode) {
	// return Base64.getDecoder().decode(encode);
	// }
	//
	// public Image convertimage(byte[] bytes) throws IOException {
	// BufferedImage bfimage= ImageIO.read(new ByteArrayInputStream(bytes));
	// return bfimage;
	// }
	//

	public int add(NhanSu nhanSu) throws ClassNotFoundException, SQLException, IOException {
		
			if(nhanSu.getName()!=null && nhanSu.getAge()>0 && nhanSu.getSex().trim()!=null) {
				sql = "INSERT INTO QLNhanSu(Name,Age,Sex,KindID,Avatar) VALUES (?,?,?,?,?)";
				int a = jdbcTemplate.update(sql, nhanSu.getName(), nhanSu.getAge(), nhanSu.getSex(), nhanSu.getKindID(),
						nhanSu.getAvatar());
	
				if (a > 0) {
					System.out.println("insert done");
					return 1;
				} else {
					System.out.println("loi them emp!!!kiem tra lai thong tin");
					return 0;
				}
			}
			return -1;
	}

//	public int addAvatar(String encodeAvt, int id) {
//		boolean check = checkID(id);
//		if (check) {
//			sql = "UPDATE QLNhanSu SET Avatar=? where id=?";
//			int a = jdbcTemplate.update(sql, encodeAvt, id);
//			if (a > 0) {
//				System.out.println("Done");
//				return 1;
//			} else {
//				System.out.println("Not Avatar");
//				return 0;
//			}
//		} else {
//			System.out.println("k co trong database");
//		}
//
//		return 0;
//	}

	public int update(NhanSu emp) {
		boolean check = checkID(emp.getID());
		if (check) {
			sql = "UPDATE QLNhanSu SET Name=? , Age=? , Sex=? , KindID=?,Avatar=? Where ID=?";
			int a = jdbcTemplate.update(sql, emp.getName(), emp.getAge(), emp.getSex(), emp.getKindID(),
					emp.getAvatar(), emp.getID());
			if (a > 0) {
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

	public int delete(int id) {
		// boolean check=checkID(id);
		// if(check) {
		sql = "Delete from QLNhanSu where ID=?";
		// String sql2="DBCC CHECKIDENT ('QLNhanSu', RESEED, 0)";
		int a = jdbcTemplate.update(sql, id);
		// jdbcTemplate.execute(sql2);
		if (a > 0) {
			System.out.println("delete done");
			return 1;
		} else {
			System.out.println("delete error");
			return 0;
		}
		// }else System.out.println("k co trong csdl");
		// return -1;
	}

	public List<NhanSu> getlist() {
		sql = "select * from QLNhanSu";
		List<NhanSu> list = jdbcTemplate.query(sql, new EmployeeMapper());
		return list;
	}

	// lay danh sach thanh vien 1 trang
	public List<NhanSu> getPage(int numbEmp, int pagenumb) {

		sql = "select * from qlnhansu order by id offset ? rows FETCH NEXT ? ROWS ONLY;";
		List<NhanSu> pageEmp = jdbcTemplate.query(sql, new EmployeeMapper(), (pagenumb - 1) * numbEmp, numbEmp);
		if (pageEmp.isEmpty())
			return null;
		return pageEmp;
	}

	public int getNumbEmp() {
		sql = "SELECT COUNT(id) FROM QLNhanSu";
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
}
