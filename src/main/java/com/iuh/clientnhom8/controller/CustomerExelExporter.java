package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.entity.Customer;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomerExelExporter extends AbtractExporter{
private XSSFWorkbook workbook ;
       private XSSFSheet sheet;
       public CustomerExelExporter(){
           workbook = new XSSFWorkbook();
       }
    private void writeHeaderLine(){
        sheet = workbook.createSheet("Customer");
        XSSFRow row = sheet.createRow(0);

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        cellStyle.setFont(font);
        createCell(row, 0, "Ma KH", cellStyle);
        createCell(row, 1, "Ho", cellStyle);
        createCell(row, 2, "Ten", cellStyle);
        createCell(row, 3, "SDT", cellStyle);
        createCell(row, 4, "Email", cellStyle);
        createCell(row, 5, "Dia chi", cellStyle);
    }
    private void createCell(XSSFRow row, int columIndex, Object value, CellStyle style){
        XSSFCell cell = row.createCell(columIndex);
        sheet.autoSizeColumn(columIndex);
        if(value instanceof Integer){
            cell.setCellValue((Integer) value);
        } else if(value instanceof Boolean){
            cell.setCellValue((Boolean) value);
         } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);

    }
    public void export (List<Customer> customerList, HttpServletResponse response) throws IOException {
       super.setResponseHeader(response, "application/octet-stream", ".xlsx");
        writeHeaderLine();
        writeDataLines(customerList);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    private void writeDataLines(List<Customer> customerList) {
           int rowIndex = 1;
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(13);
            cellStyle.setFont(font);
           for (Customer customer : customerList){
               XSSFRow row = sheet.createRow(rowIndex++);
               int columnIndex = 0;
               createCell(row, columnIndex++, customer.getId(), cellStyle);
               createCell(row, columnIndex++, customer.getFirstName(), cellStyle);
               createCell(row, columnIndex++, customer.getLastName(), cellStyle);
               createCell(row, columnIndex++, customer.getEmail(), cellStyle);
               createCell(row, columnIndex++, customer.getPhoneNumber(), cellStyle);
               createCell(row, columnIndex++, customer.getAddress(), cellStyle);
           }
    }


}
