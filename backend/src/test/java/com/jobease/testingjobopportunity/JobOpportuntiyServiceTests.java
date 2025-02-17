package com.jobease.testingjobopportunity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.jobease.model.Preferences;
import com.jobease.service.JobOpportunityService;
import com.jobease.service.UserService;

@SpringBootTest
public class JobOpportuntiyServiceTests {

    // creates user service mock
    @Mock
    private UserService userService;

    // injects user service mock into job opportunity service
    @InjectMocks
    private JobOpportunityService jobOpportunityService;

    // sets up the mocks
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // testing a typical use case of whether the seek url is built correctly with the given preferences
    @Test
    void testTypicalSeekUrl() {
        Preferences preferences = new Preferences();
        preferences.setExperienceLevel(new String[] {"Entry", "Executive"});
        preferences.setJobType(new String[] {"Full-time", "Part-time"});
        preferences.setLocation(new String[] {"Melbourne"});
        preferences.setRemote(new String[] {"Remote", "Hybrid"});
        preferences.setIndustry(new String[] {"Information Technology"});

        String testUrl = jobOpportunityService.seekUrl(preferences);
        String expectedUrl = "https://www.seek.com.au/-entry-level-executive-jobs/in-All-Melbourne-VIC?workarrangement=2%2C3%2C&?classification=6281%2C&worktype=242%2C243%2C";
        assertEquals(expectedUrl, testUrl);
    }

    // testing a use case when no preferences are given to the seek url builder
    @Test
    void testSeekUrlWithNoPreferences() {
        Preferences preferences = new Preferences();
        String testUrl = jobOpportunityService.seekUrl(preferences);
        String expectedUrl = "https://www.seek.com.au/jobs";
        assertEquals(expectedUrl, testUrl);
    }
}
