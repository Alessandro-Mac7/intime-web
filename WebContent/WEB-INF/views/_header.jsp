<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<link href='https://fonts.googleapis.com/css?family=Capriola'
	rel='stylesheet'>
	<link rel="stylesheet" href="fontawesome-stars.css">

<link rel="stylesheet" href="css/jquery.fullpage.css">
<link rel="stylesheet" href="css/in-time.css">

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
<script>
	$(window).load(function() {
		$(".loader").fadeOut("slow");
		;
	});
</script>

<title>inTime</title>
</head>

<body>
	<div class="loader"></div>

	<header>
		<!-- Navigazione -->
		<nav class="navbar custom-nav navbar-default navbar-fixed-top">
			<div class="container-fluid">
				<!-- HEADER-NAV -->
				<div class="col-sm-2 col-md-2">
					<div class="navbar-header">
						<c:choose>
							<c:when test="${not empty loginedClient}">
								<a class="navbar-brand"
									href="${pageContext.request.contextPath}/areaPersonale">inTime</a>
							</c:when>
							<c:when test="${empty loginedClient}">
								<a class="navbar-brand"
									href="${pageContext.request.contextPath}/home">inTime</a>
							</c:when>
						</c:choose>

						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#myNavbar">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</div>
				</div>
				<!-- /HEADER-NAV -->
				<!-- BODY-NAV -->
				<div class="collapse navbar-collapse" id="myNavbar">
					<div class="col-sm-8 col-md-8">
						<form class="navbar-form" method="get" action="RicercaServlet">
							<div class="input-group input-group-custom">
								<input id="ricerca" name="ricerca"
									class="form-control" placeholder="Cerca professionista..."
									size="100" value=${ricercaVal}>
								<div class="input-group-btn">
									<button class="btn" type="submit">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
						</form>
					</div>

					<div class="col-sm-2 col-md-2">

						<ul class="nav navbar-nav navbar-right">
							<c:choose>
								<c:when test="${not empty loginedClient}">
									<li class="dropdown"><a class="dropdown-toggle"
										data-toggle="dropdown" href="#" style="font-size: 10px">${loginedClient.mail}<span
											class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="${pageContext.request.contextPath}/logout">
													<span class="fa fa-sign-out"></span> Esci
											</a></li>
										</ul></li>
								</c:when>
								<c:when test="${empty loginedClient}">
									<li><a href="${pageContext.request.contextPath}/login">
											<span class="fa fa-sign-in"></span> Accedi
									</a></li>
								</c:when>
							</c:choose>
						</ul>
					</div>
				</div>
			</div>
		</nav>
	</header>


	<script src="js/autocompleteSearch.js"></script>

</body>
</html>