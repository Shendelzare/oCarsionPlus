package com.grupolemon.ocarsionplus.utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.grupolemon.ocarsionplus.model.Car;
import com.grupolemon.ocarsionplus.model.Color;
import com.grupolemon.ocarsionplus.model.Price;

public class CarExcelGenerator {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Car> listCars;

	public CarExcelGenerator(List<Car> listCars) {
		this.listCars = listCars;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Cars");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Car ID", style);
		createCell(row, 1, "Model name", style);
		createCell(row, 2, "Color", style);
		createCell(row, 3, "Brand", style);
		createCell(row, 4, "Price", style);
		createCell(row, 5, "Effective date", style);
		createCell(row, 6, "End Effective Date", style);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Color) {
			cell.setCellValue(((Color) value).name());
		} else if (value instanceof Double) {
			cell.setCellValue((Double) value);
		} else if (value instanceof LocalDate) {
			cell.setCellValue(((LocalDate) value).toString());
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Car car : listCars) {

			for (Price price : car.getPrice()) {
				Row row = sheet.createRow(rowCount++);
				int columnCount = 0;
				createCell(row, columnCount++, car.getId(), style);
				createCell(row, columnCount++, car.getModelName(), style);
				createCell(row, columnCount++, car.getColor(), style);
				createCell(row, columnCount++, car.getBrand().getBrandName(), style);
				createCell(row, columnCount++, price.getValue(), style);
				createCell(row, columnCount++, price.getEffectiveDate(), style);
				createCell(row, columnCount++, price.getEndEffectiveDate(), style);
			}
		}
	}

	public byte[] export() throws IOException {
		writeHeaderLine();
		writeDataLines();

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			workbook.write(bos);
			workbook.close();
		} finally {
			bos.close();
		}
		return bos.toByteArray();

	}

}
