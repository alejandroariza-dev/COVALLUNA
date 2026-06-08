package dao;

import conexion.Conexion;
import modelo.TipoEmpleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoEmpleadoDAO {

    public ArrayList<TipoEmpleado> listar() {

        ArrayList<TipoEmpleado> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM tipo_empleado";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                TipoEmpleado tipo =
                        new TipoEmpleado();

                tipo.setIdTipo(
                        rs.getInt("id_tipo")
                );

                tipo.setNombreTipo(
                        rs.getString("nombre_tipo")
                );

                lista.add(tipo);
            }

            con.close();

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return lista;
    }

    public String obtenerNombreTipo(
            int idTipo
    ) {

        String sql =
                "SELECT nombre_tipo " +
                "FROM tipo_empleado " +
                "WHERE id_tipo = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    idTipo
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                String nombre =
                        rs.getString(
                                "nombre_tipo"
                        );

                con.close();

                return nombre;
            }

            con.close();

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return "";
    }
    
    public int obtenerIdPorNombre(
            String nombre
    ) {

        String sql =
                "SELECT id_tipo " +
                "FROM tipo_empleado " +
                "WHERE nombre_tipo = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    nombre
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                int id =
                        rs.getInt(
                                "id_tipo"
                        );

                con.close();

                return id;
            }

            con.close();

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return -1;
    }
    
    public ArrayList<String> obtenerNombresTipo() {

        ArrayList<String> lista =
                new ArrayList<>();

        String sql =
                "SELECT nombre_tipo " +
                "FROM tipo_empleado " +
                "ORDER BY id_tipo";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                lista.add(
                        rs.getString(
                                "nombre_tipo"
                        )
                );
            }

            con.close();

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return lista;
    }
}