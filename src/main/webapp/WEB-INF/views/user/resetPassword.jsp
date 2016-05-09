<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>One Suite Room | Reset Password</title>
<link rel="icon" type="image/x-icon"
	href="<c:url value="/img/OSR_Favicon.png"/>" />
<link rel="shortcut icon" type="image/x-icon href=" <c:url value="/img/OSR_Favicon.png"/>"/>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/normalize.css"/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/foundation.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/app.css"/>" />
<script src="<c:url value="/js/vendor/modernizr.js"/>"></script>
<script src="<c:url value="/js/jquery-1.11.1.min.js"/>"
	type="text/javascript"></script>


</head>
<!-- à¼¼ ã¤ â—•_â—• à¼½ã¤ welcome to OSR's source â•®(â•¯â–½â•°)â•­ -->
<body>



	<div class="row">
		<div id="splash-back" class="large-12 columns">

			<div class="row">
				<header id="splash-logo"
					class="small-10 small-centered medium-10 medium-centered large-10 large-centered columns">
				<img src="<c:url value="/img/homepagebanner.png"/>"
					alt="one-suite-room" /> </header>
			</div>


			<c:if test="${!empty  errorMessage}">${errorMessage}</c:if>
			<div class="row" style="margin: 5% 0 9% 0">
				<div class="large-12 columns">

					<form:form id="changePW"
						action="${pageContext.request.contextPath}/user/resetPassword"
						commandName="resetPassword" method="POST" style="margin-left: 30%">
						<form:hidden path="token" />
						<label> new password <form:password path="password"
								style="width: 55%; border: 1px solid #ccc;  border-radius: 5px;
    margin-bottom:5px;    margin-left: 26px;    padding: 5px;" /> <form:errors path="password" style="color: red;   float: left;    margin-bottom: 15px;
    margin-left: 131px;    width: 83%;"></form:errors>
						</label>
						<label> confirm password <form:password
								path="retypePassword" style="width: 55%; border: 1px solid #ccc;  border-radius: 5px;
    margin-bottom: 5px;  padding: 5px;  " /> <form:errors
								path="retypePassword" style="color: red;   float: left;    margin-bottom: 15px;
    margin-left: 131px;    width: 83%;"></form:errors>
						</label>
						<div>
							<button
								style="background-color: transparent !important; padding: 0;">
								<img src="<c:url value="/img/return-to-login.png"/>"
									width="200px" alt="submit" />
							</button>
						</div>

					</form:form>

				</div>
			</div>
		</div>
	</div>







	<!--footer-->

	<footer>
	<div class="row top-footer">
		<div class="large-12 medium-12 small-12 columns"></div>
	</div>
	<div class="row bottom-footer" style="margin-top: 80px;">
		<div class="large-12 medium-12 small-12 columns">
			<div  style="float:left; margin-left:20px;color:#fff;bottom: 0px;">© 2015 One Suite Room. All rights reserved.</div>
		</div>
	</div>
	</footer>









	<script src="<c:url value="/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.js"/>"></script>
	<script src="<c:url value="/js/foundation/foundation.alert.js"/>"></script>


	<script>
		$(document).foundation();
	</script>
	<jsp:include page="../feedback.jsp">
		<jsp:param value="resetpassword" name="pagename" />
	</jsp:include>
</body>
</html>



