package tests;

import api.env.EnvSettings;
import endpoints.posts.PostUtilMethods;
import endpoints.posts.PostUtilData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

@Slf4j
public class BaseTest {

    protected static PostUtilMethods postUtilMethods = new PostUtilMethods();
    protected static PostUtilData postUtilData = new PostUtilData();

    @BeforeAll
    public static void beforeAll() {
        EnvSettings.setBaseUrl();
    }

    @BeforeEach
    public void beforeEachTest(TestInfo testInfo) {
        logRunningTestName(testInfo);
    }


    private void logRunningTestName(TestInfo testInfo) {
        log.info("--------------");
        log.info("Starting test: {}", testInfo.getTestMethod().orElseThrow().getName());
        log.info("Test description: {}", testInfo.getDisplayName());
        log.info("--------------");
    }

}
