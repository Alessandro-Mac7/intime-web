package controller.cliente;

import java.io.IOException;
import java.time.LocalDate;

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
import model.Valutazione;
import persistence.DataBaseManager;

/**
 * Servlet implementation class PrenotazioneClienteServlet
 */
@WebServlet("/prenotazione-cliente")
public class PrenotazioneClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("type").equals("show")) {
			Long codice = Long.parseLong(request.getParameter("codiceAppuntamento"));

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			Appuntamento app = DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao()
					.findByPrimaryKey(codice);
			int prenotati = DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao().findNumeroPrenotati(codice);
			
			Gson gson = new Gson();

			String appGson = gson.toJson(app);
			String preGson = gson.toJson(prenotati);
			
			String arrayGson = "[" + appGson + "," + preGson + "]";

			response.getWriter().write(arrayGson);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("type").equals("delete")) {

			HttpSession session = request.getSession();
			Cliente loginedClient = Utility.getLoginedClient(session);

			String parameter = request.getParameter("codiceAppuntamento");

			if (parameter == null) {
				response.sendError(400);
				return;
			}
			
			ServletContext context = getServletContext();

			String host = context.getInitParameter("host");
			String port = context.getInitParameter("port");
			String user = context.getInitParameter("user");
			String pass = context.getInitParameter("pass");
			
			Long codice = Long.parseLong(parameter);
			Appuntamento appuntamento = DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao().findByPrimaryKey(codice);

			DataBaseManager.getInstance().getDaoFactory().getPrenotazioneDao().delete(loginedClient.getMail(), codice);
			
			try {
	            EmailUtility.sendNotificationClient(host, port, user, pass, loginedClient, appuntamento, true);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

		}

		if (request.getParameter("type").equals("new-valutazione")) {

			HttpSession session = request.getSession();
			Cliente loginedClient = Utility.getLoginedClient(session);

			String parameter = request.getParameter("codiceAppuntamento");

			if (request.getParameter("descrizione") == null || request.getParameter("descrizione").isEmpty()
					|| request.getParameter("rating") == null || request.getParameter("rating").isEmpty()) {
				response.sendError(400);
				return;
			}
			
			Long codice= Long.parseLong(parameter);
			String descrizione = request.getParameter("descrizione");
			int rating = Integer.parseInt(request.getParameter("rating"));
			LocalDate data = LocalDate.now();
			
			Valutazione valutazione = new Valutazione();
			valutazione.setDescrizione(descrizione);
			valutazione.setCodice_appuntamento(codice);
			valutazione.setData(data);
			valutazione.setMail_cliente(loginedClient.getMail());
			valutazione.setRating(rating);
			

			DataBaseManager.getInstance().getDaoFactory().getValutazioneDao().save(valutazione);

		}

	}

}
