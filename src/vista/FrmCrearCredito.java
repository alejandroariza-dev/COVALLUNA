package vista;


import dao.AgenciaDAO;
import dao.AsociadoDAO;
import dao.CreditoDAO;
import modelo.Agencia;
import modelo.Asociado;
import modelo.Credito;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

import com.toedter.calendar.JDateChooser;

public class FrmCrearCredito extends JFrame {

    private JTextField txtNumeroRadicado;
    private JTextField txtValorSolicitado;
    private JTextField txtValorAprobado;
    private JTextField txtPlazoMeses;
    private JTextField txtTasaInteres;
    
    private JDateChooser dcFechaAprobacion;
    private JDateChooser dcFechaPrimerVencimiento;

    private JComboBox<String> cbEstado;
    private JComboBox<String> cbAsociado;
    private JComboBox<String> cbLinea;
    private JComboBox<String> cbAgencia;

    private JButton btnGuardar;
    private JButton btnCancelar;

    private CreditoDAO creditoDAO;
    private AsociadoDAO asociadoDAO;
    private AgenciaDAO agenciaDAO;

    public FrmCrearCredito() {

        creditoDAO = new CreditoDAO();
        asociadoDAO = new AsociadoDAO();
        agenciaDAO = new AgenciaDAO();

        setTitle("Radicar Crédito");

        setSize(700, 750);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(null);

        JLabel lblTitulo =
                new JLabel("RADICAR CRÉDITO");

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 24)
        );

        lblTitulo.setBounds(200, 20, 350, 40);

        add(lblTitulo);

        JLabel lblRadicado =
                new JLabel("N° Radicado:");

        lblRadicado.setBounds(50, 90, 150, 25);

        add(lblRadicado);

        txtNumeroRadicado = new JTextField();

        txtNumeroRadicado.setBounds(
                220, 90, 200, 25
        );

        add(txtNumeroRadicado);

        JLabel lblValorSol =
                new JLabel("Valor Solicitado:");

        lblValorSol.setBounds(50, 135, 150, 25);

        add(lblValorSol);

        txtValorSolicitado = new JTextField();

        txtValorSolicitado.setBounds(
                220, 135, 200, 25
        );

        add(txtValorSolicitado);

        JLabel lblValorAprob =
                new JLabel("Valor Aprobado:");

        lblValorAprob.setBounds(50, 180, 150, 25);

        add(lblValorAprob);

        txtValorAprobado = new JTextField();

        txtValorAprobado.setBounds(
                220, 180, 200, 25
        );

        add(txtValorAprobado);

        JLabel lblPlazo =
                new JLabel("Plazo (meses):");

        lblPlazo.setBounds(50, 225, 150, 25);

        add(lblPlazo);

        txtPlazoMeses = new JTextField();

        txtPlazoMeses.setBounds(
                220, 225, 100, 25
        );

        add(txtPlazoMeses);

        JLabel lblTasa =
                new JLabel("Tasa Interés (%):");

        lblTasa.setBounds(50, 270, 150, 25);

        add(lblTasa);

        txtTasaInteres = new JTextField();

        txtTasaInteres.setBounds(
                220, 270, 100, 25
        );

        add(txtTasaInteres);

        JLabel lblFechaAprob =
                new JLabel("Fecha Aprobación:");

        lblFechaAprob.setBounds(50, 315, 160, 25);

        add(lblFechaAprob);

        dcFechaAprobacion = new JDateChooser();

        dcFechaAprobacion.setDateFormatString(
                "yyyy-MM-dd"
        );

        dcFechaAprobacion.setBounds(
                220, 315, 180, 25
        );

        add(dcFechaAprobacion);

        JLabel lblFechaVenc =
                new JLabel("Primer Vencimiento:");

        lblFechaVenc.setBounds(50, 360, 165, 25);

        add(lblFechaVenc);

        dcFechaPrimerVencimiento = new JDateChooser();

        dcFechaPrimerVencimiento.setDateFormatString(
                "yyyy-MM-dd"
        );

        dcFechaPrimerVencimiento.setBounds(
                220, 360, 180, 25
        );

        add(dcFechaPrimerVencimiento);

        JLabel lblEstado =
                new JLabel("Estado:");

        lblEstado.setBounds(50, 405, 150, 25);

        add(lblEstado);

        cbEstado = new JComboBox<>();

        cbEstado.addItem("en_estudio");
        cbEstado.addItem("aprobado");
        cbEstado.addItem("desembolsado");
        cbEstado.addItem("al_dia");
        cbEstado.addItem("en_mora");
        cbEstado.addItem("cancelado");
        cbEstado.addItem("castigado");

        cbEstado.setBounds(220, 405, 180, 25);

        add(cbEstado);

        JLabel lblLinea =
                new JLabel("Línea Crédito:");

        lblLinea.setBounds(50, 450, 150, 25);

        add(lblLinea);

        cbLinea = new JComboBox<>();

        cbLinea.addItem("1 - Libre Inversión");
        cbLinea.addItem("2 - Vivienda");
        cbLinea.addItem("3 - Agropecuario");
        cbLinea.addItem("4 - Educativo");
        cbLinea.addItem("5 - Empresarial");

        cbLinea.setBounds(220, 450, 200, 25);

        add(cbLinea);

        JLabel lblAsociado =
                new JLabel("Asociado:");

        lblAsociado.setBounds(50, 495, 150, 25);

        add(lblAsociado);

        cbAsociado = new JComboBox<>();

        for (Asociado a : asociadoDAO.listar()) {

            cbAsociado.addItem(
                    a.getCedula()
                            + " - "
                            + a.getNombres()
                            + " "
                            + a.getApellidos()
            );
        }

        cbAsociado.setBounds(220, 495, 280, 25);

        add(cbAsociado);

        JLabel lblAgencia =
                new JLabel("Agencia:");

        lblAgencia.setBounds(50, 540, 150, 25);

        add(lblAgencia);

        cbAgencia = new JComboBox<>();

        for (Agencia ag : agenciaDAO.listar()) {

            cbAgencia.addItem(
                    ag.getCodigoAgencia()
                            + " - "
                            + ag.getNombre()
            );
        }

        cbAgencia.setBounds(220, 540, 280, 25);

        add(cbAgencia);

        btnGuardar = new JButton("Guardar");

        btnGuardar.setBounds(180, 640, 120, 35);

        add(btnGuardar);

        btnCancelar = new JButton("Cancelar");

        btnCancelar.setBounds(340, 640, 120, 35);

        add(btnCancelar);

        btnGuardar.addActionListener(
                e -> guardarCredito()
        );

        btnCancelar.addActionListener(
                e -> {
                    new FrmGestionCredito()
                            .setVisible(true);
                    dispose();
                }
        );
    }

    private void guardarCredito() {

        try {

            String radicado =
                    txtNumeroRadicado
                            .getText().trim();

            if (radicado.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese el número de radicado"
                );

                return;
            }

            double valorSolicitado =
                    Double.parseDouble(
                            txtValorSolicitado
                                    .getText().trim()
                    );

            double valorAprobado =
                    Double.parseDouble(
                            txtValorAprobado
                                    .getText().trim()
                    );

            int plazoMeses =
                    Integer.parseInt(
                            txtPlazoMeses
                                    .getText().trim()
                    );

            double tasa =
                    Double.parseDouble(
                            txtTasaInteres
                                    .getText().trim()
                    );

            if (dcFechaAprobacion.getDate() == null) {

    JOptionPane.showMessageDialog(
            this,
            "Seleccione la fecha de aprobación"
    );

    return;
}

if (dcFechaPrimerVencimiento.getDate() == null) {

    JOptionPane.showMessageDialog(
            this,
            "Seleccione la fecha de primer vencimiento"
    );

    return;
}

        Date fechaAprobacion =
                new Date(
                        dcFechaAprobacion
                                .getDate()
                                .getTime()
                );

        Date fechaVencimiento =
                new Date(
                        dcFechaPrimerVencimiento
                                .getDate()
                                .getTime()
                );

            String estado =
                    cbEstado.getSelectedItem()
                            .toString();
            
            String lineaSeleccionada =
                    cbLinea.getSelectedItem()
                            .toString();

            int idLinea =
                    Integer.parseInt(
                            lineaSeleccionada
                                    .split(" - ")[0]
                                    .trim()
                    );
            
            String asociadoSeleccionado =
                    cbAsociado.getSelectedItem()
                            .toString();

            String cedulaAsociado =
                    asociadoSeleccionado
                            .split(" - ")[0]
                            .trim();
            
            String agenciaSeleccionada =
                    cbAgencia.getSelectedItem()
                            .toString();

            String codigoAgencia =
                    agenciaSeleccionada
                            .split(" - ")[0]
                            .trim();

            Credito credito = new Credito(
                    radicado,
                    valorSolicitado,
                    valorAprobado,
                    plazoMeses,
                    tasa,
                    fechaAprobacion,
                    fechaVencimiento,
                    estado,
                    cedulaAsociado,
                    idLinea,
                    codigoAgencia
            );

            boolean resultado =
                    creditoDAO.insertar(credito);

            if (resultado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Crédito radicado correctamente"
                );

                new FrmGestionCredito()
                        .setVisible(true);

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo radicar el crédito"
                );
            }

        } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Verifique que los valores " +
                "numéricos sean correctos"
        );

        } catch (IllegalArgumentException e) {

        JOptionPane.showMessageDialog(
                this,
                "Formato de fecha incorrecto. " +
                "Use AAAA-MM-DD"
        );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }
}