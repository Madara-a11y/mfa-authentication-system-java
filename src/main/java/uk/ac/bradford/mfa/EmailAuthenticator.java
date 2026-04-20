/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.bradford.mfa;

/**
 *
 * @author maxim
 */
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailAuthenticator implements MFAStrategy {
    private String lastGeneratedCode;

    @Override
public void sendCode(String recipientEmail) {
    // 1. YOUR REAL DETAILS (Use the 16-char App Password here)
    final String senderEmail = "sellermk39@gmail.com"; 
    final String appPassword = "bfljgoqukybcidbw"; 

    // 2. SMTP Server Settings for Gmail
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true"); // Required for security
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587"); // The standard TLS port

    // 3. Create the Session with Authentication
    Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmail, appPassword);
        }
    });

    try {
        // 4. Construct the Email
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("University of Bradford: Secure MFA Code");

        // Generate a real random 6-digit code
        this.lastGeneratedCode = String.format("%06d", (int)(Math.random() * 1000000));
        
        message.setText("Welcome to the University of Bradford Portal.\n\n" +
                        "Your verification code is: " + lastGeneratedCode + "\n\n" +
                        "If you did not request this, please contact IT Services.");

        // 5. Send it!
        System.out.println("[SYSTEM] Connecting to Gmail SMTP...");
        Transport.send(message);
        System.out.println("[SUCCESS] Real Email sent to " + recipientEmail);

    } catch (MessagingException e) {
        System.out.println("[ERROR] Gmail SMTP Error: " + e.getMessage());
        System.out.println("Tip: Ensure 2-Step Verification is ON and you are using an App Password.");
    }
}

    @Override
    public boolean verifyCode(String inputCode) {
        return inputCode != null && inputCode.equals(lastGeneratedCode);
    }

    @Override
    public String getMethodName() { return "SMTP Email Service"; }
}