package dao;

import conexion.Conexion;
import modelo.Credito;
import modelo.Garantia;

import java.sql.*;
import java.util.ArrayList;

public class CreditoDAO {

    public boolean insertar(
            Credito credito
    ) {

        String sql =
                "INSERT INTO credito " +
                "(numero_radicado, valor_solicitado, valor_aprobado, " +
                "plazo_meses, tasa_interes_mensual, fecha_aprobacion, " +
                "fecha_primer_vencimiento, estado_credito, " +
                "cedula_asociado, id_linea, codigo_agencia) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, credito.getNumeroRadicado());
            ps.setDouble(2, credito.getValorSolicitado());
            ps.setDouble(3, credito.getValorAprobado());
            ps.setInt(4, credito.getPlazoMeses());
            ps.setDouble(5, credito.getTasaInteresMensual());
            ps.setDate(6, credito.getFechaAprobacion());
            ps.setDate(7, credito.getFechaPrimerVencimiento());
            ps.setString(8, credito.getEstadoCredito());
            ps.setString(9, credito.getCedulaAsociado());
            ps.setInt(10, credito.getIdLinea());
            ps.setString(11, credito.getCodigoAgencia());

            int filas =
                    ps.executeUpdate();

            con.close();

            return filas > 0;

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );

            return false;
        }
    }

    public ArrayList<Credito> listar() {

        ArrayList<Credito> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM credito " +
                "ORDER BY fecha_aprobacion DESC";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Credito c = new Credito();

                c.setNumeroRadicado(
                        rs.getString("numero_radicado")
                );

                c.setValorSolicitado(
                        rs.getDouble("valor_solicitado")
                );

                c.setValorAprobado(
                        rs.getDouble("valor_aprobado")
                );

                c.setPlazoMeses(
                        rs.getInt("plazo_meses")
                );

                c.setTasaInteresMensual(
                        rs.getDouble("tasa_interes_mensual")
                );

                c.setFechaAprobacion(
                        rs.getDate("fecha_aprobacion")
                );

                c.setFechaPrimerVencimiento(
                        rs.getDate("fecha_primer_vencimiento")
                );

                c.setEstadoCredito(
                        rs.getString("estado_credito")
                );

                c.setCedulaAsociado(
                        rs.getString("cedula_asociado")
                );

                c.setIdLinea(
                        rs.getInt("id_linea")
                );

                c.setCodigoAgencia(
                        rs.getString("codigo_agencia")
                );

                lista.add(c);
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return lista;
    }

    public Credito buscarPorRadicado(
            String numeroRadicado
    ) {

        String sql =
                "SELECT * FROM credito " +
                "WHERE numero_radicado = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, numeroRadicado);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                Credito c = new Credito();

                c.setNumeroRadicado(
                        rs.getString("numero_radicado")
                );

                c.setValorSolicitado(
                        rs.getDouble("valor_solicitado")
                );

                c.setValorAprobado(
                        rs.getDouble("valor_aprobado")
                );

                c.setPlazoMeses(
                        rs.getInt("plazo_meses")
                );

                c.setTasaInteresMensual(
                        rs.getDouble("tasa_interes_mensual")
                );

                c.setFechaAprobacion(
                        rs.getDate("fecha_aprobacion")
                );

                c.setFechaPrimerVencimiento(
                        rs.getDate("fecha_primer_vencimiento")
                );

                c.setEstadoCredito(
                        rs.getString("estado_credito")
                );

                c.setCedulaAsociado(
                        rs.getString("cedula_asociado")
                );

                c.setIdLinea(
                        rs.getInt("id_linea")
                );

                c.setCodigoAgencia(
                        rs.getString("codigo_agencia")
                );

                con.close();

                return c;
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return null;
    }
    
    public ArrayList<Credito> listarPorAsociado(
            String cedulaAsociado
    ) {

        ArrayList<Credito> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM credito " +
                "WHERE cedula_asociado = ?";

        try {

            Connection con = Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, cedulaAsociado);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Credito c = new Credito();

                c.setNumeroRadicado(
                        rs.getString("numero_radicado")
                );

                c.setValorSolicitado(
                        rs.getDouble("valor_solicitado")
                );

                c.setValorAprobado(
                        rs.getDouble("valor_aprobado")
                );

                c.setPlazoMeses(
                        rs.getInt("plazo_meses")
                );

                c.setTasaInteresMensual(
                        rs.getDouble(
                                "tasa_interes_mensual"
                        )
                );

                c.setFechaAprobacion(
                        rs.getDate("fecha_aprobacion")
                );

                c.setFechaPrimerVencimiento(
                        rs.getDate(
                                "fecha_primer_vencimiento"
                        )
                );

                c.setEstadoCredito(
                        rs.getString("estado_credito")
                );

                c.setCedulaAsociado(
                        rs.getString("cedula_asociado")
                );

                c.setIdLinea(
                        rs.getInt("id_linea")
                );

                c.setCodigoAgencia(
                        rs.getString("codigo_agencia")
                );

                lista.add(c);
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return lista;
    }
    
    public ArrayList<Garantia> listarGarantias() {

        ArrayList<Garantia> lista =
                new ArrayList<>();

        String sql =
                "SELECT g.numero_radicado, " +
                "c.cedula_asociado AS titular, " +
                "g.cedula_codeudor, " +
                "g.fecha_firma_pagare, " +
                "c.valor_aprobado, " +
                "c.estado_credito " +
                "FROM garantiza g " +
                "JOIN credito c " +
                "ON g.numero_radicado = " +
                "c.numero_radicado";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Garantia g = new Garantia();

                g.setNumeroRadicado(
                        rs.getString("numero_radicado")
                );

                g.setCedulaTitular(
                        rs.getString("titular")
                );

                g.setCedulaCodeudor(
                        rs.getString("cedula_codeudor")
                );

                g.setFechaFirmaPagare(
                        rs.getDate("fecha_firma_pagare")
                );

                g.setValorAprobado(
                        rs.getDouble("valor_aprobado")
                );

                g.setEstadoCredito(
                        rs.getString("estado_credito")
                );

                lista.add(g);
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return lista;
    }
    
}