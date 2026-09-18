package dam.accesodatos.bloque2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionPostgreSQL {

    public static void main(String[] args) {

        String url =
                "jdbc:postgresql://localhost:5433/accesodatos";

        String usuario = "dam";
        String password = "dam123";

        try {

            Connection conexion =
                    DriverManager.getConnection(
                            url,
                            usuario,
                            password
                    );

            System.out.println(
                    "Conexion con PostgreSQL realizada correctamente."
            );

            conexion.close();

        } catch (SQLException e) {

            System.out.println(
                    "Error al conectar con PostgreSQL."
            );

            System.out.println(e.getMessage());
        }
    }
}