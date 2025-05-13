const URL_API = "https://www.googleapis.com/books/v1/volumes";
let currentIndex = 0;
let lastQuery = "";
let lastMode = "";
const pagination = 20;

async function getBooks(query, mode, startIndex = 0) {
  const formattedQuery = query.replace(/ /g, "+");
  let searchParam = "";

  if (mode === "title") searchParam = `intitle:${formattedQuery}`;
  else if (mode === "author") searchParam = `inauthor:${formattedQuery}`;
  else searchParam = formattedQuery;

  try {
    const response = await fetch(
      `${URL_API}?q=${searchParam}&startIndex=${startIndex}&maxResults=${pagination}&key=AIzaSyBkGuaU30mlnQeCZXHHulQ6VI5TKv3MoxQ`
    );
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error:", error);
    return null;
  }
}

const form = document.getElementById("form");
const inputText = document.getElementById("inputText");
const authorButton = document.getElementById("author");
const allButton = document.getElementById("all");
const titleButton = document.getElementById("title");

form.addEventListener("submit", function (event) {
  event.preventDefault();
  event.stopPropagation();

  if (validateForm()) {
    const query = inputText.value.trim();
    lastQuery = query;

    if (titleButton.checked) {
      lastMode = "title";
    } else if (authorButton.checked) {
      lastMode = "author";
    } else {
      lastMode = "all";
    }

    currentIndex = 0;
    fetchAndRenderBooks();
  }
});

function fetchAndRenderBooks() {
  getBooks(lastQuery, lastMode, currentIndex).then((books) => {
    renderBooks(books);
  });
}

const rowCards = document.querySelector(".row");
function renderBooks(books) {
  rowCards.innerHTML = "";

  if (!books || !books.items || books.items.length === 0) {
    rowCards.innerHTML = "<p>No se encontraron resultados.</p>";
    return;
  }

  books.items.forEach((item) => {
    const book = item.volumeInfo;
    const bookId = item.id;
    const cardItem = document.createElement("div");
    cardItem.classList.add("col");

    cardItem.innerHTML = `
      <div class="card mx-auto font" style="width: 18rem;">
        <img src="${
          book.imageLinks?.thumbnail ||
          "https://via.placeholder.com/128x195?text=No+Image"
        }" class="img-top" alt="Book cover">
        <div class="card-body">
          <div class="d-flex justify-content-between">
            <h5 class="title">${book.title || "Unknown Title"}</h5>
            <div>
              <p class="year">${book.publishedDate?.split("-")[0] || "N/A"}</p>
            </div>
          </div>
          <div class="d-flex">
            <p class="property">Autor</p>
            <p class="dataproperty">${book.authors?.join(", ") || "Unknown"}</p>
          </div>
          <div class="d-flex">
            <p class="property">Nota</p>
            <p class="dataproperty">${book.averageRating || "N/A"}</p>
          </div>
          <div class="d-flex">
            <p class="property">Numero de valoraciones</p>
            <p class="dataproperty">${book.ratingsCount || "N/A"}</p>
          </div>
          <div class="d-flex">
            <p class="property">Numero de paginas</p>
            <p class="dataproperty">${book.pageCount || "N/A"}</p>
          </div>
          <div class="d-flex">
             <button class="btn btn-primary mt-2" onclick="viewBookDetails('${bookId}')">Ver más</button>
          </div>
          
        </div>
      </div>
    `;

    rowCards.appendChild(cardItem);
  });
}

document.getElementById("prevPage").addEventListener("click", () => {
  if (currentIndex >= pagination) {
    currentIndex -= pagination;
    fetchAndRenderBooks();
  }
});

document.getElementById("nextPage").addEventListener("click", () => {
  currentIndex += pagination;
  fetchAndRenderBooks();
});

function validateForm() {
  let isValid = true;

  if (inputText.value.trim() === "") {
    markFieldAsNotValid(inputText, "Enter a query!");
    isValid = false;
  } else {
    markFieldAsValid(inputText);
  }

  return isValid;
}

function markFieldAsNotValid(
  element,
  message = "Invalid field. Insert a valid value."
) {
  const errorElement = element.parentNode.querySelector(".error-message");
  if (errorElement) {
    errorElement.textContent = message;
  }
  element.parentNode.classList.add("is-not-valid-field");
}

function markFieldAsValid(element) {
  const errorElement = element.parentNode.querySelector(".error-message");
  if (errorElement) {
    errorElement.textContent = "";
  }
  element.parentNode.classList.remove("is-not-valid-field");
}

function viewBookDetails(bookId) {
  window.location.href = `bookDetails.html?id=${bookId}`;
}
window.viewBookDetails = viewBookDetails;
