package controller.professionista;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import controller.Utility;
import javafx.scene.chart.PieChart.Data;
import model.Professionista;
import persistence.DataBaseManager;

/**
 * Servlet implementation class ProfiloServlet
 */
@WebServlet("/profilo")
public class ProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/profiloView.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Professionista pro = Utility.getLoginedPro(session);
		// chiamata per avere la posizione del professionista da mettere sulla mappa
		if (request.getParameter("type").equals("posizione")) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String posizione = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao()
					.findByMail(pro.getMail()).getIndirizzo();
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(posizione));
		}

		if (request.getParameter("type").equals("edit")) {
			
			response.setCharacterEncoding("UTF-8");
			
			Professionista editPro = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().findByMail(pro.getMail());
			String professione = request.getParameter("professione");
			String indirizzo = request.getParameter("indirizzo");
			String telefono = request.getParameter("telefono");
			String sito = request.getParameter("sito");
			String descrizione = request.getParameter("descrizione");

			
			if(professione!="") {
				editPro.setProfessione(professione);
				pro.setProfessione(professione);
			}
			if(indirizzo!="") {
				editPro.setIndirizzo(indirizzo);
				pro.setIndirizzo(indirizzo);
			}
			if(telefono!="") {
				editPro.setTelefono(telefono);
				pro.setTelefono(telefono);
			}
			if(sito!="") {
				editPro.setSito(sito);
				pro.setSito(sito);
			}
			if(descrizione!="") {
				editPro.setSito(descrizione);
				pro.setSito(descrizione);
			}

			
			
			DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().update(editPro);

		}
		
	}

}
