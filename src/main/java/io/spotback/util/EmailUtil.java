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
        mailConfig = new MailConfig(config);
    }

    private void setMessage(String email, String firstname, String messageKey, String subject) {
        eMessage.setFrom(config.getString("username"));
        eMessage.setTo(email);
        eMessage.setSubject(config.getString(subject));
        eMessage.setText(config.getString(Constants.EMAIL_TEXT));
        eMessage.setHtml(StrSubstitutor.replace(config.getString(messageKey), new JsonObject().put("email", email).put("firstName", firstname).getMap()));
    }


    private MailClient getEmailClient() {
        if(mailClient == null) {
            mailClient = MailClient.createShared(vertx, mailConfig);
            return mailClient;
        } else {
            return mailClient;
        }
    }

    public Future<String> sendEmail(String email, String firstname, String messageKey, String subject) {
        Future<String> status = Future.future();
        setMessage(email, firstname, messageKey, subject);
        getEmailClient().sendMail(eMessage, ar -> {
           if(ar.succeeded()) {
               status.complete(ar.result().getRecipients().toString());
               mailClient.close();
           } else {
               mailClient.close();
               status.fail(ar.cause().getMessage());
           }
        });
        return status;
    }
}
