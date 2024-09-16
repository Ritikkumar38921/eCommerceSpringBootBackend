package com.alibou.ecommerce.email;

import static com.alibou.ecommerce.email.EmailTemplates.ORDER_CONFIRMATION;
import static com.alibou.ecommerce.email.EmailTemplates.PAYMENT_CONFIRMATION;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.alibou.ecommerce.config.EmailConfig;
import com.alibou.ecommerce.kafka.order.Product;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

@Service
public class EmailService {

	private static Logger log = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private EmailConfig emailConfig;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Async
	public void sendPaymentSuccessEmail(String destinationEmail, String customerName, BigDecimal amount,
			String orderReference) throws MessagingException {

		Properties propers = new Properties();

		propers.put("mail.smtp.auth", emailConfig.getAuth());
		propers.put("mail.smtp.host", emailConfig.getHost());
		propers.put("mail.smtp.port", emailConfig.getPort());
		propers.put("mail.smtp.starttls.enable", emailConfig.getEnable());
		propers.put("mail.smtp.ssl.protocols", emailConfig.getProtocols());
		propers.put("mail.smtp.starttls.required", emailConfig.getRequired());

		Session session = Session.getInstance(propers, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailConfig.getFrom(), emailConfig.getPassword());
			}
		});

		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(emailConfig.getFrom()));
		message.setSubject("Payment Confirmation");

		message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinationEmail));

		MimeBodyPart messageBodyPart = new MimeBodyPart();

		final String templateName = PAYMENT_CONFIRMATION.getTemplate();

		Map<String, Object> variables = new HashMap<>();
		variables.put("customerName", customerName);
		variables.put("amount", amount);
		variables.put("orderReference", orderReference);

		Context context = new Context();
		context.setVariables(variables);
		message.setSubject(PAYMENT_CONFIRMATION.getSubject());

		try {
			String htmlTemplate = templateEngine.process(templateName, context);
			messageBodyPart.setText(htmlTemplate, "UTF-8", "html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail,
					templateName));
		} catch (MessagingException e) {
			log.warn("WARNING - Cannot send Email to {} ", destinationEmail);
		}

	}

	@Async
	public void sendOrderConfirmationEmail(String destinationEmail, String customerName, BigDecimal amount,
			String orderReference, List<Product> products) throws MessagingException {

		Properties propers = new Properties();

		propers.put("mail.smtp.auth", emailConfig.getAuth());
		propers.put("mail.smtp.host", emailConfig.getHost());
		propers.put("mail.smtp.port", emailConfig.getPort());
		propers.put("mail.smtp.starttls.enable", emailConfig.getEnable());
		propers.put("mail.smtp.ssl.protocols", emailConfig.getProtocols());
		propers.put("mail.smtp.starttls.required", emailConfig.getRequired());

		Session session = Session.getInstance(propers, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailConfig.getFrom(), emailConfig.getPassword());
			}
		});

		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(emailConfig.getFrom()));
		message.setSubject(ORDER_CONFIRMATION.getSubject());

		message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinationEmail));

		MimeBodyPart messageBodyPart = new MimeBodyPart();

		final String templateName = ORDER_CONFIRMATION.getTemplate();

		Map<String, Object> variables = new HashMap<>();
		variables.put("customerName", customerName);
		variables.put("totalAmount", amount);
		variables.put("orderReference", orderReference);
		variables.put("products", products);

		Context context = new Context();
		context.setVariables(variables);

		try {
			String htmlTemplate = templateEngine.process(templateName, context);
			messageBodyPart.setText(htmlTemplate, "UTF-8", "html");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail,
					templateName));
		} catch (MessagingException e) {
			log.warn("WARNING - Cannot send Email to {} ", destinationEmail);
		}

	}
}
