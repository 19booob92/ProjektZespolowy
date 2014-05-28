package com.pwr.Other;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.JOptCommandLinePropertySource;
import org.springframework.stereotype.Component;

import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.pwr.UserRegistration.Requests;
import com.pwr.UserRegistration.UserDTO;
import com.pwr.UserRegistration.UserGameDTO;

@Component
public class CreateHTMLRaport {

	@Autowired
	private Requests requests;

	public void generateHTML() {
		File file = new File("RAPORT.html");

		List<UserDTO> listaUserow;
		try {
			listaUserow = requests.getAllUsers();
			Collections.sort(listaUserow);

			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write("<html>");
			bw.write("<head>");

			bw.write("<style>");

			bw.write("<!--");
			
			FileInputStream fstream = new FileInputStream("style.css");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while((strLine = br.readLine())!= null) {
				bw.write(strLine);
			}

			bw.write("-->");

			bw.write("</style>");

			bw.write("</head>");
			bw.write("<body>");
			bw.write("<h1 align=\"center\">Raport</h1>");

			bw.write("<table class=\"stats\" border=\"2\" align=\"center\">");
			// to jest chyba nie wydajne !!!
			bw.write("<td align=\"center\">Login</td>");
			bw.write("<td align=\"center\">Punkty</td>");
			bw.write("<td align=\"center\">Data Ukonczenia</td>");

			for (UserDTO userDTO : listaUserow) {
				UserGameDTO userGameDTO = userDTO.getUserGame();
				bw.write("<tr align=\"center\">");
				bw.write("<td>" + userDTO.getLogin() + "</td>");
				if (userGameDTO != null) {
					bw.write("<td>" + userGameDTO.getPoints() + "</td>");
					bw.write("<td>" + userGameDTO.getEndTime().toString()
							+ "</td>");

				} else {
					bw.write("<td>Brak</td>");
					bw.write("<td>Brak</td>");
				}
				bw.write("</tr>");
			}

			bw.write("</table>");

			bw.write("</body>");
			bw.write("</html>");

			bw.close();

			Desktop.getDesktop().browse(file.toURI());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Nie udalo sie utworzyc raportu");
			e.printStackTrace();
		}
	}
}
