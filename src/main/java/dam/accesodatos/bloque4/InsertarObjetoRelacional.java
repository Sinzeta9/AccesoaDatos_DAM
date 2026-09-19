package dam.accesodatos.bloque4;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;

import dam.accesodatos.bloque2.ConexionBD;

public class InsertarObjetoRelacional {

    public static void main(String[] args) {

        String sql =
                """
                INSERT INTO personas_or (
                    nombre,
                    direccion,
                    telefonos
                )
                VALUES (
                    ?,
                    ROW(?, ?, ?)::direccion_tipo,
                    ?
                )
                """;

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement sentencia =
                        conexion.prepareStatement(sql)
        ) {

            sentencia.setString(
                    1,
                    "Carlos"
            );

            sentencia.setString(
                    2,
                    "Calle Recogidas"
            );

            sentencia.setString(
                    3,
                    "Granada"
            );

            sentencia.setString(
                    4,
                    "18005"
            );

            String[] telefonosJava = {
                    "611111111",
                    "622222222"
            };

            Array telefonosSQL =
                    conexion.createArrayOf(
                            "text",
                            telefonosJava
                    );

            sentencia.setArray(
                    5,
                    telefonosSQL
            );

            int filas =
                    sentencia.executeUpdate();

            System.out.println(
                    "Filas insertadas: "
                            + filas
            );

            telefonosSQL.free();

        } catch (Exception e) {

            System.out.println(
                    "Error al insertar datos objeto-relacionales."
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }
}