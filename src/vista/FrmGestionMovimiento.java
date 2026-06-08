package vista;

import dao.MovimientoDAO;
import modelo.Movimiento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FrmGestionMovimiento extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JTextField txtBuscarCuenta;

    private JButton btnRegistrar;
    private JButton btnBuscarCuenta;
    private JButton btnCalcularSaldo;
    private JButton btnActualizar;
    private JButton btnVolver;

    private MovimientoDAO movimientoDAO;

    public FrmGestionMovimiento() {

        movimientoDAO =
                new MovimientoDAO();

        setTitle(
                "Gestión Movimientos"
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

        add(lblLogo);

        JSeparator separador =
                new JSeparator();

        separador.setBounds(
                30,
                80,
                1040,
                2
        );

        add(separador);

        JLabel lblTitulo =
                new JLabel(
                        "GESTIÓN DE MOVIMIENTOS"
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
                100,
                400,
                40
        );

        add(lblTitulo);

        JLabel lblBuscar =
                new JLabel(
                        "N° Cuenta:"
                );

        lblBuscar.setBounds(
                30,
                155,
                100,
                25
        );

        add(lblBuscar);

        txtBuscarCuenta =
                new JTextField();

        txtBuscarCuenta.setBounds(
                135,
                155,
                200,
                25
        );

        add(txtBuscarCuenta);

        btnBuscarCuenta =
                new JButton(
                        "Buscar Cuenta"
                );

        btnBuscarCuenta.setBounds(
                345,
                155,
                150,
                25
        );

        add(btnBuscarCuenta);

        modelo =
                new DefaultTableModel();

        modelo.addColumn(
                "N° Transacción"
        );

        modelo.addColumn(
                "Tipo"
        );

        modelo.addColumn(
                "Valor"
        );

        modelo.addColumn(
                "Fecha y Hora"
        );

        modelo.addColumn(
                "Canal"
        );

        modelo.addColumn(
                "N° Cuenta"
        );

        tabla =
                new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(
                30,
                200,
                760,
                450
        );

        add(scroll);

        btnRegistrar =
                new JButton(
                        "Registrar Movimiento"
                );

        btnRegistrar.setBounds(
                800,
                433,
                170,
                35
        );

        add(btnRegistrar);

        btnCalcularSaldo =
                new JButton(
                        "Calcular Saldo"
                );

        btnCalcularSaldo.setBounds(
                800,
                478,
                170,
                35
        );

        add(btnCalcularSaldo);

        btnActualizar =
                new JButton(
                        "Actualizar Tabla"
                );

        btnActualizar.setBounds(
                800,
                523,
                170,
                35
        );

        add(btnActualizar);

        btnVolver =
                new JButton(
                        "Volver"
                );

        btnVolver.setBounds(
                800,
                568,
                120,
                35
        );

        add(btnVolver);

        cargarTabla();

        btnActualizar.addActionListener(
                e -> cargarTabla()
        );

        btnBuscarCuenta.addActionListener(
                e -> buscarPorCuenta()
        );

        btnCalcularSaldo.addActionListener(
                e -> mostrarSaldo()
        );

        btnRegistrar.addActionListener(
                e -> {
                    new FrmCrearMovimiento()
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

        ArrayList<Movimiento> lista =
                movimientoDAO.listar();

        for (Movimiento m : lista) {

            modelo.addRow(
                    new Object[]{
                            m.getNumeroTransaccion(),
                            m.getTipoMovimiento(),
                            m.getValor(),
                            m.getFechaHora(),
                            m.getCanal(),
                            m.getNumeroCuenta()
                    }
            );
        }
    }

    private void buscarPorCuenta() {

        String numeroCuenta =
                txtBuscarCuenta.getText().trim();

        if (numeroCuenta.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un número de cuenta"
            );

            return;
        }

        modelo.setRowCount(0);

        ArrayList<Movimiento> lista =
                movimientoDAO.listarPorCuenta(
                        numeroCuenta
                );

        if (lista.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se encontraron movimientos " +
                    "para la cuenta: " + numeroCuenta
            );

            return;
        }

        for (Movimiento m : lista) {

            modelo.addRow(
                    new Object[]{
                            m.getNumeroTransaccion(),
                            m.getTipoMovimiento(),
                            m.getValor(),
                            m.getFechaHora(),
                            m.getCanal(),
                            m.getNumeroCuenta()
                    }
            );
        }
    }

    private void mostrarSaldo() {

        String numeroCuenta =
                txtBuscarCuenta.getText().trim();

        if (numeroCuenta.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un número de cuenta " +
                    "para calcular el saldo"
            );

            return;
        }

        double saldo =
                movimientoDAO.calcularSaldo(
                        numeroCuenta
                );

        JOptionPane.showMessageDialog(
                this,
                "Saldo actual de la cuenta "
                        + numeroCuenta
                        + ": $"
                        + String.format(
                                "%.2f",
                                saldo
                        )
        );
    }
}