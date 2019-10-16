package controller.professionista;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import controller.EmailUtility;
import controller.Utility;
import model.Appuntamento;
import model.Cliente;
import model.Professionista;
import persistence.DataBaseManager;

/**
 * Servlet implementation class AppuntamentoServlet
 */
@WebServlet("/appuntamento")
public class AppuntamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AppuntamentoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("type").equals("show")) {

			HttpSession session = request.getSession();
			String mail = Utility.getLoginedPro(session).getMail();
			Long codice = Long.parseLong(request.getParameter("codiceAppuntamento"));

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			Appuntamento app = DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao()
					.findByPrimaryKey(codice);
			List<Cliente> pre = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao()
					.findTuttiPrenotatiSingoloAppuntamento(mail, codice);
			
			Gson gson = new Gson();

			String appGson = gson.toJson(app);
			String preGson = gson.toJson(pre);

			String arrayGson = "[" + appGson + "," + preGson + "]";

			response.getWriter().write(arrayGson);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("type").equals("edit")) {

			String parameter = request.getParameter("codiceAppuntamento");

			if (parameter == null) {
				response.sendError(400);
				return;
			}

			HttpSession session = request.getSession();

			Professionista pro = Utility.getLoginedPro(session);

			Long codice = Long.parseLong(parameter);

			ServletContext context = getServletContext();

			String host = context.getInitParameter("host");
			String port = context.getInitParameter("port");
			String user = context.getInitParameter("user");
			String pass = context.getInitParameter("pass");

			Appuntamento appuntamento = DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao()
					.findByPrimaryKey(codice);

			String dataString = request.getParameter("data");
			String timeInitString = request.getParameter("ora");
			String timeEndString = request.getParameter("oraFine");
			String descrizione = request.getParameter("descrizione");
			String clienti = request.getParameter("clienti");

			DateTimeFormatter formatter = null;
			LocalDate date = null;
			LocalTime timeInit = null;
			LocalTime timeEnd = null;

			if (dataString.trim() != "") {
				formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				date = LocalDate.parse(dataString, formatter);
				appuntamento.setData(date);
			}
			if (timeInitString.trim() != "") {
				timeInit = LocalTime.parse(timeInitString);
				appuntamento.setOra_inizio(timeInit);
			}
			if (timeEndString.trim() != "") {
				timeEnd = LocalTime.parse(timeEndString);
				appuntamento.setOra_fine(timeEnd);
			}
			if (descrizione.trim() != "") {
				appuntamento.setDescrizione(descrizione);
			}
			if (clienti.trim() != "") {
				appuntamento.setNumero_clienti(Integer.parseInt(clienti));
			}

			appuntamento.setCodice(codice);
			appuntamento.setMail_professionista(pro.getMail());

			List<Cliente> prenotati = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao()
					.findTuttiPrenotatiSingoloAppuntamento(pro.getMail(), codice);

			DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao().update(appuntamento);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			Gson gson = new Gson();

			if (dataString.trim() != "" && timeInitString.trim() != "" && timeEndString.trim() != "") {
				if (DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao()
						.controlloInserimento(pro.getMail(), date, timeInit, timeEnd).isEmpty()) {
					DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao().save(appuntamento);
					try {
						EmailUtility.sendNotificationEmail(host, port, user, pass, prenotati, appuntamento, false);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					response.getWriter().write(gson.toJson(appuntamento));
					return;
				} else {
					response.sendError(400);
					return;
				}
			} else {
				try {
					EmailUtility.sendNotificationEmail(host, port, user, pass, prenotati, appuntamento, false);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				response.getWriter().write(gson.toJson(appuntamento));

			}
		}

		if (request.getParameter("type").equals("delete")) {

			String parameter = request.getParameter("codiceAppuntamento");

			if (parameter == null) {
				response.sendError(400);
				return;
			}

			HttpSession session = request.getSession();

			String mail = Utility.getLoginedPro(session).getMail();

			ServletContext context = getServletContext();

			String host = context.getInitParameter("host");
			String port = context.getInitParameter("port");
			String user = context.getInitParameter("user");
			String pass = context.getInitParameter("pass");

			Long codice = Long.parseLong(parameter);

			Appuntamento appuntamento = DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao()
					.findByPrimaryKey(codice);
			List<Cliente> clienti = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao()
					.findTuttiPrenotatiSingoloAppuntamento(mail, codice);

			DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao().delete(codice);

			try {
				EmailUtility.sendNotificationEmail(host, port, user, pass, clienti, appuntamento, true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
	}

}
