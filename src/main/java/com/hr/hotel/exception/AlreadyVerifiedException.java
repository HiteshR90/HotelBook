package com.hr.hotel.exception;

public class AlreadyVerifiedException extends BaseWebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyVerifiedException(
			AlreadyVerifiedExceptionType alreadyVerifiedExceptionType) {
		super(409, alreadyVerifiedExceptionType.getErrorCode(),
				alreadyVerifiedExceptionType.getErrorMessage(),
				alreadyVerifiedExceptionType.getDeveloperMessage());
	}

	public enum AlreadyVerifiedExceptionType {
		Token("40902", "The token has already been verified",
				"The token has already been verified"), User("40903",
				"The user has already been verified",
				"The user has already been verified");

		private final String errorMessage;
		private final String errorCode;
		private final String developerMessage;

		AlreadyVerifiedExceptionType(String errorCode, String errorMessage,
				String developerMessage) {
			this.errorCode = errorCode;
			this.errorMessage = errorMessage;
			this.developerMessage = developerMessage;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public String getErrorCode() {
			return errorCode;
		}

		public String getDeveloperMessage() {
			return developerMessage;
		}
	}
}
