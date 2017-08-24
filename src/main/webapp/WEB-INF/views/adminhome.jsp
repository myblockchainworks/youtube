<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>YouTube DApp</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/youtube.js" />"></script>

</head>
<body>

	<h1>YouTube DApp - Subscribers Reward App</h1>
	
	<ul class="nav navbar-nav pull-right">
			<li style="padding-top: 15px;color: white;">Welcome ${currentUser.fullname}</li><li class=""><a href="#">|</a></li><li class=""><a href="logout">Logout</a></li>
		</ul>
		<br><br>
		<h2>Channels</h2>
		<div style="float:right;">
	    	<a href="newchannel" class="buttontype"> + New Channel</a>
	  	</div>
	  	<br>
	  <table class="table table-bordered table-hover" id="products">
		<tr>
			<th>#</th>
          	<th>Title</th>
          	<th>Id</th>
          	<th>Owner</th>
          	<th>Subscriptions</th>
          	<th>Last Payout Date</th>
          	<th>Paid Token (Owner)</th>
		</tr>
		<c:set var="count" value="0" scope="page" />
		<c:forEach items="${channels}" var="channel">
			<c:set var="count" value="${count + 1}" scope="page"/>
			<tr>
				<td>${count}</td>
				<td>${channel.title}</td>
				<td>${channel.id}</td>
				<td>${channel.owner}</td>
				<td>${channel.subscriberCount}</td>
				<td class="payoutDate${count}"><script>formatDate('<c:out value='${channel.lastPayoutDate}'/>', 'payoutDate' + '<c:out value='${count}'/>');</script></td>
				<td>${channel.token}</td>
			</tr>
		</c:forEach>
	</table>
  <span>Total Channels : </span> <span id="channelsCount">${count}</span>
</body>
</html>