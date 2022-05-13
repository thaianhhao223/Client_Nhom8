package com.iuh.clientnhom8.controller;

import com.iuh.clientnhom8.entity.Customer;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class CustomerPdfExporter extends AbtractExporter{
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Ma KH", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Ho", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Ten", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("SDT", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Dia chi", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table, List<Customer> customerList) {
        for (Customer customer : customerList) {
            table.addCell(customer.getId());
            table.addCell(customer.getFirstName());
            table.addCell(customer.getLastName());
            table.addCell(customer.getPhoneNumber());
            table.addCell(customer.getEmail());
            table.addCell(customer.getAddress());
        }
    }
    public void export(List<Customer> customerList, HttpServletResponse response) throws IOException, DocumentException {
        super.setResponseHeader(response, "application/pdf", ".pdf");
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Danh sach khach hang", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table, customerList);

        document.add(table);

        document.close();
    }

}
