<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type"
	content="text/html; charset=windows-1250">
<meta name="viewport" content="width=device-width" />
<title>Confirm Room</title>
<link rel="stylesheet"
	href='<c:url value="/css/admin/components.css"></c:url>'>
<link rel="stylesheet"
	href='<c:url value="/css/admin/responsee.css"></c:url>'>

<!-- CUSTOM STYLE -->
<link rel="stylesheet"
	href='<c:url value="/css/admin/template-style.css"></c:url>'>
<link rel="stylesheet"
	href='<c:url value="/css/admin/formfield.css"></c:url>'>
<link href='<c:url value="/css/admin/smartpaginator.css"></c:url>'
	rel="stylesheet" type="text/css" />

<script src='<c:url value="/scripts/jquery-1.8.3.min.js"></c:url>'></script>
<script type="text/javascript"
	src='<c:url value="/scripts/jquery-ui-1.10.4.custom.js"></c:url>'></script>

<script type="text/javascript"
	src='<c:url value="/scripts/modernizr.js"></c:url>'></script>
<script type="text/javascript"
	src='<c:url value="/scripts/responsee.js"></c:url>'></script>

<script src='<c:url value="/scripts/smartpaginator.js"></c:url>'
	type="text/javascript"></script>



<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		$('#black').smartpaginator({
			totalrecords : ${fn:length(roomData)},
			recordsperpage : 5,
			datacontainer : 'mt',
			dataelement : 'tr',
			initval : 0,
			next : 'Next',
			prev : 'Prev',
			first : 'First',
			last : 'Last',
			theme : 'black'
		});
		$('#black-contents').css('display', 'always');
	});
</script>


<!--[if lt IE 9]>
  <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
</head>
<body class="size-1140">
	<!-- TOP NAV WITH LOGO -->

	<jsp:include page="header.jsp" />
	<section>
		<!-- CAROUSEL -->
		<div class="contact">
			<div class="contact-form">
				<div class="breadcum"></div>
				<div>
					<c:if test="${!empty successMessage}">${successMessage}</c:if>
				</div>
				<div>
					<c:if test="${!empty errorMessage}">${errorMessage}</c:if>
				</div>
				<div id="content-bottom">
					<div id="addDynamicContent">
						<c:if test="${!empty  roomData}">
							<table id="mt" cellpadding="0" cellspacing="0" border="0"
								class="table">
								<tbody>
									<tr class="header" style="">
										<th>Hotel Name</th>
										<th>Cost</th>
										<th>Address</th>
										<th>Details</th>
										<th>Checkin/Checkout</th>
										<th>Status</th>
										<th>Confirm</th>
										<th>Reject</th>
									</tr>
									<c:forEach items="${roomData}" var="data">
										<tr class="notfirst" style="">
											<td>${data.hotelName}</td>
											<td>$${data.price}</td>
											<td><div>
													<span>${data.address}</span>
												</div></td>
											<td><div>
													<span>Room Type : ${data.roomType}</span>
												</div></td>
											<td><div>
													<span>Checkin :${data.checkInDate}<%-- <fmt:formatDate
															value="${data.checkInDate}" pattern="yyyy-MM-dd" /> --%>
													</span>
												</div>
												<div>
													<span>Checkout : ${data.checkOutDate}<%-- <fmt:formatDate
															value="${data.checkOutDate}" pattern="yyyy-MM-dd" /> --%></span>
												</div></td>
											<td><div>
													<span> <c:if test="${data.active=='true'}">
															-
														</c:if> <c:if test="${data.active!='true'}">
															<c:choose>
																<c:when test="${data.isConfirmed=='true'}">Approve</c:when>
																<c:when test="${data.isConfirmed=='false'}">Rejected</c:when>
																<c:otherwise>-</c:otherwise>
															</c:choose>
														</c:if>

													</span>
												</div></td>
											<c:if test="${data.active=='true'}">
												<td><a
													href="${pageContext.servletContext.contextPath}/user/admin/confirm-room/${data.id}"><input
														type="button" value="Confirm" class="buttonSmall"></a></td>
												<td><a
													href="${pageContext.servletContext.contextPath}/user/admin/reject-room/${data.id}"><input
														type="button" value="Reject" class="buttonSmall"></a></td>
											</c:if>
											<c:if test="${data.active!='true'}">
												<td>-</td>
												<td>-</td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div id="black-contents" class="contents">
								<div id="black" style="margin: auto;"></div>
							</div>
						</c:if>
					</div>


				</div>


			</div>
			<div class="clear"></div>
		</div>

	</section>

	<!-- FOOTER -->
	<jsp:include page="footer.jsp" />

</body>
</html>
