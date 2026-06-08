package vista;

import javax.swing.*;
import java.awt.*;

import modelo.Sesion;

public class FrmAsesor extends JFrame {

    private JButton btnBeneficiario;
    private JButton btnCuenta;
    private JButton btnMovimiento;
    private JButton btnCredito;
    private JButton btnReportes;
    private JButton btnSalir;

    public FrmAsesor() {

        setTitle("Asesor / Cajero");

        setSize(500, 580);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(null);

        JLabel lblTitulo =
                new JLabel(
                        "Panel Asesor / Cajero"
                );

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 22)
        );

        lblTitulo.setBounds(130, 30, 300, 30);

        add(lblTitulo);

        btnBeneficiario =
                new JButton("Gestión Beneficiarios");

        btnBeneficiario.setBounds(
                130, 90, 220, 40
        );

        add(btnBeneficiario);

        btnCuenta =
                new JButton("Gestión Cuentas");

        btnCuenta.setBounds(130, 145, 220, 40);

        add(btnCuenta);

        btnMovimiento =
                new JButton("Gestión Movimientos");

        btnMovimiento.setBounds(
                130, 200, 220, 40
        );

        add(btnMovimiento);

        btnCredito =
                new JButton("Gestión Créditos");

        btnCredito.setBounds(130, 255, 220, 40);

        add(btnCredito);

        btnReportes =
                new JButton("Reportes");

        btnReportes.setBounds(
                130, 310, 220, 40
        );

        add(btnReportes);

        JButton btnSolicitudes =
                new JButton("Solicitudes Pendientes");

        btnSolicitudes.setBounds(
                130, 365, 220, 40
        );

        add(btnSolicitudes);

        btnSalir =
                new JButton("Cerrar Sesión");

        btnSalir.setBounds(130, 420, 220, 40);

        add(btnSalir);

        btnBeneficiario.addActionListener(
                e -> {
                    new FrmGestionBeneficiario()
                            .setVisible(true);
                    dispose();
                }
        );

        btnCuenta.addActionListener(
                e -> {
                    new FrmGestionCuenta()
                            .setVisible(true);
                    dispose();
                }
        );

        btnMovimiento.addActionListener(
                e -> {
                    new FrmGestionMovimiento()
                            .setVisible(true);
                    dispose();
                }
        );

        btnCredito.addActionListener(
                e -> {
                    new FrmGestionCredito()
                            .setVisible(true);
                    dispose();
                }
        );

        btnReportes.addActionListener(
                e -> {
                    new FrmReportesAsesor()
                            .setVisible(true);
                    dispose();
                }
        );
        
        btnSolicitudes.addActionListener(
                e -> {
                    new FrmSolicitudesPendientes()
                            .setVisible(true);
                    dispose();
                }
        );

        btnSalir.addActionListener(
                e -> {
                    Sesion.cerrarSesion();
                    new FrmLogin()
                            .setVisible(true);
                    dispose();
                }
        );
    }
}