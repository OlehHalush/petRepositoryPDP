package rest_assure;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class PeekAndPrint extends BaseClass{


    @Test
    public void peek() {
        RestAssured.get(BASE_URL).peek();
    }

    @Test
    public void  prettyPeek() {
        RestAssured.get(BASE_URL).prettyPeek();
    }

    @Test
    public void print() {
        RestAssured.get(BASE_URL).print();
    }

    @Test
    public void prettyPrint() {
        RestAssured.get(BASE_URL).prettyPrint();
    }
}
