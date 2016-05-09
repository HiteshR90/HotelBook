package com.hr.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "xc_book_from")
@Entity
public class BookFrom extends BaseEntity {

	// @Id
	// @Column(name = "book_from_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Long bookFromId;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4594479656070302801L;

	@Column(name = "book_from", nullable = false, length = 50, unique = true)
	private String bookFrom;

	@Column(name = "book_from_desc")
	private String bookFromDesc;

	@Column(name = "active")
	private Boolean active;

	public String getBookFrom() {
		return bookFrom;
	}

	public void setBookFrom(String bookFrom) {
		this.bookFrom = bookFrom;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
