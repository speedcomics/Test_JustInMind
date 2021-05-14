import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JustInMind_SignUp_Page extends TestDriver {
    private static Logger logger = LoggerFactory.getLogger(JustInMind_SignUp_Page.class);

    public JustInMind_SignUp_Page() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "s-Input_1")
    WebElement fullName_field;
    @FindBy(xpath = "//*[@id=\"s-Input_2\"]")
    WebElement email_field;
    @FindBy(id = "s-Input_3")
    WebElement areaOfInterest_field;
    @FindBy(xpath = "//*[@id=\"s-Select-options\"]")
    WebElement category_dropdown;
    @FindBy(xpath = "//*[@id=\"s-Checkbox\"]")
    WebElement termsAndConditions_checkbox;
    @FindBy(id = "s-Button_active")
    WebElement signUp_button;
    @FindBy(xpath = "//*[@id=\"s-Confirmation\"]")
    WebElement confirmation;
    @FindBy(id = "s-Button_gotIt")
    WebElement buttonGotIT;

    public void clickIntoInputField1() {
        new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(fullName_field)).click();
    }

    public void enterInputIntoFullNameField(String fullName) {
        new Actions(driver).sendKeys(fullName).perform();

    }

    public void clickIntoInputField2() {
        new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(email_field)).click();
    }

    public void enterInputIntoEmailField(String email) {

        new Actions(driver).sendKeys(email).perform();
    }

    public void clickIntoInputField3() {
        new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(areaOfInterest_field)).click();
    }

    public void enterInputIntoAreaOfInterestField(String areaOfInterest) {
        new Actions(driver).sendKeys(areaOfInterest).perform();
    }

    public void selectCategoryFromTheDropDown(String categoryOption) {
//        logger.info("Expanding dropdown");
//        category_dropdown = wait.until(ExpectedConditions.visibilityOf(category_dropdown))
        Select select = new Select(category_dropdown);
        select.selectByVisibleText(categoryOption);
    }

    public void checkTermsAndConditionsCheckBox() {
        termsAndConditions_checkbox.click();
    }

    public void clickSignUpButton() {
        new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(signUp_button)).click();
    }

    public void validateConfirmation() {
        if (driver.getPageSource().contains("Your information was successfully submitted!")) {
//            System.out.print("Confirmation : your information was successfully submitted!");
            logger.info("Confirmation : your information was successfully submitted!");
        } else {
            System.out.print("Something is wrong. Please review the page");
        }
    }

    public void clickButtonGotIt() {
        new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(buttonGotIT)).click();
    }

}


