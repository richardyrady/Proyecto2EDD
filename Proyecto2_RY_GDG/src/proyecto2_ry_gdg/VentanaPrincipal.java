/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_ry_gdg;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Ventana principal de la aplicación de Bioinformática.
 * Proporciona la interfaz gráfica para interactuar con el sistema.
 * @author Hp Victus
 */
public class VentanaPrincipal extends JFrame {

    private final MiHashTable tablaHash;
    private final AnalizadorADN analizador;

    private JButton btnCargarArchivo;
    private JTextArea txtResultados;

    /**
     * Constructor de la ventana principal.
     */
    public VentanaPrincipal() {
        // 1. Inicializar las estructuras de datos y la lógica de negocio
        // Se recomienda un tamaño primo para la tabla hash. 64 es 4^3, 
        // por lo que los patrones de 3 letras tienen claves únicas de 0 a 63.
        // Un tamaño como 97 (primo) ayuda a dispersar otras posibles claves.
        this.tablaHash = new MiHashTable(97); 
        this.analizador = new AnalizadorADN(this.tablaHash);

        // 2. Configuración de la ventana (JFrame)
        setTitle("Proyecto 2: Bioinformática");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 3. Crear e inicializar los componentes de la GUI
        initComponents();
        
        // 4. Añadir los listeners de eventos
        initListeners();
    }

    /**
     * Inicializa y organiza los componentes visuales en la ventana.
     */
    private void initComponents() {
        btnCargarArchivo = new JButton("Cargar Archivo de ADN (.txt)");
        txtResultados = new JTextArea("Bienvenido. Por favor, cargue un archivo para comenzar el análisis.");
        txtResultados.setEditable(false);
        
        // Se añade un JScrollPane para que el área de texto tenga barras de desplazamiento
        JScrollPane scrollPane = new JScrollPane(txtResultados);

        // Añadir componentes a la ventana
        add(btnCargarArchivo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Configura los manejadores de eventos para los componentes interactivos.
     */
    private void initListeners() {
        btnCargarArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarArchivo();
            }
        });
    }

    /**
     * Abre un JFileChooser para que el usuario seleccione un archivo .txt,
     * y luego invoca al analizador para procesarlo.
     */
    private void cargarArchivo() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccione el archivo de secuencia de ADN");
        
        // Filtrar para que solo se muestren archivos .txt 
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Texto (*.txt)", "txt");
        selector.setFileFilter(filtro);

        int resultado = selector.showOpenDialog(this);

        // Si el usuario selecciona un archivo y presiona "Abrir"
        if (resultado == JFileChooser.APPROVE_OPTION) {
            String rutaArchivo = selector.getSelectedFile().getAbsolutePath();
            txtResultados.setText("Procesando archivo: " + rutaArchivo + "\n");
            
            try {
                // Llamar a la lógica de negocio para procesar el archivo
                analizador.procesarYcargar(rutaArchivo);
                txtResultados.append("¡Archivo procesado exitosamente!\n");
                txtResultados.append("La tabla de hash ha sido poblada con los patrones de ADN.\n");
                 // Aquí se podrían habilitar otros botones (Listar, Buscar, etc.)
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al procesar el archivo:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Método principal para iniciar la aplicación.
     * @param args Argumentos de la línea de comandos (no se usan).
     */
    public static void main(String[] args) {
        // Se asegura que la GUI se cree en el hilo de despacho de eventos (EDT)
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
}
