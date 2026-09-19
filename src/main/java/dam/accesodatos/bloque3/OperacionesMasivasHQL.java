package dam.accesodatos.bloque3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class OperacionesMasivasHQL {

    public static void main(String[] args) {

        SessionFactory sessionFactory =
                HibernateUtil.getSessionFactory();

        Integer idTemporal = null;

        Transaction transaccion = null;

        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            transaccion =
                    session.beginTransaction();

            /*
             * Creamos un módulo temporal
             * para hacer las pruebas.
             */
            ModuloEntidad modulo =
                    new ModuloEntidad(
                            "Modulo Temporal"
                    );

            session.persist(modulo);

            /*
             * Fuerza el INSERT para obtener
             * el ID antes de continuar.
             */
            session.flush();

            idTemporal =
                    modulo.getId();

            System.out.println(
                    "Modulo temporal creado:"
            );

            System.out.println(modulo);

            /*
             * UPDATE masivo con HQL.
             */
            int actualizados =
                    session.createMutationQuery(
                            "UPDATE ModuloEntidad m "
                            + "SET m.nombre = :nombre "
                            + "WHERE m.id = :id"
                    )
                    .setParameter(
                            "nombre",
                            "Modulo Actualizado HQL"
                    )
                    .setParameter(
                            "id",
                            idTemporal
                    )
                    .executeUpdate();

            System.out.println(
                    "Registros actualizados: "
                            + actualizados
            );

            /*
             * Las operaciones masivas HQL
             * actúan directamente sobre la BD.
             *
             * Limpiamos la Session para evitar
             * quedarnos con objetos antiguos.
             */
            session.clear();

            ModuloEntidad actualizado =
                    session.find(
                            ModuloEntidad.class,
                            idTemporal
                    );

            System.out.println(
                    "Modulo despues del UPDATE:"
            );

            System.out.println(
                    actualizado
            );

            /*
             * DELETE masivo con HQL.
             */
            int eliminados =
                    session.createMutationQuery(
                            "DELETE FROM ModuloEntidad m "
                            + "WHERE m.id = :id"
                    )
                    .setParameter(
                            "id",
                            idTemporal
                    )
                    .executeUpdate();

            System.out.println(
                    "Registros eliminados: "
                            + eliminados
            );

            transaccion.commit();

        } catch (Exception e) {

            if (
                    transaccion != null
                            && transaccion.isActive()
            ) {

                transaccion.rollback();
            }

            System.out.println(
                    "Error en operaciones masivas."
            );

            System.out.println(
                    e.getMessage()
            );

        }

        /*
         * Comprobamos en una Session nueva
         * que el módulo ya no existe.
         */
        try (
                Session session =
                        sessionFactory.openSession()
        ) {

            ModuloEntidad comprobacion =
                    session.find(
                            ModuloEntidad.class,
                            idTemporal
                    );

            System.out.println(
                    "Comprobacion final:"
            );

            if (comprobacion == null) {

                System.out.println(
                        "El modulo temporal ya no existe."
                );

            } else {

                System.out.println(
                        comprobacion
                );
            }

        } finally {

            sessionFactory.close();
        }
    }
}