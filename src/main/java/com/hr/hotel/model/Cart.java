package com.hr.hotel.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.hr.hotel.common.Constant;

@Entity
@Table(name = "xc_cart")
public class Cart extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -381967422180376372L;

	// @Id
	// @Column(name = "cart_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Long cartId;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "add_by", nullable = false, updatable = false)
	private User addBy;

	@Column(name = "is_locked", nullable = false)
	private Boolean isLocked;

	@Column(name = "is_completed", nullable = false)
	private Boolean isCompleted;

	@Column(name = "unlock_at")
	@DateTimeFormat(pattern = Constant.DATE_FORMAT_WITH_TIME)
	@Temporal(TemporalType.TIMESTAMP)
	private Date unlockAt;

	@OneToMany(mappedBy = "cart")
	private List<CartDetail> cartDetails = new ArrayList<CartDetail>(0);

	@OneToMany(mappedBy = "cart")
	private List<Order> orders = new ArrayList<Order>(0);

	@OneToMany(mappedBy = "cart")
	private List<PaymentFromBuyer> paymentFromBuyers = new ArrayList<PaymentFromBuyer>(
			0);

	// public Long getCartId() {
	// return cartId;
	// }
	//
	// public void setCartId(Long cartId) {
	// this.cartId = cartId;
	// }

	public User getAddBy() {
		return addBy;
	}

	public void setAddBy(User addBy) {
		this.addBy = addBy;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Date getUnlockAt() {
		return unlockAt;
	}

	public void setUnlockAt(Date unlockAt) {
		this.unlockAt = unlockAt;
	}

	public List<CartDetail> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(List<CartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<PaymentFromBuyer> getPaymentFromBuyers() {
		return paymentFromBuyers;
	}

	public void setPaymentFromBuyers(List<PaymentFromBuyer> paymentFromBuyers) {
		this.paymentFromBuyers = paymentFromBuyers;
	}

	public void createCart(User loginUser,Date currentDate){
		this.setAddBy(loginUser);
		this.setCreatedBy(loginUser.getId());
		this.setIsCompleted(false);
		this.setIsLocked(false);
		this.setUnlockAt(currentDate);
	}
}