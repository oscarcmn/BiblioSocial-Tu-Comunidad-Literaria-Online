package daos;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.BookList;

public class BookListDAO extends BaseJPADao{

	    public static void save(BookList list) {
	    	EntityManager em=getEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        em.persist(list);
	        tx.commit();
	    }

	    public void update(BookList list) {
	    	EntityManager em=getEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        em.merge(list);
	        tx.commit();
	    }

	    public static void delete(BookList list) {
	    	EntityManager em=getEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        em.remove(em.contains(list) ? list : em.merge(list));
	        tx.commit();
	    }

	    public static BookList findById(int id) {
	    	EntityManager em=getEntityManager();
	        return em.find(BookList.class, id);
	    }

	    public static List<BookList> findByUserId(int userId) {
	    	EntityManager em=getEntityManager();
	    	TypedQuery<BookList> query = em.createQuery(
	            "SELECT bl FROM BookList bl WHERE bl.user.id = :userId", BookList.class);
	        query.setParameter("userId", userId);
	        return query.getResultList();
	    }
}
