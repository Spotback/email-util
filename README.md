# email-util

This utility can be used to send an email. To use this tool first construct a new EmailUtil object `public EmailUtil(Vertx vertx, JsonObject config)`.
The config should look something like this:

```json
{
    "hostname" : "smtp.gmail.com",
    "port" : 465,
    "starttls" : null,
    "login" : "REQUIRED",
    "username" : "spotbackteam@gmail.com",
    "password" : "Spotbackteam!",
    "ssl" : true,
    "trustAll" : true,
    "keyStore" : null,
    "keyStorePassword" : null,
    "authMethods" : "PLAIN",
    "ownHostName" : null,
    "maxPoolSize" : 10,
    "keepAlive" : true,
    "allowRcptErrors" : false,
    "welcomeSubject" : "Verification Email from Spotback",
    "text" : "",
    "validate.link": "https://localhost:8880/${email}",
    "WELCOME" : "<h4>Dear ${firstName},</h4><p>Thank you for joining the Spotback Community! We are very excited to have you on board to solve the communities parking hassles. Please click this link to verify your account <a href=\"https://localhost:8880/validate/${email}\">https://localhost:8880/validate/${email}</a> Remember to post the spot you are parked in before leaving so another driver can request for you to hold it until they arrive.<br><br>Sincerely,<br><br>The Spotback Team<br><br><img src=\"https://s3.us-east-2.amazonaws.com/aws-codestar-us-east-2-639360651737/Spotback+new-2.png\"width=\"35%\"></p>"
}
```
Then to send the email simply call this instance method `public Future<String> sendEmail(String email, String firstname, String messageKey, String subject)`.
sendEmail takes in email of who you are sending the message to, firstname of that person, the message(pass is a key and it will pull the message from the configuration you constructed with), and the subject key which is also stored in the cofiguration.

To get a this project into your build:

# Step 1. Add the JitPack repository to your build file
**maven**

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
# Step 2. Add the dependency
	<dependency>
	    <groupId>com.github.Spotback</groupId>
	    <artifactId>email-util</artifactId>
	    <version>Tag</version>
	</dependency>
