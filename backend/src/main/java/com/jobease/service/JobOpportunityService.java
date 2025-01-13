package com.jobease.service;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.jobease.model.JobOpportunity;

@Service
public class JobOpportunityService {
    public List<JobOpportunity> scrapeJobs() throws IOException {
        JobOpportunity opportunity = new JobOpportunity();

        // example fron jsoup docs, unrelated to the project
        Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
        System.out.println(doc.title());
        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {
            System.out.printf("%s\n\t%s%n", 
                headline.attr("title"), headline.absUrl("href"));
        }
        
        // returns nothing atm
        return List.of(opportunity);
    }
}
