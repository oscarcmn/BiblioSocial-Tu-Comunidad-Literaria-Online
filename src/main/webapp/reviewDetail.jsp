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
	<main class="container mt-5">
		<h2 class="mb-4">Detalle de la Reseñas</h2>

		<!-- Tarjeta de la reseÃ±a -->
		<div class="card mb-4">
			<div class="card-body d-flex align-items-start">
				<div class="me-4">
					<div class="book-info" data-book-id="${review.bookGoogleId}"
						id="book-${review.id}"></div>
				</div>
				<div>
					<h5 class="card-title">@${review.user.username} reseña:</h5>
					<p class="card-text">${review.comment}</p>
				</div>
			</div>
		</div>

		<!-- Comentarios -->
		<div class="mb-5">
			<h4>Comentarios</h4>
			<c:forEach var="comment" items="${comments}">
				<div class="border rounded p-3 mb-3">
					<p class="mb-1">
						<strong>${comment.user.username}</strong> comenta:
					</p>
					<p class="mb-0">${comment.content}</p>
					<p class="text-muted small">${comment.commentedAt}</p>
				</div>
			</c:forEach>
		</div>

		<!-- Formulario para aÃ±adir un comentario -->
		<form action="Controller?operacion=addReviewComment" method="post"
			class="mb-5">
			<input type="hidden" name="reviewId" value="${review.id}">
			<div class="mb-3">
				<label for="content" class="form-label">Tu comentario:</label>
				<textarea class="form-control" name="content" id="content" rows="3"
					required></textarea>
			</div>
			<button type="submit" class="btn btn-primary">Comentar</button>
		</form>
	</main>
	<footer>
		<!-- place footer here -->
	</footer>
	<script type="module" src="js/reviewDetBook.js"></script>
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