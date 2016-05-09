<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header>
	<nav>
		<div class="line">
			<div class="s-2">

				<img src='<c:url value="/img/admin.png"></c:url>' height="150%"
					width="140%">
			</div>
			<p class="nav-text">Menu Items</p>
			<div class="top-nav s-10 right">
				<ul class="right">
					<!--<li><a>Home</a></li>-->
					<!-- <li><a>Service Provider</a>
						<ul>
							<li><a href="#">Request for Payment</a></li>
						</ul></li>

					<li><a>Charity</a>
						<ul>
							<li><a href="#">Request for Approval</a></li>
							<li><a href="#">Request for Payment</a></li>
						</ul></li>


					<li><a>Reports</a>
						<ul>
							<li><a>Transaction Report</a>
								<ul>
									<li><a href="#">Daily Transaction</a></li>
									<li><a href="#">Weekly Transaction</a></li>
									<li><a href="#">Monthly Transaction</a></li>
								</ul></li>
							<li><a href="#">New Customer report</a></li>
							<li><a href="#">Top rated Service Provider</a></li>
						</ul></li>
 -->

					<li><a>Admin Functionality</a>
						<ul>
							<li><a href="#">Manage Users</a></li>
							<li><a href="#">Manage Roles</a></li>
						</ul></li>
					<li><a href="#">Confirm Room</a></li>
					<li><a href="#">Contact Us</a></li>
					<li><a>Settings</a>
						<ul>
							<li><a href="#">Profile</a></li>
							<li><a href="<c:url value="/user/admin/logout"/>">Logout</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
</header>
