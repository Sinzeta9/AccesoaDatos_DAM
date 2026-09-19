package dam.accesodatos.bloque4;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dam.accesodatos.bloque2.ConexionBD;

public class ConsultaObjetoRelacional {

    public static void main(String[] args) {

        String sql =
                """
                SELECT
                    nombre,
                    (direccion).calle AS calle,
                    (direccion).ciudad AS ciudad,
                    (direccion).codigo_postal AS codigo_postal,
                    telefonos
                FROM personas_or
                WHERE id = ?
                """;

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement sentencia =
                        conexion.prepareStatement(sql)
        ) {

            sentencia.setInt(1, 1);

            try (
                    ResultSet resultado =
                            sentencia.executeQuery()
            ) {

                if (resultado.next()) {

                    String nombre =
                            resultado.getString(
                                    "nombre"
                            );

                    String calle =
                            resultado.getString(
                                    "calle"
                            );

                    String ciudad =
                            resultado.getString(
                                    "ciudad"
                            );

                    String codigoPostal =
                            resultado.getString(
                                    "codigo_postal"
                            );

                    Array arrayTelefonos =
                            resultado.getArray(
                                    "telefonos"
                            );

                    String[] telefonos =
                            (String[])
                                    arrayTelefonos.getArray();

                    System.out.println(
                            "Nombre: " + nombre
                    );

                    System.out.println(
                            "Direccion:"
                    );

                    System.out.println(
                            " Calle: " + calle
                    );

                    System.out.println(
                            " Ciudad: " + ciudad
                    );

                    System.out.println(
                            " CP: " + codigoPostal
                    );

                    System.out.println(
                            "Telefonos:"
                    );

                    for (String telefono : telefonos) {

                        System.out.println(
                                " - " + telefono
                        );
                    }

                } else {

                    System.out.println(
                            "Persona no encontrada."
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al consultar datos objeto-relacionales."
            );

            System.out.println(
                    e.getMessage()
            );
        }
    }
}