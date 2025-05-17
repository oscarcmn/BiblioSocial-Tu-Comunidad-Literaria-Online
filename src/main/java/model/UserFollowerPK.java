package model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * The primary key class for the user_followers database table.
 *
 */
@Embeddable
public class UserFollowerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="follower_id", insertable=false, updatable=false)
	private int followerId;

	@Column(name="followed_id", insertable=false, updatable=false)
	private int followedId;

	public UserFollowerPK() {
	}
	public int getFollowerId() {
		return this.followerId;
	}
	public void setFollowerId(int followerId) {
		this.followerId = followerId;
	}
	public int getFollowedId() {
		return this.followedId;
	}
	public void setFollowedId(int followedId) {
		this.followedId = followedId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserFollowerPK)) {
			return false;
		}
		UserFollowerPK castOther = (UserFollowerPK)other;
		return
			(this.followerId == castOther.followerId)
			&& (this.followedId == castOther.followedId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.followerId;
		hash = hash * prime + this.followedId;

		return hash;
	}
}