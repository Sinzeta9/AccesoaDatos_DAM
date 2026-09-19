package dam.accesodatos.bloque3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class InsertarAlumnoHibernate {

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
                    new AlumnoEntidad(
                            "Hibernate",
                            30,
                            "DAM"
                    );

            session.persist(alumno);

            transaccion.commit();

            System.out.println(
                    "Alumno insertado correctamente."
            );

            System.out.println(
                    "ID generado: "
                            + alumno.getId()
            );

        } catch (Exception e) {

            if (transaccion != null) {
                transaccion.rollback();
            }

            System.out.println(
                    "Error al insertar alumno."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}