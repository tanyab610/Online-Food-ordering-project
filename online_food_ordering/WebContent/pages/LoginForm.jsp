<!DOCTYPE html>
<html>
<head>
<title>Order food online</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
<link rel="stylesheet" type="text/css" href="/online_food_ordering/resources/style.css" media="all" />
<link rel="stylesheet" type="text/css" href="/online_food_ordering/resources/demo.css" media="all" />
</head>
<body>
<div class="container">
<header>
<h1><span>HUNGRY??  ORDER FOOD ONLINE!!</span> LOGIN OR REGISTER AS FOODLOVERS</h1>
</header>
<div  class="form">
		<form id="contactform" action="/online_food_ordering/LoginServlet" method="post">
		<br>
		<h1><a href="Index.jsp">Click to go back</a></h1>
				<br>
		<p class="contact">
		<label for="username">Customer Name</label>
		</p>
		<input id="username" name="name" placeholder="name" required=" " tabindex="1" type="text" >
		<p class="contact">
		<label for="email">Email</label>
		</p>
		<input type="email" id="email" name="email" required=" " tabindex="2"> <br>
		<p class="contact">
		<label for="address">address</label>
		</p>
		<input id="address" name="address" required=" " tabindex="2"> <br>
		<br> <input class="buttom" name="submit" id="submit" tabindex="3" value="Login !" type="submit">
		<p class="contact">
		<h1><a href="CustomerRegistration.jsp">Register Me</a></h1>
		</p>
		
		</form>
		</div>
		</div>
		</body>
		</html>