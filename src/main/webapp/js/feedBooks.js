document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".book-info").forEach(div => {
        const bookId = div.dataset.bookId;

        if (!bookId) {
            div.innerHTML = `<p class='text-warning'>ID no disponible</p>`;
            return;
        }

        fetch(`https://www.googleapis.com/books/v1/volumes/${bookId}?key=AIzaSyBkGuaU30mlnQeCZXHHulQ6VI5TKv3MoxQ`)
            .then(response => response.json())
            .then(data => {
                if (!data.volumeInfo) throw new Error("Sin volumeInfo");

                const info = data.volumeInfo;
                const thumbnail = info.imageLinks?.thumbnail || "img/default-book.png";
                const title = info.title || "Título no disponible";
                const authors = info.authors?.join(", ") || "Autor desconocido";

                div.innerHTML = `
                    <img src="${thumbnail}" alt="Portada del libro" class="img-thumbnail" style="max-width:100px;">
                    <p><strong>${title}</strong></p>
                    <p><small>${authors}</small></p>
                `;
            })
            .catch(err => {
                div.innerHTML = `<p class='text-danger'>Error al cargar libro</p>`;
                console.error("Error al cargar el libro:", err);
            });
    });
});
