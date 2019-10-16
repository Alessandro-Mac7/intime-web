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
				<li><a
					href="${pageContext.request.contextPath}/storicoAppuntamenti"><i
						class="fa fa-calendar" aria-hidden="true"></i> Appuntamenti</a></li>

				<li class="active"><a
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
					<div class="col-md-8">

						<div class="panel panel-default" id="panelTable"
							style="min-height: 450px;">
							<div class="panel-heading">
								<span> <i class="fa fa-users"></i> &nbsp;I miei
									Clienti
								</span>
							</div>
							<div class="panel-body">
								<table id="clientiTable" class="display">
									<thead>
										<tr>
											<th class="text-center">Nome</th>
											<th class="text-center">Cognome</th>
											<th class="text-center">Telefono</th>
											<th class="text-center">Email</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="cliente" items="${clienti}">


											<tr class="clienteID" id="${cliente.mail}">

												<td class="text-center">${cliente.nome }</td>
												<td class="text-center">${cliente.cognome }</td>
												<td class="text-center">${cliente.telefono }</td>
												<td class="text-center">${cliente.mail }</td>

											</tr>

										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="panel panel-default" id="panelChart"
							style="min-height: 450px;">
							<div class="panel-heading">
								<span> <i class="fa fa-pie-chart"></i> &nbsp;Top Clienti
								</span>
								<div class="btn-group pull-right">
									<button class="btn btn-xs btn-default " data-toggle="collapse"
										data-target="#myChart-wrap" title="Riduci">
										<i class="fa fa-minus"></i>
									</button>
								</div>
							</div>
							<div class="chart-wrap">
								<canvas id="topClienti"></canvas>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="modalAppuntamento" role="dialog">
		<div class="modal-dialog" style="overflow-y: initial !important">

			<div class="modal-content">
				<div class="modal-header">
					<div class="btn-group pull-right">
						<button type="button" class="btn btn-xs btn-default"
							data-dismiss="modal" data-toggle="tooltip" title="Chiudi">
							<i class="fa fa-times" aria-hidden="true"></i>
						</button>
					</div>

					<div class="modal-title">
						<ul class="nav nav-tabs nav-justified">
							<li class="active"><a data-toggle="tab"
								href="#prenotazioniAppuntamento">Prenotazioni</a></li>
							<li><a data-toggle="tab" href="#valutazioniAppuntamento">Valutazioni</a></li>
						</ul>
					</div>
				</div>
				<div class="modal-body" style="overflow-y: auto; max-heigth: 250px;">

					<!-- Elementi del tab -->
					<div class="tab-content">
						<div id="prenotazioniAppuntamento"
							class="tab-pane fade in active text-center">

							<ul class="list-group dynamicList">

							</ul>
						</div>

						<div id="valutazioniAppuntamento"
							class="tab-pane fade in text-center">
							<ul class="list-group dynamicList">
							</ul>
						</div>


					</div>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>

	<div id="ajaxLoader"></div>

	<!-- FOOTER -->
	<jsp:include page="_footer.jsp"></jsp:include>
	<!-- /FOOTER -->
	<script type="text/javascript"
		src="https://cdn.datatables.net/v/dt/dt-1.10.16/datatables.min.js"></script>
	<script type="text/javascript" src="js/clienti-pro.js"></script>
	<script type="text/javascript" src="js/topClienti.js"></script>
</body>
</html>