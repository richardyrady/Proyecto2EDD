/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_ry_gdg;

/**
 * Implementación de una Tabla de Dispersión (Hash Table) con manejo de
 * colisiones por encadenamiento para almacenar patrones de ADN.
 */
public class MiHashTable {

    private final NodoHash[] tabla;
    private final int tamano;

    /**
     * Constructor que inicializa la tabla hash con un tamaño específico.
     * Un tamaño primo es generalmente una buena opción para reducir colisiones.
     * @param tamano El tamaño del arreglo subyacente.
     */
    public MiHashTable(int tamano) {
        this.tamano = tamano;
        this.tabla = new NodoHash[tamano];
    }

    /**
     * Convierte un carácter de nucleótido a un valor numérico.
     * @param c El carácter (A, C, G, T).
     * @return Un valor entero de 0 a 3.
     */
    private int valorDeChar(char c) {
        switch (c) {
            case 'A': return 0;
            case 'C': return 1;
            case 'G': return 2;
            case 'T': return 3;
            default: return -1; // Manejo de error para caracteres inválidos
        }
    }

    /**
     * Calcula el índice de hash para un patrón de ADN de 3 letras.
     * Trata el patrón como un número en base 4 para generar una clave única.
     * @param patron La secuencia de 3 letras.
     * @return El índice calculado para la tabla.
     */
    private int getHash(String patron) {
        if (patron == null || patron.length() != 3) {
            return 0; // O manejar como un error
        }
        int hash = 0;
        hash += valorDeChar(patron.charAt(0)) * 16; // 4^2
        hash += valorDeChar(patron.charAt(1)) * 4;  // 4^1
        hash += valorDeChar(patron.charAt(2));      // 4^0
        return hash % tamano;
    }

    /**
     * Inserta un patrón en la tabla hash. Si el patrón ya existe, actualiza
     * su frecuencia y añade la nueva ubicación. Si no existe, lo crea.
     * @param patron El patrón de ADN a insertar.
     * @param posicion La ubicación del patrón en la secuencia principal.
     */
    public void insertar(String patron, int posicion) {
        int indice = getHash(patron);
        NodoHash actual = tabla[indice];

        // Recorrer la lista enlazada en este índice
        while (actual != null) {
            if (actual.patron.equals(patron)) {
                // El patrón ya existe, solo actualizamos sus datos
                actual.datos.registrarNuevaAparicion(posicion);
                return;
            }
            actual = actual.siguiente;
        }

        // Si el patrón es nuevo, lo insertamos al inicio de la lista
        NodoHash nuevoNodo = new NodoHash(patron, posicion);
        nuevoNodo.siguiente = tabla[indice];
        tabla[indice] = nuevoNodo;
    }

    /**
     * Busca un patrón en la tabla hash y devuelve sus datos.
     * @param patron El patrón a buscar.
     * @return Los DatosPatron asociados o null si no se encuentra.
     */
    public DatosPatron buscar(String patron) {
        int indice = getHash(patron);
        NodoHash actual = tabla[indice];

        while (actual != null) {
            if (actual.patron.equals(patron)) {
                return actual.datos;
            }
            actual = actual.siguiente;
        }
        return null; // No se encontró el patrón
    }
}
