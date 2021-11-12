import org.openqa.selenium.WebDriver;

public class ReserveCourt {

    public static void main(String[] args) {
        int elementTimeout = 30;
        WebDriver driver = null;
        Constant constant = new Constant();
        Browser browser = new Browser(driver);
        driver = browser.chromeBrowser("not-headless");

        LoginPage loginPage = new LoginPage(driver, 30);
        ReservationPage reservationPage = new ReservationPage(driver, 30);

        try {
            loginPage.login(Constant.USERNAME, Constant.PASSWORD);
            //reservationPage.bookCourt("12:00PM");
            //browser.closeBrowser();
        } catch (Exception unknown) {
            //browser.closeBrowser();
            System.out.println("Unable to reserve the court");
        }
    }
}
