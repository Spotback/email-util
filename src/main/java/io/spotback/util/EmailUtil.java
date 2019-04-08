package io.spotback.util;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mail.*;
import org.apache.commons.lang.text.StrSubstitutor;

public class EmailUtil {
    MailClient mailClient;
    MailMessage eMessage;
    MailConfig mailConfig;
    JsonObject config;
    Vertx vertx;

    public EmailUtil(Vertx vertx, JsonObject config) {
        this.vertx = vertx;
        this.config = config;
        mailConfig = new MailConfig();
        mailConfig.setHostname(config.getString(Constants.HOST));
        mailConfig.setPort(config.getInteger(Constants.PORT));
        mailConfig.setSsl(config.getBoolean(Constants.SSL));
        mailConfig.setLogin(LoginOption.REQUIRED);
        mailConfig.setUsername(config.getString("username"));
        mailConfig.setPassword(config.getString("password"));
        mailConfig.setAuthMethods("PLAIN");
    }

    private void setMessage(String email, String firstname, String messageKey, String subject) {
        eMessage = new MailMessage();
        eMessage.setFrom(config.getString("username"));
        eMessage.setTo(email);
        eMessage.setSubject(config.getString(subject));
        eMessage.setHtml(StrSubstitutor.replace(config.getString(messageKey), new JsonObject().put("email", email).put("firstName", firstname).getMap()));
    }


    private MailClient getEmailClient() {
        if (mailClient == null) {
            mailClient = MailClient.createNonShared(vertx, mailConfig);
            return mailClient;
        } else {
            return mailClient;
        }
    }

    public Future<String> sendEmail(String email, String firstname, String messageKey, String subject) {
        Future<String> status = Future.future();
        setMessage(email, firstname, messageKey, subject);
        getEmailClient().sendMail(eMessage, ar -> {
            if (ar.succeeded()) {
                status.complete(ar.result().getRecipients().toString());
            } else {
                status.fail(ar.cause().getMessage());
            }
            mailClient.close();
            mailClient = null;
        });
        return status;
    }

//    public static void main(String[] args) {
//        EmailUtil emailUtil = new EmailUtil(Vertx.vertx(), new JsonObject("{\n" +
//                "    \"hostname\" : \"smtp.gmail.com\",\n" +
//                "    \"port\" : 465,\n" +
//                "    \"starttls\" : null,\n" +
//                "    \"login\" : \"REQUIRED\",\n" +
//                "    \"username\" : \"spotbackteam@gmail.com\",\n" +
//                "    \"password\" : \"Spoticus19\",\n" +
//                "    \"ssl\" : true,\n" +
//                "    \"trustAll\" : true,\n" +
//                "    \"keyStore\" : null,\n" +
//                "    \"keyStorePassword\" : null,\n" +
//                "    \"authMethods\" : \"PLAIN\",\n" +
//                "    \"ownHostName\" : null,\n" +
//                "    \"maxPoolSize\" : 10,\n" +
//                "    \"keepAlive\" : true,\n" +
//                "    \"allowRcptErrors\" : false,\n" +
//                "    \"welcomeSubject\" : \"Verification Email from Spotback\",\n" +
//                "    \"newEmailSubject\" : \"Verification Email from Spotback\",\n" +
//                "    \"text\" : \"\",\n" +
//                "    \"validate.link\": \"https://localhost:8880/${email}\",\n" +
//                "    \"WELCOME\" : \"<h4>Dear ${firstName},</h4><p>Thank you for joining the Spotback Community! We are very excited to have you on board to solve the communities parking hassles. Please click this link to verify your account <a href=\\\"https://localhost:8880/validate/${email}\\\">https://localhost:8880/validate/${email}</a> Remember to post the spot you are parked in before leaving so another driver can request for you to hold it until they arrive.<br><br>Sincerely,<br><br>The Spotback Team<br><br><img src=\\\"https://s3.us-east-2.amazonaws.com/aws-codestar-us-east-2-639360651737/Spotback+new-2.png\\\"width=\\\"35%\\\"></p>\",\n" +
//                "    \"emailChange\" : \"<h4>Dear ${firstName},</h4><p>You have successfully changed your email address for your spotback account! Please click this link to verify your account <a href=\\\"https://localhost:8880/validate/${email}\\\">https://localhost:8880/validate/${email}</a> Remember to post the spot you are parked in before leaving so another driver can request for you to hold it until they arrive.<br><br>Sincerely,<br><br>The Spotback Team<br><br><img src=\\\"https://s3.us-east-2.amazonaws.com/aws-codestar-us-east-2-639360651737/Spotback+new-2.png\\\"width=\\\"35%\\\"></p>\"\n" +
//                "  }"));
//        emailUtil.sendEmail("dylancorbus@outlook.com", "dylan", "hell", "hello").setHandler(ar -> {
//            if(ar.succeeded()) {
//                System.out.println(ar.result());
//            } else {
//                System.out.println(ar.cause().getMessage());
//            }
//        });
//    }
}