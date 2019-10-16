package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cliente;
import model.Professionista;

public class Utility {

	private static final String ATTRIBUTE_MAIL = "MAIL_STORED_IN_COOKIE";
	private static final String LAST_QUERY = "LAST_QUERY_STORED_IN_COOKIE";

	// Salva le informazioni dei Clienti nella Session
	public static void storeLoginedClient(HttpSession session, Cliente loginedClient) {
		// accesso a JSP via ${loginedClient}
		session.setAttribute("loginedClient", loginedClient);
	}

	// Salva le informazioni dei Professionisti nella Session
	public static void storeLoginedPro(HttpSession session, Professionista loginedPro) {
		session.setAttribute("loginedPro", loginedPro);
	}

	// Restituisce le informazioni dei Clienti salvati nella Sessione
	public static Cliente getLoginedClient(HttpSession session) {
		Cliente loginedClient = (Cliente) session.getAttribute("loginedClient");
		return loginedClient;
	}

	// Restituisce le informazioni dei Professionisti salvati nella Sessione
	public static Professionista getLoginedPro(HttpSession session) {
		Professionista loginedPro = (Professionista) session.getAttribute("loginedPro");
		return loginedPro;
	}

	// Salva le info nei Cookie
	public static void storeClientCookie(HttpServletResponse response, Cliente client) {
		Cookie cookieMail = new Cookie(ATTRIBUTE_MAIL, client.getMail());
		// Le cookie vengono salvate per un giorno
		cookieMail.setMaxAge(24 * 60 * 60);
		response.addCookie(cookieMail);
	}

	// Salva le info nei Cookie
	public static void storeProCookie(HttpServletResponse response, Professionista pro) {
		Cookie cookieMail = new Cookie(ATTRIBUTE_MAIL, pro.getMail());
		cookieMail.setMaxAge(24 * 60 * 60);
		response.addCookie(cookieMail);
	}

	public static String getMailStoredInCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (ATTRIBUTE_MAIL.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	// Elimina Cookie
	public static void deleteCookie(HttpServletResponse response) {
		Cookie cookieMail = new Cookie(ATTRIBUTE_MAIL, null);
		// 0 secondi cioè le elimina subito
		cookieMail.setMaxAge(0);
		response.addCookie(cookieMail);
	}

}
