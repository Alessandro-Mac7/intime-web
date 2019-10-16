package controller.cliente;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Utility;
import model.Appuntamento;
import model.Cliente;
import model.Professionista;
import persistence.DataBaseManager;

@WebServlet(urlPatterns = { "/ricercaAppuntamento" })
public class RicercaAppuntamentoServlet extends HttpServlet {

	private static final long serialVersionUID = 6L;
	private static String lastQuery = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Cliente cliente = Utility.getLoginedClient(session);
		Professionista pro = null;
		String actualQuery = req.getParameter("id");
		
		LocalDate data = LocalDate.now();
		
		if (actualQuery != null)
			lastQuery = actualQuery;

		List<Appuntamento> appuntamenti = null;

		if (cliente == null) {
			pro = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().findByMail(actualQuery);
			appuntamenti = DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao()
					.findByProfessionistaPrenotabile(actualQuery, data);
		} else if (actualQuery != null) {
			pro = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().findByMail(actualQuery);
			appuntamenti = DataBaseManager.getInstance().getDaoFactory().getClienteDao()
					.findAppuntamentiPrenotabili(actualQuery, cliente.getMail(), data);

		} else {
			pro = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().findByMail(lastQuery);
			appuntamenti = DataBaseManager.getInstance().getDaoFactory().getClienteDao()
					.findAppuntamentiPrenotabili(lastQuery, cliente.getMail(), data);
		}

		req.setAttribute("appuntamenti", appuntamenti);
		req.setAttribute("pro", pro);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/ricercaAppuntamentoView.jsp");
		dispatcher.forward(req, resp);
	}

}
