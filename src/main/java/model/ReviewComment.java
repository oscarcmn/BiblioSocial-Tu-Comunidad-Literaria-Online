package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the review_comments database table.
 * 
 */
@Entity
@Table(name="review_comments")
@NamedQuery(name="ReviewComment.findAll", query="SELECT r FROM ReviewComment r")
public class ReviewComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="commented_at")
	private Timestamp commentedAt;

	@Lob
	private String content;

	//bi-directional many-to-one association to Review
	@ManyToOne
	private Review review;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public ReviewComment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCommentedAt() {
		return this.commentedAt;
	}

	public void setCommentedAt(Timestamp commentedAt) {
		this.commentedAt = commentedAt;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Review getReview() {
		return this.review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}