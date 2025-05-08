//error de eclipse con exports me como una mierda y lo tengo que cambiar

export async function getBooksByTitle(title) {
  const formattedTitle = title.replace(/ /g, "+");
  try {
    const response = await fetch(
      `${API_URL}/search.json?title=${formattedTitle}&fields=title,author_name,publish_year,ratings_average,ratings_count,cover_i,number_of_pages_median&language=eng`
    );
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error:", error);
    return null;
  }
}

export async function getBooksByAnything(query) {
  const formattedquery = query.replace(/ /g, "+");
  try {
    const response = await fetch(
      `${API_URL}/search.json?title=${formattedquery}&author=${formattedquery}&fields=title,author_name,publish_year,ratings_average,ratings_count,cover_i,number_of_pages_median&language=eng`
    );
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error:", error);
    return null;
  }
}

export async function getBooksByAuthor(author) {
  const formattedAuthor = author.replace(/ /g, "+");
  try {
    const response = await fetch(
      `${API_URL}/search.json?author=${formattedAuthor}&fields=title,author_name,publish_year,ratings_average,ratings_count,cover_i,number_of_pages_median&language=eng`
    );
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error:", error);
    return null;
  }
}
