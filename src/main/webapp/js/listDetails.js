const API_KEY = "AIzaSyBkGuaU30mlnQeCZXHHulQ6VI5TKv3MoxQ";
const API_URL = "https://www.googleapis.com/books/v1/volumes";

async function fetchBookById(bookId) {
	console.log("Libros recibidos:", libros);
  try {
    const response = await fetch(`${API_URL}/${bookId}?key=${API_KEY}`);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error al cargar el libro:", error);
    return null;
  }
}

async function renderBooks() {
  const container = document.getElementById("contenedorLibros");
  container.innerHTML = "<p>Cargando libros...</p>";

  const books = await Promise.all(librosJson.map(entry => fetchBookById(entry.id)));
  container.innerHTML = "";

  books.forEach((book) => {
    if (!book || !book.volumeInfo) return;
    const info = book.volumeInfo;

    const card = document.createElement("div");
    card.className = "col-md-4 mb-4";

    card.innerHTML = `
      <div class="card h-100">
        <img src="${info.imageLinks?.thumbnail || 'https://via.placeholder.com/128x195?text=No+Image'}" class="card-img-top" alt="Portada">
        <div class="card-body">
          <h5 class="card-title">${info.title}</h5>
          <p class="card-text">${info.authors?.join(", ") || "Autor desconocido"}</p>
          <a href="bookDetails.jsp?id=${book.id}" class="btn btn-primary btn-sm">Ver más</a>
        </div>
      </div>
    `;

    container.appendChild(card);
  });
}

window.onload = renderBooks;
