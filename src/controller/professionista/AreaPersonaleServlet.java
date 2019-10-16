package controller.professionista;

import java.io.IOException;
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
import model.Valutazione;
import persistence.DataBaseManager;
import persistence.dao.AppuntamentoDao;
import persistence.dao.ClienteDao;
import persistence.dao.ProfessionistaDao;

/**
 * Servlet implementation class AreaPersonaleServlet
 */
@WebServlet("/areaPersonale")
public class AreaPersonaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AreaPersonaleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Professionista loginedPro = Utility.getLoginedPro(session);
		Cliente loginedClient = Utility.getLoginedClient(session);

		if (loginedPro != null) {

			AppuntamentoDao app_dao = DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao();
			ProfessionistaDao pro_dao = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao();
			int appuntamentiDaEffettuare = 0;
			int clienti = 0;
			appuntamentiDaEffettuare = app_dao.findAppuntamentiDaEffettuare(loginedPro.getMail()).size();
			double rating = 0;
			int numVal = 0;
			boolean profiloCompleto = true;
			
			if (loginedPro.getDescrizione() == null || loginedPro.getTelefono() == null || loginedPro.getSito() == null
					|| loginedPro.getIndirizzo() == null) {
				profiloCompleto = false;
			}

			appuntamentiDaEffettuare = app_dao.findAppuntamentiDaEffettuare(loginedPro.getMail()).size();
			List<Cliente> clientiPrenotati = pro_dao.findTuttiPrenotati(loginedPro.getMail());
			List<Appuntamento> appuntamentiOdierni = pro_dao.findAppuntamentiOdierni(loginedPro.getMail());
			List<Appuntamento> appuntamentiRecenti = pro_dao.findAppuntamentiCreatiOggi(loginedPro.getMail());
			List<Valutazione> valutazioniRecenti = pro_dao.findRecensioniRecenti(loginedPro.getMail());
			List<Valutazione> valutazioni = pro_dao.findRecensioni(loginedPro.getMail());

			clienti = clientiPrenotati.size();
			appuntamentiDaEffettuare = app_dao.findAppuntamentiDaEffettuare(loginedPro.getMail()).size();
			rating = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao()
					.findMediaValutazioni(loginedPro.getMail());
			numVal = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao()
					.findNumeroValutazioniRicevute(loginedPro.getMail());

			request.setAttribute("profiloCompleto", profiloCompleto);
			request.setAttribute("valutazioni", valutazioni);
			request.setAttribute("valutazioniRecenti", valutazioniRecenti);
			request.setAttribute("appuntamentiRecenti", appuntamentiRecenti);
			request.setAttribute("appuntamentiOdierni", appuntamentiOdierni);
			request.setAttribute("clienti", clienti);
			request.setAttribute("clientiPrenotati", clientiPrenotati);
			request.setAttribute("appuntamentiDaEffettuare", appuntamentiDaEffettuare);
			request.setAttribute("storicoValutazioni", appuntamentiDaEffettuare);
			request.setAttribute("rating", Math.round(rating));
			request.setAttribute("numVal", numVal);
			
			RequestDispatcher dispatcher = this.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/areaPersonaleView.jsp");
			dispatcher.forward(request, response);

		} else if (loginedClient != null) {

			ClienteDao dao = DataBaseManager.getInstance().getDaoFactory().getClienteDao();
			List<Appuntamento> appuntamentiDaValutare = dao.findAppuntamentiValutabili(loginedClient.getMail());
			List<Appuntamento> appuntamentiPrenotati = dao.findAppuntamentiFuturi(loginedClient.getMail());
			List<Appuntamento> prenotazioniOdierne = dao.findAppuntamentiOdierni(loginedClient.getMail());
			List<Valutazione> storicoValutazioni = DataBaseManager.getInstance().getDaoFactory().getClienteDao()
					.findStoricoValutazioni(loginedClient.getMail());

			request.setAttribute("prenotazioniOdierne", prenotazioniOdierne);
			request.setAttribute("storicoValutazioni", storicoValutazioni);
			request.setAttribute("numPrenotazioni", appuntamentiPrenotati.size());
			request.setAttribute("numValutazioni", storicoValutazioni.size());
			request.setAttribute("appuntamentiPrenotati", appuntamentiPrenotati);
			request.setAttribute("appuntamentiDaValutare", appuntamentiDaValutare);
			RequestDispatcher dispatcher = this.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/areaPersonaleClienteView.jsp");
			dispatcher.forward(request, response);

		} else
			response.sendRedirect(request.getContextPath() + "/login");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
