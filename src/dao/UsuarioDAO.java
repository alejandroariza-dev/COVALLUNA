package dao;

import conexion.Conexion;
import modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {

    public Usuario login(
            String username,
            String password
    ) {

        String sql =
                "SELECT * FROM usuario " +
                "WHERE username = ? " +
                "AND password = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                Usuario u = new Usuario();

                u.setIdUsuario(
                        rs.getInt("id_usuario")
                );

                u.setUsername(
                        rs.getString("username")
                );

                u.setPassword(
                        rs.getString("password")
                );

                u.setRol(
                        rs.getString("rol")
                );

                u.setCedula(
                        rs.getString("cedula")
                );

                con.close();

                return u;
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return null;
    }

    public boolean insertar(
            Usuario usuario
    ) {

        String sql =
                "INSERT INTO usuario " +
                "(username, password, rol, cedula) " +
                "VALUES (?, ?, ?, ?)";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getRol());
            ps.setString(4, usuario.getCedula());

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
            int idUsuario
    ) {

        String sql =
                "DELETE FROM usuario " +
                "WHERE id_usuario = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, idUsuario);

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

    public boolean cambiarPassword(
            int idUsuario,
            String nuevaPassword
    ) {

        String sql =
                "UPDATE usuario " +
                "SET password = ? " +
                "WHERE id_usuario = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, nuevaPassword);
            ps.setInt(2, idUsuario);

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

    public ArrayList<Usuario> listar() {

        ArrayList<Usuario> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM usuario " +
                "ORDER BY rol, username";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Usuario u = new Usuario();

                u.setIdUsuario(
                        rs.getInt("id_usuario")
                );

                u.setUsername(
                        rs.getString("username")
                );

                u.setPassword(
                        rs.getString("password")
                );

                u.setRol(
                        rs.getString("rol")
                );

                u.setCedula(
                        rs.getString("cedula")
                );

                lista.add(u);
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return lista;
    }
    
    public boolean existeCedulaEnEmpleado(
            String cedula
    ) {

        String sql =
                "SELECT cedula FROM empleado " +
                "WHERE cedula = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, cedula);

            ResultSet rs =
                    ps.executeQuery();

            boolean existe = rs.next();

            con.close();

            return existe;

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );

            return false;
        }
    }
    
    public boolean existeCedulaEnAsociado(
            String cedula
    ) {

        String sql =
                "SELECT cedula FROM asociado " +
                "WHERE cedula = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, cedula);

            ResultSet rs =
                    ps.executeQuery();

            boolean existe = rs.next();

            con.close();

            return existe;

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );

            return false;
        }
    }
}