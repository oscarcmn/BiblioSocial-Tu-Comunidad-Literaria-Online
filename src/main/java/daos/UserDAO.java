package daos;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import model.User;

public class UserDAO {

	    private EntityManager em;

	    public UserDAO(EntityManager em) {
	        this.em = em;
	    }

	    public void save(User user) {
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        em.persist(user);
	        tx.commit();
	    }

	    public void update(User user) {
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        em.merge(user);
	        tx.commit();
	    }

	    public void delete(User user) {
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        em.remove(em.contains(user) ? user : em.merge(user));
	        tx.commit();
	    }

	    public User findById(int id) {
	        return em.find(User.class, id);
	    }

	    public User findByEmail(String email) {
	        try {
	            TypedQuery<User> query = em.createQuery(
	                "SELECT u FROM User u WHERE u.email = :email", User.class);
	            query.setParameter("email", email);
	            return query.getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }

	    public List<User> findAll() {
	        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
	    }
	}
