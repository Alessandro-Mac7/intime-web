package controller.cliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import controller.Utility;
import model.Appuntamento;
import model.Cliente;
import persistence.DataBaseManager;

@WebServlet("/storicoPrenotazioni")
public class StoricoPrenotazioniServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Cliente cliente = Utility.getLoginedClient(session);

		List<Appuntamento> storicoAppuntamenti = DataBaseManager.getInstance().getDaoFactory().getClienteDao()
				.findStoricoAppuntamenti(cliente.getMail());

		Type listType = new TypeToken<ArrayList<Appuntamento>>() {
		}.getType();
		Gson gson = new Gson();
		// System.out.println(gson.toJson(storicoAppuntamenti,listType));

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(gson.toJson(storicoAppuntamenti, listType));

	}
}
