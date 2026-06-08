package vista;

import com.toedter.calendar.JDateChooser;
import dao.AgenciaDAO;
import dao.EmpleadoDAO;
import dao.TipoEmpleadoDAO;
import modelo.Agencia;
import modelo.Empleado;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Date;

public class FrmEditarEmpleado extends JFrame {

    private String cedulaEmpleado;
    private JTextField txtCedula;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtSalario;
    private JTextField txtCorreo;
    private JTextField txtSupervisor;

    private JComboBox<String> cbEstado;
    private JComboBox<String> cbAgencia;
    private JComboBox<String> cbTipo;

    private JDateChooser dcFechaIngreso;

    private JButton btnGuardar;
    private JButton btnCancelar;

    private EmpleadoDAO empleadoDAO;
    private AgenciaDAO agenciaDAO;
    private TipoEmpleadoDAO tipoDAO;

    public FrmEditarEmpleado(
            String cedula
    ) {

        this.cedulaEmpleado =
                cedula;

        empleadoDAO =
                new EmpleadoDAO();

        agenciaDAO =
                new AgenciaDAO();

        tipoDAO =
                new TipoEmpleadoDAO();

        setTitle(
                "Editar Empleado"
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
                        "EDITAR EMPLEADO"
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
        
        txtCedula.setEditable(
                false
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

        JLabel lblFecha =
                new JLabel(
                        "Fecha Ingreso:"
                );

        lblFecha.setBounds(
                50,
                210,
                120,
                25
        );

        add(
                lblFecha
        );

        dcFechaIngreso =
                new JDateChooser();

        dcFechaIngreso.setBounds(
                220,
                210,
                200,
                25
        );

        add(
                dcFechaIngreso
        );

        JLabel lblSalario =
                new JLabel(
                        "Salario Base:"
                );

        lblSalario.setBounds(
                50,
                250,
                120,
                25
        );

        add(
                lblSalario
        );

        txtSalario =
                new JTextField();

        txtSalario.setBounds(
                220,
                250,
                200,
                25
        );

        add(
                txtSalario
        );

        JLabel lblCorreo =
                new JLabel(
                        "Correo:"
                );

        lblCorreo.setBounds(
                50,
                290,
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
                290,
                250,
                25
        );

        add(
                txtCorreo
        );

        JLabel lblEstado =
                new JLabel(
                        "Estado:"
                );

        lblEstado.setBounds(
                50,
                330,
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
                "en_licencia"
        );

        cbEstado.addItem(
                "retirado"
        );

        cbEstado.setBounds(
                220,
                330,
                200,
                25
        );

        add(
                cbEstado
        );

        JLabel lblAgencia =
                new JLabel(
                        "Agencia:"
                );

        lblAgencia.setBounds(
                50,
                370,
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
                370,
                200,
                25
        );

        add(
                cbAgencia
        );

        JLabel lblTipo =
                new JLabel(
                        "Tipo:"
                );

        lblTipo.setBounds(
                50,
                410,
                120,
                25
        );

        add(
                lblTipo
        );

        cbTipo =
                new JComboBox<>();

        for (
                String tipo
                :
                tipoDAO.obtenerNombresTipo()
        ) {

            cbTipo.addItem(
                    tipo
            );
        }

        cbTipo.setBounds(
                220,
                410,
                200,
                25
        );

        add(
                cbTipo
        );

        JLabel lblSupervisor =
                new JLabel(
                        "Supervisor:"
                );

        lblSupervisor.setBounds(
                50,
                450,
                120,
                25
        );

        add(
                lblSupervisor
        );

        txtSupervisor =
                new JTextField();

        txtSupervisor.setBounds(
                220,
                450,
                200,
                25
        );

        add(
                txtSupervisor
        );

        btnGuardar =
                new JButton(
                        "Guardar Cambios"
                );

        btnGuardar.setBounds(
                180,
                530,
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
                530,
                120,
                35
        );

        add(
                btnCancelar
        );

        btnGuardar.addActionListener(
                e -> actualizarEmpleado()
        );

        btnCancelar.addActionListener(
                e -> {

                    new FrmGestionEmpleado()
                            .setVisible(true);

                    dispose();
                }
        );
        
        cargarEmpleado();
    }
    
    private void cargarEmpleado() {

        Empleado empleado =
                empleadoDAO.buscarPorCedula(
                        cedulaEmpleado
                );

        if (empleado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Empleado no encontrado"
            );

            dispose();

            return;
        }

        txtCedula.setText(
                empleado.getCedula()
        );

        txtNombres.setText(
                empleado.getNombres()
        );

        txtApellidos.setText(
                empleado.getApellidos()
        );

        dcFechaIngreso.setDate(
                empleado.getFechaIngreso()
        );

        txtSalario.setText(
                empleado.getSalarioBase()
                        .toString()
        );

        txtCorreo.setText(
                empleado.getCorreoCorporativo()
        );

        cbEstado.setSelectedItem(
                empleado.getEstadoLaboral()
        );

        cbAgencia.setSelectedItem(
                empleado.getCodigoAgencia()
        );

        cbTipo.setSelectedItem(
                tipoDAO.obtenerNombreTipo(
                        empleado.getIdTipo()
                )
        );

        if (
                empleado.getCedulaSupervisor()
                        != null
        ) {

            txtSupervisor.setText(
                    empleado.getCedulaSupervisor()
            );
        }
    }

    private void actualizarEmpleado(){

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
        
        if (txtNombres.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese los nombres"
            );

            return;
        }
        
        if (txtApellidos.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese los apellidos"
            );

            return;
        }
        
        if (dcFechaIngreso.getDate()
                        .after(
                                new java.util.Date()
                        )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "La fecha de ingreso no puede ser futura"
            );

            return;
        }
        
        if (dcFechaIngreso.getDate() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una fecha de ingreso"
            );

            return;
        }
        
        if (!txtSalario.getText().matches("\\d{6,8}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un salario válido"
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

        String supervisor =
                txtSupervisor.getText().trim();

        if (
                supervisor.isEmpty()
        ) {

            supervisor = null;
        }

        Empleado empleado =
                new Empleado(
                        txtCedula.getText(),
                        txtNombres.getText(),
                        txtApellidos.getText(),
                        new Date(
                                dcFechaIngreso
                                        .getDate()
                                        .getTime()
                        ),
                        new BigDecimal(
                                txtSalario.getText()
                        ),
                        correo,
                        cbEstado.getSelectedItem()
                                .toString(),
                        cbAgencia.getSelectedItem()
                                .toString(),
                        tipoDAO.obtenerIdPorNombre(
                                cbTipo.getSelectedItem()
                                        .toString()
                        ),
                        supervisor
                );

            boolean resultado =
                empleadoDAO.actualizar(
                        empleado
                );

            if (
                    resultado
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Empleado actualizado correctamente"
                );

                new FrmGestionEmpleado()
                        .setVisible(true);

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo crear el empleado"
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