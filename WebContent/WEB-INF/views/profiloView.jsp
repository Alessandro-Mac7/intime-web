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
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=chiave&callback=initMap"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>

<script type="text/javascript" src="js/geolocation.js"></script>
<title>inTime</title>
</head>
<body>

	<script type="text/javascript">
		$(window).load(function() {
			$('.loader').fadeOut();
		});
	</script>

	<div class="loader"></div>

	<div class="wrapper">
		<!-- Sidebar -->
		<nav id="sidebar">
			<div class="sidebar-header">
				<div class="sidebar-brand">inTime</div>
				<img alt="user-photo" src="user-image/default.png" width="80"
					height="80">
				<p>${loginedPro.nome}</p>
				<a href="${pageContext.request.contextPath}/logout"><button
						class="btn btn-xs btn-logout" type="button">
						<i class="fa fa-power-off"></i>
					</button></a>
			</div>

			<ul class="list-unstyled components">
				<li><a href="${pageContext.request.contextPath}/areaPersonale"><i
						class="fa fa-home" aria-hidden="true"></i> Home</a></li>
				<li><a
					href="${pageContext.request.contextPath}/storicoAppuntamenti"><i
						class="fa fa-calendar" aria-hidden="true"></i> Appuntamenti</a></li>
				<li><a href="${pageContext.request.contextPath}/clienti"><i
						class="fa fa-users" aria-hidden="true"></i> Clienti</a></li>

				<li class="active"><a
					href="${pageContext.request.contextPath}/profilo"><i
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
				<div class="row">
					<div class="col-sm-6 col-sm-offset-5">
						<img alt="user-photo" src="user-image/default.png" width="100"
							height="100">
					</div>
				</div>

				<hr>

				<c:if test="${not empty message}">
					<div class="row text-center">
						<h3>${message}</h3>
					</div>
					<div class="row">
						<div class="progress">
							<div class="progress-bar progress-bar-striped active"
								role="progressbar" aria-valuenow="40" aria-valuemin="0"
								aria-valuemax="100" style="width: ${progresso}%">${progresso}%</div>
						</div>
					</div>
				</c:if>

				<div class="row">
					<div class="col-sm-6">
						<div class="panel panel-default" id="panelDati">
							<div class="panel-heading">
								<span> <i class="fa fa-address-book"></i>&nbsp;&nbsp;Informazioni
									Personali
								</span>
								<div class="btn-group pull-right">
									<button class="btn btn-xs btn-default " id="editMode"
										data-toggle="tooltip" title="Modifica">
										<i class="fa fa-edit"></i>
									</button>
								</div>
							</div>
							<div class="panel-body" style="min-height: 400px">
								<ul class="list-group clearfix">
									<li class="list-group-item">Nome:
										<div class="pull-right">${loginedPro.nome}</div>
									</li>
									<li class="list-group-item">Cognome:
										<div class="pull-right">${loginedPro.cognome}</div>
									</li>
									<li class="list-group-item">Professione:
										<div class="pull-right editable">${loginedPro.professione}</div>
										<div class="pull-right inputMode">
											<input type="text" name="professione">
										</div>
									</li>
									<li class="list-group-item">Indirizzo:
										<div class="pull-right editable">${loginedPro.indirizzo}</div>
										<div id="addressAutocomplete" class="pull-right inputMode">
											<input type="text" name="indirizzo">
										</div>
									</li>
									<li class="list-group-item">Telefono: <c:if
											test="${not empty loginedPro.telefono}">
											<div class="pull-right editable">${loginedPro.telefono}</div>
										</c:if> <c:if test="${empty loginedPro.telefono}">
											<div class="pull-right editable">Aggiungi un recapito
												telefonico</div>
										</c:if>
										<div class="pull-right inputMode">
											<input type="text" name="telefono">
										</div>
									</li>
									<li class="list-group-item">Sito:<c:if
											test="${not empty loginedPro.sito}">
											<div class="pull-right editable">${loginedPro.sito}</div>
										</c:if> <c:if test="${empty loginedPro.sito}">
											<div class="pull-right editable">Aggiungi il tuo Sito</div>
										</c:if>
										<div class="pull-right inputMode">
											<input type="text" name="sito">
										</div>
									</li>
								</ul>

								<div class="pull-right" style="padding-top: 20px;">
									<button type="button" class="btn btn-default inputMode"
										id="confirmEdit">Conferma Modifiche</button>
								</div>
							</div>
						</div>

					</div>
					<div class="col-sm-6">
						<div class="panel-group">
							<div class="panel panel-default">
								<div class="panel-heading"><i class="fa fa-book" aria-hidden="true"></i>&nbsp;&nbsp;
								Biografia</div>
								<div class="panel-body">
									<c:if test="${not empty loginedPro.descrizione}">
										<div class="well well-lg"><a class="editable">${loginedPro.descrizione}</a>
											<input class="inputMode" type="text" name="descrizione">
											
										</div>
										
									</c:if>

									<c:if test="${empty loginedPro.descrizione}">
										<div class="well well-lg">
											<a class="editable">Aggiungi una descrizione...</a>
										
											<input class="inputMode" type="text" name="descrizione">
								
										</div>
									</c:if>

								</div>
							</div>
							<!-- Mappa API google -->
							<div class="panel panel-default">
								<div class="panel-heading">
									<span> <i class="fa fa-map-marker"></i>&nbsp;&nbsp;Indirizzo
								</span>
								</div>
									<div id="map" class="panel-body" style="max-height: 250px">
									
									</div>
								
							</div>
						</div>
					</div>

				</div>


				<!-- /Row -->
			</div>
			<!-- /Container -->
		</div>
		<!-- /Main -->
	</div>
	<!-- /Wrapper -->


	<!-- Modal -->
	<div class="modal fade" id="modalConfirm" role="dialog">
		<div class="modal-dialog">

			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h4 class="modal-title">Conferma modifiche</h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						id="modalConfirmButton" data-dismiss="modal">SI</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">NO</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Successo Valutazione Popup -->
	<div class="alert alert-success alert-dismissable alert-fixed fade in"
		id="successModifica" style="display: none;">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>Successo!</strong> I dati sono stati modificati con successo!
	</div>

	<!-- FOOTER -->
	<jsp:include page="_footer.jsp"></jsp:include>
	<!-- /FOOTER -->
	<script type="text/javascript" src="js/profilo-pro.js"></script>

</body>
</html>