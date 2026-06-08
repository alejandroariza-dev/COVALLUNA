package vista;

import dao.AsociadoDAO;
import dao.SolicitudActualizacionDAO;
import modelo.Asociado;
import modelo.Sesion;
import modelo.SolicitudActualizacion;

import javax.swing.*;
import java.awt.*;

public class FrmSolicitarActualizacion extends JFrame {

    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTextField txtDireccion;

    public FrmSolicitarActualizacion() {

        AsociadoDAO asociadoDAO =
                new AsociadoDAO();

        SolicitudActualizacionDAO solicitudDAO =
                new SolicitudActualizacionDAO();

        String cedula =
                Sesion.getCedulaAsociadoActivo();

        Asociado a =
                asociadoDAO
                        .buscarPorCedulaYEstado(
                                cedula
                        );

        setTitle(
                "Solicitar Actualización de Datos"
        );

        setSize(600, 450);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(null);

        JLabel lblTitulo =
                new JLabel(
                        "SOLICITAR ACTUALIZACIÓN"
                );

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 20)
        );

        lblTitulo.setBounds(130, 20, 380, 35);

        add(lblTitulo);

        JLabel lblInfo =
                new JLabel(
                        "Los cambios quedarán " +
                        "pendientes de aprobación."
                );

        lblInfo.setFont(
                new Font(
                        "Segoe UI",
                        Font.ITALIC,
                        11
                )
        );

        lblInfo.setBounds(100, 55, 400, 20);

        add(lblInfo);

        JLabel lblTelefono =
                new JLabel("Nuevo Teléfono:");

        lblTelefono.setBounds(50, 100, 150, 25);

        add(lblTelefono);

        txtTelefono = new JTextField(
                a != null
                        ? a.getTelefono()
                        : ""
        );

        txtTelefono.setBounds(220, 100, 250, 25);

        add(txtTelefono);

        JLabel lblCorreo =
                new JLabel("Nuevo Correo:");

        lblCorreo.setBounds(50, 150, 150, 25);

        add(lblCorreo);

        txtCorreo = new JTextField(
                a != null
                        ? a.getCorreo()
                        : ""
        );

        txtCorreo.setBounds(220, 150, 250, 25);

        add(txtCorreo);

        JLabel lblDireccion =
                new JLabel("Nueva Dirección:");

        lblDireccion.setBounds(50, 200, 150, 25);

        add(lblDireccion);

        txtDireccion = new JTextField(
                a != null
                        ? a.getDireccion()
                        : ""
        );

        txtDireccion.setBounds(220, 200, 250, 25);

        add(txtDireccion);

        JButton btnEnviar =
                new JButton("Enviar Solicitud");

        btnEnviar.setBounds(150, 320, 160, 35);

        add(btnEnviar);

        JButton btnCancelar =
                new JButton("Cancelar");

        btnCancelar.setBounds(340, 320, 120, 35);

        add(btnCancelar);

        btnEnviar.addActionListener(
                e -> {

                    String telefono =
                            txtTelefono
                                    .getText().trim();

                    String correo =
                            txtCorreo
                                    .getText().trim();

                    String direccion =
                            txtDireccion
                                    .getText().trim();

                    if (telefono.isEmpty()
                            && correo.isEmpty()
                            && direccion.isEmpty()) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Ingrese al menos un " +
                                "dato a actualizar"
                        );

                        return;
                    }

                    SolicitudActualizacion s =
                            new SolicitudActualizacion();

                    s.setCedulaAsociado(cedula);
                    s.setNuevoTelefono(telefono);
                    s.setNuevoCorreo(correo);
                    s.setNuevaDireccion(direccion);

                    boolean resultado =
                            solicitudDAO.insertar(s);

                    if (resultado) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Solicitud enviada " +
                                "correctamente. Un asesor " +
                                "revisará los cambios."
                        );

                        new FrmAsociado()
                                .setVisible(true);

                        dispose();

                    } else {

                        JOptionPane.showMessageDialog(
                                this,
                                "No se pudo enviar " +
                                "la solicitud"
                        );
                    }
                }
        );

        btnCancelar.addActionListener(
                e -> {
                    new FrmAsociado()
                            .setVisible(true);
                    dispose();
                }
        );
    }
}