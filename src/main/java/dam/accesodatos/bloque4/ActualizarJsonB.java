package dam.accesodatos.bloque4;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dam.accesodatos.bloque2.ConexionBD;

public class ActualizarJsonB {

    public static void main(String[] args) {

        String sql = """
                UPDATE perfiles_json
                SET datos = jsonb_set(
                    datos,
                    '{edad}',
                    to_jsonb(CAST(? AS INTEGER))
                )
                WHERE id = ?
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement sentencia = conexion.prepareStatement(sql)
        ) {

            sentencia.setInt(1, 30);
            sentencia.setInt(2, 2);

            int filas = sentencia.executeUpdate();

            System.out.println(
                    "Filas actualizadas: " + filas
            );

        } catch (Exception e) {

            System.out.println(
                    "Error al actualizar JSONB."
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }
}