package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Rating;

import java.util.List;

public class RatingDAO {
    private EntityManager em;

    public RatingDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Rating rating) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(rating);
        tx.commit();
    }

    public void update(Rating rating) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(rating);
        tx.commit();
    }

    public void delete(Rating rating) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.contains(rating) ? rating : em.merge(rating));
        tx.commit();
    }

    public Rating findById(int id) {
        return em.find(Rating.class, id);
    }

    public List<Rating> findByBookGoogleId(String volumeId) {
        TypedQuery<Rating> query = em.createQuery(
            "SELECT r FROM Rating r WHERE r.bookGoogleId = :volumeId", Rating.class);
        query.setParameter("volumeId", volumeId);
        return query.getResultList();
    }

    public List<Rating> findByUserId(int userId) {
        TypedQuery<Rating> query = em.createQuery(
            "SELECT r FROM Rating r WHERE r.user.id = :userId", Rating.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public double getAverageRatingForBook(String volumeId) {
        TypedQuery<Double> query = em.createQuery(
            "SELECT AVG(r.rating) FROM Rating r WHERE r.bookGoogleId = :volumeId", Double.class);
        query.setParameter("volumeId", volumeId);
        Double result = query.getSingleResult();
        return result != null ? result : 0.0;
    }
}

