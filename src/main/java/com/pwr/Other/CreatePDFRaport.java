package com.pwr.Other;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import org.xhtmlrenderer.pdf.ITextRenderer;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.Base64;
import com.lowagie.text.html.HtmlTags;
import com.lowagie.text.html.simpleparser.*;
import com.pwr.UserRegistration.Requests;
import com.pwr.UserRegistration.UserDTO;
import com.pwr.UserRegistration.UserGameDTO;

@Component
public class CreatePDFRaport {

	@Autowired
	private Requests requests;

	private Document document;
	static final int COLUMNS_COUNT = 3;

	public CreatePDFRaport() {
	}

	public void generatePDF() {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					document = new Document();
					String inputFile = "RAPORT.html";
					String outputFile = "RAPORT.pdf";
					String url = new File(inputFile).toURI().toURL().toString();
					OutputStream os = new FileOutputStream(outputFile);
					ITextRenderer renderer = new ITextRenderer();
					renderer.setDocument(url);
					renderer.layout();
					renderer.createPDF(os);
					os.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Nie mozna zapisac pliku");
					System.err.println(e);
				}
			}
		});

	}
}
