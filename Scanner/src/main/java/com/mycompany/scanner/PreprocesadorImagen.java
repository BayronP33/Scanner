/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.scanner;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class PreprocesadorImagen {

    static {
        try {
            String rutaDLL = System.getProperty("user.dir") + "/src/main/resources/native-lib/opencv_java4110.dll";
            System.load(rutaDLL);
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Error al cargar la librería OpenCV: " + e.getMessage());
        }
    }

    // Parámetros ajustables
    private static final double CLAHE_CLIP_LIMIT = 1.0;  //  2.0, 3.0, 4.0
    private static final Size CLAHE_TILE_GRID = new Size(0, 0); //  (8,8) o (16,16)

    private static final int BILATERAL_DIAMETER = 0;  //  5, 9, 12
    private static final double BILATERAL_SIGMA_COLOR = 0; //  50, 75, 100
    private static final double BILATERAL_SIGMA_SPACE = 0; //  50, 75, 100

    private static final int THRESHOLD_TYPE = Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU; 
    // Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU
    // Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU 
    // o Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C

    public BufferedImage procesarImagen(BufferedImage imagen) {
        long startTime = System.currentTimeMillis();

        Mat imagenMat = bufferedImageToMat(imagen);

        // Convertir a escala de grises
        if (imagenMat.channels() > 1) {
            Imgproc.cvtColor(imagenMat, imagenMat, Imgproc.COLOR_BGR2GRAY);
        }

        // Aplicar CLAHE
        Mat claheMat = new Mat();
        Imgproc.createCLAHE(CLAHE_CLIP_LIMIT, CLAHE_TILE_GRID).apply(imagenMat, claheMat);

        // Filtrado bilateral para reducción de ruido
        Mat filtradaMat = new Mat();
        Imgproc.bilateralFilter(claheMat, filtradaMat, BILATERAL_DIAMETER, BILATERAL_SIGMA_COLOR, BILATERAL_SIGMA_SPACE);

        // Aplicar umbralización
        Mat binarizadaMat = new Mat();
        Imgproc.threshold(filtradaMat, binarizadaMat, 0, 255, THRESHOLD_TYPE);

        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo de procesamiento: " + (endTime - startTime) + " ms");

        return matToBufferedImage(binarizadaMat);
    }

    private Mat bufferedImageToMat(BufferedImage imagen) {
        byte[] pixels = ((DataBufferByte) imagen.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(imagen.getHeight(), imagen.getWidth(), CvType.CV_8UC1);
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

