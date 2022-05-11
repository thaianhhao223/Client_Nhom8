package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.entity.Customer;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomerCsvExporter {
    public void export (List<Customer> customerList, HttpServletResponse response) throws IOException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = dateFormatter.format(new Date());
        String fileName = "customers_" + timestamp +".csv";
        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+ fileName;
        response.setHeader(headerKey, headerValue);
        ICsvBeanWriter iCsvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[]    csvHeader = {"Ma KH", "Ho", "Ten", "Email", "SDT", "Dia chi"};
        String[]    fieldMapping = {"id", "firstName", "lastName", "email", "phoneNumber", "address"};
        iCsvBeanWriter.writeHeader(csvHeader);
        for(Customer customer : customerList){

            iCsvBeanWriter.write(customer, fieldMapping);
        }
        iCsvBeanWriter.close();
    }
}
