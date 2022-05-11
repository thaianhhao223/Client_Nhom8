package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.entity.ProductBrand;
import com.iuh.clientnhom8.entity.ProductType;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductBrandExelExporter extends AbtractExporter{
private XSSFWorkbook workbook ;
       private XSSFSheet sheet;
       public ProductBrandExelExporter(){
           workbook = new XSSFWorkbook();
       }
    private void writeHeaderLine(){
        sheet = workbook.createSheet("ProductBrand");
        XSSFRow row = sheet.createRow(0);

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        cellStyle.setFont(font);

        createCell(row, 0, "Ma TH", cellStyle);
        createCell(row, 1, "Ten TH", cellStyle);
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
    public void export (List<ProductBrand> productBrands, HttpServletResponse response) throws IOException {
       super.setResponseHeader(response, "application/octet-stream", ".xlsx");
        writeHeaderLine();
        writeDataLines(productBrands);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    private void writeDataLines(List<ProductBrand> productBrands) {
           int rowIndex = 1;
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(12);
            cellStyle.setFont(font);
           for (ProductBrand productBrand : productBrands){
               XSSFRow row = sheet.createRow(rowIndex++);
               int columnIndex = 0;
               createCell(row, columnIndex++, productBrand.getId(), cellStyle);
               createCell(row, columnIndex++, productBrand.getName(), cellStyle);
           }
    }


}
