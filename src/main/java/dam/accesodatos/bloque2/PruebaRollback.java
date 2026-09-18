package dam.accesodatos.bloque2;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PruebaRollback {

    public static void main(String[] args) {

        String sql =
                "INSERT INTO alumnos (nombre, edad, ciclo) " +
                "VALUES (?, ?, ?)";

        try (
                Connection conexion =
                        ConexionBD.conectar()
        ) {

            conexion.setAutoCommit(false);

            try (
                    PreparedStatement sentencia =
                            conexion.prepareStatement(sql)
            ) {

                // Primera operación: correcta
                sentencia.setString(1, "AlumnoRollback");
                sentencia.setInt(2, 30);
                sentencia.setString(3, "DAM");

                sentencia.executeUpdate();

                System.out.println(
                        "Primer INSERT realizado."
                );

                // Segunda operación: provocamos un error
                // nombre es NOT NULL
                sentencia.setNull(
                        1,
                        java.sql.Types.VARCHAR
                );

                sentencia.setInt(2, 31);
                sentencia.setString(3, "DAW");

                sentencia.executeUpdate();

                // Solo llegaría aquí si todo fuera correcto
                conexion.commit();

                System.out.println(
                        "Transaccion confirmada."
                );

            } catch (Exception e) {

                conexion.rollback();

                System.out.println();
                System.out.println(
                        "Ha ocurrido un error."
                );

                System.out.println(
                        "ROLLBACK ejecutado."
                );

                System.out.println(
                        "Ninguna operacion de la transaccion se ha guardado."
                );

                System.out.println();
                System.out.println(
                        "Error: " + e.getMessage()
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error de conexion: "
                            + e.getMessage()
            );
        }
    }
}