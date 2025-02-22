/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.scanner;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.awt.image.BufferedImage;

public class OCRExtractor {

    public static String extraerTexto(BufferedImage imagen) {
        // 1. Instancia de Tesseract
        Tesseract tesseract = new Tesseract();

        // 2. Ruta de los datos de Tesseract
        tesseract.setDatapath("C:\\Users\\Familia Pedraza\\Documents\\NetBeansProjects\\Scanner\\src\\main\\resources\\tessdata");

        tesseract.setLanguage("spa");  // Español

        try {
            // 3. Procesa la imagen y extraer texto
            return tesseract.doOCR(imagen);
        } catch (TesseractException e) {
            return "Error al leer la imagen: " + e.getMessage();
        }
    }
}
