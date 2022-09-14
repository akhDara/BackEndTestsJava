package minimarket;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class newDbTest {
    @BeforeAll
    static void beforeAll () {
        RestAssured.baseURI = "https://minimarket1.herokuapp.com/market";
        RequestSpecification base = new RequestSpecBuilder()
                .build();
        RestAssured.requestSpecification = base;
    }

    private static String requestBody = "{\n" +
            "  \"title\": \"Cinnamon Bun\",\n" +
            "  \"price\": 100, \n" +
            " \"categoryTitle\": \"Food\" \n }";
    private static int id = 1196;

    @Test
    @Order(1)
    @DisplayName("New post in BD")
    void testPostMiniMarket () {
        RestAssured.given()
                .headers("Content-Type", "application/json")
                .body(requestBody)
                .log()
                .all()
                .expect()
                .statusCode(201)
                .time(Matchers.lessThanOrEqualTo(1700L))
                .when()
                .post("/api/v1/products")
                .getBody()
                .jsonPath()
                .prettyPeek();

    }

    @Test
    @Order(2)
    @DisplayName("There are no deletion item")
    void deletedItem () {
        RestAssured.given()
                .headers("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(404)
                .time(Matchers.lessThanOrEqualTo(1700L))
                .when()
                .get("/api/v1/products/1196")
                .body()
                .asPrettyString();

    }
}
