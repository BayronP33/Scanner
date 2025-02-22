/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorTexto {

    /**
     * Extrae el NUIP eliminando los puntos y dejando solo los dígitos.
     */
    public static String extraerNUIP(String texto) {
        Pattern patron = Pattern.compile("NUIP[\\s:]*([0-9.]+)");
        Matcher matcher = patron.matcher(texto);

        if (matcher.find()) {
            return matcher.group(1).replace(".", "");  // Elimina los puntos
        }
        return "NUIP no encontrado";
    }

    /**
     * Extrae la fecha de nacimiento en formato DDMMM AAAA (ejemplo: 15FEB 1990).
     */
    public static String extraerFechaNacimiento(String texto) {
        Pattern patron = Pattern.compile("\\b(\\d{2}[A-Z]{3}\\s\\d{4})\\b");
        Matcher matcher = patron.matcher(texto);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return "Fecha de nacimiento no encontrada";
    }

    /**
     * Extrae el nombre desde el formato MRZ, eliminando los caracteres "<".
     */
    public static String extraerNombreMRZ(String texto) {
        Pattern patron = Pattern.compile("[A-Z<]+\\s[A-Z<]+");  // Detecta líneas con "<"
        Matcher matcher = patron.matcher(texto);

        if (matcher.find()) {
            String nombreMRZ = matcher.group(0);
            return nombreMRZ.replace("<", " ").trim();  // Reemplaza "<" por espacios
        }
        return "Nombre no encontrado";
    }

    /**
     * Método para analizar el texto y extraer la información relevante.
     */
    public static void analizarTexto(String texto) {
        System.out.println("NUIP: " + extraerNUIP(texto));
        System.out.println("Fecha de Nacimiento: " + extraerFechaNacimiento(texto));
        System.out.println("Nombre Completo: " + extraerNombreMRZ(texto));
    }
}

