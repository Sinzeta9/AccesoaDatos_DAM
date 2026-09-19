package dam.accesodatos.bloque4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dam.accesodatos.bloque2.ConexionBD;

public class ConsultaJsonB {

    public static void main(String[] args) {

        String sql = """
                SELECT
                    nombre,
                    datos ->> 'ciclo' AS ciclo,
                    datos -> 'direccion' ->> 'ciudad' AS ciudad,
                    datos -> 'lenguajes' ->> 0 AS primer_lenguaje
                FROM perfiles_json
                WHERE id = ?
                """;

        try (
                Connection conexion = ConexionBD.conectar();
                PreparedStatement sentencia = conexion.prepareStatement(sql)
        ) {

            sentencia.setInt(1, 1);

            try (
                    ResultSet resultado = sentencia.executeQuery()
            ) {

                if (resultado.next()) {

                    System.out.println(
                            "Nombre: "
                                    + resultado.getString("nombre")
                    );

                    System.out.println(
                            "Ciclo: "
                                    + resultado.getString("ciclo")
                    );

                    System.out.println(
                            "Ciudad: "
                                    + resultado.getString("ciudad")
                    );

                    System.out.println(
                            "Primer lenguaje: "
                                    + resultado.getString("primer_lenguaje")
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al consultar JSONB."
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }
}