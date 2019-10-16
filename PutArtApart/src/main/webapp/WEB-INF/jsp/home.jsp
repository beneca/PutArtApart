<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %> 

<html>

	<head>
		<meta charset="utf-8">
		<title>Put Art Apart</title>
	
		<!-- framework Bootstrap -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
	
		<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
		<c:url var = "css" value = "/resources/styles/stile.css"/>
		<link href="${css}" rel="stylesheet" type="text/css">
		<c:url var = "icona" value = "/resources/images/urlo.jpg"/>
		<link rel="shortcut icon" href="${icona}" type="image/x-icon">
		<c:url var = "jquery" value = "/resources/scripts/jquery-3.3.1.js"/>
		<script type="text/javascript" src="${jquery}"></script>
		<c:url var = "javascript" value = "/resources/scripts/main.js"/>
		<script type="text/javascript" src="${javascript}"></script>
		
	</head>
	
	<body>
		<header>
			<h1>Semantic Technologies: Put Art Apart</h1>
		</header>
	
	<h2>Search artworks around</h2>
		<form id="where">
		<pre>
			<label for="city">In which city are you?</label>
			<input type="text" id="city" name="city"/>
			<button type="button" class="pulsante" id="button_search">search</button>
		</pre>
		</form>
	</body>
</html>