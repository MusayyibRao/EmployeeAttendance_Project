package com.PSL.management.bulkDataUpload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.PSL.management.employeeModel.EmployeeEntity;

import javax.sql.rowset.serial.SerialBlob;


public class RegisterEmployeesHelper {

	
	public static boolean checkExcelFormat(MultipartFile file) {

		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}

	}

	public static List<EmployeeEntity> convertExcelToListOfProduct(InputStream employeesFile, String filename) throws IOException, SQLException {
		List<EmployeeEntity> newEmployeeslist = new ArrayList<>();
		
		XSSFWorkbook newEmployeesWorkbook =  new XSSFWorkbook(employeesFile);

		XSSFSheet sheet = newEmployeesWorkbook.getSheet(filename);

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

			EmployeeEntity employeeEntity = new EmployeeEntity();

			while (cells.hasNext() == true) {
				Cell cell = cells.next();
				switch (cid) {
				case 0:
					employeeEntity.setEmployeeId(cell.getStringCellValue());
					break;
				case 1:
					employeeEntity.setEmployeeFirstName(cell.getStringCellValue());
					break;
				case 2:
					employeeEntity.setEmployeeLastName(cell.getStringCellValue());
					break;
				case 3:
					employeeEntity.setEmail(cell.getStringCellValue());
					break;
				case 4:
					long phoneNumber = (long) cell.getNumericCellValue();
					String empPhone = String.valueOf(phoneNumber);
					employeeEntity.setEmployeePhoneNumber(empPhone);
					break;
				case 5:
					employeeEntity.setEmployeeAddress(cell.getStringCellValue());
					break;
				case 6:
					employeeEntity.setEmployeeRegistrationDate(cell.getDateCellValue());
					break;
				case 7:
					employeeEntity.setEmployeeJoiningDate(cell.getDateCellValue());
					break;
				case 8:
//					employeeRegistrationEntityModel.setEmployeeleavedate(cell.getDateCellValue());
					break;
				case 9:
					String employeeImg = cell.getStringCellValue();
					FileInputStream img = new FileInputStream(employeeImg);
					byte[] fileContent = img.readAllBytes();
					Blob photoBlob = new SerialBlob(fileContent);
					employeeEntity.setEmployeeImage(photoBlob);
					break;
				default:
					break;
				}
				cid++;
			}
			newEmployeeslist.add(employeeEntity);
		}
		return newEmployeeslist;
	}
}
