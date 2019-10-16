package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Cliente;
import model.Professionista;
import persistence.DataBaseManager;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */
@WebFilter(filterName = "autoLoginFilter", urlPatterns = { "/*" })
public class AutoLoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AutoLoginFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		Cliente clientInSession = Utility.getLoginedClient(session);
		Professionista proInSession = Utility.getLoginedPro(session);

		if (proInSession != null) {
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
			chain.doFilter(request, response);
			return;
		}

		if (clientInSession != null) {
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
			chain.doFilter(request, response);
			return;
		}

		String checked = (String) session.getAttribute("COOKIE_CHECKED");
		if (checked == null) {

			String mail = Utility.getMailStoredInCookie(req);
			Professionista pro = DataBaseManager.getInstance().getDaoFactory().getProfessionistaDao().findByMail(mail);
			if (pro != null) {
				Utility.storeLoginedPro(session, pro);
			}
			else {
				Cliente client = DataBaseManager.getInstance().getDaoFactory().getClienteDao().findByMail(mail);
				Utility.storeLoginedClient(session, client);
			}
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
