package dam.accesodatos.bloque2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String HOST =
            System.getenv().getOrDefault(
                    "DB_HOST",
                    "localhost"
            );

    private static final String URL =
            "jdbc:postgresql://" + HOST + ":5433/accesodatos";

    private static final String USUARIO = "dam";
    private static final String PASSWORD = "dam123";

    public static Connection conectar()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USUARIO,
                PASSWORD
        );
    }
}