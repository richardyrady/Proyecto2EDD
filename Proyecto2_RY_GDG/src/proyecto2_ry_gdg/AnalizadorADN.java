/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_ry_gdg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase encargada de la lógica de negocio para procesar secuencias de ADN.
 * Lee un archivo, extrae la secuencia, la divide en patrones y los
 * carga en una tabla hash.
 */
public class AnalizadorADN {

    private final MiHashTable tablaHash;

    /**
     * Constructor que recibe la tabla hash que se va a utilizar.
     * @param tablaHash La instancia de MiHashTable donde se guardarán los datos.
     */
    public AnalizadorADN(MiHashTable tablaHash) {
        this.tablaHash = tablaHash;
    }

    /**
     * Lee un archivo de texto, procesa la secuencia de ADN y la carga en la tabla hash.
     * @param rutaArchivo La ruta completa del archivo a procesar.
     */
    public void procesarYcargar(String rutaArchivo) {
        // Usamos StringBuilder por eficiencia al concatenar strings.
        StringBuilder secuenciaBuilder = new StringBuilder();

        // Try-with-resources para asegurar que el lector se cierre automáticamente.
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Limpiamos la línea de espacios y la convertimos a mayúsculas
                // para un procesamiento uniforme.
                secuenciaBuilder.append(linea.trim().toUpperCase());
            }
        } catch (IOException e) {
            // En una aplicación real, mostraríamos esto en la GUI.
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return; // Detenemos la ejecución si hay un error de lectura.
        }

        String secuenciaPrincipal = secuenciaBuilder.toString();

        // Requerimiento: Los patrones a buscar deben tener una longitud de 3. [cite: 9]
        // Se procesa la secuencia principal de 3 en 3 caracteres. [cite: 10]
        for (int i = 0; i <= secuenciaPrincipal.length() - 3; i += 3) {
            String patron = secuenciaPrincipal.substring(i, i + 3);

            // Verificamos que el patrón solo contenga caracteres válidos.
            if (patron.matches("[ACGT]+")) {
                tablaHash.insertar(patron, i);
            }
        }
        
        System.out.println("Procesamiento completado. La tabla hash ha sido poblada.");
    }
}

