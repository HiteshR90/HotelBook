<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href='<c:url value="/css/style.css"></c:url>'>
<style>
.msg
{
	font-size: 18px;
	float : left;
	width:100%;
	text-align:left;
}
</style>
<div id="feedback"  >
	<form >
		<h2>Help us improve!</h2>
		<span id="commonFBMSG" style=" margin-left: 9px;"></span>
		<div style="margin-left: 3%; text-align: center;">
			<p style="float: left; text-align: center; margin-right: 10%;">
				<label>Comments</label><br> <input type="radio" name="fbType"
					value="comment" />
			</p>
			<p style="float: left;; text-align: center;">
				<label>Questions</label><br> <input type="radio" name="fbType"
					value="question" />
			</p>
			<p style="float: left; text-align: center;">
				<label>Bugs</label><br> <input type="radio" name="fbType"
					value="bug" />
			</p>
		</div>
		<span id="fbTypeError" class="msg"></span>
		<p style="text-align: center;">
			<label></label>
			<textarea id="feedbackNote"></textarea>
		</p>
		<p>
			<span id="feedbackError"  class="msg"></span>
		</p>
		<p>
			<input type="button" value="Submit&raquo;" class="btn submitfeedback" />
		</p>
		<input type="hidden" id="pageName" value="${param.pagename}">
	</form>
	<a href="#" class="pull_feedback" title="Click to leave feedback"><img
		src="<c:url value="/img/feedback.png"></c:url>" width="35"
		height="100" alt="feedback" /></a>
</div>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var isIE = (navigator.userAgent
								.indexOf("MSIE") != -1 || navigator.userAgent
								.indexOf("rv:11") != -1);
						if (isIE) {
							jQuery("#feedback")
									.animate({
										left : "-530px"
									});
						} else {
							jQuery("#feedback")
									.animate({
										left : "-362px"
									});
						}
						var isOpen = false;
						$(".pull_feedback")
								.click(
										function() {
											if (!isOpen) {
												isOpen = true;
												jQuery("#feedback").animate({
													left : "0px"
												});
											} else {
												isOpen = false;
												var isIE = (navigator.userAgent
														.indexOf("MSIE") != -1 || navigator.userAgent
														.indexOf("rv:11") != -1);
												if (isIE) {
													jQuery("#feedback")
															.animate({
																left : "-530px"
															});
												} else {
													jQuery("#feedback")
															.animate({
																left : "-362px"
															});
												}

											}
										});
						$('.submitfeedback')
								.click(
										function() {
											$('#feedbackError').html('');
											$('#fbTypeError').html('');
											$('#commonFBMSG').html('');

											var fbMessage = $('#feedbackNote')
													.val();
											var fbtype = $(
													'input[name=fbType]:checked')
													.val();
											var hashError = false;
											if (fbMessage == "") {
												$('#feedbackError')
														.html(
																'Please enter feedback').css('color','#f78c30');
												hashError = true;
											}
											if (fbtype == ""
													|| fbtype == undefined) {
												$('#fbTypeError').html(
														'Please select').css('color','#f78c30');
												hashError = true;
											}
											if (!hashError) {
												$
														.ajax({
															type : "POST",
															url : "${pageContext.servletContext.contextPath}/feedback/"
																	+ $(
																			"#pageName")
																			.val()
																	+ ".json",
															data : {
																feedBackType : fbtype,
																feedbackNote : fbMessage
															},
															success : function(
																	response) {
																$(
																		'#feedbackNote')
																		.val('');
																$(
																		'input:radio[name="fbType"]')
																		.removeAttr(
																				'checked');
																$(
																		'#commonFBMSG')
																		.html(
																				'Thanks for your feedback').css('color','#f78c30');
															},
															error : function(
																	response) {
																alert("error"
																		+ response);
															}
														});
											}
										});
					}); //document.ready
</script>