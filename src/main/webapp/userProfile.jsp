<%@page import="java.util.List" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">

<head>
    <title>Perfil</title>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <!-- Bootstrap CSS v5.2.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
        <link rel="stylesheet" href="css/style.css">
</head>

<body>
    <%@ include file="header.jsp" %>
    <main>
        <div class="container py-5">
            <div class="row mb-4">
                <div class="col-md-8">
                    <h2>Perfil del Usuario</h2>
                    <div class="card mb-4">
                        <div class="card-body">
                            <p><strong>Correo electrónico:</strong> ${user.email}</p>
                            <p><strong>Nombre de usuario:</strong> ${user.username}</p>
                            <p><strong>Seguidos:</strong> ${followingCount}</p>
                            <p><strong>Seguidores:</strong> ${followersCount}</p>
                        </div>
                    </div>

                    <div class="row">
						<div class="col-md-8">
							<h3>Listas</h3>
							<div class="accordion" id="acordeonListas">
								<c:forEach var="lista" items="${listasLibros}" varStatus="status">
									<div class="card mb-3">
										<div class="card-body d-flex justify-content-between align-items-center">
											<span class="fs-5">${lista.name}</span> <a
												href="Controller?operacion=showListDetails&listaId=${lista.id}"
												class="btn btn-outline-primary btn-sm">Ver contenido</a>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
                </div>
            </div>
        </div>
    </main>
    <footer>
        <!-- place footer here -->
    </footer>
    <!-- Bootstrap JavaScript Libraries -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
</body>

</html>