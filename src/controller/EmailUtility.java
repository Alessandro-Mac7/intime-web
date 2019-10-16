package controller;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.Appuntamento;
import model.Cliente;

public class EmailUtility {

	public static void sendNotificationEmail(String host, String port, final String userName, final String password,
			List<Cliente> clienti, Appuntamento appuntamento, boolean delete)
			throws AddressException, MessagingException {

		// Proprietà SMTP
		Properties properties = new Properties();

		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// Nuova Sessione per l'Authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		String toAddress = "";
		String subject = "";
		String message = "";

		if (delete) {
			subject = "Notifica di cancellazione Appuntamento";
			message = "Si notifica che il Professionista " + appuntamento.getMail_professionista()
					+ " ha cancellato il seguente appuntamento: " + '\n' + appuntamento.getDescrizione() + "in data: "
					+ appuntamento.getData() + " delle ore: " + appuntamento.getOra_inizio() + " a "
					+ appuntamento.getOra_fine();
		} else {
			subject = "Notifica di modifica Appuntamento";
			message = "Si notifica che il Professionista " + appuntamento.getMail_professionista()
					+ " ha modificato il seguente appuntamento: " + '\n' + appuntamento.getDescrizione() + "in data: "
					+ appuntamento.getData() + " delle ore: " + appuntamento.getOra_inizio() + " a "
					+ appuntamento.getOra_fine();

		}

		for (Cliente cliente : clienti) {
			toAddress += cliente.getMail() + ", ";
		}

		msg.setFrom(new InternetAddress(userName));

		InternetAddress[] toAddresses = InternetAddress.parse(toAddress, true);
		;
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(message);

		Transport.send(msg);

	}

	public static void sendNotificationClient(String host, String port, final String userName, final String password,
			Cliente cliente, Appuntamento appuntamento, boolean delete) throws AddressException, MessagingException {

		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getInstance(properties, auth);

		Message msg = new MimeMessage(session);

		String toAddress = "";
		String subject = "";
		String message = "";

		if (delete) {
			subject = "Notifica di cancellazione Prenotazione";
			message = "Si notifica che il Cliente " + cliente.getNome() + " " + cliente.getCognome() + " telefono: "
					+ cliente.getTelefono() + "  ha cancellato la prenotazione al seguente appuntamento: " + '\n' + '\n'
					+ "Codice: " + appuntamento.getCodice() + appuntamento.getDescrizione() + "in data: "
					+ appuntamento.getData() + " delle ore: " + appuntamento.getOra_inizio() + " a "
					+ appuntamento.getOra_fine();
		} else {
			subject = "Notifica di prenotazione Appuntamento";
			message = "Si notifica che il Cliente: " + cliente.getNome() + " " + cliente.getCognome() + " telefono: "
					+ cliente.getTelefono() + " si  è prenotato al seguente appuntamento:" + '\n' + '\n' + "Codice: "
					+ appuntamento.getCodice() + " " + appuntamento.getDescrizione() + "in data: "
					+ appuntamento.getData() + " delle ore: " + appuntamento.getOra_inizio() + " a "
					+ appuntamento.getOra_fine();

		}

		msg.setFrom(new InternetAddress(userName));
		toAddress = appuntamento.getMail_professionista();
		InternetAddress[] toAddresses = InternetAddress.parse(toAddress, true);
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(message);

		Transport.send(msg);
	}
}
