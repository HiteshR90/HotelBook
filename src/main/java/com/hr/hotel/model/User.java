package com.hr.hotel.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "xc_user")
@DynamicUpdate
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public interface EditProfile {

	}

	public interface CheckDelegate {

	}

	// @Id
	// @Column(name = "userid")
	// @GeneratedValue(strategy = GenerationType.AUTO)
	// private Integer userId;

	@Column(name = "username", nullable = false, length = 50, unique = true)
	@NotEmpty(groups = { EditProfile.class }, message = "{NotEmpty.userMaster.userName}")
	private String userName;

	@Column(name = "first_name", nullable = false, length = 50, unique = true)
	@NotEmpty(groups = { EditProfile.class }, message = "{NotEmpty.userMaster.userName}")
	private String fName;

	@Column(name = "last_name", nullable = false, length = 50, unique = true)
	@NotEmpty(groups = { EditProfile.class }, message = "{NotEmpty.userMaster.userName}")
	private String lName;

	@Column(name = "gender")
	private String gender;

	//@Column(name = "firstname", length = 100)
	//private String firstName;

	//@Column(name = "middlename", length = 100)
	//private String middleName;

	//@Column(name = "lastname", length = 100)
	//private String lastName;

	@Column(name = "dob")
	@Past(groups = { EditProfile.class })
	private Date dob;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "zipcode")
	private String zipcode;

	@Column(name = "phonenumber", length = 30)
	private String phoneNumber;

	@Column(name = "faxnumber", length = 30)
	private String faxNumber;

	@Column(name = "mobilenumber", length = 30)
	private String mobileNumber;

	@Column(name = "email", nullable = false, length = 50, unique = true)
	@NotEmpty(groups = { EditProfile.class, CheckDelegate.class }, message = "{NotEmpty.userMaster.email}")
	@Email(groups = { EditProfile.class, CheckDelegate.class }, message = "{Email}")
	private String email;

	@Column(name = "password", length = 100, nullable = false)
	private String password;

	// @Column(name = "active", columnDefinition = "BIT default 0", length = 1)
	// private Boolean active;

	@Column(name = "is_verified", columnDefinition = "BIT default 0", length = 1)
	private Boolean isVerified;

	// @Transient
	// @NotEmpty(groups = { UserRegister.class, ChangePassword.class }, message
	// = "{NotEmpty.userMaster.retypePassword}")
	// private String retypePassword;
	//
	// @Transient
	// @AssertTrue(groups = { UserRegister.class }, message =
	// "{NotEmpty.userMaster.termAgreement}")
	// private Boolean termAgree;

	// @OneToMany(mappedBy = "bookBy")
	// private List<Block> blockBookBy = new ArrayList<Block>(0);
	//
	// @OneToMany(mappedBy = "bookFor")
	// private List<Block> blockBookFor = new ArrayList<Block>(0);

	@OneToMany(mappedBy = "sellBy")
	private List<Sell> sellBy = new ArrayList<Sell>(0);

	@OneToMany(mappedBy = "sellFor")
	private List<Sell> sellFor = new ArrayList<Sell>(0);

	@OneToMany(mappedBy = "user")
	private List<UserPaymentInfo> userPaymentInfos = new ArrayList<UserPaymentInfo>(
			0);

	@OneToMany(mappedBy = "addBy")
	private List<Cart> carts = new ArrayList<Cart>(0);

	// public Integer getUserId() {
	// return userId;
	// }
	//
	// public void setUserId(Integer userId) {
	// this.userId = userId;
	// }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	/*public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}*/

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// public List<Block> getBlockBookBy() {
	// return blockBookBy;
	// }
	//
	// public void setBlockBookBy(List<Block> blockBookBy) {
	// this.blockBookBy = blockBookBy;
	// }
	//
	// public List<Block> getBlockBookFor() {
	// return blockBookFor;
	// }
	//
	// public void setBlockBookFor(List<Block> blockBookFor) {
	// this.blockBookFor = blockBookFor;
	// }

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public List<Sell> getSellBy() {
		return sellBy;
	}

	public void setSellBy(List<Sell> sellBy) {
		this.sellBy = sellBy;
	}

	public List<Sell> getSellFor() {
		return sellFor;
	}

	public void setSellFor(List<Sell> sellFor) {
		this.sellFor = sellFor;
	}

	public List<UserPaymentInfo> getUserPaymentInfos() {
		return userPaymentInfos;
	}

	public void setUserPaymentInfos(List<UserPaymentInfo> userPaymentInfos) {
		this.userPaymentInfos = userPaymentInfos;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

}
