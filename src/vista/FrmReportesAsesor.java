package vista;

import dao.AsociadoDAO;
import dao.CreditoDAO;
import dao.MovimientoDAO;
import dao.PagoCuotaDAO;
import modelo.Asociado;
import modelo.Credito;
import modelo.Movimiento;
import modelo.PagoCuota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FrmReportesAsesor extends JFrame {

    private JTabbedPane tabs;

    public FrmReportesAsesor() {

        setTitle("Reportes - Asesor");

        setSize(1100, 700);

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

        lblLogo.setBounds(30, 15, 250, 35);

        add(lblLogo);

        JSeparator sep = new JSeparator();

        sep.setBounds(30, 55, 1030, 2);

        add(sep);

        JButton btnVolver =
                new JButton("Volver");

        btnVolver.setBounds(950, 15, 100, 30);

        add(btnVolver);

        btnVolver.addActionListener(
                e -> {
                    new FrmAsesor()
                            .setVisible(true);
                    dispose();
                }
        );

        tabs = new JTabbedPane();

        tabs.setBounds(30, 65, 1030, 580);

        tabs.addTab(
                "1. Asociados",
                crearTabReporte1()
        );

        tabs.addTab(
                "2. Extracto Cuenta",
                crearTabReporte2()
        );

        tabs.addTab(
                "3. En Mora",
                crearTabReporte4()
        );

        tabs.addTab(
                "4. Historial Pagos",
                crearTabReporte5()
        );

        add(tabs);
    }
    
    private JPanel crearTabReporte1() {

        JPanel panel = new JPanel(null);

        JLabel lblTitulo = new JLabel(
                "Listado de Asociados por Estado y Agencia"
        );

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        lblTitulo.setBounds(10, 10, 500, 25);

        panel.add(lblTitulo);

        JLabel lblEstado =
                new JLabel("Estado:");

        lblEstado.setBounds(10, 50, 80, 25);

        panel.add(lblEstado);

        JComboBox<String> cbEstado =
                new JComboBox<>();

        cbEstado.addItem("Todos");
        cbEstado.addItem("activo");
        cbEstado.addItem("suspendido");
        cbEstado.addItem("retirado");

        cbEstado.setBounds(90, 50, 150, 25);

        panel.add(cbEstado);

        JLabel lblAgencia =
                new JLabel("Municipio:");

        lblAgencia.setBounds(260, 50, 80, 25);

        panel.add(lblAgencia);

        JTextField txtAgencia =
                new JTextField();

        txtAgencia.setBounds(340, 50, 150, 25);

        panel.add(txtAgencia);

        JButton btnGenerar =
                new JButton("Generar");

        btnGenerar.setBounds(510, 50, 100, 25);

        panel.add(btnGenerar);

        DefaultTableModel modelo =
                new DefaultTableModel();

        modelo.addColumn("Cédula");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Tipo");
        modelo.addColumn("Fecha Afiliación");
        modelo.addColumn("Estado");
        modelo.addColumn("Municipio");

        JTable tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(10, 90, 990, 430);

        panel.add(scroll);

        btnGenerar.addActionListener(e -> {

            modelo.setRowCount(0);

            String estadoFiltro =
                    cbEstado.getSelectedItem()
                            .toString();

            String agenciaFiltro =
                    txtAgencia.getText().trim();

            AsociadoDAO dao = new AsociadoDAO();

            ArrayList<Asociado> lista =
                    dao.listar();

            for (Asociado a : lista) {

                boolean coincideEstado =
                        estadoFiltro.equals("Todos")
                        ||
                        a.getEstado()
                                .equals(estadoFiltro);

                boolean coincideAgencia =
                        agenciaFiltro.isEmpty()
                        ||
                        a.getMunicipio()
                                .toLowerCase()
                                .contains(
                                        agenciaFiltro
                                                .toLowerCase()
                                );

                if (coincideEstado
                        && coincideAgencia) {

                    modelo.addRow(
                            new Object[]{
                                    a.getCedula(),
                                    a.getNombres(),
                                    a.getApellidos(),
                                    "Asociado",
                                    a.getFechaAfiliacion(),
                                    a.getEstado(),
                                    a.getMunicipio()
                            }
                    );
                }
            }
        });

        return panel;
    }
    
    private JPanel crearTabReporte2() {

        JPanel panel = new JPanel(null);

        JLabel lblTitulo = new JLabel(
                "Extracto de Cuenta de Ahorro"
        );

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        lblTitulo.setBounds(10, 10, 400, 25);

        panel.add(lblTitulo);

        JLabel lblCuenta =
                new JLabel("N° Cuenta:");

        lblCuenta.setBounds(10, 50, 100, 25);

        panel.add(lblCuenta);

        JTextField txtCuenta =
                new JTextField();

        txtCuenta.setBounds(110, 50, 150, 25);

        panel.add(txtCuenta);

        JLabel lblTipo =
                new JLabel("Tipo:");

        lblTipo.setBounds(280, 50, 60, 25);

        panel.add(lblTipo);

        JComboBox<String> cbTipo =
                new JComboBox<>();

        cbTipo.addItem("Todos");
        cbTipo.addItem("deposito");
        cbTipo.addItem("retiro");
        cbTipo.addItem("transferencia_entrante");
        cbTipo.addItem("transferencia_saliente");

        cbTipo.setBounds(340, 50, 200, 25);

        panel.add(cbTipo);

        JLabel lblCanal =
                new JLabel("Canal:");

        lblCanal.setBounds(560, 50, 60, 25);

        panel.add(lblCanal);

        JComboBox<String> cbCanal =
                new JComboBox<>();

        cbCanal.addItem("Todos");
        cbCanal.addItem("agencia");
        cbCanal.addItem("app_movil");
        cbCanal.addItem("cajero");

        cbCanal.setBounds(620, 50, 150, 25);

        panel.add(cbCanal);

        JButton btnGenerar =
                new JButton("Generar");

        btnGenerar.setBounds(790, 50, 100, 25);

        panel.add(btnGenerar);

        DefaultTableModel modelo =
                new DefaultTableModel();

        modelo.addColumn("N° Transacción");
        modelo.addColumn("Tipo");
        modelo.addColumn("Valor");
        modelo.addColumn("Fecha y Hora");
        modelo.addColumn("Canal");

        JTable tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(10, 90, 990, 370);

        panel.add(scroll);

        JLabel lblSaldoTxt =
                new JLabel("Saldo:");

        lblSaldoTxt.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        lblSaldoTxt.setBounds(10, 470, 80, 25);

        panel.add(lblSaldoTxt);

        JLabel lblSaldoValor =
                new JLabel("-");

        lblSaldoValor.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        lblSaldoValor.setBounds(90, 470, 200, 25);

        panel.add(lblSaldoValor);

        btnGenerar.addActionListener(e -> {

            String numeroCuenta =
                    txtCuenta.getText().trim();

            if (numeroCuenta.isEmpty()) {

                JOptionPane.showMessageDialog(
                        panel,
                        "Ingrese un número de cuenta"
                );

                return;
            }

            modelo.setRowCount(0);

            MovimientoDAO dao =
                    new MovimientoDAO();

            String tipoFiltro =
                    cbTipo.getSelectedItem()
                            .toString();

            String canalFiltro =
                    cbCanal.getSelectedItem()
                            .toString();

            ArrayList<Movimiento> lista =
                    dao.listarPorCuenta(
                            numeroCuenta
                    );

            for (Movimiento m : lista) {

                boolean coincideTipo =
                        tipoFiltro.equals("Todos")
                        ||
                        m.getTipoMovimiento()
                                .equals(tipoFiltro);

                boolean coincideCanal =
                        canalFiltro.equals("Todos")
                        ||
                        m.getCanal()
                                .equals(canalFiltro);

                if (coincideTipo && coincideCanal) {

                    modelo.addRow(
                            new Object[]{
                                    m.getNumeroTransaccion(),
                                    m.getTipoMovimiento(),
                                    String.format(
                                            "$%.2f",
                                            m.getValor()
                                    ),
                                    m.getFechaHora(),
                                    m.getCanal()
                            }
                    );
                }
            }

            double saldo =
                    dao.calcularSaldo(numeroCuenta);

            lblSaldoValor.setText(
                    String.format("$%.2f", saldo)
            );
        });

        return panel;
    }
    
    private JPanel crearTabReporte4() {

        JPanel panel = new JPanel(null);

        JLabel lblTitulo = new JLabel(
                "Asociados en Mora"
        );

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        lblTitulo.setBounds(10, 10, 400, 25);

        panel.add(lblTitulo);

        JButton btnGenerar =
                new JButton("Generar");

        btnGenerar.setBounds(10, 50, 100, 25);

        panel.add(btnGenerar);

        DefaultTableModel modelo =
                new DefaultTableModel();

        modelo.addColumn("Asociado");
        modelo.addColumn("N° Crédito");
        modelo.addColumn("N° Cuota");
        modelo.addColumn("Estado Pago");
        modelo.addColumn("Fecha Pago");
        modelo.addColumn("Valor");

        JTable tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(10, 90, 990, 430);

        panel.add(scroll);

        btnGenerar.addActionListener(e -> {

            modelo.setRowCount(0);

            CreditoDAO creditoDAO =
                    new CreditoDAO();

            PagoCuotaDAO pagoDAO =
                    new PagoCuotaDAO();

            ArrayList<Credito> creditos =
                    creditoDAO.listar();

            for (Credito c : creditos) {

                ArrayList<PagoCuota> pagos =
                        pagoDAO.listarPorCredito(
                                c.getNumeroRadicado()
                        );

                for (PagoCuota p : pagos) {

                    if (p.getEstadoPago()
                            .equals("pago_demorado")
                            ||
                            p.getEstadoPago()
                                    .equals("pendiente")
                    ) {

                        modelo.addRow(
                                new Object[]{
                                        c.getCedulaAsociado(),
                                        c.getNumeroRadicado(),
                                        p.getNumeroCuota(),
                                        p.getEstadoPago(),
                                        p.getFechaPago(),
                                        String.format(
                                                "$%.2f",
                                                p.getValorPagado()
                                        )
                                }
                        );
                    }
                }
            }
        });

        return panel;
    }
    
    private JPanel crearTabReporte5() {

        JPanel panel = new JPanel(null);

        JLabel lblTitulo = new JLabel(
                "Historial de Pagos de un Crédito"
        );

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        lblTitulo.setBounds(10, 10, 400, 25);

        panel.add(lblTitulo);

        JLabel lblRadicado =
                new JLabel("N° Radicado:");

        lblRadicado.setBounds(10, 50, 110, 25);

        panel.add(lblRadicado);

        JTextField txtRadicado =
                new JTextField();

        txtRadicado.setBounds(125, 50, 180, 25);

        panel.add(txtRadicado);

        JButton btnGenerar =
                new JButton("Generar");

        btnGenerar.setBounds(320, 50, 100, 25);

        panel.add(btnGenerar);

        DefaultTableModel modelo =
                new DefaultTableModel();

        modelo.addColumn("N° Cuota");
        modelo.addColumn("Fecha Pago");
        modelo.addColumn("Valor Pagado");
        modelo.addColumn("Estado");

        JTable tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(10, 90, 990, 430);

        panel.add(scroll);

        btnGenerar.addActionListener(e -> {

            String radicado =
                    txtRadicado.getText().trim();

            if (radicado.isEmpty()) {

                JOptionPane.showMessageDialog(
                        panel,
                        "Ingrese el número de radicado"
                );

                return;
            }

            modelo.setRowCount(0);

            PagoCuotaDAO dao =
                    new PagoCuotaDAO();

            ArrayList<PagoCuota> lista =
                    dao.listarPorCredito(radicado);

            if (lista.isEmpty()) {

                JOptionPane.showMessageDialog(
                        panel,
                        "No se encontraron pagos " +
                        "para este crédito"
                );

                return;
            }

            for (PagoCuota p : lista) {

                modelo.addRow(
                        new Object[]{
                                p.getNumeroCuota(),
                                p.getFechaPago(),
                                String.format(
                                        "$%.2f",
                                        p.getValorPagado()
                                ),
                                p.getEstadoPago()
                        }
                );
            }
        });

        return panel;
    }
}