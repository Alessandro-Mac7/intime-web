package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cliente;
import model.Professionista;
import persistence.DataBaseManager;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Visualizza la pagina di login
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		String select = request.getParameter("selection");
		String ricordami = request.getParameter("ricordami");
		boolean rememberMe = "Y".equals(ricordami);

		Professionista professionista = null;
		Cliente cliente = null;
		String errorMessage = null;
		boolean error = false;
		
		if (select.equals("Professionista")) {
			if (!DataBaseManager.getInstance().getDaoFactory().getLoginDao().accessoProfessionisti(mail, password)) {
				errorMessage = "Il Professionista non è registrato nel Sistema!";
				error = true; 
			} else {
				professionista = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().findByMail(mail);
			}

		} else {
			if (!DataBaseManager.getInstance().getDaoFactory().getLoginDao().accessoClienti(mail, password)) {
				errorMessage = "Il Cliente non è registrato nel Sistema!";
				error = true;
			} else {
				cliente = DataBaseManager.getInstance().getDaoFactory().getClienteDao().findByMail(mail);
			}
		}

		if (error) {
			// Creo un nuovo cliente e gli setto la password e l'email corrente in modo da
			// poterle trovare inserite nella jsp
			if (select.equals("Professionista")) {
				professionista = new Professionista();
				professionista.setMail(mail);
				professionista.setPassword(password);
				request.setAttribute("utente", professionista);
			} else {
				cliente = new Cliente();
				cliente.setMail(mail);
				cliente.setPassword(password);
				request.setAttribute("utente", cliente);
			}
			// Salvo le informazione prima di fare il forward
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
			dispatcher.forward(request, response);
		} else {
			if (select.equals("Professionista")) {
				// Salvataggio del Login nella Session
				
				HttpSession session = request.getSession();			
				Utility.storeLoginedPro(session, professionista);
				
				if (rememberMe) {
					Utility.storeProCookie(response, professionista);
					System.out.println("Y");
				}

				response.sendRedirect(request.getContextPath() + "/areaPersonale");

			} else {

				HttpSession session = request.getSession();
				Utility.storeLoginedClient(session, cliente);
				// System.out.println(Utility.getLoginedClient(session).getNome());
				if (rememberMe) {
					Utility.storeClientCookie(response, cliente);
				}

				response.sendRedirect(request.getContextPath() + "/areaPersonale");
			}
		}

	}

}
