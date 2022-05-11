package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.entity.Customer;
import com.iuh.clientnhom8.entity.Product;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductExelExporter extends AbtractExporter{
private XSSFWorkbook workbook ;
       private XSSFSheet sheet;
       public ProductExelExporter(){
           workbook = new XSSFWorkbook();
       }
    private void writeHeaderLine(){
        sheet = workbook.createSheet("Product");
        XSSFRow row = sheet.createRow(0);

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        cellStyle.setFont(font);

        createCell(row, 0, "Ma SP", cellStyle);
        createCell(row, 1, "Ten SP", cellStyle);
        createCell(row, 2, "Gia SP", cellStyle);
        createCell(row, 3, "Giam Gia", cellStyle);
        createCell(row, 4, "Kich Thuoc", cellStyle);
        createCell(row, 5, "So Luong Ton", cellStyle);
        createCell(row, 6, "Mo ta", cellStyle);
    }
    private void createCell(XSSFRow row, int columIndex, Object value, CellStyle style){
        XSSFCell cell = row.createCell(columIndex);
        sheet.autoSizeColumn(columIndex);
        if(value instanceof Integer){
            cell.setCellValue((Integer) value);
        } else if(value instanceof Boolean){
            cell.setCellValue((Boolean) value);
         } else if(value instanceof String){
            cell.setCellValue((String) value);
        } else{
            cell.setCellValue((Float) value);
        }
        cell.setCellStyle(style);

    }
    public void export (List<Product> products, HttpServletResponse response) throws IOException {
       super.setResponseHeader(response, "application/octet-stream", ".xlsx");
        writeHeaderLine();
        writeDataLines(products);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    private void writeDataLines(List<Product> products) {
           int rowIndex = 1;
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(13);
            cellStyle.setFont(font);
           for (Product product : products){
               XSSFRow row = sheet.createRow(rowIndex++);
               int columnIndex = 0;
               createCell(row, columnIndex++, product.getId(), cellStyle);
               createCell(row, columnIndex++, product.getName(), cellStyle);
               createCell(row, columnIndex++, product.getPrice().toString(), cellStyle);
               createCell(row, columnIndex++, product.getPercentDiscount(), cellStyle);
               createCell(row, columnIndex++, product.getSize(), cellStyle);
               createCell(row, columnIndex++, product.getAmountToString(), cellStyle);
               createCell(row, columnIndex++, product.getDescription(), cellStyle);
           }
    }


}
