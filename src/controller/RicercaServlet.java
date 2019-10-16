package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Professionista;
import persistence.DataBaseManager;

@WebServlet(urlPatterns = { "/ricerca" })
public class RicercaServlet extends HttpServlet {

	private static final long serialVersionUID = 3L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String toUpper = req.getParameter("ricerca");
		toUpper = toUpper.toUpperCase();

		List<Professionista> professionisti = new ArrayList<Professionista>();
		professionisti = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao()
				.findByCognomeProfessione(toUpper);
		
		if (professionisti.isEmpty()) {
			professionisti = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().findAll();
		}
		
		req.setAttribute("ricercaVal", toUpper);
		req.setAttribute("professionisti", professionisti);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/ricercaView.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
