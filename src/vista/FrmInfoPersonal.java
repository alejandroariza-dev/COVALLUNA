package vista;

import dao.AsociadoDAO;
import dao.BeneficiarioDAO;
import modelo.Asociado;
import modelo.Beneficiario;
import modelo.Sesion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FrmInfoPersonal extends JFrame {

    public FrmInfoPersonal() {

        AsociadoDAO asociadoDAO =
                new AsociadoDAO();

        BeneficiarioDAO beneficiarioDAO =
                new BeneficiarioDAO();

        String cedula =
                Sesion.getCedulaAsociadoActivo();

        Asociado a =
                asociadoDAO
                        .buscarPorCedulaYEstado(
                                cedula
                        );

        setTitle("Mi Información Personal");

        setSize(700, 680);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(null);

        JLabel lblTitulo =
                new JLabel("MI INFORMACIÓN PERSONAL");

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 20)
        );

        lblTitulo.setBounds(200, 20, 400, 35);

        add(lblTitulo);

        JSeparator sep =
                new JSeparator();

        sep.setBounds(30, 60, 630, 2);

        add(sep);

        int y = 75;
        int gap = 35;

        agregarCampo(
                "Cédula:", a.getCedula(), y
        );

        agregarCampo(
                "Nombres:", a.getNombres(), y += gap
        );

        agregarCampo(
                "Apellidos:", a.getApellidos(), y += gap
        );

        agregarCampo(
                "Fecha Nacimiento:",
                a.getFechaNacimiento().toString(),
                y += gap
        );

        agregarCampo(
                "Dirección:", a.getDireccion(), y += gap
        );

        agregarCampo(
                "Municipio:", a.getMunicipio(), y += gap
        );

        agregarCampo(
                "Teléfono:", a.getTelefono(), y += gap
        );

        agregarCampo(
                "Correo:", a.getCorreo(), y += gap
        );

        agregarCampo(
                "Fecha Afiliación:",
                a.getFechaAfiliacion().toString(),
                y += gap
        );

        agregarCampo(
                "Estado:", a.getEstado(), y += gap
        );

        JLabel lblBenef =
                new JLabel("Mis Beneficiarios:");

        lblBenef.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        lblBenef.setBounds(30, y += gap + 10, 200, 25);

        add(lblBenef);

        DefaultTableModel modeloTabla =
                new DefaultTableModel();

        modeloTabla.addColumn("Documento");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Parentesco");
        modeloTabla.addColumn("Porcentaje");

        ArrayList<Beneficiario> benef =
                beneficiarioDAO
                        .listarPorAsociado(cedula);

        for (Beneficiario b : benef) {

            modeloTabla.addRow(
                    new Object[]{
                            b.getNumeroDocumento(),
                            b.getNombreCompleto(),
                            b.getParentesco(),
                            b.getPorcentajeParticipacion()
                    }
            );
        }

        JTable tabla =
                new JTable(modeloTabla);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(30, y += 30, 630, 120);

        add(scroll);

        JButton btnVolver =
                new JButton("Volver");

        btnVolver.setBounds(
                280, y + 130, 120, 35
        );

        add(btnVolver);

        btnVolver.addActionListener(
                e -> {
                    new FrmAsociado()
                            .setVisible(true);
                    dispose();
                }
        );
    }

    private void agregarCampo(
            String label,
            String valor,
            int y
    ) {

        JLabel lbl = new JLabel(label);

        lbl.setFont(
                new Font("Segoe UI", Font.BOLD, 12)
        );

        lbl.setBounds(30, y, 160, 25);

        add(lbl);

        JLabel val = new JLabel(
                valor != null ? valor : "-"
        );

        val.setBounds(200, y, 400, 25);

        add(val);
    }
}