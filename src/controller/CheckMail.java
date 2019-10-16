package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.DataBaseManager;
import persistence.dao.ClienteDao;
import persistence.dao.ProfessionistaDao;

/**
 * Servlet implementation class CheckMail
 */
@WebServlet("/check")
public class CheckMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckMail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String professione = request.getParameter("professione");
		
		if(professione!=null) {
			String mailPro = request.getParameter("mail");
			ProfessionistaDao dao = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao();
			if(dao.findByMail(mailPro)!=null) {
				response.getWriter().write("false");
				return;
			}
		}
		else {
			String mail = request.getParameter("mail");
			ClienteDao dao = DataBaseManager.getInstance().getDaoFactory().getClienteDao();
			if(dao.findByMail(mail)!=null) {
				response.getWriter().write("false");
				return;
			}
		}
		response.getWriter().write("true");
	}

}
