import React from "react";
import type { VolumeInfo } from "../../types/interfaces";

interface BookDetailsProps {
  bookdetails: VolumeInfo;
}

function BookDetails({ bookdetails }: BookDetailsProps) {
  const covers = bookdetails.covers || [];
  return (
    <div className="text-black p-4 ml-20 mr-20 mt-10">
      <div className="flex flex-col md:flex-row">
        <img
          src={`https://covers.openlibrary.org/b/id/${covers[0] || ""}.jpg`}
          className="w-40 h-71 object-cover rounded-lg mb-4 md:mb-0 md:mr-4"
          alt="Book cover"
        />

        <div>
          <h1 className="text-2xl font-bold mb-4">{bookdetails.title}</h1>
          <h2 className="text-xl font-bold mb-4">Sinopsis</h2>
          <p className="mt-2">{bookdetails.description}</p>
        </div>
      </div>
    </div>
  );
}

export default BookDetails;
