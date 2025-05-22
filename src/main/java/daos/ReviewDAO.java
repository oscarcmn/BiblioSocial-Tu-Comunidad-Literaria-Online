package daos;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Review;

public class ReviewDAO extends BaseJPADao{

    public ReviewDAO() {
		super();
	}

	public void save(Review review) {
    	EntityManager em=getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(review);
        tx.commit();
    }

    public void update(Review review) {
    	EntityManager em=getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(review);
        tx.commit();
    }

    public void delete(Review review) {
    	EntityManager em=getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.contains(review) ? review : em.merge(review));
        tx.commit();
    }

    public static Review findById(int id) {
    	EntityManager em=getEntityManager();
        return em.find(Review.class, id);
    }

    public List<Review> findAll() {
    	EntityManager em=getEntityManager();
        return em.createQuery("SELECT r FROM Review r", Review.class).getResultList();
    }

    public List<Review> findByUserId(int userId) {
    	EntityManager em=getEntityManager();
        TypedQuery<Review> query = em.createQuery(
            "SELECT r FROM Review r WHERE r.user.id = :userId", Review.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public List<Review> findByVolumeId(String volumeId) {
    	EntityManager em=getEntityManager();
        TypedQuery<Review> query = em.createQuery(
            "SELECT r FROM Review r WHERE r.bookGoogleId = :volumeId", Review.class);
        query.setParameter("volumeId", volumeId);
        return query.getResultList();
    }
    public Review findByUserAndBook(int userId, String volumeId) {
    	EntityManager em=getEntityManager();
        TypedQuery<Review> query = em.createQuery(
            "SELECT r FROM Review r WHERE r.user.id = :userId AND r.bookGoogleId = :volumeId", Review.class);
        query.setParameter("userId", userId);
        query.setParameter("volumeId", volumeId);
        List<Review> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
    public static List<Review> findReviewsByFollowedUsers(int userId) {
        EntityManager em = getEntityManager();
        return em.createQuery("""
                SELECT r FROM Review r
                WHERE r.user.id IN (
                    SELECT uf.id.followedId FROM UserFollower uf WHERE uf.id.followerId = :userId
                )
                ORDER BY r.createdAt DESC
                """, Review.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
