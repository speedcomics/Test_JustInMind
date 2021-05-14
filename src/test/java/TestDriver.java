import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class TestDriver {


    private static Logger logger = LoggerFactory.getLogger(TestDriver.class);
    static WebDriver driver;
    static Properties prop = new Properties();
//    public static WebDriverWait wait;

    @BeforeClass
    public static void init() throws IOException {

        String log4jPath = System.getProperty("user.dir") + "/log4j.properties";
        PropertyConfigurator.configure(log4jPath);

        BasicConfigurator.configure();


        InputStream input = new FileInputStream("config.properties");
        prop.load(input);


        if (driver == null) {
            switch (prop.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("disable-infobars");
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

            }

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }


        driver.get(prop.getProperty("url"));
        logger.info("Deleting All Cookies");
        driver.manage().deleteAllCookies();
        logger.info("Maximazing the Window");
        driver.manage().window().maximize();
//        wait = new WebDriverWait(driver,20);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() {
        logger.info("Quitting");
        driver.quit();
    }
}

