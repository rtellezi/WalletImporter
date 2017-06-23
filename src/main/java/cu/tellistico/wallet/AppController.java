package cu.tellistico.wallet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class AppController {

    private WebDriver driver = null;

    public AppController() throws MalformedURLException {

        driver = new RemoteWebDriver(new URL("http://localhost:9515"), DesiredCapabilities.chrome());
        driver.get("https://app.budgetbakers.com/auth/login");
        driver.manage().window().maximize();
    }

    private void click(WebElement element) {

        boolean continueFlag = true;
        int cont = 0;
        do {
            try {
                element.click();
                continueFlag = false;
            } catch (WebDriverException wde) {
                try {
                    System.out.println(String.format("retrying (%d)...", ++cont));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (continueFlag);
    }

    public void login(String user, String pass) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='username']")));

        WebElement userField = driver.findElement(By.xpath("//input[@name='username']"));
        userField.click();
        userField.clear();
        userField.sendKeys(user);

        WebElement passField = driver.findElement(By.xpath("//input[@name='password']"));
        passField.click();
        passField.clear();
        passField.sendKeys(pass);

        WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#records']")));
        WebElement recordsTab = driver.findElement(By.xpath("//a[@href='#records']"));

        click(recordsTab);
    }

    public void addRecord(Record record) throws InterruptedException {
        int sleep = 2000;

        WebElement button = driver.findElement(By.xpath("//button[contains(@class,'button-add-record')]"));
        button.click();
        Thread.sleep(sleep);

        if (record.getType() == 1) {
            WebElement expenseTab = driver.findElement(By.xpath("//a[contains(@class, 'item')]/span[text()='Expense']"));
            expenseTab.click();
        } else {
            WebElement incomeTab = driver.findElement(By.xpath("//a[contains(@class, 'item')]/span[text()='Income']"));
            incomeTab.click();
        }

        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='amount']")));
        WebElement amount = driver.findElement(By.xpath("//input[@name='amount']"));
        amount.click();
        amount.clear();
        amount.sendKeys(String.valueOf(record.getAmount()));

        WebElement category = driver.findElement(By.xpath("//div[text()='Select category']"));
        category.click();
        Thread.sleep(sleep / 3);
        WebElement categoryValue = driver.findElement(By.xpath("//form//div[@class='category-option']//span[text()='" + record.getCategory() + "']"));
        categoryValue.click();
        Thread.sleep(sleep / 3);

        WebElement subcategory = driver.findElement(By.xpath("//div[text()='Select subcategory']"));
        subcategory.click();
        Thread.sleep(sleep / 3);
        WebElement subcategoryValue = driver.findElement(By.xpath("//form//div[contains(@class,'category-option')]//div[text()='" + record.getSubcategory() + "']"));
        subcategoryValue.click();
        Thread.sleep(sleep / 3);

        WebElement account = driver.findElement(By.xpath("//div[text()='Select account']"));
        account.click();
        Thread.sleep(sleep / 3);
        WebElement accValue = driver.findElement(By.xpath("//form//span[text()='" + record.getAccount() + "']"));
        accValue.click();
        Thread.sleep(sleep / 2);

        WebElement dateInput = driver.findElement(By.xpath("//div[label/label/span[text()='Date']]//input"));
        dateInput.click();
        dateInput.clear();
        dateInput.sendKeys(record.getDate());

        WebElement hoursInput = driver.findElement(By.xpath("//input[@name='hours']"));
        hoursInput.click();
        hoursInput.clear();
        hoursInput.sendKeys(String.valueOf(record.getHours()));

        WebElement minutesInput = driver.findElement(By.xpath("//input[@name='minutes']"));
        minutesInput.click();
        minutesInput.clear();
        minutesInput.sendKeys(String.valueOf(record.getMinutes()));

        WebElement notesField = driver.findElement(By.xpath("//form//textarea[@name='note']"));
        notesField.click();
        notesField.clear();
        notesField.sendKeys(record.getNotes());

        WebElement paymentType = driver.findElement(By.xpath("//div[label/span[text()='Payment type']]//div[@class='text']/span"));
        paymentType.click();
        Thread.sleep(sleep / 3);
        WebElement paymentTypeItem = driver.findElement(By.xpath("//div[label/span[text()='Payment type']]//div[contains(@class, 'visible')]//div[contains(@class, 'item')]/span[text()='" + record.getPaymentType() + "']"));
        paymentTypeItem.click();
        Thread.sleep(sleep / 3);

        WebElement actionButton = driver.findElement(By.xpath("//button/span[text()='Save changes']"));//Save changes
        actionButton.click();
        Thread.sleep(sleep / 2);

        System.out.println("Action button reached!");

    }

    public void close() {
        driver.close();
        driver.quit();
    }
}
