package dam.accesodatos.bloque3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ActualizarAlumnoHibernate {

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
                        "Antes:"
                );

                System.out.println(alumno);

                alumno.setEdad(31);
                alumno.setCiclo("DAW");

                transaccion.commit();

                System.out.println();
                System.out.println(
                        "Despues:"
                );

                System.out.println(alumno);

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
                    "Error al actualizar alumno."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}