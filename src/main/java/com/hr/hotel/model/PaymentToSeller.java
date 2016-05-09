package com.hr.hotel.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "xc_payment_to_seller")
public class PaymentToSeller extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Id
	// @Column(name = "payment_to_seller")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer paymentToSeller;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_detail_id", nullable = false, unique = true)
	private OrderDetail orderDetail;

	@Column(name = "amount_to_transfer", nullable = false)
	private Double amountToTransfer;

	@Column(name = "transfer_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date transferDate;

	@Column(name = "total_amount")
	private Double totalAmount;

	@Column(name = "is_transfered", nullable = false)
	private Boolean isTransfered;

	@Column(name = "status")
	private String status;

	@Column(name = "correlation_id")
	private String correlationID;

	@Column(name = "time_stamp")
	private String timeStamp;

	@Column(name = "pay_key")
	private String payKey;

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Double getAmountToTransfer() {
		return amountToTransfer;
	}

	public void setAmountToTransfer(Double amountToTransfer) {
		this.amountToTransfer = amountToTransfer;
	}

	public Boolean getIsTransfered() {
		return isTransfered;
	}

	public void setIsTransfered(Boolean isTransfered) {
		this.isTransfered = isTransfered;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCorrelationID() {
		return correlationID;
	}

	public void setCorrelationID(String correlationID) {
		this.correlationID = correlationID;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getPayKey() {
		return payKey;
	}

	public void setPayKey(String payKey) {
		this.payKey = payKey;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
