package proyecto2_ry_gdg;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Almacena los datos asociados a un patrón de ADN: su frecuencia y sus
 * ubicaciones.
 * @author Richard Yrady, Gianmarco Del Giudice
 */
public class DatosPatron {

    private int frecuencia;
    private final List<Integer> ubicaciones;

    /**
     * Constructor para inicializar los datos de un patrón.
     * @param primeraUbicacion La primera posición donde se encontró el patrón.
     */
    public DatosPatron(int primeraUbicacion) {
        this.frecuencia = 1;
        this.ubicaciones = new ArrayList<>();
        this.ubicaciones.add(primeraUbicacion);
    }

    /**
     * Incrementa la frecuencia del patrón y añade una nueva ubicación.
     * @param nuevaUbicacion La nueva posición donde se encontró el patrón.
     */
    public void registrarNuevaAparicion(int nuevaUbicacion) {
        this.frecuencia++;
        this.ubicaciones.add(nuevaUbicacion);
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public List<Integer> getUbicaciones() {
        return ubicaciones;
    }

    @Override
    public String toString() {
        return "Frecuencia: " + frecuencia + ", Ubicaciones: " + ubicaciones.toString();
    }
}