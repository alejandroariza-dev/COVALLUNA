package vista;

import dao.CreditoDAO;
import modelo.Credito;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FrmGestionCredito extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JTextField txtBuscarRadicado;

    private JButton btnRadicar;
    private JButton btnRegistrarPago;
    private JButton btnBuscar;
    private JButton btnActualizar;
    private JButton btnVolver;

    private CreditoDAO creditoDAO;

    public FrmGestionCredito() {

        creditoDAO =
                new CreditoDAO();

        setTitle(
                "Gestión Créditos"
        );

        setSize(
                1110,
                700
        );

        setLocationRelativeTo(
                null
        );

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

        JSeparator separador =
                new JSeparator();

        separador.setBounds(30, 80, 1040, 2);

        add(separador);

        JLabel lblTitulo =
                new JLabel("GESTIÓN DE CRÉDITOS");

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 24)
        );

        lblTitulo.setBounds(30, 100, 400, 40);

        add(lblTitulo);

        JLabel lblBuscar =
                new JLabel("N° Radicado:");

        lblBuscar.setBounds(30, 155, 120, 25);

        add(lblBuscar);

        txtBuscarRadicado =
                new JTextField();

        txtBuscarRadicado.setBounds(
                155, 155, 200, 25
        );

        add(txtBuscarRadicado);

        btnBuscar =
                new JButton("Buscar");

        btnBuscar.setBounds(
                365, 155, 100, 25
        );

        add(btnBuscar);

        modelo = new DefaultTableModel();

        modelo.addColumn("N° Radicado");
        modelo.addColumn("Val. Solicitado");
        modelo.addColumn("Val. Aprobado");
        modelo.addColumn("Plazo (meses)");
        modelo.addColumn("Tasa");
        modelo.addColumn("Estado");
        modelo.addColumn("Cédula Asociado");
        modelo.addColumn("Agencia");

        tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(30, 200, 760, 450);

        add(scroll);

        btnRadicar =
                new JButton("Radicar Crédito");

        btnRadicar.setBounds(800, 433, 170, 35);

        add(btnRadicar);

        btnRegistrarPago =
                new JButton("Registrar Pago");

        btnRegistrarPago.setBounds(
                800, 478, 170, 35
        );

        add(btnRegistrarPago);

        btnActualizar =
                new JButton("Actualizar Tabla");

        btnActualizar.setBounds(
                800, 523, 170, 35
        );

        add(btnActualizar);

        btnVolver =
                new JButton("Volver");

        btnVolver.setBounds(
                800, 568, 120, 35
        );

        add(btnVolver);

        cargarTabla();

        btnActualizar.addActionListener(
                e -> cargarTabla()
        );

        btnBuscar.addActionListener(
                e -> buscarPorRadicado()
        );

        btnRadicar.addActionListener(
                e -> {
                    new FrmCrearCredito()
                            .setVisible(true);
                    dispose();
                }
        );

        btnRegistrarPago.addActionListener(
                e -> {

                    int fila =
                            tabla.getSelectedRow();

                    if (fila == -1) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Seleccione un crédito " +
                                "de la tabla primero"
                        );

                        return;
                    }

                    String radicado =
                            modelo.getValueAt(
                                    fila, 0
                            ).toString();

                    new FrmCrearPagoCuota(radicado)
                            .setVisible(true);

                    dispose();
                }
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

        ArrayList<Credito> lista =
                creditoDAO.listar();

        for (Credito c : lista) {

            modelo.addRow(
                    new Object[]{
                            c.getNumeroRadicado(),
                            String.format("$%.2f", c.getValorSolicitado()),
                            c.getValorAprobado() != 0
                                    ? String.format("$%.2f", c.getValorAprobado())
                                    : "Sin aprobar",
                            c.getPlazoMeses(),
                            c.getTasaInteresMensual() + "%",
                            c.getEstadoCredito(),
                            c.getCedulaAsociado(),
                            c.getCodigoAgencia()
                    }
            );
        }
    }

    private void buscarPorRadicado() {

        String radicado =
                txtBuscarRadicado.getText().trim();

        if (radicado.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un número de radicado"
            );

            return;
        }

        modelo.setRowCount(0);

        Credito c =
                creditoDAO.buscarPorRadicado(
                        radicado
                );

        if (c == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se encontró el crédito: "
                            + radicado
            );

            return;
        }

        modelo.addRow(
                new Object[]{
                        c.getNumeroRadicado(),
                        String.format("$%.2f", c.getValorSolicitado()),
                        c.getValorAprobado() != 0
                                ? String.format("$%.2f", c.getValorAprobado())
                                : "Sin aprobar",
                        c.getPlazoMeses(),
                        c.getTasaInteresMensual() + "%",
                        c.getEstadoCredito(),
                        c.getCedulaAsociado(),
                        c.getCodigoAgencia()
                    }
            );
    }
}