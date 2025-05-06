const URL_API = "https://www.googleapis.com/books/v1/volumes";


import type {
    GoogleBook,
    GoogleBooksAPIResponse
} from "../types/interfaces";


//fetch by anything
export async function getBooksByAnything(query: string): Promise<GoogleBook[]> {
    const formattedQuery = query.replace(/ /g, "+");
    try {
      const response: Response = await fetch(`${URL_API}?q=${formattedQuery}&key=AIzaSyBkGuaU30mlnQeCZXHHulQ6VI5TKv3MoxQ`);
  
      if (!response.ok) {
        throw new Error(
          `Failed to fetch Books by anything list: ${response.status} ${response.statusText}`
        );
      }
  
      const data: GoogleBooksAPIResponse = await response.json();
      console.log("Fetched Books by anything list:", data.items);
      return data.items;
    } catch (error) {
      console.error("Error fetching Books by anything:", error);
      return []; 
    }
  }
  
  //fetch by title
  export async function getBooksByTitle(title: string): Promise<GoogleBook[]> {
    const formattedTitle = title.replace(/ /g, "+");
    try {
      const response: Response = await fetch(`${URL_API}?q=${formattedTitle}&key=AIzaSyBkGuaU30mlnQeCZXHHulQ6VI5TKv3MoxQ`);
  
      if (!response.ok) {
        throw new Error(
          `Failed to fetch Books by Title list: ${response.status} ${response.statusText}`
        );
      }
  
      const data: GoogleBooksAPIResponse = await response.json();
      console.log("Fetched Books by title list:", data.items);
      return data.items;
    } catch (error) {
      console.error("Error fetching Books by title:", error);
      return []; 
    }
  }
  
  //fetch by author
  export async function getBooksByAuthor(author: string): Promise<GoogleBook[]> {
    const formattedAuthor = author.replace(/ /g, "+");
    try {
      const response: Response = await fetch(`${URL_API}?q=${formattedAuthor}&key=AIzaSyBkGuaU30mlnQeCZXHHulQ6VI5TKv3MoxQ`);
  
      if (!response.ok) {
        throw new Error(
          `Failed to fetch Books By Author list: ${response.status} ${response.statusText}`
        );
      }
  
      const data: GoogleBooksAPIResponse = await response.json();
      console.log("Fetched Books By Author list:", data.items);
      return data.items;
    } catch (error) {
      console.error("Error fetching Books By Author:", error);
      return []; 
    }
  }
