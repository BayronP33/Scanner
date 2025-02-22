/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.scanner;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class PreprocesadorImagen {
    
    static {
        try {
            // Obtener ruta del archivo DLL dentro de src/main/resources/native-lib
            String rutaDLL = System.getProperty("user.dir") + "/src/main/resources/native-lib/opencv_java4110.dll";
            System.load(rutaDLL);
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Error al cargar la librería OpenCV: " + e.getMessage());
        }
    }

    public BufferedImage procesarImagen(BufferedImage imagen) {
        // Convertir BufferedImage a Mat
        Mat imagenMat = bufferedImageToMat(imagen);

        // Convertir a escala de grises
        Imgproc.cvtColor(imagenMat, imagenMat, Imgproc.COLOR_BGR2GRAY);

        // Aplicar binarización (umbral adaptativo)
        Imgproc.adaptiveThreshold(imagenMat, imagenMat, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 11, 2);

        // Reducir ruido con desenfoque gaussiano
        Imgproc.GaussianBlur(imagenMat, imagenMat, new Size(3, 3), 0);

        // Convertir Mat a BufferedImage
        return matToBufferedImage(imagenMat);
    }

    private Mat bufferedImageToMat(BufferedImage imagen) {
        byte[] pixels = ((DataBufferByte) imagen.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(imagen.getHeight(), imagen.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, pixels);
        return mat;
    }

    private BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        BufferedImage imagen = new BufferedImage(mat.cols(), mat.rows(), type);
        byte[] data = new byte[mat.cols() * mat.rows() * (int) mat.elemSize()];
        mat.get(0, 0, data);
        imagen.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);
        return imagen;
    }
}
