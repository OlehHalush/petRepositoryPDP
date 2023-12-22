package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class CustomLoggingFilter implements Filter {

    private final LogDetail logDetail;

    public CustomLoggingFilter(LogDetail logDetail) {
        this.logDetail = logDetail;
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        System.out.println("Request:");
        System.out.println(requestSpec.log().all().toString());


        System.out.println("sadasdasdasda");
        Response response = ctx.next(requestSpec, responseSpec);

        System.out.println("Response:");
        System.out.println(responseSpec.log().all().toString());

        return response;
    }
}
