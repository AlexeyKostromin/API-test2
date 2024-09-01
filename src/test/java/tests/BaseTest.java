package tests;

import endpoints.ApiEndpoints;
import endpoints.ApiRequestGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

@Slf4j
public class BaseTest {

    protected static ApiEndpoints testApiEndpoints = new ApiEndpoints();
    protected static ApiRequestGenerator testApiRequestGenerator = new ApiRequestGenerator(testApiEndpoints);


    @BeforeEach
    public void beforeEachTest(TestInfo testInfo) {
        logRunningTestName(testInfo);
    }


    private void logRunningTestName(TestInfo testInfo) {
        String methodName = testInfo.getTestMethod().orElseThrow().getName();
        String testName = testInfo.getDisplayName();
        log.info("Starting test: {}", methodName);
        log.info("Test description: {}", testName);
        log.info("--------------");
    }

}
