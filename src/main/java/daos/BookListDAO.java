package daos;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.BookList;

public class BookListDAO {

	 private EntityManager em;

	    public BookListDAO(EntityManager em) {
	        this.em = em;
	    }

	    public void save(BookList list) {
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        em.persist(list);
	        tx.commit();
	    }

	    public void update(BookList list) {
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        em.merge(list);
	        tx.commit();
	    }

	    public void delete(BookList list) {
	        EntityTransaction tx = em.getTransaction();
	        tx.begin();
	        em.remove(em.contains(list) ? list : em.merge(list));
	        tx.commit();
	    }

	    public BookList findById(int id) {
	        return em.find(BookList.class, id);
	    }

	    public List<BookList> findByUserId(int userId) {
	    	TypedQuery<BookList> query = em.createQuery(
	            "SELECT bl FROM BookList bl WHERE bl.user.id = :userId", BookList.class);
	        query.setParameter("userId", userId);
	        return query.getResultList();
	    }
}
