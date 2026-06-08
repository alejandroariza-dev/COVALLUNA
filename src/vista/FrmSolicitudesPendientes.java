package vista;

import dao.SolicitudActualizacionDAO;
import modelo.SolicitudActualizacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FrmSolicitudesPendientes extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private SolicitudActualizacionDAO solicitudDAO;

    public FrmSolicitudesPendientes() {

        solicitudDAO =
                new SolicitudActualizacionDAO();

        setTitle(
                "Solicitudes de Actualización"
        );

        setSize(1110, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(null);

        JLabel lblLogo =
                new JLabel("COOVALLUNA");

        lblLogo.setFont(
                new Font("Segoe UI", Font.BOLD, 24)
        );

        lblLogo.setBounds(30, 30, 250, 40);

        add(lblLogo);

        JSeparator sep = new JSeparator();

        sep.setBounds(30, 80, 1040, 2);

        add(sep);

        JLabel lblTitulo =
                new JLabel(
                        "SOLICITUDES DE ACTUALIZACIÓN"
                );

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 22)
        );

        lblTitulo.setBounds(30, 100, 500, 40);

        add(lblTitulo);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Cédula Asociado");
        modelo.addColumn("Nuevo Teléfono");
        modelo.addColumn("Nuevo Correo");
        modelo.addColumn("Nueva Dirección");
        modelo.addColumn("Fecha Solicitud");

        tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(30, 200, 760, 450);

        add(scroll);

        JButton btnAprobar =
                new JButton("Aprobar");

        btnAprobar.setBounds(
                800, 433, 170, 35
        );

        add(btnAprobar);

        JButton btnRechazar =
                new JButton("Rechazar");

        btnRechazar.setBounds(
                800, 478, 170, 35
        );

        add(btnRechazar);

        JButton btnActualizar =
                new JButton("Actualizar Tabla");

        btnActualizar.setBounds(
                800, 523, 170, 35
        );

        add(btnActualizar);

        JButton btnVolver =
                new JButton("Volver");

        btnVolver.setBounds(
                800, 568, 120, 35
        );

        add(btnVolver);

        cargarTabla();

        btnActualizar.addActionListener(
                e -> cargarTabla()
        );

        btnAprobar.addActionListener(
                e -> aprobarSolicitud()
        );

        btnRechazar.addActionListener(
                e -> rechazarSolicitud()
        );

        btnVolver.addActionListener(
                e -> {
                    new FrmAsesor()
                            .setVisible(true);
                    dispose();
                }
        );
    }

    private void cargarTabla() {

        modelo.setRowCount(0);

        ArrayList<SolicitudActualizacion> lista =
                solicitudDAO.listarPendientes();

        for (SolicitudActualizacion s : lista) {

            modelo.addRow(
                    new Object[]{
                            s.getIdSolicitud(),
                            s.getCedulaAsociado(),
                            s.getNuevoTelefono() != null
                                    ? s.getNuevoTelefono()
                                    : "-",
                            s.getNuevoCorreo() != null
                                    ? s.getNuevoCorreo()
                                    : "-",
                            s.getNuevaDireccion() != null
                                    ? s.getNuevaDireccion()
                                    : "-",
                            s.getFechaSolicitud()
                    }
            );
        }
    }

    private void aprobarSolicitud() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una solicitud"
            );

            return;
        }

        int idSolicitud =
                (int) modelo.getValueAt(fila, 0);

        String cedula =
                modelo.getValueAt(
                        fila, 1
                ).toString();

        String telefono =
                modelo.getValueAt(
                        fila, 2
                ).toString();

        String correo =
                modelo.getValueAt(
                        fila, 3
                ).toString();

        String direccion =
                modelo.getValueAt(
                        fila, 4
                ).toString();

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Aprobar solicitud de " + cedula + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        boolean resultado =
                solicitudDAO.aprobar(
                        idSolicitud,
                        cedula,
                        telefono.equals("-")
                                ? "" : telefono,
                        correo.equals("-")
                                ? "" : correo,
                        direccion.equals("-")
                                ? "" : direccion
                );

        if (resultado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Solicitud aprobada y datos " +
                    "del asociado actualizados"
            );

            cargarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo aprobar"
            );
        }
    }

    private void rechazarSolicitud() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una solicitud"
            );

            return;
        }

        int idSolicitud =
                (int) modelo.getValueAt(fila, 0);

        String cedula =
                modelo.getValueAt(
                        fila, 1
                ).toString();

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Rechazar solicitud de "
                        + cedula + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        boolean resultado =
                solicitudDAO.rechazar(idSolicitud);

        if (resultado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Solicitud rechazada"
            );

            cargarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo rechazar"
            );
        }
    }
}