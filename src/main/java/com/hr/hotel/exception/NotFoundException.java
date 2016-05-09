package com.hr.hotel.exception;

public class NotFoundException extends BaseWebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4553101964302123808L;

	public NotFoundException(NotFound notFound) {
		super(404, notFound.getStatusCode(), notFound.getErrorMessage(),
				notFound.getDeveloperMessage());
	}

	public enum NotFound {
		UserNotFound("40401", "User Not Found",	"No User could be found for that Id"),
		DefaultRoleNotSet("40402", "Default role not set by admin","Admin not set role in database"),
		TokenNotFound("40403", "Token Not Found","No token could be found for that Id"),
		CartNotFound("40404", "Cart Not Found", "No Cart could be found for that Id"), 
		NotAssigner("40405", "Not Assigner User","No Assigner User could be found for that email address"),
		SelfDelegate("40406", "You can't make delegate your self","User can't make themselves as delegate"),
		DataNotFound("40407", "Data not found", "Data not found"),
		RoomNotAvailable("40408", "Room is not avilable", "Room is not avilable"),
		RoomNotFoundInCart("40409", "No Room Found In Cart", "No Room Found In Cart"),
		RoomNotFound("40410", "Room not found", "Room not found"),
		RoomNotFoundForSell("", "room not found for sell", "room not found for sell"),
		HotelNotFound("40411", "Hotel not found", "Hotel not found"),
		CartUnderProcess("40411", "Cart is under process","Cart is processed by another user"),
		CityNotFound("","City Not Found", "City not foun in database"),
		StateNotFound("","State Not Found", "State not foun in database"),
		NotAdminUser("", "Not Admin User", "Given user is not admin user");

		private String statusCode;
		private String errorMessage;
		private String developerMessage;

		private NotFound(String statusCode, String errorMessage,
				String developerMessage) {
			this.statusCode = statusCode;
			this.errorMessage = errorMessage;
			this.developerMessage = developerMessage;
		}

		public String getStatusCode() {
			return statusCode;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public String getDeveloperMessage() {
			return developerMessage;
		}

	}
}
