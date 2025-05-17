package model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
 * The persistent class for the users database table.
 *
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	@Column(name="password_hash")
	private String passwordHash;

	private String username;

	//bi-directional many-to-one association to BookList
	@OneToMany(mappedBy="user")
	private List<BookList> bookLists;

	//bi-directional many-to-one association to Rating
	@OneToMany(mappedBy="user")
	private List<Rating> ratings;

	//bi-directional many-to-one association to ReviewComment
	@OneToMany(mappedBy="user")
	private List<ReviewComment> reviewComments;

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="user")
	private List<Review> reviews;

	//bi-directional many-to-one association to UserFollower
	@OneToMany(mappedBy="user1")
	private List<UserFollower> userFollowers1;

	//bi-directional many-to-one association to UserFollower
	@OneToMany(mappedBy="user2")
	private List<UserFollower> userFollowers2;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<BookList> getBookLists() {
		return this.bookLists;
	}

	public void setBookLists(List<BookList> bookLists) {
		this.bookLists = bookLists;
	}

	public BookList addBookList(BookList bookList) {
		getBookLists().add(bookList);
		bookList.setUser(this);

		return bookList;
	}

	public BookList removeBookList(BookList bookList) {
		getBookLists().remove(bookList);
		bookList.setUser(null);

		return bookList;
	}

	public List<Rating> getRatings() {
		return this.ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public Rating addRating(Rating rating) {
		getRatings().add(rating);
		rating.setUser(this);

		return rating;
	}

	public Rating removeRating(Rating rating) {
		getRatings().remove(rating);
		rating.setUser(null);

		return rating;
	}

	public List<ReviewComment> getReviewComments() {
		return this.reviewComments;
	}

	public void setReviewComments(List<ReviewComment> reviewComments) {
		this.reviewComments = reviewComments;
	}

	public ReviewComment addReviewComment(ReviewComment reviewComment) {
		getReviewComments().add(reviewComment);
		reviewComment.setUser(this);

		return reviewComment;
	}

	public ReviewComment removeReviewComment(ReviewComment reviewComment) {
		getReviewComments().remove(reviewComment);
		reviewComment.setUser(null);

		return reviewComment;
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review addReview(Review review) {
		getReviews().add(review);
		review.setUser(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setUser(null);

		return review;
	}

	public List<UserFollower> getUserFollowers1() {
		return this.userFollowers1;
	}

	public void setUserFollowers1(List<UserFollower> userFollowers1) {
		this.userFollowers1 = userFollowers1;
	}

	public UserFollower addUserFollowers1(UserFollower userFollowers1) {
		getUserFollowers1().add(userFollowers1);
		userFollowers1.setUser1(this);

		return userFollowers1;
	}

	public UserFollower removeUserFollowers1(UserFollower userFollowers1) {
		getUserFollowers1().remove(userFollowers1);
		userFollowers1.setUser1(null);

		return userFollowers1;
	}

	public List<UserFollower> getUserFollowers2() {
		return this.userFollowers2;
	}

	public void setUserFollowers2(List<UserFollower> userFollowers2) {
		this.userFollowers2 = userFollowers2;
	}

	public UserFollower addUserFollowers2(UserFollower userFollowers2) {
		getUserFollowers2().add(userFollowers2);
		userFollowers2.setUser2(this);

		return userFollowers2;
	}

	public UserFollower removeUserFollowers2(UserFollower userFollowers2) {
		getUserFollowers2().remove(userFollowers2);
		userFollowers2.setUser2(null);

		return userFollowers2;
	}

}