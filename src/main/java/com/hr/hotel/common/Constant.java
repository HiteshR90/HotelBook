package com.hr.hotel.common;

public interface Constant {

	Double TRANSCATION_LIMIT = 10000D;

	// TODO mvc
	String SESSION_USER = "sessionUser";

	String STATUS_SUCCESS = "SUCCESS";
	String STATUS_FAIL = "FAIL";
	String MAP_ERROR_MESSAGE = "errorMessage";
	String MAP_SUCCESS_MESSAGE = "successMessage";
	// TODO mvc end

	String DATE_FORMAT = "MM/dd/yy";
	String DATE_FORMAT_WITH_TIME = "dd/MM/yyyy HH:mm:ss.SSS";

	// String DATE_FORMAT_JAVASCRIPT = "d-M-yyyy";

	// resource
	String RESOURCE_VALIDATOR = "validator";

	// properties files
	String PROP_MESSAGE = "classpath:message.properties";
	String PROP_ERROR_MESSAGE = "classpath:errormessage.properties";
	String PROP_PAYPAL = "classpath:paypal.properties";
	// String PROP_XCHANGE = "classpath:xchangehotel.properties";

	// controller
	// String CONTROL_HOME = "/";
	// String CONTROL_USER_LOGIN = "/userLogin";
	// String CONTROL_EDIT_PROFILE = "/editProfile";
	// String CONTROL_USER_PREFERENCES = "/userPreferences";
	// String CONTROL_REGISTRATION = "/registration";
	// String CONTROL_EMAIL_CONFIRM = "/emailConfirmation";
	// String CONTROL_ACTIVE_USER = "/activateUser";
	// String CONTROL_SOCIAL_LOGIN = "/socialLogin";
	// String CONTROL_LOGOUT = "/logout";
	// String CONTROL_FORGOT_PASSWORD = "/forgotPassword";
	// String CONTROL_CHANGE_PASSWORD = "/chnagePassword";
	// String CONTROL_GET_IMAGE = "/image/{imageId}";
	// String CONTROL_AJAX_VALIDATE = "/ajaxValidate";
	// String CONTROL_ISLOGIN = "isLogin";

	// hotel controller API
	// String CONTROL_HOTEL_GET_ROOM_DATA = "/getRoomsData";

	// hotel controller
	// String CONTROL_HOTEL = "hotel";
	// String CONTROL_HOTEL_ROOM_LIST = "/rooms";
	// String CONTROL_HOTEL_ROOM_DETAIL = "/hotelRoomDetail/{hotelId}/{roomId}";
	// String CONTROL_HOTEL_ROOM_BOOK = "bookRoom";
	// String CONTROL_HOTEL_SEARCH = "/searchHotel";
	// String CONTROL_HOTEL_ADVANCE_SEARCH = "/advanceSearchHotel";
	// String CONTROL_HOTEL_SEARCH_BY_LOCATION = "searchByLocation";
	// String CONTROL_HOTEL_AJAX_SEARCH_BY_LOCATION = "ajaxSearchByLocation";
	// String CONTROL_HOTEL_AJAX_ROOM_DATAIL = "ajaxGetRoomDetail";
	// String CONTROL_HOTEL_ADVANCE_SEARCH_BACK = "/advanceSearchBack";
	// String CONTROL_HOTEL_SELECT_PAYMENT = "/selectPaymentMethod";
	// String CONTROL_HOTEL_ORDER_HISTORY = "orderHistory";
	// String CONTROL_HOTEL_BOOKED_ROOM_LIST = "bookedRoomList";
	// String CONTROL_HOTEL_SELL_ROOM = "/sellRoom/{userRoomBooking}";

	// paypal controller
	// String CONTROL_PAYPAL_REDIRECT =
	// "paypalRedirect/{userRoomBooking}/{status}";

	// View
	// view pages
	// String VIEW_ERROR = "errorPage";
	// String VIEW_INDEX = "index";
	// String VIEW_FORGOT_PASSWORD = "forgotPassword";
	// String VIEW_CHANGE_PASSWORD = "changePassword";

	// String VIEW_EDIT_PROFILE = "profile";

	// hotel view
	// String VIEW_HOTEL_ROOM_LIST = "hotel/rooms";
	// String VIEW_HOTEL_DETAIL = "hotel/hotelDetail";
	// String VIEW_HOTEL_ROOM_BOOK = "hotel/bookRoom";
	// String VIEW_HOTEL_ADVANCE_SEARCH = "hotel/advanceSearch";
	// String VIEW_HOTEL_SEARCH_BY_LOCATION = "hotel/searchByLocation";
	// String VIEW_HOTEL_ROOM_BOOK_PAYMENT = "hotel/bookRoomPayment";
	// String VIEW_HOTEL_ORDER_HISTORY = "hotel/orderHistory";
	// String VIEW_HOTEL_BOOKED_ROOM_LIST = "hotel/bookedRoomList";
	// String VIEW_HOTEL_SELL_ROOM = "hotel/sellRoom";

	// template name for email
	String TEMPLATE_EMAIL_BOOK_ROOM = "bookroom.ftl";
	String TEMPLATE_EMAIL_SELL_ROOM = "sellroom.ftl";
	String TEMPLATE_EMAIL_SELL_ROOM_INTERN = "sellinternroom.ftl";
	String TEMPLATE_EMAIL_SELL_ROOM_EXTERN = "sellexternroom.ftl";
	String TEMPLATE_EMAIL_MONEY_TRANSFER = "moneytransfer.ftl";
	String TEMPLATE_EMAIL_MONEY_TRANSFER_PAYPAL_SET = "moneytransfersetpaypal.ftl";
	String TEMPLATE_SOCIAL_CREATE_ACCOUNT = "socialSignup.ftl";
	String TEMPLATE_EMAIL_CREATE_ACCOUNT = "createAccount.ftl";
	String TEMPLATE_EMAIL_CHANGE_PASSWORD = "changePassword.ftl";
	String TEMPLATE_EMAIL_RESET_PASSWORD = "resetPassword.ftl";
	String TEMPLATE_CONFIRM_SELL_ROOM = "confirmSellRoom.ftl";
	String TEMPLATE_REJECT_SELL_ROOM = "rejectSellRoom.ftl";
	String TEMPLATE_INVITE_DELEGATE = "inviteDelegate.ftl";
	String TEMPLATE_MAKE_DELEGATE = "makeDelegate";
	String TEMPLATE_ACCEPT_DELEGATE_SENDER = "acceptDelegateSenderSend.ftl";
	String TEMPLATE_REJECT_DELEGATE_SENDER = "rejectDelegateSenderSend.ftl";
	String TEMPLATE_ACCEPT_DELEGATE_DELEGATE = "acceptDelegateDelegateSend.ftl";
	String TEMPLATE_REJECT_DELEGATE_DELEGATE = "rejectDelegateDelegateSend.ftl";

	// hotel Map Put
	// String MAP_HOTEL_ROOM_LIST = "roomList";
	// String MAP_HOTEL_LIST = "hotelList";
	// String MAP_HOTEL = "hotel";
	// String MAP_HOTEL_ROOM = "hotelRoom";
	// String MAP_HOTEL_ROOM_SEARCH = "hotelRoomSearch";
	// String MAP_HOTEL_PRICE_MIN = "priceMin";
	// String MAP_HOTEL_PRICE_MAX = "priceMax";
	// String MAP_HOTEL_RATETO_HOTEL = "rateToHotelDirect";
	// String MAP_HOTEL_RATETO_USER = "rateToUserDirect";
	// String MAP_HOTEL_AMENITIESDESC = "amenitiesdesk";
	// String MAP_HOTEL_RATETO_HOTEL_OR_USER = "ratoTOHotelOrUser";
	// String MAP_HOTEL_BACK_TO_ADVANCE_SEARCH = "backToSearch";
	// String MAP_HOTEL_AMENITIESDESC_LIST = "amenitiesDescList";
	// String MAP_HOTEL_BOOKED_ROOM_LIST = "bookedRoomList";
	// String MAP_PAYMENT_DETAIL = "mapPaymentDetail";
	// String MAP_HOTEL_SELL_BOOKING_ROOM = "sellBookingRoom";
	// String MAP_HOTEL_SELL_ROOM_DETAIL = "sellRoomDetails";
	// user room booking map put
	// String MAP_USER_ROOM_BOOKING_ID = "userRoomBookingId";
	// String MAP_BOOK_ORDER_HISTORY = "bookOrderHistory";
	// String MAP_SELL_ORDER_HISTORY = "sellOrderHistory";
	// modelMapAttributes
	// String MAP_USERMASTER_LOGIN = "userMasterLogin";
	// String MAP_USERMASTER_REGISTER = "userMasterRegister";
	// String MAP_USERMASTER_PROFILE = "profile";
	// String MAP_USERMASTER = "userMaster";
	// String MAP_ERROR_MESSAGE = "errorMessage";
	// String MAP_SUCCESS_MESSAGE = "successMessage";
	// String MAP_USER_NAME = "userName";
	// String MAP_DATE_ARRAY = "dateArray";
	// String MAP_TIME = "time";

	// path param
	// String PATH_PARAM_IMAGEID = "imageId";
	// String PATH_PARAM_HOTELID = "hotelId";
	// String PATH_PARAM_ROOMID = "roomId";
	// String PATH_PARAM_USER_ROOM_BOOKING_ID = "userRoomBooking";
	// String PATH_PARAM_STATUS = "status";

	// request param
	// String REQUEST_PARAM_DISTANCE = "distance";
	// String REQUEST_PARAM_LATITUDE = "latitude";
	// String REQUEST_PARAM_LONGITUDE = "longitude";
	// String REQUEST_PARAM_HOTELID = "hotelId";
	// String REQUEST_PARAM_PAYPAL_PAYERID = "PayerID";
	// String REQUEST_PARAM_PAYPAL_TOKEN = "token";
	// String REQUEST_PARAM_HOTEL_NAME = "hotelName";
	// String REQUEST_PARAM_CITY_NAME = "cityName";
	// String REQUEST_PARAM_DELEGATE_USER_ID = "delegateUserId";
	// String REQUEST_PARAM_DELEGATE_USER_EMAIL = "delegatedUserEmail";

	// pattern
	String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	// UserMaster
	// String FIELD_USERMASTER_USERNAME = "userName";
	// String FIELD_USERMASTER_EMAIL = "email";
	// String FIELD_ACTIVE = "active";
	// String FIELD_USERMASTER_PASSWORD = "password";
	// String FIELD_USERMASTER_RETYPEPASSWORD = "retypePassword";
	// String FIELD_USERMASTER_USERID = "userId";

	// HotelMaster
	// String FIELD_HOTEL_MASTER_HOTEL_ID = "hotelId";
	// String FIELD_HOTEL_MASTER_HOTEL_NAME = "hotelName";
	// String FIELD_HOTEL_MASTER_HOTEL_ADDRESS = "address";
	// String FIELD_HOTEL_MASTER_HOTEL_CITY = "city";
	// String FIELD_HOTEL_MASTER_HOTEL_BRAND = "hotelBrand";

	// HotelRoom
	// String FIELD_HOTEL_ROOM_ID = "roomId";
	// String FIELD_HOTEL_ROOM_BLOCK = "block";
	// String FIELD_HOTEL_ROOM_HOTEL_ID = "hotelId";
	// String FIELD_HOTEL_ROOM_NOOF_ROOMS = "noOfRooms";
	// String FIELD_HOTEL_ROOM_NOOF_ADULTS = "noOfAdults";
	// String FIELD_HOTEL_ROOM_NOOF_CHILDREN = "noOfChildren";
	// String FIELD_HOTEL_ROOM_USER_ROOM_SELLINGID = "userRoomSellingId";
	// String FIELD_HOTEL_ROOM_USER_ROOM_SELLING_FLAG = "userRoomSellingFlag";
	// String FIELD_HOTEL_ROOM_COST = "cost";

	// HotelRoomPhotos
	// String FIELD_HOTEL_ROOM_PHOTO_ID = "roomPhotoId";
	// String FIELD_HOTEL_ROOM_PHOTOS = "hotelRoomPhotos";

	// Room List
	// String FIELD_HOTEL_ROOM_START_DATE = "startDate";
	// String FIELD_HOTEL_ROOM_END_DATE = "endDate";
	// String FIELD_HOTEL_ROOM_SEARCH_HOTELNAME = "hotelsName";

	// UserRoomBooking
	// String FIELD_USER_ROOM_BOOKING_START_DATE = "startDate";
	// String FIELD_USER_ROOM_BOOKING_END_DATE = "endDate";
	// String FIELD_USER_ROOM_BOOKING_ROOM_ID = "roomId";
	// String FIELD_USER_ROOM_BOOKING_ID = "userRoomBookingId";
	// String FIELD_USER_USER_ID = "userId";
	// String FIELD_USER_ROOM_BOOKING_SELL_FLAG = "sellFlag";

	// HotelRating
	// String FIELD_HOTEL_RATING_RATING = "rating";
	// String FIELD_HOTEL_RATING_FORHOTEL = "forHotel";

	// PaymentMaster
	// String FIELD_PAYMENT_ID = "id";
	// String FIELD_PAYMENT_AMOUNT = "amount";
	// String FIELD_PAYMENT_DATE = "date";
	// String FIELD_PAYMENT_DESCRIPTION = "description";
	// String FIELD_PAYMENT_PAYMENT_ID = "paymentId";
	// String FIELD_PAYMENT_STATE = "state";
	// String FIELD_PAYMENT_USER_ROOM_BOOKING_ID = "userRoomBookingId";

	// EROOR Message
	// String ERROR_LOGIN = "Enter Valid Username and Password";
	// String ERROR_PASSWORD_MATCH = "Password Not Match";

	// session
	// String SESSION_ATTRIBUTE = "sessionUserMaster";
	// String SESSION_HOTEL_ROOM = "hotelRoom";
	// String SESSION_PRICE_MIN = "priceMin";
	// String SESSION_PRICE_MAX = "priceMax";
	// String SESSION_RATE_TO_HOTEL_OR_USER = "ratoTOHotelOrUser";
	// String SESSION_RATE_TO_HOTEL_DIRECT = "rateToHotelDirect";
	// String SESSION_RATE_TO_USER_DIRECT = "rateToUserDirect";
	// String SESSION_AMENITIESDESK = "amenitiesdesk";

	// AJAX Status
	// String STATUS_SUCCESS = "SUCCESS";
	// String STATUS_FAIL = "FAIL";
	// String AJAX_ERROR_KEY_COMMON = "errorMessageCommon";
	// String AJAX_ERROR_KEY_VALID = "valid";
	// String AJAX_ERROR_KEY_NOT_VALID = "notvalid";

	// common
	String STRING_AND = "&";
	String STRING_QUESTION = "?";
	String STRING_EQUAL = "=";
	String UTF8 = "UTF-8";
	String STRING_EMPTY = "";
	String STRING_DOT = ".";
	String STRING_ONE = "1";
	String STRING_SLASH = "/";
	String STRING_COLON = ":";
	String STRING_SPACE = " ";
	String STRING_DOUBLE_QUOTE = "\"";
	String STRING_ZERO = "0";
	String STRING_TRUE = "true";
	String STRING_FLASE = "false";

	String STRING_PAYPAL_APPROVAL_URL = "approval_url";
	String STRING_PAYPAL_STATUS_APPROVE = "approve";
	String STRING_PAYPAL_STATUS_CANCEL = "cancel";

	// redirect and forward
	String REDIRECT = "redirect";
	String FORWARD = "forward";

	// URL
	String URL_KEY = "key";
	String QUERY_STRING_OPERATION = "operation";
	String QUERY_STRING_OPERATION_CONFIRM = "confirm";
	String QUERY_STRING_OPERATION_CHANGE_PASSWORD = "chancgePassword";
	String QUERY_STRING_OPERATION_MAKE_DELEGATE = "makeDelegate";

	// Mail
	String EMAIL_HEADER_CONFIRM_EMAIL = "Confirm Your Email Address";
	String EMAIL_HEADER_SOCIAL_ACCOUNT = "Account created";
	String EMAIL_HEADER_CHNAGE_PASSWORD = "Change Password";
	String EMAIL_HEADER_RESET_PASSWORD = "Reset Password";
	String EMAIL_HEADER_ROOM_CONFIRM = "Room Confirm";
	String EMAIL_HEADER_ROOM_SELL = "Room Sell Confirm";
	String EMAIL_HEADER_CONFIRM_SELL_ROOM = "Room sell confirmation";
	String EMAIL_HEADER_REJECT_SELL_ROOM = "Room Sell Reject";
	String EMAIL_HEADER_INVITE_DELEGET = "Invite For Delegate";
	String EMAIL_HEADER_MAKE_DELEGET = "Add delegete";
	String EMAIL_HEADER_ACCEPT_DELEGATE = "accept delegete";
	String EMAIL_HEADER_REJECT_DELEGATE = "reject delegete";
	String EMAIL_HEADER_BOOK_ROOMS = "Book Room";
	String EMAIL_HEADER_SELL_ROOM = "Sell Room";
	String EMAIL_HEADER_MONEY_TRANSFER = "Money Transfer";

	// content type
	String CONTENT_IMAGE_JPEG = "image/jpeg";
	String CONTENT_JSON = "application/json";

	// ENV Message
	String ENV_ERROR_ROOM_SELECT = "error.selectRoom";
	String ENV_ERROR_EXCEPTION = "error.exception";
	String ENV_ERROR_AJAX_VALID = "error.ajax.notValid";
	String ENV_ERROR_INVALID_USER = "error.invalid.user";
	String ENV_ERROR_INVALID_EMAIL = "error.ajax.notValid.Email";
	String ENV_ERROR_PASSWORD_NOT_MATCH = "notmatch.password";
	String ENV_ERROR_GREATER_DATE = "greater.endDate";
	String ENV_ERROR_ROOM_ALREADY_BOOKED = "error.room.book.date";
	String ENV_ERROR_ROOM_BOOK_TIME_OUT = "error.room.book.timeout";
	String ENV_ERROR_ROOM_BOOK = "error.room.book";
	String ENV_ERROR_ROOM_NOT_AVAIL = "error.room.not.availabel";

	String ENV_SUCCESS_REGISTER = "index.register.success";
	String ENV_SUCCCESS_AJAX_VALID = "success.ajax.valid";
	String ENV_SUCCESS_CHECK_MAIL = "success.checkMail";
	String ENV_SUCCESS_ROOM_BOOK = "success.room.book";

	// ENV paypal
	String ENV_PAYPAL_CLIENT_ID = "paypal.clientID";
	String ENV_PAYPAL_CLIENT_SECRET = "paypal.clientSecret";
	String ENV_PAYPAL_PAYMENY_PAYPAL = "payment.Paypal";
	String ENV_PAYPAL_PAYMENY_CREDIT_CARD = "paymeny.CreditCard";
	String ENV_PAYPAL_INTENT_SALE = "intent.Sale";
	String ENV_PAYPAL_REDIRECT_CANCLE = "redirect.CancelUrl";
	String ENV_PAYPAL_REDIRECT_RETURN = "redirect.ReturnUrl";
	String ENV_PAYPAL_CURRENCY = "currency";

	// SERVICE CLASS NAME
	String SERVICE_COMMON = "commonService";
	String SERVICE_RATING = "ratingService";
	String SERVICE_MAIL_LOG = "mailLogService";
	String SERVICE_MOULE_MASTER = "moduleMasterService";
	String SERVICE_MODULE_ROOL = "moduleRoleService";
	String SERVICE_ROLE_MASTER = "roleMasterService";
	String SERVICE_SAVE_SEARCH_HISTORY = "saveSearchHistoryService";
	String SERVICE_USER_ROLE = "userRoleService";
	String SERVICE_USER_ROOM_BOOKING = "userRoomBookingService";
	String SERVICE_USER_ROOM_SELLING = "userRoomSellingService";
	String SERVICE_APPLICATION_MAIL = "applicationMailerService";
	String SERVICE_PAYPAL = "paypalService";
	// String SERVICE_PAYMENT = "paymentService";
	// String SERVICE_USER = "userService";
	String SERVICE_SELL_ROOM = "roomSellService";
	String SERVICE_DELEGATE_USER_SERVICE = "delegateUserService";
	String SERVICE_USER_PAYMENT_INFO = "userPaymentInfoService";
	String SERVICE_TEMP_SELL = "tempSellService";
	String SERVICE_USER_PREFERENCE = "userPreferenceService";

	String SERVICE_PAYMENT_FROM_BUYER = "paymentFromBuyerService";
	String SERVICE_PAYMENT_TO_SELLER = "paymentToSellerService";
	String SERVICE_SELL = "sellService";
	String SERVICE_CART = "cartService";
	String SERVICE_CART_DETAIL = "cartDetailService";
	String SERVICE_ORDER = "orderService";
	String SERVICE_ORDER_DETAIL = "orderDetailService";
	String SERVICE_AMENITY = "amenityService";
	String SERVICE_ROOM_AMENITY = "roomAmenityService";
	String SERVICE_PHOTO = "photoDaoService";
	String SERVICE_ROOM_PHOTO = "roomPhotoDaoService";
	String SERVICE_ORGANIZATION = "organizationService";
	String SERVICE_CITY = "cityService";
	String SERVICE_SEND_MAIL = "sendMailService";
	String SERVICE_VERIFICATION_TOKEN = "verificationTokenService";
	String SERVICE_SOCIAL_USER = "socialUserService";
	String SERVICE_ORDER_SELL = "orderedSellService";

	String SERVICE_PERMISSION = "permissionService";
	String SERVICE_ROLE = "roleService";
	String SERVICE_ROLE_PERMISSION = "rolePermissionService";

	String SERVICE_PAYMENT = "paymentService";
	String SERVICE_HOTEL_ROOM = "hotelRoomService";
	String SERVICE_HOTEL = "hotelService";
	String SERVICE_DELEGATE_USER = "delegateUserService";
	String SERVICE_USER = "userService";
	String SERVICE_ADMIN = "adminService";

	// DAO CLASS NAME
	// String DAO_COMMON = "commonDao";
	String DAO_HOTEL_ROOM = "hotelRoomDao";
	// String DAO_HOTEL_MASTER = "hotelMasterDao";
	// String DAO_RATING = "ratingDao";
	// String DAO_MAIL_LOG = "mailLogDao";
	// String DAO_MOULE_MASTER = "moduleMasterDao";
	// String DAO_MODULE_ROOL = "moduleRoleDao";
	// String DAO_ROLE_MASTER = "roleMasterDao";
	// String DAO_SAVE_SEARCH_HISTORY = "saveSearchHistoryDao";
	// String DAO_USER = "userDao";
	// String DAO_USER_ROLE = "userRoleDao";
	// String DAO_USER_ROOM_BOOKING = "userRoomBookingDao";
	// String DAO_USER_ROOM_SELLING = "userRoomSellingDao";
	// String DAO_DELEGATE_USER = "delegateUserDao";
	// String DAO_USER_PAYMENT_INFO = "userPaymentInfoDao";
	// String DAO_USER_PREFERENCE = "userPreferencesDao";
	// String DAO_TEMP_SELL = "tempSellDao";
	// String DAO_PAYMENT_FROM_BUYER = "paymentFromBuyerDao";
	// String DAO_PAYMENT_TO_SELLER = "paymentToSellerDao";
	// String DAO_SELL = "sellDao";
	// String DAO_CART = "cartDao";
	// String DAO_CART_DETAIL = "cartDetailDao";
	// String DAO_ORDER = "orderDao";
	// String DAO_ORDER_DETAIL = "orderDetailDao";
	// String DAO_AMENITY = "amenityDao";
	// String DAO_ROOM_AMENITY = "roomAmenityDao";
	// String DAO_PHOTO = "photoDao";
	// String DAO_ROOM_PHOTO = "roomPhotoDao";
	// String DAO_ORGANIZATION = "organizationDao";
	// String DAO_CITY = "cityDao";
	// String DAO_VERIFICATION_TOKEN = "verificationTokenDao";
	// String DAO_PERMISSION = "permissionDao";
	// String DAO_ROLE = "roleDao";
	// String DAO_ROLE_PERMISSION = "rolePermissionDao";
	// String DAO_SOCIAL_USER = "socialUserDao";

	// Criteria constants
	// String CRITERIA_TARGET_HOTEL_ROOM = "hotelRoom";
	// String CRITERIA_TARGET_HOTEL_MASTER = "hotelMaster";
	// String CRITERIA_TARGET_HOTEL_RATING = "hotelRating";
	// String CRITERIA_TARGET_HOTEL_ROOM_DETAIL = "hotelRoomDetail";
	// String CRITERIA_TARGET_HOTEL_ROOM_PHOTOS = "hotelRoomPhotos";
	// String CRITERIA_TARGET_MAIL_LOG = "mailLog";
	// String CRITERIA_TARGET_MODULE_MASTER = "moduleMaster";
	// String CRITERIA_TARGET_MODULE_ROLE = "moduleRole";
	// String CRITERIA_TARGET_ROLE_MASTER = "roleMaster";
	// String CRITERIA_TARGET_SAVE_SEARCH = "saveSearchHistory";
	// String CRITERIA_TARGET_USER_MASTER = "userMaster";
	// String CRITERIA_TARGET_USER_ROLE = "userRole";
	// String CRITERIA_TARGET_USER_ROOM_BOOKING = "userRoomBooking";
	// String CRITERIA_TARGET_USER_ROOM_SELLING = "userRoomSelling";
	// String CRITERIA_TARGET_PAYMENT_MASTER = "paymentMaster";

	// database operation
	// int SQL_OPERATION_LESS_THAN = 1;
	// int SQL_OPERATION_GREATER_TAHN = 2;
	// int SQL_OPERATION_LESS_EQUAL = 3;
	// int SQL_OPERATION_GREATER_EQUAL = 4;
	// int SQL_OPERATION_LIKE = 5;
	// int SQL_OPERATION_BETWEEN = 6;
	// int SQL_OPERATION_IS_NULL = 7;
	// int SQL_OPERATION_NOT_NULL = 8;
	// int SQL_OPERATION_EQUAL = 9;
	// int SQL_OPERATION_IN = 10;

	// database aggregate operation
	// int SQL_PROJECTION_ROW_COUNT = 1;
	// int SQL_PROJECTION_AVG = 2;
	// int SQL_PROJECTION_COUNT_DISTINCT = 3;
	// int SQL_PROJECTION_MAX = 4;
	// int SQL_PROJECTION_MIN = 5;
	// int SQL_PROJECTION_SUM = 6;

	// database aggregate operation
	// int SQL_ORDER_ASC = 1;
	// int SQL_ORDER_DESC = 2;

	//
	// int SQL_CRITERION_TYPE_NULL = 1;
	// int SQL_CRITERION_TYPE_AND = 2;
	// int SQL_CRITERION_TYPE_OR = 3;

	// int BLOCK_MINUTE = 15;

	Integer RELEASE_MINUTE = 15;
	Integer CART_UNLOCK_MINUTE = 5;
	Integer EXTRA_RELAEASE_MINUTE = 2;

	Integer REGISTER_EMAIL_LINK_EXPIRE_TIME = 12 * 24;
	Integer LOST_PASSWORD_EMAIL_LINK_EXPIRE_TIME = 12 * 24;

	int ERROR_LOGIN_CODE = 1;
	int ERROR_ROOM_TIME_OUT = 5;
	int ERROR_PAYMENT_CODE = 2;
	String ERROR_LOGIN_MESSAGE = "Please Login...";

	String CONTACT_EMAIL = "info@xchangehotel.com";
}
