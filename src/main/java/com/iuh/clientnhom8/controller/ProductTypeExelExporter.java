package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.entity.Customer;
import com.iuh.clientnhom8.entity.ProductType;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductTypeExelExporter extends AbtractExporter{
private XSSFWorkbook workbook ;
       private XSSFSheet sheet;
       public ProductTypeExelExporter(){
           workbook = new XSSFWorkbook();
       }
    private void writeHeaderLine(){
        sheet = workbook.createSheet("ProductType");
        XSSFRow row = sheet.createRow(0);

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        cellStyle.setFont(font);

        createCell(row, 0, "Ma Loai SP", cellStyle);
        createCell(row, 1, "Ten", cellStyle);
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
    public void export (List<ProductType> productTypes, HttpServletResponse response) throws IOException {
       super.setResponseHeader(response, "application/octet-stream", ".xlsx");
        writeHeaderLine();
        writeDataLines(productTypes);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    private void writeDataLines(List<ProductType> productTypes) {
           int rowIndex = 1;
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(12);
            cellStyle.setFont(font);
           for (ProductType productType : productTypes){
               XSSFRow row = sheet.createRow(rowIndex++);
               int columnIndex = 0;
               createCell(row, columnIndex++, productType.getId(), cellStyle);
               createCell(row, columnIndex++, productType.getName(), cellStyle);
           }
    }


}
