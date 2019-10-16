<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/rotating-card.css" rel="stylesheet" />
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=chiave"></script>
<title>Ricerca</title>
</head>
<body>

	<!-- HEADER -->
	<jsp:include page="_header.jsp"></jsp:include>
	<!-- /HEADER -->

	<div class="container fix-padding">
<!-- 
		<div class="row">
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<i class="fa fa-search"></i> Parametri di ricerca
					</div>
					<div class="panel-body">

						<form>
							<div class="form-group">
								<label for="professione">Professione:</label> <select
									class="form-control" id="professione">
									<optgroup label="Amministrazione e Finanza">
										<option>Addetto alla Contabilità Generale</option>
										<option>Analista Finanziario d'Impresa</option>
										<option>Credit Manager</option>
										<option>Dottore Commercialista</option>
										<option>Giurista d'Impresa</option>
										<option>Responsabile Contabilità Generale ed
											industriale</option>
										<option>Responsabile del bilancio</option>
										<option>Revisore del Bilancio</option>
										<option>Tributarista</option>
									</optgroup>
									<optgroup label="Commerciale e Marketing">
										<option>Agente di Commercio</option>
										<option>Call Center Manager</option>
										<option>Direttore Commerciale</option>
										<option>Direttore Marketing Strategico</option>
										<option>Esperto Mercati Esteri</option>
									</optgroup>
									<optgroup label="Informatica e Tecnologia">
										<option>Amministratore di rete</option>
										<option>Analista Programmatore</option>
										<option>Analista di Sistema</option>
										<option>Database Administrator</option>
										<option>Programmatore Informatico</option>
										<option>SEO Copywriter</option>
										<option>Tecnico Informatico</option>
										<option>Web Developer</option>
									</optgroup>
									<optgroup label="Risorse Umane">
										<option>Amministratore del Personale</option>
										<option>Cake Designer</option>
										<option>Diet Coach</option>
										<option>Consulente del Lavoro</option>
										<option>Estetista Professionista</option>
										<option>Gestore di Catering</option>
										<option>Organizzatore di eventi</option>
										<option>Wedding Planner</option>
									</optgroup>
									<optgroup label="Altro">
										<option>Altro</option>
									</optgroup>

								</select>
							</div>

							<div class="checkbox">
								<label><input type="checkbox"> Vicino a te</label>
							</div>
							<button type="button" class="btn btn-default" id="formFilter">Applica
								Filtri</button>
						</form>

					</div>
				</div>
			</div>
  -->
			<div class="col-md-12">
				<c:if test="${not empty professionisti }">
				
					<c:forEach var="professionista" items="${professionisti}">
						<div class="col-sm-4">
							<div class="card">
								<div class="c-header">
									<i class="fa fa-user-circle-o fa-4x" aria-hidden="true"></i>
									<h3>${professionista.cognome}&ensp;${professionista.nome }</h3>
									<p>${professionista.professione}</p>
								</div>
								<div class="c-body">
									<a
										href="${pageContext.request.contextPath}/ricercaAppuntamento?id=${professionista.mail}">
										Cerca appuntamenti</a>

								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>

				<c:if test="${empty professionisti}">
					<div align="center">
						<h3>Nessun risultato trovato</h3>
					</div>
				</c:if>
			</div>
		</div>





		<!-- 
		<script type="text/javascript">
		window.setInterval(function() {
		    var elmnt = document.getElementById("notizie");
		    elmnt.scrollTop += 30;
		},2000);
		</script>

		<div class="col-md-12">
			<div class="panel panel-default">
				<div id="not" class="panel-heading">
					<iframe id="notizie"
						src="//rss.bloople.net/?url=https%3A%2F%2Fnews.google.com%2Fnews%2Frss%2Fheadlines%2Fsection%2Ftopic%2FBUSINESS.it_it%2FEconomia%3Fned%3Dit%26hl%3Dit%26gl%3DIT&showtitle=false&forceutf8=true&type=html">
					</iframe>
				</div>
			</div>
		</div>

-->
	</div>

	<!-- FOOTER -->
	<jsp:include page="_footer.jsp"></jsp:include>
	<!-- /FOOTER -->
	<script src="js/ricerca.js"></script>
	<!-- <script src="js/vicinoTe.js"></script> -->
</body>
</html>