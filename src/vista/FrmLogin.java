package vista;

import dao.UsuarioDAO;
import modelo.Sesion;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class FrmLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;

    private UsuarioDAO usuarioDAO;

    public FrmLogin() {

        usuarioDAO = new UsuarioDAO();

        setTitle("COOVALLUNA");

        setSize(500, 380);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(null);

        JLabel lblTitulo =
                new JLabel("COOVALLUNA");

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        lblTitulo.setBounds(160, 30, 200, 30);

        add(lblTitulo);

        JLabel lblSubtitulo =
                new JLabel(
                        "Cooperativa de Ahorro y Crédito"
                );

        lblSubtitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        lblSubtitulo.setBounds(155, 60, 280, 20);

        add(lblSubtitulo);

        JSeparator sep = new JSeparator();

        sep.setBounds(50, 90, 380, 2);

        add(sep);

        JLabel lblUsuario =
                new JLabel("Usuario:");

        lblUsuario.setBounds(100, 120, 100, 25);

        add(lblUsuario);

        txtUsuario = new JTextField();

        txtUsuario.setBounds(200, 120, 180, 25);

        add(txtUsuario);

        JLabel lblContrasena =
                new JLabel("Contraseña:");

        lblContrasena.setBounds(100, 170, 100, 25);

        add(lblContrasena);

        txtContrasena = new JPasswordField();

        txtContrasena.setBounds(200, 170, 180, 25);

        add(txtContrasena);

        btnIngresar =
                new JButton("Iniciar Sesión");

        btnIngresar.setBounds(170, 250, 150, 35);

        add(btnIngresar);

        btnIngresar.addActionListener(
                e -> iniciarSesion()
        );
        
        txtContrasena.addActionListener(
                e -> iniciarSesion()
        );
    }

    private void iniciarSesion() {

        String username =
                txtUsuario.getText().trim();

        String password =
                new String(
                        txtContrasena.getPassword()
                );

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese usuario y contraseña"
            );

            return;
        }

        Usuario usuario =
                usuarioDAO.login(
                        username,
                        password
                );

        if (usuario == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario o contraseña incorrectos"
            );

            return;
        }

        Sesion.setUsuarioActivo(usuario);

        switch (usuario.getRol()) {

            case "administrador":
                new FrmAdministrador()
                        .setVisible(true);
                dispose();
                break;

            case "asesor":
                new FrmAsesor()
                        .setVisible(true);
                dispose();
                break;

            case "asociado":
                new FrmAsociado()
                        .setVisible(true);
                dispose();
                break;

            default:
                JOptionPane.showMessageDialog(
                        this,
                        "Rol no reconocido"
                );
        }
    }
}