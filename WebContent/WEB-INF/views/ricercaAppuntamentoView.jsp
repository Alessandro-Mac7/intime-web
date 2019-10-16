<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=chiave&callback=initMap"></script>
<script type="text/javascript" src="js/geolocationAddress.js"></script>
<title>Ricerca</title>
</head>
<body>

	<!-- HEADER -->
	<jsp:include page="_header.jsp"></jsp:include>
	<!-- /HEADER -->

	<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
		aria-labelledby="modalTitleCenter" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modalTitle">Conferma Prenotazione</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">Vuoi prenotare l'appuntamento del
					Sig.&ensp;${pro.cognome}?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" id="confirmPrenota">SI</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">NO</button>
				</div>
			</div>
		</div>
	</div>


	<div class="container fix-padding">

		<!-- Successo Prenotazione Popup -->
		<div class="alert alert-success alert-dismissable alert-fixed fade in"
			id="successPrenotazione">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Successo!</strong> L'Appuntamento è stato prenotato!
		</div>


		<div class="row">
			<div class="col-sm-12">
				<div class="well">
					<h3>${pro.nome}&ensp;${pro.cognome}</h3>
					<p>${pro.professione}</p>
				</div>
			</div>
		</div>

		<div class="row">

			<div class="col-sm-3">
				<div class="panel panel-default">
					<div class="panel-heading">
						Sig. ${pro.cognome} &ensp; &ensp; &ensp;<img alt="user-photo"
							src="user-image/default.png" width="80" height="80">
					</div>
					<div class="panel-body">
						<div class="list-group" id="list-tab" role="tablist">
							<a class="list-group-item list-group-item-action active"
								data-toggle="list" href="#prenotaOnline" role="tab"><i
								class="fa fa-calendar"></i>&ensp;Prenota Appuntamento</a> <a
								class="list-group-item list-group-item-action"
								data-toggle="list" href="#informazioniPro" role="tab"><i
								class="fa fa-info-circle"></i>&ensp;Informazioni</a>
						</div>
					</div>
				</div>
			</div>

			<div class="col-sm-6">

				<div class="tab-content">
					<div class="tab-pane active" id="prenotaOnline" role="tabpanel">
						<c:if test="${not empty appuntamenti }">

							<div class="panel panel-default">
								<div class="panel-heading">Appuntamenti disponibili</div>
								<div class="panel-body">
									<h4>Seleziona un appuntamento per effettuare una
										prenotazione</h4>
									<p>Inserisci un campo per filtrare gli appuntamenti da
										visualizzare:</p>
									<input class="form-control" id="inputData" type="text"
										placeholder="Cerca..."> <br>
									<div class="table-responsive">
										<table id="appuntamentiTable"
											class="table table-hover table-bordered text-center">
											<thead>
												<tr>
													<th class="text-center">Data</th>
													<th class="text-center">Ora Inizio</th>
													<th class="text-center">Ora Fine</th>
													<th class="text-center">Descrizione</th>
													<th class="text-center">Limite Clienti</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="appuntamento" items="${appuntamenti}">
													<tr class="appuntamentoID" id="${appuntamento.codice }">
														<td>${appuntamento.data }</td>
														<td>${appuntamento.ora_inizio }</td>
														<td>${appuntamento.ora_fine }</td>
														<td>${appuntamento.descrizione }</td>
														<td>${appuntamento.numero_clienti }</td>

													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</c:if>


						<c:if test="${empty appuntamenti and empty appuntamentiPrenotati}">
							<div align="center">
								<h3>Nessun appuntamento disponibile</h3>
							</div>
						</c:if>
					</div>

					<div class="tab-pane fade" id="informazioniPro" role="tabpanel">
						<div class="panel panel-default">
							<div class="panel-heading">Descrizione</div>
							<div class="panel-body">${pro.descrizione}</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-sm-3">
				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="fa fa-address-card"></i>&ensp;Contatti
					</div>
					<div class="panel-body">
						<p>
							<i class="fa fa-phone"></i>&ensp;Telefono
						</p>
						<p>
							<a href="tel: ${pro.telefono}">${pro.telefono}</a>
						</p>
						<hr>
						<p>
							<i class="fa fa-globe"></i>&ensp;Sito Web
						</p>
						<p>
							<a href="https://${pro.sito}/">${pro.sito}</a>
						</p>
						<hr>
						<p>
							<i class="fa fa-map"></i>&ensp;Indirizzo
						</p>
						<p>${pro.indirizzo}</p>
						<div id="minimappa">${pro.indirizzo}</div>

					</div>
				</div>

			</div>
		</div>
	</div>
		<div id="ajaxLoader"></div>

	<!-- FOOTER -->
	<jsp:include page="_footer.jsp"></jsp:include>
	<!-- /FOOTER -->
	

	<script type="text/javascript" src="js/prenotaAppuntamento.js"></script>
	<script src="js/geolocationAddress.js"></script>
</body>
</html>