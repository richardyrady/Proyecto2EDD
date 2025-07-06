/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_ry_gdg;

/**
 * Representa un nodo dentro del Árbol Binario de Búsqueda.
 * Debe ser pública para ser accesible desde otras clases en el mismo paquete.
 * @author Richard Yrady, Gianmarco Del Giudice
 */
public class NodoArbol {
    
    // Atributos privados para una correcta encapsulación
    private String patron;
    private int frecuencia;
    private DatosPatron datos;
    private NodoArbol izquierda;
    private NodoArbol derecha;

    /**
     * Constructor del NodoArbol.
     */
    public NodoArbol(String patron, DatosPatron datos) {
        this.patron = patron;
        this.datos = datos;
        this.frecuencia = datos.getFrecuencia();
        this.izquierda = null;
        this.derecha = null;
    }
}