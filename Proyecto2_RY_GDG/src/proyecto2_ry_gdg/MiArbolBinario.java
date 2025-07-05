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

    public MiArbolBinario() {
        this.raiz = null;
    }

    public void insertar(String patron, DatosPatron datos) {
        this.raiz = insertarRecursivo(this.raiz, patron, datos);
    }

    private NodoArbol insertarRecursivo(NodoArbol actual, String patron, DatosPatron datos) {
        if (actual == null) {
            return new NodoArbol(patron, datos);
        }

        if (datos.getFrecuencia() < actual.frecuencia) {
            actual.izquierda = insertarRecursivo(actual.izquierda, patron, datos);
        } 
        else {
            actual.derecha = insertarRecursivo(actual.derecha, patron, datos);
        }

        return actual;
    }

    public List<String> getListaOrdenadaPorFrecuencia() {
        List<String> lista = new ArrayList<>();
        recorridoEnOrdenInverso(this.raiz, lista);
        return lista;
    }

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
 */
class NodoArbol {
    String patron;
    int frecuencia;
    DatosPatron datos; 
    NodoArbol izquierda;
    NodoArbol derecha;

    public NodoArbol(String patron, DatosPatron datos) {
        this.patron = patron;
        this.datos = datos;
        this.frecuencia = datos.getFrecuencia();
        this.izquierda = null;
        this.derecha = null;
    }
}