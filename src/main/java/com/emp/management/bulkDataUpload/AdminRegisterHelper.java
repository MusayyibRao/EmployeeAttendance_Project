package com.emp.management.bulkDataUpload;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.emp.management.dataModel.AdminEntity;

public class AdminRegisterHelper {
	
	public static boolean checkExcelFormat(MultipartFile file) {

		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}

	}

	public static List<AdminEntity> convertExcelToListOfProduct(InputStream adminFile, String filename) throws IOException {
		List<AdminEntity> adminlist = new ArrayList<>();
		
		XSSFWorkbook adminWorkbook =  new XSSFWorkbook(adminFile);

		XSSFSheet sheet = adminWorkbook.getSheet(filename);

		int rowNumber = 0;
		Iterator<Row> iterator = sheet.iterator();

		while (iterator.hasNext() == true) {
			Row row = iterator.next();

			if (rowNumber == 0) {
				rowNumber++;
				continue;
			}

			Iterator<Cell> cells = row.iterator();

			int cid = 0;

			AdminEntity adminEntityAdmin = new AdminEntity();

			while (cells.hasNext() == true) {
				Cell cell = cells.next();
				switch (cid) {
				case 0:
					adminEntityAdmin.setFirstname(cell.getStringCellValue());
					break;
				case 1:
					adminEntityAdmin.setLastname(cell.getStringCellValue());
					break;
				case 2:
					adminEntityAdmin.setEmail(cell.getStringCellValue());
					break;
				case 3:
					String passValue = cell.getStringCellValue();
					String password = new BCryptPasswordEncoder().encode(passValue);
					adminEntityAdmin.setPassword(password);
					break;
				case 4:
					adminEntityAdmin.setCreatedBy(cell.getStringCellValue());
					break;
				case 5:
					adminEntityAdmin.setCreatedDate(cell.getDateCellValue());
					break;
				case 6:
					adminEntityAdmin.setModifyBy(cell.getStringCellValue());
					break;
				case 7:
					adminEntityAdmin.setModifyDate(cell.getDateCellValue());
				default:
					break;
				}
				cid++;
			}
			adminlist.add(adminEntityAdmin);
		}
		return adminlist;
	}
}
