package vista;

import dao.CreditoDAO;
import dao.PagoCuotaDAO;
import modelo.Credito;
import modelo.PagoCuota;
import modelo.Sesion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FrmMisCreditos extends JFrame {

    private JTable tablaCreditos;
    private DefaultTableModel modeloCreditos;
    private CreditoDAO creditoDAO;
    private PagoCuotaDAO pagoCuotaDAO;

    public FrmMisCreditos() {

        creditoDAO = new CreditoDAO();
        pagoCuotaDAO = new PagoCuotaDAO();

        setTitle("Mis Créditos");

        setSize(1000, 600);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(null);

        JLabel lblTitulo =
                new JLabel("MIS CRÉDITOS");

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 22)
        );

        lblTitulo.setBounds(400, 20, 300, 35);

        add(lblTitulo);

        JSeparator sep = new JSeparator();

        sep.setBounds(30, 65, 930, 2);

        add(sep);

        JLabel lblCreditos =
                new JLabel("Créditos activos:");

        lblCreditos.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        lblCreditos.setBounds(30, 75, 200, 25);

        add(lblCreditos);

        modeloCreditos =
                new DefaultTableModel();

        modeloCreditos.addColumn("N° Radicado");
        modeloCreditos.addColumn("Val. Aprobado");
        modeloCreditos.addColumn("Plazo");
        modeloCreditos.addColumn("Tasa");
        modeloCreditos.addColumn("Estado");
        modeloCreditos.addColumn("Próx. Vencimiento");
        modeloCreditos.addColumn("Cuotas Pagadas");

        tablaCreditos =
                new JTable(modeloCreditos);

        JScrollPane scrollCreditos =
                new JScrollPane(tablaCreditos);

        scrollCreditos.setBounds(
                30, 105, 930, 200
        );

        add(scrollCreditos);

        JLabel lblPagos =
                new JLabel(
                        "Historial de pagos " +
                        "(seleccione un crédito):"
                );

        lblPagos.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        lblPagos.setBounds(30, 315, 400, 25);

        add(lblPagos);

        DefaultTableModel modeloPagos =
                new DefaultTableModel();

        modeloPagos.addColumn("N° Cuota");
        modeloPagos.addColumn("Fecha Pago");
        modeloPagos.addColumn("Valor Pagado");
        modeloPagos.addColumn("Estado");

        JTable tablaPagos =
                new JTable(modeloPagos);

        JScrollPane scrollPagos =
                new JScrollPane(tablaPagos);

        scrollPagos.setBounds(
                30, 345, 930, 150
        );

        add(scrollPagos);

        JButton btnVerPagos =
                new JButton("Ver Pagos");

        btnVerPagos.setBounds(
                30, 510, 150, 35
        );

        add(btnVerPagos);

        JButton btnVolver =
                new JButton("Volver");

        btnVolver.setBounds(
                840, 510, 120, 35
        );

        add(btnVolver);

        cargarCreditos();

        btnVerPagos.addActionListener(
                e -> {

                    int fila =
                            tablaCreditos
                                    .getSelectedRow();

                    if (fila == -1) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Seleccione un crédito"
                        );

                        return;
                    }

                    String radicado =
                            modeloCreditos.getValueAt(
                                    fila, 0
                            ).toString();

                    modeloPagos.setRowCount(0);

                    ArrayList<PagoCuota> pagos =
                            pagoCuotaDAO
                                    .listarPorCredito(
                                            radicado
                                    );

                    for (PagoCuota p : pagos) {

                        modeloPagos.addRow(
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

    private void cargarCreditos() {

        modeloCreditos.setRowCount(0);

        String cedula =
                Sesion.getCedulaAsociadoActivo();

        ArrayList<Credito> lista =
                creditoDAO.listarPorAsociado(cedula);

        for (Credito c : lista) {

            int cuotasPagadas =
                    pagoCuotaDAO
                            .listarPorCredito(
                                    c.getNumeroRadicado()
                            ).size();

            modeloCreditos.addRow(
                    new Object[]{
                            c.getNumeroRadicado(),
                            String.format(
                                    "$%.2f",
                                    c.getValorAprobado()
                            ),
                            c.getPlazoMeses() + " meses",
                            c.getTasaInteresMensual()
                                    + "%",
                            c.getEstadoCredito(),
                            c.getFechaPrimerVencimiento(),
                            cuotasPagadas
                    }
            );
        }
    }
}