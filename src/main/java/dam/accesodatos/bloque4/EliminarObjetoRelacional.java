package dam.accesodatos.bloque4;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dam.accesodatos.bloque2.ConexionBD;

public class EliminarObjetoRelacional {

    public static void main(String[] args) {

        String sql =
                """
                DELETE FROM personas_or
                WHERE id = ?
                """;

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement sentencia =
                        conexion.prepareStatement(sql)
        ) {

            sentencia.setInt(
                    1,
                    2
            );

            int filas =
                    sentencia.executeUpdate();

            System.out.println(
                    "Filas eliminadas: "
                            + filas
            );

        } catch (Exception e) {

            System.out.println(
                    "Error al eliminar datos objeto-relacionales."
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }
}