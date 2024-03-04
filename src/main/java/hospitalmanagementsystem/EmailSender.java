package hospitalmanagementsystem;
import com.sun.mail.util.MailConnectException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    public static void main(String[] args) {
        String recipientEmail = "piroukoffi@gmail.com"; // adresse e-mail du destinataire
        String emailSubject = "Sujet de l'e-mail";
        String emailBody = "Contenu de l'e-mail";

        sendEmail(recipientEmail, emailSubject, emailBody);
    }
    public static void PreparationEmail(String message,String value) throws SQLException {
        // envoi de l'email au client
        Connection cn = Database.connectDB();// connexion a la base de donnée
        Statement stmt2= cn.createStatement();
        Statement stmt3= cn.createStatement();



        String username = "",password = "",destinataireEmail=value,BodyLetter="",reponse="",sql1="",sql2="";
        if (message.equals("admin")) {
            sql1 = "SELECT username FROM admin WHERE email='" + value + "'";
            sql2 = "SELECT password FROM admin WHERE email='" + value + "'";
            reponse = "username";
        }
        else if (message.equals("doctor")) {
            sql1 = "SELECT doctor_id FROM doctor WHERE email='" + value + "'";
            sql2 = "SELECT password FROM doctor WHERE email='" + value + "'";
            reponse = "doctor_id";
        }
        try (


                ResultSet user= stmt2.executeQuery(sql1);
             ResultSet pass = stmt3.executeQuery(sql2);)

                {

            if (user.next()) {

                username = user.getString(reponse);// recuperation de la colonne choisie
            }
              if (pass.next()) {
                password = pass.getString("password");// recuperation de la colonne password
            }
             else{
                 System.out.println("echec");
            }// recuperation de la colonne email


            String ObjetEmail=" Cabinet medical ";
            switch (message){

                // Pour mot de passe oublié
                case"admin":
                    // select de la colonne password :


                    BodyLetter="votre ursername est : "+username+" \n votre mot de passe est :"+password;
                    System.out.println(" mot de passe renitialise");
                    break;


                case "doctor":
                    BodyLetter="Votre ID est: "+username+"\n Votre mot de passe est :  "+password;
                    break;

            }
            sendEmail(destinataireEmail,ObjetEmail , BodyLetter);// envoie de l'email


            // Reste du code pour l'envoi d'email
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cn.close();// fermeture de la connexion
    }

    public static void sendEmail(String recipientEmail, String subject, String body) {
        final String senderEmail = "cd5031843@gmail.com"; // Remplacez par votre propre adresse e-mail
        final String senderPassword = "t l p h d h n f i y p u o q d h"; //votre mot de passe d'application généré sous l'onglet sécurité de google

        // Configuration des propriétés pour la connexion au serveur SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Remplacez par l'adresse du serveur SMTP
        properties.put("mail.smtp.port", "587"); // Port SMTP

        // Création d'une session de messagerie avec authentification
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        AlertMessage alert =new AlertMessage();

        try {

            // Création du message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            // Envoi du message
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès !");

            alert.successMessage("E-mail envoyé avec succès !");
        } catch (MessagingException e) {
            if (e instanceof MailConnectException) {
                alert.errorMessage(" Vérifiez votre connexion Internet.");
            }

            else{
            System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
            alert.errorMessage("Erreur lors de l'envoi de l'e-mail : "+e.getMessage());}
            e.printStackTrace();
        }

    }
}

