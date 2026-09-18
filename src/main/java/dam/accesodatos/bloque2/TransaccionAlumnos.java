package dam.accesodatos.bloque2;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransaccionAlumnos {

    public static void main(String[] args) {

        String sql =
                "INSERT INTO alumnos (nombre, edad, ciclo) " +
                "VALUES (?, ?, ?)";

        try (
                Connection conexion =
                        ConexionBD.conectar()
        ) {

            // Desactivamos el autocommit
            conexion.setAutoCommit(false);

            try (
                    PreparedStatement sentencia =
                            conexion.prepareStatement(sql)
            ) {

                // Primer alumno
                sentencia.setString(1, "Ana");
                sentencia.setInt(2, 23);
                sentencia.setString(3, "DAM");
                sentencia.executeUpdate();

                // Segundo alumno
                sentencia.setString(1, "Pedro");
                sentencia.setInt(2, 27);
                sentencia.setString(3, "DAW");
                sentencia.executeUpdate();

                // Si todo ha ido bien
                conexion.commit();

                System.out.println(
                        "Transaccion completada correctamente."
                );

            } catch (Exception e) {

                // Si algo falla, deshacemos todo
                conexion.rollback();

                System.out.println(
                        "Error en la transaccion."
                );

                System.out.println(
                        "Se han deshecho los cambios."
                );

                System.out.println(e.getMessage());
            }

        } catch (Exception e) {

            System.out.println(
                    "Error de conexion."
            );

            System.out.println(e.getMessage());
        }
    }
}