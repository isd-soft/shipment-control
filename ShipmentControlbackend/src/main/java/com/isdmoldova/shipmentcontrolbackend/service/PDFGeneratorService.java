package com.isdmoldova.shipmentcontrolbackend.service;

import com.isdmoldova.shipmentcontrolbackend.dto.CargoDTO;
import com.isdmoldova.shipmentcontrolbackend.dto.LegDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PDFGeneratorService {

    private final CargoService cargoService;

    public void export(HttpServletResponse response, Long id) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("INVOICE", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        CargoDTO cargoDTO = cargoService.findById(id);
        Date date = new Date();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusDays(10);
        Date currentDatePlusTenDays = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Paragraph paragraph2 = new Paragraph(
                "TO: " + cargoDTO.getUser()
                        + "\nSENT BY: " + cargoDTO.getProvider()
                        + "\nNo. " + cargoDTO.getTrackingNumber()
                        + "\nDATE: " + date
                        + "\nBank Details & Payment Information"
                        + "\nIBAN MD90AG000000022591234567"
                        + "\nIDNP 0123456789123"
                        + "\nRecipient bank: BC \"MOLDOVA-AGROINDBANK\" SA SWIFT AGRNMD2X"
                , fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        addEmptyLine(paragraph2, 3);

        addLogo(document);

        Paragraph table = new Paragraph();

        createTable(table, cargoDTO);
        addEmptyLine(table, 10);


        Paragraph paragraph5 = new Paragraph("Please make sure to pay by "
                + currentDatePlusTenDays
        );
        paragraph5.setAlignment(Paragraph.ALIGN_LEFT);
        addEmptyLine(paragraph5, 5);


        Paragraph paragraph4 = new Paragraph("Thank you for choosing our services!" +
                "\nIf you have any questions about this invoice please contact" +
                "\nshipmentcontrol2022@gmail.com\n");
        paragraph4.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);
        document.add(paragraph2);
        document.add(table);
        document.add(paragraph5);
        document.add(paragraph4);

        document.close();

    }

    private static void createTable(Paragraph paragraph, CargoDTO cargoDTO) throws BadElementException {
        PdfPTable table = new PdfPTable(3);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        PdfPCell c1 = new PdfPCell(new Phrase("Legs"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Price"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Quantity"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(c1);
        table.setHeaderRows(1);

        Double price = 0d;
        String currency = "";
        List<LegDTO> legList = cargoDTO.getItineraryDTO().getLegDTOS();
        for (LegDTO l : legList) {
            table.addCell(l.getName());
            table.addCell(l.getPrice().toString());
            price += l.getPrice();
            table.addCell("1");
            currency = l.getCurrency().toString();
        }

        paragraph.add(table);
        Paragraph paragraph3 = new Paragraph("Total Payment " + price + " " + currency);
        paragraph3.setAlignment(Paragraph.ALIGN_CENTER);

        paragraph.add(paragraph3);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private void addLogo(Document document) {
        try {
            Image image1 = Image.getInstance(Objects.requireNonNull(Image.class.getResource("/static/logo.jpeg")));
            image1.setAbsolutePosition(400f, 650f);
            image1.scaleAbsolute(150, 150);
            document.add(image1);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

}
