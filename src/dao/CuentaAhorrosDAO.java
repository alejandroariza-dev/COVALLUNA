package dao;

import conexion.Conexion;
import modelo.CuentaAhorros;

import java.sql.*;
import java.util.ArrayList;

public class CuentaAhorrosDAO {

    public boolean insertar(
            CuentaAhorros cuenta
    ) {

        String sql =
                "INSERT INTO cuenta_ahorros " +
                "(numero_cuenta, fecha_apertura, estado, cedula_asociado, codigo_agencia) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    cuenta.getNumeroCuenta()
            );

            ps.setDate(
                    2,
                    cuenta.getFechaApertura()
            );

            ps.setString(
                    3,
                    cuenta.getEstado()
            );

            ps.setString(
                    4,
                    cuenta.getCedulaAsociado()
            );

            ps.setString(
                    5,
                    cuenta.getCodigoAgencia()
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

    public ArrayList<CuentaAhorros> listar() {

        ArrayList<CuentaAhorros> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM cuenta_ahorros";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                CuentaAhorros cuenta =
                        new CuentaAhorros();

                cuenta.setNumeroCuenta(
                        rs.getString(
                                "numero_cuenta"
                        )
                );

                cuenta.setFechaApertura(
                        rs.getDate(
                                "fecha_apertura"
                        )
                );

                cuenta.setEstado(
                        rs.getString(
                                "estado"
                        )
                );

                cuenta.setCedulaAsociado(
                        rs.getString(
                                "cedula_asociado"
                        )
                );

                cuenta.setCodigoAgencia(
                        rs.getString(
                                "codigo_agencia"
                        )
                );

                lista.add(
                        cuenta
                );
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return lista;
    }

    public CuentaAhorros buscarPorNumero(
            String numeroCuenta
    ) {

        String sql =
                "SELECT * FROM cuenta_ahorros " +
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

                CuentaAhorros cuenta =
                        new CuentaAhorros();

                cuenta.setNumeroCuenta(
                        rs.getString(
                                "numero_cuenta"
                        )
                );

                cuenta.setFechaApertura(
                        rs.getDate(
                                "fecha_apertura"
                        )
                );

                cuenta.setEstado(
                        rs.getString(
                                "estado"
                        )
                );

                cuenta.setCedulaAsociado(
                        rs.getString(
                                "cedula_asociado"
                        )
                );

                cuenta.setCodigoAgencia(
                        rs.getString(
                                "codigo_agencia"
                        )
                );

                con.close();

                return cuenta;
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return null;
    }

    public boolean actualizar(
            CuentaAhorros cuenta
    ) {

        String sql =
                "UPDATE cuenta_ahorros " +
                "SET fecha_apertura = ?, " +
                "estado = ?, " +
                "cedula_asociado = ?, " +
                "codigo_agencia = ? " +
                "WHERE numero_cuenta = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setDate(
                    1,
                    cuenta.getFechaApertura()
            );

            ps.setString(
                    2,
                    cuenta.getEstado()
            );

            ps.setString(
                    3,
                    cuenta.getCedulaAsociado()
            );

            ps.setString(
                    4,
                    cuenta.getCodigoAgencia()
            );

            ps.setString(
                    5,
                    cuenta.getNumeroCuenta()
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

    public boolean eliminar(
            String numeroCuenta
    ) {

        String sql =
                "DELETE FROM cuenta_ahorros " +
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
    
    public ArrayList<CuentaAhorros> listarPorAsociado(
            String cedulaAsociado
    ) {

        ArrayList<CuentaAhorros> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM cuenta_ahorros " +
                "WHERE cedula_asociado = ?";

        try {

            Connection con = Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, cedulaAsociado);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                CuentaAhorros c =
                        new CuentaAhorros();

                c.setNumeroCuenta(
                        rs.getString("numero_cuenta")
                );

                c.setFechaApertura(
                        rs.getDate("fecha_apertura")
                );

                c.setEstado(
                        rs.getString("estado")
                );

                c.setCedulaAsociado(
                        rs.getString("cedula_asociado")
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
}