package fr.vannes.gretajavafx;

public class Setting {

    /**
     * Config BDD
     */

    public static String HOST = "jdbc:mysql://localhost:3306/";
    public static String BDD = "mediatheque";
    public static String LOGIN = "admin";
    public static String PWD = "admin";

    public static String TYPE_BDD = "mysql";

    public static int PORT = 3306;
    /**
     * Config Brevo
     */
    public static String API_KEY = "Your API KEY";

    public static String FROM_MAIL = "toto@free.fr";
    public static String FROM_MAIL_NAME = "toto ";
    public static String TO_EMAIL = "john@free.fr";
    public static String TO_EMAIL_CC = "sarah@free.fr";
    public static String TO_EMAIL_NAME = "john";
    public static String TO_EMAIL_NAME_CC = "Sarah";
    public static String TO_EMAIL_NAME_BCC = "Jim";
    public static String TO_EMAIL_BCC = "jim@free.fr";
    public static String REPLY_EMAIL = ",noreply@free.fr";
    public static String REPLY_EMAIL_NAME = ",noreply";

    public static String BODY_EMAIL = "<html><body><h1>This is my first transactional email </h1></body></html>";
    public static String SUBJECT_EMAIL = "test";
}
