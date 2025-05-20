<%@page contentType="text/html" pageEncoding="UTF-8" %>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page import="java.util.List" %>
      <!DOCTYPE html>
      <html lang="es">

      <head>
        <meta charset="UTF-8">
        <title>Detalles del Libro</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
        <style>
          .star {
            font-size: 1.5rem;
            cursor: pointer;
            color: gray;
          }

          .star.checked {
            color: gold;
          }
        </style>
      </head>

      <body>

        <div class="container py-5">
          <!-- Detalles cargados por JS -->
          <div id="details" class="mb-5"></div>

          <!-- Valoración del usuario -->
          <div class="mb-4">
            <h4>Valora este libro</h4>
            <form action="Controller?operacion=rateBook" method="post" id="ratingForm">
              <div id="userRating" class="mb-2">
                <c:forEach begin="1" end="5" var="i">
                  <span class="star" data-value="${i}">&#9733;</span>
                </c:forEach>
              </div>
              <input type="hidden" name="puntuacion" id="puntuacionInput">
              <input type="hidden" name="bookId" value="${param.id}">
              <button type="submit" class="btn btn-primary">Enviar valoración</button>
            </form>
          </div>

          <!-- Valoración media -->
          <div class="mb-4">
            <h5>Valoración media:</h5>
            <div>
              <c:set var="estrellas" value="${valoracionMedia != null ? valoracionMedia.intValue() : 0}" />
              <c:forEach begin="1" end="5" var="i">
                <c:choose>
                  <c:when test="${i <= estrellas}">
                    <span class="star checked">&#9733;</span>
                  </c:when>
                  <c:otherwise>
                    <span class="star">&#9733;</span>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
              <span class="ms-2">
                (
                <c:out value="${valoracionMedia}" />
                de
                <c:out value="${totalValoraciones != null ? totalValoraciones : 0}" /> valoraciones)
              </span>
            </div>
          </div>

          <!-- Reseña -->
          <div class="mb-4">
            <h4>Escribe una reseña</h4>
            <form action="Controller?operacion=reviewBook" method="post">
              <div class="mb-3">
                <textarea name="contenido" class="form-control" rows="4" placeholder="Escribe aquí tu reseña..."
                  required></textarea>
              </div>
              <input type="hidden" name="bookId" value="${param.id}">
              <button type="submit" class="btn btn-success">Enviar reseña</button>
            </form>
          </div>
        </div>

        <!-- Carga detalles dinámicamente -->
        <script type="module" src="js/detailsInfo.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
          integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
          crossorigin="anonymous"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
          integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
          crossorigin="anonymous"></script>
      </body>

      </html>