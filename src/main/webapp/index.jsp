<%@page import="java.util.List" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <!doctype html>
            <html lang="en">

            <head>
                <title>Inicio</title>
                <!-- Required meta tags -->
                <meta charset="utf-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

                <!-- Bootstrap CSS v5.2.1 -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
                    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
                    crossorigin="anonymous" />
                <link rel="stylesheet" href="css/style.css">
                <link rel="stylesheet"
                    href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
            </head>

            <body>
                <header>
                    <nav class="navbar navbar-expand-sm navbar-light header-bg">
                        <div class="container">
                            <a class="navbar-brand" href="index.jsp"><img src="img/logobibliosocial.jpeg" alt=""
                                    width="170px"></a>
                            <a class="navbar-brand" href="Controller?operacion=feed">Reseñas</a>
                            <a class="navbar-brand" href="searchUsers.jsp">Usuarios</a>
                            <a class="navbar-brand" href="Controller?operacion=showLists">Listas</a>
                            <a class="navbar-brand" href="Controller?operacion=showProfile">Perfil</a>
                            <c:choose>
                                <c:when test="${user==null}">
                                    <button class="btn btn-header my-2 my-sm-0" data-bs-toggle="modal"
                                        data-bs-target="#modallogin">Iniciar sesion</button>
                                </c:when>
                                <c:otherwise>
                                    <span class="text-primary ml-2 h5">Bienvenido
                                        ${user.username}</span>
                                    <a href="Controller?operacion=logout"
                                        class="btn btn-danger my-2 my-sm-0">Cerrar sesion</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </nav>
                </header>
                <main>
                    <div class="container mt-5">
                        <form id="form" class="d-flex">
                            <input type="text" id="inputText" class="form-control me-2"
                                placeholder="Busca un libro">
                            <button class="btn btn-header" type="submit">Buscar</button>
                            <div class="error-message"></div>
                        </form>

                        <div class="mt-3">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="opciones" id="all" value="all"
                                    checked>
                                <label class="form-check-label" for="all">Todo</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="opciones" id="title" value="title">
                                <label class="form-check-label" for="title">Titulo</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="opciones" id="author" value="author">
                                <label class="form-check-label" for="author">Autor</label>
                            </div>
                        </div>

                    </div>

                    <div class="container mt-5">
                        <div class="row"></div>
                    </div>
                    <div id="pagination" class="mt-4 text-center">
                        <button id="prevPage" class="btn btn-header">Anterior</button>
                        <button id="nextPage" class="btn btn-header">Siguiente</button>
                    </div>
                </main>
                <!-- Modal info -->
                <div class="modal fade" id="modallogin" tabindex="-1" data-bs-keyboard="false" role="dialog"
                    aria-labelledby="modalTitleId" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalTitleId">Iniciar sesion</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <form action="Controller?operacion=login" method="post">
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Nombre</label> <input type="text"
                                            class="form-control" name="nick" id="" aria-describedby="helpId"
                                            placeholder="" />

                                    </div>
                                    <div class="mb-3">
                                        <label for="" class="form-label">Email</label> <input type="text"
                                            class="form-control" name="email" id="" aria-describedby="helpId"
                                            placeholder="" />

                                    </div>
                                    <div class="mb-3">
                                        <label for="" class="form-label">Contraseña</label> <input type="password"
                                            class="form-control" name="pass" id="" placeholder="" />
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary"
                                        data-bs-dismiss="modal">Cancelar</button>
                                    <button type="submit" class="btn btn-header">Aceptar</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
                <%@ include file="footer.jsp" %>
                    <script type="module" src="js/mainNew2.js"></script>
                    <!-- Bootstrap JavaScript Libraries -->
                    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
                        crossorigin="anonymous"></script>

                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
                        crossorigin="anonymous"></script>
            </body>

            </html>