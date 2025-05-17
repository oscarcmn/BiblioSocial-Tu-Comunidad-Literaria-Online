package daos;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import model.User;

public class UserDAO extends BaseJPADao{

	    public static void save(User user) {
	    	EntityManager em=getEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        em.persist(user);
	        tx.commit();
	    }


		public void update(User user) {
			EntityManager em=getEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        em.merge(user);
	        tx.commit();
	    }

	    public void delete(User user) {
	    	EntityManager em=getEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        em.remove(em.contains(user) ? user : em.merge(user));
	        tx.commit();
	    }

	    public User findById(int id) {
	    	EntityManager em=getEntityManager();
	        return em.find(User.class, id);
	    }

	    public static User findByEmail(String email) {
	        try {
	        	EntityManager em=getEntityManager();
	            TypedQuery<User> query = em.createQuery(
	                "SELECT u FROM User u WHERE u.email = :email", User.class);
	            query.setParameter("email", email);
	            return query.getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }


	    public List<User> findAll() {
	    	EntityManager em=getEntityManager();
	        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
	    }
	}
