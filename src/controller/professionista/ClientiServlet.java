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

import com.google.gson.Gson;

import controller.Utility;
import model.Appuntamento;
import model.Cliente;
import model.Professionista;
import model.Valutazione;
import persistence.DataBaseManager;
import persistence.dao.ProfessionistaDao;

/**
 * Servlet implementation class ClientiServlet
 */
@WebServlet("/clienti")
public class ClientiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		HttpSession session = request.getSession();

		Professionista loginedPro = Utility.getLoginedPro(session);
		
		ProfessionistaDao pro_dao = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao();

		List<Cliente> clientiPrenotati = pro_dao.findTuttiPrenotati(loginedPro.getMail());

		request.setAttribute("clienti", clientiPrenotati);
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/clientiView.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if (request.getParameter("type").equals("show")) {
			
			HttpSession session = request.getSession();
			String mail = Utility.getLoginedPro(session).getMail();
			String codice = request.getParameter("mail");
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			List<Appuntamento> appuntamenti = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().findTuttiAppuntamentiPrenotatiSingoloCliente(codice, mail);
			List<Valutazione> valutazioni = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().findTutteValutazioniSingoloCliente(codice, mail);
			
			Gson gson = new Gson();

			String appGson = gson.toJson(appuntamenti);
			String valGson = gson.toJson(valutazioni);
		
			String arrayGson = "[" + appGson + "," + valGson + "]";

			response.getWriter().write(arrayGson);
		}
	}

}
