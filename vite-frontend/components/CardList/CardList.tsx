import Card from "../Card/Card";
import type { GoogleBook } from "~/types/interfaces";

interface CardListProps {
  books: GoogleBook[];
  onAddToFavorites: (book: GoogleBook) => void;
}

const CardList = ({ books = [], onAddToFavorites }: CardListProps) => {
  // Default to an empty array if undefined
  return (
    <div className="grid grid-cols-1 sm:grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6 p-6">
      {books.length === 0 ? ( // Show a message if no Pokémon are found
        <p className="text-white text-center col-span-full">No book found.</p>
      ) : (
        books.map((book: GoogleBook) => (
          <Link
            to={`/book/${encodeURIComponent(book.key)}`}
            key={book.key}
            className="block transform transition duration-300 hover:scale-105"
          >
            <Card book={book} onAddToFavorites={onAddToFavorites} />
          </Link>
        ))
      )}
    </div>
  );
};

export default CardList;
