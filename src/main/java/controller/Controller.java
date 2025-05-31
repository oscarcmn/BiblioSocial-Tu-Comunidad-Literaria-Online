package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import daos.BookListDAO;
import daos.BookListItemDAO;
import daos.RatingDAO;
import daos.ReviewCommentDAO;
import daos.ReviewDAO;
import daos.UserDAO;
import daos.UserFollowerDAO;
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
import model.ReviewComment;
import model.User;
import model.UserFollower;
import model.UserFollowerPK;
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
		case "deleteList": {
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
		case "showListDetails": {
			int listaId = Integer.parseInt(request.getParameter("listaId"));
			BookList lista = BookListDAO.findById(listaId);
			request.setAttribute("nombreLista", lista.getName());
			request.setAttribute("listaId", listaId);
			List<BookListItem> books = BookListItemDAO.findByListId(listaId);
			request.setAttribute("books", books);
			request.getRequestDispatcher("listDetails.jsp").forward(request, response);
			break;
		}
		case "searchUserById": {
			int id = Integer.parseInt(request.getParameter("userId"));
			User foundUser = UserDAO.findById(id);
			request.setAttribute("foundUser", foundUser);
			request.getRequestDispatcher("searchUsers.jsp").forward(request, response);
			break;
		}
		case "searchUserByEmail": {
			String email = request.getParameter("email");
			User foundUser = UserDAO.findByEmail(email);
			request.setAttribute("foundUser", foundUser);
			request.getRequestDispatcher("searchUsers.jsp").forward(request, response);
			break;
		}
		case "followUser": {
			User follower = (User) session.getAttribute("user");
			int followedId = Integer.parseInt(request.getParameter("followedId"));
			User followed = UserDAO.findById(followedId);

			if (follower != null && followed != null && follower.getId() != followed.getId()) {
				UserFollower existing = UserFollowerDAO.findByIds(follower.getId(), followedId);
				if (existing == null) {
					UserFollower uf = new UserFollower();
					UserFollowerPK pk = new UserFollowerPK();
					pk.setFollowerId(follower.getId());
					pk.setFollowedId(followedId);
					uf.setId(pk);
					uf.setUser1(follower);
					uf.setUser2(followed);
					uf.setFollowedAt(new Timestamp(System.currentTimeMillis()));
					UserFollowerDAO.save(uf);
				}
			}
			response.sendRedirect("Controller?operacion=searchUserById&userId=" + followedId);
			break;
		}
		case "feed": {
			User userActual = (User) session.getAttribute("user");
			if (userActual != null) {
				List<Review> feed = ReviewDAO.findReviewsByFollowedUsers(userActual.getId());
				request.setAttribute("feed", feed);
				request.getRequestDispatcher("reviewFeed.jsp").forward(request, response);
			} else {
				response.sendRedirect("index.jsp");
			}
			break;
		}
		case "reviewDetail": {
			User userActual = (User) session.getAttribute("user");
			int reviewId = Integer.parseInt(request.getParameter("id"));
			Review review = ReviewDAO.findById(reviewId);
			String bookId = review.getBookGoogleId();
			List<ReviewComment> comments = ReviewCommentDAO.findByReviewId(reviewId);
			Rating rating = RatingDAO.findByUserAndBook(userActual.getId(),bookId);
			request.setAttribute("rating", rating);
			request.setAttribute("review", review);
			request.setAttribute("comments", comments);
			request.getRequestDispatcher("reviewDetail.jsp").forward(request, response);
			break;
		}
		case "addReviewComment": {
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			String content = request.getParameter("content");
			User userActual = (User) session.getAttribute("user");

			Review review = ReviewDAO.findById(reviewId);
			ReviewComment rc = new ReviewComment();
			rc.setReview(review);
			rc.setUser(userActual);
			rc.setContent(content);
			rc.setCommentedAt(new Timestamp(System.currentTimeMillis()));
			ReviewCommentDAO.save(rc);

			response.sendRedirect("Controller?operacion=reviewDetail&id=" + reviewId);
			break;
		}
		case "showProfile": {
			User userActual = (User) session.getAttribute("user");
			if (userActual != null) {
				User fullUser = UserDAO.findById(userActual.getId());
				int followers = fullUser.getUserFollowers2().size();
				int following = fullUser.getUserFollowers1().size();
				List<BookList> listas;
				listas = BookListDAO.findByUserId(userActual.getId());
				request.setAttribute("listasLibros", listas);
				request.setAttribute("followersCount", followers);
				request.setAttribute("followingCount", following);
				request.getRequestDispatcher("userProfile.jsp").forward(request, response);

			} else {
				response.sendRedirect("index.jsp");
			}
			break;
		}
		case "deleteBookFromList":{
			int listId = Integer.parseInt(request.getParameter("listId"));
		    String volumeId = request.getParameter("volumeId");

		    BookListItem bookToDelete = BookListItemDAO.findByListIdAndBookId(listId, volumeId);

		    if (bookToDelete != null) {
		        BookListItemDAO.delete(bookToDelete);
		    }

		    response.sendRedirect("Controller?operacion=showListDetails&listaId=" + listId);
		    break;
		}
		case "showUserProfile": {
		    int userId = Integer.parseInt(request.getParameter("userId"));
		    User profileUser = UserDAO.findById(userId);

		    if (profileUser != null) {
		        int followers = profileUser.getUserFollowers2().size();
		        int following = profileUser.getUserFollowers1().size();
		        List<BookList> listas = BookListDAO.findByUserId(profileUser.getId());

		        request.setAttribute("user", profileUser);
		        request.setAttribute("followersCount", followers);
		        request.setAttribute("followingCount", following);
		        request.setAttribute("listasLibros", listas);

		        request.getRequestDispatcher("userProfile.jsp").forward(request, response);
		    } else {
		        response.sendRedirect("searchUsers.jsp");
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
