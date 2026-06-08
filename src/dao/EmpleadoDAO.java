package dao;

import conexion.Conexion;
import modelo.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpleadoDAO {

    public boolean insertar(Empleado empleado) {

        String sql =
                "INSERT INTO empleado (" +
                "cedula, " +
                "nombres, " +
                "apellidos, " +
                "fecha_ingreso, " +
                "salario_base, " +
                "correo_corporativo, " +
                "estado_laboral, " +
                "codigo_agencia, " +
                "id_tipo, " +
                "cedula_supervisor" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    empleado.getCedula()
            );

            ps.setString(
                    2,
                    empleado.getNombres()
            );

            ps.setString(
                    3,
                    empleado.getApellidos()
            );

            ps.setDate(
                    4,
                    empleado.getFechaIngreso()
            );

            ps.setBigDecimal(
                    5,
                    empleado.getSalarioBase()
            );

            ps.setString(
                    6,
                    empleado.getCorreoCorporativo()
            );

            ps.setString(
                    7,
                    empleado.getEstadoLaboral()
            );

            ps.setString(
                    8,
                    empleado.getCodigoAgencia()
            );

            ps.setInt(
                    9,
                    empleado.getIdTipo()
            );

            ps.setString(
                    10,
                    empleado.getCedulaSupervisor()
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

    public ArrayList<Empleado> listar() {

        ArrayList<Empleado> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM empleado";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Empleado empleado =
                        new Empleado();

                empleado.setCedula(
                        rs.getString("cedula")
                );

                empleado.setNombres(
                        rs.getString("nombres")
                );

                empleado.setApellidos(
                        rs.getString("apellidos")
                );

                empleado.setFechaIngreso(
                        rs.getDate("fecha_ingreso")
                );

                empleado.setSalarioBase(
                        rs.getBigDecimal("salario_base")
                );

                empleado.setCorreoCorporativo(
                        rs.getString("correo_corporativo")
                );

                empleado.setEstadoLaboral(
                        rs.getString("estado_laboral")
                );

                empleado.setCodigoAgencia(
                        rs.getString("codigo_agencia")
                );

                empleado.setIdTipo(
                        rs.getInt("id_tipo")
                );

                empleado.setCedulaSupervisor(
                        rs.getString("cedula_supervisor")
                );

                lista.add(
                        empleado
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

    public Empleado buscarPorCedula(String cedula) {

        String sql =
                "SELECT * FROM empleado " +
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

                Empleado empleado =
                        new Empleado();

                empleado.setCedula(
                        rs.getString("cedula")
                );

                empleado.setNombres(
                        rs.getString("nombres")
                );

                empleado.setApellidos(
                        rs.getString("apellidos")
                );

                empleado.setFechaIngreso(
                        rs.getDate("fecha_ingreso")
                );

                empleado.setSalarioBase(
                        rs.getBigDecimal("salario_base")
                );

                empleado.setCorreoCorporativo(
                        rs.getString("correo_corporativo")
                );

                empleado.setEstadoLaboral(
                        rs.getString("estado_laboral")
                );

                empleado.setCodigoAgencia(
                        rs.getString("codigo_agencia")
                );

                empleado.setIdTipo(
                        rs.getInt("id_tipo")
                );

                empleado.setCedulaSupervisor(
                        rs.getString("cedula_supervisor")
                );

                con.close();

                return empleado;
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return null;
    }

    public boolean actualizar(Empleado empleado) {

        String sql =
                "UPDATE empleado " +
                "SET nombres = ?, " +
                "apellidos = ?, " +
                "fecha_ingreso = ?, " +
                "salario_base = ?, " +
                "correo_corporativo = ?, " +
                "estado_laboral = ?, " +
                "codigo_agencia = ?, " +
                "id_tipo = ?, " +
                "cedula_supervisor = ? " +
                "WHERE cedula = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    empleado.getNombres()
            );

            ps.setString(
                    2,
                    empleado.getApellidos()
            );

            ps.setDate(
                    3,
                    empleado.getFechaIngreso()
            );

            ps.setBigDecimal(
                    4,
                    empleado.getSalarioBase()
            );

            ps.setString(
                    5,
                    empleado.getCorreoCorporativo()
            );

            ps.setString(
                    6,
                    empleado.getEstadoLaboral()
            );

            ps.setString(
                    7,
                    empleado.getCodigoAgencia()
            );

            ps.setInt(
                    8,
                    empleado.getIdTipo()
            );

            ps.setString(
                    9,
                    empleado.getCedulaSupervisor()
            );

            ps.setString(
                    10,
                    empleado.getCedula()
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

    public boolean eliminar(String cedula) {

        String sql =
                "DELETE FROM empleado " +
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
    
    public String obtenerNombrePorId(
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
}