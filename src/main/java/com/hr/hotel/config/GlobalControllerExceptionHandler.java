package com.hr.hotel.config;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.social.InternalServerErrorException;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hr.hotel.common.Constant;
import com.hr.hotel.dto.ErrorDto;
import com.hr.hotel.dto.ValidationErrorDTO;
import com.hr.hotel.exception.BaseWebApplicationException;
import com.hr.hotel.exception.FieldErrorException;

import freemarker.core.ParseException;
import freemarker.template.TemplateException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(GlobalControllerExceptionHandler.class);

	/**
	 * initBinder is used by validator for add validation for class like Date
	 * etc.
	 * 
	 * @param binder
	 *            used for register new validator
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// validate date format
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);

		// register java.util.Date class
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@ExceptionHandler(BaseWebApplicationException.class)
	@ResponseBody
	public ErrorDto webApplicationException(
			BaseWebApplicationException baseWebApplicationException,
			HttpServletResponse response) {
		logger.error("webApplicationException", baseWebApplicationException);
		// Response objResponse = null;
		// Map<String, Object> responseData = null;
		// responseData = new HashMap<String, Object>();
		// responseData.put("errorCode",
		// baseWebApplicationException.getErrorCode());
		// responseData.put("errorMessage",
		// baseWebApplicationException.getErrorMessage());
		// responseData.put("developerMessage",
		// baseWebApplicationException.getDeveloperMessage());
		// responseData.put("code", baseWebApplicationException.getStatus());
		// objResponse = new Response("FAIL", responseData, true);
		response.setStatus(baseWebApplicationException.getStatus());
		return new ErrorDto(baseWebApplicationException.getErrorMessage(),
				baseWebApplicationException.getErrorCode(),
				baseWebApplicationException.getDeveloperMessage());
	}

	@ExceptionHandler(FieldErrorException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public Map<String, Object> fieldErrorException(
			FieldErrorException fieldErrorException) {
		logger.error("fieldErrorException", fieldErrorException);
		// Response objResponse = null;
		Map<String, Object> responseData = null;
		responseData = new HashMap<String, Object>();
		responseData.put("errors", fieldErrorException.getValidationErrorDTO()
				.getFieldErrors());
		// responseData.put("code", HttpStatus.BAD_REQUEST);
		// objResponse = new Response("FAIL", responseData, true);
		return responseData;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public Map<String, Object> processValidationError(
			MethodArgumentNotValidException ex) {
		logger.error("processValidationError", ex);
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		// Response objResponse = null;
		Map<String, Object> responseData = null;
		responseData = new HashMap<String, Object>();
		responseData.put("errors", processFieldErrors(fieldErrors)
				.getFieldErrors());
		// responseData.put("code", HttpStatus.BAD_REQUEST);
		// objResponse = new Response("FAIL", responseData, true);
		return responseData;
	}

	private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
		ValidationErrorDTO dto = new ValidationErrorDTO();

		for (FieldError fieldError : fieldErrors) {
			// String localizedErrorMessage =
			// resolveLocalizedErrorMessage(fieldError);
			// dto.addFieldError(fieldError.getField(), localizedErrorMessage);
			dto.addFieldError(fieldError.getField(),
					fieldError.getDefaultMessage());
		}

		return dto;
	}

	/*
	 * private String resolveLocalizedErrorMessage(FieldError fieldError) {
	 * Locale currentLocale = LocaleContextHolder.getLocale(); String
	 * localizedErrorMessage = messageSource.getMessage(fieldError,
	 * currentLocale);
	 * 
	 * //If the message was not found, return the most accurate field error code
	 * instead. //You can remove this check if you prefer to get the default
	 * error message. if
	 * (localizedErrorMessage.equals(fieldError.getDefaultMessage())) { String[]
	 * fieldErrorCodes = fieldError.getCodes(); localizedErrorMessage =
	 * fieldErrorCodes[0]; }
	 * 
	 * return localizedErrorMessage; }
	 */

	@ExceptionHandler(value = { IOException.class, TemplateException.class,
			MessagingException.class, IllegalAccessException.class,
			InvocationTargetException.class, NoSuchMethodException.class,
			ParseException.class, Exception.class })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> exceptionHandle(Exception exception) {
		logger.error("exceptionHandle", exception);
		// Response objResponse = null;
		Map<String, Object> responseData = new HashMap<String, Object>();
		// responseData.put("message", "Please contact Administrator");
		responseData.put("message", exception.getMessage());
		// responseData.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
		// objResponse = new Response("FAIL", responseData, true);
		exception.printStackTrace();
		return responseData;
	}

	@ExceptionHandler(value = { InvalidAuthorizationException.class })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	public Map<String, Object> invalidAuthorizationException(
			InvalidAuthorizationException exception) {
		logger.error("invalidAuthorizationException", exception);
		Map<String, Object> responseData = new HashMap<String, Object>();
		responseData.put("message", exception.getMessage());
		// responseData.put("code", HttpStatus.METHOD_NOT_ALLOWED);
		exception.printStackTrace();
		// return new Response("FAIL", responseData, true);
		return responseData;
	}

	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	public Map<String, Object> methodNotSupportedException(
			HttpRequestMethodNotSupportedException exception) {
		logger.error("HttpRequestMethodNotSupportedException", exception);
		Map<String, Object> responseData = new HashMap<String, Object>();
		responseData.put("message", exception.getMessage());
		// responseData.put("code", HttpStatus.METHOD_NOT_ALLOWED);
		exception.printStackTrace();
		// return new Response("FAIL", responseData, true);
		return responseData;
	}

	@ExceptionHandler(value = { HttpMediaTypeNotSupportedException.class })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public Map<String, Object> httpMediaTypeNotSupportedException(
			HttpMediaTypeNotSupportedException exception) {
		logger.error("httpMediaTypeNotSupportedException", exception);
		Map<String, Object> responseData = new HashMap<String, Object>();
		responseData.put("message", exception.getMessage());
		// responseData.put("code", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		exception.printStackTrace();
		// return new Response("FAIL", responseData, true);
		return responseData;
	}

	@ExceptionHandler(value = { HttpMessageNotReadableException.class })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public Map<String, Object> httpMessageNotReadableException(
			HttpMessageNotReadableException exception) {
		logger.error("httpMessageNotReadableException", exception);
		Map<String, Object> responseData = new HashMap<String, Object>();
		// responseData.put("message", exception.getMessage());
		responseData.put("message", "Not Valid Json Format");
		// responseData.put("code", HttpStatus.NOT_ACCEPTABLE);
		exception.printStackTrace();
		// return new Response("FAIL", responseData, true);
		return responseData;
	}

	@ExceptionHandler(value = { InternalServerErrorException.class })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public Map<String, Object> internalServerErrorException(
			InternalServerErrorException exception) {
		logger.error("internalServerErrorException", exception);
		Map<String, Object> responseData = new HashMap<String, Object>();
		responseData.put("message",
				"Not valid access token or " + exception.getMessage());
		// responseData.put("code", HttpStatus.NOT_ACCEPTABLE);
		exception.printStackTrace();
		// return new Response("FAIL", responseData, true);
		return responseData;
	}

	@ExceptionHandler(value = { OptimisticEntityLockException.class })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public Map<String, Object> optimisticEntityLockException(
			OptimisticEntityLockException exception) {
		logger.error("optimisticEntityLockException", exception);
		Map<String, Object> responseData = new HashMap<String, Object>();
		//responseData.put("message", exception.getMessage());
		responseData.put("message", "Multiple update found");
		// responseData.put("code", HttpStatus.NOT_ACCEPTABLE);
		//exception.printStackTrace();
		// return new Response("FAIL", responseData, true);
		return responseData;
	}
}
