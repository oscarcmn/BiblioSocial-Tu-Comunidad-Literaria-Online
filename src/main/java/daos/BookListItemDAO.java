package daos;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.BookListItem;


public class BookListItemDAO {
	private EntityManager em;

    public BookListItemDAO(EntityManager em) {
        this.em = em;
    }

    public void save(BookListItem item) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(item);
        tx.commit();
    }

    public void delete(BookListItem item) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.contains(item) ? item : em.merge(item));
        tx.commit();
    }

    public List<BookListItem> findByListId(int listId) {
        TypedQuery<BookListItem> query = em.createQuery(
            "SELECT i FROM BookListItem i WHERE i.bookList.id = :listId", BookListItem.class);
        query.setParameter("listId", listId);
        return query.getResultList();
    }

    public boolean existsInList(int listId, String bookGoogleId) {
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(i) FROM BookListItem i WHERE i.bookList.id = :listId AND i.bookGoogleId = :bookId", Long.class);
        query.setParameter("listId", listId);
        query.setParameter("bookId", bookGoogleId);
        return query.getSingleResult() > 0;
    }
}
