import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browser {

    public WebDriver driver;
    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver chromeBrowser(String type) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--start-maximized");
        options.addArguments("incognito");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        if (type.equalsIgnoreCase("HEADLESS")) {
            options.addArguments("--headless");
        }
        System.setProperty("webdriver.chrome.driver", Constant.CHROMEDRIVER);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        System.out.println("*****Started chrome driver*****");
        return driver;
    }

    public void closeBrowser() {
        driver.quit();
    }

}
