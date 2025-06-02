<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<title>Detalle de la lista</title>
<!-- Required meta tags -->
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<!-- Bootstrap CSS v5.2.1 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
	<link rel="stylesheet" href="css/style.css">
</head>

<body>
<%@ include file="header.jsp" %>
	<main>
		<div class="container py-5">
			<h2 class="mb-4">
				Lista: <span class="text-primary">${nombreLista}</span>
			</h2>

			<div class="row">
				<c:forEach var="item" items="${books}">
					<div class="col-md-4 mb-4 book-card"
						data-volume-id="${item.id.volumeId}" data-list-id="${listaId}">
						<div class="card h-100 shadow-sm">
							<div class="card-body book-info text-center">
								<p class="text-muted">Cargando libro...</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</main>
	<%@ include file="footer.jsp" %>

	<script type="module" src="js/listDetails.js"></script>
	<!-- Bootstrap JavaScript Libraries -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
		integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
		crossorigin="anonymous"></script>
</body>

</html>