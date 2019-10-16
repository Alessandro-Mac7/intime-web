package controller.cliente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import persistence.DataBaseManager;
import persistence.dao.ClienteDao;

@WebServlet(urlPatterns = { "/iscriviCliente" })
public class IscriviClienteServlet extends HttpServlet {

	private static final long serialVersionUID = 3L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/homeView.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
			Cliente cliente = new Cliente();
			cliente.setMail(req.getParameter("mail"));
			cliente.setPassword(req.getParameter("password"));
			cliente.setNome(req.getParameter("nome"));
			cliente.setCognome(req.getParameter("cognome"));
			cliente.setTelefono(req.getParameter("telefono"));
			

			DataBaseManager.getInstance().getDaoFactory().getClienteDao().save(cliente);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/homeView.jsp");
		dispatcher.forward(req, resp);

	}
}
