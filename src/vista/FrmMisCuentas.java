package vista;

import dao.CuentaAhorrosDAO;
import dao.MovimientoDAO;
import modelo.CuentaAhorros;
import modelo.Sesion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FrmMisCuentas extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private CuentaAhorrosDAO cuentaDAO;
    private MovimientoDAO movimientoDAO;

    public FrmMisCuentas() {

        cuentaDAO = new CuentaAhorrosDAO();
        movimientoDAO = new MovimientoDAO();

        setTitle("Mis Cuentas de Ahorro");

        setSize(900, 550);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(null);

        JLabel lblTitulo =
                new JLabel("MIS CUENTAS DE AHORRO");

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 22)
        );

        lblTitulo.setBounds(295, 20, 400, 35);

        add(lblTitulo);

        JSeparator sep = new JSeparator();

        sep.setBounds(30, 65, 830, 2);

        add(sep);

        modelo = new DefaultTableModel();

        modelo.addColumn("N° Cuenta");
        modelo.addColumn("Fecha Apertura");
        modelo.addColumn("Estado");
        modelo.addColumn("Agencia");
        modelo.addColumn("Saldo Actual");

        tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(30, 80, 830, 350);

        add(scroll);

        JButton btnVerMovimientos =
                new JButton("Ver Movimientos");

        btnVerMovimientos.setBounds(
                30, 445, 180, 35
        );

        add(btnVerMovimientos);

        JButton btnVolver =
                new JButton("Volver");

        btnVolver.setBounds(
                700, 445, 120, 35
        );

        add(btnVolver);

        cargarCuentas();

        btnVerMovimientos.addActionListener(
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
                                    fila, 0
                            ).toString();

                    new FrmExtractoCuenta(
                            numeroCuenta
                    ).setVisible(true);

                    dispose();
                }
        );

        btnVolver.addActionListener(
                e -> {
                    new FrmAsociado()
                            .setVisible(true);
                    dispose();
                }
        );
    }

    private void cargarCuentas() {

        modelo.setRowCount(0);

        String cedula =
                Sesion.getCedulaAsociadoActivo();

        ArrayList<CuentaAhorros> lista =
                cuentaDAO.listarPorAsociado(cedula);

        for (CuentaAhorros c : lista) {

            double saldo =
                    movimientoDAO.calcularSaldo(
                            c.getNumeroCuenta()
                    );

            modelo.addRow(
                    new Object[]{
                            c.getNumeroCuenta(),
                            c.getFechaApertura(),
                            c.getEstado(),
                            c.getCodigoAgencia(),
                            String.format("$%.2f", saldo)
                    }
            );
        }
    }
}