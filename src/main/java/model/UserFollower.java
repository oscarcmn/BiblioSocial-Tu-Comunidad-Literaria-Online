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
 * The persistent class for the user_followers database table.
 *
 */
@Entity
@Table(name="user_followers")
@NamedQuery(name="UserFollower.findAll", query="SELECT u FROM UserFollower u")
public class UserFollower implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserFollowerPK id;

	@Column(name="followed_at")
	private Timestamp followedAt;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="follower_id")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="followed_id")
	private User user2;

	public UserFollower() {
	}

	public UserFollowerPK getId() {
		return this.id;
	}

	public void setId(UserFollowerPK id) {
		this.id = id;
	}

	public Timestamp getFollowedAt() {
		return this.followedAt;
	}

	public void setFollowedAt(Timestamp followedAt) {
		this.followedAt = followedAt;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}