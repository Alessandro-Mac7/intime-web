<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
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
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=chiave&callback=initMap"></script>
<script type="text/javascript" src="js/geolocationAddress.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
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
				<li class="active"><a
					href="${pageContext.request.contextPath}/areaPersonale"><i
						class="fa fa-home" aria-hidden="true"></i> Home</a></li>
				<li><a
					href="${pageContext.request.contextPath}/storicoAppuntamenti"><i
						class="fa fa-calendar" aria-hidden="true"></i> Appuntamenti</a></li>
				<li><a href="${pageContext.request.contextPath}/clienti"><i
						class="fa fa-users" aria-hidden="true"></i> Clienti</a></li>

				<li><a href="${pageContext.request.contextPath}/profilo"><i
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
					<p class="bigFont col-sm-8">Bentornato ${loginedPro.nome}</p>

				</div>

				<hr>
				<div class="row">
					<!-- Pannello Appuntamenti -->
					<div class="col-md-4 col-sm-4">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-calendar fa-2x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="bigFont">${appuntamentiDaEffettuare}</div>
										<p class="lead">Appuntamenti</p>
									</div>
								</div>
							</div>

						</div>
					</div>
					<!-- /Pannello Appuntamenti -->

					<!-- Pannello Valutazioni -->
					<div class="col-md-4 col-sm-4">
						<div class="panel panel-green">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-comments fa-2x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div>
											<option id="rating" class="bigFont" value=${rating }>
										</div>
										<p id="numVal" class="lead">${numVal }&nbsp;Valutazioni</p>
									</div>
								</div>
							</div>

						</div>
					</div>
					<!-- /Pannello Valutazioni -->

					<!-- Pannello Prenotazioni -->
					<div class="col-md-4">
						<div class="panel panel-yellow">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-users fa-2x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="bigFont">${clienti}</div>
										<p class="lead">Clienti</p>
									</div>
								</div>
							</div>

						</div>
					</div>
					<!-- /Pannello Clienti -->
				</div>
				<!-- /Row -->

				<c:if test="${profiloCompleto ne true}">
					<div class="alert alert-warning alert-dismissable fade in"
						id="warningProfilo">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Attenzione!</strong> Mancano dei dati nel tuo Profilo,
						completali per avere maggiore visibilità!.
					</div>
				</c:if>

				<div class="row">
					<div class="col-sm-12">
						<div class="panel panel-default" id="panelOdierni">
							<div class="panel-heading">
								<span> <i class="fa fa-suitcase"></i>&nbsp;&nbsp;Appuntamenti
									Odierni
								</span>
								<div class="btn-group pull-right">
									<button class="btn btn-xs btn-default " id="reloadOdierni"
										data-toggle="tooltip" title="Refresh">
										<i class="fa fa-refresh"></i>
									</button>
									<button class="btn btn-xs btn-default " data-toggle="collapse"
										data-target="#bodyOdierni" title="Riduci">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="panel-body text-center collapse" id="bodyOdierni">

								<div class="ajaxLoader">
									<img class="img-responsive" alt="loading..."
										src="assets/ajax-loader.gif"
										style="padding-left: 50%; padding-top: 50px;">
								</div>

								<c:if test="${not empty appuntamentiOdierni }">
									<div id="carouselOdierni" class="carousel slide"
										data-ride="carousel">
										<div class="carousel-inner dynamicItem">


											<c:forEach var="appuntamentoOdierno"
												items="${appuntamentiOdierni}">
												<div class="item ">
													<div class="col-sm-offset-3 col-sm-6">

														<div class="well well-lg">
															<p>${appuntamentoOdierno.descrizione}</p>
															<hr>
															<strong>${appuntamentoOdierno.data}</strong>
															<hr>
															<p>Dalle&nbsp;${appuntamentoOdierno.ora_inizio}&nbsp;a&nbsp;
																${appuntamentoOdierno.ora_fine}</p>
															<hr>
															<button class="btn btn-default open-modal-button"
																id="${appuntamentoOdierno.codice}">Info</button>
														</div>
													</div>
												</div>
											</c:forEach>

										</div>
										<a data-slide="prev" href="#carouselOdierni"
											class="left carousel-control"><i
											class="fa fa-chevron-left"></i></a> <a data-slide="next"
											href="#carouselOdierni" class="right carousel-control"><i
											class="fa fa-chevron-right"></i></a>
									</div>
								</c:if>

								<c:if test="${empty appuntamentiOdierni }">
									<p class="empty">Nessun appuntameto creato oggi!</p>
								</c:if>
							</div>
						</div>
					</div>
				</div>

				<!-- Row2 -->
				<div class="row">
					<div class="col-sm-8">
						<div class="panel panel-default" id="panelNuovoAppuntamento">
							<div class="panel-heading">
								<span> <i class="fa fa-clock-o"></i>&nbsp;Crea un
									appuntamento
								</span>
								<div class="btn-group pull-right">
									<button class="btn btn-xs btn-default " data-toggle="collapse"
										data-target="#bodyNuovoAppuntamento" title="Riduci">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="panel-body collapse" id="bodyNuovoAppuntamento">

								<div class="ajaxLoader">
									<img class="img-responsive" alt="loading..."
										src="assets/ajax-loader.gif"
										style="padding-left: 50%; padding-top: 50px;">
								</div>

								<form class="form-horizontal" id="nuovoAppuntamentoForm"
									method="post">
									<div class="form-group resize-form">
										<label class="control-label col-sm-1">Data:</label>
										<div class="col-sm-3">
											<input type="date" class="form-control" name="data"
												placeholder="">
										</div>
										<label class="control-label col-sm-1">Inizio:</label>
										<div class="col-sm-3">
											<input type="time" class="form-control" name="oraInizio">
										</div>
										<label class="control-label col-sm-1">Fine:</label>
										<div class="col-sm-3">
											<input type="time" class="form-control" name="oraFine">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-12">
											<textarea class="form-control clear-space" rows="3"
												name="descrizione"
												placeholder="Inserisci una descrizione..."></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="clienti">Clienti:</label>
										<div class="col-sm-10">
											<input type="number" min="1" step="1" value="1"
												class="form-control" name="clienti">
										</div>
									</div>
									<p id="errorMessage" class="col-sm-12 text-center animated"
										style="color: tomato;"></p>
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-6">
											<button type="submit" class="btn btn-default">Crea
												Appuntamento</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>

					<div class="col-sm-4">
						<div class="panel panel-default" id="panelRecenti">
							<div class="panel-heading">
								<span> <i class="fa fa-calendar"></i>&nbsp;&nbsp;Appuntamenti
									Recenti
								</span>
								<div class="btn-group pull-right">
									<button class="btn btn-xs btn-default " id="reloadRecenti"
										data-toggle="tooltip" title="Refresh">
										<i class="fa fa-refresh"></i>
									</button>
									<button class="btn btn-xs btn-default " data-toggle="collapse"
										data-target="#bodyRecenti" data-toggle="tooltip"
										title="Riduci">
										<i class="fa fa-minus"></i>
									</button>
								</div>


							</div>
							<div class="panel-body text-center collapse" id="bodyRecenti">
								<div class="ajaxLoader">
									<img class="img-responsive" alt="loading..."
										src="assets/ajax-loader.gif"
										style="padding-left: 50%; padding-top: 50px;">
								</div>
								<c:if test="${not empty appuntamentiRecenti }">

									<div class="list-group dynamicList">

										<c:forEach var="appuntamentoRecente"
											items="${appuntamentiRecenti}">
											<button
												class="list-group-item list-group-item-action open-modal-button"
												id="${appuntamentoRecente.codice}">
												<p>${appuntamentoRecente.descrizione}</p>
												<strong>${appuntamentoRecente.data}</strong>
												<p>
													Dalle&nbsp;${appuntamentoRecente.ora_inizio}&nbsp;a&nbsp;
													${appuntamentoRecente.ora_fine}</p>
											</button>
										</c:forEach>

									</div>
								</c:if>


								<c:if test="${empty appuntamentiRecenti }">
									<p class="empty">Nessun appuntameto creato oggi!</p>
								</c:if>


							</div>
						</div>


					</div>
				</div>
				<!-- /Row2 -->

				<div class="row">
					<div class="col-sm-12">

						<div class="panel panel-default" id="panelValutazioniRecenti">
							<div class="panel-heading">
								<span> <i class="fa fa-comment"></i>&nbsp;&nbsp;Ultime
									Recensioni
								</span>
								<div class="btn-group pull-right">
									<button class="btn btn-xs btn-default " data-toggle="collapse"
										data-target="#bodyValutazioniRecenti" title="Riduci">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="panel-body text-center collapse"
								id="bodyValutazioniRecenti">

								<c:if test="${not empty valutazioniRecenti }">
									<div class="carousel slide" data-ride="carousel"
										id="quote-carousel">

										<div class="carousel-inner text-center">


											<c:forEach var="valutazioneRecente"
												items="${valutazioniRecenti}">
												<div class="item">
													<blockquote>
														<div class="row">
															<div class="col-sm-8 col-sm-offset-2">

																<p>${valutazioneRecente.descrizione}</p>
																<small>${valutazioneRecente.data}</small> <small
																	class="ratingStar">${valutazioneRecente.rating}</small>
																<small>${valutazioneRecente.mail_cliente}</small>

															</div>
														</div>
													</blockquote>
												</div>
											</c:forEach>

										</div>

									</div>
									<!-- Carousel Buttons Next/Prev -->
									<a data-slide="prev" href="#quote-carousel"
										class="left carousel-control"><i
										class="fa fa-chevron-left"></i></a>
									<a data-slide="next" href="#quote-carousel"
										class="right carousel-control"><i
										class="fa fa-chevron-right"></i></a>
								</c:if>

								<c:if test="${empty valutazioniRecenti }">
									<p class="empty">Nessuna valutazione oggi!</p>
								</c:if>

							</div>

						</div>
					</div>


				</div>

				<!-- Row3 -->
				<div class="row">

					<div class="col-sm-7">
						<div class="panel panel-default" id="panelChart">
							<div class="panel-heading">
								<span> <i class="fa fa-pie-chart"></i> &nbsp;Andamento
									Recensioni
								</span>
								<div class="btn-group pull-right">
									<button class="btn btn-xs btn-default " data-toggle="collapse"
										data-target="#myChart-wrap" title="Riduci">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="chart-wrap collapse" id="myChart-wrap">
								<canvas id="myChart"></canvas>
							</div>
						</div>
					</div>

					<div class="col-sm-5">
						<div class="panel panel-default" id="panelValutazioni">
							<div class="panel-heading">
								<span> <i class="fa fa-list-ol"></i> &nbsp;Valutazioni
								</span>
								<div class="btn-group pull-right">
									<button class="btn btn-xs btn-default " id="reloadValutazioni"
										data-toggle="tooltip" title="Refresh">
										<i class="fa fa-refresh"></i>
									</button>
									<button class="btn btn-xs btn-default " data-toggle="collapse"
										data-target="#bodyValutazioni" title="Riduci">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="panel-body text-center collapse" id="bodyValutazioni">

								<div class="ajaxLoader">
									<img class="img-responsive" alt="loading..."
										src="assets/ajax-loader.gif"
										style="padding-left: 50%; padding-top: 50px;">
								</div>

								<c:if test="${not empty valutazioni }">

									<div class="list-group dynamicList">

										<c:forEach var="valutazione" items="${valutazioni}">
											<button class="list-group-item list-group-item-action"
												id="${valutazione.codice}">
												<p>${valutazione.descrizione}</p>
												<strong>${valutazione.data}</strong>
												<p>
													Rating: &nbsp;<small class="ratingStar">${valutazione.rating}</small>
												</p>
												<p>Dal Cliente: &nbsp;${valutazione.mail_cliente}</p>
											</button>
										</c:forEach>

									</div>

								</c:if>
								<c:if test="${empty valutazioni }">
									<p class="empty">Nessun appuntameto creato oggi!</p>
								</c:if>


							</div>
						</div>
					</div>

				</div>
				<!-- /Row3 -->

			</div>
			<!-- /Container -->
		</div>
		<!-- /Main-Content -->
	</div>
	<!-- /Wrapper -->

	<!-- SuccessAppuntamento Popup -->
	<div class="alert alert-success alert-dismissable alert-fixed fade in"
		id="successAppuntamento" style="display: none;">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		L'Appuntamento è stato creato con successo!
	</div>

	<!-- Modal -->
	<div class="modal fade" id="modalAppuntamento" role="dialog">
		<div class="modal-dialog">

			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>




	<!-- FOOTER -->
	<jsp:include page="_footer.jsp"></jsp:include>
	<!-- /FOOTER -->
	<script type="text/javascript" src="js/area-personale-pro.js"></script>
	<script type="text/javascript" src="js/diagramma.js"></script>
</body>
</html>
