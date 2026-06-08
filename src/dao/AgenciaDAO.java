package dao;

import conexion.Conexion;
import modelo.Agencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AgenciaDAO {

    public boolean insertar(Agencia agencia) {

        String sql =
                "INSERT INTO agencia " +
                "(codigo_agencia,nombre,direccion,municipio,telefono,fecha_apertura) " +
                "VALUES (?,?,?,?,?,?)";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    agencia.getCodigoAgencia()
            );

            ps.setString(
                    2,
                    agencia.getNombre()
            );

            ps.setString(
                    3,
                    agencia.getDireccion()
            );

            ps.setString(
                    4,
                    agencia.getMunicipio()
            );

            ps.setString(
                    5,
                    agencia.getTelefono()
            );

            ps.setDate(
                    6,
                    agencia.getFechaApertura()
            );

            ps.executeUpdate();

            con.close();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );

            return false;
        }
    }

    public ArrayList<Agencia> listar() {

        ArrayList<Agencia> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM agencia";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Agencia agencia =
                        new Agencia();

                agencia.setCodigoAgencia(
                        rs.getString("codigo_agencia")
                );

                agencia.setNombre(
                        rs.getString("nombre")
                );

                agencia.setDireccion(
                        rs.getString("direccion")
                );

                agencia.setMunicipio(
                        rs.getString("municipio")
                );

                agencia.setTelefono(
                        rs.getString("telefono")
                );

                agencia.setFechaApertura(
                        rs.getDate("fecha_apertura")
                );

                lista.add(
                        agencia
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

    public Agencia buscarPorCodigo(String codigo) {

        String sql =
                "SELECT * FROM agencia " +
                "WHERE codigo_agencia = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    codigo
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                Agencia agencia =
                        new Agencia();

                agencia.setCodigoAgencia(
                        rs.getString("codigo_agencia")
                );

                agencia.setNombre(
                        rs.getString("nombre")
                );

                agencia.setDireccion(
                        rs.getString("direccion")
                );

                agencia.setMunicipio(
                        rs.getString("municipio")
                );

                agencia.setTelefono(
                        rs.getString("telefono")
                );

                agencia.setFechaApertura(
                        rs.getDate("fecha_apertura")
                );

                con.close();

                return agencia;
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return null;
    }
    
    public boolean actualizar(Agencia agencia) {

        String sql =
                "UPDATE agencia " +
                "SET nombre = ?, " +
                "direccion = ?, " +
                "municipio = ?, " +
                "telefono = ?, " +
                "fecha_apertura = ? " +
                "WHERE codigo_agencia = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    agencia.getNombre()
            );

            ps.setString(
                    2,
                    agencia.getDireccion()
            );

            ps.setString(
                    3,
                    agencia.getMunicipio()
            );

            ps.setString(
                    4,
                    agencia.getTelefono()
            );

            ps.setDate(
                    5,
                    agencia.getFechaApertura()
            );

            ps.setString(
                    6,
                    agencia.getCodigoAgencia()
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
    
    public boolean eliminar(String codigo) {

        String sql =
                "DELETE FROM agencia " +
                "WHERE codigo_agencia = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    codigo
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
    
    public String generarCodigo() {

        String sql =
                "SELECT codigo_agencia " +
                "FROM agencia " +
                "ORDER BY codigo_agencia DESC " +
                "LIMIT 1";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                String ultimo =
                        rs.getString(
                                "codigo_agencia"
                        );

                int numero =
                        Integer.parseInt(
                                ultimo.substring(2)
                        );

                numero++;

                con.close();

                return String.format(
                        "AG%03d",
                        numero
                );
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return "AG001";
    }
}