package dam.accesodatos.bloque4;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dam.accesodatos.bloque2.ConexionBD;

public class InsertarJsonB {

    public static void main(String[] args) {

        String sql = """
                INSERT INTO perfiles_json (
                    nombre,
                    datos
                )
                VALUES (
                    ?,
                    CAST(? AS jsonb)
                )
                """;

        String nombre = "Carlos";

        String datosJson = """
                {
                    "edad": 29,
                    "ciclo": "DAM",
                    "direccion": {
                        "ciudad": "Granada",
                        "cp": "18005"
                    },
                    "lenguajes": [
                        "Java",
                        "SQL",
                        "Kotlin"
                    ]
                }
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement sentencia = conexion.prepareStatement(sql)
        ) {

            sentencia.setString(1, nombre);
            sentencia.setString(2, datosJson);

            int filas = sentencia.executeUpdate();

            System.out.println(
                    "Filas insertadas: " + filas
            );

        } catch (Exception e) {

            System.out.println(
                    "Error al insertar JSONB."
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }
}