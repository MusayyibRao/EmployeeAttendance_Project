package com.PSL.management.bulkDataUpload;

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

import com.PSL.management.employeeModel.EmployeeAbsentEntity;


public class AbsentEmployeeHelper {

	
	public static boolean checkExcelFormat(MultipartFile file) {

		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}

	}

	public static List<EmployeeAbsentEntity> convertExcelToListOfProduct(InputStream absentFile, String filename) throws IOException {
		List<EmployeeAbsentEntity> employeeAbsentlist = new ArrayList<>();
		
		XSSFWorkbook employeeAbsentWorkbook =  new XSSFWorkbook(absentFile);

		XSSFSheet sheet = employeeAbsentWorkbook.getSheet(filename);

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

			EmployeeAbsentEntity employeeAbsent = new EmployeeAbsentEntity();

			while (cells.hasNext() == true) {
				Cell cell = cells.next();
				switch (cid) {
				case 0:
					employeeAbsent.setEmployeeId(cell.getStringCellValue());
					break;
				case 1:
					employeeAbsent.setEmployeeName(cell.getStringCellValue());
					break;
				case 2:
					employeeAbsent.setLeaveReason(cell.getStringCellValue());
					break;
				case 3:
					employeeAbsent.setEmloyeeAbsentDate(cell.getDateCellValue());
					break;
				default:
					break;
				}
				cid++;
			}
			employeeAbsentlist.add(employeeAbsent);
		}
		return employeeAbsentlist;
	}
}
