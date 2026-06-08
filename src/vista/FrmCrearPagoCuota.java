package vista;


import dao.PagoCuotaDAO;
import modelo.PagoCuota;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

import com.toedter.calendar.JDateChooser;

public class FrmCrearPagoCuota extends JFrame {

    private JTextField txtNumeroCuota;
    private JTextField txtValorPagado;
    private JDateChooser dcFechaPago;

    private JComboBox<String> cbEstadoPago;

    private JLabel lblRadicado;

    private JButton btnGuardar;
    private JButton btnCancelar;

    private PagoCuotaDAO pagoCuotaDAO;
    private String numeroRadicado;

    public FrmCrearPagoCuota(
            String numeroRadicado
    ) {

        this.numeroRadicado = numeroRadicado;

        pagoCuotaDAO = new PagoCuotaDAO();

        setTitle("Registrar Pago de Cuota");

        setSize(700, 520);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(null);

        JLabel lblTitulo =
                new JLabel("REGISTRAR PAGO DE CUOTA");

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 22)
        );

        lblTitulo.setBounds(130, 20, 450, 40);

        add(lblTitulo);

        JLabel lblCreditoTxt =
                new JLabel("Crédito N°:");

        lblCreditoTxt.setBounds(50, 90, 150, 25);

        add(lblCreditoTxt);

        lblRadicado = new JLabel(numeroRadicado);

        lblRadicado.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        lblRadicado.setBounds(220, 90, 250, 25);

        add(lblRadicado);

        JLabel lblNumeroCuota =
                new JLabel("N° Cuota:");

        lblNumeroCuota.setBounds(50, 135, 150, 25);

        add(lblNumeroCuota);

        txtNumeroCuota = new JTextField();

        txtNumeroCuota.setBounds(
                220, 135, 100, 25
        );

        add(txtNumeroCuota);

        JLabel lblFecha =
                new JLabel("Fecha de Pago:");

        lblFecha.setBounds(50, 180, 150, 25);

        add(lblFecha);

        dcFechaPago = new JDateChooser();

        dcFechaPago.setDateFormatString(
                "yyyy-MM-dd"
        );

        dcFechaPago.setBounds(
                220, 180, 180, 25
        );

        add(dcFechaPago);

        JLabel lblValor =
                new JLabel("Valor Pagado:");

        lblValor.setBounds(50, 225, 150, 25);

        add(lblValor);

        txtValorPagado = new JTextField();

        txtValorPagado.setBounds(
                220, 225, 200, 25
        );

        add(txtValorPagado);

        JLabel lblEstado =
                new JLabel("Estado Pago:");

        lblEstado.setBounds(50, 270, 150, 25);

        add(lblEstado);

        cbEstadoPago = new JComboBox<>();

        cbEstadoPago.addItem("a_tiempo");
        cbEstadoPago.addItem("pago_demorado");
        cbEstadoPago.addItem("pendiente");

        cbEstadoPago.setBounds(220, 270, 180, 25);

        add(cbEstadoPago);

        btnGuardar = new JButton("Guardar");

        btnGuardar.setBounds(180, 390, 120, 35);

        add(btnGuardar);

        btnCancelar = new JButton("Cancelar");

        btnCancelar.setBounds(340, 390, 120, 35);

        add(btnCancelar);

        btnGuardar.addActionListener(
                e -> guardarPago()
        );

        btnCancelar.addActionListener(
                e -> {
                    new FrmGestionCredito()
                            .setVisible(true);
                    dispose();
                }
        );
    }

    private void guardarPago() {

        try {

            String cuotaTxt =
                    txtNumeroCuota
                            .getText().trim();

            if (cuotaTxt.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese el número de cuota"
                );

                return;
            }

            int numeroCuota =
                    Integer.parseInt(cuotaTxt);

            if (numeroCuota <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "El número de cuota debe " +
                        "ser mayor a cero"
                );

                return;
            }

            if (dcFechaPago.getDate() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione la fecha de pago"
            );

            return;
        }

        Date fechaPago =
                new Date(
                        dcFechaPago
                                .getDate()
                                .getTime()
                );

            String valorTxt =
                    txtValorPagado
                            .getText().trim();

            if (valorTxt.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese el valor pagado"
                );

                return;
            }

            double valorPagado =
                    Double.parseDouble(valorTxt);

            if (valorPagado <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "El valor pagado debe ser " +
                        "mayor a cero"
                );

                return;
            }

            String estadoPago =
                    cbEstadoPago.getSelectedItem()
                            .toString();

            int idPago =
                    pagoCuotaDAO.generarIdPago();

            PagoCuota pago = new PagoCuota(
                    idPago,
                    numeroCuota,
                    fechaPago,
                    valorPagado,
                    estadoPago,
                    numeroRadicado
            );

            boolean resultado =
                    pagoCuotaDAO.insertar(pago);

            if (resultado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Pago registrado correctamente"
                );

                new FrmGestionCredito()
                        .setVisible(true);

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo registrar el pago"
                );
            }

        } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Verifique los valores numéricos"
        );

    } catch (IllegalArgumentException e) {

        JOptionPane.showMessageDialog(
                this,
                "Formato de fecha incorrecto. " +
                "Use AAAA-MM-DD"
        );

    } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }
}