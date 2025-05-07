package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the book_lists database table.
 * 
 */
@Entity
@Table(name="book_lists")
@NamedQuery(name="BookList.findAll", query="SELECT b FROM BookList b")
public class BookList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="created_at")
	private Timestamp createdAt;

	private String name;

	//bi-directional many-to-one association to BookListItem
	@OneToMany(mappedBy="bookList")
	private List<BookListItem> bookListItems;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public BookList() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BookListItem> getBookListItems() {
		return this.bookListItems;
	}

	public void setBookListItems(List<BookListItem> bookListItems) {
		this.bookListItems = bookListItems;
	}

	public BookListItem addBookListItem(BookListItem bookListItem) {
		getBookListItems().add(bookListItem);
		bookListItem.setBookList(this);

		return bookListItem;
	}

	public BookListItem removeBookListItem(BookListItem bookListItem) {
		getBookListItems().remove(bookListItem);
		bookListItem.setBookList(null);

		return bookListItem;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}