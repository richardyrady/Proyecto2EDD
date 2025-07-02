/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_ry_gdg;


/**
 * Representa un nodo dentro del Árbol Binario de Búsqueda.
 *
 * El árbol se ordena por la frecuencia del patrón.
 * @author Hp Victus
*/

class NodoArbol {
    String patron;
    int frecuencia;
    DatosPatron datos; // Referencia a los datos completos (incluye ubicaciones)
    NodoArbol izquierda;
    NodoArbol derecha;

    /**
     * Constructor del NodoArbol.
     * @param patron La secuencia de 3 letras de ADN.
     * @param datos Los datos asociados al patrón (frecuencia y ubicaciones).
     */
    public NodoArbol(String patron, DatosPatron datos) {
        this.patron = patron;
        this.datos = datos;
        this.frecuencia = datos.getFrecuencia();
        this.izquierda = null;
        this.derecha = null;
    }
}