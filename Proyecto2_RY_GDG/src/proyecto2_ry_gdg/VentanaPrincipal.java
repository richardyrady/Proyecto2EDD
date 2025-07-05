/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_ry_gdg;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Ventana principal de la aplicación de Bioinformática.
 * @author Richard Yrady, Gianmarco Del Giudice
 */
public class VentanaPrincipal extends JFrame {

    // --- Atributos de Estructuras de Datos ---
    private final MiHashTable tablaHash;
    private final AnalizadorADN analizador;

    // --- Atributos de Componentes GUI ---
    private JButton btnCargarArchivo;
    private JTextArea txtResultados;
    
    // --- Atributos para Fase 3 (Integrante 1) ---
    private JButton btnBuscarPatron;
    private JButton btnReporteColisiones;
    private JComboBox<String> cmbPatrones;

    // --- ATRIBUTOS AÑADIDOS PARA FASE 3 (Integrante 2) ---
    private JButton btnListarPorFrecuencia;
    private JButton btnMasFrecuente;
    private JButton btnMenosFrecuente;

    public VentanaPrincipal() {
        this.tablaHash = new MiHashTable(97); 
        this.analizador = new AnalizadorADN(this.tablaHash);

        setTitle("Proyecto 2: Bioinformática");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();
        initListeners();
    }

    private void initComponents() {
        btnCargarArchivo = new JButton("Cargar Archivo de ADN (.txt)");
        txtResultados = new JTextArea("Bienvenido. Por favor, cargue un archivo para comenzar el análisis.");
        txtResultados.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(txtResultados);

        add(btnCargarArchivo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // --- Panel de Controles ---
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        cmbPatrones = new JComboBox<>();
        cmbPatrones.setEnabled(false);

        btnBuscarPatron = new JButton("Buscar Patrón");
        btnBuscarPatron.setEnabled(false);

        btnReporteColisiones = new JButton("Reporte de Colisiones");
        btnReporteColisiones.setEnabled(false);
        
        // --- BOTONES NUEVOS AÑADIDOS AQUÍ ---
        btnListarPorFrecuencia = new JButton("Listar por Frecuencia");
        btnListarPorFrecuencia.setEnabled(false);

        btnMasFrecuente = new JButton("Más Frecuente");
        btnMasFrecuente.setEnabled(false);

        btnMenosFrecuente = new JButton("Menos Frecuente");
        btnMenosFrecuente.setEnabled(false);

        panelControles.add(new JLabel("Patrón:"));
        panelControles.add(cmbPatrones);
        panelControles.add(btnBuscarPatron);
        panelControles.add(btnReporteColisiones);
        panelControles.add(btnListarPorFrecuencia);
        panelControles.add(btnMasFrecuente);
        panelControles.add(btnMenosFrecuente);

        add(panelControles, BorderLayout.SOUTH);
    }

    private void initListeners() {
        btnCargarArchivo.addActionListener(e -> cargarArchivo());
        btnBuscarPatron.addActionListener(e -> buscarPatronSeleccionado());
        btnReporteColisiones.addActionListener(e -> mostrarReporteColisiones());
        
        // --- LISTENERS NUEVOS AÑADIDOS AQUÍ ---
        btnListarPorFrecuencia.addActionListener(e -> listarPorFrecuencia());
        btnMasFrecuente.addActionListener(e -> mostrarMasFrecuente());
        btnMenosFrecuente.addActionListener(e -> mostrarMenosFrecuente());
    }

    private void cargarArchivo() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccione el archivo de secuencia de ADN");
        
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Texto (*.txt)", "txt");
        selector.setFileFilter(filtro);

        int resultado = selector.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            String rutaArchivo = selector.getSelectedFile().getAbsolutePath();
            txtResultados.setText("Procesando archivo: " + rutaArchivo + "\n");
            
            try {
                tablaHash.limpiar(); 
                analizador.procesarYcargar(rutaArchivo);
                txtResultados.append("¡Archivo procesado exitosamente!\n");
                
                poblarComboBoxPatrones();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al procesar el archivo:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void poblarComboBoxPatrones() {
        cmbPatrones.removeAllItems();
        List<String> patrones = tablaHash.getTodosLosPatrones();
        
        Collections.sort(patrones); 
        
        for (String patron : patrones) {
            cmbPatrones.addItem(patron);
        }
        
        // Habilitar todos los botones
        cmbPatrones.setEnabled(true);
        btnBuscarPatron.setEnabled(true);
        btnReporteColisiones.setEnabled(true);
        btnListarPorFrecuencia.setEnabled(true);
        btnMasFrecuente.setEnabled(true);
        btnMenosFrecuente.setEnabled(true);
    }
    
    private void buscarPatronSeleccionado() {
        String patronSeleccionado = (String) cmbPatrones.getSelectedItem();
        if (patronSeleccionado != null) {
            DatosPatron datos = tablaHash.buscar(patronSeleccionado);
            if (datos != null) {
                txtResultados.setText("--- Información del Patrón: " + patronSeleccionado + " ---\n");
                txtResultados.append("Frecuencia: " + datos.getFrecuencia() + "\n");
                txtResultados.append("Ubicaciones: " + datos.getUbicaciones().toString() + "\n");
            } else {
                txtResultados.setText("No se encontró información para el patrón: " + patronSeleccionado);
            }
        }
    }

    private void mostrarReporteColisiones() {
        String reporte = tablaHash.generarReporteColisiones();
        txtResultados.setText(reporte);
    }
    
    // --- MÉTODOS NUEVOS AÑADIDOS AQUÍ ---
    private MiArbolBinario construirArbolDeFrecuencias() {
        MiArbolBinario arbol = new MiArbolBinario();
        List<EntradaHash> entradas = tablaHash.getTodasLasEntradas();
        for (EntradaHash entrada : entradas) {
            arbol.insertar(entrada.patron, entrada.datos);
        }
        return arbol;
    }

    private void listarPorFrecuencia() {
        MiArbolBinario arbol = construirArbolDeFrecuencias();
        List<String> listaOrdenada = arbol.getListaOrdenadaPorFrecuencia();
        
        StringBuilder resultado = new StringBuilder("--- Lista de Patrones por Frecuencia (Mayor a Menor) ---\n");
        for (String item : listaOrdenada) {
            resultado.append(item).append("\n");
        }
        txtResultados.setText(resultado.toString());
    }
    
    private void mostrarMasFrecuente() {
        MiArbolBinario arbol = construirArbolDeFrecuencias();
        NodoArbol nodo = arbol.getPatronMasFrecuente();
        if (nodo != null) {
            String resultado = "--- Patrón Más Frecuente ---\n" +
                               "Patrón: " + nodo.patron + "\n" +
                               "Frecuencia: " + nodo.frecuencia + "\n" +
                               "Ubicaciones: " + nodo.datos.getUbicaciones().toString();
            txtResultados.setText(resultado);
        } else {
            txtResultados.setText("No hay datos para analizar.");
        }
    }

    private void mostrarMenosFrecuente() {
        MiArbolBinario arbol = construirArbolDeFrecuencias();
        NodoArbol nodo = arbol.getPatronMenosFrecuente();
        if (nodo != null) {
            String resultado = "--- Patrón Menos Frecuente ---\n" +
                               "Patrón: " + nodo.patron + "\n" +
                               "Frecuencia: " + nodo.frecuencia + "\n" +
                               "Ubicaciones: " + nodo.datos.getUbicaciones().toString();
            txtResultados.setText(resultado);
        } else {
            txtResultados.setText("No hay datos para analizar.");
        }
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}