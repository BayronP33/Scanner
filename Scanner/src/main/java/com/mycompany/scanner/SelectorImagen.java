/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.scanner;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SelectorImagen {
    private File imagenSeleccionada;
    private BufferedImage imagen;

    public boolean seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar imagen de cédula");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes (JPG, JPEG, PNG)", "jpg", "jpeg", "png"));
        
        int resultado = fileChooser.showOpenDialog(null);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            imagenSeleccionada = fileChooser.getSelectedFile();
            try {
                imagen = ImageIO.read(imagenSeleccionada);
                return true;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }
    
    public BufferedImage getImagen() {
        return imagen;
    }
    
    public File getArchivoImagen() {
        return imagenSeleccionada;
    }
}
