package dam.accesodatos.bloque4;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;

import dam.accesodatos.bloque2.ConexionBD;

public class ActualizarObjetoRelacional {

    public static void main(String[] args) {

        String sql =
                """
                UPDATE personas_or
                SET
                    direccion =
                        ROW(?, ?, ?)::direccion_tipo,
                    telefonos = ?
                WHERE id = ?
                """;

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement sentencia =
                        conexion.prepareStatement(sql)
        ) {

            sentencia.setString(
                    1,
                    "Camino de Ronda"
            );

            sentencia.setString(
                    2,
                    "Granada"
            );

            sentencia.setString(
                    3,
                    "18004"
            );

            String[] nuevosTelefonos = {
                    "611111111",
                    "622222222",
                    "633333333"
            };

            Array telefonosSQL =
                    conexion.createArrayOf(
                            "text",
                            nuevosTelefonos
                    );

            sentencia.setArray(
                    4,
                    telefonosSQL
            );

            sentencia.setInt(
                    5,
                    2
            );

            int filas =
                    sentencia.executeUpdate();

            System.out.println(
                    "Filas actualizadas: "
                            + filas
            );

            telefonosSQL.free();

        } catch (Exception e) {

            System.out.println(
                    "Error al actualizar datos objeto-relacionales."
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }
}