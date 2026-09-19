package dam.accesodatos.bloque3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BuscarAlumnoHibernate {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            AlumnoEntidad alumno =
                    session.find(
                            AlumnoEntidad.class,
                            45
                    );

            if (alumno != null) {

                System.out.println(
                        "Alumno encontrado:"
                );

                System.out.println(alumno);

            } else {

                System.out.println(
                        "Alumno no encontrado."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error al buscar alumno."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}