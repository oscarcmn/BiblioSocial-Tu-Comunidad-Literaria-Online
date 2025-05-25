<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<title>Title</title>
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
</head>

<body>
	<header>
		<!-- place navbar here -->
	</header>
	<main>
		<div class="container mt-5">
			<h2 class="mb-4 text-center">Buscar Usuario por Email</h2>

			<!-- Formulario de búsqueda -->
			<form action="Controller" method="get" class="mb-5">
				<input type="hidden" name="operacion" value="searchUserByEmail" />
				<div class="row g-2 justify-content-center">
					<div class="col-md-6">
						<input type="email" class="form-control" name="email"
							placeholder="Introduce un email" required>
					</div>
					<div class="col-md-2">
						<button type="submit" class="btn btn-primary w-100">Buscar</button>
					</div>
				</div>
			</form>

			<!-- Usuario encontrado -->
			<c:if test="${not empty foundUser}">
				<div class="card text-center mx-auto" style="max-width: 400px;">
					<div class="card-body">
						<h5 class="card-title">${foundUser.username}</h5>
						<p class="card-text">
							<strong>Email:</strong> ${foundUser.email}
						</p>

						<form action="Controller" method="get">
							<input type="hidden" name="operacion" value="followUser" /> <input
								type="hidden" name="followedId" value="${foundUser.id}" />
							<button type="submit" class="btn btn-success">Seguir</button>
						</form>
						<br>
						<form action="Controller" method="get" style="display: inline;">
							<input type="hidden" name="operacion" value="showUserProfile" />
							<input type="hidden" name="userId" value="${foundUser.id}" />
							<button type="submit" class="btn btn-info">Ver Perfil</button>
						</form>
					</div>
				</div>
			</c:if>

			<!-- Usuario no encontrado -->
			<c:if test="${param.email != null && empty foundUser}">
				<div class="alert alert-warning text-center mt-4" role="alert">
					No se encontró ningún usuario con ese email.</div>
			</c:if>
		</div>
	</main>
	<footer>
		<!-- place footer here -->
	</footer>
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