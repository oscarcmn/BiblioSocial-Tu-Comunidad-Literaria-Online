package daos;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Review;

public class ReviewDAO {

	private EntityManager em;

    public ReviewDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Review review) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(review);
        tx.commit();
    }

    public void update(Review review) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(review);
        tx.commit();
    }

    public void delete(Review review) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.contains(review) ? review : em.merge(review));
        tx.commit();
    }

    public Review findById(int id) {
        return em.find(Review.class, id);
    }

    public List<Review> findAll() {
        return em.createQuery("SELECT r FROM Review r", Review.class).getResultList();
    }

    public List<Review> findByUserId(int userId) {
        TypedQuery<Review> query = em.createQuery(
            "SELECT r FROM Review r WHERE r.user.id = :userId", Review.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public List<Review> findByVolumeId(String volumeId) {
        TypedQuery<Review> query = em.createQuery(
            "SELECT r FROM Review r WHERE r.bookGoogleId = :volumeId", Review.class);
        query.setParameter("volumeId", volumeId);
        return query.getResultList();
    }
}
