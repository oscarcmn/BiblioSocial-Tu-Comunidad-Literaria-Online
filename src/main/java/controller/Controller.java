package controller;

import java.io.IOException;

import daos.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String operacion = request.getParameter("operacion");
		RequestDispatcher dispatcher;
		User user = null;

	switch (operacion) {
	case "login":{
		String username = request.getParameter("nick");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		//buscamos el user por si está registrado
		 user = UserDAO.findByEmail(email);

		if (user!=null) { //si hay un user registrado con ese email
			//compruebo si la clave es correcta
			if(user.getPasswordHash().equals(Hash.getSha256(pass))) {
				//guardo el objeto user dentro de la session
				session.setAttribute("user", user);
			}
			else {
				//quitar el user de la session
				session.removeAttribute("user");
			}
		} else {//si no está el user registrado
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
	case "logout":{
		session.removeAttribute("user");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		break;
	}
	default:
		throw new IllegalArgumentException("Unexpected value: " + operacion);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
