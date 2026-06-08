package vista;

import dao.MovimientoDAO;
import modelo.Movimiento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class FrmExtractoCuenta extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private MovimientoDAO movimientoDAO;
    private String numeroCuenta;

    private JComboBox<String> cbTipo;
    private JComboBox<String> cbCanal;

    public FrmExtractoCuenta(
            String numeroCuenta
    ) {

        this.numeroCuenta = numeroCuenta;

        movimientoDAO = new MovimientoDAO();

        setTitle(
                "Extracto - Cuenta: "
                + numeroCuenta
        );

        setSize(1000, 660);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(null);

        JLabel lblTitulo =
                new JLabel("EXTRACTO DE CUENTA");

        lblTitulo.setFont(
                new Font("Segoe UI", Font.BOLD, 22)
        );

        lblTitulo.setBounds(350, 20, 350, 35);

        add(lblTitulo);

        JLabel lblCuenta =
                new JLabel(
                        "Cuenta: " + numeroCuenta
                );

        lblCuenta.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        lblCuenta.setBounds(30, 60, 300, 25);

        add(lblCuenta);

        JLabel lblTipo =
                new JLabel("Tipo:");

        lblTipo.setBounds(30, 100, 60, 25);

        add(lblTipo);

        cbTipo = new JComboBox<>();

        cbTipo.addItem("Todos");
        cbTipo.addItem("deposito");
        cbTipo.addItem("retiro");
        cbTipo.addItem("transferencia_entrante");
        cbTipo.addItem("transferencia_saliente");

        cbTipo.setBounds(90, 100, 200, 25);

        add(cbTipo);

        JLabel lblCanal =
                new JLabel("Canal:");

        lblCanal.setBounds(310, 100, 60, 25);

        add(lblCanal);

        cbCanal = new JComboBox<>();

        cbCanal.addItem("Todos");
        cbCanal.addItem("agencia");
        cbCanal.addItem("app_movil");
        cbCanal.addItem("cajero");

        cbCanal.setBounds(375, 100, 150, 25);

        add(cbCanal);

        JButton btnFiltrar =
                new JButton("Filtrar");

        btnFiltrar.setBounds(
                540, 100, 100, 25
        );

        add(btnFiltrar);

        JButton btnLimpiar =
                new JButton("Limpiar");

        btnLimpiar.setBounds(
                650, 100, 100, 25
        );

        add(btnLimpiar);

        modelo = new DefaultTableModel();

        modelo.addColumn("N° Transacción");
        modelo.addColumn("Tipo");
        modelo.addColumn("Valor");
        modelo.addColumn("Fecha y Hora");
        modelo.addColumn("Canal");

        tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        scroll.setBounds(30, 140, 930, 350);

        add(scroll);

        JLabel lblSaldoTxt =
                new JLabel("Saldo actual:");

        lblSaldoTxt.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        lblSaldoTxt.setBounds(30, 505, 120, 25);

        add(lblSaldoTxt);

        JLabel lblSaldoValor =
                new JLabel(
                        String.format(
                                "$%.2f",
                                movimientoDAO
                                        .calcularSaldo(
                                                numeroCuenta
                                        )
                        )
                );

        lblSaldoValor.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        lblSaldoValor.setBounds(
                160, 505, 200, 25
        );

        add(lblSaldoValor);

        JButton btnDescargarPDF =
                new JButton("Descargar PDF");

        btnDescargarPDF.setBounds(
                30, 545, 160, 35
        );

        add(btnDescargarPDF);

        JButton btnDescargarCSV =
                new JButton("Descargar CSV");

        btnDescargarCSV.setBounds(
                200, 545, 160, 35
        );

        add(btnDescargarCSV);

        JButton btnVolver =
                new JButton("Volver");

        btnVolver.setBounds(
                840, 545, 120, 35
        );

        add(btnVolver);

        cargarMovimientos();

        btnFiltrar.addActionListener(
                e -> filtrar()
        );

        btnLimpiar.addActionListener(
                e -> {
                    cbTipo.setSelectedIndex(0);
                    cbCanal.setSelectedIndex(0);
                    cargarMovimientos();
                }
        );

        btnVolver.addActionListener(
                e -> {
                    new FrmMisCuentas()
                            .setVisible(true);
                    dispose();
                }
        );
        
        btnDescargarPDF.addActionListener(
                e -> descargarPDF()
        );

        btnDescargarCSV.addActionListener(
                e -> descargarCSV()
        );
    }

    private void cargarMovimientos() {

        modelo.setRowCount(0);

        ArrayList<Movimiento> lista =
                movimientoDAO.listarPorCuenta(
                        numeroCuenta
                );

        for (Movimiento m : lista) {

            modelo.addRow(
                    new Object[]{
                            m.getNumeroTransaccion(),
                            m.getTipoMovimiento(),
                            String.format(
                                    "$%.2f", m.getValor()
                            ),
                            m.getFechaHora(),
                            m.getCanal()
                    }
            );
        }
    }

    private void filtrar() {

        String tipoFiltro =
                cbTipo.getSelectedItem()
                        .toString();

        String canalFiltro =
                cbCanal.getSelectedItem()
                        .toString();

        modelo.setRowCount(0);

        ArrayList<Movimiento> lista =
                movimientoDAO.listarPorCuenta(
                        numeroCuenta
                );

        for (Movimiento m : lista) {

            boolean coincideTipo =
                    tipoFiltro.equals("Todos")
                    ||
                    m.getTipoMovimiento()
                            .equals(tipoFiltro);

            boolean coincideCanal =
                    canalFiltro.equals("Todos")
                    ||
                    m.getCanal()
                            .equals(canalFiltro);

            if (coincideTipo && coincideCanal) {

                modelo.addRow(
                        new Object[]{
                                m.getNumeroTransaccion(),
                                m.getTipoMovimiento(),
                                String.format(
                                        "$%.2f",
                                        m.getValor()
                                ),
                                m.getFechaHora(),
                                m.getCanal()
                        }
                );
            }
        }
    }
    
    private void descargarPDF() {

        if (modelo.getRowCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "No hay datos para exportar"
            );

            return;
        }

        JFileChooser fileChooser =
                new JFileChooser();

        fileChooser.setSelectedFile(
                new java.io.File(
                        "Extracto_"
                        + numeroCuenta
                        + ".pdf"
                )
        );

        int opcion =
                fileChooser.showSaveDialog(this);

        if (opcion != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String ruta =
                fileChooser.getSelectedFile()
                        .getAbsolutePath();

        if (!ruta.endsWith(".pdf")) {
            ruta += ".pdf";
        }

        try {

            Document documento =
                    new Document();

            PdfWriter.getInstance(
                    documento,
                    new FileOutputStream(ruta)
            );

            documento.open();
            
            com.itextpdf.text.Font fuenteTitulo =
                new com.itextpdf.text.Font(
                        com.itextpdf.text.Font.FontFamily.HELVETICA,
                        16,
                        com.itextpdf.text.Font.BOLD
                );

            Paragraph titulo =
                    new Paragraph(
                            "COOVALLUNA - Extracto de Cuenta",
                            fuenteTitulo
                    );

            titulo.setAlignment(
                    Paragraph.ALIGN_CENTER
            );

            documento.add(titulo);

            documento.add(
                    new Paragraph(
                            "Cuenta: " + numeroCuenta
                    )
            );

            documento.add(
                    new Paragraph(" ")
            );
            
            PdfPTable tablaPDF =
                    new PdfPTable(5);

            tablaPDF.setWidthPercentage(100);
            
            String[] encabezados = {
                    "N° Transacción",
                    "Tipo",
                    "Valor",
                    "Fecha y Hora",
                    "Canal"
            };

            com.itextpdf.text.Font fuenteEncabezado =
                new com.itextpdf.text.Font(
                        com.itextpdf.text.Font.FontFamily.HELVETICA,
                        10,
                        com.itextpdf.text.Font.BOLD,
                        BaseColor.WHITE
                );

            for (String enc : encabezados) {

                PdfPCell celda =
                        new PdfPCell(
                                new Paragraph(
                                        enc,
                                        fuenteEncabezado
                                )
                        );

                celda.setBackgroundColor(
                        BaseColor.DARK_GRAY
                );

                tablaPDF.addCell(celda);
            }
            
            com.itextpdf.text.Font fuenteFila =
                new com.itextpdf.text.Font(
                        com.itextpdf.text.Font.FontFamily.HELVETICA,
                        9
                );

            for (int i = 0; i < modelo.getRowCount(); i++) {

                for (int j = 0; j < modelo.getColumnCount(); j++) {

                    tablaPDF.addCell(
                            new Paragraph(
                                    modelo.getValueAt(i, j)
                                            .toString(),
                                    fuenteFila
                            )
                    );
                }
            }

            documento.add(tablaPDF);

            documento.add(
                    new Paragraph(" ")
            );
            
            com.itextpdf.text.Font fuenteSaldo =
                new com.itextpdf.text.Font(
                        com.itextpdf.text.Font.FontFamily.HELVETICA,
                        12,
                        com.itextpdf.text.Font.BOLD
                );

            double saldo =
                    movimientoDAO
                            .calcularSaldo(numeroCuenta);

            documento.add(
                    new Paragraph(
                            "Saldo actual: "
                            + String.format(
                                    "$%.2f", saldo
                            ),
                            fuenteSaldo
                    )
            );

            documento.close();

            JOptionPane.showMessageDialog(
                    this,
                    "PDF guardado correctamente en:\n"
                    + ruta
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al generar PDF: "
                    + ex.getMessage()
            );
        }
    }

    private void descargarCSV() {

        if (modelo.getRowCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "No hay datos para exportar"
            );

            return;
        }

        JFileChooser fileChooser =
                new JFileChooser();

        fileChooser.setSelectedFile(
                new java.io.File(
                        "Extracto_"
                        + numeroCuenta
                        + ".csv"
                )
        );

        int opcion =
                fileChooser.showSaveDialog(this);

        if (opcion != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String ruta =
                fileChooser.getSelectedFile()
                        .getAbsolutePath();

        if (!ruta.endsWith(".csv")) {
            ruta += ".csv";
        }

        try {

            FileWriter fw =
                    new FileWriter(ruta);
            
            fw.write(
                    "N° Transacción,Tipo," +
                    "Valor,Fecha y Hora,Canal\n"
            );
            
            for (int i = 0;
                    i < modelo.getRowCount();
                    i++) {

                StringBuilder fila =
                        new StringBuilder();

                for (int j = 0;
                        j < modelo.getColumnCount();
                        j++) {

                    fila.append(
                            modelo.getValueAt(i, j)
                                    .toString()
                    );

                    if (j < modelo.getColumnCount() - 1) {
                        fila.append(",");
                    }
                }

                fw.write(fila.toString() + "\n");
            }
            
            double saldo =
                    movimientoDAO
                            .calcularSaldo(numeroCuenta);

            fw.write(
                    "\nSaldo actual,"
                    + String.format("$%.2f", saldo)
                    + "\n"
            );

            fw.close();

            JOptionPane.showMessageDialog(
                    this,
                    "CSV guardado correctamente en:\n"
                    + ruta
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al generar CSV: "
                    + ex.getMessage()
            );
        }
    }
}