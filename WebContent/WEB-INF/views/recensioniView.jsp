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
				<img alt="user-photo" src="user-image/default.png"
					width="80" height="80">
				<p>${loginedPro.nome}</p>
				<a href="${pageContext.request.contextPath}/logout"><button
						class="btn btn-xs btn-logout" type="button">
						<i class="fa fa-power-off"></i>
					</button></a>
			</div>

			<ul class="list-unstyled components">
				<li><a href="${pageContext.request.contextPath}/areaPersonale"><i
						class="fa fa-home" aria-hidden="true"></i> Home</a></li>
				<li ><a
					href="${pageContext.request.contextPath}/storicoAppuntamenti"><i
						class="fa fa-calendar" aria-hidden="true"></i> Appuntamenti</a></li>
				<li><a href="${pageContext.request.contextPath}/clienti"><i
						class="fa fa-users" aria-hidden="true"></i> Clienti</a></li>
				<li class="active"><a
					href="${pageContext.request.contextPath}/recensioniProfessionista"><i
						class="fa fa-comments-o" aria-hidden="true"></i> Recensioni</a></li>
				<li><a
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
					<c:if test="${not empty appuntamentiDaEffettuare }">
						<h3>Appuntamenti non ancora effettuati:</h3>
						<div class="col-sm-6">
							<div class="table-responsive">
								<table class="table table-hover table-bordered text-center">
									<thead>
										<tr>
											<th class="text-center">Data</th>
											<th class="text-center">Ora Inizio</th>
											<th class="text-center">Ora Fine</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="appuntamento"
											items="${appuntamentiDaEffettuare}">


											<tr class="appuntamentoID" id="${appuntamento.codice}">

												<td class="text-center">${appuntamento.data }</td>
												<td class="text-center">${appuntamento.ora_inizio }</td>
												<td class="text-center">${appuntamento.ora_fine }</td>

											</tr>

										</c:forEach>
									</tbody>
								</table>
							</div>
							<c:if test="${not empty appuntamentiEffettuati }">
								<h3>Appuntamenti effettuati:</h3>
								<div class="table-responsive">
									<table class="table table-hover table-bordered text-center">
										<thead>
											<tr>
												<th class="text-center">Data</th>
												<th class="text-center">Ora Inizio</th>
												<th class="text-center">Ora Fine</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="appuntamentoEffettuato"
												items="${appuntamentiEffettuati}">

												<tr class="appuntamentoID effettuato"
													id="${appuntamentoEffettuato.codice}" >

													<td class="text-center">${appuntamentoEffettuato.data }</td>
													<td class="text-center">${appuntamentoEffettuato.ora_inizio }</td>
													<td class="text-center">${appuntamentoEffettuato.ora_fine }</td>

												</tr>

											</c:forEach>
										</tbody>
									</table>
								</div>
								</c:if>
						</div>

					</c:if>
					<div class="col-sm-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								Appuntamento&ensp;&ensp;&ensp;&ensp;<i class="fa fa-edit"
									id="editAppuntamento"></i>&ensp;<i class="fa fa-trash"
									id="removeAppuntamento"></i>
							</div>
							<div class="panel-body">
								<ul class="list-group" id="appuntamentoVisibile">
									<li class="list-group-item">Appuntamento selezionato: <span
										id="codiceAppuntamento"></span></li>
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
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" class="btn btn-default">Modifica</button>
										</div>
									</div>
								</form>
							</div>

							<div class="panel-footer text-center">
								<p id="errorMessage" class=" animated" style="color: red;">Inserisci
									tutti i campi!</p>
							</div>
						</div>
					</div>
				</div>

				<c:if
					test="${empty appuntamentiEffettuati and empty appuntamentiDaEffettuare}">
					<h3 class="text-center">Nessun appuntamento disponibile</h3>
				</c:if>
			</div>
		</div>
	</div>



	<!-- FOOTER -->
	<jsp:include page="_footer.jsp"></jsp:include>
	<!-- /FOOTER -->
	<script type="text/javascript" src="js/appuntamentoSelezionato.js"></script>

</body>
</html>
