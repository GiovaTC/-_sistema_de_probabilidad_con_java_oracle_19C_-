import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    private JTextField txtNombre, txtFrecuencia;
    private JTable tabla;
    private EventoDAO dao = new EventoDAO();

    public MainFrame() {
        setTitle("gestion de probabilidad");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lbl1 = new JLabel("Evento:");
        lbl1.setBounds(20, 20, 100, 25);
        add(lbl1);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 20, 150, 25);
        add(txtNombre);

        JLabel lbl2 = new JLabel("Frecuencia:");
        lbl2.setBounds(20, 60, 100, 25);
        add(lbl2);

        txtFrecuencia = new JTextField();
        txtFrecuencia.setBounds(100, 60, 150, 25);
        add(txtFrecuencia);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(300, 20, 120, 30);
        add(btnGuardar);

        JButton btnCalcular = new JButton("Calcular Probabilidad");
        btnCalcular.setBounds(300, 60, 200, 30);
        add(btnCalcular);

        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(20, 120, 540, 200);
        add(scroll);

        tabla = new JTable();
        scroll.setViewportView(tabla);

        btnGuardar.addActionListener((ActionEvent e) -> {
            dao.insertar(txtNombre.getText(), Integer.parseInt(txtFrecuencia.getText()));
            cargarTabla();
        });

        btnCalcular.addActionListener((ActionEvent e) -> {
            dao.calcularProbabilidad();
            cargarTabla();
        });
    }

    private void cargarTabla() {
        String[] columnas = {"ID", "Evento", "Frecuencia", "Probabilidad"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        for (String[] fila : dao.listar()) {
            model.addRow(fila);
        }

        tabla.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
