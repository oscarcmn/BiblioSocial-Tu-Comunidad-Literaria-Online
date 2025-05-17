package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
 * The persistent class for the reviews database table.
 *
 */
@Entity
@Table(name="reviews")
@NamedQuery(name="Review.findAll", query="SELECT r FROM Review r")
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="book_google_id")
	private String bookGoogleId;

	@Lob
	private String comment;

	@Column(name="created_at")
	private Timestamp createdAt;

	//bi-directional many-to-one association to ReviewComment
	@OneToMany(mappedBy="review")
	private List<ReviewComment> reviewComments;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Review() {
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

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public List<ReviewComment> getReviewComments() {
		return this.reviewComments;
	}

	public void setReviewComments(List<ReviewComment> reviewComments) {
		this.reviewComments = reviewComments;
	}

	public ReviewComment addReviewComment(ReviewComment reviewComment) {
		getReviewComments().add(reviewComment);
		reviewComment.setReview(this);

		return reviewComment;
	}

	public ReviewComment removeReviewComment(ReviewComment reviewComment) {
		getReviewComments().remove(reviewComment);
		reviewComment.setReview(null);

		return reviewComment;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}