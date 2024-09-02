package api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static api.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;

public class RequestSpec {

    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setUrlEncodingEnabled(false)
            .setContentType(ContentType.JSON)
            .addFilter(withCustomTemplates())
            .log(LogDetail.URI)
            .log(LogDetail.METHOD)
            .log(LogDetail.BODY)
            .addFilter(new CustomLogFilter())
            .build();
}
