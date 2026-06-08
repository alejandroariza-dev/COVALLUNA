package vista;

import dao.UsuarioDAO;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FrmGestionUsuarios extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private UsuarioDAO usuarioDAO;

    private JButton btnCrear;
    private JButton btnCambiarPassword;
    private JButton btnEliminar;
    private JButton btnActualizar;
    private JButton btnVolver;

    public FrmGestionUsuarios() {

        usuarioDAO = new UsuarioDAO();

        setTitle("Gestión de Usuarios");

        setSize(1110, 700);

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

        lblLogo.setBounds(30, 30, 250, 40);

        add(lblLogo);

        JSeparator sep = new JSeparator();

        sep.setBounds(30, 80, 1040, 2);

        add(sep);

        JLabel lblTitulo =
                new JLabel("GESTIÓN DE USUARIOS");

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 24)
        );

        lblTitulo.setBounds(30, 100, 400, 40);

        add(lblTitulo);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Usuario");
        modelo.addColumn("Rol");
        modelo.addColumn("Cédula Asociada");

        tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(30, 200, 760, 450);

        add(scroll);

        btnCrear =
                new JButton("Crear Usuario");

        btnCrear.setBounds(800, 433, 170, 35);

        add(btnCrear);

        btnCambiarPassword =
                new JButton("Cambiar Contraseña");

        btnCambiarPassword.setBounds(
                800, 478, 170, 35
        );

        add(btnCambiarPassword);

        btnEliminar =
                new JButton("Eliminar Usuario");

        btnEliminar.setBounds(800, 523, 170, 35);

        add(btnEliminar);

        btnActualizar =
                new JButton("Actualizar Tabla");

        btnActualizar.setBounds(800, 568, 170, 35);

        add(btnActualizar);

        btnVolver =
                new JButton("Volver");

        btnVolver.setBounds(800, 613, 120, 35);

        add(btnVolver);

        cargarTabla();

        btnActualizar.addActionListener(
                e -> cargarTabla()
        );

        btnCrear.addActionListener(
                e -> mostrarFormCrear()
        );

        btnCambiarPassword.addActionListener(
                e -> cambiarPassword()
        );

        btnEliminar.addActionListener(
                e -> eliminarUsuario()
        );

        btnVolver.addActionListener(
                e -> {
                    new FrmAdministrador()
                            .setVisible(true);
                    dispose();
                }
        );
    }

    private void cargarTabla() {

        modelo.setRowCount(0);

        ArrayList<Usuario> lista =
                usuarioDAO.listar();

        for (Usuario u : lista) {

            modelo.addRow(
                    new Object[]{
                            u.getIdUsuario(),
                            u.getUsername(),
                            u.getRol(),
                            u.getCedula() != null
                                    ? u.getCedula()
                                    : "-"
                    }
            );
        }
    }

    private void mostrarFormCrear() {

        JTextField txtUsername =
                new JTextField();

        JPasswordField txtPassword =
                new JPasswordField();

        JComboBox<String> cbRol =
                new JComboBox<>();

        cbRol.addItem("administrador");
        cbRol.addItem("asesor");
        cbRol.addItem("asociado");

        JTextField txtCedula =
                new JTextField();

        Object[] campos = {
                "Usuario:", txtUsername,
                "Contraseña:", txtPassword,
                "Rol:", cbRol,
                "Cédula (solo asociado/asesor):",
                txtCedula
        };

        int opcion = JOptionPane.showConfirmDialog(
                this,
                campos,
                "Crear Usuario",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcion != JOptionPane.OK_OPTION) {
            return;
        }

        String username =
                txtUsername.getText().trim();

        String password =
                new String(
                        txtPassword.getPassword()
                ).trim();

        String rol =
                cbRol.getSelectedItem().toString();

        String cedula =
                txtCedula.getText().trim();

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario y contraseña " +
                    "son obligatorios"
            );

            return;
        }

        if (rol.equals("asesor")) {

            if (cedula.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Un asesor debe tener " +
                        "una cédula asociada"
                );

                return;
            }

            if (!usuarioDAO.existeCedulaEnEmpleado(
                    cedula
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "La cédula no existe " +
                        "en la tabla de empleados. " +
                        "Registre primero al empleado."
                );

                return;
            }
        }

        if (rol.equals("asociado")) {

            if (cedula.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Un asociado debe tener " +
                        "una cédula asociada"
                );

                return;
            }

            if (!usuarioDAO.existeCedulaEnAsociado(
                    cedula
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "La cédula no existe " +
                        "en la tabla de asociados. " +
                        "Registre primero al asociado."
                );

                return;
            }
        }

        Usuario u = new Usuario();

        u.setUsername(username);
        u.setPassword(password);
        u.setRol(rol);
        u.setCedula(
                cedula.isEmpty() ? null : cedula
        );

        boolean resultado =
                usuarioDAO.insertar(u);

        if (resultado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario creado correctamente"
            );

            cargarTabla();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo crear el usuario. " +
                    "Verifique que el nombre " +
                    "no esté en uso."
            );
        }
    }

    private void cambiarPassword() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un usuario"
            );

            return;
        }

        int idUsuario =
                (int) modelo.getValueAt(fila, 0);

        String username =
                modelo.getValueAt(
                        fila, 1
                ).toString();

        JPasswordField txtNueva =
                new JPasswordField();

        Object[] campos = {
                "Nueva contraseña para "
                + username + ":",
                txtNueva
        };

        int opcion = JOptionPane.showConfirmDialog(
                this,
                campos,
                "Cambiar Contraseña",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcion != JOptionPane.OK_OPTION) {
            return;
        }

        String nueva =
                new String(
                        txtNueva.getPassword()
                ).trim();

        if (nueva.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese una contraseña válida"
            );

            return;
        }

        boolean resultado =
                usuarioDAO.cambiarPassword(
                        idUsuario,
                        nueva
                );

        if (resultado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Contraseña actualizada"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo actualizar"
            );
        }
    }

    private void eliminarUsuario() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un usuario"
            );

            return;
        }

        String username =
                modelo.getValueAt(
                        fila, 1
                ).toString();

        if (username.equals("admin")) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se puede eliminar " +
                    "el usuario admin"
            );

            return;
        }

        int idUsuario =
                (int) modelo.getValueAt(fila, 0);

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar usuario: "
                        + username + "?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {

            boolean resultado =
                    usuarioDAO.eliminar(idUsuario);

            if (resultado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Usuario eliminado"
                );

                cargarTabla();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo eliminar"
                );
            }
        }
    }
}