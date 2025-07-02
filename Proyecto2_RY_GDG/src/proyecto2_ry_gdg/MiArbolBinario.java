/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_ry_gdg;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Hp Victus
 * Implementación de un Árbol Binario de Búsqueda (ABB) para ordenar
 * los patrones de ADN según su frecuencia de aparición.
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
}