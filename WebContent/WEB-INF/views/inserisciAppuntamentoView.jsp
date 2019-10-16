<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href='https://fonts.googleapis.com/css?family=Capriola'
	rel='stylesheet'>
<link rel="stylesheet" href="css/in-time.css">
<title>inTime</title>
</head>
<body>
	<div class="wrapper">
		<!-- Sidebar -->
		<nav id="sidebar">
			<div class="sidebar-header">
				<div class="sidebar-brand">inTime</div>
				<img alt="user-photo" src="user-image/${loginedPro.mail}.png"
					width="80" height="80">
				<p>${loginedPro.mail}</p>
				<a href="${pageContext.request.contextPath}/logout"><button
						class="btn btn-xs btn-logout" type="button">
						<i class="fa fa-power-off"></i>
					</button></a>
			</div>

			<ul class="list-unstyled components">
				<li ><a
					href="${pageContext.request.contextPath}/areaPersonale"><i
						class="fa fa-home" aria-hidden="true"></i> Home</a></li>
				<li class="active"><a
					href="${pageContext.request.contextPath}/inserisciAppuntamento"><i
						class="fa fa-clock-o" aria-hidden="true"></i> Nuovo Appuntamento</a></li>
				<li><a
					href="${pageContext.request.contextPath}/storicoAppuntamenti"><i
						class="fa fa-calendar" aria-hidden="true"></i> Storico
						Appuntamenti</a></li>
				<li><a href="${pageContext.request.contextPath}/clienti"><i
						class="fa fa-users" aria-hidden="true"></i> I miei Clienti</a></li>
				<li><a
					href="${pageContext.request.contextPath}/recensioniProfessionista"><i
						class="fa fa-comments-o" aria-hidden="true"></i> Recensioni</a></li>
				<li><a
					href="${pageContext.request.contextPath}/recensioniProfessionista"><i
						class="fa fa-user" aria-hidden="true"></i> Profilo</a></li>
			</ul>
		</nav>


		<!-- Page Content -->
		<div class="main-content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-sm-4">
						<button type="button" id="sidebarCollapse" class="btn navbar-btn">
							<i class="fa fa-bars" aria-hidden="true"></i>
						</button>

					</div>
				</div>
				<div class="container-fluid">
					<div class="row">
						<div class="col md-12 text-center">
							<h2>Crea un appuntamento</h2>
							<p class="lead">Crea un appuntamento in base alle tue
								esigenze specificando la data l'orario d'inizio e l'orario di
								fine.</p>
						</div>
					</div>
					<form class="form-horizontal" action="InserisciAppuntamentoServlet"
						method="post">
						<div class="form-group">
							<label class="control-label col-sm-2" for="data">Data:</label>
							<div class="col-sm-10">
								<input type="date" class="form-control" name="data"
									placeholder="gg/mm/aaaa">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="oraInizio">Orario
								inizio:</label>
							<div class="col-sm-10">
								<input type="time" class="form-control" name="oraInizio">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="oraFine">Orario
								fine:</label>
							<div class="col-sm-10">
								<input type="time" class="form-control" name="oraFine">
							</div>
						</div>
						<p class="col-sm-offset-2 col-sm-8" style="color: red;">${errorMessage}</p>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-8">
								<button type="submit" class="btn btn-default">Crea
									Appuntamento</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		</div>

		<!-- FOOTER -->
		<jsp:include page="_footer.jsp"></jsp:include>
		<!-- /FOOTER -->
</body>
</html>