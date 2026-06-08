package dao;

import conexion.Conexion;
import modelo.Beneficiario;

import java.sql.*;
import java.util.ArrayList;

public class BeneficiarioDAO {

    public boolean insertar(
            Beneficiario beneficiario
    ) {

        String sql =
                "INSERT INTO beneficiario " +
                "(numero_documento, nombre_completo, parentesco, " +
                "porcentaje_participacion, telefono, cedula_asociado) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    beneficiario.getNumeroDocumento()
            );

            ps.setString(
                    2,
                    beneficiario.getNombreCompleto()
            );

            ps.setString(
                    3,
                    beneficiario.getParentesco()
            );

            ps.setDouble(
                    4,
                    beneficiario.getPorcentajeParticipacion()
            );

            ps.setString(
                    5,
                    beneficiario.getTelefono()
            );

            ps.setString(
                    6,
                    beneficiario.getCedulaAsociado()
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

    public ArrayList<Beneficiario> listar() {

        ArrayList<Beneficiario> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM beneficiario";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Beneficiario beneficiario =
                        new Beneficiario();

                beneficiario.setNumeroDocumento(
                        rs.getString(
                                "numero_documento"
                        )
                );

                beneficiario.setNombreCompleto(
                        rs.getString(
                                "nombre_completo"
                        )
                );

                beneficiario.setParentesco(
                        rs.getString(
                                "parentesco"
                        )
                );

                beneficiario.setPorcentajeParticipacion(
                        rs.getDouble(
                                "porcentaje_participacion"
                        )
                );

                beneficiario.setTelefono(
                        rs.getString(
                                "telefono"
                        )
                );

                beneficiario.setCedulaAsociado(
                        rs.getString(
                                "cedula_asociado"
                        )
                );

                lista.add(
                        beneficiario
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

    public Beneficiario buscarPorDocumento(
            String documento
    ) {

        String sql =
                "SELECT * FROM beneficiario " +
                "WHERE numero_documento = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    documento
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                Beneficiario beneficiario =
                        new Beneficiario();

                beneficiario.setNumeroDocumento(
                        rs.getString(
                                "numero_documento"
                        )
                );

                beneficiario.setNombreCompleto(
                        rs.getString(
                                "nombre_completo"
                        )
                );

                beneficiario.setParentesco(
                        rs.getString(
                                "parentesco"
                        )
                );

                beneficiario.setPorcentajeParticipacion(
                        rs.getDouble(
                                "porcentaje_participacion"
                        )
                );

                beneficiario.setTelefono(
                        rs.getString(
                                "telefono"
                        )
                );

                beneficiario.setCedulaAsociado(
                        rs.getString(
                                "cedula_asociado"
                        )
                );

                con.close();

                return beneficiario;
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
            Beneficiario beneficiario
    ) {

        String sql =
                "UPDATE beneficiario " +
                "SET nombre_completo = ?, " +
                "parentesco = ?, " +
                "porcentaje_participacion = ?, " +
                "telefono = ?, " +
                "cedula_asociado = ? " +
                "WHERE numero_documento = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    beneficiario.getNombreCompleto()
            );

            ps.setString(
                    2,
                    beneficiario.getParentesco()
            );

            ps.setDouble(
                    3,
                    beneficiario.getPorcentajeParticipacion()
            );

            ps.setString(
                    4,
                    beneficiario.getTelefono()
            );

            ps.setString(
                    5,
                    beneficiario.getCedulaAsociado()
            );

            ps.setString(
                    6,
                    beneficiario.getNumeroDocumento()
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
            String documento
    ) {

        String sql =
                "DELETE FROM beneficiario " +
                "WHERE numero_documento = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    documento
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
    
    public ArrayList<Beneficiario> listarPorAsociado(
            String cedulaAsociado
    ) {

        ArrayList<Beneficiario> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM beneficiario " +
                "WHERE cedula_asociado = ?";

        try {

            Connection con = Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, cedulaAsociado);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Beneficiario b = new Beneficiario();

                b.setNumeroDocumento(
                        rs.getString("numero_documento")
                );

                b.setNombreCompleto(
                        rs.getString("nombre_completo")
                );

                b.setParentesco(
                        rs.getString("parentesco")
                );

                b.setPorcentajeParticipacion(
                        rs.getDouble(
                                "porcentaje_participacion"
                        )
                );

                b.setTelefono(
                        rs.getString("telefono")
                );

                b.setCedulaAsociado(
                        rs.getString("cedula_asociado")
                );

                lista.add(b);
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return lista;
    }
    
    public int contarPorAsociado(
            String cedulaAsociado
    ) {

        String sql =
                "SELECT COUNT(*) AS total " +
                "FROM beneficiario " +
                "WHERE cedula_asociado = ?";

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, cedulaAsociado);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                int total =
                        rs.getInt("total");

                con.close();

                return total;
            }

            con.close();

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return 0;
    }
}