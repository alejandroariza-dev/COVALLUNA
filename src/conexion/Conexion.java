package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/coovalluna";

    private static final String USER = "postgres";

    private static final String PASSWORD = "univalle";

    public static Connection conectar() {

        try {

            Connection con = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Conexion exitosa");

            return con;

        } catch (SQLException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

            return null;
        }
    }
}
