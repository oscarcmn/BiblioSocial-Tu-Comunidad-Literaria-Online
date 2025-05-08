package daos;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.UserFollower;

public class UserFollowerDAO {
	private EntityManager em;

    public UserFollowerDAO(EntityManager em) {
        this.em = em;
    }

    public void save(UserFollower uf) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(uf);
        tx.commit();
    }

    public void delete(UserFollower uf) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.contains(uf) ? uf : em.merge(uf));
        tx.commit();
    }

    public List<UserFollower> findFollowersOfUser(int userId) {
        TypedQuery<UserFollower> query = em.createQuery(
            "SELECT uf FROM UserFollower uf WHERE uf.user2.id = :userId", UserFollower.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public List<UserFollower> findFollowedByUser(int userId) {
        TypedQuery<UserFollower> query = em.createQuery(
            "SELECT uf FROM UserFollower uf WHERE uf.user1.id = :userId", UserFollower.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public boolean isFollowing(int followerId, int followingId) {
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(uf) FROM UserFollower uf WHERE uf.user1.id = :followerId AND uf.user2.id = :followingId", Long.class);
        query.setParameter("followerId", followerId);
        query.setParameter("followingId", followingId);
        return query.getSingleResult() > 0;
    }
}
