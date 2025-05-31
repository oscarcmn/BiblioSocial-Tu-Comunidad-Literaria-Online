package daos;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.BookListItem;


public class BookListItemDAO extends BaseJPADao{

    public static void save(BookListItem item) {
    	EntityManager em=getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(item);
        tx.commit();
    }

    public static void delete(BookListItem item) {
    	EntityManager em=getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(em.contains(item) ? item : em.merge(item));
        tx.commit();
    }

    public static List<BookListItem> findByListId(int listId) {
    	EntityManager em=getEntityManager();
        TypedQuery<BookListItem> query = em.createQuery(
            "SELECT i FROM BookListItem i WHERE i.bookList.id = :listId", BookListItem.class);
        query.setParameter("listId", listId);
        return query.getResultList();
    }

    public static BookListItem findByListIdAndBookId(int listId, String volumeId) {
    	EntityManager em = getEntityManager();
        TypedQuery<BookListItem> query = em.createQuery(
            "SELECT i FROM BookListItem i WHERE i.id.listId = :listId AND i.id.volumeId = :volumeId", 
            BookListItem.class);
        query.setParameter("listId", listId);
        query.setParameter("volumeId", volumeId);

        List<BookListItem> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

}
