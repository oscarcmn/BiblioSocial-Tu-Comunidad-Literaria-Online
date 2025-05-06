import type { GoogleBook } from "../../types/interfaces";
import BookDetails from "../BookDetails/BookDetails";

import heartRed from "heart-red.svg"; // Asegúrate de importar la imagen
import { useState } from "react";

interface CardProps {
  book: GoogleBook;
  onAddToFavorites: (book: GoogleBook) => void; // Función para añadir a favoritos
}

function Card({ book, onAddToFavorites }: CardProps) {
  const [isFavorite, setIsFavorite] = useState(false);

  const handleFavoriteClick = () => {
    setIsFavorite(!isFavorite);
    onAddToFavorites(book);
  };

  return (
    <div
      className="card mx-auto font bg-soft-yellow p-6 rounded-lg shadow-lg z-50 text-black group relative"
      style={{ width: "18rem" }}
    >
      <div className="flex justify-center items-center mb-4">
        <img
          src={`https://covers.openlibrary.org/b/id/${book.cover_i || ""}.jpg`}
          className="img-top w-40 h-40 object-contain"
          alt="Book cover"
        />
      </div>

      {/* Botón para añadir a favoritos */}
      <button
        className="absolute top-2 right-2 bg-transparent border-none p-0 opacity-0 group-hover:opacity-100 transition-opacity duration-200"
        onClick={(e) => {
          e.stopPropagation();
          e.preventDefault();
          handleFavoriteClick();
        }}
      >
        <img src={heartRed} alt="Add to Favorites" className="w-8 h-8" />
      </button>

      <div className="card-body text-black">
        <div className="flex justify-between items-center mb-4">
          <h5 className="title text-xl font-bold">
            {book.title || "Unknown Title"}
          </h5>
          <div>
            <p className="year">
              {book.publish_year ? book.publish_year[0] : "N/A"}
            </p>
          </div>
        </div>
        <div className="flex justify-between items-center mb-2">
          <p className="font-medium">Author:</p>
          <p>{book.author_name ? book.author_name : "Unknown"}</p>
        </div>
        <div className="flex justify-between items-center mb-2">
          <p className="font-medium">Avg Rating:</p>
          <p>{book.ratings_average || "N/A"}</p>
        </div>
        <div className="flex justify-between items-center mb-2">
          <p className="font-medium">Number of Ratings:</p>
          <p>{book.ratings_count || "N/A"}</p>
        </div>
        <div className="flex justify-between items-center mb-4">
          <p className="font-medium">Number of Pages:</p>
          <p>{book.number_of_pages_median || "N/A"}</p>
        </div>
      </div>
    </div>
  );
}

export default Card;
