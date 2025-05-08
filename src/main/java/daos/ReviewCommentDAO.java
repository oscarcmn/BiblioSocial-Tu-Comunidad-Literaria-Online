package daos;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.ReviewComment;

public class ReviewCommentDAO {

	private EntityManager em;

    public ReviewCommentDAO(EntityManager em) {
        this.em = em;
    }

    public void save(ReviewComment comment) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(comment);
        tx.commit();
    }

    public void update(ReviewComment comment) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(comment);
        tx.commit();
    }

    public void delete(ReviewComment comment) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.contains(comment) ? comment : em.merge(comment));
        tx.commit();
    }

    public ReviewComment findById(int id) {
        return em.find(ReviewComment.class, id);
    }

    public List<ReviewComment> findByReviewId(int reviewId) {
        TypedQuery<ReviewComment> query = em.createQuery("SELECT c FROM ReviewComment c WHERE c.review.id = :reviewId", ReviewComment.class);
        query.setParameter("reviewId", reviewId);
        return query.getResultList();
    }
}
