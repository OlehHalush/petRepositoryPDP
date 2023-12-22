package rest_assure;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.PropertiesReader;

public class BaseClass {

    protected static final String BASE_URL = "https://api.github.com";
    protected static final String TOKEN = PropertiesReader.getProperty("token");
    protected static RequestSpecification baseRequestSpec = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .build();
    protected static ResponseSpecification baseResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();

    public static void enableLogging() {
        RestAssured.filters(new RequestLoggingFilter(LogDetail.ALL), new ResponseLoggingFilter(LogDetail.ALL));
    }

}
