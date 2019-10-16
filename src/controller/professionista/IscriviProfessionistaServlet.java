package controller.professionista;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Professionista;
import persistence.DataBaseManager;

@WebServlet(urlPatterns = { "/iscriviProfessionista" })
public class IscriviProfessionistaServlet extends HttpServlet {

	private static final long serialVersionUID = 4L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/homeView.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Professionista professionista = new Professionista();
		professionista.setMail(req.getParameter("mail"));
		professionista.setPassword(req.getParameter("password"));
		professionista.setNome(req.getParameter("nome"));
		professionista.setCognome(req.getParameter("cognome"));
		professionista.setProfessione(req.getParameter("professione"));
		professionista.setProfessione(req.getParameter("indirizzo"));
		
		if(req.getParameter("sito").trim()!="")
			professionista.setDescrizione(req.getParameter("sito"));
		if(req.getParameter("telefono").trim()!="")
			professionista.setDescrizione(req.getParameter("telefono"));
		

		
		
		DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().save(professionista);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/homeView.jsp");
		dispatcher.forward(req, resp);
	}
}
