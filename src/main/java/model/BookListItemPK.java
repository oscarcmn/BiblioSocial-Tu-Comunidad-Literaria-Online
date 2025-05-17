package model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * The primary key class for the book_list_items database table.
 *
 */
@Embeddable
public class BookListItemPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="list_id", insertable=false, updatable=false)
	private int listId;

	@Column(name="volume_id")
	private String volumeId;

	public BookListItemPK() {
	}
	public int getListId() {
		return this.listId;
	}
	public void setListId(int listId) {
		this.listId = listId;
	}
	public String getVolumeId() {
		return this.volumeId;
	}
	public void setVolumeId(String volumeId) {
		this.volumeId = volumeId;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BookListItemPK)) {
			return false;
		}
		BookListItemPK castOther = (BookListItemPK)other;
		return
			(this.listId == castOther.listId)
			&& this.volumeId.equals(castOther.volumeId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.listId;
		hash = hash * prime + this.volumeId.hashCode();

		return hash;
	}
}