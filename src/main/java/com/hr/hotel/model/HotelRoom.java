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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.joda.time.Days;
import org.joda.time.LocalDate;

@Entity
@Table(name = "xc_hotel_room")
@DynamicUpdate
@DynamicInsert
public class HotelRoom extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1506033790745180461L;

	// public interface RoomDetail {
	// }
	//
	// public interface CheckDateAvailable {
	//
	// }

	// @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// @Column(name = "room_id")
	// private Integer roomId;

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(value = CascadeType.ALL)
	@JoinColumn(name = "hotel_id", nullable = false)
	private HotelMaster hotel;

	// @Column(name = "category")
	// private String category;

	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade(value = CascadeType.ALL)
	@JoinColumn(name = "room_type_id", nullable = false)
	private RoomType roomType;

	@Column(name = "no_of_rooms", length = 2)
	private Integer noOfRooms;

	@Column(name = "no_of_adults", length = 2)
	private Integer noOfAdults;

	@Column(name = "no_of_children", length = 2)
	private Integer noOfChildren;

	@Column(name = "checkin_time")
	private Date checkInTime;

	@Column(name = "checkout_time")
	private Date checkOutTime;

	@Column(name = "one_night_cost", nullable = false)
	private Double oneNightCost;

	@Column(name = "start_date", nullable = false)
	// @NotNull(groups = { RoomDetail.class, CheckDateAvailable.class })
	// @Future(groups = { RoomDetail.class, CheckDateAvailable.class })
	// @JsonDeserialize(using = CustomDateDeserializer.class)
	// @DateTimeFormat(pattern = Constant.DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Column(name = "end_date", nullable = false)
	// @NotNull(groups = { RoomDetail.class, CheckDateAvailable.class })
	// @Future(groups = { RoomDetail.class, CheckDateAvailable.class })
	// @JsonDeserialize(using = CustomDateDeserializer.class)
	// @DateTimeFormat(pattern = Constant.DATE_FORMAT)
	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Column(name = "is_full_book", nullable = false)
	private Boolean isFullBooked;

	@Column(name = "is_full_suite", nullable = false)
	private Boolean isFullSuite;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "room_desc")
	private String roomDesc;

	@Version
	private Integer version;

	@Column(name = "book_at")
	private Date bookAt;

	@OneToMany(mappedBy = "hotelRoom")
	private List<RoomAmenity> roomAmenities = new ArrayList<RoomAmenity>(0);

	@OneToMany(mappedBy = "hotelRoom")
	private List<RoomPhoto> roomPhotos = new ArrayList<RoomPhoto>(0);

	@OneToMany(mappedBy = "hotelRoom")
	private List<Sell> sells = new ArrayList<Sell>(0);

	@OneToMany(mappedBy = "hotelRoom")
	private List<Rating> ratings = new ArrayList<Rating>(0);

	// @OneToMany(mappedBy = "hotelRoom")
	// private List<RoomBlock> roomBlocks = new ArrayList<RoomBlock>(0);

	@OneToMany(mappedBy = "hotelRoom")
	private List<CartDetail> cartDetails = new ArrayList<CartDetail>(0);

	// @OneToMany(mappedBy = "roomId")
	// private List<HotelRating> hotelRatings = new ArrayList<HotelRating>();

	// public String getCategory() {
	// return category;
	// }
	//
	// public void setCategory(String category) {
	// this.category = category;
	// }

	public Integer getNoOfRooms() {
		return noOfRooms;
	}

	public HotelMaster getHotel() {
		return hotel;
	}

	public void setHotel(HotelMaster hotel) {
		this.hotel = hotel;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public void setNoOfRooms(Integer noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public Integer getNoOfAdults() {
		return noOfAdults;
	}

	public void setNoOfAdults(Integer noOfAdults) {
		this.noOfAdults = noOfAdults;
	}

	public Integer getNoOfChildren() {
		return noOfChildren;
	}

	public void setNoOfChildren(Integer noOfChildren) {
		this.noOfChildren = noOfChildren;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Date getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public Double getOneNightCost() {
		return oneNightCost;
	}

	public void setOneNightCost(Double oneNightCost) {
		this.oneNightCost = oneNightCost;
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

	public Boolean getIsFullBooked() {
		return isFullBooked;
	}

	public void setIsFullBooked(Boolean isFullBooked) {
		this.isFullBooked = isFullBooked;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}

	public List<RoomAmenity> getRoomAmenities() {
		return roomAmenities;
	}

	public void setRoomAmenities(List<RoomAmenity> roomAmenities) {
		this.roomAmenities = roomAmenities;
	}

	public List<RoomPhoto> getRoomPhotos() {
		return roomPhotos;
	}

	public void setRoomPhotos(List<RoomPhoto> roomPhotos) {
		this.roomPhotos = roomPhotos;
	}

	public List<Sell> getSells() {
		return sells;
	}

	public void setSells(List<Sell> sells) {
		this.sells = sells;
	}

	// public List<RoomBlock> getRoomBlocks() {
	// return roomBlocks;
	// }
	//
	// public void setRoomBlocks(List<RoomBlock> roomBlocks) {
	// this.roomBlocks = roomBlocks;
	// }

	public Boolean getIsFullSuite() {
		return isFullSuite;
	}

	public void setIsFullSuite(Boolean isFullSuite) {
		this.isFullSuite = isFullSuite;
	}

	public List<CartDetail> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(List<CartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getBookAt() {
		return bookAt;
	}

	public void setBookAt(Date bookAt) {
		this.bookAt = bookAt;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	/*
	 * public boolean isRoomAvailable(Date startDate, Date endDate) { Date
	 * todayDate = null; Date todayDateWithTime = null; try { todayDate =
	 * DateUtil.currentDateWithoutTime(); todayDateWithTime = new Date(); if
	 * ((startDate.after(this.startDate) || this.startDate .before(endDate)) &&
	 * (endDate.before(this.endDate) || endDate .equals(this.endDate)) &&
	 * !this.isFullBooked && endDate.after(startDate) &&
	 * (startDate.equals(todayDate) || startDate .after(todayDate))) { for
	 * (CartDetail cartDetail : cartDetails) {
	 * System.out.println(!cartDetail.getCart().getIsCompleted());
	 * System.out.println(cartDetail.getReleaseAt().after( todayDateWithTime));
	 * System.out.println(cartDetail.getReleaseAt());
	 * System.out.println(todayDateWithTime); if
	 * (!cartDetail.getCart().getIsCompleted() &&
	 * cartDetail.getReleaseAt().after( todayDateWithTime)) { return false; } }
	 * return true; }
	 * 
	 * // SELECT hotelRoom.id FROM CartDetail AS cartDetail JOIN //
	 * cartDetail.cart AS cart JOIN cartDetail.hotelRoom AS hotelRoom // WHERE
	 * hotelRoom.id= AND cart.isCompleted=false AND //
	 * cartDetail.releaseAt>NOW() } finally {
	 * 
	 * } return false; }
	 */
	public double getRoomCost(Date startDate, Date endDate) {
		int days = Days.daysBetween(new LocalDate(startDate),
				new LocalDate(endDate)).getDays();
		return this.oneNightCost * days;
	}
}
