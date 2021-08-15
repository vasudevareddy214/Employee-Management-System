<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/edit.css">
<title>edit profile</title>
</head>
<body>
<nav>


<label class="logo">What'sapp</label>
<ul>
<li><a class="active" href="GlobalServlet?choice=view">View Profile</a></li>
<li><a  href="GlobalServlet?choice=search">Search Profile</a></li>
<li><a  href="GlobalServlet?choice=delete">Delete Profile</a></li>
<li><a  href="GlobalServlet?choice=edit">Edit Profile</a></li>
<li><a  href="GlobalServlet?choice=logout">Logout</a></li>
</ul>
</nav>
<div class="main">

<div class="register">
<h2>Edit Profile Here</h2>
<form  id="register" method="post">
<lable>Name:	</lable>
<br>
<input type="text" name="fname" id="name" placeholder="Enter your new name"> 
<br><br>

<lable> Location:	</lable>
<br>
<input type="text" name="address" id="name" placeholder="Enter Your new address">
<br><br>
<lable> Password:	</lable>
<br>
<input type="password" name="password" id="name" placeholder="Create new password">
<br><br>
<center><button class="btn" name="submit" id="submit">Edit</button></center>
</form>
</div>
</div>
</body>
</html>