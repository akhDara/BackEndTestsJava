package com.geekbrains.spoonacullar;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ShoppingListApiTest {

   private static String requestBody = "{\n" +
            "  \"item\": \"1 package baking powder\",\n" +
            "  \"aisle\": \"Baking\",\n" +
            "  \"parse\": \"true\" \n}";


    @BeforeEach
     void beforeAll () {
        RestAssured.baseURI = "https://api.spoonacular.com";
    }

    @Test
    @Order(1)
    @DisplayName("Add to Shopping List")
    void testAddToShoppingList () {
        RestAssured.given()
                .queryParam("apiKey", "8a31eefd36604b8ca910047c1b6a1ce0")
                .queryParam("hash", "c2d28ebfb0fb500f5d5cc658ec7ab04b8570f0f3")
                .body(requestBody)
                .log()
                .all()
                .expect()
                .statusCode(200)
                .time(Matchers.lessThanOrEqualTo(1700L))
                .when()
                .post("/mealplanner/myusername1/shopping-list/items")
                .getBody()
                .jsonPath()
                .prettyPeek();
//                .as(ApiSearchResult.class);


//       String expected = readResourceAsString("expected.json");
//        JsonAssert.assertJsonEquals(
//                expected,
//                response,
//                JsonAssert.when(Option.IGNORING_ARRAY_ORDER)
//        );

    }

    private String readResourceAsString(String resourceName) {
         String path = getClass().getSimpleName() + FileSystems.getDefault().getSeparator() + resourceName;
          try (InputStream inputStream = getClass().getResourceAsStream(path)) {
          assert inputStream != null;
         byte[] data = inputStream.readAllBytes();
          return new String(data, StandardCharsets.UTF_8);
          } catch (IOException e) {
            throw new RuntimeException(e);
         }
    }

    @Test
    @Order(2)
    @DisplayName("Get and Delete Shopping List")
    void testGetShoppingList () {
        String response1 = RestAssured.given()
                .queryParam("apiKey", "8a31eefd36604b8ca910047c1b6a1ce0")
                .queryParam("hash", "c2d28ebfb0fb500f5d5cc658ec7ab04b8570f0f3")
                .body(requestBody)
                .log()
                .all()
                .expect()
                .statusCode(200)
                .time(Matchers.lessThanOrEqualTo(1700L))
                .when()
                .get("/mealplanner/myusername1/shopping-list")
                .getBody()
                .asPrettyString();

        String id = JsonPath.from(String.valueOf(response1)).getString("aisles.items.id[0]");
        String arr[] = id.split(" ", 2);

        String first = arr[0];
        String id1 = first.substring(1, first.length() - 1);
        System.out.println(id1);

      /*Возможно сделала не правильно, но мне очень хотелось вытащить id из get, a перебросить его не смогла,
       и это был самый легкий способ
      Знаю, что так делать в жизни нельзя)
        */
        //тут начинается удаление
        Response response2 = RestAssured.given()
                .queryParam("apiKey", "8a31eefd36604b8ca910047c1b6a1ce0")
                .queryParam("hash", "c2d28ebfb0fb500f5d5cc658ec7ab04b8570f0f3")
                .log()
                .all()
                .expect()
                .statusCode(200)
                .time(Matchers.lessThanOrEqualTo(1700L))
                .when()
                .delete("/mealplanner/myusername1/shopping-list/items/" + id1)
                .then()
                .extract().response();

        Assertions.assertEquals(200, response2.statusCode());

    }
}
