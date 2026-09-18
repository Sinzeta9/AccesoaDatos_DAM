package dam.accesodatos.bloque2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertarAlumno {

    public static void main(String[] args) {

        String url =
                "jdbc:postgresql://localhost:5433/accesodatos";

        String usuario = "dam";
        String password = "dam123";

        String nombre = "Marta";
        int edad = 24;
        String ciclo = "DAM";

        String sql =
                "INSERT INTO alumnos (nombre, edad, ciclo) " +
                "VALUES (?, ?, ?)";

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

            sentencia.setString(1, nombre);
            sentencia.setInt(2, edad);
            sentencia.setString(3, ciclo);

            int filas =
                    sentencia.executeUpdate();

            System.out.println(
                    "Filas insertadas: " + filas
            );

        } catch (Exception e) {

            System.out.println(
                    "Error al insertar el alumno."
            );

            System.out.println(e.getMessage());
        }
    }
}