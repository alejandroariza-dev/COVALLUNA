package vista;

import dao.AsociadoDAO;
import modelo.Asociado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FrmGestionAsociado extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnCrear;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnActualizar;
    private JButton btnVolver;

    private AsociadoDAO asociadoDAO;

    public FrmGestionAsociado() {

        asociadoDAO =
                new AsociadoDAO();

        setTitle(
                "Gestión de Asociados"
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

        add(
                lblLogo
        );

        JSeparator separador =
                new JSeparator();

        separador.setBounds(
                30,
                80,
                1040,
                2
        );

        add(
                separador
        );

        JLabel lblTitulo =
                new JLabel(
                        "GESTIÓN DE ASOCIADOS"
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

        add(
                lblTitulo
        );

        modelo =
                new DefaultTableModel();

        modelo.addColumn(
                "Cédula"
        );

        modelo.addColumn(
                "Nombres"
        );

        modelo.addColumn(
                "Apellidos"
        );

        modelo.addColumn(
                "Municipio"
        );

        modelo.addColumn(
                "Teléfono"
        );

        modelo.addColumn(
                "Correo"
        );

        modelo.addColumn(
                "Estado"
        );

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

        add(
                scroll
        );

        btnCrear =
                new JButton(
                        "Crear Asociado"
                );

        btnCrear.setBounds(
                800,
                433,
                140,
                35
        );

        add(
                btnCrear
        );

        btnEditar =
                new JButton(
                        "Editar Asociado"
                );

        btnEditar.setBounds(
                800,
                478,
                140,
                35
        );

        add(
                btnEditar
        );

        btnEliminar =
                new JButton(
                        "Eliminar Asociado"
                );

        btnEliminar.setBounds(
                800,
                523,
                140,
                35
        );

        add(
                btnEliminar
        );

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

        add(
                btnActualizar
        );

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

        add(
                btnVolver
        );

        cargarTabla();
        
        btnCrear.addActionListener(
                e -> {

                    new FrmCrearAsociado()
                            .setVisible(true);

                    dispose();
                }
        );
        
        btnEliminar.addActionListener(
                e -> eliminarAsociado()
        );
        
        btnEditar.addActionListener(
                e -> {

                    int fila =
                            tabla.getSelectedRow();

                    if (fila == -1) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Seleccione un asociado"
                        );

                        return;
                    }

                    String cedula =
                            modelo.getValueAt(
                                    fila,
                                    0
                            ).toString();

                    new FrmEditarAsociado(
                            cedula
                    ).setVisible(
                            true
                    );

                    dispose();
                }
        );

        btnActualizar.addActionListener(
                e -> cargarTabla()
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

        ArrayList<Asociado> lista =
                asociadoDAO.listar();

        for (
                Asociado asociado
                :
                lista
        ) {

            modelo.addRow(
                    new Object[]{
                            asociado.getCedula(),
                            asociado.getNombres(),
                            asociado.getApellidos(),
                            asociado.getMunicipio(),
                            asociado.getTelefono(),
                            asociado.getCorreo(),
                            asociado.getEstado()
                    }
            );
        }
    }
    
    private void eliminarAsociado() {

        int fila =
                tabla.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un asociado"
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
                        "¿Desea eliminar el asociado "
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
                    asociadoDAO.eliminar(
                            cedula
                    );

            if (
                    resultado
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Asociado eliminado"
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