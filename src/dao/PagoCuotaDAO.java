package dao;

import conexion.Conexion;
import modelo.PagoCuota;

import java.sql.*;
import java.util.ArrayList;

public class PagoCuotaDAO {

    public boolean insertar(
            PagoCuota pago
    ) {

        String sql =
                "INSERT INTO pago_cuota " +
                "(id_pago, numero_cuota, fecha_pago, " +
                "valor_pagado, estado_pago, numero_radicado) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, pago.getIdPago());
            ps.setInt(2, pago.getNumeroCuota());
            ps.setDate(3, pago.getFechaPago());
            ps.setDouble(4, pago.getValorPagado());
            ps.setString(5, pago.getEstadoPago());
            ps.setString(6, pago.getNumeroRadicado());

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

    public ArrayList<PagoCuota> listarPorCredito(
            String numeroRadicado
    ) {

        ArrayList<PagoCuota> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM pago_cuota " +
                "WHERE numero_radicado = ? " +
                "ORDER BY numero_cuota ASC";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, numeroRadicado);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                PagoCuota p = new PagoCuota();

                p.setIdPago(
                        rs.getInt("id_pago")
                );

                p.setNumeroCuota(
                        rs.getInt("numero_cuota")
                );

                p.setFechaPago(
                        rs.getDate("fecha_pago")
                );

                p.setValorPagado(
                        rs.getDouble("valor_pagado")
                );

                p.setEstadoPago(
                        rs.getString("estado_pago")
                );

                p.setNumeroRadicado(
                        rs.getString("numero_radicado")
                );

                lista.add(p);
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return lista;
    }

    public int generarIdPago() {

        String sql =
                "SELECT COUNT(*) AS total " +
                "FROM pago_cuota";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                int total =
                        rs.getInt("total");

                con.close();

                return total + 1;
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return 1;
    }
}