<html>
<body>
	Hello, ${name}<br/>
	Your Room is booked for
	<table>
		<tr>
			<th>Hotel Name</th>
			<th>Start Date</th>
			<th>End Date</th>
			<th>Price</th>
		</tr>
		<#list roomData as room>
		<tr>
			<td>${room.hotelName}</td>
			<td>${room.startDate}</td>
			<td>${room.endDate}</td>
			<td>${room.cost}</td>
		</tr>
		</#list>
	</table>
	<br/><br/>
	Thanks,<br/>
	XchangeHotel Team.
</body>
</html>