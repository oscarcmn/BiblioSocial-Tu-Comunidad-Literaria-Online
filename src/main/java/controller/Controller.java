package controller;

import java.io.IOException;
import java.util.List;

import daos.BookListDAO;
import daos.BookListItemDAO;
import daos.RatingDAO;
import daos.ReviewDAO;
import daos.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.BookList;
import model.BookListItem;
import model.BookListItemPK;
import model.Rating;
import model.Review;
import model.User;
import util.Hash;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String operacion = request.getParameter("operacion");
		RequestDispatcher dispatcher;
		User user = null;

		switch (operacion) {
		case "login": {
			String username = request.getParameter("nick");
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			// buscamos el user por si está registrado
			user = UserDAO.findByEmail(email);

			if (user != null) { // si hay un user registrado con ese email
				// compruebo si la clave es correcta
				if (user.getPasswordHash().equals(Hash.getSha256(pass))) {
					// guardo el objeto user dentro de la session
					session.setAttribute("user", user);
				} else {
					// quitar el user de la session
					session.removeAttribute("user");
				}
			} else {// si no está el user registrado
				user = new User();
				user.setEmail(email);
				user.setUsername(username);
				user.setPasswordHash(Hash.getSha256(pass));
				UserDAO.save(user);
				session.setAttribute("user", user);
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		}
		case "logout": {
			session.removeAttribute("user");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
		}
		case "showLists": {
			User userActual = (User) session.getAttribute("user");
			if (userActual != null) {
				List<BookList> listas;
				listas = BookListDAO.findByUserId(userActual.getId());
				request.setAttribute("listasLibros", listas);
				request.getRequestDispatcher("lists.jsp").forward(request, response);
			} else {
				response.sendRedirect("index.jsp");
			}
			break;
		}
		case "createNewList": {
			String nombreLista = request.getParameter("nombreLista");
			User userActual = (User) session.getAttribute("user");
			BookList lista = new BookList();
			lista.setName(nombreLista);
			lista.setUser(userActual);
			BookListDAO.save(lista);
			List<BookList> listas;
			listas = BookListDAO.findByUserId(userActual.getId());
			request.setAttribute("listasLibros", listas);
			request.getRequestDispatcher("lists.jsp").forward(request, response);
			break;
		}
		case "deleteList":{
			int listaId = Integer.parseInt(request.getParameter("listaId"));
		    BookList lista = BookListDAO.findById(listaId);
		    BookListDAO.delete(lista);
		    response.sendRedirect("Controller?operacion=showLists");
		    break;
		}
		case "rateBook": {
			User userActual = (User) session.getAttribute("user");
		    String bookId = request.getParameter("bookId");
		    int puntuacion = Integer.parseInt(request.getParameter("puntuacion"));
		    RatingDAO ratingDAO = new RatingDAO();

		    Rating existente = ratingDAO.findByUserAndBook(userActual.getId(), bookId);
		    if (existente != null) {
		        existente.setRating(puntuacion);
		        ratingDAO.update(existente);
		    } else {
		        Rating nuevo = new Rating();
		        nuevo.setBookGoogleId(bookId);
		        nuevo.setRating(puntuacion);
		        nuevo.setUser(userActual);
		        ratingDAO.save(nuevo);
		    }

		    response.sendRedirect("bookDetails.jsp?id=" + bookId);
		    break;
		}
		case "reviewBook": {
			User userActual = (User) session.getAttribute("user");
		    String bookId = request.getParameter("bookId");
		    String contenido = request.getParameter("contenido");

		    ReviewDAO reviewDAO = new ReviewDAO();

		    Review existente = reviewDAO.findByUserAndBook(userActual.getId(), bookId);
		    if (existente != null) {
		        existente.setComment(contenido);
		        reviewDAO.update(existente);
		    } else {
		        Review nueva = new Review();
		        nueva.setBookGoogleId(bookId);
		        nueva.setComment(contenido);
		        nueva.setUser(userActual);
		        reviewDAO.save(nueva);
		    }

		    response.sendRedirect("bookDetails.jsp?id=" + bookId);
		    break;
		}
		case "addToList": {
		    User userActual = (User) session.getAttribute("user");
		    if (userActual != null) {
		        String bookId = request.getParameter("bookId");
		        int listaId = Integer.parseInt(request.getParameter("listaId"));

		        BookList lista = BookListDAO.findById(listaId);
		        if (lista != null && lista.getUser().getId() == userActual.getId()) {
		            BookListItem item = new BookListItem();
		            BookListItemPK pk = new BookListItemPK();
		            pk.setListId(listaId);
		            pk.setVolumeId(bookId);
		            item.setId(pk);
		            item.setBookList(lista);
		            item.setAddedAt(new java.sql.Timestamp(System.currentTimeMillis()));

		            BookListItemDAO.save(item);
		        }
		        response.sendRedirect("bookDetails.jsp?id=" + bookId);
		    } else {
		        response.sendRedirect("index.jsp");
		    }
		    break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + operacion);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
