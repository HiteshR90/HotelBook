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
import javax.persistence.Version;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.hr.hotel.common.Constant;

@Entity
@Table(name = "xc_order_detail")
@DynamicUpdate(value = true)
public class OrderDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4519907225519470544L;

	// @Id
	// @Column(name = "order_detail_id")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer orderDetailId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_room_id", nullable = false)
	private HotelRoom hotelRoom;

	// @Column(name = "is_confirm")
	// private Boolean isConfirm;

	@Column(name = "is_cancel", nullable = false)
	private Boolean isCancel;

	@Column(name = "sell_flag", nullable = false)
	private Boolean sellFlag;

	// @JsonDeserialize(using = CustomDateDeserializer.class)
	@DateTimeFormat(pattern = Constant.DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date", nullable = false)
	private Date startDate;

	// @JsonDeserialize(using = CustomDateDeserializer.class)
	@DateTimeFormat(pattern = Constant.DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date", nullable = false)
	private Date endDate;

	@Version
	private Integer version;

	@OneToMany(mappedBy = "orderDetail")
	private List<PaymentToSeller> paymentToSellers = new ArrayList<PaymentToSeller>(
			0);

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public HotelRoom getHotelRoom() {
		return hotelRoom;
	}

	public void setHotelRoom(HotelRoom hotelRoom) {
		this.hotelRoom = hotelRoom;
	}

	public Boolean getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Boolean isCancel) {
		this.isCancel = isCancel;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<PaymentToSeller> getPaymentToSellers() {
		return paymentToSellers;
	}

	public void setPaymentToSellers(List<PaymentToSeller> paymentToSellers) {
		this.paymentToSellers = paymentToSellers;
	}

	public Boolean getSellFlag() {
		return sellFlag;
	}

	public void setSellFlag(Boolean sellFlag) {
		this.sellFlag = sellFlag;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
