import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class key {
    public static void main(String[] args) throws IOException {
        System.setProperty("webdriver.chrome.driver", "G:/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://www.seek.com.au/entry-level-computer-science-jobs/in-All-Sydney-NSW");
            // List<WebElement> divs = driver.findElements(By.cssSelector("div._1fggenz0._1feq2e75h._1feq2e753, span._1fggenz0._1feq2e74z._474bdu0._474bdu1._474bdu21._18ybopc4._474bdu7"));
            List<WebElement> divs = driver.findElements(By.cssSelector("[data-automation]"));

            Thread.sleep(1000);
            for (WebElement div : divs) {
                String dataAutomationValue = div.getAttribute("data-automation");
                if ("jobTitle".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobTitle': " + div.getText());
                if ("jobTitle".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobLink': " + div.getAttribute("href"));
                if ("jobCompany".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobCompany': " + div.getText());
                if ("jobLocation".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobLocation': " + div.getText());
                if ("jobSalary".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobSalary': " + div.getText());
                if ("jobShortDescription".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobShortDescription': " + div.getText());
                if ("jobSubClassification".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobSubClassification': " + div.getText());
                if ("jobListingDate".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobListingDate': " + div.getText());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
