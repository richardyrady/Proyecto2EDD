/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_ry_gdg;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de un Árbol Binario de Búsqueda (ABB) para ordenar
 * los patrones de ADN según su frecuencia de aparición.
 * @author Richard Yrady, Gianmarco Del Giudice
 */
public class MiArbolBinario {

    private NodoArbol raiz;

    /**
     * Constructor que inicializa un árbol vacío.
     */
    public MiArbolBinario() {
        this.raiz = null;
    }

    /**
     * Método público para insertar un nuevo patrón en el árbol.
     * @param patron El patrón de ADN.
     * @param datos Los datos asociados al patrón (frecuencia y ubicaciones).
     */
    public void insertar(String patron, DatosPatron datos) {
        this.raiz = insertarRecursivo(this.raiz, patron, datos);
    }

    /**
     * Método recursivo privado para encontrar la posición correcta e insertar
     * el nuevo nodo. La comparación se basa en la frecuencia.
     * @param actual El nodo actual en el recorrido.
     * @param patron El patrón a insertar.
     * @param datos Los datos del patrón.
     * @return El nodo actualizado.
     */
    private NodoArbol insertarRecursivo(NodoArbol actual, String patron, DatosPatron datos) {
        if (actual == null) {
            return new NodoArbol(patron, datos);
        }

        // Si la frecuencia del nuevo patrón es menor, va a la izquierda.
        if (datos.getFrecuencia() < actual.frecuencia) {
            actual.izquierda = insertarRecursivo(actual.izquierda, patron, datos);
        } 
        // Si es mayor o igual, va a la derecha.
        else {
            actual.derecha = insertarRecursivo(actual.derecha, patron, datos);
        }

        return actual;
    }

    /**
     * Devuelve una lista de los patrones ordenados por frecuencia de mayor a menor.
     * @return Una lista de Strings con la información de cada patrón.
     */
    public List<String> getListaOrdenadaPorFrecuencia() {
        List<String> lista = new ArrayList<>();
        // Usamos un recorrido en orden inverso para obtener de mayor a menor.
        recorridoEnOrdenInverso(this.raiz, lista);
        return lista;
    }

    /**
     * Realiza un recorrido "Derecha-Raíz-Izquierda" para obtener los elementos
     * ordenados de forma descendente por frecuencia.
     * @param nodo El nodo actual del recorrido.
     * @param lista La lista donde se almacenarán los resultados.
     */
    private void recorridoEnOrdenInverso(NodoArbol nodo, List<String> lista) {
        if (nodo != null) {
            recorridoEnOrdenInverso(nodo.derecha, lista);
            lista.add("Patrón: " + nodo.patron + " -> " + nodo.datos.toString());
            recorridoEnOrdenInverso(nodo.izquierda, lista);
        }
    }

    /**
     * Busca el patrón con la frecuencia más baja (el nodo más a la izquierda).
     * @return El NodoArbol con la menor frecuencia, o null si el árbol está vacío.
     */
    public NodoArbol getPatronMenosFrecuente() {
        if (raiz == null) {
            return null;
        }
        NodoArbol actual = raiz;
        while (actual.izquierda != null) {
            actual = actual.izquierda;
        }
        return actual;
    }

    /**
     * Busca el patrón con la frecuencia más alta (el nodo más a la derecha).
     * @return El NodoArbol con la mayor frecuencia, o null si el árbol está vacío.
     */
    public NodoArbol getPatronMasFrecuente() {
        if (raiz == null) {
            return null;
        }
        NodoArbol actual = raiz;
        while (actual.derecha != null) {
            actual = actual.derecha;
        }
        return actual;
    }
}

/**
 * Representa un nodo dentro del Árbol Binario de Búsqueda.
 * El árbol se ordena por la frecuencia del patrón.
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