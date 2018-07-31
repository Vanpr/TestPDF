package me.aboullaite.view;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import me.aboullaite.model.User;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class PdfView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"my-pdf-file.pdf\"");

        List<User> users = (List<User>) model.get("users");
        document.add(new Paragraph("Generated Users " + LocalDate.now()));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("Generation", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Gender", font));
        cell.setColspan(2);
        cell.setRowspan(4);
        table.addCell(cell);

        cell.setPhrase(new Phrase("Course", font));
        cell.setRowspan(3);
        table.addCell(cell);

        cell.setPhrase(new Phrase("No of Student", font));
        cell.setColspan(2);
        table.addCell(cell);



        for(User user : users){
            table.addCell(user.getFirstName());
            table.addCell(user.getLastName());


        }

        document.add(table);
    }
}
