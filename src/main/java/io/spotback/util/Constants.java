package io.spotback.util;

public class Constants {
  /**
   * AWS configuration keys
   */
  public static final String AWS_CONFIGURATION_KEY = "aws";
  public static final String AWS_KEY = "aws_access_key_id";
  public static final String AWS_SECRET_KEY = "aws_secret_access_key";
  public static final String DYNAMO = "dynamo.endpoint";
  public static final String TABLE_REGION = "region";
  public static final String AWS_MATCHING = "matching.service";
  public static final String AWS_MATCHING_GET = "matching.uri.get";
  public static final String AWS_MATCHING_POST = "matching.uri.post";
  public static final String AWS_MATCHING_PORT = "matching.port";
  public static final String USER_TABLE = "Users";
  public static final String REFERRAL_TABLE = "Referrals";
  /**
   * Email configuration keys
   */
  public static final String EMAIL_CONFIGURATION_KEY = "email.configuration";
  public static final String HOST = "hostname";
  public static final String AUTH = "set.auth.methods";
  public static final String EMAIL = "company.email";
  public static final String EMAIL_PASSWORD = "email.password";
  public static final String EMAIL_HTML = "html";
  public static final String EMAIL_SUBJECT = "subject";
  public static final String EMAIL_TEXT = "text";
  /**
   * Common configuration keys
   */
  public static final String PORT = "port";
  public static final String SSL = "ssl";
  public static final String SET_TRUSTALL = "set.trust.all";
  public static final String SET_ALPN = "use.ALPN";
  /**
   * Schema configuration keys
   */
  public static final String SCHEMA_CONFIGURATION_KEY = "schemas";
  public static final String CREATE_SCHEMA = "create.account.schema";
  public static final String READ_SCHEMA = "read.account.schema";
  public static final String UPDATE_SCHEMA = "update.account.schema";
  public static final String DELETE_SCHEMA = "delete.account.schema";
  public static final String MATCH_SCHEMA_GET = "match.account.schema.get";
  public static final String MATCH_SCHEMA_POST = "match.account.schema.post";
  public static final String SCHEMA_LOCATION = "path";
  /**
   * JWT configuration keys
   */
  public static final String JWT_CONFIGURATION_KEY = "jwt";
  public static final String JWT_TOKEN = "token";
  public static final String JWT_PUBLIC_KEY = "public.key";
  public static final String JWT_PRIVATE_KEY = "private.key";
  public static final String JWT_ALGORITHM = "algorithm";
  public static final String JWT_SYMMETRIC = "symmetric";
  public static final String JWT_EXPIRATION = "expiration.minutes";
  public static final String JWT_ISSUER = "issuer";

  public static final int HTTP_CLIENT_SIDE = 400;
  public static final int HTTP_SERVER_SIDE = 500;



  public static final String USER_CONFIGURATION_KEY = "user";
  public static final String USER_IDENTIFIER = "id";
  public static final String PASSWORD = "authorization";
  public static final String USER_NAME1 = "name1";
  public static final String USER_NAME2 = "name2";
  public static final String USER_IMAGE = "image";
  public static final String USER_HISTORY = "history";
  public static final String USER_PERMISSION = "permission";


  public static final String CONFIG_TYPE = "file";
  /**TODO
   * must change this variable to match the environment
   */
  public static final String CONFIG_LOCATION = "lambda_secrets_e0.json";
  public static final String ADDRESSES_CONFIGURATION_KEY = "user.service.addresses";


  public static final String CREATE = "create";
  public static final String READ = "read";
  public static final String UPDATE = "update";
  public static final String DELETE = "delete";
  public static final String MATCH_GET = "match.get";
  public static final String MATCH_POST = "match.post";

  public static final String VALIDATE = "validate";


  public static final String SERVER_CERT = "server.cert";
  public static final String SERVER_KEY = "server.key";
  public static final String SERVER_KEYSTORE_PASSWORD = "keystore.password";
  public static final String CLIENT_TRUSTSTORE = "client.truststore";
  public static final String USER_SERVICE_SERVER = "user.service.http";

  public static final String CIRCUIT_BREAK_CONFIGURATION_KEY = "circuit.breaker";
  public static final String CIRCUIT_RESET = "set.reset.timeout";
  public static final String CIRCUIT_TIMEOUT = "set.timeout";
  public static final String CIRCUIT_FALLBACK = "set.fallback";
  public static final String CIRCUIT_MAX_FAILS = "set.max.failures";


}
