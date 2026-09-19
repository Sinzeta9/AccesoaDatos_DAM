package dam.accesodatos.bloque4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dam.accesodatos.bloque2.ConexionBD;

public class BuscarPorTelefono {

    public static void main(String[] args) {

        String telefonoBuscado =
                "600111222";

        String sql =
                """
                SELECT
                    id,
                    nombre,
                    telefonos
                FROM personas_or
                WHERE ? = ANY(telefonos)
                """;

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement sentencia =
                        conexion.prepareStatement(sql)
        ) {

            sentencia.setString(
                    1,
                    telefonoBuscado
            );

            try (
                    ResultSet resultado =
                            sentencia.executeQuery()
            ) {

                boolean encontrado =
                        false;

                while (resultado.next()) {

                    encontrado =
                            true;

                    System.out.println(
                            "Persona encontrada:"
                    );

                    System.out.println(
                            "ID: "
                                    + resultado.getInt("id")
                    );

                    System.out.println(
                            "Nombre: "
                                    + resultado.getString("nombre")
                    );
                }

                if (!encontrado) {

                    System.out.println(
                            "No existe ninguna persona "
                                    + "con ese telefono."
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al buscar por telefono."
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }
}