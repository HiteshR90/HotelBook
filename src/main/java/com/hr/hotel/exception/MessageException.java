package com.hr.hotel.exception;

public class MessageException extends BaseWebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageException(MessageExceptionErroCode messageExceptionErroCode) {
		super(400, messageExceptionErroCode.getStatusCode(),
				messageExceptionErroCode.getErrorMessage(),
				messageExceptionErroCode.getDeveloperMessage());
	}

	public enum MessageExceptionErroCode {
		DATENOTAVAILABLE("4000501", "Date is not avilable",
				"Date is not avilable"), DATANOTSAVE("4000502",
				"Data not save", "Data not save"), NOTVALIDFBTOKEN("4000503",
				"Not Valid Access Token", "Not Valid Access Token"), PAYPALIDNOTSET(
				"4000504", "Please Set PaypalId", "Paypal id not set by user"), BANKACCOUNTNOTSET(
				"4000505", "Please Set Bank Information",
				"Bank information not set by user");

		private String statusCode;
		private String errorMessage;
		private String developerMessage;

		private MessageExceptionErroCode(String statusCode,
				String errorMessage, String developerMessage) {
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
