package vista;

import dao.CuentaAhorrosDAO;
import modelo.CuentaAhorros;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FrmGestionCuenta extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnCrear;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnActualizar;
    private JButton btnVolver;

    private CuentaAhorrosDAO cuentaDAO;

    public FrmGestionCuenta() {

        cuentaDAO =
                new CuentaAhorrosDAO();

        setTitle(
                "Gestión de Cuentas"
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

        setLayout(
                null
        );

        JLabel lblLogo =
                new JLabel(
                        "COOVALLUNA"
                );

        lblLogo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        lblLogo.setBounds(
                30,
                30,
                250,
                40
        );

        add(
                lblLogo
        );

        JSeparator separador =
                new JSeparator();

        separador.setBounds(
                30,
                80,
                1040,
                2
        );

        add(
                separador
        );

        JLabel lblTitulo =
                new JLabel(
                        "GESTIÓN DE CUENTAS"
                );

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        lblTitulo.setBounds(
                30,
                150,
                350,
                40
        );

        add(
                lblTitulo
        );

        btnCrear =
                new JButton(
                        "Crear Cuenta"
                );

        btnCrear.setBounds(
                800,
                433,
                140,
                35
        );

        add(
                btnCrear
        );

        btnEditar =
                new JButton(
                        "Editar Cuenta"
                );

        btnEditar.setBounds(
                800,
                478,
                140,
                35
        );

        add(
                btnEditar
        );

        btnEliminar =
                new JButton(
                        "Eliminar Cuenta"
                );

        btnEliminar.setBounds(
                800,
                523,
                140,
                35
        );

        add(
                btnEliminar
        );

        btnActualizar =
                new JButton(
                        "Actualizar Tabla"
                );

        btnActualizar.setBounds(
                800,
                568,
                140,
                35
        );

        add(
                btnActualizar
        );

        btnVolver =
                new JButton(
                        "Volver"
                );

        btnVolver.setBounds(
                800,
                613,
                100,
                35
        );

        add(
                btnVolver
        );

        modelo =
                new DefaultTableModel();

        modelo.addColumn(
                "Número Cuenta"
        );

        modelo.addColumn(
                "Fecha Apertura"
        );

        modelo.addColumn(
                "Estado"
        );

        modelo.addColumn(
                "Cédula Asociado"
        );

        modelo.addColumn(
                "Código Agencia"
        );

        tabla =
                new JTable(
                        modelo
                );

        JScrollPane scroll =
                new JScrollPane(
                        tabla
                );

        scroll.setBounds(
                30,
                200,
                760,
                450
        );

        add(
                scroll
        );

        cargarTabla();

        btnActualizar.addActionListener(
                e -> cargarTabla()
        );

        btnVolver.addActionListener(
                e -> {

                    new FrmAsesor()
                            .setVisible(true);

                    dispose();
                }
        );

        btnCrear.addActionListener(
                e -> {

                    new FrmCrearCuenta()
                            .setVisible(true);

                    dispose();
                }
        );

        btnEditar.addActionListener(
                e -> {

                    int fila =
                            tabla.getSelectedRow();

                    if (fila == -1) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Seleccione una cuenta"
                        );

                        return;
                    }

                    String numeroCuenta =
                            modelo.getValueAt(
                                    fila,
                                    0
                            ).toString();

                    new FrmEditarCuenta(
                            numeroCuenta
                    ).setVisible(
                            true
                    );

                    dispose();
                }
        );

        btnEliminar.addActionListener(
                e -> eliminarCuenta()
        );
    }

    private void cargarTabla() {

        modelo.setRowCount(
                0
        );

        ArrayList<CuentaAhorros> lista =
                cuentaDAO.listar();

        for (
                CuentaAhorros cuenta
                :
                lista
        ) {

            modelo.addRow(
                    new Object[]{
                            cuenta.getNumeroCuenta(),
                            cuenta.getFechaApertura(),
                            cuenta.getEstado(),
                            cuenta.getCedulaAsociado(),
                            cuenta.getCodigoAgencia()
                    }
            );
        }
    }

    private void eliminarCuenta() {

        int fila =
                tabla.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una cuenta"
            );

            return;
        }

        String numeroCuenta =
                modelo.getValueAt(
                        fila,
                        0
                ).toString();

        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Desea eliminar la cuenta "
                                + numeroCuenta
                                + "?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION
                );

        if (opcion == JOptionPane.YES_OPTION) {

            boolean resultado =
                    cuentaDAO.eliminar(
                            numeroCuenta
                    );

            if (resultado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cuenta eliminada"
                );

                cargarTabla();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo eliminar"
                );
            }
        }
    }
}