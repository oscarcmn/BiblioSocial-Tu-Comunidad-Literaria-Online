<%@page import="java.util.List" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <!doctype html>
            <html lang="en">

            <head>
                <title>Feed de Reseñas</title>
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
                <%@ include file="header.jsp" %>
                <main>
                    <div class="container mt-5">
                        <h2 class="mb-4">Feed de Reseñas</h2>

                        <c:forEach var="review" items="${feed}">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h5 class="card-title">${review.user.username} reseñó un libro</h5>
                                    <div class="d-flex align-items-start">
                                        <div class="me-3">
                                            <div class="book-info" id="book-${review.id}" data-book-id="${review.bookGoogleId}">
                                            </div>
                                        </div>
                                        <div>
                                            <p class="card-text">${review.comment}</p>
                                            <a href="Controller?operacion=reviewDetail&id=${review.id}"
                                                class="btn btn-primary">Ver
                                                y comentar</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </main>
                <footer>
                    <!-- place footer here -->
                </footer>
                <script type="module" src="js/feedBooks.js"></script>
                <!-- Bootstrap JavaScript Libraries -->
                <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                    integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
                    crossorigin="anonymous"></script>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                    integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
                    crossorigin="anonymous"></script>
            </body>

            </html>