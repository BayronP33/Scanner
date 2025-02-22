/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.scanner;

import java.awt.image.BufferedImage;

public class Scanner {
    public static void main(String[] args) {
        SelectorImagen selector = new SelectorImagen();
        
        if (selector.seleccionarImagen()) { 
            BufferedImage imagenOriginal = selector.getImagen();
            
            //  Preprocesa la imagen
            PreprocesadorImagen preprocesador = new PreprocesadorImagen();
            BufferedImage imagenProcesada = preprocesador.procesarImagen(imagenOriginal);

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




