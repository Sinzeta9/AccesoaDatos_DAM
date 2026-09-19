package dam.accesodatos.bloque3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EstadosEntidadHibernate {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        AlumnoCursoEntidad alumno;

        /*
         * 1. ENTIDAD PERSISTENT
         */
        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            alumno =
                    session.find(
                            AlumnoCursoEntidad.class,
                            1
                    );

            System.out.println(
                    "Alumno cargado:"
            );

            System.out.println(alumno);
        }

        /*
         * La Session anterior ya está cerrada.
         *
         * El objeto alumno sigue existiendo,
         * pero ahora está DETACHED.
         */

        System.out.println(
                "Session cerrada."
        );

        System.out.println(
                "El alumno ahora esta DETACHED."
        );

        alumno.setEdad(23);

        /*
         * Este cambio se ha hecho sobre
         * un objeto detached.
         *
         * Hibernate todavía no lo está
         * sincronizando con PostgreSQL.
         */

        Transaction transaccion = null;

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            transaccion =
                    session.beginTransaction();

            /*
             * merge devuelve una entidad
             * gestionada nuevamente por Hibernate.
             */

            AlumnoCursoEntidad alumnoGestionado =
                    session.merge(alumno);

            transaccion.commit();

            System.out.println(
                    "Entidad fusionada con merge()."
            );

            System.out.println(
                    "Alumno actualizado:"
            );

            System.out.println(
                    alumnoGestionado
            );

        } catch (Exception e) {

            if (transaccion != null) {
                transaccion.rollback();
            }

            System.out.println(
                    "Error al trabajar con la entidad."
            );

            System.out.println(
                    e.getMessage()
            );

        } finally {

            sessionFactory.close();
        }
    }
}