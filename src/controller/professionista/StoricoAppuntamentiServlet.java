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
import model.Professionista;
import persistence.DataBaseManager;

/**
 * Servlet implementation class StoricoAppuntamentiServlet
 */
@WebServlet("/storicoAppuntamenti")
public class StoricoAppuntamentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoricoAppuntamentiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Professionista pro = Utility.getLoginedPro(session);

		List<Appuntamento> appuntamentiDaEffettuare = DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao()
				.findAppuntamentiDaEffettuare(pro.getMail());

		List<Appuntamento> appuntamentiEffettuati = DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao()
		.findAppuntamentiEffettuati(pro.getMail());

		request.setAttribute("appuntamentiDaEffettuare", appuntamentiDaEffettuare);
		request.setAttribute("appuntamentiEffettuati", appuntamentiEffettuati);
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/storicoAppuntamentiView.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
