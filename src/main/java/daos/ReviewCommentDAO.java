package daos;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.ReviewComment;

public class ReviewCommentDAO extends BaseJPADao{

    public static void save(ReviewComment comment) {
    	EntityManager em=getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(comment);
        tx.commit();
    }

    public void update(ReviewComment comment) {
    	EntityManager em=getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(comment);
        tx.commit();
    }

    public void delete(ReviewComment comment) {
    	EntityManager em=getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.contains(comment) ? comment : em.merge(comment));
        tx.commit();
    }

    public ReviewComment findById(int id) {
    	EntityManager em=getEntityManager();
        return em.find(ReviewComment.class, id);
    }

    public static List<ReviewComment> findByReviewId(int reviewId) {
    	EntityManager em=getEntityManager();
        TypedQuery<ReviewComment> query = em.createQuery("SELECT c FROM ReviewComment c WHERE c.review.id = :reviewId", ReviewComment.class);
        query.setParameter("reviewId", reviewId);
        return query.getResultList();
    }
}
