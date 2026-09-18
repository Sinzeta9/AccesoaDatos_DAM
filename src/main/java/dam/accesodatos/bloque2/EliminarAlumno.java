package dam.accesodatos.bloque2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class EliminarAlumno {

    public static void main(String[] args) {

        String url =
                "jdbc:postgresql://localhost:5433/accesodatos";

        String usuario = "dam";
        String password = "dam123";

        String sql =
                "DELETE FROM alumnos " +
                "WHERE nombre = ?";

        try (
                Connection conexion =
                        DriverManager.getConnection(
                                url,
                                usuario,
                                password
                        );

                PreparedStatement sentencia =
                        conexion.prepareStatement(sql)
        ) {

            sentencia.setString(1, "Marta");

            int filas =
                    sentencia.executeUpdate();

            System.out.println(
                    "Filas eliminadas: " + filas
            );

        } catch (Exception e) {

            System.out.println(
                    "Error al eliminar el alumno."
            );

            System.out.println(e.getMessage());
        }
    }
}