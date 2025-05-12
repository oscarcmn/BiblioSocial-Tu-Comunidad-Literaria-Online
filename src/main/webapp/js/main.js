const URL_API = "https://www.googleapis.com/books/v1/volumes";

async function getBooksByTitle(title) {
  const formattedTitle = title.replace(/ /g, "+");
  try {
    const response = await fetch(
      `${URL_API}?q=${formattedTitle}&key=AIzaSyBkGuaU30mlnQeCZXHHulQ6VI5TKv3MoxQ`
    );
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error:", error);
    return null;
  }
}
async function getBooksByAnything(query) {
  const formattedquery = query.replace(/ /g, "+");
  try {
    const response = await fetch(
      `${URL_API}?q=${formattedquery}&key=AIzaSyBkGuaU30mlnQeCZXHHulQ6VI5TKv3MoxQ`
    );
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error:", error);
    return null;
  }
}
async function getBooksByAuthor(author) {
  const formattedAuthor = author.replace(/ /g, "+");
  try {
    const response = await fetch(
      `${URL_API}?q=${formattedAuthor}&key=AIzaSyBkGuaU30mlnQeCZXHHulQ6VI5TKv3MoxQ`
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
const privateApiButton = document.getElementById("privateApiButton");

form.addEventListener("submit", function (event) {
  event.preventDefault();
  event.stopPropagation();

  if (validateForm()) {
    const query = inputText.value.trim();

    if (titleButton.checked) {
      getBooksByTitle(query)
        .then((books) => {
          console.log(books);
          renderBooks(books);
        })
        .catch((err) => {
          console.log("ERROR: " + err);
        });
    } else if (authorButton.checked) {
      getBooksByAuthor(query)
        .then((books) => {
          console.log(books);
          renderBooks(books);
        })
        .catch((err) => {
          console.log("ERROR: " + err);
        });
    } else if (allButton.checked) {
      getBooksByAnything(query)
        .then((books) => {
          console.log(books);
          renderBooks(books);
        })
        .catch((err) => {
          console.log("ERROR: " + err);
        });
    } else {
      console.log("No radio button selected.");
    }
  } else {
    console.log("There is some not-valid field. The user should check them.");
  }
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

const rowCards = document.querySelector(".row");
function renderBooks(books) {
  rowCards.innerHTML = "";

  // Verifica si hay resultados
  if (!books || !books.items || books.items.length === 0) {
    rowCards.innerHTML = "<p>No se encontraron resultados.</p>";
    return;
  }

  books.items.forEach((item) => {
    const book = item.volumeInfo;

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
            <p class="property">Author</p>
            <p class="dataproperty">${book.authors?.join(", ") || "Unknown"}</p>
          </div>
          <div class="d-flex">
            <p class="property">Avg Rating</p>
            <p class="dataproperty">${book.averageRating || "N/A"}</p>
          </div>
          <div class="d-flex">
            <p class="property">Ratings Count</p>
            <p class="dataproperty">${book.ratingsCount || "N/A"}</p>
          </div>
          <div class="d-flex">
            <p class="property">Page Count</p>
            <p class="dataproperty">${book.pageCount || "N/A"}</p>
          </div>
        </div>
      </div>
    `;

    rowCards.appendChild(cardItem);
  });
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
