<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="UTF-8">
<title>Listas de Libros</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body>

	<div class="container py-5">
		<div class="row mb-4">
			<div class="col-md-6">
				<h2>Crear Nueva Lista</h2>
				<form action="Controller?operacion=createNewList" method="post"
					class="d-flex gap-2">
					<input type="text" name="nombreLista" class="form-control"
						placeholder="Nombre de la lista" required>
					<button type="submit" class="btn btn-primary">Crear Lista</button>
				</form>
			</div>
		</div>

		<div class="row">
			<div class="col-md-8">
				<h3>Mis Listas</h3>
				<div class="accordion" id="acordeonListas">
					<c:forEach var="lista" items="${listasLibros}" varStatus="status">
						<div class="card mb-3">
							<div
								class="card-body d-flex justify-content-between align-items-center">
								<span class="fs-5">${lista.name}</span> <a
									href="VerListaServlet?listaId=${lista.id}"
									class="btn btn-outline-primary btn-sm">Ver contenido</a> <a
									href="Controller?operacion=deleteList&listaId=${lista.id}"
									class="btn btn-outline-danger btn-sm"
									onclick="return confirm('¿Estás seguro de que quieres eliminar esta lista?')">Eliminar</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>