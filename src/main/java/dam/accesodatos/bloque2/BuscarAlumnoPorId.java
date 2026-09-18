package dam.accesodatos.bloque2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BuscarAlumnoPorId {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        String sql =
                "SELECT * FROM alumnos WHERE id = ?";

        System.out.print("Introduce el ID del alumno: ");
        int idBuscado = teclado.nextInt();

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement sentencia =
                        conexion.prepareStatement(sql)
        ) {

            sentencia.setInt(1, idBuscado);

            try (
                    ResultSet resultado =
                            sentencia.executeQuery()
            ) {

                if (resultado.next()) {

                    int id =
                            resultado.getInt("id");

                    String nombre =
                            resultado.getString("nombre");

                    int edad =
                            resultado.getInt("edad");

                    String ciclo =
                            resultado.getString("ciclo");

                    System.out.println();
                    System.out.println("Alumno encontrado:");
                    System.out.println(
                            id + " - "
                                    + nombre + " - "
                                    + edad + " - "
                                    + ciclo
                    );

                } else {

                    System.out.println(
                            "No existe ningún alumno con ID "
                                    + idBuscado
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al buscar el alumno."
            );

            System.out.println(e.getMessage());
        }

        teclado.close();
    }
}