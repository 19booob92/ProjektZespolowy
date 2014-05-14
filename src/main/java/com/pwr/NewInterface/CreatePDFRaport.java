package com.pwr.NewInterface;

import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.pwr.UserRegistration.Requests;
import com.pwr.UserRegistration.UserDTO;
import com.pwr.UserRegistration.UserGameDTO;

@Component
public class CreatePDFRaport {

	@Autowired
	private Requests requests;

	private Document document;

	public CreatePDFRaport() {
		document = new Document();
	}

	public void generatePDF() {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(
					"RAPORT.pdf");
			PdfWriter pdfWriter = PdfWriter.getInstance(document, fileOutputStream);

			document.open();

			PdfPTable table = new PdfPTable(3); // 3 columns.

			Font font = new Font(Font.BOLD);

			PdfPCell loginCol = new PdfPCell(new Paragraph("Login", font));
			PdfPCell pointsCol = new PdfPCell(new Paragraph("Punkty", font));
			PdfPCell dateCol = new PdfPCell(new Paragraph("Data", font));

			table.addCell(loginCol);
			table.addCell(pointsCol);
			table.addCell(dateCol);
			table.addCell(" ");
			table.completeRow();

			List<UserDTO> listaUserow = requests.getAllUsers();

			// to jest chyba nie wydajne !!!
			for (UserDTO userDTO : listaUserow) {
				PdfPCell login = new PdfPCell(new Paragraph(userDTO.getLogin()));
				table.addCell(login);
				UserGameDTO userGameDTO = userDTO.getUserGame();
				if (userGameDTO != null) {
					PdfPCell points = new PdfPCell(new Paragraph(
							String.valueOf(userGameDTO.getPoints())));
					table.addCell(points);
					PdfPCell date = new PdfPCell(new Paragraph(userGameDTO
							.getEndTime().toString()));
					table.addCell(date);
				} else {
					table.addCell(new PdfPCell(new Paragraph("Brak")));
					table.addCell(new PdfPCell(new Paragraph("Brak")));
				}
			}

			document.add(table);

			document.close();
			pdfWriter.close();
			fileOutputStream.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Nie mozna zapisac pliku");
		}
	}

}
