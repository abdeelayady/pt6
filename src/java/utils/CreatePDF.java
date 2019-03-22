/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;

import java.util.List;
import models.Paciente;

/**
 *
 * @author abde
 */
public class CreatePDF {

    public static void createpdf(List listado) throws FileNotFoundException, DocumentException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("/home/abde/sample1.pdf"));
        document.open();

        document.add(new Paragraph("LISTADO DE PACIENTES",
                FontFactory.getFont("arial",22, Font.ITALIC, BaseColor.CYAN)));   

        document.add(new Paragraph("Edat-GrupEdat-Pes-Altura-IMC-Classificacio-TMeno-Menaruia-Meno"));
        for (Iterator it = listado.iterator(); it.hasNext();) {
            Paciente p = (Paciente) it.next();

            document.add(new Paragraph(p.getEdat() + ' ' + p.getGrupEdat() + ' ' + p.getPes() + ' ' + p.getTalla() + ' '
                    + p.getImc() + ' ' + p.getClassificacio() + ' ' + p.getTipusMenopausia() + ' ' + p.getMenarquia() + ' ' + p.getMenopausia()));

        }

        document.close();

    }
}
