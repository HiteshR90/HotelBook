<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type"
	content="text/html; charset=windows-1250">
<meta name="viewport" content="width=device-width" />
<title>OSR | Admin</title>

<link rel="stylesheet"
	href='<c:url value="/css/admin/components.css"></c:url>'>
<link rel="stylesheet"
	href='<c:url value="/css/admin/responsee.css"></c:url>'>

<!-- CUSTOM STYLE -->

<link rel="stylesheet"
	href='<c:url value="/css/admin/template-style.css"></c:url>'>
<link rel="stylesheet"
	href='<c:url value="/css/admin/formfield.css"></c:url>'>

<script src='<c:url value="/scripts/jquery-1.8.3.min.js"></c:url>'></script>
<script type="text/javascript"
	src='<c:url value="/scripts/jquery-ui-1.10.4.custom.js"></c:url>'></script>

<script type="text/javascript"
	src='<c:url value="/scripts/modernizr.js"></c:url>'></script>
<script type="text/javascript"
	src='<c:url value="/scripts/responsee.js"></c:url>'></script>



<!--[if lt IE 9]>
  <script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
<body class="size-1140">
	<!-- TOP NAV WITH LOGO -->
	<header> <nav>
	<div class="line">
		<div class="s-2">
			<img src='<c:url value="/img/admin.png"></c:url>'>
		</div>


	</div>
	</nav> </header>
	<section> <!-- CAROUSEL -->

	<div class="contact">
		<div class="login-form">
			<div class="content">
				<h2 class="style">Admin Login</h2>
			</div>
			<div>
				<c:if test="${!empty errorMessage}">${errorMessage}</c:if>
			</div>
			<form:form method="POST" action="${pageContext.servletContext.contextPath}/user/admin/login" commandName="adminUser">
				<div>
					<span> <form:input path="userName" cssClass="textbox"
							placeholder="email/username" /></span>
				</div>
				<div>
					<span> <form:errors path="userName" cssClass="errorMSG"></form:errors>
					</span>
				</div>
				<div>
					<span> <form:password path="password" cssClass="textbox"
							placeholder="password" />
					</span>
				</div>
				<div>
					<span> <form:errors path="password" cssClass="errorMSG"></form:errors>
					</span>
				</div>
				<div>
					<span><input type="submit" value="Login"></span>
				</div>
			</form:form>
		</div>
		<div class="clear"></div>
	</div>

	</section>
	<!-- FOOTER -->
	<jsp:include page="footer.jsp" />
</body>
</html>
