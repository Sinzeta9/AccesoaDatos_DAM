package dam.accesodatos.bloque2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {

    public List<Alumno> listarTodos() {

        List<Alumno> alumnos = new ArrayList<>();

        String sql =
                "SELECT * FROM alumnos ORDER BY id";

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement sentencia =
                        conexion.prepareStatement(sql);

                ResultSet resultado =
                        sentencia.executeQuery()
        ) {

            while (resultado.next()) {

                Alumno alumno = new Alumno(
                        resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getInt("edad"),
                        resultado.getString("ciclo")
                );

                alumnos.add(alumno);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al listar alumnos."
            );

            System.out.println(e.getMessage());
        }

        return alumnos;
    }


    public Alumno buscarPorId(int id) {

        String sql =
                "SELECT * FROM alumnos WHERE id = ?";

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement sentencia =
                        conexion.prepareStatement(sql)
        ) {

            sentencia.setInt(1, id);

            try (
                    ResultSet resultado =
                            sentencia.executeQuery()
            ) {

                if (resultado.next()) {

                    return new Alumno(
                            resultado.getInt("id"),
                            resultado.getString("nombre"),
                            resultado.getInt("edad"),
                            resultado.getString("ciclo")
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al buscar alumno."
            );

            System.out.println(e.getMessage());
        }

        return null;
    }


    public void insertar(Alumno alumno) {

        String sql =
                "INSERT INTO alumnos " +
                "(nombre, edad, ciclo) " +
                "VALUES (?, ?, ?)";

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement sentencia =
                        conexion.prepareStatement(sql)
        ) {

            sentencia.setString(
                    1,
                    alumno.getNombre()
            );

            sentencia.setInt(
                    2,
                    alumno.getEdad()
            );

            sentencia.setString(
                    3,
                    alumno.getCiclo()
            );

            sentencia.executeUpdate();

            System.out.println(
                    "Alumno insertado correctamente."
            );

        } catch (Exception e) {

            System.out.println(
                    "Error al insertar alumno."
            );

            System.out.println(e.getMessage());
        }
    }


    public void actualizar(Alumno alumno) {

        String sql =
                "UPDATE alumnos " +
                "SET nombre = ?, edad = ?, ciclo = ? " +
                "WHERE id = ?";

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement sentencia =
                        conexion.prepareStatement(sql)
        ) {

            sentencia.setString(
                    1,
                    alumno.getNombre()
            );

            sentencia.setInt(
                    2,
                    alumno.getEdad()
            );

            sentencia.setString(
                    3,
                    alumno.getCiclo()
            );

            sentencia.setInt(
                    4,
                    alumno.getId()
            );

            sentencia.executeUpdate();

            System.out.println(
                    "Alumno actualizado correctamente."
            );

        } catch (Exception e) {

            System.out.println(
                    "Error al actualizar alumno."
            );

            System.out.println(e.getMessage());
        }
    }


    public void eliminar(int id) {

        String sql =
                "DELETE FROM alumnos WHERE id = ?";

        try (
                Connection conexion =
                        ConexionBD.conectar();

                PreparedStatement sentencia =
                        conexion.prepareStatement(sql)
        ) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();

            System.out.println(
                    "Alumno eliminado correctamente."
            );

        } catch (Exception e) {

            System.out.println(
                    "Error al eliminar alumno."
            );

            System.out.println(e.getMessage());
        }
    }
}