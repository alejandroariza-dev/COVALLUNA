package vista;

import dao.BeneficiarioDAO;
import modelo.Beneficiario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FrmGestionBeneficiario extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnCrear;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnActualizar;
    private JButton btnVolver;

    private BeneficiarioDAO beneficiarioDAO;

    public FrmGestionBeneficiario() {

        beneficiarioDAO =
                new BeneficiarioDAO();

        setTitle(
                "Gestión Beneficiarios"
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
                        "GESTIÓN DE BENEFICIARIOS"
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

        btnCrear =
                new JButton(
                        "Crear Beneficiario"
                );

        btnCrear.setBounds(
                800,
                433,
                170,
                35
        );

        add(
                btnCrear
        );

        btnEditar =
                new JButton(
                        "Editar Beneficiario"
                );

        btnEditar.setBounds(
                800,
                478,
                170,
                35
        );

        add(
                btnEditar
        );

        btnEliminar =
                new JButton(
                        "Eliminar Beneficiario"
                );

        btnEliminar.setBounds(
                800,
                523,
                170,
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
                170,
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
                120,
                35
        );

        add(
                btnVolver
        );

        modelo =
                new DefaultTableModel();

        modelo.addColumn(
                "Documento"
        );

        modelo.addColumn(
                "Nombre"
        );

        modelo.addColumn(
                "Parentesco"
        );

        modelo.addColumn(
                "Porcentaje"
        );

        modelo.addColumn(
                "Teléfono"
        );

        modelo.addColumn(
                "Cédula Asociado"
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

                    new FrmAsesor()
                            .setVisible(true);

                    dispose();
                }
        );

        btnCrear.addActionListener(
                e -> {

                    new FrmCrearBeneficiario()
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
                                "Seleccione un beneficiario"
                        );

                        return;
                    }

                    String documento =
                            modelo.getValueAt(
                                    fila,
                                    0
                            ).toString();

                    new FrmEditarBeneficiario(
                            documento
                    ).setVisible(
                            true
                    );

                    dispose();
                }
        );

        btnEliminar.addActionListener(
                e -> eliminarBeneficiario()
        );
    }

    private void cargarTabla() {

        modelo.setRowCount(
                0
        );

        ArrayList<Beneficiario> lista =
                beneficiarioDAO.listar();

        for (
                Beneficiario beneficiario
                :
                lista
        ) {

            modelo.addRow(
                    new Object[]{
                            beneficiario.getNumeroDocumento(),
                            beneficiario.getNombreCompleto(),
                            beneficiario.getParentesco(),
                            beneficiario.getPorcentajeParticipacion(),
                            beneficiario.getTelefono(),
                            beneficiario.getCedulaAsociado()
                    }
            );
        }
    }

    private void eliminarBeneficiario() {

        int fila =
                tabla.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un beneficiario"
            );

            return;
        }

        String documento =
                modelo.getValueAt(
                        fila,
                        0
                ).toString();

        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Desea eliminar el beneficiario "
                                + documento
                                + "?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION
                );

        if (opcion == JOptionPane.YES_OPTION) {

            boolean resultado =
                    beneficiarioDAO.eliminar(
                            documento
                    );

            if (resultado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Beneficiario eliminado"
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