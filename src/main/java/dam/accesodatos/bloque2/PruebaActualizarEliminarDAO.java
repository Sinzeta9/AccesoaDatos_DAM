package dam.accesodatos.bloque2;

public class PruebaActualizarEliminarDAO {

    public static void main(String[] args) {

        AlumnoDAO dao = new AlumnoDAO();

        // Buscar a Lucia
        Alumno lucia = dao.buscarPorId(43);

        if (lucia == null) {

            System.out.println(
                    "No existe el alumno con ID 43."
            );

            return;
        }

        System.out.println("=== ANTES ===");
        System.out.println(lucia);

        // ACTUALIZAR
        lucia.setEdad(23);
        lucia.setCiclo("DAW");

        dao.actualizar(lucia);

        System.out.println();
        System.out.println("=== DESPUES DEL UPDATE ===");

        Alumno actualizado =
                dao.buscarPorId(43);

        System.out.println(actualizado);

        // ELIMINAR
        System.out.println();
        System.out.println("=== DELETE ===");

        dao.eliminar(43);

        Alumno eliminado =
                dao.buscarPorId(43);

        if (eliminado == null) {
            System.out.println(
                    "El alumno con ID 43 ya no existe."
            );
        }
    }
}