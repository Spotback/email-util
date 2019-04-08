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
//        emailUtil.sendEmail("dylancorbus@outlook.com", "dylan", "hell", "hello").setHandler(ar -> {
//            if(ar.succeeded()) {
//                System.out.println(ar.result());
//            } else {
//                System.out.println(ar.cause().getMessage());
//            }
//        });
//    }
}