public class Constant {

    public static String BASE_URL = "";
    public static String CHROMEDRIVER = "";
    public static String USERNAME = "";
    public static String PASSWORD = "";
    public static String RESERVE_TIME = "";

    public Constant() {
        BASE_URL = System.getenv("BASE_URL"); //https://www.yourcourts.com
        CHROMEDRIVER = System.getenv("CHROMEDRIVER");
        USERNAME = System.getenv("USERNAME");
        PASSWORD = System.getenv("PASSWORD");
        RESERVE_TIME = System.getenv("RESERVE_TIME");
    }
}
