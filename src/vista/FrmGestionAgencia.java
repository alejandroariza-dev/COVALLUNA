package vista;

import dao.AgenciaDAO;
import modelo.Agencia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FrmGestionAgencia extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnCrear;
    private JButton btnActualizar;
    private JButton btnVolver;
    private JButton btnEliminar;
    private JButton btnEditar;
    private AgenciaDAO agenciaDAO;

    public FrmGestionAgencia() {

        agenciaDAO =
                new AgenciaDAO();

        setTitle(
                "Gestión de Agencia"
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
                        "GESTIÓN DE AGENCIA"
                );

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        lblTitulo.setBounds(
                30, //Eje X
                150, //Eje Y
                350, //Ancho
                40 //Alto
        );

        add(
                lblTitulo
        );

        btnCrear =
                new JButton(
                        "Crear Agencia"
                );

        btnCrear.setBounds(
                800,
                433,
                129,
                35
        );

        add(
                btnCrear
        );
        
        btnEliminar =
                new JButton(
                        "Eliminar Agencia"
                );

        btnEliminar.setBounds(
                800,
                523,
                129,
                35
        );

        add(
                btnEliminar
        );
        
        btnEditar =
                new JButton(
                        "Editar Agencia"
                );
        
        btnEditar.setBounds(
                800, 
                478, 
                130, 
                35
        );
        
        add(
                btnEditar
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
                800,//Eje X
                613,//Eje Y
                100,//Ancho
                35  //Alto
        );

        add(
                btnVolver
        );

        modelo =
                new DefaultTableModel();

        modelo.addColumn(
                "Código"
        );

        modelo.addColumn(
                "Nombre"
        );

        modelo.addColumn(
                "Municipio"
        );

        modelo.addColumn(
                "Dirección"
        );

        modelo.addColumn(
                "Teléfono"
        );

        modelo.addColumn(
                "Fecha Apertura"
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

        cargarTabla();

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
        
        btnCrear.addActionListener(
                e -> {

            new FrmCrearAgencia()
                    .setVisible(true);

            dispose();
        }     
        );
        
        btnEditar.addActionListener(
                e -> {

                    int fila =
                            tabla.getSelectedRow();

                    if (fila == -1) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Seleccione una agencia"
                        );

                        return;
                    }

                    String codigo =
                            modelo.getValueAt(
                                    fila,
                                    0
                            ).toString();

                    new FrmEditarAgencia(
                            codigo
                    ).setVisible(
                            true
                    );

                    dispose();
                }
        );
        
        btnEliminar.addActionListener(
                e -> eliminarAgencia()
        );
    }

    private void cargarTabla() {

        modelo.setRowCount(
                0
        );

        ArrayList<Agencia> lista =
                agenciaDAO.listar();

        for(
                Agencia agencia
                :
                lista
        ){

            modelo.addRow(
                    new Object[]{
                            agencia.getCodigoAgencia(),
                            agencia.getNombre(),
                            agencia.getMunicipio(),
                            agencia.getDireccion(),
                            agencia.getTelefono(),
                            agencia.getFechaApertura()
                    }
            );
        }
    }
    
    private void eliminarAgencia() {

        int fila =
                tabla.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una agencia"
            );

            return;
        }

        String codigo =
                modelo.getValueAt(
                        fila,
                        0
                ).toString();

        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Desea eliminar la agencia "
                                + codigo
                                + "?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION
                );

        if (opcion == JOptionPane.YES_OPTION) {

            boolean resultado =
                    agenciaDAO.eliminar(
                            codigo
                    );

            if (resultado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Agencia eliminada"
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