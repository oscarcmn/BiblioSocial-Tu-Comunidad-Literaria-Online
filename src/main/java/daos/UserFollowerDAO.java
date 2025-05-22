package daos;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import model.UserFollower;

public class UserFollowerDAO extends BaseJPADao{


    public static void save(UserFollower uf) {
    	EntityManager em=getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(uf);
        tx.commit();
    }

    public void delete(UserFollower uf) {
    	EntityManager em=getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.contains(uf) ? uf : em.merge(uf));
        tx.commit();
    }

    public List<UserFollower> findFollowersOfUser(int userId) {
    	EntityManager em=getEntityManager();
        TypedQuery<UserFollower> query = em.createQuery(
            "SELECT uf FROM UserFollower uf WHERE uf.user2.id = :userId", UserFollower.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public List<UserFollower> findFollowedByUser(int userId) {
    	EntityManager em=getEntityManager();
        TypedQuery<UserFollower> query = em.createQuery(
            "SELECT uf FROM UserFollower uf WHERE uf.user1.id = :userId", UserFollower.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public boolean isFollowing(int followerId, int followingId) {
    	EntityManager em=getEntityManager();
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(uf) FROM UserFollower uf WHERE uf.user1.id = :followerId AND uf.user2.id = :followingId", Long.class);
        query.setParameter("followerId", followerId);
        query.setParameter("followingId", followingId);
        return query.getSingleResult() > 0;
    }
    public static UserFollower findByIds(int followerId, int followedId) {
        try {
            EntityManager em = getEntityManager();
            TypedQuery<UserFollower> query = em.createQuery(
                "SELECT uf FROM UserFollower uf WHERE uf.user1.id = :followerId AND uf.user2.id = :followedId",
                UserFollower.class);
            query.setParameter("followerId", followerId);
            query.setParameter("followedId", followedId);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
