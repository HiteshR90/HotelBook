package com.hr.hotel.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.hr.hotel.common.Constant;
import com.hr.hotel.dao.HotelRoomDao;
import com.hr.hotel.dto.RoomData;
import com.hr.hotel.service.CommonService;
import com.hr.hotel.util.DateUtil;

@Repository(Constant.DAO_HOTEL_ROOM)
public class HotelRoomDaoImpl implements HotelRoomDao {

	// @Autowired
	// private SessionFactory sessionFactory;

	@PersistenceContext
	private EntityManager entityManager;

	@Resource(name = Constant.SERVICE_COMMON)
	private CommonService commonService;

	// private boolean isDateValid(String date) {
	// try {
	// if (null != date) {
	// DateFormat df = new SimpleDateFormat(Constant.DATE_FORMAT);
	// df.setLenient(false);
	// df.parse(date);
	// return true;
	// }
	// return false;
	// } catch (ParseException e) {
	// return false;
	// }
	// }

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomData> getHotelRooms(Integer sortBy, Integer pageNo,
			Integer noOfRecord, Integer[] noOfRoom, Integer priceMin,
			Integer priceMax, Integer[] adults, Integer[] childrens,
			Integer[] hotelBrand, Integer[] typeOfBookings,
			Integer[] sellerRatings, Integer[] hotelRatings,
			String[] amenities, String cityName, String startDate,
			String endDate, String address, Integer pincode, String phoneNumber) {
		List<RoomData> objRoomDatas = null;
		StringBuilder objSB = null;
		Query objQuery = null;
		Integer startLimit = 0;
		Integer endLimit = 9;
		// used for send query param
		Map<String, Object> objMapQuery = null;
		try {
			objSB = new StringBuilder();
			objMapQuery = new HashMap<String, Object>();
			// objSB.delete(0, objSB.length())
			// .append("SELECT DISTINCT hotelRoom.id AS roomId,")
			// .append("hotelRoom.oneNightCost AS cost,")
			// .append("hotelRoom.startDate AS startDate,")
			// .append("hotelRoom.endDate AS endDate,")
			// .append("hotelMaster.hotelName  AS hotelName,")
			// .append("hotelMaster.id AS hotelId,")
			// .append("hotelRoom.isFullSuite AS isFullSuite,")
			// .append("city.name AS city,")
			// .append("roomType.roomType AS roomType,")
			// .append("hotelMaster.hotelBrand AS hotelBrand,")
			// .append("hotelRoom.roomDesc AS roomDesc,")
			// .append("photo.path AS path,")
			// .append("hotelMaster.latitude AS latitude,")
			// .append("hotelMaster.longitude AS longitude")
			// .append(" FROM HotelRoom AS hotelRoom")
			// .append(" JOIN hotelRoom.roomType AS roomType")
			// .append(" JOIN hotelRoom.hotel AS hotelMaster")
			// .append(" JOIN hotelMaster.city AS city")
			// .append(" LEFT JOIN hotelRoom.roomPhotos AS roomPhotos ")
			// .append(" LEFT JOIN roomPhotos.photo AS photo")
			// .append(" LEFT JOIN hotelRoom.roomAmenities AS roomAmenities ")
			// .append(" LEFT JOIN roomAmenities.amenity AS amenity ")
			// .append(" WHERE roomPhotos.isDefault=:defaultPhoto ")
			// .append(" AND hotelRoom.isFullBooked=:isFullBooked")
			// .append(" AND hotelRoom.endDate>=:endDate")
			// .append(" AND hotelRoom.id NOT IN (")
			// .append("SELECT hotelRoomIn.id ")
			// .append(" FROM CartDetail as cartDetailIn")
			// .append(" JOIN cartDetailIn.cart AS cartIn")
			// .append(" JOIN cartDetailIn.hotelRoom AS hotelRoomIn")
			// .append(" WHERE cartDetailIn.releaseAt>=NOW()")
			// .append(" AND cartIn.isCompleted=:isCartCompleted")
			// .append(")");
			objSB.delete(0, objSB.length())
					.append("SELECT DISTINCT NEW com.hr.onesuite.dto.RoomData(hotelRoom.id,")
					.append("hotelRoom.oneNightCost,")
					.append("hotelRoom.startDate,")
					.append("hotelRoom.endDate,")
					.append("hotelMaster.hotelName,")
					.append("hotelMaster.address,")
					.append("hotelMaster.pincode,")
					.append("hotelMaster.phoneNumber,")
					.append("hotelMaster.id,")
					.append("hotelRoom.isFullSuite,")
					.append("city.name,")
					.append("roomType.roomType,")
					.append("hotelMaster.hotelBrand,")
					.append("hotelRoom.roomDesc,")
					.append("photo.path,")
					.append("hotelMaster.latitude,")
					.append("hotelMaster.longitude)")
					.append(" FROM HotelRoom AS hotelRoom")
					.append(" JOIN hotelRoom.roomType AS roomType")
					.append(" JOIN hotelRoom.hotel AS hotelMaster")
					.append(" JOIN hotelMaster.city AS city")
					.append(" LEFT JOIN hotelRoom.roomPhotos AS roomPhotos ")
					.append(" LEFT JOIN roomPhotos.photo AS photo")
					.append(" LEFT JOIN hotelRoom.roomAmenities AS roomAmenities ")
					.append(" LEFT JOIN roomAmenities.amenity AS amenity ")
					.append(" LEFT JOIN hotelRoom.ratings AS hotelRating ")
					.append(" LEFT JOIN hotelRoom.ratings AS userRating ")
					.append(" WHERE roomPhotos.isDefault=:defaultPhoto ")
					.append(" AND hotelRoom.isFullBooked=:isFullBooked")
					.append(" AND hotelRoom.endDate>:endDateQ")
					.append(" AND hotelRoom.id NOT IN (")
					.append("SELECT hotelRoomIn.id  FROM CartDetail as cartDetailIn JOIN cartDetailIn.cart AS cartIn JOIN cartDetailIn.hotelRoom AS hotelRoomIn WHERE cartDetailIn.releaseAt>=NOW() AND cartIn.isCompleted=:isCartCompleted")
					.append(")");
			// TODO search
			if (cityName != null
					&& !Constant.STRING_EMPTY.equalsIgnoreCase(cityName)) {
				objMapQuery.put("cityName", "%" + cityName + "%");
				objSB.append(" AND city.name LIKE :cityName");
			}
			if (this.commonService.isValidDate(startDate, Constant.DATE_FORMAT)
					&& this.commonService.isValidDate(endDate,
							Constant.DATE_FORMAT)) {
				// objSB.append(" AND :startDate BETWEEN hotelRoom.startDate AND hotelRoom.endDate");
				// objSB.append(" AND :endDate BETWEEN hotelRoom.startDate AND hotelRoom.endDate");
				// objSB.append(" AND :startDate<:endDate");
				objSB.append(" AND hotelRoom.startDate<=:startDate AND hotelRoom.endDate>=:endDate AND :startDate<:endDate AND :startDate>=NOW()");
				objSB.append(" AND hotelRoom.id NOT IN (")
						.append("SELECT DISTINCT(oHotelRoom.id)  FROM OrderDetail as orderDetail JOIN orderDetail.hotelRoom AS oHotelRoom WHERE 1=1 AND (orderDetail.startDate=:startDate AND orderDetail.endDate=:endDate) OR (orderDetail.startDate>=:startDate AND orderDetail.startDate<:endDate) OR (orderDetail.endDate>:startDate AND orderDetail.endDate<=:endDate)")
						.append(")");
				objMapQuery.put("startDate",
						DateUtil.getDate(startDate, Constant.DATE_FORMAT));
				objMapQuery.put("endDate",
						DateUtil.getDate(endDate, Constant.DATE_FORMAT));

				// Date objStartDate = objDateFormat.parse(startDate);
				// objMapQuery.put("startDateFilter", objStartDate);
				// objSB.append(" AND hotelRoom.startDate >= :startDateFilter");
				//
				// Date objEndDate = objDateFormat.parse(endDate);
				// objMapQuery.put("endDateFilter", objEndDate);
				// objSB.append(" AND hotelRoom.endDate <= :endDateFilter");

			}
			/*
			 * if (endDate != null &&
			 * !Constant.STRING_EMPTY.equalsIgnoreCase(endDate)) { Date
			 * objEndDate = objDateFormat.parse(endDate);
			 * objMapQuery.put("endDate", objEndDate);
			 * objSB.append(" AND hotelRoom.endDate <= :endDate");
			 * objSBCount.append(" AND hotelRoom.endDate <= :endDate"); }
			 */
			// no of rooms
			if (noOfRoom != null && noOfRoom.length >= 1) {
				if (noOfRoom.length == 1 && noOfRoom[0] != 0) {
					objSB.append(" AND hotelRoom.noOfRooms =")
							.append(noOfRoom[0]).append(" ");
				} else if (noOfRoom.length > 1) {
					objSB.append(" AND hotelRoom.noOfRooms IN (");
					for (Integer rooms : noOfRoom) {
						if (rooms != 0) {
							objSB.append(rooms).append(",");
						}
					}
					objSB.delete(objSB.length() - 1, objSB.length())
							.append(")");
				}
			}
			if (priceMin != null && priceMax != null) {
				objSB.append(" AND hotelRoom.oneNightCost BETWEEN ")
						.append(priceMin).append(" AND ").append(priceMax)
						.append(" ");
			}
			if (adults != null && adults.length >= 1) {
				if (adults.length == 1 && adults[0] != 0) {
					objSB.append(" AND hotelRoom.noOfAdults =")
							.append(adults[0]).append(" ");

				} else if (adults.length > 1) {
					objSB.append(" AND hotelRoom.noOfAdults IN (");
					for (Integer adult : adults) {
						objSB.append(adult).append(",");
					}
					objSB.delete(objSB.length() - 1, objSB.length())
							.append(")");
				}
			}
			if (childrens != null && childrens.length >= 1) {
				if (childrens.length == 1 && childrens[0] != 0) {
					objSB.append(" AND hotelRoom.noOfChildren =")
							.append(childrens[0]).append(" ");
				} else if (childrens.length > 1) {
					objSB.append(" AND hotelRoom.noOfChildren IN (");
					for (Integer children : childrens) {
						objSB.append(children).append(",");
					}
					objSB.delete(objSB.length() - 1, objSB.length())
							.append(")");
				}
			}
			if (hotelBrand != null && hotelBrand.length >= 1) {
				if (hotelBrand.length == 1 && hotelBrand[0] != 0) {
					objSB.append(" AND hotelMaster.hotelBrand =")
							.append(hotelBrand[0]).append(" ");
				} else if (hotelBrand.length > 1) {
					objSB.append(" AND hotelMaster.hotelBrand IN (");
					for (Integer brand : hotelBrand) {
						objSB.append(brand).append(",");
					}
					objSB.delete(objSB.length() - 1, objSB.length())
							.append(")");
				}
			}

			if (amenities != null && amenities.length > 0) {
				/*objSB.append(" AND amenity.amenityName IN (");
				for (String amenity : amenities) {
					objSB.append("'").append(amenity).append("'").append(",");
				}
				objSB.delete(objSB.length() - 1, objSB.length()).append(")");*/
				for (String amenity : amenities) {
					objSB.append(" AND amenity.amenityName='").append(amenity).append("'");
				}
			}
			if (hotelRatings != null && hotelRatings.length >= 1) {
				objSB.append(" AND hotelRating.isHotelRating=true ");
				if (hotelRatings.length == 1 && hotelRatings[0] != 0) {
					objSB.append(
							" GROUP BY hotelRoom.id HAVING ROUND(AVG(hotelRating.rating)) = ")
							.append(hotelRatings[0]).append(" ");
					// objSB.append(
					// " HAVING ROUND(AVG(hotelRating.rating)) = ")
					// .append(hotelRatings[0]).append(" ");
				} else if (hotelRatings.length > 1) {
					objSB.append(" GROUP BY hotelRoom.id HAVING ROUND(AVG(hotelRating.rating)) IN (");
					// objSB.append(" HAVING ROUND(AVG(hotelRating.rating)) IN (");
					for (Integer hotelRating : hotelRatings) {
						objSB.append(hotelRating).append(",");
					}
					objSB.delete(objSB.length() - 1, objSB.length())
							.append(")");
				}
			}
			// TODO search end
			// check that query for sorting or not
			if (sortBy != null && sortBy != 0) {
				// check sort param
				switch (sortBy) {
				// if sort by hotel name
				case 1:
					objSB.append(" ORDER BY hotelMaster.hotelName");
					break;
				// if sort by cost and ascending
				case 2:
					objSB.append(" ORDER BY hotelRoom.oneNightCost ASC");
					break;
				// if sort by cost and descending
				case 3:
					objSB.append(" ORDER BY hotelRoom.oneNightCost DESC");
					break;
				// if sort by rating and ascending
				case 4:
					objSB.append(" ORDER BY hotelMaster.hotelBrand ASC");
					break;
				// if sort by rating and descending
				case 5:
					objSB.append(" ORDER BY hotelMaster.hotelBrand DESC");
					break;
				default:
					break;
				}

			} else {
				// objSB.append(" ORDER BY premierList.premierPrice DESC  ");
			}

			if (noOfRecord != null && noOfRecord != 0) {
				// start limit
				// startLimit = ((noOfRecord * pageNo) - (noOfRecord));
				if (pageNo != null && pageNo != 0) {
					startLimit = (pageNo - 1) * noOfRecord;
					// end limit
					endLimit = noOfRecord;
				} else {
					pageNo = 1;
					startLimit = (pageNo - 1) * noOfRecord;
					// end limit
					endLimit = noOfRecord;
				}

			} else if (pageNo != null && pageNo != 0) {
				if (noOfRecord != null && noOfRecord != 0) {
					startLimit = (pageNo - 1) * noOfRecord;
					// end limit
					endLimit = noOfRecord;
				} else {
					noOfRecord = 12;
					startLimit = (pageNo - 1) * noOfRecord;
					// end limit
					endLimit = noOfRecord;
				}
			} else {
				noOfRecord = 12;
				pageNo = 1;
				// start limit
				startLimit = (pageNo - 1) * noOfRecord;
				// end limit
				endLimit = noOfRecord;
			}
			// objQuery = this.sessionFactory.getCurrentSession().createQuery(
			// objSB.toString());
			objQuery = this.entityManager.createQuery(objSB.toString());
			objQuery.setParameter("defaultPhoto", true);
			objQuery.setParameter("isFullBooked", false);
			// set room is active
			// objQuery.setBoolean("active", true);
			// set room is not block
			// objQuery.setBoolean("block", false);
			objQuery.setParameter("endDateQ", DateUtil.currentDateWithoutTime());
			objQuery.setParameter("isCartCompleted", false);
			// objQuery.setProperties(objMapQuery);
			for (String key : objMapQuery.keySet()) {
				objQuery.setParameter(key, objMapQuery.get(key));
			}
			// if (null != objMapQuery) {
			// for (String param : objMapQuery.keySet()) {
			// objQuery.setParameter(param, objMapQuery.get(param));
			// }
			// }

			objQuery.setFirstResult(startLimit);
			objQuery.setMaxResults(endLimit);
			// objQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			// objRoomDatas = objQuery.list();
			objRoomDatas = objQuery.getResultList();
		} finally {

		}
		return objRoomDatas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getTotalPages(Integer sortBy, Integer pageNo,
			Integer noOfRecord, Integer[] noOfRoom, Integer priceMin,
			Integer priceMax, Integer[] adults, Integer[] childrens,
			Integer[] hotelBrand, Integer[] typeOfBookings,
			Integer[] sellerRatings, Integer[] hotelRatings,
			String[] amenities, String cityName, String startDate,
			String endDate) {
		StringBuilder objSBCount = null;
		// used for send query param
		Map<String, Object> objMapQuery = null;
		// get data from list and store in JSONObject
		Integer endLimit = 9;
		Integer totalPage = null;
		List<Long> objTotalRecord = null;
		Query objQuery = null;
		Long totalRecords = null;

		try {
			objSBCount = new StringBuilder();

			// store query param
			objMapQuery = new HashMap<String, Object>();
			// set defaultPhoto
			// objMapQuery.put("defaultPhoto", true);
			// set room is active
			// objMapQuery.put("active", true);
			// set room is not block
			// objMapQuery.put("block", false);
			objMapQuery.put("endDateQ", DateUtil.currentDateWithoutTime());
			objMapQuery.put("isCartCompleted", false);
			objMapQuery.put("isFullBooked", false);

			// create query
			objSBCount
					.delete(0, objSBCount.length())
					.append("SELECT COUNT(DISTINCT hotelRoom)")
					.append(" FROM HotelRoom AS hotelRoom")
					.append(" JOIN hotelRoom.hotel AS hotelMaster")
					.append(" JOIN hotelMaster.city AS city")
					.append(" LEFT JOIN hotelRoom.roomAmenities AS roomAmenities ")
					.append(" LEFT JOIN roomAmenities.amenity AS amenity ")
					.append(" LEFT JOIN hotelRoom.ratings AS hotelRating ")
					.append(" LEFT JOIN hotelRoom.ratings AS userRating ")
					// .append(" LEFT JOIN hotelRoom.premierList AS premierList")
					// .append(" LEFT JOIN hotelRoom.hotel.hotelRatings AS ratingHotel ")
					// .append(" LEFT JOIN hotelRoom.hotel.hotelRatings AS ratingUser ")
					.append(" WHERE hotelRoom.isFullBooked=:isFullBooked")
					// .append("AND hotelRoom.active=:active")
					// .append(" AND hotelRoom.block=:block")
					.append(" AND hotelRoom.endDate>:endDateQ")
					.append(" AND hotelRoom.id NOT IN (")
					.append("SELECT hotelRoomIn.id ")
					.append(" FROM CartDetail as cartDetailIn")
					.append(" JOIN cartDetailIn.cart AS cartIn")
					.append(" JOIN cartDetailIn.hotelRoom AS hotelRoomIn")
					.append(" WHERE cartDetailIn.releaseAt>=NOW()")
					.append(" AND cartIn.isCompleted=:isCartCompleted")
					.append(")");
			// TODO search
			if (cityName != null
					&& !Constant.STRING_EMPTY.equalsIgnoreCase(cityName)) {
				objMapQuery.put("cityName", "%" + cityName + "%");
				objSBCount.append(" AND city.name LIKE :cityName");
			}
			if (this.commonService.isValidDate(startDate, Constant.DATE_FORMAT)
					&& this.commonService.isValidDate(endDate,
							Constant.DATE_FORMAT)) {
				/*
				 * objSBCount .append(
				 * " AND :startDate BETWEEN hotelRoom.startDate AND hotelRoom.endDate"
				 * ); objSBCount .append(
				 * " AND :endDate BETWEEN hotelRoom.startDate AND hotelRoom.endDate"
				 * ); objSBCount.append(" AND :startDate<:endDate");
				 */
				objSBCount
						.append(" AND hotelRoom.startDate<=:startDate AND hotelRoom.endDate>=:endDate AND :startDate<:endDate");
				objSBCount
						.append(" AND hotelRoom.id NOT IN (")
						.append("SELECT DISTINCT(oHotelRoom.id)  FROM OrderDetail as orderDetail JOIN orderDetail.hotelRoom AS oHotelRoom WHERE 1=1 AND (orderDetail.startDate=:startDate AND orderDetail.endDate=:endDate) OR (orderDetail.startDate>=:startDate AND orderDetail.startDate<:endDate) OR (orderDetail.endDate>:startDate AND orderDetail.endDate<=:endDate)")
						.append(")");
				objMapQuery.put("startDate",
						DateUtil.getDate(startDate, Constant.DATE_FORMAT));
				objMapQuery.put("endDate",
						DateUtil.getDate(endDate, Constant.DATE_FORMAT));
				// Date objStartDate = objDateFormat.parse(startDate);
				// objMapQuery.put("startDateFilter", objStartDate);
				// objSB.append(" AND hotelRoom.startDate >= :startDateFilter");
				//
				// Date objEndDate = objDateFormat.parse(endDate);
				// objMapQuery.put("endDateFilter", objEndDate);
				// objSB.append(" AND hotelRoom.endDate <= :endDateFilter");

			}
			/*
			 * if (endDate != null &&
			 * !Constant.STRING_EMPTY.equalsIgnoreCase(endDate)) { Date
			 * objEndDate = objDateFormat.parse(endDate);
			 * objMapQuery.put("endDate", objEndDate);
			 * objSB.append(" AND hotelRoom.endDate <= :endDate");
			 * objSBCount.append(" AND hotelRoom.endDate <= :endDate"); }
			 */
			// no of rooms
			if (noOfRoom != null && noOfRoom.length >= 1) {
				if (noOfRoom.length == 1 && noOfRoom[0] != 0) {
					objSBCount.append(" AND hotelRoom.noOfRooms =")
							.append(noOfRoom[0]).append(" ");
				} else if (noOfRoom.length > 1) {
					objSBCount.append(" AND hotelRoom.noOfRooms IN (");
					for (Integer rooms : noOfRoom) {
						if (rooms != 0) {
							objSBCount.append(rooms).append(",");
						}
					}
					objSBCount.delete(objSBCount.length() - 1,
							objSBCount.length()).append(")");
				}
			}
			if (priceMin != null && priceMax != null) {
				objSBCount.append(" AND hotelRoom.oneNightCost BETWEEN ")
						.append(priceMin).append(" AND ").append(priceMax)
						.append(" ");
			}
			if (adults != null && adults.length >= 1) {
				if (adults.length == 1 && adults[0] != 0) {
					objSBCount.append(" AND hotelRoom.noOfAdults =")
							.append(adults[0]).append(" ");
				} else if (adults.length > 1) {
					objSBCount.append(" AND hotelRoom.noOfAdults IN (");
					for (Integer adult : adults) {
						objSBCount.append(adult).append(",");
					}
					objSBCount.delete(objSBCount.length() - 1,
							objSBCount.length()).append(")");
				}
			}
			if (childrens != null && childrens.length >= 1) {
				if (childrens.length == 1 && childrens[0] != 0) {
					objSBCount.append(" AND hotelRoom.noOfChildren =")
							.append(childrens[0]).append(" ");
				} else if (childrens.length > 1) {
					objSBCount.append(" AND hotelRoom.noOfChildren IN (");
					for (Integer children : childrens) {
						objSBCount.append(children).append(",");
					}
					objSBCount.delete(objSBCount.length() - 1,
							objSBCount.length()).append(")");
				}
			}
			if (hotelBrand != null && hotelBrand.length >= 1) {
				if (hotelBrand.length == 1 && hotelBrand[0] != 0) {
					objSBCount.append(" AND hotelMaster.hotelBrand =")
							.append(hotelBrand[0]).append(" ");
				} else if (hotelBrand.length > 1) {
					objSBCount.append(" AND hotelMaster.hotelBrand IN (");
					for (Integer brand : hotelBrand) {
						objSBCount.append(brand).append(",");
					}
					objSBCount.delete(objSBCount.length() - 1,
							objSBCount.length()).append(")");
				}
			}

			if (amenities != null && amenities.length > 0) {
				objSBCount.append(" AND amenity.amenityName IN (");
				for (String amenity : amenities) {
					objSBCount.append("'").append(amenity).append("'")
							.append(",");
				}
				objSBCount.delete(objSBCount.length() - 1, objSBCount.length())
						.append(")");
			}
			if (hotelRatings != null && hotelRatings.length >= 1) {
				objSBCount.append(" AND hotelRating.isHotelRating=true");
			}
			objSBCount.append(" GROUP BY hotelRoom.id ");
			if (hotelRatings != null && hotelRatings.length >= 1) {
				if (hotelRatings.length == 1 && hotelRatings[0] != 0) {
					objSBCount
							.append(" HAVING ROUND(AVG(hotelRating.rating)) =")
							.append(hotelRatings[0]).append(" ");
				} else if (hotelRatings.length > 1) {
					objSBCount
							.append(" HAVING ROUND(AVG(hotelRating.rating)) IN (");
					for (Integer hotelRating : hotelRatings) {
						objSBCount.append(hotelRating).append(",");
					}
					objSBCount.delete(objSBCount.length() - 1,
							objSBCount.length()).append(")");
				}
			}

			// objQuery = this.sessionFactory.getCurrentSession().createQuery(
			// objSBCount.toString());
			objQuery = this.entityManager.createQuery(objSBCount.toString());
			// objQuery.setProperties(objMapQuery);
			if (null != objMapQuery) {
				for (String param : objMapQuery.keySet()) {
					objQuery.setParameter(param, objMapQuery.get(param));
				}
			}

			objTotalRecord = objQuery.getResultList();
			if (objTotalRecord != null && objTotalRecord.size() > 0) {
				totalRecords = (long) objTotalRecord.size();
				if (null != noOfRecord && noOfRecord != 0) {
					totalPage = (int) Math
							.ceil(totalRecords * 1.0 / noOfRecord);
				} else {
					totalPage = (int) Math.ceil(totalRecords * 1.0 / endLimit);
				}
			} else {
				totalPage = 0;
			}
		} finally {

		}
		return totalPage;
	}

}
