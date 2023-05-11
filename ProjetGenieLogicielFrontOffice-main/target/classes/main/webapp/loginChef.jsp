<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Sign in & Sign up Form</title>
<meta charset="UTF-8" />

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!--Link css-->

<style>

       <%@ include file="./css/logincss.css"%>

</style>
<!--Font-->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@300;400;700&#038;display=swap"
	rel="stylesheet" />
<script src="https://kit.fontawesome.com/64d58efce2.js"
	crossorigin="anonymous"></script>
</head>
<body>

	<div class="container1">
		<div class="forms-container1">
			<div class="signin-signup">

				<form action="<%=request.getContextPath()%>/signin_chef"
					class="sign-in-form" method="POST">
					<h2 class="title">Sign in</h2>
					<p style="color: red; font-size: 20px">${message2}</p>
					<div class="input-field">
						<i class="fas fa-user"></i> <input type="text" placeholder="CIN"
							name="cin" required />
					</div>
					<div class="input-field">
						<i class="fas fa-user"></i> <input type="text" placeholder="Email"
							name="email" required />
					</div>

					<div class="input-field">
						<i class="fas fa-lock"></i> <input type="password"
							placeholder="Password" name="passwd" required /><br>
						<br>
					</div>
					<input type="submit" value="Login" class="btn solid" />



				</form>


			</div>
		</div>
		<div class="panels-container1">
			<div class="panel left-panel">
				<div class="content">
					<h3>Welcome to space Chef division</h3>

				</div>

			</div>

		</div>


		<script type="text/javascript">
			
		<%@ include file="./javascript/app2.js"%>
			
		</script>
</body>
</html>