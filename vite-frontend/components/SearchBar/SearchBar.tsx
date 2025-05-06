import React from "react";

interface SearchBoxProps {
  placeholder: string;
  onSearchChange: (searchText: string) => void;
}

const SearchBar = ({ placeholder, onSearchChange }: SearchBoxProps) => {
  function onChange(e: React.ChangeEvent<HTMLInputElement>) {
    onSearchChange(e.target.value);
  }

  return (
    <input
      type="search"
      placeholder={placeholder}
      onChange={onChange}
      className="px-30 py-2  bg-[var(--color-dark-yellow)]  rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-bg-[var(--color-soft-yellow)]-500 focus:border-[var(--color-dark-yellow)]-500 transition duration-200"
      //className="w-full max-w-sm px-4 py-2  bg-[var(--color-dark-yellow)]  rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-[var(--color-dark-yellow)]-500 transition duration-200"
    />
  );
};

export default SearchBar;
