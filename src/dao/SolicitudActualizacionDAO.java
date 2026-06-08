package dao;

import conexion.Conexion;
import modelo.SolicitudActualizacion;

import java.sql.*;
import java.util.ArrayList;

public class SolicitudActualizacionDAO {

    public boolean insertar(
            SolicitudActualizacion solicitud
    ) {

        String sql =
                "INSERT INTO solicitud_actualizacion " +
                "(cedula_asociado, nuevo_telefono, " +
                "nuevo_correo, nueva_direccion, estado, " +
                "fecha_solicitud) " +
                "VALUES (?, ?, ?, ?, 'pendiente', NOW())";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    solicitud.getCedulaAsociado()
            );

            ps.setString(
                    2,
                    solicitud.getNuevoTelefono()
            );

            ps.setString(
                    3,
                    solicitud.getNuevoCorreo()
            );

            ps.setString(
                    4,
                    solicitud.getNuevaDireccion()
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

    public ArrayList<SolicitudActualizacion>
            listarPendientes() {

        ArrayList<SolicitudActualizacion> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM solicitud_actualizacion " +
                "WHERE estado = 'pendiente' " +
                "ORDER BY fecha_solicitud DESC";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                SolicitudActualizacion s =
                        new SolicitudActualizacion();

                s.setIdSolicitud(
                        rs.getInt("id_solicitud")
                );

                s.setCedulaAsociado(
                        rs.getString("cedula_asociado")
                );

                s.setNuevoTelefono(
                        rs.getString("nuevo_telefono")
                );

                s.setNuevoCorreo(
                        rs.getString("nuevo_correo")
                );

                s.setNuevaDireccion(
                        rs.getString("nueva_direccion")
                );

                s.setEstado(
                        rs.getString("estado")
                );

                s.setFechaSolicitud(
                        rs.getTimestamp("fecha_solicitud")
                );

                lista.add(s);
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return lista;
    }

    public boolean aprobar(
            int idSolicitud,
            String cedulaAsociado,
            String nuevoTelefono,
            String nuevoCorreo,
            String nuevaDireccion
    ) {

        String sqlUpdate =
                "UPDATE asociado SET " +
                "telefono = COALESCE(?, telefono), " +
                "correo = COALESCE(?, correo), " +
                "direccion = COALESCE(?, direccion) " +
                "WHERE cedula = ?";

        String sqlEstado =
                "UPDATE solicitud_actualizacion " +
                "SET estado = 'aprobada' " +
                "WHERE id_solicitud = ?";

        try {

            Connection con =
                    Conexion.conectar();

            con.setAutoCommit(false);

            PreparedStatement ps1 =
                    con.prepareStatement(sqlUpdate);

            ps1.setString(
                    1,
                    nuevoTelefono.isEmpty()
                            ? null : nuevoTelefono
            );

            ps1.setString(
                    2,
                    nuevoCorreo.isEmpty()
                            ? null : nuevoCorreo
            );

            ps1.setString(
                    3,
                    nuevaDireccion.isEmpty()
                            ? null : nuevaDireccion
            );

            ps1.setString(4, cedulaAsociado);

            ps1.executeUpdate();

            PreparedStatement ps2 =
                    con.prepareStatement(sqlEstado);

            ps2.setInt(1, idSolicitud);

            ps2.executeUpdate();

            con.commit();

            con.close();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );

            return false;
        }
    }

    public boolean rechazar(
            int idSolicitud
    ) {

        String sql =
                "UPDATE solicitud_actualizacion " +
                "SET estado = 'rechazada' " +
                "WHERE id_solicitud = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, idSolicitud);

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
}