<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link href='https://fonts.googleapis.com/css?family=Capriola'
	rel='stylesheet'>
<link rel="stylesheet" href="css/in-time.css">



</head>
<body>

	<div class="container" style="margin-top: 100px">
		<div class="col-sm-4 col-sm-offset-4">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3>Entra nell'Area Personale.</h3>
					<p> Non possiedi un account? <a
							href="iscrivitiView.html">CLICCA QUI</a> per effettuare la
						registrazione!
					</p>
				</div>
				<!-- Body del Panel -->
				<div class="panel-body">
					<!-- Form Login -->
					<div id="entra" class="tab-pane fade in active text-center">
						<form id="form-login" class="form-horizontal"
							action="LoginServlet" method="post">
							<div class="form-group">
								<div class="col-sm-12">
									<input type="email" class="form-control" name="mail"
										value="${utente.mail}" placeholder="Inserisci Email">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<input type="password" class="form-control" name="password"
										value="${utente.password}" placeholder="Inserisci Password">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<label for="sel1">Accedi come:</label> <select
										class="form-control" id="sel1" name="selection">
										<option>Professionista</option>
										<option>Cliente</option>
									</select>
								</div>
							</div>
							<label class="error">${errorMessage}</label>
							<div class="form-group">
								<div class="col-sm-6 col-sm-offset-3">
									<div class="checkbox">
										<label> <input type="checkbox" name="ricordami"
											value="Y"> Ricordami
										</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-4">
									<button type="submit" class="btn btn-default">Login</button>
								</div>
							</div>
						</form>

					</div>
					<!-- /Form Login -->

				</div>
				<!-- /Body del Panel -->
			</div>
		</div>
	</div>

	<script type="text/javascript" src="js/lib/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/lib/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/lib/jquery.validate.min.js"></script>
	<script src="js/validaLogin.js"></script>

</body>
</html>