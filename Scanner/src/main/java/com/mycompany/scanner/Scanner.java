/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.scanner;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Scanner {
    public static void main(String[] args) {
        SelectorImagen selector = new SelectorImagen();
        
        if (selector.seleccionarImagen()) { 
            BufferedImage imagenOriginal = selector.getImagen();
            
            // Preprocesar la imagen
            PreprocesadorImagen preprocesador = new PreprocesadorImagen();
            BufferedImage imagenProcesada = preprocesador.procesarImagen(imagenOriginal);

            // Guardar la imagen preprocesada para depuración
            try {
                ImageIO.write(imagenProcesada, "png", new File("imagen_procesada.png"));
                System.out.println("Imagen preprocesada guardada como 'imagen_procesada.png'");
            } catch (Exception e) {
                System.err.println("Error al guardar la imagen preprocesada: " + e.getMessage());
            }

            // Extraer texto con OCR
            String textoExtraido = OCRExtractor.extraerTexto(imagenProcesada);

            // Mostrar texto extraído en la consola
            System.out.println("Texto extraído:\n" + textoExtraido);

            // Analizar el texto y extraer los atributos clave
            AnalizadorTexto.analizarTexto(textoExtraido);
        } else {
            System.out.println("No se seleccionó ninguna imagen.");
        }
    }
}





