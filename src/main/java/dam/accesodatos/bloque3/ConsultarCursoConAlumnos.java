package dam.accesodatos.bloque3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ConsultarCursoConAlumnos {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            CursoEntidad curso =
                    session.find(
                            CursoEntidad.class,
                            1
                    );

            if (curso != null) {

                System.out.println(
                        "Curso: " + curso
                );

                System.out.println(
                        "=== ALUMNOS ==="
                );

                for (
                        AlumnoCursoEntidad alumno
                        : curso.getAlumnos()
                ) {

                    System.out.println(alumno);
                }

            } else {

                System.out.println(
                        "Curso no encontrado."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al consultar curso."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}