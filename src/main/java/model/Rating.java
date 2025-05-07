package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ratings database table.
 * 
 */
@Entity
@Table(name="ratings")
@NamedQuery(name="Rating.findAll", query="SELECT r FROM Rating r")
public class Rating implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="book_google_id")
	private String bookGoogleId;

	@Column(name="created_at")
	private Timestamp createdAt;

	private int rating;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Rating() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookGoogleId() {
		return this.bookGoogleId;
	}

	public void setBookGoogleId(String bookGoogleId) {
		this.bookGoogleId = bookGoogleId;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}