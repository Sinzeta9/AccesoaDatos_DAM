package dam.accesodatos.bloque2;

import java.util.Scanner;

public class MenuAlumnos {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        AlumnoDAO dao = new AlumnoDAO();

        int opcion;

        do {

            System.out.println();
            System.out.println("===== GESTION DE ALUMNOS =====");
            System.out.println("1. Listar alumnos");
            System.out.println("2. Buscar alumno por ID");
            System.out.println("3. Insertar alumno");
            System.out.println("4. Actualizar alumno");
            System.out.println("5. Eliminar alumno");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1 -> {

                    System.out.println();
                    System.out.println("=== LISTA DE ALUMNOS ===");

                    for (Alumno alumno : dao.listarTodos()) {
                        System.out.println(alumno);
                    }
                }

                case 2 -> {

                    System.out.print("ID del alumno: ");
                    int id = teclado.nextInt();
                    teclado.nextLine();

                    Alumno alumno = dao.buscarPorId(id);

                    if (alumno != null) {
                        System.out.println(alumno);
                    } else {
                        System.out.println("Alumno no encontrado.");
                    }
                }

                case 3 -> {

                    System.out.print("Nombre: ");
                    String nombre = teclado.nextLine();

                    System.out.print("Edad: ");
                    int edad = teclado.nextInt();
                    teclado.nextLine();

                    System.out.print("Ciclo: ");
                    String ciclo = teclado.nextLine();

                    Alumno nuevo =
                            new Alumno(
                                    nombre,
                                    edad,
                                    ciclo
                            );

                    dao.insertar(nuevo);
                }

                case 4 -> {

                    System.out.print(
                            "ID del alumno a actualizar: "
                    );

                    int id = teclado.nextInt();
                    teclado.nextLine();

                    Alumno alumno =
                            dao.buscarPorId(id);

                    if (alumno == null) {

                        System.out.println(
                                "Alumno no encontrado."
                        );

                        break;
                    }

                    System.out.print("Nuevo nombre: ");
                    String nombre = teclado.nextLine();

                    System.out.print("Nueva edad: ");
                    int edad = teclado.nextInt();
                    teclado.nextLine();

                    System.out.print("Nuevo ciclo: ");
                    String ciclo = teclado.nextLine();

                    alumno.setNombre(nombre);
                    alumno.setEdad(edad);
                    alumno.setCiclo(ciclo);

                    dao.actualizar(alumno);
                }

                case 5 -> {

                    System.out.print(
                            "ID del alumno a eliminar: "
                    );

                    int id = teclado.nextInt();
                    teclado.nextLine();

                    Alumno alumno =
                            dao.buscarPorId(id);

                    if (alumno == null) {

                        System.out.println(
                                "Alumno no encontrado."
                        );

                    } else {

                        System.out.println(
                                "Se eliminara:"
                        );

                        System.out.println(alumno);

                        dao.eliminar(id);
                    }
                }

                case 0 ->
                        System.out.println(
                                "Programa finalizado."
                        );

                default ->
                        System.out.println(
                                "Opcion no valida."
                        );
            }

        } while (opcion != 0);

        teclado.close();
    }
}