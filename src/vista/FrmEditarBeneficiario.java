package vista;

import dao.AsociadoDAO;
import dao.BeneficiarioDAO;
import modelo.Asociado;
import modelo.Beneficiario;

import javax.swing.*;
import java.awt.*;

public class FrmEditarBeneficiario extends JFrame {

    private JTextField txtDocumento;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtPorcentaje;
    

    private JComboBox<String> cbParentesco;
    private JComboBox<String> cbAsociado;

    private JButton btnGuardar;
    private JButton btnCancelar;

    private BeneficiarioDAO beneficiarioDAO;
    private AsociadoDAO asociadoDAO;
    
    private String documento;

    public FrmEditarBeneficiario(
            String documento
    ) {

        this.documento =
                documento;

        beneficiarioDAO =
                new BeneficiarioDAO();

        asociadoDAO =
                new AsociadoDAO();

        setTitle(
                "Editar Beneficiario"
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
                        "CREAR BENEFICIARIO"
                );

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        lblTitulo.setBounds(
                180,
                20,
                350,
                40
        );

        add(
                lblTitulo
        );

        JLabel lblDocumento =
                new JLabel(
                        "Documento:"
                );

        lblDocumento.setBounds(
                50,
                100,
                120,
                25
        );

        add(
                lblDocumento
        );

        txtDocumento =
                new JTextField();

        txtDocumento.setBounds(
                220,
                100,
                200,
                25
        );

        add(
                txtDocumento
        );
        
        txtDocumento.setEditable(
                false
        );

        JLabel lblNombre =
                new JLabel(
                        "Nombre Completo:"
                );

        lblNombre.setBounds(
                50,
                150,
                150,
                25
        );

        add(
                lblNombre
        );

        txtNombre =
                new JTextField();

        txtNombre.setBounds(
                220,
                150,
                300,
                25
        );

        add(
                txtNombre
        );

        JLabel lblParentesco =
                new JLabel(
                        "Parentesco:"
                );

        lblParentesco.setBounds(
                50,
                200,
                120,
                25
        );

        add(
                lblParentesco
        );

        cbParentesco =
                new JComboBox<>();

        cbParentesco.addItem(
                "Padre"
        );

        cbParentesco.addItem(
                "Madre"
        );

        cbParentesco.addItem(
                "Hijo"
        );

        cbParentesco.addItem(
                "Hija"
        );

        cbParentesco.addItem(
                "Hermano"
        );

        cbParentesco.addItem(
                "Hermana"
        );

        cbParentesco.addItem(
                "Cónyuge"
        );

        cbParentesco.addItem(
                "Otro"
        );

        cbParentesco.setBounds(
                220,
                200,
                200,
                25
        );

        add(
                cbParentesco
        );

        JLabel lblPorcentaje =
                new JLabel(
                        "Porcentaje:"
                );

        lblPorcentaje.setBounds(
                50,
                250,
                120,
                25
        );

        add(
                lblPorcentaje
        );

        txtPorcentaje =
                new JTextField();

        txtPorcentaje.setBounds(
                220,
                250,
                120,
                25
        );

        add(
                txtPorcentaje
        );

        JLabel lblTelefono =
                new JLabel(
                        "Teléfono:"
                );

        lblTelefono.setBounds(
                50,
                300,
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
                300,
                200,
                25
        );

        add(
                txtTelefono
        );

        JLabel lblAsociado =
                new JLabel(
                        "Asociado:"
                );

        lblAsociado.setBounds(
                50,
                350,
                120,
                25
        );

        add(
                lblAsociado
        );

        cbAsociado =
                new JComboBox<>();

        for (
                Asociado asociado
                :
                asociadoDAO.listar()
        ) {

            cbAsociado.addItem(
                    asociado.getCedula()
            );
        }

        cbAsociado.setBounds(
                220,
                350,
                200,
                25
        );

        add(
                cbAsociado
        );

        btnGuardar =
                new JButton(
                        "Guardar"
                );

        btnGuardar.setBounds(
                180,
                500,
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
                500,
                120,
                35
        );

        add(
                btnCancelar
        );

        btnGuardar.addActionListener(
                e -> actualizarBeneficiario()
        );

        btnCancelar.addActionListener(
                e -> {

                    new FrmGestionBeneficiario()
                            .setVisible(true);

                    dispose();
                }
        );
        
        cargarDatos();
}
    
    private void cargarDatos() {

        Beneficiario beneficiario =
                beneficiarioDAO.buscarPorDocumento(
                        documento
                );

        if (beneficiario == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Beneficiario no encontrado"
            );

            return;
        }

        txtDocumento.setText(
                beneficiario.getNumeroDocumento()
        );

        txtNombre.setText(
                beneficiario.getNombreCompleto()
        );

        txtTelefono.setText(
                beneficiario.getTelefono()
        );

        txtPorcentaje.setText(
                String.valueOf(
                        beneficiario.getPorcentajeParticipacion()
                )
        );

        cbParentesco.setSelectedItem(
                beneficiario.getParentesco()
        );

        cbAsociado.setSelectedItem(
                beneficiario.getCedulaAsociado()
        );
    }

    private void actualizarBeneficiario() {

        try {

            if (txtNombre.getText()
                    .trim()
                    .length() < 5) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese un nombre válido"
                );

                return;
            }

            if (!txtTelefono.getText()
                    .matches("\\d{7,15}")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese un teléfono válido"
                );

                return;
            }

            double porcentaje =
                    Double.parseDouble(
                            txtPorcentaje.getText()
                    );

            if (
                    porcentaje <= 0
                    ||
                    porcentaje > 100
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "El porcentaje debe estar entre 1 y 100"
                );

                return;
            }

            Beneficiario beneficiario =
                    new Beneficiario(
                            txtDocumento.getText(),
                            txtNombre.getText(),
                            cbParentesco.getSelectedItem()
                                    .toString(),
                            porcentaje,
                            txtTelefono.getText(),
                            cbAsociado.getSelectedItem()
                                    .toString()
                    );

            boolean resultado =
                    beneficiarioDAO.actualizar(
                            beneficiario
                    );

            if (resultado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Beneficiario actualizado correctamente"
                );

                new FrmGestionBeneficiario()
                        .setVisible(true);

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo actualizar"
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "El porcentaje debe ser numérico"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Datos inválidos"
            );
        }
    }
}
    
