package controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Appuntamento;
import model.Professionista;
import model.Valutazione;
import persistence.DataBaseManager;
import persistence.dao.ProfessionistaDao;

/**
 * Servlet implementation class DataServlet
 */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataServlet() {
		super();
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

		if (request.getParameter("makedata").equals("search")) {
			String query = request.getParameter("queryString");

			List<Professionista> professionisti = new ArrayList<Professionista>();
			professionisti = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao()
					.findByCognomeProfessione(query);

			Type listType = new TypeToken<ArrayList<Professionista>>() {
			}.getType();
			Gson gson = new Gson();

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gson.toJson(professionisti, listType));
		}

		if (request.getParameter("makedata").equals("recenti")) {

			HttpSession session = request.getSession();
			String mail = Utility.getLoginedPro(session).getMail();

			List<Appuntamento> appuntamentiRecenti = new ArrayList<Appuntamento>();
			appuntamentiRecenti = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao()
					.findAppuntamentiCreatiOggi(mail);

			Type listType = new TypeToken<ArrayList<Appuntamento>>() {
			}.getType();
			Gson gson = new Gson();

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gson.toJson(appuntamentiRecenti, listType));
		}

		if (request.getParameter("makedata").equals("chart"))

		{

			HttpSession session = request.getSession();
			String mail = Utility.getLoginedPro(session).getMail();

			LinkedList<Integer> rating = new LinkedList<>();

			rating.add(DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().findRating(mail, 1));
			rating.add(DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().findRating(mail, 2));
			rating.add(DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().findRating(mail, 3));
			rating.add(DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().findRating(mail, 4));
			rating.add(DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().findRating(mail, 5));

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(rating));

		}

		if (request.getParameter("makedata").equals("odierni")) {

			HttpSession session = request.getSession();
			String mail = Utility.getLoginedPro(session).getMail();

			ProfessionistaDao pro_dao = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao();
			List<Appuntamento> appuntamentiOdierni = pro_dao.findAppuntamentiOdierni(mail);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			Type listType = new TypeToken<LinkedList<Appuntamento>>() {
			}.getType();

			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(appuntamentiOdierni, listType));

		}

		if (request.getParameter("makedata").equals("valutazioni")) {

			HttpSession session = request.getSession();
			String mail = Utility.getLoginedPro(session).getMail();

			ProfessionistaDao pro_dao = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao();
			List<Valutazione> valutazioni = pro_dao.findRecensioni(mail);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			Type listType = new TypeToken<LinkedList<Valutazione>>() {
			}.getType();

			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(valutazioni, listType));

		}

		if (request.getParameter("makedata").equals("valutazioni-recenti")) {

			HttpSession session = request.getSession();
			String mail = Utility.getLoginedPro(session).getMail();

			ProfessionistaDao pro_dao = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao();
			List<Valutazione> valutazioniRecenti = pro_dao.findRecensioniRecenti(mail);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			Type listType = new TypeToken<LinkedList<Valutazione>>() {
			}.getType();

			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(valutazioniRecenti, listType));

		}

		if (request.getParameter("makedata").equals("statisticheCliente")) {
			HttpSession session = request.getSession();
			String mail = Utility.getLoginedClient(session).getMail();

			Type type = new TypeToken<Map<String, Integer>>() {
			}.getType();
			Map<String, Integer> map = DataBaseManager.getInstance().getDaoFactory().getClienteDao()
					.findPercentualeAppuntamenti(mail);

			Gson gson = new Gson();

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			response.getWriter().write(gson.toJson(map, type));
		}

		if (request.getParameter("makedata").equals("topClienti")) {
			HttpSession session = request.getSession();
			String mail = Utility.getLoginedPro(session).getMail();

			Type type = new TypeToken<Map<String, Integer>>() {
			}.getType();
			Map<String, Integer> map = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao()
					.findTopClienti(mail);

			Gson gson = new Gson();

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			response.getWriter().write(gson.toJson(map, type));
		}

		if (request.getParameter("makedata").equals("calendar")) {

			HttpSession session = request.getSession();
			String mail = Utility.getLoginedPro(session).getMail();

			List<Appuntamento> appuntamenti = DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao()
					.findByProfessionista(mail);

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			Gson gson = new Gson();

			response.getWriter().write(gson.toJson(appuntamenti));
		}

	}

}
