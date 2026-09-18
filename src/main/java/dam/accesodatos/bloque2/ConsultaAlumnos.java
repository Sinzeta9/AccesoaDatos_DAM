package dam.accesodatos.bloque2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConsultaAlumnos {

    public static void main(String[] args) {

        String sql = "SELECT * FROM alumnos";

        try (
                Connection conexion =
                        ConexionBD.conectar();

                Statement statement =
                        conexion.createStatement();

                ResultSet resultado =
                        statement.executeQuery(sql)
        ) {

            while (resultado.next()) {

                int id =
                        resultado.getInt("id");

                String nombre =
                        resultado.getString("nombre");

                int edad =
                        resultado.getInt("edad");

                String ciclo =
                        resultado.getString("ciclo");

                System.out.println(
                        id + " - "
                                + nombre + " - "
                                + edad + " - "
                                + ciclo
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al consultar los alumnos."
            );

            System.out.println(e.getMessage());
        }
    }
}