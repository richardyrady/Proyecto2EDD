/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_ry_gdg;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de una Tabla de Dispersión (Hash Table) con manejo de
 * colisiones por encadenamiento para almacenar patrones de ADN.
 * @author Richard Yrady, Gianmarco Del Giudice
 */
public class MiHashTable {

    private NodoHash[] tabla;
    private final int tamano;

    public MiHashTable(int tamano) {
        this.tamano = tamano;
        this.tabla = new NodoHash[tamano];
    }

    private int valorDeChar(char c) {
        switch (c) {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
            default: return -1;
        }
    }

    private int getHash(String patron) {
        if (patron == null || patron.length() != 3) {
            return 0;
        }
        int hash = 0;
        hash += valorDeChar(patron.charAt(0)) * 16;
        hash += valorDeChar(patron.charAt(1)) * 4;
        hash += valorDeChar(patron.charAt(2));
        return hash % tamano;
    }

    public void insertar(String patron, int posicion) {
        int indice = getHash(patron);
        NodoHash actual = tabla[indice];

        while (actual != null) {
            if (actual.patron.equals(patron)) {
                actual.datos.registrarNuevaAparicion(posicion);
                return;
            }
            actual = actual.siguiente;
        }

        NodoHash nuevoNodo = new NodoHash(patron, posicion);
        nuevoNodo.siguiente = tabla[indice];
        tabla[indice] = nuevoNodo;
    }

    public DatosPatron buscar(String patron) {
        int indice = getHash(patron);
        NodoHash actual = tabla[indice];

        while (actual != null) {
            if (actual.patron.equals(patron)) {
                return actual.datos;
            }
            actual = actual.siguiente;
        }
        return null;
    }
    
    public List<String> getTodosLosPatrones() {
        List<String> patrones = new ArrayList<>();
        for (int i = 0; i < tamano; i++) {
            NodoHash actual = tabla[i];
            while (actual != null) {
                patrones.add(actual.patron);
                actual = actual.siguiente;
            }
        }
        return patrones;
    }

    public String generarReporteColisiones() {
        StringBuilder reporte = new StringBuilder("--- Reporte de Colisiones ---\n");
        boolean hayColisiones = false;
        
        for (int i = 0; i < tamano; i++) {
            NodoHash actual = tabla[i];
            if (actual != null && actual.siguiente != null) {
                hayColisiones = true;
                reporte.append("Índice [").append(i).append("]: Colisionaron los patrones -> ");
                while (actual != null) {
                    reporte.append("'").append(actual.patron).append("' ");
                    actual = actual.siguiente;
                }
                reporte.append("\n");
            }
        }

        if (!hayColisiones) {
            reporte.append("No se encontraron colisiones en la tabla.\n");
        }
        
        return reporte.toString();
    }
    
    public void limpiar() {
        this.tabla = new NodoHash[this.tamano];
    }
    
    /**
     * Devuelve una lista con todas las entradas (patrón y sus datos) de la tabla.
     * @return Una lista de objetos EntradaHash.
     */
    public List<EntradaHash> getTodasLasEntradas() {
        List<EntradaHash> entradas = new ArrayList<>();
        for (int i = 0; i < tamano; i++) {
            NodoHash actual = tabla[i];
            while (actual != null) {
                entradas.add(new EntradaHash(actual.patron, actual.datos));
                actual = actual.siguiente;
            }
        }
        return entradas;
    }
}

/**
 * Representa un nodo dentro de la tabla hash.
 */
class NodoHash {
    String patron;
    DatosPatron datos;
    NodoHash siguiente;

    public NodoHash(String patron, int posicion) {
        this.patron = patron;
        this.datos = new DatosPatron(posicion);
        this.siguiente = null;
    }
}

/**
 * Clase auxiliar para encapsular una entrada (clave-valor) de la tabla hash.
 */
class EntradaHash {
    String patron;
    DatosPatron datos;

    public EntradaHash(String patron, DatosPatron datos) {
        this.patron = patron;
        this.datos = datos;
    }
}