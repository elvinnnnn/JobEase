package com.jobease.service;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import com.jobease.dtos.OpportunityDto;
import com.jobease.model.Preferences;

@Service
public class JobOpportunityService {
    private final UserService userService;

    public JobOpportunityService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Scrape job opportunities based on user's preferences. Currently only scraping seek.com.au.
     * Future implmentation will include more job boards from linkedin, indeed, etc.
     * @param email
     * @return
     */
    public List<OpportunityDto> scrapeJobs(String email) {
        System.out.println("Getting Preferences...");
        Preferences preferences = userService.getPreferences(email);
        System.out.println("Scraping SEEK...");
        return seekScrape(seekUrl(preferences));
    }

    /**
     * seek.com.au web scraper that uses Selenium to find job opportunities
     * @param seekUrl
     * @return A list of job opportunities
     */
    public List<OpportunityDto> seekScrape(String seekUrl) {
        List<OpportunityDto> jobs = new ArrayList<>();
        System.setProperty("webdriver.chrome.driver", "G:/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        try {
            System.out.println(seekUrl);
            driver.get(seekUrl);
            // List<WebElement> divs = driver.findElements(By.cssSelector("div._1fggenz0._1feq2e75h._1feq2e753, span._1fggenz0._1feq2e74z._474bdu0._474bdu1._474bdu21._18ybopc4._474bdu7"));
            List<WebElement> divs = driver.findElements(By.cssSelector("[data-automation]"));
            Thread.sleep(1000);
            OpportunityDto job = null;
            for (WebElement div : divs) {
                String dataAutomationValue = div.getAttribute("data-automation");
                if ("jobTitle".equals(dataAutomationValue)) {
                    if (job != null) {
                        jobs.add(job);
                    }
                    job = new OpportunityDto();
                    job.setJobTitle(div.getText());
                    job.setJobUrl(div.getAttribute("href"));
                } else if (job != null) {
                    if ("jobCompany".equals(dataAutomationValue)) job.setCompanyName(div.getText());
                    if ("jobLocation".equals(dataAutomationValue)) job.setLocation(div.getText());
                    if ("jobSalary".equals(dataAutomationValue)) job.setSalaryRange(div.getText());
                    if ("jobShortDescription".equals(dataAutomationValue)) job.setDescription(div.getText());
                    if ("jobListingDate".equals(dataAutomationValue)) job.setListDate(div.getText());
                }
            }
            if (job != null) {
                jobs.add(job);
            }
        } catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
        }
        System.out.println("SEEK Scrape Complete!");
        return jobs;
    }

    /**
     * Generate seek.com.au URL based on the user's preferences
     * @param preferences
     * @return A formatted seek.com.au URL
     */
    public String seekUrl(Preferences preferences) {
        var url = new StringBuilder("https://www.seek.com.au/");

        // Handle experience level preference
        boolean hasExp = false;
        if (preferences.getExperienceLevel() != null && preferences.getExperienceLevel().length > 0) {
            hasExp = true;
            for (var level : preferences.getExperienceLevel()) {
                switch (level) {
                    case "Entry" -> url.append("-entry-level");
                    case "Associate" -> url.append("-associate");
                    case "Mid-Senior" -> url.append("-mid-senior");
                    case "Director" -> url.append("-director");
                    case "Executive" -> url.append("-executive");
                    default -> {}
                }
            }
        }

        if (hasExp) {
            url.append("-jobs");
        } else url.append("jobs");

        // Handle location preference
        if (preferences.getLocation() != null && preferences.getLocation().length > 0) {
            switch (preferences.getLocation()[0]) {
                case "Sydney" -> url.append("/in-All-Sydney-NSW");
                case "Melbourne" -> url.append("/in-All-Melbourne-VIC");
                case "Brisbane" -> url.append("/in-All-Brisbane-QLD");
                case "Perth" -> url.append("/in-All-Perth-WA");
                case "Adelaide" -> url.append("/in-All-Adelaide-SA");
                case "Canberra" -> url.append("/in-All-Canberra-ACT");
                case "Hobart" -> url.append("/in-All-Hobart-TAS");
                case "Darwin" -> url.append("/in-All-Darwin-NT");
                default -> {}
            }
        }

        // Handle remote preference
        if (preferences.getRemote() != null && preferences.getRemote().length > 0) {
            url.append("?workarrangement=");
            for (var arrangement : preferences.getRemote()) {
                switch (arrangement) {
                    case "Onsite" -> url.append("1%2C");
                    case "Remote" -> url.append("2%2C");
                    case "Hybrid" -> url.append("3%2C");
                    default -> {}
                }
            }
            url.append("&");     
        }

        // Handle industry preference
        if (preferences.getIndustry() != null && preferences.getIndustry().length > 0) {
            url.append("?classification=");
            for (var area : preferences.getIndustry()) {
                switch (area) {
                    case "IT" -> url.append("6281%2C");
                    case "Engineering" -> url.append("1209%2C");
                    case "Finance" -> url.append("1203%2C");
                    case "Science" -> url.append("1225%2C");
                    case "Consulting" -> url.append("6076%2C");
                    case "Trades" -> url.append("1223%2C");
                    case "Government" -> url.append("1210%2C");
                    default -> {}
                }
            }
            url.append("&");
        }

        // Handle job type preference
        if (preferences.getJobType() != null && preferences.getJobType().length > 0) {
            url.append("worktype=");
            for (var type : preferences.getJobType()) {
                switch (type) {
                    case "Full-Time" -> url.append("242%2C");
                    case "Part-Time" -> url.append("243%2C");
                    case "Contract" -> url.append("244%2C");
                    case "Temporary" -> url.append("244%2C");
                    case "Internship" -> url.append("245%2C"); // This is actually Casual/Vacation in SEEK but we map it to Internship because we don't have that option
                    default -> {}
                }
            }
        }

        System.out.println(url.toString());
        return url.toString();
    }
}