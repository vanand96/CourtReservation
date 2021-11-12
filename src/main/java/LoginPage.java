import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String usernameXpath = "//input[contains(@name, 'username')]";
    @FindBy(xpath = usernameXpath)
    private WebElement username;

    private final String passwordXpath = "//input[contains(@name, 'password')]";
    @FindBy(xpath = passwordXpath)
    private WebElement password;

    private final String loginButtonXpath = "//button[contains(text(), 'Log In ')]";
    @FindBy(xpath = loginButtonXpath)
    private WebElement loginButton;

    public LoginPage(WebDriver driver, int timeout) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public void login(String usernameText, String passwordText) {
        driver.get(Constant.BASE_URL+"/yourcourts/security/showLogin");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(usernameXpath)));
        username.sendKeys(usernameText);
        password.sendKeys(passwordText);
        loginButton.click();
        wait.until(ExpectedConditions.urlContains(Constant.BASE_URL+"/yourcourts/member/index"));
    }

}
