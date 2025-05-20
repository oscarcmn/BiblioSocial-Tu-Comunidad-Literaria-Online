async function fetchBookDetails() {
	const params = new URLSearchParams(window.location.search);
	const bookId = params.get("id");

	if (!bookId) {
		document.getElementById("details").innerHTML = "<p>No se especificó ningún libro.</p>";
		return;
	}

	try {
		const response = await fetch(`https://www.googleapis.com/books/v1/volumes/${bookId}`);
		const data = await response.json();
		renderBookDetails(data);
	} catch (error) {
		document.getElementById("details").innerHTML = "<p>Error al cargar los detalles del libro.</p>";
		console.error("Error:", error);
	}
}

function renderBookDetails(book) {
	const info = book.volumeInfo;
	document.getElementById("details").innerHTML = `
      <h1>${info.title}</h1>
      <h3>${info.authors?.join(", ") || "Autor desconocido"}</h3>
      <img src="${info.imageLinks?.thumbnail || ""}" alt="Portada" />
      <p><strong>Publicado:</strong> ${info.publishedDate || "N/A"}</p>
      <p><strong>Descripción:</strong><br>${info.description || "No hay descripción."}</p>
      <p><strong>Páginas:</strong> ${info.pageCount || "N/A"}</p>
      <p><strong>Editorial:</strong> ${info.publisher || "Desconocida"}</p>
    `;
}

document.addEventListener("DOMContentLoaded", () => {
	fetchBookDetails();

	const stars = document.querySelectorAll("#userRating .star");
	const puntuacionInput = document.getElementById("puntuacionInput");

	stars.forEach((star, index) => {
		star.addEventListener("click", () => {
			puntuacionInput.value = star.dataset.value;
			stars.forEach((s, i) => {
				s.classList.toggle("checked", i <= index);
			});
		});
	});
});