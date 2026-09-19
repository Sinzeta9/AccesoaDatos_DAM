package dam.accesodatos.bloque3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EliminarAlumnoHibernate {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        Transaction transaccion = null;

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            transaccion =
                    session.beginTransaction();

            AlumnoEntidad alumno =
                    session.find(
                            AlumnoEntidad.class,
                            45
                    );

            if (alumno != null) {

                System.out.println(
                        "Se eliminara:"
                );

                System.out.println(alumno);

                session.remove(alumno);

                transaccion.commit();

                System.out.println(
                        "Alumno eliminado correctamente."
                );

            } else {

                System.out.println(
                        "Alumno no encontrado."
                );

                transaccion.rollback();
            }

        } catch (Exception e) {

            if (transaccion != null) {
                transaccion.rollback();
            }

            System.out.println(
                    "Error al eliminar alumno."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}