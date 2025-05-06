import React, { useState, useEffect } from "react";
import {
  getBooksByAnything,
  getBooksByAuthor,
  getBooksByTitle,
} from "../rest/GoogleBooksAPI";
import SearchBar from "../components/SearchBar/SearchBar";
import CardList from "../components/CardList/CardList";
import type { GoogleBook } from "../types/interfaces";
import "./App.css";

function App() {
  const [search, setSearch] = useState<string>("");
  const [filter, setFilter] = useState<string>("all");
  const [books, setBooks] = useState<GoogleBook[]>([]);
  const [loading, setLoading] = useState<boolean>(false);

  // Función para añadir un libro a favoritos
  const addToFavorites = (book: GoogleBook) => {
    const storedFavorites = localStorage.getItem("favorites");
    const favorites = storedFavorites ? JSON.parse(storedFavorites) : [];
    if (!favorites.some((fav: GoogleBook) => fav.id === book.id)) {
      favorites.push(book);
      localStorage.setItem("favorites", JSON.stringify(favorites));
      alert("Book added to favorites!");
    } else {
      alert("Book is already in favorites!");
    }
  };

  useEffect(() => {
    if (search.trim() === "") {
      setBooks([]);
      return;
    }

    const fetchBooks = async () => {
      setLoading(true);
      try {
        let fetchedBooks: GoogleBook[] = [];
        if (filter === "all") {
          fetchedBooks = await getBooksByAnything(search);
        } else if (filter === "author") {
          fetchedBooks = await getBooksByAuthor(search);
        } else if (filter === "title") {
          fetchedBooks = await getBooksByTitle(search);
        }
        setBooks(fetchedBooks);
      } catch (error) {
        console.error("Error fetching Books:", error);
        setBooks([]);
      } finally {
        setLoading(false);
      }
    };

    fetchBooks();
  }, [search, filter]);

  return (
    <div className="flex flex-col items-center justify-center text-black transition-colors duration-500">
      <div className="  text-black-900 rounded-2xl p-8 w-[90%] text-center">
        <h3 className="text-2xl font-bold text-black-600 dark:text-black-400 mb-8">
          Search for a Book
        </h3>
        <SearchBar placeholder="Write a Book name" onSearchChange={setSearch} />
        <div className="flex justify-center gap-4 mt-4">
          <label>
            <input
              type="radio"
              name="filter"
              value="all"
              checked={filter === "all"}
              onChange={(e) => setFilter(e.target.value)}
            />
            All
          </label>
          <label>
            <input
              type="radio"
              name="filter"
              value="author"
              checked={filter === "author"}
              onChange={(e) => setFilter(e.target.value)}
            />
            Author
          </label>
          <label>
            <input
              type="radio"
              name="filter"
              value="title"
              checked={filter === "title"}
              onChange={(e) => setFilter(e.target.value)}
            />
            Title
          </label>
        </div>
        {loading ? (
          <p className="text-gray-500 mt-4">Loading books...</p>
        ) : books.length === 0 ? (
          <p className="text-gray-500 mt-4">No books found.</p>
        ) : (
          <CardList books={books} onAddToFavorites={addToFavorites} />
        )}
      </div>
    </div>
  );
}

export default App;
