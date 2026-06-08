package vista;

import javax.swing.*;
import java.awt.*;

import modelo.Sesion;

public class FrmAdministrador extends JFrame {

    private JButton btnAgencia;

    private JButton btnEmpleado;

    private JButton btnAsociado;

    private JButton btnSalir;

    public FrmAdministrador() {

        setTitle(
                "Administrador"
        );

        setSize(
                500,
                530
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
                        "Panel Administrador"
                );

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        lblTitulo.setBounds(
                130,
                30,
                250,
                30
        );

        add(
                lblTitulo
        );

        btnAgencia =
                new JButton(
                        "Gestión de Agencia"
                );

        btnAgencia.setBounds(
                130,
                100,
                220,
                40
        );

        add(
                btnAgencia
        );

        btnEmpleado =
                new JButton(
                        "Gestión de Empleados"
                );

        btnEmpleado.setBounds(
                130,
                160,
                220,
                40
        );

        add(
                btnEmpleado
        );

        btnAsociado =
                new JButton(
                        "Gestión de Asociados"
                );

        btnAsociado.setBounds(
                130,
                220,
                220,
                40
        );

        add(
                btnAsociado
        );

        btnSalir =
                new JButton(
                        "Cerrar Sesión"
                );

        btnSalir.setBounds(
                130,
                400,
                220,
                40
        );

        add(
                btnSalir
        );

        btnAgencia.addActionListener(
                e -> {
                    new FrmGestionAgencia()
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
        
        btnEmpleado.addActionListener(
                e -> {

                    new FrmGestionEmpleado()
                            .setVisible(true);

                    dispose();
                }
        );
        
        btnAsociado.addActionListener(
                e -> {
                    new FrmGestionAsociado()
                            .setVisible(true);
                    dispose();
                }
        );

        JButton btnUsuarios =
                new JButton(
                        "Gestión de Usuarios"
                );

        btnUsuarios.setBounds(
                130,
                280,
                220,
                40
        );

        add(btnUsuarios);

        btnUsuarios.addActionListener(
                e -> {
                    new FrmGestionUsuarios()
                            .setVisible(true);
                    dispose();
                }
        );

        JButton btnReportes =
                new JButton(
                        "Reportes"
                );

        btnReportes.setBounds(
                130,
                340,
                220,
                40
        );

        add(btnReportes);

        btnReportes.addActionListener(
                e -> {
                    new FrmReportes()
                            .setVisible(true);
                    dispose();
                }
        );
        
    }
}