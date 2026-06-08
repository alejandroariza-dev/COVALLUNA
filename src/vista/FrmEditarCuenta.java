package vista;

import com.toedter.calendar.JDateChooser;
import dao.AgenciaDAO;
import dao.AsociadoDAO;
import dao.CuentaAhorrosDAO;
import modelo.Agencia;
import modelo.Asociado;
import modelo.CuentaAhorros;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class FrmEditarCuenta extends JFrame {

    private JTextField txtNumeroCuenta;

    private JComboBox<String> cbEstado;
    private JComboBox<String> cbAsociado;
    private JComboBox<String> cbAgencia;

    private JDateChooser dcFechaApertura;

    private JButton btnGuardar;
    private JButton btnCancelar;

    private CuentaAhorrosDAO cuentaDAO;
    private AsociadoDAO asociadoDAO;
    private AgenciaDAO agenciaDAO;
    
    private String numeroCuenta;

    public FrmEditarCuenta(
        String numeroCuenta) {

    this.numeroCuenta =
            numeroCuenta;

        cuentaDAO =
                new CuentaAhorrosDAO();

        asociadoDAO =
                new AsociadoDAO();

        agenciaDAO =
                new AgenciaDAO();

        setTitle(
                "Editar Cuenta"
        );

        setSize(
                700,
                600
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
                        "EDITAR CUENTA"
                );

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        lblTitulo.setBounds(
                220,
                20,
                300,
                40
        );

        add(
                lblTitulo
        );

        JLabel lblNumero =
                new JLabel(
                        "Número Cuenta:"
                );

        lblNumero.setBounds(
                50,
                100,
                120,
                25
        );

        add(
                lblNumero
        );

        txtNumeroCuenta =
                new JTextField();

        txtNumeroCuenta.setBounds(
                220,
                100,
                200,
                25
        );

        add(
                txtNumeroCuenta
        );
        
        txtNumeroCuenta.setEditable(
                false
        );

        JLabel lblFecha =
                new JLabel(
                        "Fecha Apertura:"
                );

        lblFecha.setBounds(
                50,
                150,
                120,
                25
        );

        add(
                lblFecha
        );

        dcFechaApertura =
                new JDateChooser();

        dcFechaApertura.setBounds(
                220,
                150,
                200,
                25
        );

        add(
                dcFechaApertura
        );

        JLabel lblEstado =
                new JLabel(
                        "Estado:"
                );

        lblEstado.setBounds(
                50,
                200,
                120,
                25
        );

        add(
                lblEstado
        );

        cbEstado =
                new JComboBox<>();

        cbEstado.addItem(
                "activa"
        );

        cbEstado.addItem(
                "inactiva"
        );

        cbEstado.addItem(
                "embargada"
        );

        cbEstado.setBounds(
                220,
                200,
                200,
                25
        );

        add(
                cbEstado
        );

        JLabel lblAsociado =
                new JLabel(
                        "Asociado:"
                );

        lblAsociado.setBounds(
                50,
                250,
                120,
                25
        );

        add(
                lblAsociado
        );

        cbAsociado =
                new JComboBox<>();

        for (
                Asociado asociado
                :
                asociadoDAO.listar()
        ) {

            cbAsociado.addItem(
                    asociado.getCedula()
            );
        }

        cbAsociado.setBounds(
                220,
                250,
                200,
                25
        );

        add(
                cbAsociado
        );

        JLabel lblAgencia =
                new JLabel(
                        "Agencia:"
                );

        lblAgencia.setBounds(
                50,
                300,
                120,
                25
        );

        add(
                lblAgencia
        );

        cbAgencia =
                new JComboBox<>();

        for (
                Agencia agencia
                :
                agenciaDAO.listar()
        ) {

            cbAgencia.addItem(
                    agencia.getCodigoAgencia()
            );
        }

        cbAgencia.setBounds(
                220,
                300,
                200,
                25
        );

        add(
                cbAgencia
        );

        btnGuardar =
                new JButton(
                        "Guardar"
                );

        btnGuardar.setBounds(
                180,
                450,
                120,
                35
        );

        add(
                btnGuardar
        );

        btnCancelar =
                new JButton(
                        "Cancelar"
                );

        btnCancelar.setBounds(
                340,
                450,
                120,
                35
        );

        add(
                btnCancelar
        );

        btnGuardar.addActionListener(
                e -> actualizarCuenta()
        );

        btnCancelar.addActionListener(
                e -> {

                    new FrmGestionCuenta()
                            .setVisible(true);

                    dispose();
                }
        );
        
        cargarDatos();
    }
    
    private void cargarDatos() {

        CuentaAhorros cuenta =
                cuentaDAO.buscarPorNumero(
                        numeroCuenta
                );

        if (cuenta == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cuenta no encontrada"
            );

            return;
        }

        txtNumeroCuenta.setText(
                cuenta.getNumeroCuenta()
        );

        dcFechaApertura.setDate(
                cuenta.getFechaApertura()
        );

        cbEstado.setSelectedItem(
                cuenta.getEstado()
        );

        cbAsociado.setSelectedItem(
                cuenta.getCedulaAsociado()
        );

        cbAgencia.setSelectedItem(
                cuenta.getCodigoAgencia()
        );
    }

private void actualizarCuenta() {

    try {

        if (dcFechaApertura.getDate() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una fecha"
            );

            return;
        }

        if (
                dcFechaApertura.getDate()
                        .after(
                                new java.util.Date()
                        )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "La fecha no puede ser futura"
            );

            return;
        }

        CuentaAhorros cuenta =
                new CuentaAhorros(
                        txtNumeroCuenta.getText(),
                        new Date(
                                dcFechaApertura
                                        .getDate()
                                        .getTime()
                        ),
                        cbEstado.getSelectedItem()
                                .toString(),
                        cbAsociado.getSelectedItem()
                                .toString(),
                        cbAgencia.getSelectedItem()
                                .toString()
                );

        boolean resultado =
                cuentaDAO.actualizar(
                        cuenta
                );

        if (resultado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cuenta actualizada correctamente"
            );

            new FrmGestionCuenta()
                    .setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo actualizar"
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Datos inválidos"
        );
        }
    }
}