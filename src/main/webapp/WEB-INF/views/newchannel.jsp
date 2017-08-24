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
		<div style="float:right;">
	    	<a href="adminhome" class="buttontype"> Back </a>
	  	</div>
	  	<br><br><br>
	  <form action="addNewChannel" method="post">
	 
		<div id="newProductScreen" style="margin-left:25%;margin-right:25%">
		    <h2>Add New Channel</h2>
		    <br><label for="channelId">Channel Id:</label><input type="text" id="channelId" name="channelId" required placeholder="e.g., UC_x5XG1OV2P6uZZ5FSM9Ttw"></input>
		    <br><label for="ownerAddress">Owner Blockchin Address:</label><input type="text" id="ownerAddress" name="ownerAddress" required placeholder="e.g., 0x12345"></input>
		    
		    <br><br><button id="add" type="submit">Add Channel</button> <button style="background-color: gray;" id="hideAddProductScreen" type="reset">Reset</button> <a href="adminhome" class="buttontype">Back</a>
		  </div>
	   </form>
</body>
</html>