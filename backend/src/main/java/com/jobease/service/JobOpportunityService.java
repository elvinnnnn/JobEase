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

    public List<OpportunityDto> scrapeJobs(String email) {
        System.out.println("Getting Preferences...");
        Preferences preferences = userService.getPreferences(email);
        System.out.println("Scraping SEEK...");
        return seekScrape(preferences.seekUrl());
    }

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
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
        }
        System.out.println("SEEK Scrape Complete!");
        return jobs;
    }
}

        // example fron jsoup docs, unrelated to the project
        // Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
        // System.out.println(doc.title());
        // Elements newsHeadlines = doc.select("#mp-itn b a");
        // for (Element headline : newsHeadlines) {
        //     System.out.printf("%s\n\t%s%n", 
        //         headline.attr("title"), headline.absUrl("href"));
        // }
        
        // returns nothing atm

        // String dataAutomationValue = div.getAttribute("data-automation");
                // if ("jobTitle".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobTitle': " + div.getText());
                // if ("jobTitle".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobLink': " + div.getAttribute("href"));
                // if ("jobCompany".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobCompany': " + div.getText());
                // if ("jobLocation".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobLocation': " + div.getText());
                // if ("jobSalary".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobSalary': " + div.getText());
                // if ("jobShortDescription".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobShortDescription': " + div.getText());
                // if ("jobSubClassification".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobSubClassification': " + div.getText());
                // if ("jobListingDate".equals(dataAutomationValue)) System.out.println("Found element with data-automation='jobListingDate': " + div.getText());