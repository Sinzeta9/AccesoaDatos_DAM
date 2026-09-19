package dam.accesodatos.bloque3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FlushVsCommitHibernate {

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

            AlumnoCursoEntidad alumno =
                    session.find(
                            AlumnoCursoEntidad.class,
                            1
                    );

            System.out.println(
                    "Edad antes del cambio: "
                            + alumno.getEdad()
            );

            alumno.setEdad(24);

            System.out.println(
                    "Edad cambiada en Java: "
                            + alumno.getEdad()
            );

            /*
             * Fuerza a Hibernate a enviar
             * el UPDATE a PostgreSQL.
             *
             * Pero NO confirma
             * la transaccion.
             */
            session.flush();

            System.out.println(
                    "flush() ejecutado."
            );

            System.out.println(
                    "El UPDATE ha sido enviado,"
                            + " pero aun no hay commit."
            );

            /*
             * Deshacemos la transaccion.
             */
            transaccion.rollback();

            System.out.println(
                    "rollback() ejecutado."
            );

        } catch (Exception e) {

            if (
                    transaccion != null
                            && transaccion.isActive()
            ) {
                transaccion.rollback();
            }

            System.out.println(
                    "Error en flush/commit."
            );

            System.out.println(
                    e.getMessage()
            );
        }

        /*
         * Nueva Session para comprobar
         * el valor real en PostgreSQL.
         */
        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            AlumnoCursoEntidad alumno =
                    session.find(
                            AlumnoCursoEntidad.class,
                            1
                    );

            System.out.println(
                    "Edad almacenada finalmente: "
                            + alumno.getEdad()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error al comprobar resultado."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}