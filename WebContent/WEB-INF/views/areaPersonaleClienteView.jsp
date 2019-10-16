<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.16/datatables.min.css" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>

<title>inTime</title>
</head>
<body>

	<!-- HEADER -->
	<jsp:include page="_header.jsp"></jsp:include>
	<!-- /HEADER -->



	<div class="container">
		<div class="row text-center">
			<div class="col-sm-12">
				<p class="bigFont col-sm-12" style="padding-top: 20px">${loginedClient.nome}&nbsp;${loginedClient.cognome}</p>
			</div>
		</div>
		<hr>
		<div class="row">
			<!-- Pannello Appuntamenti -->
			<div class="col-md-6 col-sm-6">
				<div class="panel panel-red">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-calendar fa-2x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div class="bigFont">${numPrenotazioni}</div>
								<p class="lead">Prenotazioni</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /Pannello Appuntamenti -->
			<!-- Pannello Valutazioni -->
			<div class="col-md-6 col-sm-6">
				<div class="panel panel-green">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-3">
								<i class="fa fa-comments fa-2x"></i>
							</div>
							<div class="col-xs-9 text-right">
								<div class="bigFont">${numValutazioni}</div>
								<p class="lead">Valutazioni</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /Pannello Valutazioni -->
		</div>


		<!-- /Row -->
		<div class="row">
			<div class="col-sm-12">
				<div class="panel panel-default" id="panelOdierni">
					<div class="panel-heading">
						<span> <i class="fa fa-suitcase"></i>&nbsp;&nbsp;Prenotazioni
							a cui partecipare oggi!
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
					<div class="panel-body text-center collapse" id="bodyOdierni"
						style="max-height: 300px">

						<div class="ajaxLoader">
							<img class="img-responsive" alt="loading..."
								src="assets/ajax-loader.gif"
								style="padding-left: 50%; padding-top: 50px;">
						</div>

						<c:if test="${not empty prenotazioniOdierne }">
							<div id="carouselOdierni" class="carousel slide"
								data-ride="carousel">
								<div class="carousel-inner dynamicItem">


									<c:forEach var="prenotazioneOdierna"
										items="${prenotazioniOdierne}">
										<div class="item ">
											<div class="col-sm-offset-3 col-sm-6">

												<div class="well well-lg">
													<p>${prenotazioneOdierna.descrizione}</p>
													<hr>
													<strong>${prenotazioneOdierna.data}</strong>
													<hr>
													<p>Dalle&nbsp;${prenotazioneOdierna.ora_inizio}&nbsp;a&nbsp;
														${prenotazioneOdierna.ora_fine}</p>
													<hr>
													<button class="btn btn-default open-modal-button"
														id="${prenotazioneOdierna.codice}">Info</button>
												</div>
											</div>
										</div>
									</c:forEach>

								</div>
								<a data-slide="prev" href="#carouselOdierni"
									class="left carousel-control"><i class="fa fa-chevron-left"></i></a>
								<a data-slide="next" href="#carouselOdierni"
									class="right carousel-control"><i
									class="fa fa-chevron-right"></i></a>
							</div>

						</c:if>

						<c:if test="${empty prenotazioniOdierne }">
							<p class="empty">Non sei prenotato a nessun appuntamento
								oggi!</p>
						</c:if>
					</div>
				</div>
			</div>
		</div>


		<!-- Row2 -->
		<div class="row">

			<div class="col-sm-6">
				<div class="panel panel-default" id="panelPrenotazioniEffettuate">
					<div class="panel-heading">
						<span> <i class="fa fa-calendar"></i>&nbsp;&nbsp;Prenotazioni
							attive
						</span>
						<div class="btn-group pull-right">
							<button class="btn btn-xs btn-default " data-toggle="collapse"
								data-target="#bodyPrenotazioniEffettuate" data-toggle="tooltip"
								title="Riduci">
								<i class="fa fa-minus"></i>
							</button>
						</div>


					</div>
					<div class="panel-body text-center collapse"
						id="bodyPrenotazioniEffettuate" style="max-height:350px; overflow-y:scroll">
						<div class="ajaxLoader">
							<img class="img-responsive" alt="loading..."
								src="assets/ajax-loader.gif"
								style="padding-left: 50%; padding-top: 50px;">
						</div>
						<c:if test="${not empty appuntamentiPrenotati }">

							<div class="list-group dynamicList">

								<c:forEach var="appuntamentoPrenotato"
									items="${appuntamentiPrenotati}">
									<button
										class="list-group-item list-group-item-action open-modal-button"
										id="${appuntamentoPrenotato.codice}">
										<p>${appuntamentoPrenotato.descrizione}</p>
										<strong>${appuntamentoPrenotato.data}</strong>
										<p>
											Dalle&nbsp;${appuntamentoPrenotato.ora_inizio}&nbsp;a&nbsp;
											${appuntamentoPrenotato.ora_fine}</p>
									</button>
								</c:forEach>

							</div>
						</c:if>

						<c:if test="${empty appuntamentiPrenotati }">
							<p class="empty">Non sei prenotato a nessun Appuntamento!</p>
						</c:if>
					</div>
				</div>
			</div>

			<div class="col-sm-6">
				<div class="panel panel-default" id="panelChart">
					<div class="panel-heading">
						<span> <i class="fa fa-calendar"></i>&nbsp;&nbsp;Professionisti
							più prenotati
						</span>
						<div class="btn-group pull-right">
							<button class="btn btn-xs btn-default " data-toggle="collapse"
								data-target="#bodyChart" data-toggle="tooltip" title="Riduci">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="panel-body collapse" id="bodyChart">
						<canvas id="doughnutChart"></canvas>
					</div>
				</div>
			</div>
		</div>

		<!-- /Row2 -->

		<hr>

		<!-- Row3 -->
		<div class="row">

			<div class="col-sm-6">

				<div class="panel panel-default" id="panelDaValutare">
					<div class="panel-heading">
						<span> <i class="fa fa-suitcase"></i>&nbsp;&nbsp;Appuntamenti
							da valutare
						</span>
						<div class="btn-group pull-right">
							<button class="btn btn-xs btn-default " data-toggle="collapse"
								data-target="#bodyDaValutare" title="Riduci">
								<i class="fa fa-minus"></i>
							</button>
						</div>
						<p class="small">Lascia una valutazione agli appuntamenti a
							cui hai partecipato!</p>
					</div>
					<div class="panel-body text-center collapse" id="bodyDaValutare" style="max-height:300px; overflow-y:scroll">

						<div class="ajaxLoader">
							<img class="img-responsive" alt="loading..."
								src="assets/ajax-loader.gif"
								style="padding-left: 50%; padding-top: 50px;">
						</div>

						<c:if test="${not empty appuntamentiDaValutare }">

							<div class="list-group dynamicList">

								<c:forEach var="appuntamentoValutabile"
									items="${appuntamentiDaValutare}">
									<button
										class="list-group-item list-group-item-action open-modal-button-insert"
										id="${appuntamentoValutabile.codice}">
										<p>${appuntamentoValutabile.descrizione}</p>
										<strong>${appuntamentoValutabile.data}</strong>
										<p>
											Dalle&nbsp;${appuntamentoValutabile.ora_inizio}&nbsp;a&nbsp;
											${appuntamentoValutabile.ora_fine}</p>
									</button>
								</c:forEach>

							</div>
						</c:if>

						<c:if test="${empty appuntamentiDaValutare }">
							<p class="empty">Nessun appuntamento da valutare</p>
						</c:if>
					</div>
				</div>
			</div>

			<div class="col-sm-6">

				<div class="panel panel-default" id="panelValutazioni">
					<div class="panel-heading">
						<span> <i class="fa fa-comment"></i>&nbsp;&nbsp;Recensioni
						</span>
						<div class="btn-group pull-right">
							<button class="btn btn-xs btn-default " data-toggle="collapse"
								data-target="#bodyValutazioni" title="Riduci">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="panel-body text-center collapse" id="bodyValutazioni">

						<c:if test="${not empty storicoValutazioni }">
							<div id="carouselShow">
								<div class="carousel slide" data-ride="carousel"
									id="quote-carousel">

									<div class="carousel-inner text-center">


										<c:forEach var="valutazione" items="${storicoValutazioni}">
											<div class="item">
												<blockquote>
													<div class="row">
														<div class="col-sm-8 col-sm-offset-2">
															<p>${valutazione.descrizione}</p>
															<small>${valutazione.data}</small> <small
																class="ratingStar">${valutazione.rating}</small>

														</div>
													</div>
												</blockquote>
											</div>
										</c:forEach>

									</div>

								</div>
								<!-- Carousel Buttons Next/Prev -->
								<a data-slide="prev" href="#quote-carousel"
									class="left carousel-control"><i class="fa fa-chevron-left"></i></a>
								<a data-slide="next" href="#quote-carousel"
									class="right carousel-control"><i
									class="fa fa-chevron-right"></i></a>
							</div>
							
						</c:if>



						<c:if test="${empty storicoValutazioni }">
							<p class="empty">Non hai valutato nessun appuntamento!</p>
						</c:if>

					</div>

				</div>


			</div>
		</div>
		<!-- /Container -->
	</div>

	<!-- Modal -->
	<div class="modal fade" id="genericModal" role="dialog">
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

	<div class="modal fade" id="modalValutazione" role="dialog">
		<div class="modal-dialog">

			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h4 class="modal-title">Inserisci una valutazione</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" id="nuovaValutazioneForm">
						<div class="form-group">
							<label class="control-label col-sm-2" for="rating">Rating:</label>
							<div class="col-sm-5">
								<select id="ratingStars" name="rating">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5" selected="selected">5</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<textarea rows="3" class="form-control" name="descrizione"
									placeholder="Inserisci una descrizione..."></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Valuta!</button>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer text-center">
					<p id="errorMessage" class=" animated" style="color: red;">Inserisci
						tutti i campi!</p>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
		aria-labelledby="modalTitleCenter" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalTitle">Conferma Eliminazione</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Sei sicuro di voler rimuovere la
					prenatazione?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" id="confirmDelete">SI</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">NO</button>
				</div>
			</div>
		</div>
	</div>

	<!-- SuccessAppuntamento Popup -->
	<div class="alert alert-success alert-dismissable alert-fixed fade in"
		id="successDeletePrenotazione" style="display: none;">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>Successo!</strong> La prenotazione è stata eliminata!
	</div>

	<!-- Successo Valutazione Popup -->
	<div class="alert alert-success alert-dismissable alert-fixed fade in"
		id="successValutazione" style="display: none;">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>Successo!</strong> La valutazione è stata registrata!
	</div>



	<!-- FOOTER -->
	<jsp:include page="_footer.jsp"></jsp:include>
	<!-- /FOOTER -->
	<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.16/datatables.min.js"></script>
	<script src="js/area-personale-cliente.js"></script>
	<script src="js/statistiche.js"></script>


	<div id="ajaxLoader"></div>

</body>
</html>