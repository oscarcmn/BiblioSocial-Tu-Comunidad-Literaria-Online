package model;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the book_list_items database table.
 *
 */
@Entity
@Table(name="book_list_items")
@NamedQuery(name="BookListItem.findAll", query="SELECT b FROM BookListItem b")
public class BookListItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BookListItemPK id;

	@Column(name="added_at")
	private Timestamp addedAt;

	//bi-directional many-to-one association to BookList
	@ManyToOne
	@JoinColumn(name="list_id")
	private BookList bookList;

	public BookListItem() {
	}

	public BookListItemPK getId() {
		return this.id;
	}

	public void setId(BookListItemPK id) {
		this.id = id;
	}

	public Timestamp getAddedAt() {
		return this.addedAt;
	}

	public void setAddedAt(Timestamp addedAt) {
		this.addedAt = addedAt;
	}

	public BookList getBookList() {
		return this.bookList;
	}

	public void setBookList(BookList bookList) {
		this.bookList = bookList;
	}

}