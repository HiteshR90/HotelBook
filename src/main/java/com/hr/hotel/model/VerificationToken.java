package com.hr.hotel.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.hr.hotel.common.Constant;

/**
 * A token that gives the user permission to carry out a specific task once
 * within a determined time period. An example would be a Lost Password token.
 * The user receives the token embedded in a link. They send the token back to
 * the server by clicking the link and the action is processed
 * 
 */
@Entity
@Table(name = "rest_verification_token")
public class VerificationToken extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_EXPIRY_TIME_IN_MINS = 60 * 24; // 24 hours

	@Column(name = "token", length = 36, nullable = false)
	private final String token;

	@Column(name = "expire_at", nullable = false)
	@DateTimeFormat(pattern = Constant.DATE_FORMAT_WITH_TIME)
	@Temporal(TemporalType.TIMESTAMP)
	private Date expireAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "token_type", nullable = false)
	private VerificationTokenType tokenType;

	@Column(name = "is_used", nullable = false)
	private Boolean isUsed;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "redirect_url")
	private String redirectUrl;

	public VerificationToken() {
		super();
		this.token = UUID.randomUUID().toString();
		this.expireAt = calculateExpiryDate(DEFAULT_EXPIRY_TIME_IN_MINS);
	}

	public VerificationToken(User user, VerificationTokenType tokenType,
			int expirationTimeInMinutes) {
		this();
		this.user = user;
		this.tokenType = tokenType;
		this.expireAt = calculateExpiryDate(expirationTimeInMinutes);
	}

	public Date getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(Date expireAt) {
		this.expireAt = expireAt;
	}

	public VerificationTokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(VerificationTokenType tokenType) {
		this.tokenType = tokenType;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	private Date calculateExpiryDate(int expiryTimeInMinutes) {
		DateTime now = new DateTime();
		return now.plusMinutes(expiryTimeInMinutes).toDate();
	}

	public enum VerificationTokenType {

		lostPassword, emailRegistration
	}

	public boolean hasExpired() {
		DateTime tokenDate = new DateTime(getExpireAt());
		return tokenDate.isBeforeNow();
	}
}
