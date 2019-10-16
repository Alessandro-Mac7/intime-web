package controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import persistence.DataBaseManager;

public class AutocompleteSearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			List<String> suggerimenti = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao()
					.autocomplete();

			Type listType = new TypeToken<ArrayList<String>>() {
			}.getType();
			Gson gson = new Gson();
			
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(gson.toJson(suggerimenti, listType));
		
	}
}
