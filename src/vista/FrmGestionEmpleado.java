package vista;

import dao.EmpleadoDAO;
import dao.TipoEmpleadoDAO;
import modelo.Empleado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FrmGestionEmpleado extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnCrear;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnActualizar;
    private JButton btnVolver;

    private EmpleadoDAO empleadoDAO;
    private TipoEmpleadoDAO tipoDAO;

    public FrmGestionEmpleado() {

        empleadoDAO =
                new EmpleadoDAO();

        tipoDAO =
                new TipoEmpleadoDAO();

        setTitle(
                "Gestión de Empleados"
        );

        setSize(
                1110,
                700
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

        JLabel lblLogo =
                new JLabel(
                        "COOVALLUNA"
                );

        lblLogo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        lblLogo.setBounds(
                30,
                30,
                250,
                40
        );

        add(lblLogo);

        JSeparator separador =
                new JSeparator();

        separador.setBounds(
                30,
                80,
                1040,
                2
        );

        add(separador);

        JLabel lblTitulo =
                new JLabel(
                        "GESTIÓN DE EMPLEADOS"
                );

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        lblTitulo.setBounds(
                30,
                150,
                400,
                40
        );

        add(lblTitulo);

        btnCrear =
                new JButton(
                        "Crear Empleado"
                );

        btnCrear.setBounds(
                800,
                433,
                140,
                35
        );

        add(btnCrear);

        btnEditar =
                new JButton(
                        "Editar Empleado"
                );

        btnEditar.setBounds(
                800,
                478,
                140,
                35
        );

        add(btnEditar);

        btnEliminar =
                new JButton(
                        "Eliminar Empleado"
                );

        btnEliminar.setBounds(
                800,
                523,
                140,
                35
        );

        add(btnEliminar);

        btnActualizar =
                new JButton(
                        "Actualizar Tabla"
                );

        btnActualizar.setBounds(
                800,
                568,
                140,
                35
        );

        add(btnActualizar);

        btnVolver =
                new JButton(
                        "Volver"
                );

        btnVolver.setBounds(
                800,
                613,
                100,
                35
        );

        add(btnVolver);

        modelo =
                new DefaultTableModel();

        modelo.addColumn("Cédula");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Correo");
        modelo.addColumn("Estado");
        modelo.addColumn("Agencia");
        modelo.addColumn("Tipo");

        tabla =
                new JTable(
                        modelo
                );

        JScrollPane scroll =
                new JScrollPane(
                        tabla
                );

        scroll.setBounds(
                30,
                200,
                760,
                450
        );

        add(scroll);

        cargarTabla();

        btnActualizar.addActionListener(
                e -> cargarTabla()
        );
        
        btnCrear.addActionListener(
                e -> {

                    new FrmCrearEmpleado()
                            .setVisible(true);

                    dispose();
                }
        );
        
        btnEliminar.addActionListener(
                e -> eliminarEmpleado()
        );
        
        btnEditar.addActionListener(
                e -> {

                    int fila =
                            tabla.getSelectedRow();

                    if (fila == -1) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Seleccione un empleado"
                        );

                        return;
                    }

                    String cedula =
                            modelo.getValueAt(
                                    fila,
                                    0
                            ).toString();

                    new FrmEditarEmpleado(
                            cedula
                    ).setVisible(
                            true
                    );

                    dispose();
                }
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

        modelo.setRowCount(
                0
        );

        ArrayList<Empleado> lista =
                empleadoDAO.listar();

        for (
                Empleado empleado
                :
                lista
        ) {

            modelo.addRow(
                    new Object[]{
                            empleado.getCedula(),
                            empleado.getNombres(),
                            empleado.getApellidos(),
                            empleado.getCorreoCorporativo(),
                            empleado.getEstadoLaboral(),
                            empleado.getCodigoAgencia(),
                            tipoDAO.obtenerNombreTipo(
                                    empleado.getIdTipo()
                            )
                    }
            );
        }
    }
    
    private void eliminarEmpleado() {

        int fila =
                tabla.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un empleado"
            );

            return;
        }

        String cedula =
                modelo.getValueAt(
                        fila,
                        0
                ).toString();

        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Desea eliminar el empleado "
                                + cedula
                                + "?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                opcion ==
                JOptionPane.YES_OPTION
        ) {

            boolean resultado =
                    empleadoDAO.eliminar(
                            cedula
                    );

            if (
                    resultado
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Empleado eliminado"
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