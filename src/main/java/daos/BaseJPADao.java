package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import listener.PersistenceAppListener;

public class BaseJPADao {

    /**
     * Default no-arg constructor
     */
    public BaseJPADao() {
    }

    /**
     * Returns a new JPA EntityManager instance using the factory initialized by the listener.
     * @return EntityManager
     */
    public static EntityManager getEntityManager() {
        EntityManagerFactory emf = PersistenceAppListener.getEntityManagerFactory();
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory is not initialized.");
        }
        return emf.createEntityManager();
    }
}