package fr.vannes.gretajavafx.util;


import fr.vannes.gretajavafx.Setting;
import javafx.application.Platform;
import fr.vannes.gretajavafx.controller.HomeController;
import javafx.application.Platform;
import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe de gestion de l'envoi du courriel
 */
public class SendMail implements Runnable {

   /**
    * String recipient : email utilisateur
    */
   private final String recipient;

   public SendMail(String recipient) {
      this.recipient = recipient;
   }

   /**
    * Méthode d'envoi du courriel
    *
    * @return reponse de l'API brevo suite à l'envoi
    * @throws Exception échec de l'envoi
    */
   public CreateSmtpEmail checkMail() throws Exception {
      try {
      ApiClient defaultClient = sendinblue.Configuration.getDefaultApiClient();
      // Configure API key authorization: api-key
      ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
      apiKey.setApiKey(Setting.API_KEY);

      TransactionalEmailsApi api = new TransactionalEmailsApi();
      SendSmtpEmailSender sender = new SendSmtpEmailSender();
      sender.setEmail(Setting.FROM_MAIL);
      sender.setName(Setting.FROM_MAIL_NAME);
      List<SendSmtpEmailTo> toList = new ArrayList<>();
      SendSmtpEmailTo to = new SendSmtpEmailTo();
      to.setEmail(Setting.TO_EMAIL);
      to.setName(Setting.TO_EMAIL_NAME);
      toList.add(to);
      List<SendSmtpEmailCc> ccList = new ArrayList<>();
      SendSmtpEmailCc cc = new SendSmtpEmailCc();
      cc.setEmail(Setting.TO_EMAIL_CC);
      cc.setName(Setting.TO_EMAIL_NAME_CC);
      ccList.add(cc);
      List<SendSmtpEmailBcc> bccList = new ArrayList<>();
      SendSmtpEmailBcc bcc = new SendSmtpEmailBcc();
      bcc.setEmail(Setting.TO_EMAIL_BCC);
      bcc.setName(Setting.TO_EMAIL_NAME_BCC);
      bccList.add(bcc);
      SendSmtpEmailReplyTo replyTo = new SendSmtpEmailReplyTo();
      replyTo.setEmail(Setting.REPLY_EMAIL);
      replyTo.setName(Setting.REPLY_EMAIL_NAME);
      SendSmtpEmailAttachment attachment = new SendSmtpEmailAttachment();
      attachment.setName("test.jpg");
      byte[] encode = Files.readAllBytes(Paths.get("images/test.jpg"));
      attachment.setContent(encode);
      List<SendSmtpEmailAttachment> attachmentList = new ArrayList<>();
      attachmentList.add(attachment);
      //  Properties headers = new Properties();
      //headers.setProperty("Some-Custom-Name", "unique-id-1234");
      // Properties params = new Properties();
      //params.setProperty("parameter", "My param value");
      // params.setProperty("subject", Constantes.SUBJECT_EMAIL);
      SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
      sendSmtpEmail.setSender(sender);
      sendSmtpEmail.setTo(toList);
      sendSmtpEmail.setCc(ccList);
      sendSmtpEmail.setBcc(bccList);
      sendSmtpEmail.setHtmlContent(Setting.BODY_EMAIL);
      sendSmtpEmail.setSubject(Setting.SUBJECT_EMAIL);
      sendSmtpEmail.setReplyTo(replyTo);
      sendSmtpEmail.setAttachment(attachmentList);
      // sendSmtpEmail.setHeaders(headers);
      //sendSmtpEmail.setParams(params);

         // retour de la réponse de l'envoi
         return api.sendTransacEmail(sendSmtpEmail);
      } catch (Exception e) {
         throw new Exception("Erreur d'envoi du courriel");
      }
   }

   /**
    * Thread pour l'éxecution de l'envoi du courriel
    */
   @Override
   public void run() {
      try {
         this.checkMail();
         // on attend l'envoi du mail pour afficher le succès ou l'erreur par une dialog
         Platform.runLater(() -> HomeController.getInstance().successAlert("Envoi réussi", "L'envoi du courriel comportant les données de votre recherche est un succès."));
      } catch (Exception e) {
         Platform.runLater(() -> HomeController.getInstance().errorAlert("L'envoi a échoué", "Une erreur est survenue lors de l'envoi du courriel, veuillez réessayer."));
      }
   }
}