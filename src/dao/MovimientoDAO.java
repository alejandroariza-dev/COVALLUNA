package dao;

import conexion.Conexion;
import modelo.Movimiento;

import java.sql.*;
import java.util.ArrayList;

public class MovimientoDAO {

    public boolean insertar(
            Movimiento movimiento
    ) {

        String sql =
                "INSERT INTO movimiento " +
                "(numero_transaccion, tipo_movimiento, valor, " +
                "fecha_hora, canal, numero_cuenta) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    movimiento.getNumeroTransaccion()
            );

            ps.setString(
                    2,
                    movimiento.getTipoMovimiento()
            );

            ps.setDouble(
                    3,
                    movimiento.getValor()
            );

            ps.setTimestamp(
                    4,
                    movimiento.getFechaHora()
            );

            ps.setString(
                    5,
                    movimiento.getCanal()
            );

            ps.setString(
                    6,
                    movimiento.getNumeroCuenta()
            );

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

    public ArrayList<Movimiento> listar() {

        ArrayList<Movimiento> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM movimiento " +
                "ORDER BY fecha_hora DESC";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Movimiento m =
                        new Movimiento();

                m.setNumeroTransaccion(
                        rs.getString(
                                "numero_transaccion"
                        )
                );

                m.setTipoMovimiento(
                        rs.getString(
                                "tipo_movimiento"
                        )
                );

                m.setValor(
                        rs.getDouble(
                                "valor"
                        )
                );

                m.setFechaHora(
                        rs.getTimestamp(
                                "fecha_hora"
                        )
                );

                m.setCanal(
                        rs.getString(
                                "canal"
                        )
                );

                m.setNumeroCuenta(
                        rs.getString(
                                "numero_cuenta"
                        )
                );

                lista.add(m);
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return lista;
    }

    public ArrayList<Movimiento> listarPorCuenta(
            String numeroCuenta
    ) {

        ArrayList<Movimiento> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM movimiento " +
                "WHERE numero_cuenta = ? " +
                "ORDER BY fecha_hora DESC";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    numeroCuenta
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Movimiento m =
                        new Movimiento();

                m.setNumeroTransaccion(
                        rs.getString(
                                "numero_transaccion"
                        )
                );

                m.setTipoMovimiento(
                        rs.getString(
                                "tipo_movimiento"
                        )
                );

                m.setValor(
                        rs.getDouble(
                                "valor"
                        )
                );

                m.setFechaHora(
                        rs.getTimestamp(
                                "fecha_hora"
                        )
                );

                m.setCanal(
                        rs.getString(
                                "canal"
                        )
                );

                m.setNumeroCuenta(
                        rs.getString(
                                "numero_cuenta"
                        )
                );

                lista.add(m);
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return lista;
    }

    public double calcularSaldo(
            String numeroCuenta
    ) {

        String sql =
                "SELECT " +
                "SUM(CASE WHEN tipo_movimiento IN " +
                "('deposito', 'transferencia_entrante') " +
                "THEN valor ELSE 0 END) - " +
                "SUM(CASE WHEN tipo_movimiento IN " +
                "('retiro', 'transferencia_saliente') " +
                "THEN valor ELSE 0 END) AS saldo " +
                "FROM movimiento " +
                "WHERE numero_cuenta = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    numeroCuenta
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                double saldo =
                        rs.getDouble("saldo");

                con.close();

                return saldo;
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return 0.0;
    }
    
    public String generarNumeroTransaccion() {

        String sql =
                "SELECT COUNT(*) AS total " +
                "FROM movimiento";

        try {

            Connection con = Conexion.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total");
                con.close();
                return "TRX" + String.format("%04d", total + 1);
            }

            con.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return "TRX0001";
    }
    
}