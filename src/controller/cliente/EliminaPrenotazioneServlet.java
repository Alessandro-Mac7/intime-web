package controller.cliente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Utility;
import model.Cliente;
import persistence.DataBaseManager;

@WebServlet(urlPatterns = { "/EliminaPrenotazione" })
public class EliminaPrenotazioneServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Cliente cliente = Utility.getLoginedClient(session);

		if (cliente == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		Long codice_appuntamento = Long.parseLong(req.getParameter("elimina"));

		if (DataBaseManager.getInstance().getDaoFactory().getPrenotazioneDao().findByPrimaryKey(cliente.getMail(),
				codice_appuntamento) != null) {
			DataBaseManager.getInstance().getDaoFactory().getPrenotazioneDao()
					.delete(cliente.getMail(), codice_appuntamento);
		}

		req.getRequestDispatcher("/ricercaAppuntamento").forward(req, resp);
	}
}
