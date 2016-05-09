package com.hr.hotel.service.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.hr.hotel.common.Constant;
import com.hr.hotel.dto.TempSellRoomDto;
import com.hr.hotel.dto.UserLoginDto;
import com.hr.hotel.exception.NotFoundException;
import com.hr.hotel.exception.NotFoundException.NotFound;
import com.hr.hotel.model.Amenity;
import com.hr.hotel.model.HotelRoom;
import com.hr.hotel.model.Organization;
import com.hr.hotel.model.Photo;
import com.hr.hotel.model.RoomAmenity;
import com.hr.hotel.model.RoomPhoto;
import com.hr.hotel.model.RoomType;
import com.hr.hotel.model.Sell;
import com.hr.hotel.model.TempSellConfirm;
import com.hr.hotel.model.User;
import com.hr.hotel.repository.AmenityRepository;
import com.hr.hotel.repository.HotelRoomRepository;
import com.hr.hotel.repository.OrganizationRepository;
import com.hr.hotel.repository.PhotoRepository;
import com.hr.hotel.repository.RoomAmenityRepository;
import com.hr.hotel.repository.RoomPhotoRepository;
import com.hr.hotel.repository.RoomTypeRepository;
import com.hr.hotel.repository.SellRepository;
import com.hr.hotel.repository.TempSellRepository;
import com.hr.hotel.service.AdminService;
import com.hr.hotel.service.SendMailService;
import com.hr.hotel.util.DateUtil;

import freemarker.template.TemplateException;

@Service(Constant.SERVICE_ADMIN)
public class AdminServiceImpl extends UserServiceImpl implements AdminService {

	@Autowired
	private TempSellRepository tempSellRepository;

	@Autowired
	private RoomTypeRepository roomTypeRepository;

	@Autowired
	private HotelRoomRepository hotelRoomRepository;

	@Autowired
	private SellRepository sellRepository;

	@Resource(name = Constant.SERVICE_SEND_MAIL)
	private SendMailService sendMailService;

	@Autowired
	private PhotoRepository photoRepository;

	@Autowired
	private AmenityRepository amenityRepository;

	@Autowired
	private RoomPhotoRepository roomPhotoRepository;

	@Autowired
	private RoomAmenityRepository roomAmenityRepository;

	@Autowired
	private OrganizationRepository organizationRepository;

	private SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(
			"ADMIN");

	@Override
	@Transactional
	public UserDetails login(UserLoginDto loginDto) {
		UserDetails objUser = super.login(loginDto);
		if (objUser != null && !objUser.getAuthorities().isEmpty()
				&& objUser.getAuthorities().contains(adminAuthority)) {
			return objUser;
		} else {
			throw new NotFoundException(NotFound.NotAdminUser);
		}
	}

	@Override
	@Transactional
	public List<TempSellRoomDto> getRooms() {
		return this.tempSellRepository.getRooms();
	}

	@Override
	@Transactional
	public void confirmRoom(Long id, User loginUser) throws MessagingException,
			IOException, TemplateException {
		TempSellConfirm objTempSellConfirm = this.tempSellRepository
				.findOne(id);

		if (objTempSellConfirm != null) {
			User sellBy = objTempSellConfirm.getSellBy();
			User sellFor = objTempSellConfirm.getSellFor();
			objTempSellConfirm.setActive(false);
			objTempSellConfirm.setIsConfirmed(true);
			objTempSellConfirm.setIsRemoved(false);
			objTempSellConfirm.setModifiedBy(loginUser.getId());
			objTempSellConfirm.setModifiedDate(DateUtil.getCurrentDate());
			// update
			this.tempSellRepository.save(objTempSellConfirm);
			// get hotel room
			RoomType objRoomType = this.roomTypeRepository
					.getRoomTypeByName(objTempSellConfirm.getRoomType());
			if (objRoomType == null) {
				objRoomType = new RoomType();
				objRoomType.setActive(true);
				objRoomType.setCreatedDate(DateUtil.getCurrentDate());
				objRoomType.setCreatedBy(sellBy.getId());
				objRoomType.setRoomType(objTempSellConfirm.getRoomType());
			}
			this.roomTypeRepository.save(objRoomType);
			HotelRoom objHotelRoom = new HotelRoom();
			objHotelRoom.setActive(true);
			objHotelRoom.setCreatedBy(sellBy.getId());
			objHotelRoom.setCreatedDate(DateUtil.getCurrentDate());
			objHotelRoom.setEndDate(objTempSellConfirm.getCheckOutDate());
			objHotelRoom.setHotel(objTempSellConfirm.getHotel());
			objHotelRoom.setIsFullBooked(false);
			objHotelRoom.setIsFullSuite(true);
			// TODO change
			objHotelRoom.setRoomDesc("This is an excellent");
			// TODO set cost
//			double oneNightCost = (objTempSellConfirm.getPrice())
//					/ (DateUtil.dateDifference(
//							objTempSellConfirm.getCheckInDate(),
//							objTempSellConfirm.getCheckOutDate()));
			objHotelRoom.setOneNightCost(objTempSellConfirm.getPrice());
			objHotelRoom.setRoomType(objRoomType);
			objHotelRoom.setStartDate(objTempSellConfirm.getCheckInDate());
			this.hotelRoomRepository.save(objHotelRoom);
			// TODO set photos and amenities
			List<Amenity> objDefaultAmenities = this.amenityRepository
					.getDefaultAmenities();
			for (Amenity amenity : objDefaultAmenities) {
				RoomAmenity objRoomAmenity = new RoomAmenity();
				objRoomAmenity.setActive(true);
				objRoomAmenity.setAmenity(amenity);
				objRoomAmenity.setCreatedBy(sellFor.getId());
				objRoomAmenity.setCreatedDate(DateUtil.getCurrentDate());
				objRoomAmenity.setHotelRoom(objHotelRoom);
				this.roomAmenityRepository.save(objRoomAmenity);
			}
			List<Photo> objDefaultPhotos = this.photoRepository
					.getDefaultAmenities();
			// for (Photo photo : objDefaultPhotos) {
			RoomPhoto objRoomPhoto = new RoomPhoto();
			objRoomPhoto.setActive(true);
			objRoomPhoto.setPhoto(objDefaultPhotos.get(0));
			objRoomPhoto.setCreatedBy(sellFor.getId());
			objRoomPhoto.setCreatedDate(DateUtil.getCurrentDate());
			objRoomPhoto.setHotelRoom(objHotelRoom);
			objRoomPhoto.setIsDefault(true);
			this.roomPhotoRepository.save(objRoomPhoto);
			// }
			// save data in sell table
			Sell objSell = new Sell();
			objSell.setCreatedBy(sellBy.getId());
			objSell.setCreatedDate(DateUtil.getCurrentDate());
			objSell.setHotelRoom(objHotelRoom);
			objSell.setSellBy(sellBy);
			objSell.setSellFor(sellFor);
			// TODO change organization
			Organization objOrganization = this.organizationRepository
					.getPaymentType("Paypal");
			objSell.setOrganization(objOrganization);
			this.sellRepository.save(objSell);
			// send mail
			this.sendMailService.sendConfirmRoomMail(sellFor,
					objTempSellConfirm.getCheckInDate(),
					objTempSellConfirm.getCheckOutDate(),
					objTempSellConfirm.getPrice());
		} else {
			throw new NotFoundException(NotFound.DataNotFound);
		}
	}

	@Override
	@Transactional
	public void rejectRoom(Long id, User loginUser) throws MessagingException,
			IOException, TemplateException {
		TempSellConfirm objTempSellConfirm = this.tempSellRepository
				.findOne(id);
		if (objTempSellConfirm != null) {
			User sellFor = objTempSellConfirm.getSellFor();
			objTempSellConfirm.setActive(false);
			objTempSellConfirm.setIsConfirmed(false);
			objTempSellConfirm.setIsRemoved(false);
			objTempSellConfirm.setModifiedBy(loginUser.getId());
			objTempSellConfirm.setModifiedDate(DateUtil.getCurrentDate());
			this.tempSellRepository.save(objTempSellConfirm);
			// send mail to user
			// send mail
			this.sendMailService.sendRejectRoomMail(sellFor,
					objTempSellConfirm.getCheckInDate(),
					objTempSellConfirm.getCheckOutDate(),
					objTempSellConfirm.getPrice());
		} else {
			throw new NotFoundException(NotFound.DataNotFound);
		}
	}
}
