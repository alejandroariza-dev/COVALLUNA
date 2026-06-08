package vista;

import com.toedter.calendar.JDateChooser;
import dao.AsociadoDAO;
import modelo.Asociado;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class FrmCrearAsociado extends JFrame {

    private JTextField txtCedula;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtCorreo;
    private JTextField txtDireccion;
    private JTextField txtMunicipio;
    private JTextField txtTelefono;
   

    private JComboBox<String> cbEstado;
    
  
    private JDateChooser dcFechaNacimiento;
    private JDateChooser dcFechaAfiliacion;

    private JButton btnGuardar;
    private JButton btnCancelar;

    private AsociadoDAO asociadoDAO;

    public FrmCrearAsociado() {

        asociadoDAO =
        new AsociadoDAO();

        setTitle(
                "Crear Asociado"
        );

        setSize(
                700,
                650
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
                        "CREAR ASOCIADO"
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

        JLabel lblCedula =
                new JLabel(
                        "Cédula:"
                );

        lblCedula.setBounds(
                50,
                90,
                120,
                25
        );

        add(
                lblCedula
        );

        txtCedula =
                new JTextField();

        txtCedula.setBounds(
                220,
                90,
                200,
                25
        );

        add(
                txtCedula
        );

        JLabel lblNombres =
                new JLabel(
                        "Nombres:"
                );

        lblNombres.setBounds(
                50,
                130,
                120,
                25
        );

        add(
                lblNombres
        );

        txtNombres =
                new JTextField();

        txtNombres.setBounds(
                220,
                130,
                250,
                25
        );

        add(
                txtNombres
        );

        JLabel lblApellidos =
                new JLabel(
                        "Apellidos:"
                );

        lblApellidos.setBounds(
                50,
                170,
                120,
                25
        );

        add(
                lblApellidos
        );

        txtApellidos =
                new JTextField();

        txtApellidos.setBounds(
                220,
                170,
                250,
                25
        );

        add(
                txtApellidos
        );
        
        JLabel lblFechaNacimiento =
                new JLabel(
                        "Fecha Nacimiento:"
                );

        lblFechaNacimiento.setBounds(
                50,
                210,
                150,
                25
        );

        add(
                lblFechaNacimiento
        );

        dcFechaNacimiento =
                new JDateChooser();

        dcFechaNacimiento.setBounds(
                220,
                210,
                200,
                25
        );

        add(
                dcFechaNacimiento
        );
        
        JLabel lblDireccion =
                new JLabel(
                        "Dirección:"
                );

        lblDireccion.setBounds(
                50,
                250,
                120,
                25
        );

        add(
                lblDireccion
        );

        txtDireccion =
                new JTextField();

        txtDireccion.setBounds(
                220,
                250,
                250,
                25
        );

        add(
                txtDireccion
        );
        
        JLabel lblMunicipio =
                new JLabel(
                        "Municipio:"
                );

        lblMunicipio.setBounds(
                50,
                290,
                120,
                25
        );

        add(
                lblMunicipio
        );

        txtMunicipio =
                new JTextField();

        txtMunicipio.setBounds(
                220,
                290,
                250,
                25
        );

        add(
                txtMunicipio
        );
        
        JLabel lblTelefono =
                new JLabel(
                        "Teléfono:"
                );

        lblTelefono.setBounds(
                50,
                330,
                120,
                25
        );

        add(
                lblTelefono
        );

        txtTelefono =
                new JTextField();

        txtTelefono.setBounds(
                220,
                330,
                200,
                25
        );

        add(
                txtTelefono
        );

        JLabel lblCorreo =
                new JLabel(
                        "Correo:"
                );

        lblCorreo.setBounds(
                50,
                370,
                120,
                25
        );

        add(
                lblCorreo
        );

        txtCorreo =
                new JTextField();

        txtCorreo.setBounds(
                220,
                370,
                250,
                25
        );

        add(
                txtCorreo
        );
        
        JLabel lblFechaAfiliacion =
                new JLabel(
                        "Fecha Afiliación:"
                );

        lblFechaAfiliacion.setBounds(
                50,
                410,
                150,
                25
        );

        add(
                lblFechaAfiliacion
        );

        dcFechaAfiliacion =
                new JDateChooser();

        dcFechaAfiliacion.setBounds(
                220,
                410,
                200,
                25
        );

        add(
                dcFechaAfiliacion
        );

        JLabel lblEstado =
                new JLabel(
                        "Estado:"
                );

        lblEstado.setBounds(
                50,
                450,
                120,
                25
        );

        add(
                lblEstado
        );

        cbEstado =
                new JComboBox<>();

        cbEstado.addItem(
                "activo"
        );

        cbEstado.addItem(
                "suspendido"
        );

        cbEstado.addItem(
                "retirado"
        );

        cbEstado.setBounds(
                220,
                450,
                200,
                25
        );

        add(
                cbEstado
        );

        btnGuardar =
                new JButton(
                        "Guardar"
                );

        btnGuardar.setBounds(
                180,
                540,
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
                540,
                120,
                35
        );

        add(
                btnCancelar
        );

        btnGuardar.addActionListener(
                e -> guardarAsociado()
        );

        btnCancelar.addActionListener(
                e -> {

                    new FrmGestionAsociado()
                            .setVisible(true);

                    dispose();
                }
        );
    }

    private void guardarAsociado() {

    try {

        String correo =
                txtCorreo.getText().trim();

        if (!txtCedula.getText().matches("\\d{6,20}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "La cédula debe tener entre 6 y 20 dígitos"
            );

            return;
        }

        if (txtNombres.getText().trim().length() < 2) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese nombres válidos"
            );

            return;
        }

        if (txtApellidos.getText().trim().length() < 2) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese apellidos válidos"
            );

            return;
        }
        
        if (dcFechaNacimiento.getDate() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione fecha de nacimiento"
            );

            return;
        }
        
        if (txtDireccion.getText().trim().length() < 8) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese una dirección completa"
            );

            return;
        }

        if (!txtTelefono.getText().matches("\\d{7,15}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Teléfono inválido"
            );

            return;
        }
        
        if (!correo.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Correo inválido"
            );

            return;
        }

        if (
                dcFechaAfiliacion.getDate()
                        .after(
                                new java.util.Date()
                        )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "La fecha de afiliación no puede ser futura"
            );

            return;
        }

        Asociado asociado =
                new Asociado(
                        txtCedula.getText(),
                        txtNombres.getText(),
                        txtApellidos.getText(),
                        new Date(
                                dcFechaNacimiento
                                        .getDate()
                                        .getTime()
                        ),
                        txtDireccion.getText(),
                        txtMunicipio.getText(),
                        txtTelefono.getText(),
                        correo,
                        new Date(
                                dcFechaAfiliacion
                                        .getDate()
                                        .getTime()
                        ),
                        cbEstado.getSelectedItem()
                                .toString()
                );

        boolean resultado =
                asociadoDAO.insertar(
                        asociado
                );

        if (resultado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Asociado creado correctamente"
            );

            new FrmGestionAsociado()
                    .setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo crear el asociado"
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