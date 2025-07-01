/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_ry_gdg;

/**
 * Representa un nodo dentro de la tabla hash. Contiene el patrón,
 * sus datos asociados y un enlace al siguiente nodo en caso de colisión.
 */
class NodoHash {
    String patron;
    DatosPatron datos;
    NodoHash siguiente;

    /**
     * Constructor del NodoHash.
     * @param patron La secuencia de 3 letras de ADN.
     * @param posicion La ubicación inicial del patrón en la secuencia principal.
     */
    public NodoHash(String patron, int posicion) {
        this.patron = patron;
        this.datos = new DatosPatron(posicion);
        this.siguiente = null;
    }
}
