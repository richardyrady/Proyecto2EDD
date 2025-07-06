/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_ry_gdg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Clase encargada de mapear los patrones de ADN a aminoácidos y generar
 * los reportes correspondientes.
 * @author Richard Yrady, Gianmarco Del Giudice
 */
public class MapeoAminoacidos {

    private final Map<String, String> mapaCodones;

    /**
     * Constructor que inicializa el mapa de codones.
     */
    public MapeoAminoacidos() {
        this.mapaCodones = new HashMap<>();
        poblarMapaCodones();
    }

    /**
     * Genera un reporte que agrupa los patrones de la tabla hash por el
     * aminoácido que sintetizan.
     * @param tablaHash La tabla hash con los patrones de ADN y sus datos.
     * @return Un String formateado con el reporte.
     */
    public String generarReporte(MiHashTable tablaHash) {
        // Usamos un TreeMap para que el reporte esté ordenado alfabéticamente por aminoácido.
        Map<String, List<String>> reporteAgrupado = new TreeMap<>();
        
        List<EntradaHash> todasLasEntradas = tablaHash.getTodasLasEntradas();

        for (EntradaHash entrada : todasLasEntradas) {
            String patronADN = entrada.patron;
            // Se reemplaza la Timina (T) por Uracilo (U) para obtener el codón de ARN [cite: 22]
            String codonARN = patronADN.replace('T', 'U'); 
            
            // Se busca el aminoácido correspondiente en el mapa.
            // Si no se encuentra, se clasifica como "Inválido".
            String aminoacido = mapaCodones.getOrDefault(codonARN, "Inválido/Desconocido");
            
            // Preparamos la línea del reporte para este patrón.
            String infoPatron = "   - Patrón: " + patronADN + " (Frecuencia: " + entrada.datos.getFrecuencia() + ")";

            // Agregamos la información al reporte agrupado.
            reporteAgrupado.computeIfAbsent(aminoacido, k -> new ArrayList<>()).add(infoPatron);
        }

        // Finalmente, construimos el String del reporte final.
        StringBuilder reporteFinal = new StringBuilder("--- Reporte por Aminoácidos ---\n\n");
        for (Map.Entry<String, List<String>> grupo : reporteAgrupado.entrySet()) {
            reporteFinal.append("Aminoácido: ").append(grupo.getKey()).append("\n");
            for (String linea : grupo.getValue()) {
                reporteFinal.append(linea).append("\n");
            }
            reporteFinal.append("\n");
        }
        
        return reporteFinal.toString();
    }

    /**
     * Rellena el mapa con las correspondencias entre codones de ARN y aminoácidos,
     * basado en la tabla del proyecto. [cite: 24, 26, 27, 28]
     */
    private void poblarMapaCodones() {
        // Fenilalanina
        mapaCodones.put("UUU", "Fenilalanina");
        mapaCodones.put("UUC", "Fenilalanina");
        // Leucina
        mapaCodones.put("UUA", "Leucina");
        mapaCodones.put("UUG", "Leucina");
        mapaCodones.put("CUU", "Leucina");
        mapaCodones.put("CUC", "Leucina");
        mapaCodones.put("CUA", "Leucina");
        mapaCodones.put("CUG", "Leucina");
        // Serina
        mapaCodones.put("UCU", "Serina");
        mapaCodones.put("UCC", "Serina");
        mapaCodones.put("UCA", "Serina");
        mapaCodones.put("UCG", "Serina");
        mapaCodones.put("AGU", "Serina");
        mapaCodones.put("AGC", "Serina");
        // Tirosina
        mapaCodones.put("UAU", "Tirosina");
        mapaCodones.put("UAC", "Tirosina");
        // Cisteína
        mapaCodones.put("UGU", "Cisteína");
        mapaCodones.put("UGC", "Cisteína");
        // Triptófano
        mapaCodones.put("UGG", "Triptófano");
        // Prolina
        mapaCodones.put("CCU", "Prolina");
        mapaCodones.put("CCC", "Prolina");
        mapaCodones.put("CCA", "Prolina");
        mapaCodones.put("CCG", "Prolina");
        // Histidina
        mapaCodones.put("CAU", "Histidina");
        mapaCodones.put("CAC", "Histidina");
        // Glutamina
        mapaCodones.put("CAA", "Glutamina");
        mapaCodones.put("CAG", "Glutamina");
        // Arginina
        mapaCodones.put("CGU", "Arginina");
        mapaCodones.put("CGC", "Arginina");
        mapaCodones.put("CGA", "Arginina");
        mapaCodones.put("CGG", "Arginina");
        mapaCodones.put("AGA", "Arginina");
        mapaCodones.put("AGG", "Arginina");
        // Isoleucina
        mapaCodones.put("AUU", "Isoleucina");
        mapaCodones.put("AUC", "Isoleucina");
        mapaCodones.put("AUA", "Isoleucina");
        // Treonina
        mapaCodones.put("ACU", "Treonina");
        mapaCodones.put("ACC", "Treonina");
        mapaCodones.put("ACA", "Treonina");
        mapaCodones.put("ACG", "Treonina");
        // Asparagina
        mapaCodones.put("AAU", "Asparagina");
        mapaCodones.put("AAC", "Asparagina");
        // Lisina
        mapaCodones.put("AAA", "Lisina");
        mapaCodones.put("AAG", "Lisina");
        // Valina
        mapaCodones.put("GUU", "Valina");
        mapaCodones.put("GUC", "Valina");
        mapaCodones.put("GUA", "Valina");
        mapaCodones.put("GUG", "Valina");
        // Alanina
        mapaCodones.put("GCU", "Alanina");
        mapaCodones.put("GCC", "Alanina");
        mapaCodones.put("GCA", "Alanina");
        mapaCodones.put("GCG", "Alanina");
        // Ácido Aspártico
        mapaCodones.put("GAU", "Ácido Aspártico");
        mapaCodones.put("GAC", "Ácido Aspártico");
        // Ácido Glutámico
        mapaCodones.put("GAA", "Ácido Glutámico");
        mapaCodones.put("GAG", "Ácido Glutámico");
        // Glicina
        mapaCodones.put("GGU", "Glicina");
        mapaCodones.put("GGC", "Glicina");
        mapaCodones.put("GGA", "Glicina");
        mapaCodones.put("GGG", "Glicina");
        // Codón de INICIO
        mapaCodones.put("AUG", "Metionina (Inicio)");
        // Codones de PARADA
        mapaCodones.put("UAA", "STOP");
        mapaCodones.put("UAG", "STOP");
        mapaCodones.put("UGA", "STOP");
    }
}
