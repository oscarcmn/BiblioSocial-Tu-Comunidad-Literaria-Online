package daos;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Rating;

public class RatingDAO extends BaseJPADao{


    public RatingDAO() {
		super();
	}

	public void save(Rating rating) {
    	EntityManager em=getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(rating);
        tx.commit();
    }

    public void update(Rating rating) {
    	EntityManager em=getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(rating);
        tx.commit();
    }

    public void delete(Rating rating) {
    	EntityManager em=getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.contains(rating) ? rating : em.merge(rating));
        tx.commit();
    }

    public Rating findById(int id) {
    	EntityManager em=getEntityManager();
        return em.find(Rating.class, id);
    }

    public List<Rating> findByBookGoogleId(String volumeId) {
    	EntityManager em=getEntityManager();
        TypedQuery<Rating> query = em.createQuery(
            "SELECT r FROM Rating r WHERE r.bookGoogleId = :volumeId", Rating.class);
        query.setParameter("volumeId", volumeId);
        return query.getResultList();
    }

    public List<Rating> findByUserId(int userId) {
    	EntityManager em=getEntityManager();
        TypedQuery<Rating> query = em.createQuery(
            "SELECT r FROM Rating r WHERE r.user.id = :userId", Rating.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public double getAverageRatingForBook(String volumeId) {
    	EntityManager em=getEntityManager();
        TypedQuery<Double> query = em.createQuery(
            "SELECT AVG(r.rating) FROM Rating r WHERE r.bookGoogleId = :volumeId", Double.class);
        query.setParameter("volumeId", volumeId);
        Double result = query.getSingleResult();
        return result != null ? result : 0.0;
    }
    public static Rating findByUserAndBook(int userId, String volumeId) {
    	EntityManager em=getEntityManager();
        TypedQuery<Rating> query = em.createQuery(
            "SELECT r FROM Rating r WHERE r.user.id = :userId AND r.bookGoogleId = :volumeId", Rating.class);
        query.setParameter("userId", userId);
        query.setParameter("volumeId", volumeId);

        List<Rating> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}

