import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class ReservationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String reserveButtonXpath = "//div[contains(@class, 'heading-elements')]/button[contains(@id, 'submitButtonId')]";
    @FindBy(xpath = reserveButtonXpath)
    private WebElement reserveButton;

    private final String reservationTextXpath = "//span[contains(@title, 'This value is based on your assigned permissions.')]";
    @FindBy(xpath = reservationTextXpath)
    private WebElement reservationText;

    private final String successfulReservationXpath = "//div[contains(@class, 'alert-success')]";
    @FindBy(xpath = successfulReservationXpath)
    private WebElement successfulReservation;


    public ReservationPage(WebDriver driver, int timeout) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public void bookCourt(String time) {
        // Get day after tomorrow date
        Format f = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 2);
        date = c.getTime();

        String reservationDate = f.format(date);
        System.out.println("Opening reservations page for: "+reservationDate);
        driver.get(Constant.BASE_URL+"/yourcourts/facility/viewschedule?reservationDate="+reservationDate);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(reservationTextXpath)));
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(text(), 'Open') and contains(@title, '"+time+"')]")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(), 'Open') and contains(@title, '"+time+"')]")));
            WebElement timeSlot = driver.findElement(By.xpath("//span[contains(text(), 'Open') and contains(@title, '"+time+"')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", timeSlot);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(reserveButtonXpath)));
            reserveButton.click();
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(successfulReservationXpath)));
            System.out.println("Your reservation is booked successfully");
        } catch (Exception unavailable) {
            unavailable.printStackTrace();
            System.out.println("Reservation time is unavailable. Please provide a different time as hh:mm");
        }
    }
}
