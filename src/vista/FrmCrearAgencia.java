package vista;

import com.toedter.calendar.JDateChooser;
import dao.AgenciaDAO;
import modelo.Agencia;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class FrmCrearAgencia extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtMunicipio;
    private JTextField txtDireccion;
    private JTextField txtTelefono;

    private JDateChooser dcFecha;

    private JButton btnGuardar;
    private JButton btnCancelar;

    private AgenciaDAO agenciaDAO;

    public FrmCrearAgencia() {

        agenciaDAO =
                new AgenciaDAO();

        setTitle(
                "Crear Agencia"
        );

        setSize(
                600,
                500
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
                        "CREAR AGENCIA"
                );

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        lblTitulo.setBounds(
                180,
                20,
                250,
                30
        );

        add(
                lblTitulo
        );

        JLabel lblCodigo =
                new JLabel(
                        "Código Agencia:"
                );

        lblCodigo.setBounds(
                50,
                80,
                120,
                25
        );

        add(
                lblCodigo
        );

        txtCodigo =
                new JTextField();

        txtCodigo.setBounds(
                200,
                80,
                200,
                25
        );

        txtCodigo.setEditable(
                false
        );

        txtCodigo.setText(
                agenciaDAO.generarCodigo()
        );

        add(
                txtCodigo
        );

        JLabel lblNombre =
                new JLabel(
                        "Nombre:"
                );

        lblNombre.setBounds(
                50,
                130,
                120,
                25
        );

        add(
                lblNombre
        );

        txtNombre =
                new JTextField();

        txtNombre.setBounds(
                200,
                130,
                250,
                25
        );

        add(
                txtNombre
        );

        JLabel lblMunicipio =
                new JLabel(
                        "Municipio:"
                );

        lblMunicipio.setBounds(
                50,
                180,
                120,
                25
        );

        add(
                lblMunicipio
        );

        txtMunicipio =
                new JTextField();

        txtMunicipio.setBounds(
                200,
                180,
                250,
                25
        );

        add(
                txtMunicipio
        );

        JLabel lblDireccion =
                new JLabel(
                        "Dirección:"
                );

        lblDireccion.setBounds(
                50,
                230,
                120,
                25
        );

        add(
                lblDireccion
        );

        txtDireccion =
                new JTextField();

        txtDireccion.setBounds(
                200,
                230,
                250,
                25
        );

        add(
                txtDireccion
        );

        JLabel lblTelefono =
                new JLabel(
                        "Teléfono:"
                );

        lblTelefono.setBounds(
                50,
                280,
                120,
                25
        );

        add(
                lblTelefono
        );

        txtTelefono =
                new JTextField();

        txtTelefono.setBounds(
                200,
                280,
                250,
                25
        );

        add(
                txtTelefono
        );

        JLabel lblFecha =
                new JLabel(
                        "Fecha Apertura:"
                );

        lblFecha.setBounds(
                50,
                330,
                120,
                25
        );

        add(
                lblFecha
        );

        dcFecha =
                new JDateChooser();

        dcFecha.setBounds(
                200,
                330,
                200,
                25
        );

        add(
                dcFecha
        );

        btnGuardar =
                new JButton(
                        "Guardar"
                );

        btnGuardar.setBounds(
                150,
                400,
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
                300,
                400,
                120,
                35
        );

        add(
                btnCancelar
        );

        btnGuardar.addActionListener(
                e -> guardarAgencia()
        );

        btnCancelar.addActionListener(
                e -> {

                    new FrmGestionAgencia()
                            .setVisible(true);

                    dispose();
                }
        );
    }

    private void guardarAgencia() {

        String nombre =
                txtNombre.getText().trim();

        String municipio =
                txtMunicipio.getText().trim();

        String direccion =
                txtDireccion.getText().trim();

        String telefono =
                txtTelefono.getText().trim();

        if (nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese el nombre"
            );

            return;
        }

        if (municipio.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese el municipio"
            );

            return;
        }

        if (direccion.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese la dirección"
            );

            return;
        }

        if (!telefono.matches("\\d{10}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "El teléfono debe tener 10 dígitos"
            );

            return;
        }

        if (dcFecha.getDate() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una fecha"
            );

            return;
        }

        Date fecha =
                new Date(
                        dcFecha.getDate()
                                .getTime()
                );

        Agencia agencia =
                new Agencia(
                        txtCodigo.getText(),
                        nombre,
                        direccion,
                        municipio,
                        telefono,
                        fecha
                );

        boolean resultado =
                agenciaDAO.insertar(
                        agencia
                );

        if (resultado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Agencia creada correctamente"
            );

            new FrmGestionAgencia()
                    .setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al crear la agencia"
            );
        }
    }
}