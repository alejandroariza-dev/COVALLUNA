package vista;

import dao.CuentaAhorrosDAO;
import dao.MovimientoDAO;
import modelo.CuentaAhorros;
import modelo.Movimiento;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;

public class FrmCrearMovimiento extends JFrame {
    
    private JTextField txtValor;

    private JComboBox<String> cbTipoMovimiento;
    private JComboBox<String> cbCanal;
    private JComboBox<String> cbCuenta;

    private JButton btnGuardar;
    private JButton btnCancelar;

    private MovimientoDAO movimientoDAO;
    private CuentaAhorrosDAO cuentaAhorrosDAO;

    public FrmCrearMovimiento() {

        movimientoDAO =
                new MovimientoDAO();

        cuentaAhorrosDAO =
                new CuentaAhorrosDAO();

        setTitle(
                "Registrar Movimiento"
        );

        setSize(
                700,
                550
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

        JLabel lblTitulo =
                new JLabel(
                        "REGISTRAR MOVIMIENTO"
                );

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        lblTitulo.setBounds(
                150,
                20,
                400,
                40
        );

        add(lblTitulo);

        JLabel lblTipo =
                new JLabel(
                        "Tipo Movimiento:"
                );

        lblTipo.setBounds(
                50,
                150,
                150,
                25
        );

        add(lblTipo);

        cbTipoMovimiento =
                new JComboBox<>();

        cbTipoMovimiento.addItem(
                "deposito"
        );

        cbTipoMovimiento.addItem(
                "retiro"
        );

        cbTipoMovimiento.addItem(
                "transferencia_entrante"
        );

        cbTipoMovimiento.addItem(
                "transferencia_saliente"
        );

        cbTipoMovimiento.setBounds(
                220,
                150,
                220,
                25
        );

        add(cbTipoMovimiento);

        JLabel lblValor =
                new JLabel(
                        "Valor:"
                );

        lblValor.setBounds(
                50,
                200,
                150,
                25
        );

        add(lblValor);

        txtValor =
                new JTextField();

        txtValor.setBounds(
                220,
                200,
                200,
                25
        );

        add(txtValor);

        JLabel lblCanal =
                new JLabel(
                        "Canal:"
                );

        lblCanal.setBounds(
                50,
                250,
                150,
                25
        );

        add(lblCanal);

        cbCanal =
                new JComboBox<>();

        cbCanal.addItem(
                "agencia"
        );

        cbCanal.addItem(
                "app_movil"
        );

        cbCanal.addItem(
                "cajero"
        );

        cbCanal.setBounds(
                220,
                250,
                200,
                25
        );

        add(cbCanal);

        JLabel lblCuenta =
                new JLabel(
                        "N° Cuenta:"
                );

        lblCuenta.setBounds(
                50,
                300,
                150,
                25
        );

        add(lblCuenta);

        cbCuenta =
                new JComboBox<>();

        for (
                CuentaAhorros cuenta
                :
                cuentaAhorrosDAO.listar()
        ) {

            cbCuenta.addItem(
                    cuenta.getNumeroCuenta()
            );
        }

        cbCuenta.setBounds(
                220,
                300,
                200,
                25
        );

        add(cbCuenta);

        btnGuardar =
                new JButton(
                        "Guardar"
                );

        btnGuardar.setBounds(
                180,
                420,
                120,
                35
        );

        add(btnGuardar);

        btnCancelar =
                new JButton(
                        "Cancelar"
                );

        btnCancelar.setBounds(
                340,
                420,
                120,
                35
        );

        add(btnCancelar);

        btnGuardar.addActionListener(
                e -> guardarMovimiento()
        );

        btnCancelar.addActionListener(
                e -> {

                    new FrmGestionMovimiento()
                            .setVisible(true);

                    dispose();
                }
        );
    }

    private void guardarMovimiento() {

        try {
            String numeroTransaccion =
                movimientoDAO
                        .generarNumeroTransaccion();

            String valorTexto =
                    txtValor.getText().trim();

            if (valorTexto.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese el valor del movimiento"
                );

                return;
            }

            double valor =
                    Double.parseDouble(
                            valorTexto
                    );

            if (valor <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "El valor debe ser mayor a cero"
                );

                return;
            }

            String tipoMovimiento =
                    cbTipoMovimiento
                            .getSelectedItem()
                            .toString();

            String canal =
                    cbCanal
                            .getSelectedItem()
                            .toString();

            if (cbCuenta.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "No hay cuentas disponibles. " +
                        "Cree una cuenta primero."
                );

                return;
            }

            String numeroCuenta =
                    cbCuenta
                            .getSelectedItem()
                            .toString();

            Timestamp fechaHora =
                    new Timestamp(
                            System.currentTimeMillis()
                    );

            Movimiento movimiento =
                    new Movimiento(
                            numeroTransaccion,
                            tipoMovimiento,
                            valor,
                            fechaHora,
                            canal,
                            numeroCuenta
                    );

            boolean resultado =
                    movimientoDAO.insertar(
                            movimiento
                    );

            if (resultado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Movimiento registrado correctamente"
                );

                new FrmGestionMovimiento()
                        .setVisible(true);

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo registrar el movimiento"
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El valor debe ser numérico"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al registrar: "
                            + e.getMessage()
            );
        }
    }
}