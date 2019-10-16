package controller.cliente;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.EmailUtility;
import controller.Utility;
import model.Appuntamento;
import model.Cliente;
import model.Prenotazione;
import persistence.DataBaseManager;

@WebServlet(urlPatterns = { "/PrenotaAppuntamento" })
public class PrenotaAppuntamentoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
        Cliente cliente = Utility.getLoginedClient(session);
 
        if (cliente == null) {
        	resp.sendError(400);
        	return;
        }
		
		Long codice_appuntamento = Long.parseLong(req.getParameter("codiceAppuntamento"));
		Appuntamento appuntamento = DataBaseManager.getInstance().getDaoFactory().getAppuntamentoDao().findByPrimaryKey(codice_appuntamento);
		
		ServletContext context = getServletContext();

		String host = context.getInitParameter("host");
		String port = context.getInitParameter("port");
		String user = context.getInitParameter("user");
		String pass = context.getInitParameter("pass");
		
		 
		
		Prenotazione prenotazione = new Prenotazione(cliente.getMail(), codice_appuntamento);
		
		if (DataBaseManager.getInstance().getDaoFactory().getPrenotazioneDao()
				.findByPrimaryKey(cliente.getMail(), codice_appuntamento) == null) {
			DataBaseManager.getInstance().getDaoFactory().getPrenotazioneDao().save(prenotazione);
		}
		
		try {
            EmailUtility.sendNotificationClient(host, port, user, pass, cliente, appuntamento, false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}
