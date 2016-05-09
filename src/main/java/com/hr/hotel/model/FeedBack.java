package com.hr.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "xc_feedback")
public class FeedBack {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "page_name", nullable = false, length = 20)
	private String pageName;

	@NotBlank(message = "Please select type")
	@Column(name = "fb_type", nullable = false, length = 10)
	private String feedBackType;

	@NotBlank(message = "Please eneter feedback")
	@Column(name = "fb_note", nullable = false)
	private String feedbackNote;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getFeedBackType() {
		return feedBackType;
	}

	public void setFeedBackType(String feedBackType) {
		this.feedBackType = feedBackType;
	}

	public String getFeedbackNote() {
		return feedbackNote;
	}

	public void setFeedbackNote(String feedbackNote) {
		this.feedbackNote = feedbackNote;
	}

}
