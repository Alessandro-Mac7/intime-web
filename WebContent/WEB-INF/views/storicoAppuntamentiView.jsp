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
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.16/datatables.min.css" />
<link rel="stylesheet" href="css/in-time.css">

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
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
				<li class="active"><a
					href="${pageContext.request.contextPath}/storicoAppuntamenti"><i
						class="fa fa-calendar" aria-hidden="true"></i> Appuntamenti</a></li>
				<li><a
					href="${pageContext.request.contextPath}/clienti"><i
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
				</div>

				<div class="row" style="margin-top: 20px">

					<div class="panel panel-default" id="panelTable"
						>
						<div class="panel-heading">
							<span> <i class="fa fa-calendar"></i> &nbsp;Appuntamenti
								da effettuare
							</span>
						</div>
						<div class="panel-body">
							<table id="tableNonEffettuati" class="display">
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
									<c:forEach var="appuntamento"
										items="${appuntamentiDaEffettuare}">


										<tr class="appuntamentoID" id="${appuntamento.codice}">

											<td class="text-center">${appuntamento.data }</td>
											<td class="text-center">${appuntamento.ora_inizio }</td>
											<td class="text-center">${appuntamento.ora_fine }</td>
											<td class="text-center">${appuntamento.descrizione }</td>
											<td class="text-center">${appuntamento.numero_clienti }</td>

										</tr>

									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>



				</div>

				<div class="row">

					<div class="panel panel-default" id="panelTable">
						<div class="panel-heading">
							<span> <i class="fa fa-calendar-check-o"></i> &nbsp;Appuntamenti
								effettuati
							</span>
						</div>
						<div class="panel-body">
							<table id="tableEffettuati" class="display">
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
									<c:forEach var="appuntamentoEffettuato"
										items="${appuntamentiEffettuati}">

										<tr class="appuntamentoID effettuato"
											id="${appuntamentoEffettuato.codice}">

											<td class="text-center">${appuntamentoEffettuato.data }</td>
											<td class="text-center">${appuntamentoEffettuato.ora_inizio }</td>
											<td class="text-center">${appuntamentoEffettuato.ora_fine }</td>
											<td class="text-center">${appuntamentoEffettuato.descrizione }</td>
											<td class="text-center">${appuntamentoEffettuato.numero_clienti }</td>

										</tr>

									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>


				</div>


				<!-- Modal -->
				<div class="modal fade" id="modalAppuntamento" role="dialog">
					<div class="modal-dialog">

						<div class="modal-content">
							<div class="modal-header">
								<div class="btn-group pull-right">
									<button class="btn btn-xs btn-default " id="editAppuntamento"
										data-toggle="tooltip" title="Modifica">
										<i class="fa fa-edit"></i>
									</button>
									<button class="btn btn-xs btn-default " id="removeAppuntamento"
										data-toggle="tooltip" title="Cancella">
										<i class="fa fa-trash"></i>
									</button>
									<button type="button" class="btn btn-xs btn-default"
										data-dismiss="modal" data-toggle="tooltip" title="Chiudi">
										<i class="fa fa-times" aria-hidden="true"></i>
									</button>
								</div>

								<h4 class="modal-title">
									Appuntamento selezionato: <span id="codiceAppuntamento"></span>
								</h4>
							</div>
							<div class="modal-body">

								<ul class="list-group" id="appuntamentoVisibile">
									<li class="list-group-item">Data: <span
										id="dataAppuntamento"></span></li>
									<li class="list-group-item">Ora Inizio: <span
										id="oraAppuntamento"></span></li>
									<li class="list-group-item">Ora Fine: <span
										id="oraFineAppuntamento"></span></li>
									<li class="list-group-item">Descrizione: <span
										id="descrizioneAppuntamento"></span></li>
									<li class="list-group-item">Clienti: <span
										id="clientiAppuntamento"></span></li>
									<li class="list-group-item">Prenotazioni: <span
										id="prenotazioniAppuntamento"></span></li>
								</ul>


								<form class="form-horizontal" id="editFormAppuntamento">
									<div class="form-group">
										<label class="control-label col-sm-2" for="data">Data:</label>
										<div class="col-sm-10">
											<input type="date" class="form-control" name="data">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="ora">Inizio:</label>
										<div class="col-sm-10">
											<input type="time" class="form-control" name="ora">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="oraFine">Fine:</label>
										<div class="col-sm-10">
											<input type="time" class="form-control" name="oraFine">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="descrizione">Descrizione:</label>
										<div class="col-sm-10">
											<textarea rows="3" class="form-control" name="descrizione"
												placeholder="Inserisci una descrizione..."></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2" for="clienti">Clienti:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="clienti">
										</div>
									</div>
									<p id="errorMessage" class="small animated"
										style="color: tomato;">Inserisci tutti i campi!</p>

									<div class="form-group">
										<div class="col-sm-offset-4 col-sm-8">
											<button type="submit" class="btn btn-default">Modifica</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>



				<div class="modal-footer"></div>
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
				<div class="modal-body">Sei sicuro di voler rimuovere
					l'appuntamento?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" id="confirmDelete"
						data-dismiss="modal">SI</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">NO</button>
				</div>
			</div>
		</div>
	</div>

	<!-- SuccessAppuntamento Popup -->
	<div class="alert alert-success alert-dismissable alert-fixed fade in"
		id="successAppuntamento" style="display: none;">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<strong>Successo!</strong> L'Appuntamento è stato modificato con i
		nuovi campi inseriti!
	</div>

	<!-- Warning Popup -->
	<div class="alert alert-warning alert-dismissable alert-fixed fade in"
		id="warningAppuntamento" style="display: none;">
		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		<i class='fa fa-exclamation-triangle'></i><strong>
			Attenzione! I seguenti dati non verranno modificati:</strong>
		<p id="warningMessage" class="animated" style="color: orange;"></p>
	</div>

	<div id="ajaxLoader"></div>




	<!-- FOOTER -->
	<jsp:include page="_footer.jsp"></jsp:include>
	<!-- /FOOTER -->
	<script type="text/javascript"
		src="https://cdn.datatables.net/v/dt/dt-1.10.16/datatables.min.js"></script>
	<script type="text/javascript" src="js/appuntamenti-pro.js"></script>
	<script type="text/javascript" src="js/topClienti.js"></script>
</body>
</html>