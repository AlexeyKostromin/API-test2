package api.base;

import api.env.EnvSettings;
import api.json.JsonPayloadHandler;
import api.services.ModulePath;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;


import static api.specs.RequestSpec.requestSpec;
import static io.restassured.RestAssured.given;

@Slf4j
public class BaseRequest {
    private RequestSpecification requestSpecification;

    public BaseRequest() {
        requestSpecification = given()
                .spec(requestSpec);
    }

    private String getUrl(ModulePath modulePath, String path) {
        EnvSettings.setBaseUrl();
        String baseURL = System.getProperty("baseTypicodeApiUrl");

        return baseURL
                .concat(modulePath.getName())
                .concat(path);
    }

    public BaseRequest body(Object body) {
        JsonPayloadHandler payloadHandler = new JsonPayloadHandler();

        if (body != null) {
            requestSpecification.contentType(ContentType.JSON)
                    .body(payloadHandler.toPayload(body));
        }
        return this;
    }

    public BaseRequest logAll() {
        requestSpecification.log().all();
        return this;
    }

    public Response executeRequest(Method method, ModulePath modulePath, String route) {
        log.info("Sending request to: ");

        return requestSpecification.request(method, getUrl(modulePath, route));
    }

}
