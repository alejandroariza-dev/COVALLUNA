package dao;

import conexion.Conexion;
import modelo.Asociado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AsociadoDAO {

    public boolean insertar(Asociado asociado) {

        String sql =
                "INSERT INTO asociado (" +
                "cedula, " +
                "nombres, " +
                "apellidos, " +
                "fecha_nacimiento, " +
                "direccion, " +
                "municipio, " +
                "telefono, " +
                "correo, " +
                "fecha_afiliacion, " +
                "estado" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, asociado.getCedula());
            ps.setString(2, asociado.getNombres());
            ps.setString(3, asociado.getApellidos());
            ps.setDate(4, asociado.getFechaNacimiento());
            ps.setString(5, asociado.getDireccion());
            ps.setString(6, asociado.getMunicipio());
            ps.setString(7, asociado.getTelefono());
            ps.setString(8, asociado.getCorreo());
            ps.setDate(9, asociado.getFechaAfiliacion());
            ps.setString(10, asociado.getEstado());

            int filas =
                    ps.executeUpdate();

            con.close();

            return filas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "ERROR: " + e.getMessage()
            );

            return false;
        }
    }

    public ArrayList<Asociado> listar() {

        ArrayList<Asociado> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM asociado";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Asociado asociado =
                        new Asociado();

                asociado.setCedula(
                        rs.getString("cedula")
                );

                asociado.setNombres(
                        rs.getString("nombres")
                );

                asociado.setApellidos(
                        rs.getString("apellidos")
                );

                asociado.setFechaNacimiento(
                        rs.getDate("fecha_nacimiento")
                );

                asociado.setDireccion(
                        rs.getString("direccion")
                );

                asociado.setMunicipio(
                        rs.getString("municipio")
                );

                asociado.setTelefono(
                        rs.getString("telefono")
                );

                asociado.setCorreo(
                        rs.getString("correo")
                );

                asociado.setFechaAfiliacion(
                        rs.getDate("fecha_afiliacion")
                );

                asociado.setEstado(
                        rs.getString("estado")
                );

                lista.add(
                        asociado
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

    public Asociado buscarPorCedula(
            String cedula
    ) {

        String sql =
                "SELECT * FROM asociado " +
                "WHERE cedula = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    cedula
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                Asociado asociado =
                        new Asociado();

                asociado.setCedula(
                        rs.getString("cedula")
                );

                asociado.setNombres(
                        rs.getString("nombres")
                );

                asociado.setApellidos(
                        rs.getString("apellidos")
                );

                asociado.setFechaNacimiento(
                        rs.getDate("fecha_nacimiento")
                );

                asociado.setDireccion(
                        rs.getString("direccion")
                );

                asociado.setMunicipio(
                        rs.getString("municipio")
                );

                asociado.setTelefono(
                        rs.getString("telefono")
                );

                asociado.setCorreo(
                        rs.getString("correo")
                );

                asociado.setFechaAfiliacion(
                        rs.getDate("fecha_afiliacion")
                );

                asociado.setEstado(
                        rs.getString("estado")
                );

                con.close();

                return asociado;
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
            Asociado asociado
    ) {

        String sql =
                "UPDATE asociado " +
                "SET nombres = ?, " +
                "apellidos = ?, " +
                "fecha_nacimiento = ?, " +
                "direccion = ?, " +
                "municipio = ?, " +
                "telefono = ?, " +
                "correo = ?, " +
                "fecha_afiliacion = ?, " +
                "estado = ? " +
                "WHERE cedula = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, asociado.getNombres());
            ps.setString(2, asociado.getApellidos());
            ps.setDate(3, asociado.getFechaNacimiento());
            ps.setString(4, asociado.getDireccion());
            ps.setString(5, asociado.getMunicipio());
            ps.setString(6, asociado.getTelefono());
            ps.setString(7, asociado.getCorreo());
            ps.setDate(8, asociado.getFechaAfiliacion());
            ps.setString(9, asociado.getEstado());
            ps.setString(10, asociado.getCedula());

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
            String cedula
    ) {

        String sql =
                "DELETE FROM asociado " +
                "WHERE cedula = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    cedula
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
    
    public Asociado buscarPorCedulaYEstado(
            String cedula
    ) {

        String sql =
                "SELECT * FROM asociado " +
                "WHERE cedula = ? " +
                "AND estado = 'activo'";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, cedula);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                Asociado a = new Asociado();

                a.setCedula(
                        rs.getString("cedula")
                );

                a.setNombres(
                        rs.getString("nombres")
                );

                a.setApellidos(
                        rs.getString("apellidos")
                );

                a.setFechaNacimiento(
                        rs.getDate("fecha_nacimiento")
                );

                a.setDireccion(
                        rs.getString("direccion")
                );

                a.setMunicipio(
                        rs.getString("municipio")
                );

                a.setTelefono(
                        rs.getString("telefono")
                );

                a.setCorreo(
                        rs.getString("correo")
                );

                a.setFechaAfiliacion(
                        rs.getDate("fecha_afiliacion")
                );

                a.setEstado(
                        rs.getString("estado")
                );

                con.close();

                return a;
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return null;
    }
}