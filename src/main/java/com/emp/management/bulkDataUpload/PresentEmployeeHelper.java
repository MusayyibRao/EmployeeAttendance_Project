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
import org.springframework.web.multipart.MultipartFile;

import com.emp.management.employeeModel.EmployeeAttendanceEntity;


public class PresentEmployeeHelper {

	

	public static boolean checkExcelFormat(MultipartFile file) {

		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}

	}

	public static List<EmployeeAttendanceEntity> convertExcelToListOfProduct(InputStream absentFile, String filename) throws IOException {
		List<EmployeeAttendanceEntity> employeePresentlist = new ArrayList<>();
		
		XSSFWorkbook employeePresentWorkbook =  new XSSFWorkbook(absentFile);

		XSSFSheet sheet = employeePresentWorkbook.getSheet(filename);

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

			EmployeeAttendanceEntity employeePresent = new EmployeeAttendanceEntity();

			while (cells.hasNext() == true) {
				Cell cell = cells.next();
				switch (cid) {
				case 0:
					employeePresent.setEmployeeId(cell.getStringCellValue());
					break;
				case 1:
					employeePresent.setEmployeeName(cell.getStringCellValue());
					break;
				case 2:
					employeePresent.setAttendanceDate(cell.getDateCellValue());
					break;
				case 3:
					employeePresent.setEntryTime(cell.getDateCellValue());
					break;
				case 4:
					employeePresent.setExitTime(cell.getDateCellValue());
					break;
				default:
					break;
				}
				cid++;
			}
			employeePresentlist.add(employeePresent);
		}
		return employeePresentlist;
	}
}
