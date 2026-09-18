package dam.accesodatos.bloque2;

public class PruebaAlumnoDAO {

    public static void main(String[] args) {

        AlumnoDAO dao = new AlumnoDAO();

        // 1. LISTAR
        System.out.println("=== LISTA INICIAL ===");

        for (Alumno alumno : dao.listarTodos()) {
            System.out.println(alumno);
        }

        // 2. BUSCAR POR ID
        System.out.println();
        System.out.println("=== BUSCAR ID 2 ===");

        Alumno encontrado = dao.buscarPorId(2);

        if (encontrado != null) {
            System.out.println(encontrado);
        } else {
            System.out.println("Alumno no encontrado.");
        }

        // 3. INSERTAR
        System.out.println();
        System.out.println("=== INSERTAR ===");

        Alumno nuevo =
                new Alumno(
                        "Lucia",
                        22,
                        "DAM"
                );

        dao.insertar(nuevo);

        // 4. LISTAR OTRA VEZ
        System.out.println();
        System.out.println("=== DESPUES DEL INSERT ===");

        for (Alumno alumno : dao.listarTodos()) {
            System.out.println(alumno);
        }
    }
}