package dam.accesodatos.bloque3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RollbackHibernate {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        Session session = null;
        Transaction transaccion = null;

        Integer idPrimero = null;

        try {

            session =
                    sessionFactory.openSession();

            transaccion =
                    session.beginTransaction();

            /*
             * Primer módulo válido.
             */
            ModuloEntidad modulo1 =
                    new ModuloEntidad(
                            "Modulo antes del error"
                    );

            session.persist(modulo1);

            /*
             * Forzamos el INSERT.
             */
            session.flush();

            idPrimero =
                    modulo1.getId();

            System.out.println(
                    "Primer modulo insertado:"
            );

            System.out.println(
                    modulo1
            );

            /*
             * Segundo módulo incorrecto.
             *
             * nombre es NOT NULL,
             * así que PostgreSQL dará error.
             */
            ModuloEntidad modulo2 =
                    new ModuloEntidad(
                            null
                    );

            session.persist(modulo2);

            /*
             * Aquí se producirá el error.
             */
            session.flush();

            /*
             * No debería llegar aquí.
             */
            transaccion.commit();

        } catch (Exception e) {

            System.out.println(
                    "Se ha producido un error."
            );

            System.out.println(
                    "Mensaje:"
            );

            System.out.println(
                    e.getMessage()
            );

            /*
             * IMPORTANTE:
             * hacemos rollback ANTES
             * de cerrar la Session.
             */
            if (
                    transaccion != null
                            && transaccion.isActive()
            ) {

                try {

                    transaccion.rollback();

                    System.out.println(
                            "Rollback ejecutado correctamente."
                    );

                } catch (Exception rollbackError) {

                    System.out.println(
                            "Error durante rollback:"
                    );

                    System.out.println(
                            rollbackError.getMessage()
                    );
                }
            }

        } finally {

            if (
                    session != null
                            && session.isOpen()
            ) {

                session.close();
            }
        }

        /*
         * Nueva Session para comprobar
         * si el primer INSERT sobrevivió
         * al rollback.
         */
        try (
                Session sessionComprobacion =
                        sessionFactory.openSession()
        ) {

            ModuloEntidad comprobacion =
                    sessionComprobacion.find(
                            ModuloEntidad.class,
                            idPrimero
                    );

            System.out.println(
                    "=== COMPROBACION FINAL ==="
            );

            if (comprobacion == null) {

                System.out.println(
                        "El primer modulo NO existe."
                );

                System.out.println(
                        "El rollback deshizo toda la transaccion."
                );

            } else {

                System.out.println(
                        "El modulo sigue existiendo:"
                );

                System.out.println(
                        comprobacion
                );
            }

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