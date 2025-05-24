window.viewBookDetails = function(bookId) {
    window.location.href = `bookDetails.jsp?id=${bookId}`;
};

window.removeBook = function(button) {
    const card = button.closest(".book-card");
    const volumeId = card.dataset.volumeId;
    const listId = card.dataset.listId;

    if (!confirm("¿Estás seguro de que deseas eliminar este libro de la lista?")) return;

    fetch(`Controller?operacion=deleteBookFromList&listId=${listId}&volumeId=${volumeId}`, {
        method: "GET",
    })
    .then((res) => {
        if (res.ok) {
            card.remove();
        } else {
            alert("Error al eliminar el libro.");
        }
    })
    .catch((err) => {
        console.error(err);
        alert("Error de red.");
    });
};

document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".book-card").forEach((card) => {
        const volumeId = card.dataset.volumeId;
        const listId = card.dataset.listId;
        const bookInfoDiv = card.querySelector(".book-info");

        if (!volumeId) {
            console.error("No se encontró volumeId en la tarjeta del libro");
            bookInfoDiv.innerHTML = `<p class="text-danger">Error: ID de libro no encontrado</p>`;
            return;
        }

        fetch(`https://www.googleapis.com/books/v1/volumes/${volumeId}`)
            .then((res) => {
                if (!res.ok) {
                    throw new Error(`Error HTTP: ${res.status}`);
                }
                return res.json();
            })
            .then((data) => {
                const info = data.volumeInfo;
                const thumbnail = info.imageLinks?.thumbnail || 
                    "https://via.placeholder.com/120x180?text=No+Image";
                const title = info.title || "Título no disponible";
                const authors = info.authors?.join(", ") || "Autor desconocido";

                bookInfoDiv.innerHTML = `
                    <img src="${thumbnail}" alt="Portada" class="img-fluid mb-3" style="max-height: 180px;">
                    <h5>${title}</h5>
                    <p class="text-muted"><small>${authors}</small></p>
                    <div class="d-flex justify-content-between mt-2">
                        <button class="btn btn-primary btn-sm" onclick="viewBookDetails('${volumeId}')">
                            Ver más
                        </button>
                        <button class="btn btn-danger btn-sm" onclick="removeBook(this)">
                            Borrar
                        </button>
                    </div>
                `;
            })
            .catch((error) => {
                console.error("Error al cargar el libro:", error);
                bookInfoDiv.innerHTML = `
                    <p class="text-danger">Error al cargar libro</p>
                    <div class="d-flex justify-content-between mt-2">
                        <button class="btn btn-primary btn-sm" onclick="viewBookDetails('${volumeId}')">
                            Ver más
                        </button>
                        <button class="btn btn-danger btn-sm" onclick="removeBook(this)">
                            Borrar
                        </button>
                    </div>
                `;
            });
    });
});