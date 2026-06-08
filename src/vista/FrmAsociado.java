package vista;

import modelo.Sesion;

import javax.swing.*;
import java.awt.*;

public class FrmAsociado extends JFrame {

    public FrmAsociado() {

        setTitle("Portal Asociado");

        setSize(500, 450);

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

        lblLogo.setBounds(155, 30, 250, 40);

        add(lblLogo);

        JSeparator separador =
                new JSeparator();

        separador.setBounds(30, 80, 430, 2);

        add(separador);

        JLabel lblBienvenida =
                new JLabel(
                        "Bienvenido: "
                        + Sesion.getCedulaAsociadoActivo()
                );

        lblBienvenida.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        lblBienvenida.setBounds(30, 90, 400, 25);

        add(lblBienvenida);

        JLabel lblTitulo =
                new JLabel("PANEL ASOCIADO");

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 20)
        );

        lblTitulo.setBounds(150, 120, 250, 35);

        add(lblTitulo);

        JButton btnInfoPersonal =
                new JButton("Mi Información Personal");

        btnInfoPersonal.setBounds(
                130, 175, 220, 35
        );

        add(btnInfoPersonal);

        JButton btnMisCuentas =
                new JButton("Mis Cuentas de Ahorro");

        btnMisCuentas.setBounds(
                130, 220, 220, 35
        );

        add(btnMisCuentas);

        JButton btnMisCreditos =
                new JButton("Mis Créditos");

        btnMisCreditos.setBounds(
                130, 265, 220, 35
        );

        add(btnMisCreditos);

        JButton btnActualizarDatos =
                new JButton("Solicitar Actualización");

        btnActualizarDatos.setBounds(
                130, 310, 220, 35
        );

        add(btnActualizarDatos);

        JButton btnCerrarSesion =
                new JButton("Cerrar Sesión");

        btnCerrarSesion.setBounds(
                130, 360, 220, 35
        );

        add(btnCerrarSesion);

        btnInfoPersonal.addActionListener(
                e -> {
                    new FrmInfoPersonal()
                            .setVisible(true);
                    dispose();
                }
        );

        btnMisCuentas.addActionListener(
                e -> {
                    new FrmMisCuentas()
                            .setVisible(true);
                    dispose();
                }
        );

        btnMisCreditos.addActionListener(
                e -> {
                    new FrmMisCreditos()
                            .setVisible(true);
                    dispose();
                }
        );

        btnActualizarDatos.addActionListener(
                e -> {
                    new FrmSolicitarActualizacion()
                            .setVisible(true);
                    dispose();
                }
        );

        btnCerrarSesion.addActionListener(
                e -> {
                    Sesion.cerrarSesion();
                    new FrmLogin()
                            .setVisible(true);
                    dispose();
                }
        );
    }
}