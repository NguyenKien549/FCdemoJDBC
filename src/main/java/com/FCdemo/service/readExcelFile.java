package com.FCdemo.service;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.FCdemo.model.NhanSu;

public class readExcelFile {
	public List<NhanSu> getlistfromExcel(File file) throws IOException, InvalidFormatException {

		List<NhanSu> listEmp = new ArrayList<NhanSu>();
		FileInputStream inputStream = new FileInputStream(file);

		Workbook wb = getWorkbook(inputStream, file.getCanonicalPath());

		Sheet firstsheet = wb.getSheetAt(0); // lay sheet chua thong tin can doc,co the sheet khac
		Iterator<Row> rows = firstsheet.iterator();
		byte[] data = null;
		List<?> listPic = wb.getAllPictures();

		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cells = row.cellIterator();
			NhanSu emp = new NhanSu();
			while (cells.hasNext()) {
				Cell cell = cells.next();
				int index = cell.getColumnIndex();
				switch (index) {
				case 0:
					emp.setName((String) getCellValue(cell));
					break;
				case 1:
					try {
						if (getCellValue(cell) != null) {
							emp.setAge((int) (Double.parseDouble(getCellValue(cell).toString()) / 1));
						} else
							emp.setAge(-1);
					} catch (Exception e) {
						e.printStackTrace();
						emp.setAge(-1);
					}
					break;
				case 2:
					String a = getCellValue(cell).toString().toLowerCase();
					if (a.equals("male") || a.equals("female") || a.equals("other")) {
						emp.setSex((String) getCellValue(cell));
					} else
						emp.setSex("NaN");

					break;
				case 3:
					emp.setKindID( ( (String) getCellValue(cell) ).toUpperCase() );
					break;
				case 4: 
					// file excel bat buoc phai co du lieu trong o thu 4 de cell nhan index de xu
					// li, neu khong coi nhu la o trong,k add duoc anh trong o do
					// anh va du lieu k lien quan den nhau
					PictureData pic = (PictureData) listPic.get(row.getRowNum());
					String type = pic.suggestFileExtension();
					if (type.equals("jpeg") || type.equals("png") || type.equals("jpg")) {
						data = pic.getData();
					}
					emp.setAvatar(Base64.encodeBase64String(data));
					
					// //neu su dung link trong excel nhung can co ham kiem tra xem co phai anh hay
					// k
					// String path=(String) getCellValue(cell);
					// if(path.endsWith("jpg")) {//|| path.endsWith("png")||path.endsWith("jpge")) {
					// BufferedImage bImage = ImageIO.read(new File(path));
					// ByteArrayOutputStream bos = new ByteArrayOutputStream();
					// ImageIO.write(bImage, "jpg", bos );
					// data = bos.toByteArray();
					// }
					// emp.setAvatar(Base64.encodeBase64String(data));
					
					break;
				default:
					System.out.println("du lieu k hop le");
					break;
				}
			}
			listEmp.add(emp);
		}

		inputStream.close();
		return listEmp;
	}

	// public String getPicture(Cell cell) {
	// String base64 = null;
	// PictureData pict = (PictureData) cell;
	// String ext = pict.suggestFileExtension();
	// byte[] data = pict.getData();
	// if (ext.equals("jpeg") || ext.equals("png"))
	// base64 = Base64.encodeBase64String(data);
	// return base64;
	// }

	public Object getCellValue(Cell cell) {

		int type = cell.getCellType();
		if (type == Cell.CELL_TYPE_STRING)
			return cell.getStringCellValue();
		else if (type == Cell.CELL_TYPE_NUMERIC)
			return cell.getNumericCellValue();
		return null;
	}

	public Workbook getWorkbook(FileInputStream inputStream, String excelFilePath)
			throws IOException, InvalidFormatException {
		Workbook workbook = null;
		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}
		return workbook;
	}

}
