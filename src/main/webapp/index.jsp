<%@page import="java.util.List" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <!doctype html>
            <html lang="en">

            <head>
                <title>Title</title>
                <!-- Required meta tags -->
                <meta charset="utf-8" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

                <!-- Bootstrap CSS v5.2.1 -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
                    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
                    crossorigin="anonymous" />
                <link rel="stylesheet" href="css/style.css">
            </head>

            <body>
                <header>
                    <nav class="navbar navbar-expand-sm navbar-light header-bg">
                        <div class="container">
                            <a class="navbar-brand" href="#"><img src="img/logobibliosocial.jpeg" alt=""
                                    width="100px"></a>
                            <a class="navbar-brand" href="#">Reseñas</a>
                            <a class="navbar-brand" href="#">Favoritos</a>
                            <a class="navbar-brand" href="Controller?operacion=showLists">Listas</a>
                            <a class="navbar-brand" href="#">Perfil</a>
                            <c:choose>
                                <c:when test="${user==null}">
                                    <button class="btn btn-danger my-2 my-sm-0" data-bs-toggle="modal"
                                        data-bs-target="#modallogin">Login</button>
                                </c:when>
                                <c:otherwise>
                                    <span class="text-primary ml-2 h5">Welcome
                                        ${user.username}</span>
                                    <a href="Controller?operacion=logout"
                                        class="btn btn-success my-2 my-sm-0">Logout</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </nav>
                </header>
                <main>
                    <div class="container mt-5">
                        <form id="form" class="d-flex">
                            <input type="text" id="inputText" class="form-control me-2"
                                placeholder="Search for a book or author">
                            <button class="btn btn-primary" type="submit">Search</button>
                            <div class="error-message"></div>
                        </form>

                        <div class="mt-3">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="opciones" id="all" value="all"
                                    checked>
                                <label class="form-check-label" for="all">All</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="opciones" id="title" value="title">
                                <label class="form-check-label" for="title">Title</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="opciones" id="author" value="author">
                                <label class="form-check-label" for="author">Author</label>
                            </div>
                        </div>

                    </div>

                    <div class="container mt-5">
                        <div class="row"></div>
                    </div>
                    <div id="pagination" class="mt-4 text-center">
                        <button id="prevPage" class="btn btn-primary">Anterior</button>
                        <button id="nextPage" class="btn btn-primary">Siguiente</button>
                    </div>
                </main>
                <footer>
                    <!-- place footer here -->
                </footer>
                <!-- Modal info -->
                <div class="modal fade" id="modallogin" tabindex="-1" data-bs-keyboard="false" role="dialog"
                    aria-labelledby="modalTitleId" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalTitleId">Login & Register</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <form action="Controller?operacion=login" method="post">
                                <div class="modal-body">
                                    <div class="mb-3">
                                        <label for="" class="form-label">Nick</label> <input type="text"
                                            class="form-control" name="nick" id="" aria-describedby="helpId"
                                            placeholder="" />

                                    </div>
                                    <div class="mb-3">
                                        <label for="" class="form-label">email</label> <input type="text"
                                            class="form-control" name="email" id="" aria-describedby="helpId"
                                            placeholder="" />

                                    </div>
                                    <div class="mb-3">
                                        <label for="" class="form-label">Password</label> <input type="password"
                                            class="form-control" name="pass" id="" placeholder="" />
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary"
                                        data-bs-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary">Login &
                                        register</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>

                <script type="module" src="js/main.js"></script>
                <!-- Bootstrap JavaScript Libraries -->
                <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                    integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
                    crossorigin="anonymous"></script>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                    integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
                    crossorigin="anonymous"></script>
            </body>

            </html>