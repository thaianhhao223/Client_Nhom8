package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.entity.Product;
import com.iuh.clientnhom8.entity.ProductType;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProductCsvExporter {
    public void export (List<Product> products, HttpServletResponse response) throws IOException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = dateFormatter.format(new Date());
        String fileName = "product_" + timestamp +".csv";
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+ fileName;
        response.setHeader(headerKey, headerValue);
        ICsvBeanWriter iCsvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[]    csvHeader = {"Ma SP", "Ten SP", "Mo ta", "Giam gia", "Gia", "Size", "So luong ton"};
        String[]    fieldMapping = {"id", "name", "description", "percentDiscount", "price", "size", "stock"};
        iCsvBeanWriter.writeHeader(csvHeader);
        for(Product product : products){
            iCsvBeanWriter.write(product, fieldMapping);
        }
        iCsvBeanWriter.close();
    }
}
