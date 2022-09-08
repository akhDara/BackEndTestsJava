package com.geekbrains.spoonacullar;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class ComplexSearchApiTest {

    @BeforeAll
    static void beforeAll () {
        RestAssured.baseURI = "https://api.spoonacular.com";
        RequestSpecification base = new RequestSpecBuilder()
                .addParam("apiKey", "8a31eefd36604b8ca910047c1b6a1ce0")
                .build();
        RestAssured.requestSpecification = base;
    }

    @Test
    @DisplayName("Проверка поиска:хлеб")
    void testSearchBread () {
        String actually = RestAssured.given()
                .param("number", 3)
                .param("limitLicense", true)
                .param("query", "bread")
                .log()
                .uri()
                //.all()
                .expect()
                .statusCode(200)
                .time(Matchers.lessThanOrEqualTo(1700L))
                .body("totalResults", is(175))
                .body("results", hasSize(3))
               // .log()
               // .all()
                .when()
                .get("/recipes/complexSearch")
                .body()
                .asPrettyString();

        String expected = readResourceAsString("expected.json");
        /*JsonAssert.assertJsonEquals(
               expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );*/

    }

   private String readResourceAsString(String resourceName) {
        // ComplexSearchApiTest/resourceName
       String path = getClass().getSimpleName() + FileSystems.getDefault().getSeparator() + resourceName;
        try (InputStream inputStream = getClass().getResourceAsStream(path)) {
            assert inputStream != null;
            byte[] data = inputStream.readAllBytes();
           return new String(data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //json-unit.ignore
}
