package controller.professionista;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Utility;
import model.Appuntamento;
import model.Professionista;
import persistence.DataBaseManager;

/**
 * Servlet implementation class InserisciAppuntamentoServlet
 */
@WebServlet("/inserisciAppuntamento")
public class InserisciAppuntamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InserisciAppuntamentoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getParameter("data") == null || request.getParameter("data").isEmpty()
				|| request.getParameter("oraInizio") == null || request.getParameter("oraInizio").isEmpty()
				|| request.getParameter("oraFine") == null || request.getParameter("oraFine").isEmpty()
				) {
			response.sendError(400);
			return;
		}
		
		HttpSession session = request.getSession();

		Professionista loginedPro = Utility.getLoginedPro(session);

		Appuntamento appuntamento = new Appuntamento();
		appuntamento.setMail_professionista(loginedPro.getMail());

		String dataString = request.getParameter("data");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(dataString, formatter);

		String timeInitString = request.getParameter("oraInizio");
		String timeEndString = request.getParameter("oraFine");

		LocalTime timeInit = LocalTime.parse(timeInitString);
		LocalTime timeEnd = LocalTime.parse(timeEndString);

		String descrizione = request.getParameter("descrizione");
		int clienti = Integer.parseInt(request.getParameter("clienti"));

		appuntamento.setData(date);
		appuntamento.setOra_inizio(timeInit);
		appuntamento.setOra_fine(timeEnd);
		appuntamento.setDescrizione(descrizione);
		appuntamento.setNumero_clienti(clienti);
		DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao().save(appuntamento);



	}

}
